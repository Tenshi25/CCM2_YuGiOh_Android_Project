package master.ccm.manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import master.ccm.ccm2yugiohproject.AddNewDeck_Activity;
import master.ccm.ccm2yugiohproject.DeckBuilder_Activity;
import master.ccm.entity.Card;
import master.ccm.entity.Deck;

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
