package master.ccm.manager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import master.ccm.ccm2yugiohproject.DeckBuilder_Activity;
import master.ccm.entity.Card;
import master.ccm.mappers.MapperCard;

public class CardManager {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static MapperCard mapperCard = new MapperCard();


    public void getAllCards(final DeckBuilder_Activity context) {
        database.collection("cards")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Card> cards = new ArrayList<>();
                            List<DocumentSnapshot> result = task.getResult().getDocuments();
                            for (DocumentSnapshot document : result) {
                                cards.add(mapperCard.mapDocumentToCard(document));
                            }
                            context.getAllCardsAsync(cards);
                        } else {
                            Log.w("selectAll", "Error getting card documents.", task.getException());
                        }
                    }
                });
    }
}
