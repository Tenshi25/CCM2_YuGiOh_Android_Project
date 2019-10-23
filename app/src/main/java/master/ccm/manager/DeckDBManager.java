package master.ccm.manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
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
import master.ccm.ccm2yugiohproject.MenuDeckList_Activity;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.entity.User;

public class DeckDBManager {
    private  FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static Boolean deckExist ;
/*
    public void AddDeck(final Deck newDeck, final CreateDeck_Activity context) {
        if(!deckExist){
            Map<String, Object> DeckMap = new HashMap<>();
            DeckMap.put("idDeck : ", CurrentUser.getInstance().getId());
            DeckMap.put("name : ", newDeck.getName());
            DeckMap.put("description : ", newDeck.getDescription());


            database.collection("Deck").add(DeckMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        Log.i("AddDeck","Le deck à été ajouter");
                        context.RegisterSucess(task.getResult().getId(),newDeck.getName());
                    }
                }
            });
        }else{
            context.RegistertFail();
        }





    }*/
    public void SelectUserDeck(User user, final MenuDeckList_Activity context) {
        database.collection("Deck")
                .whereEqualTo("capital", true)
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
                                unDeck.setDescription(document.get("prenom").toString());
                                listDeck.add(unDeck);



                            }
                            context.selectAllUserDeckFini(listDeck);
                        } else {
                            Log.w("selectAll", "Error getting documents.", task.getException());
                        }
                    }
                });




    }
    /*
    public void VerifdeckExistBeforeInsert(final Deck newDeck, final MenuDeck_Activity context) {
        deckExist=false;
        database.collection("Deck").whereEqualTo("name",newDeck.getName()).whereEqualTo("idUser",newDeck.getName()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()",""+task.getResult().size());
                    Log.i("selectDeck","Le nom existe déjà");
                    DeckDBManager.setdeckExist(true);
                    AddDeck(newDeck,context);

                }else{
                    DeckDBManager.setdeckExist(false);
                    AddDeck(newDeck,context);
                }

            }
        });



    }*/
    private static void setdeckExist(boolean p_deckExist) {
        DeckDBManager.deckExist = p_deckExist;

    }
}
