package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.Card;
import master.ccm.entity.Deck;
import master.ccm.manager.CardDBManager;
import master.ccm.manager.DeckDBManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DeckBuilder_Activity extends AppCompatActivity {
    private Deck leDeck;

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

        Intent intent= getIntent();
        Bundle extrasData = intent.getExtras();

        leDeck = new Deck();
        leDeck.setId(extrasData.get("idDeck").toString());
        leDeck.setDescription(extrasData.get("deckDesc").toString());
        leDeck.setName(extrasData.get("deckName").toString());

        Log.i("extras : ","idDeck : " + extrasData.get("idDeck").toString() + " nom :"+extrasData.get("deckName").toString());
        listViewDeckCards = (ListView) findViewById(R.id.lv_deckCards);
        listViewCards = (ListView) findViewById(R.id.lv_cards);

        DeckDBManager deckDBManager =new DeckDBManager();
        deckDBManager.selectAllCardDeck(leDeck,this);

        CardDBManager cardDBManager =new CardDBManager();
        cardDBManager.selectAllCards(this);

        /*Card aCard = new Card ();
        aCard.setId("12");
        aCard.setName("nom");
        aCard.setAtk(1200);
        aCard.setDef(1600);
        ArrayList<Card> deckCardsList = new ArrayList<Card>();
        deckCardsList.add(aCard);
        RemplirListViewDeckCard(deckCardsList);*/
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
                TextView cardLevel = (TextView) convertView.findViewById(R.id.tv_cards_buider_duplicate);
                TextView cardAtk = (TextView) convertView.findViewById(R.id.tv_cards_buider_atk);
                TextView cardDef = (TextView) convertView.findViewById(R.id.tv_cards_buider_def);
                ImageView cardImage = (ImageView) convertView.findViewById(R.id.id_iv_card_image_list);

                if(aCard.getName()!= null){
                    cardName.setText(aCard.getName());
                }
                if(aCard.getDescription()!= null){
                    cardLevel.setText(String.valueOf(aCard.getLevel()));
                }

                Log.i("UrlCard", "card url : " + aCard.getUrl());
                if (aCard.getUrl() != null) {
                    URL url = null;
                    new MyDownloadTask().execute();
                }

                cardAtk.setText(String.valueOf(aCard.getAtk()));
                cardDef.setText(String.valueOf(aCard.getDef()));
                vraiPosition++;
                return convertView;
            }
        };
        listViewCards.setAdapter(monArrayAdapter);

    }
    public void RemplirListViewDeckCard(ArrayList<Card> p_cardsList) {

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

        ArrayAdapter<Card> DeckCardArrayAdapter = new ArrayAdapter<Card>(this, R.layout.line_carddeck_builder, tabDeckCard){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logDeckPos", "pos : "+ position);

                Card aCard = tabDeckCard[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.line_carddeck_builder, parent, false);
                }
                TextView deckCardName = (TextView) convertView.findViewById(R.id.tv_deckCard_buider_name2);
                TextView deckCardDuplicate = (TextView) convertView.findViewById(R.id.tv_deckCard_buider_duplicate);
                TextView deckCardAtk = (TextView) convertView.findViewById(R.id.tv_deckCard_buider_atk);
                TextView deckCardDef = (TextView) convertView.findViewById(R.id.tv_deckCard_buider_def);

                if(aCard.getName()!= null){
                    deckCardName.setText(aCard.getName());
                }
                if(aCard.getDuplicate()!= 0){
                    deckCardDuplicate.setText(String.valueOf(aCard.getDuplicate()));
                }
                deckCardAtk.setText(String.valueOf(aCard.getAtk()));
                deckCardDef.setText(String.valueOf(aCard.getDef()));
                vraiPosition++;
                return convertView;
            }
        };
        listViewDeckCards.setAdapter(DeckCardArrayAdapter);

    }
    public void onClickAddCardToDeck(View view) {
        DeckDBManager deckDBManager = new DeckDBManager();

        //on récupère la carte selectionner
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Card cardToAdd = cardsList.get(position);

        //on ajoute le lien
        if(cardToAdd.getLimit()> cardToAdd.getDuplicate() )
        {
            deckDBManager.addLinkDeckCard(leDeck,cardToAdd,this);
            cardToAdd.setDuplicate(cardToAdd.getDuplicate()+1);
            //on met à jour le tableau et la list view
            if (!deckCardsList.contains(cardToAdd)){
            deckCardsList.add(cardToAdd);
            }

        }else{
            Toast.makeText(this,"Vous avez atient la limite",Toast.LENGTH_SHORT).show();

        }
        RemplirListViewDeckCard(deckCardsList);
    }
    public void onClickDeleteLinkCardDeck(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Card cardASupprimer = deckCardsList.get(position);


        cardASupprimer.setDuplicate(cardASupprimer.getDuplicate()-1);
        if(cardASupprimer.getDuplicate() < 1 )
        {
            DeckDBManager deckDBManager =  new DeckDBManager();
            deckDBManager.deleteLinkCardDeck(leDeck,cardASupprimer,this);
            //on met à jour le tableau et la list view
            if (deckCardsList.contains(cardASupprimer)){
                deckCardsList.remove(cardASupprimer);
            }
        }

        RemplirListViewDeckCard(deckCardsList);
        //deckDBManager.selectUserDecks(this);
    }
    public void AddLinkFail() {
        Toast.makeText(this,"La carte n'a pas été ajouter",Toast.LENGTH_SHORT).show();
    }

    public void AddLinkSucess() {
        Toast.makeText(this,"La carte a été ajouter",Toast.LENGTH_SHORT).show();
    }

    public void selectAllCardDeckFini(ArrayList<Card> listCard) {
        RemplirListViewDeckCard(listCard);
    }

    public void selectAllCards(ArrayList<Card> listCard) {
        RemplirListViewCards(listCard);
    }

    public void onClickDeleteLinkCardDeckFail() {
        Toast.makeText(this,"La carte n'a pas été retire du deck",Toast.LENGTH_SHORT).show();
    }

    public void onClickDeleteLinkCardDeckSucess() {
        Toast.makeText(this,"La carte à été retirer au deck",Toast.LENGTH_SHORT).show();

    }
    public void onClickRetour(View view) {
        Intent intent = new Intent(this, MenuDeckList_Activity.class);
        startActivity(intent);
        finish();
    }

    public void setImg(Bitmap bitmap) {
        ImageView cardImage = (ImageView) findViewById(R.id.id_iv_card_image_list);
        cardImage.setImageBitmap(bitmap);

    }


    class MyDownloadTask extends AsyncTask<Void,Void,Void>
    {

        protected void onPreExecute() {
            //display progress dialog.

        }
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://storage.googleapis.com/ygoprodeck.com/pics/07902349.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                setImg(myBitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            // dismiss progress dialog and update ui
        }
    }
}
