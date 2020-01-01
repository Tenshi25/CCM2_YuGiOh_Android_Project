package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.Card;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.entity.Phase;
import master.ccm.entity.Player;
import master.ccm.manager.DeckDBManager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Game_activity extends AppCompatActivity {
    private int nbTurn;
    private int nbplayer;
    private String nomPlayer;
    private Player currentplayer;
    private Phase currentPhase;
    private Deck playerDeck;
    private int ordrePhase = 0;
    private boolean deckPlayerCharger;

    private TextView tv_iaLifePoint;
    private TextView tv_playerLifePoint;
    private TextView tv_iaName;
    private TextView tv_playerName;

    private TextView tv_nbdeckCardPlayer;
    private TextView tv_nbdeckCardIA;

    private Button bt_drawPhase;
    private Button bt_stanby;
    private Button bt_mainPhase;
    private Button bt_battlePhase;
    private Button bt_mainPhase2;
    private Button bt_EndPhase;

    private ImageView iv_deckPlayer;
    private ImageView iv_deckIA;

    //private Evenement Chaine;
    private ArrayList<Player> listPlayer = new ArrayList<>();
    private ArrayList<Phase> listePhase = new ArrayList<>();
    Bundle extrasData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tv_iaName = findViewById(R.id.id_tv_nameIA);
        tv_iaLifePoint = findViewById(R.id.id_tv_lifepoint_IA);

        tv_playerLifePoint = findViewById(R.id.id_tv_lifepoint_player);
        tv_playerName = findViewById(R.id.id_tv_name_player);

        Intent intent= getIntent();
        extrasData= intent.getExtras();

        DeckDBManager deckDBManager = new DeckDBManager();

        if(CurrentUser.getInstance().getName() != null)
        {
            nomPlayer = CurrentUser.getInstance().getName();
        }else {
            nomPlayer = CurrentUser.getInstance().getPseudo();
        }


        //set player
        Player player = new Player();
        player.setName(nomPlayer);
        tv_playerName.setText(player.getName());
        player.setLifepoint(Integer.parseInt(extrasData.get("lifepoint").toString()));
        tv_playerLifePoint.setText(String.valueOf(player.getLifepoint()));
        player.setPlayerDeck(CurrentUser.getInstance().getDeckByID(extrasData.get("idPlayerDeck").toString()));

        listPlayer.add(player);

        //chargement du deck de lutilisateur
        Log.w("selectCard", "deck id : "+player.getPlayerDeck().getId());
        deckDBManager.selectAllCardDeck(CurrentUser.getInstance().getDeckByID(player.getPlayerDeck().getId()),this);

        // set ia


        player = new Player();
        String nomIA = extrasData.get("typeIA").toString();
        player.setName(nomIA);
        tv_iaName.setText(player.getName());
        player.setLifepoint(Integer.parseInt(extrasData.get("lifepoint").toString()));
        tv_iaLifePoint.setText(String.valueOf(player.getLifepoint()));
        Toast.makeText(this,String.valueOf(player.getLifepoint()),Toast.LENGTH_SHORT).show();
        player.setPlayerDeck(CurrentUser.getInstance().getDeckByID(extrasData.get("idIADeck").toString()));
        //player.setPlayerDeck(playerDeck);

        listPlayer.add(player);



        // find text view phase and deck
        bt_drawPhase = findViewById(R.id.bt_drawPhase);
        bt_stanby = findViewById(R.id.bt_stanbyPhase);
        bt_mainPhase = findViewById(R.id.bt_mainPhase);
        bt_battlePhase = findViewById(R.id.bt_battlePhase);
        bt_mainPhase2 = findViewById(R.id.bt_mainPhase2);
        bt_EndPhase = findViewById(R.id.bt_endPhase);

        iv_deckIA = (ImageView) findViewById(R.id.iv_background);

        tv_nbdeckCardPlayer = findViewById(R.id.tv_nbDeckCardsPlayer);
        tv_nbdeckCardIA = findViewById(R.id.tv_nbDeckCardsIA);

        iv_deckPlayer = (ImageView) findViewById(R.id.iv_deckPlayer);
        iv_deckPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                List<Card> lesCartes =listPlayer.get(0).getPlayerDeck().drawCard(1);
                ShowDrawCard((ArrayList<Card>) lesCartes);

            }

        });



    }


    public void gameStart(ArrayList<Player> p_listPlayer) {
        nbplayer = listPlayer.size();
        phaseInit();

    }
    public void nextTurn(){
        nbTurn = nbTurn +1;
        int playerTurn = nbTurn % nbplayer;
        currentplayer = listPlayer.get(playerTurn);
    }
    public void phaseInit(){
        //draw phase
        Phase newphase = new Phase("DrawPhase");
        listePhase.add(newphase);

        //standby phase
        newphase = new Phase("StandbyPhase");
        listePhase.add(newphase);

        //main phase
        newphase = new Phase("MainPhase");
        listePhase.add(newphase);

        //battle phase
        newphase = new Phase("BattlePhase");
        listePhase.add(newphase);

        // main phase 2
        newphase = new Phase("MainPhase2");
        listePhase.add(newphase);

        //end phase
        newphase = new Phase("EndPhase");
        listePhase.add(newphase);

        //set first phase
        ordrePhase =0;
    }
    public void nextPhase(){
        ordrePhase ++;
        ordrePhase =ordrePhase % 6;
        currentPhase = listePhase.get(ordrePhase);
        Toast.makeText(this,currentPhase.getName(),Toast.LENGTH_SHORT).show();
        //nouvelle draw phase
        if(ordrePhase == 0 ){
            nextTurn();
        }
    }
    public void ChangePhase(int p_numPhase){
        ordrePhase =p_numPhase;
        currentPhase = listePhase.get(ordrePhase);
        Toast.makeText(this,currentPhase.getName(),Toast.LENGTH_SHORT).show();

    }
    public void onClickBP(View view){
        ChangePhase(3);
    }
    public void onClickMP2(View view){
        ChangePhase(4);
    }
    public void onClickEP(View view){
        ChangePhase(5);
    }
    public void OnFinishSelectPlayerCard(ArrayList<Card> listCards){
        if(!deckPlayerCharger) {
            deckPlayerCharger =true;
            listPlayer.get(0).initDeckToPlay(listCards);
            Toast.makeText(this,"premiere carte "+listCards.get(0).getName(),Toast.LENGTH_SHORT).show();
            Log.w("premiere carte", "premiere carte "+listPlayer.get(0).getPlayerDeck().getListCard().get(0).getName());
            Log.w("premiere carte", "premiere carte 2"+listCards.get(0).getName());
            //listPlayer.get(0).setPlayerDeck(listPlayer.get(0).initDeckToPlay(listCards));;
            //listPlayer.get(0).getPlayerDeck().setListCard(listCards);
            DeckDBManager deckDBManager = new DeckDBManager();
            deckDBManager.selectAllCardDeck(listPlayer.get(1).getPlayerDeck(),this);
            Toast.makeText(this,"deck joueur charger ",Toast.LENGTH_SHORT).show();
            //Toast.makeText(this,"premiere carte "+listPlayer.get(0).getPlayerDeck().getListCard().get(0).getName(),Toast.LENGTH_SHORT).show();


        }else{
            //listPlayer.get(1).getPlayerDeck().setListCard(listCards);
            listPlayer.get(1).initDeckToPlay(listCards);
            majNBPlayerDeckCard();
        }
    }
    public void ShowDrawCard(ArrayList<Card> p_listCards){
        for (Card aCard : p_listCards){
                    Toast.makeText(this,"pioche :"+ aCard.getName(),Toast.LENGTH_SHORT).show();
                    Log.d("pioche",aCard.getName());
                    listPlayer.get(0).getPlayerMain().getListCards().add(aCard);
                    majNBPlayerDeckCard();
                    Log.d("pioche"," nb carte main : " + listPlayer.get(0).getPlayerMain().getListCards().size());
        }


    }
    public void majNBPlayerDeckCard(){
        tv_nbdeckCardPlayer.setText(String.valueOf(listPlayer.get(0).getPlayerDeck().getListCard().size()));
        tv_nbdeckCardIA.setText(String.valueOf(listPlayer.get(1).getPlayerDeck().getListCard().size()));
    }


}


