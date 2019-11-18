package master.ccm.manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import master.ccm.ccm2yugiohproject.AddNewDeck_Activity;
import master.ccm.ccm2yugiohproject.MenuDeckList_Activity;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.entity.User;

public class DeckDBManager {
    private  FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static Boolean deckExist ;

    public void BeforeAddDeck(final Deck deck, final AddNewDeck_Activity context) {
        database.collection("Deck").whereEqualTo("name",deck.getName()).whereEqualTo("id_user",CurrentUser.getInstance().getId()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()",""+task.getResult().size());
                    Log.i("selectDeck","Le deck existe déjà");
                    context.DeckAlreadyExists();

                }else{
                    AddDeck(deck,context);
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
                    if (task.isSuccessful()){
                        Log.i("AddDeck","Le deck à été ajouter");
                        newDeck.setId(task.getResult().getId());
                        context.AddDeckSucess(newDeck);
                    }else{
                    context.AddDeckFail();
                }
                }
            });






    }
    public void selectUserDecks( final MenuDeckList_Activity context) {
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
                            context.selectAllUserDeckFini(listDeck);
                        } else {
                            Log.w("selectAll", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private static void setdeckExist(boolean p_deckExist) {
        DeckDBManager.deckExist = p_deckExist;

    }

    public void deleteDeck (String idDeck,final MenuDeckList_Activity context){
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
}
