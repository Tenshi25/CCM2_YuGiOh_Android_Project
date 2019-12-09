package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.Card;
import master.ccm.entity.Deck;
import master.ccm.manager.CardDBManager;
import master.ccm.manager.DeckDBManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeckBuilder_Activity extends AppCompatActivity {
    private ArrayList<Card> deckCardsList ;
    private ListView listViewDeckCards;
    private List<String> tableauChainesDeckCards = new ArrayList<String>();
    private Card selectedDeckCard;
    private Card[] tabDeckCard;

    private ArrayList<Card> cardsList ;
    private ListView listViewCards;
    private List<String> tableauChainesCards = new ArrayList<String>();
    private Card selectedCard;
    private Card[] tabCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_builder);

        listViewDeckCards = (ListView) findViewById(R.id.lv_deckCards);
        listViewCards = (ListView) findViewById(R.id.lv_cards);
        Deck leDeck = new Deck();

        DeckDBManager deckDBManager =new DeckDBManager();
        deckDBManager.selectAllCardDeck(leDeck,this);

        CardDBManager cardDBManager =new CardDBManager();
        cardDBManager.selectAllCards(leDeck,this);

        //userDBManager.selectUserDecks(this);

    }

    public void RemplirListViewCards(ArrayList<Card> p_cardsList) {

        Log.i("logNomTailleListeDeck", "taille : " + p_cardsList.size());

        cardsList = p_cardsList;
        int cpt = 0;
        tabCards = new Card[p_cardsList.size()];
        Log.i("logNomTailleTabDeck", "taille : " + tabCards.length);

        for (Card oneCard : cardsList) {
            tabCards[cpt] = oneCard;
            Log.i("logDeck", cpt + " name : " + oneCard.getName());
            cpt++;
        }

        ArrayAdapter<Card> monArrayAdapter = new ArrayAdapter<Card>(this, R.layout.line_cards_builder, tabCards){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logDeckPos", "pos : "+ position);

                Card aCard = tabCards[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.line_cards_builder, parent, false);
                }
                TextView cardName = (TextView) convertView.findViewById(R.id.tv_cards_buider_name);
                TextView cardType = (TextView) convertView.findViewById(R.id.tv_cards_buider_cardtype);
                if(aCard.getName()!= null){
                    cardName.setText(aCard.getName());
                }
                if(aCard.getDescription()!= null){
                    cardType.setText(aCard.getDescription());
                }
                vraiPosition++;
                return convertView;
            }
        };
        listViewDeckCards.setAdapter(monArrayAdapter);

    }
    public void RemplirListView(ArrayList<Card> p_cardsList) {

        Log.i("logNomTailleListeDeck", "taille : " + p_cardsList.size());

        deckCardsList = p_cardsList;
        int cpt = 0;
        tabDeckCard = new Card[p_cardsList.size()];
        Log.i("logNomTailleTabDeck", "taille : " + tabDeckCard.length);

        for (Card oneCard : deckCardsList) {
            tabDeckCard[cpt] = oneCard;
            Log.i("logDeck", cpt + " name : " + oneCard.getName());
            cpt++;
        }

        ArrayAdapter<Card> monArrayAdapter = new ArrayAdapter<Card>(this, R.layout.line_carddeck_builder, tabDeckCard){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logDeckPos", "pos : "+ position);

                Card aCard = tabDeckCard[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.line_deck_menu, parent, false);
                }
                TextView nameDeck = (TextView) convertView.findViewById(R.id.tv_lineCardDeck_name);
                TextView descDeck = (TextView) convertView.findViewById(R.id.tv_lineDeck_desc);
                if(aCard.getName()!= null){
                    nameDeck.setText(aCard.getName());
                }
                if(aCard.getDescription()!= null){
                    descDeck.setText(aCard.getDescription());
                }
                vraiPosition++;
                return convertView;
            }
        };
        listViewDeckCards.setAdapter(monArrayAdapter);

    }

    public void AddLinkFail() {
        Toast.makeText(this,"La carte n'a pas été ajouter",Toast.LENGTH_SHORT).show();
    }

    public void AddLinkSucess() {
        Toast.makeText(this,"La carte a été ajouter",Toast.LENGTH_SHORT).show();
    }

    public void selectAllCardDeckFini(ArrayList<Card> listCard) {
    }

    public void selectAllCards(ArrayList<Card> listCard) {
    }
}
