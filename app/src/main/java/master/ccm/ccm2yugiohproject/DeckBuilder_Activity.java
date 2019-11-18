package master.ccm.ccm2yugiohproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import master.ccm.entity.Card;
import master.ccm.manager.CardManager;

public class DeckBuilder_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_builder);

        CardManager manager = new CardManager();
        manager.getAllCards(this);
    }


    public void getAllCardsAsync(List<Card> cardList) {
        Log.i("CARDS", "taille = " +cardList.size());

        cardList.forEach(card -> Log.i("CARDS", card.getName()));
    }
}
