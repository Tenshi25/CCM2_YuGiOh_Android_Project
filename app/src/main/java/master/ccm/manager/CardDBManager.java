package master.ccm.manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import master.ccm.ccm2yugiohproject.DeckBuilder_Activity;
import master.ccm.entity.Card;
import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class CardDBManager {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void selectAllCards(final DeckBuilder_Activity context) {
        database.collection("cards")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Card> listCard = new ArrayList<Card>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Card aCard = new Card();
                                aCard.setId(document.getId());
                                aCard.setName(document.get("name").toString());
                                aCard.setDescription(document.get("description").toString());
                                aCard.setLevel(Integer.parseInt(document.get("level").toString()));
                                aCard.setLimit(Integer.parseInt(document.get("limit").toString()));
                                aCard.setAtk(Integer.parseInt(document.get("atk").toString()));
                                aCard.setDef(Integer.parseInt(document.get("def").toString()));
                                Log.d("cardType", "cardType"+document.get("cardType"));
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
                                aCard.setUrl(document.get("imageUrl").toString());

                                listCard.add(aCard);
                            }
                            context.selectAllCards(listCard);
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
