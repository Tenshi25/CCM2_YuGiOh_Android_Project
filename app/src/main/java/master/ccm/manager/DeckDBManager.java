package master.ccm.manager;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import master.ccm.ccm2yugiohproject.AddNewDeck_Activity;
import master.ccm.ccm2yugiohproject.DeckBuilder_Activity;
import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.ccm2yugiohproject.Home;
import master.ccm.ccm2yugiohproject.MenuDeckList_Activity;
import master.ccm.entity.Card;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.PileDeCarte.Deck;
import master.ccm.entity.subcard.effect.Effect;
import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class DeckDBManager {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static Boolean deckExist;

    public void BeforeAddDeck(final Deck deck, final AddNewDeck_Activity context) {
        database.collection("Deck").whereEqualTo("name", deck.getName()).whereEqualTo("id_user", CurrentUser.getInstance().getId()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()", "" + task.getResult().size());
                    Log.i("selectDeck", "Le deck existe déjà");
                    context.DeckAlreadyExists();

                } else {
                    AddDeck(deck, context);
                }

            }
        });
    }

    public void AddDeck(final Deck newDeck, final AddNewDeck_Activity context) {

        Map<String, Object> DeckMap = new HashMap<>();
        DeckMap.put("id_user", newDeck.getIdUser());
        DeckMap.put("name", newDeck.getName());
        DeckMap.put("description", newDeck.getDescription());


        database.collection("Deck").add(DeckMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Log.i("AddDeck", "Le deck à été ajouter");
                    newDeck.setId(task.getResult().getId());
                    context.AddDeckSucess(newDeck);
                } else {
                    context.AddDeckFail();
                }
            }
        });


    }

    public void selectUserDecks(final Context context) {
        database.collection("Deck")
                .whereEqualTo("id_user", CurrentUser.getInstance().getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Deck> listDeck = new ArrayList<Deck>();
                            List<DocumentSnapshot> result = task.getResult().getDocuments();
                            for (DocumentSnapshot document : result) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Deck unDeck = new Deck();
//                                unPersonnage.setId(task.getResult().getDocuments().get(cpt).getId());
                                unDeck.setId(document.getId());
                                unDeck.setName(document.get("name").toString());
                                unDeck.setDescription(document.get("description").toString());
                                listDeck.add(unDeck);
                            }
                            if (context.getClass() == MenuDeckList_Activity.class) {
                                ((MenuDeckList_Activity) context).selectAllUserDeckFini(listDeck);
                            } else if (context.getClass() == Home.class) {
                                ((Home) context).selectAllUserDeckFini(listDeck);
                            }

                        } else {
                            Log.w("selectAll", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private static void setdeckExist(boolean p_deckExist) {
        DeckDBManager.deckExist = p_deckExist;

    }

    public void deleteDeck(String idDeck, final MenuDeckList_Activity context) {
        database.collection("Deck").document(idDeck)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("delete", "DocumentSnapshot successfully deleted!");
                        context.deleteDeckSucess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("delete", "Error deleting document", e);
                        context.deleteDeckFail();
                    }
                });

    }

    public void addLinkDeckCard(final Deck leDeck, final Card card, final DeckBuilder_Activity context) {

        database.collection("Link")
                .whereEqualTo("id_deck", leDeck.getId()).whereEqualTo("id_card", card.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() >= 1) {

                                int duplicate = card.getDuplicate();
                                //int duplicate = (int) task.getResult().getDocuments().get(0).get("duplicate");
                                if (duplicate < 4) {
                                    //duplicate = duplicate + 1;
                                    DocumentReference linkRef = database.collection("Link").document(task.getResult().getDocuments().get(0).getId());
                                    linkRef
                                            .update("duplicate", duplicate)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("update", "DocumentSnapshot successfully updated!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("update", "Error updating document", e);
                                                }
                                            });
                                }


                            } else {
                                Map<String, Object> DeckMap = new HashMap<>();
                                DeckMap.put("id_deck", leDeck.getId());
                                DeckMap.put("id_card", card.getId());
                                DeckMap.put("duplicate", 1);
                                database.collection("Link").add(DeckMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {
                                            Log.i("AddLink", "Le lien à été ajouter");

                                            context.AddLinkSucess();
                                        } else {
                                            context.AddLinkFail();
                                        }
                                    }
                                });
                            }
                        } else {
                            Log.d("addLink", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    public void selectAllCardDeck(final Deck leDeck, final Context context) {
        Log.w("selectCard", "deck id : " + leDeck.getId());
        database.collection("Link")
                .whereEqualTo("id_deck", leDeck.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            //liste de cartes
                            final ArrayList<Card> listlinkCard = new ArrayList<Card>();

                            //select des cartes du deck
                            final List<DocumentSnapshot> result = task.getResult().getDocuments();
                            for (DocumentSnapshot document : result) {
                                Log.w("selectCard", "card id : " + document.get("id_card"));
                                Card aCard = new Card();
                                aCard.setId(document.get("id_card").toString());
                                aCard.setDuplicate(Integer.parseInt(document.get("duplicate").toString()));
                                listlinkCard.add(aCard);
                            }
                            selectAllCardDeck2(listlinkCard, leDeck, context);
                            //context.selectAllCardDeckFini(listCard);

                        } else {
                            Log.w("selectAll", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void deleteLinkCardDeck(Deck leDeck, final Card card, final DeckBuilder_Activity deckBuilder_activity) {
        database.collection("Link")
                .whereEqualTo("id_deck", leDeck.getId()).whereEqualTo("id_card", card.getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() >= 1) {
                                List<DocumentSnapshot> result = task.getResult().getDocuments();
                                for (DocumentSnapshot document : result) {
                                    int nbDuplicate = card.getDuplicate();


                                    Log.d("updateLink", "" + nbDuplicate);
                                    if(nbDuplicate > 0){
                                        int duplicate = Integer.parseInt(document.get("duplicate").toString());
                                        duplicate = duplicate -1;
                                        DocumentReference linkRef = database.collection("Link").document(document.getId());
                                        linkRef
                                                .update("duplicate",duplicate)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d("updateLink", "DocumentSnapshot successfully updated!");
                                                        deckBuilder_activity.onUpdateLinkCardSuccess();
                                                    }

                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("updateLink", "Error updating document", e);
                                                    }
                                                });
                                    }else{
                                        database.collection("Link").document(document.getId())
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d("delete", "DocumentSnapshot successfully deleted!");
                                                        deckBuilder_activity.onClickDeleteLinkCardDeckSucess();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("delete", "Error deleting document", e);
                                                        deckBuilder_activity.onClickDeleteLinkCardDeckFail();
                                                    }
                                                });
                                    }
                                }

                            }
                        }else{
                            Log.d("delLink", "il y a un lien multiple");
                        }
                    }

    });


    }
    public void selectAllCardDeck2(final ArrayList<Card> tabidCardDeck,Deck leDeck,final Context context) {
        database.collection("cards").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Card> listCards = new ArrayList<Card>();
                    List<DocumentSnapshot> result = task.getResult().getDocuments();
                    for (DocumentSnapshot document : result) {
                        for (Card lalinkCard : tabidCardDeck) {

                            String iddoc = document.getId();
                            if (lalinkCard.getId().equals(iddoc)) {

                                Card aCard = new Card();
                                aCard.setId(document.getId());
                                Log.w("selectCard", "card id : " + aCard.getId());
                                aCard.setReference(document.get("reference").toString());
                                aCard.setName(document.get("name").toString());
                                Log.w("selectCard", "card name : " + aCard.getName());
                                aCard.setLevel(Integer.parseInt(document.get("level").toString()));
                                aCard.setLimit(Integer.parseInt(document.get("limit").toString()));
                                aCard.setAtk(Integer.parseInt(document.get("atk").toString()));
                                aCard.setDef(Integer.parseInt(document.get("def").toString()));
                                if(document.get("cardType")!= null){
                                    aCard.setCardType(CardType.valueOf(document.get("cardType").toString()));
                                }
                                if(document.get("cardSubType") != null){
                                    aCard.setCardSubType((CardTypeSub.valueOf(document.get("cardSubType").toString())));
                                }
                                if(document.get("categorized") != null){
                                    aCard.setCategorized((CategorizedType.valueOf(document.get("categorized").toString())));
                                }
                                if(document.get("attribute") != null){
                                    aCard.setAttribut(((AttributeType.valueOf(document.get("attribute").toString()))));
                                }

                                if (document.get("effects") != null) {
                                    ObjectMapper mapper = new ObjectMapper();
                                    List<Effect> myObjects = null;
                                    try {
                                        myObjects = mapper.readValue(mapper.writeValueAsString(document.get("effects")), mapper.getTypeFactory().constructCollectionType(List.class, Effect.class));
                                        aCard.setEffects(myObjects);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                aCard.setDuplicate(lalinkCard.getDuplicate());
                                aCard.setDescription(document.get("description").toString());
                                aCard.setUrl(document.get("imageUrl").toString());

                                listCards.add(aCard);
                                break;
                            }
                        }
                    }
                    if(context.getClass() == DeckBuilder_Activity.class)
                    {
                        ((DeckBuilder_Activity) context).selectAllCardDeckFini(listCards);
                    }else if(context.getClass() == Game_activity.class){
                        ((Game_activity) context).OnFinishSelectPlayerCard(listCards);
                    }

                } else {
                    Log.w("selectAll", "Error getting documents.", task.getException());
                }
            }
        });
    }
}


