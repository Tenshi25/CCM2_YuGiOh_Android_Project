package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.ccm2yugiohproject.utils.LoadImageTask;
import master.ccm.entity.Action;
import master.ccm.entity.BattleSystem;
import master.ccm.entity.Card;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.entity.Effects.EffectGainPertePV;
import master.ccm.entity.Effects.EffectInvoquerNormale;
import master.ccm.entity.Effects.EffectPioche;
import master.ccm.entity.Effects.EffectPoseNormal;
import master.ccm.entity.PileDeCarte.Deck;
import master.ccm.entity.Phase;
import master.ccm.entity.PileDeCarte.Main;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;
import master.ccm.entity.subcard.Piege;
import master.ccm.manager.DeckDBManager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

    private ImageView iv_imageCardZoom;
    private TextView tv_descCardZoom;

    private TextView tv_nbdeckCardPlayer;
    private TextView tv_nbdeckCardIA;

    private Button bt_drawPhase;
    private Button bt_stanby;
    private Button bt_mainPhase;
    private Button bt_battlePhase;
    private Button bt_mainPhase2;
    private Button bt_EndPhase;

    //button
    private Button bt_activer;
    private Button bt_invocation;
    private Button bt_poser;
    private Button bt_attaquer;
    private Button bt_changerPosition;

    private ImageView iv_deckPlayer;
    private ImageView iv_deckIA;

    private ArrayList<Action>listActionsEffect = new ArrayList<>();

    private  ImageView iv_mainPlayerCard_1;
    private  ImageView iv_mainPlayerCard_2;
    private  ImageView iv_mainPlayerCard_3;
    private  ImageView iv_mainPlayerCard_4;
    private  ImageView iv_mainPlayerCard_5;
    private  ImageView iv_mainPlayerCard_6;
    private  ImageView iv_mainPlayerCard_7;
    private  ImageView iv_mainPlayerCard_8;
    private  ImageView iv_mainPlayerCard_9;
    private  ImageView iv_mainPlayerCard_10;

    private  ImageView iv_mainIACard_1;
    private  ImageView iv_mainIACard_2;
    private  ImageView iv_mainIACard_3;
    private  ImageView iv_mainIACard_4;
    private  ImageView iv_mainIACard_5;
    private  ImageView iv_mainIACard_6;
    private  ImageView iv_mainIACard_7;
    private  ImageView iv_mainIACard_8;
    private  ImageView iv_mainIACard_9;
    private  ImageView iv_mainIACard_10;

    private  ImageView iv_terrainMonstreJoueur_1;
    private  ImageView iv_terrainMonstreJoueur_2;
    private  ImageView iv_terrainMonstreJoueur_3;
    private  ImageView iv_terrainMonstreJoueur_4;
    private  ImageView iv_terrainMonstreJoueur_5;

    private  ImageView iv_terrainMagieJoueur_1;
    private  ImageView iv_terrainMagieJoueur_2;
    private  ImageView iv_terrainMagieJoueur_3;
    private  ImageView iv_terrainMagieJoueur_4;
    private  ImageView iv_terrainMagieJoueur_5;

    private  ImageView iv_terrainMonstreIA_1;
    private  ImageView iv_terrainMonstreIA_2;
    private  ImageView iv_terrainMonstreIA_3;
    private  ImageView iv_terrainMonstreIA_4;
    private  ImageView iv_terrainMonstreIA_5;

    private  ImageView iv_terrainMagieIA_1;
    private  ImageView iv_terrainMagieIA_2;
    private  ImageView iv_terrainMagieIA_3;
    private  ImageView iv_terrainMagieIA_4;
    private  ImageView iv_terrainMagieIA_5;



    private ArrayList<ImageView> listIvMain= new ArrayList<>();
    //private Evenement Chaine;
    private ArrayList<Player> listPlayer = new ArrayList<>();
    private ArrayList<Phase> listePhase = new ArrayList<>();
    Bundle extrasData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bt_invocation =findViewById(R.id.bt_invocation);
        bt_poser =findViewById(R.id.bt_poser);
        bt_activer =findViewById(R.id.bt_activer);
        bt_changerPosition =findViewById(R.id.bt_changer_position);
        bt_attaquer = findViewById(R.id.bt_attaquer);

        tv_iaName = findViewById(R.id.id_tv_nameIA);
        tv_iaLifePoint = findViewById(R.id.id_tv_lifepoint_IA);

        tv_playerLifePoint = findViewById(R.id.id_tv_lifepoint_player);
        tv_playerName = findViewById(R.id.id_tv_name_player);

        iv_mainIACard_1 = findViewById(R.id.iv_mainIA_1);
        iv_mainIACard_2 = findViewById(R.id.iv_mainIA_2);
        iv_mainIACard_3 = findViewById(R.id.iv_mainIA_3);
        iv_mainIACard_4 = findViewById(R.id.iv_mainIA_4);
        iv_mainIACard_5 = findViewById(R.id.iv_mainIA_5);
        iv_mainIACard_6 = findViewById(R.id.iv_mainIA_6);
        iv_mainIACard_7 = findViewById(R.id.iv_mainIA_7);
        iv_mainIACard_8 = findViewById(R.id.iv_mainIA_8);
        iv_mainIACard_9 = findViewById(R.id.iv_mainIA_9);
        iv_mainIACard_10 = findViewById(R.id.iv_mainIA_10);

        iv_mainPlayerCard_1 = findViewById(R.id.iv_mainPlayer_1);
        iv_mainPlayerCard_2 = findViewById(R.id.iv_mainPlayer_2);
        iv_mainPlayerCard_3 = findViewById(R.id.iv_mainPlayer_3);
        iv_mainPlayerCard_4 = findViewById(R.id.iv_mainPlayer_4);
        iv_mainPlayerCard_5 = findViewById(R.id.iv_mainPlayer_5);
        iv_mainPlayerCard_6 = findViewById(R.id.iv_mainPlayer_6);
        iv_mainPlayerCard_7 = findViewById(R.id.iv_mainPlayer_7);
        iv_mainPlayerCard_8 = findViewById(R.id.iv_mainPlayer_8);
        iv_mainPlayerCard_9 = findViewById(R.id.iv_mainPlayer_9);
        iv_mainPlayerCard_10 = findViewById(R.id.iv_mainPlayer_10);

        iv_terrainMonstreJoueur_1 =findViewById(R.id.iv_terrainMonstre1_joueur);
        iv_terrainMonstreJoueur_2 = findViewById(R.id.iv_terrainMonstre2_joueur);
        iv_terrainMonstreJoueur_3 = findViewById(R.id.iv_terrainMonstre3_joueur);
        iv_terrainMonstreJoueur_4 =findViewById(R.id.iv_terrainMonstre4_joueur);
        iv_terrainMonstreJoueur_5 =findViewById(R.id.iv_terrainMonstre5_joueur);

        ImageView[] tabIv_terrainMonstre = new ImageView[5];
        tabIv_terrainMonstre[0] = iv_terrainMonstreJoueur_1;
        tabIv_terrainMonstre[1] = iv_terrainMonstreJoueur_2;
        tabIv_terrainMonstre[2] = iv_terrainMonstreJoueur_3;
        tabIv_terrainMonstre[3] = iv_terrainMonstreJoueur_4;
        tabIv_terrainMonstre[4] = iv_terrainMonstreJoueur_5;

        iv_terrainMagieJoueur_1 =findViewById(R.id.iv_terrainMagie1_joueur);
        iv_terrainMagieJoueur_2 = findViewById(R.id.iv_terrainMagie2_joueur);
        iv_terrainMagieJoueur_3 = findViewById(R.id.iv_terrainMagie3_joueur);
        iv_terrainMagieJoueur_4 =findViewById(R.id.iv_terrainMagie4_joueur);
        iv_terrainMagieJoueur_5 =findViewById(R.id.iv_terrainMagie5_joueur);

        ImageView[] tabIv_terrainMagiePiege = new ImageView[5];
        tabIv_terrainMagiePiege[0] = iv_terrainMagieJoueur_1;
        tabIv_terrainMagiePiege[1] = iv_terrainMagieJoueur_2;
        tabIv_terrainMagiePiege[2] = iv_terrainMagieJoueur_3;
        tabIv_terrainMagiePiege[3] = iv_terrainMagieJoueur_4;
        tabIv_terrainMagiePiege[4] = iv_terrainMagieJoueur_5;

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
        listIvMain.add(iv_mainPlayerCard_1);
        listIvMain.add(iv_mainPlayerCard_2);
        listIvMain.add(iv_mainPlayerCard_3);
        listIvMain.add(iv_mainPlayerCard_4);
        listIvMain.add(iv_mainPlayerCard_5);
        listIvMain.add(iv_mainPlayerCard_6);
        listIvMain.add(iv_mainPlayerCard_7);
        listIvMain.add(iv_mainPlayerCard_8);
        listIvMain.add(iv_mainPlayerCard_9);
        listIvMain.add(iv_mainPlayerCard_10);

        tv_descCardZoom = findViewById(R.id.tv_descCardZoom);
        iv_imageCardZoom = findViewById(R.id.iv_cardViewZoom);

        player.getPlayerMain().setImageViewZoom(iv_imageCardZoom);
        player.getPlayerMain().setDescCardZoom(tv_descCardZoom);

        player.getPlayerMain().setListIv_main(listIvMain);



        player.setName(nomPlayer);
        tv_playerName.setText(player.getName());
        player.setLifepoint(Integer.parseInt(extrasData.get("lifepoint").toString()));
        tv_playerLifePoint.setText(String.valueOf(player.getLifepoint()));
        //player.setPlayerDeck(CurrentUser.getInstance().getDeckByID(extrasData.get("idPlayerDeck").toString()));
        Deck playerDeck = new Deck();
        playerDeck.setId(extrasData.get("idPlayerDeck").toString());
        player.setPlayerDeck(playerDeck);

        player.getPlayerTerrain().setTableauZoneMonstreImageView(tabIv_terrainMonstre);
        player.getPlayerTerrain().setTableauZoneMagiePiegeImageView(tabIv_terrainMagiePiege);
        listPlayer.add(player);

        //chargement du deck de lutilisateur
        Log.w("selectCard", "deck id : "+player.getPlayerDeck().getId());
        deckDBManager.selectAllCardDeck(CurrentUser.getInstance().getDeckByID(player.getPlayerDeck().getId()),this);

        // set ia

        ArrayList<ImageView> listIvMainIA = new ArrayList<>();
        listIvMainIA.add(iv_mainIACard_1);
        listIvMainIA.add(iv_mainIACard_2);
        listIvMainIA.add(iv_mainIACard_3);
        listIvMainIA.add(iv_mainIACard_4);
        listIvMainIA.add(iv_mainIACard_5);
        listIvMainIA.add(iv_mainIACard_6);
        listIvMainIA.add(iv_mainIACard_7);
        listIvMainIA.add(iv_mainIACard_8);
        listIvMainIA.add(iv_mainIACard_9);
        listIvMainIA.add(iv_mainIACard_10);

        Player IAplayer = new Player();

        IAplayer.getPlayerMain().setListIv_main(listIvMainIA);
        String nomIA = extrasData.get("typeIA").toString();
        IAplayer.setName(nomIA);
        tv_iaName.setText(IAplayer.getName());
        IAplayer.setLifepoint(Integer.parseInt(extrasData.get("lifepoint").toString()));
        tv_iaLifePoint.setText(String.valueOf(IAplayer.getLifepoint()));
        Toast.makeText(this,String.valueOf(IAplayer.getLifepoint()),Toast.LENGTH_SHORT).show();
        //IAplayer.setPlayerDeck(CurrentUser.getInstance().getDeckByID(extrasData.get("idIADeck").toString()));
        Deck IADeck = new Deck();
        IADeck.setId(extrasData.get("idIADeck").toString());
        IAplayer.setPlayerDeck(IADeck);
        //player.setPlayerDeck(playerDeck);

        listPlayer.add(IAplayer);



        // find text view phase and deck
        bt_drawPhase = findViewById(R.id.bt_drawPhase);
        bt_stanby = findViewById(R.id.bt_stanbyPhase);
        bt_mainPhase = findViewById(R.id.bt_mainPhase);
        bt_battlePhase = findViewById(R.id.bt_battlePhase);
        bt_mainPhase2 = findViewById(R.id.bt_mainPhase2);
        bt_EndPhase = findViewById(R.id.bt_endPhase);

        iv_deckIA = (ImageView) findViewById(R.id.iv_deckPlayerIA);

        tv_nbdeckCardPlayer = findViewById(R.id.tv_nbDeckCardsPlayer);
        tv_nbdeckCardIA = findViewById(R.id.tv_nbDeckCardsIA);

        iv_deckPlayer = (ImageView) findViewById(R.id.iv_deckPlayer);


        bt_activer.setVisibility(View.GONE);
        bt_invocation.setVisibility(View.GONE);
        bt_poser.setVisibility(View.GONE);
        bt_attaquer.setVisibility(View.GONE);
        bt_changerPosition.setVisibility(View.GONE);

    }
    public void clickHandler(View v) {
        Player lejoueur=listPlayer.get(0);
        Path path = new Path();
        ObjectAnimator animator ;
        switch (v.getId()) {
            case R.id.iv_deckPlayer:
                if (currentPhase.containsActionByName("PiocheNormale") && currentplayer.equals(listPlayer.get(0))) {

                    currentPhase.findActionByName("PiocheNormale").getEffect().execute(v.getContext(), listPlayer, 0, 1, null, null, null);
                    //List<Card> lesCartes = listPlayer.get(0).getPlayerDeck().drawCard(1);

                    this.majNBPlayerDeckCard();

                    //ShowDrawCard((ArrayList<Card>) lesCartes);
                    majMain();
                    nextPhase();
                }
                break;
            case R.id.iv_mainPlayer_1:

                if(lejoueur.getPlayerMain().getListCards().size() > 0) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(0), iv_mainPlayerCard_1,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(0) );
                }
                break;
            case R.id.iv_mainPlayer_2:
                if(lejoueur.getPlayerMain().getListCards().size() > 1 ) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(1), iv_mainPlayerCard_2,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(1) );
                }
                break;
            case R.id.iv_mainPlayer_3:

                if(lejoueur.getPlayerMain().getListCards().size() > 2) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(2), iv_mainPlayerCard_3,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(2) );
                }
                break;
            case R.id.iv_mainPlayer_4:

                if(lejoueur.getPlayerMain().getListCards().size() > 3) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(3), iv_mainPlayerCard_4,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(3) );
                }
                break;
            case R.id.iv_mainPlayer_5:

                if(lejoueur.getPlayerMain().getListCards().size() > 4) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(4), iv_mainPlayerCard_5,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(4) );
                }
                break;
            case R.id.iv_mainPlayer_6:

                if(lejoueur.getPlayerMain().getListCards().size() > 5) {


                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(5), iv_mainPlayerCard_6,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(5) );
                }
                break;
            case R.id.iv_mainPlayer_7:

                if(lejoueur.getPlayerMain().getListCards().size() > 6) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(6), iv_mainPlayerCard_7,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(6) );
                }
                break;
            case R.id.iv_mainPlayer_8:

                if(lejoueur.getPlayerMain().getListCards().size() > 7) {

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(7), iv_mainPlayerCard_8,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(7) );
                }
                break;
            case R.id.iv_mainPlayer_9:
                if(lejoueur.getPlayerMain().getListCards().size() > 8){

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(8), iv_mainPlayerCard_9,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(8) );
                }
                break;
            case R.id.iv_mainPlayer_10:
                if(lejoueur.getPlayerMain().getListCards().size() > 9){

                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerMain().getListCards().get(9),iv_mainPlayerCard_10,this,"Main");
                    majbtInvocation(lejoueur.getPlayerMain().getListCards().get(9) );
                }
                break;
            case R.id.iv_terrainMonstre1_joueur:
                if(lejoueur.getPlayerTerrain().getCardZoneMonstre(0) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMonstre(0),iv_terrainMonstreJoueur_1,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMonstre(0) );
                    }
                break;
            case R.id.iv_terrainMonstre2_joueur:

                if(listPlayer.get(0).getPlayerTerrain().getCardZoneMonstre(1) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMonstre(1),iv_terrainMonstreJoueur_2,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMonstre(1) );
                }
                break;
            case R.id.iv_terrainMonstre3_joueur:

                if(listPlayer.get(0).getPlayerTerrain().getCardZoneMonstre(2) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMonstre(2),iv_terrainMonstreJoueur_3,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMonstre(2) );
                }
                break;
            case R.id.iv_terrainMonstre4_joueur:
                if(listPlayer.get(0).getPlayerTerrain().getCardZoneMonstre(3) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMonstre(3),iv_terrainMonstreJoueur_4,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMonstre(3) );
                }
                break;
            case R.id.iv_terrainMonstre5_joueur:

                if(listPlayer.get(0).getPlayerTerrain().getCardZoneMonstre(4) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMonstre(4),iv_terrainMonstreJoueur_5,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMonstre(4) );
                }
                break;
            case R.id.iv_terrainMonstre1_ia:

                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(0) != null) {
                    lejoueur.getPlayerMain().setFrom("Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(0) );
                    Picasso.with(this).load(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(0).getUrl()).error(R.drawable.cardunknow).into(this.iv_imageCardZoom);
                    tv_descCardZoom.setText(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(0).getDescription());
                }
                break;
            case R.id.iv_terrainMonstre2_ia:

                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(1) != null) {
                    lejoueur.getPlayerMain().setFrom("Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(1) );
                    Picasso.with(this).load(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(1).getUrl()).error(R.drawable.cardunknow).into(this.iv_imageCardZoom);
                    tv_descCardZoom.setText(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(1).getDescription());
                }
                break;
            case R.id.iv_terrainMonstre3_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(2) != null) {
                    lejoueur.getPlayerMain().setFrom("Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(2) );
                    Picasso.with(this).load(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(2).getUrl()).error(R.drawable.cardunknow).into(this.iv_imageCardZoom);
                    tv_descCardZoom.setText(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(2).getDescription());
                }
                break;
            case R.id.iv_terrainMonstre4_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(3) != null) {
                    lejoueur.getPlayerMain().setFrom("Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(3) );
                    Picasso.with(this).load(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(3).getUrl()).error(R.drawable.cardunknow).into(this.iv_imageCardZoom);
                    tv_descCardZoom.setText(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(3).getDescription());
                }
                break;
            case R.id.iv_terrainMonstre5_ia:

                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(4) != null) {
                    lejoueur.getPlayerMain().setFrom("Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(4) );
                    Picasso.with(this).load(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(4).getUrl()).error(R.drawable.cardunknow).into(this.iv_imageCardZoom);
                    tv_descCardZoom.setText(listPlayer.get(1).getPlayerTerrain().getCardZoneMonstre(4).getDescription());
                }
                break;
            case R.id.iv_terrainMagie1_joueur:
                if(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(0) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(0),iv_terrainMagieJoueur_1,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(0) );
                }
                break;
            case R.id.iv_terrainMagie2_joueur:
                if(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(1) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(1),iv_terrainMagieJoueur_1,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(1) );
                }
                break;
            case R.id.iv_terrainMagie3_joueur:
                if(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(2) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(2),iv_terrainMagieJoueur_1,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(2) );
                }
                break;
            case R.id.iv_terrainMagie4_joueur:
                if(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(3) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(3),iv_terrainMagieJoueur_1,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(3) );
                }
                break;
            case R.id.iv_terrainMagie5_joueur:
                if(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(4) != null) {
                    lejoueur.getPlayerMain().changeSelectedCard(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(4),iv_terrainMagieJoueur_1,this,"Terrain");
                    majbtInvocation(lejoueur.getPlayerTerrain().getCardZoneMagiePiege(4) );
                }
                break;
            case R.id.iv_terrainMagie1_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0) != null) {
                    listPlayer.get(1).getPlayerMain().changeSelectedCard(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0),iv_terrainMagieIA_1,this,"Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0) );
                }
                break;
            case R.id.iv_terrainMagie2_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(1) != null) {
                    listPlayer.get(1).getPlayerMain().changeSelectedCard(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(1),iv_terrainMagieIA_2,this,"Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0) );
                }
                break;
            case R.id.iv_terrainMagie3_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(2) != null) {
                    listPlayer.get(1).getPlayerMain().changeSelectedCard(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(2),iv_terrainMagieIA_3,this,"Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0) );
                }
                break;
            case R.id.iv_terrainMagie4_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(3) != null) {
                    listPlayer.get(1).getPlayerMain().changeSelectedCard(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(3),iv_terrainMagieIA_4,this,"Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0) );
                }
                break;
            case R.id.iv_terrainMagie5_ia:
                if(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(4) != null) {
                    listPlayer.get(1).getPlayerMain().changeSelectedCard(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(4),iv_terrainMagieIA_5,this,"Terrain");
                    majbtInvocation(listPlayer.get(1).getPlayerTerrain().getCardZoneMagiePiege(0) );
                }
                break;
            default:
                break;
        }
    }

    public void gameStart() {
        // init list actions

        EffectPioche effectPioche = new EffectPioche();
        Action actionPioche = new Action("Pioche",effectPioche );
        //listActionsEffect.add(actionPioche);

        nbplayer = listPlayer.size();
        currentplayer = listPlayer.get(0);
        Log.d("remiplayer",currentplayer.getName());
        Log.d("remiplayer",listPlayer.get(0).getName());
        phaseInit();
        //for (int i=0; i==listPlayer.size() ;i++){
        listPlayer.get(0).getPlayerDeck().shuffleDeck();
        listPlayer.get(1).getPlayerDeck().shuffleDeck();

        actionPioche.getEffect().execute(this,listPlayer,0,5,null,null,null);
        actionPioche.getEffect().execute(this,listPlayer,1,5,null,null,null);


        majMain();
        majNBPlayerDeckCard();
        //gameCore();

    };
/*
    private void gameCore() {

        if(listPlayer.get(0).getLifepoint() <=0 || listPlayer.get(1).getLifepoint() <=0){

        }else{
            gameCore();
        }
    }*/

    public void nextTurn(){
        nbTurn = nbTurn +1;
        Log.d("numPhasenbTurn",""+nbTurn);
        Log.d("numPhasenbplayer",""+nbplayer);
        int playerTurn = nbTurn % nbplayer;
        currentplayer = listPlayer.get(playerTurn);
        Toast.makeText(this,"Changement de tour !",Toast.LENGTH_SHORT).show();
    }


    //initialisation avant partie
    public void phaseInit(){
        //draw phase
        Phase newphase = new Phase("DrawPhase");
        EffectPioche effectPioche = new EffectPioche();
        Action actionPioche = new Action("PiocheNormale",effectPioche );


        //action activer carte piege
        ArrayList<Action> listeAction =  new ArrayList<Action>();
        listeAction.add(actionPioche);
        newphase.setListAction(listeAction);
        listePhase.add(newphase);

        //standby phase
        newphase = new Phase("StandbyPhase");
        listePhase.add(newphase);

        //main phase
        newphase = new Phase("MainPhase");

        //Ajout de l'action Invocation Normale à la liste d'action
        EffectInvoquerNormale effectInvoquerNormale = new EffectInvoquerNormale();
        Action actionInvocationNormale = new Action("InvocationNormale",effectInvoquerNormale );
        listeAction =  new ArrayList<Action>();
        listeAction.add(actionInvocationNormale);
        newphase.setListAction(listeAction);

        //Ajout de l'action poser à la liste d'action
        EffectPoseNormal effectPoseNormale = new EffectPoseNormal();
        Action actionPoseNormale = new Action("PoseNormale",effectPoseNormale );
        listeAction.add(actionPoseNormale);

        listePhase.add(newphase);

        //battle phase
        newphase = new Phase("BattlePhase");
        listePhase.add(newphase);

        // main phase 2
        newphase = new Phase("MainPhase2");

        //Ajout de l'action Invocation Normale à la liste d'action
        effectInvoquerNormale = new EffectInvoquerNormale();
        actionInvocationNormale = new Action("InvocationNormale",effectInvoquerNormale );
        listeAction =  new ArrayList<Action>();
        listeAction.add(actionInvocationNormale);
        newphase.setListAction(listeAction);

        //Ajout de l'action poser à la liste d'action
        effectPoseNormale = new EffectPoseNormal();
        actionPoseNormale = new Action("PoseNormale",effectPoseNormale );
        listeAction.add(actionPoseNormale);

        listePhase.add(newphase);

        //end phase
        newphase = new Phase("EndPhase");
        listePhase.add(newphase);

        //set first phase
        ChangePhase(0);
    }

    //permet de passer à la phase suivante
    public void nextPhase(){
        ordrePhase ++;
        if(ordrePhase == 6 ){
            nextTurn();
        }
        ordrePhase =ordrePhase % 6;
        ChangePhase(ordrePhase);
        /*currentPhase = listePhase.get(ordrePhase);
        Toast.makeText(this,currentPhase.getName(),Toast.LENGTH_SHORT).show();*/
        //nouvelle draw phase

    }

    //permet de changer de phase
    public void ChangePhase(int p_numPhase){
        Log.d("numPhase",""+p_numPhase);
        Log.d("numPhasecurrentplayer",""+currentplayer.getName());
        ordrePhase =p_numPhase;
        currentPhase = listePhase.get(ordrePhase);
        Toast.makeText(this,currentPhase.getName(),Toast.LENGTH_LONG).show();
        if (currentplayer.getName().equals(listPlayer.get(0).getName())){
            switch (ordrePhase){

                case 0:
                    //draw phase
                    currentplayer.setCountInvocationNormale(0);
                    blinkdeck();
                    //Picasso.with(this).load(R.drawable.cardcoverlight).error(R.drawable.cardunknow).into(this.iv_deckPlayer);

                    bt_drawPhase.setTextColor(Color.BLUE);
                    bt_stanby.setTextColor(Color.BLUE);
                    bt_mainPhase.setTextColor(Color.BLUE);
                    bt_battlePhase.setTextColor(Color.BLUE);
                    bt_mainPhase2.setTextColor(Color.BLUE);
                    bt_EndPhase.setTextColor(Color.BLUE);
                    break;

                case 1:
                    //stand by
                    //Picasso.with(this).load(R.drawable.cardcover).error(R.drawable.cardunknow).into(this.iv_deckPlayer);
                    nextPhase();
                    break;

                case 2:
                    //Main phase
                    iv_deckPlayer.clearAnimation();
                    if(currentplayer.getPlayerMain().getSelectedCard().getCardType() != null){
                        majbtInvocation(currentplayer.getPlayerMain().getSelectedCard());
                    }

                    break;
                case 3:
                    //Battle phase
                    majbtInvocation(currentplayer.getPlayerMain().getSelectedCard());
                    break;
                case 4:
                    //main phase 2
                    break;
                case 5:
                    //end phase
                    currentplayer.getPlayerTerrain().desetCountAtk();
                    currentplayer.getPlayerTerrain().desetMalActivation();
                    nextPhase();
                    break;
            }

        }else{
            switch (ordrePhase){

                case 0:
                    //draw phase
                    currentplayer.setCountInvocationNormale(0);
                    bt_drawPhase.setTextColor(Color.RED);
                    bt_stanby.setTextColor(Color.RED);
                    bt_mainPhase.setTextColor(Color.RED);
                    bt_battlePhase.setTextColor(Color.RED);
                    bt_mainPhase2.setTextColor(Color.RED);
                    bt_EndPhase.setTextColor(Color.RED);
                    //bt_EndPhase.setBackgroundColor(Color.RED);


                    currentPhase.findActionByName("PiocheNormale").getEffect().execute(this,listPlayer,1,1,null,null,null);

                    // test pour faire perdre un des joueur avec les pv
                    /*EffectGainPertePV perte500 = new EffectGainPertePV();
                    Action perteAction = new Action("pertePV",perte500);
                    perteAction.getEffect().execute(this,listPlayer,0,-500,null,null,null);
                    majPv();*/
                    majMain();
                    majNBPlayerDeckCard();
                    nextPhase();
                    break;

                case 1:
                case 2:
                case 3:
                case 4:
                    //stand by
                    //Main phase
                    //Battle phase
                    //main phase 2
                    nextPhase();
                    break;
                case 5:
                    //end phase
                    //nextTurn();
                    currentplayer.getPlayerTerrain().desetCountAtk();
                    currentplayer.getPlayerTerrain().desetMalActivation();
                    nextPhase();
                    break;
            }
        }



    }
    public void onClickBP(View view){
        if(ordrePhase == 2) {
            ChangePhase(3);
        }
    }
    public void onClickMP2(View view){
        if(ordrePhase == 3) {
            ChangePhase(4);
        }
    }
    public void onClickEP(View view){
        if(ordrePhase <5 && ordrePhase >1) {
            ChangePhase(5);
        }
    }
    public void OnFinishSelectPlayerCard(ArrayList<Card> listCards){
        if(!deckPlayerCharger) {
            deckPlayerCharger =true;
            listPlayer.get(0).initDeckToPlay(listCards,listPlayer.get(0));
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
            listPlayer.get(1).initDeckToPlay(listCards,listPlayer.get(1));
            majNBPlayerDeckCard();
            gameStart();
        }
    }
    public void ShowDrawCard(ArrayList<CardInGame> p_listCards){
        for (CardInGame aCard : p_listCards){
                    Toast.makeText(this,"pioche :"+ aCard.getName(),Toast.LENGTH_SHORT).show();
                    Log.d("pioche",aCard.getName());
                    listPlayer.get(0).getPlayerMain().getListCards().add(aCard);
                    majNBPlayerDeckCard();
                    Log.d("pioche"," nb carte main : " + listPlayer.get(0).getPlayerMain().getListCards().size());
        }


    }
    public void majNBPlayerDeckCard(){

        // nombre de carte du joueur
        int nbcardDeckPlayer = listPlayer.get(0).getPlayerDeck().getListCard().size();
        tv_nbdeckCardPlayer.setText(String.valueOf(nbcardDeckPlayer));

        // on fait disparaitre l'image si on a plus de carte dans  le deck du joueur

        if(nbcardDeckPlayer <= 0){
            //iv_deckPlayer.setVisibility(View.INVISIBLE);
            iv_deckPlayer.setImageResource(R.drawable.caseterrain);
        }else{
            iv_deckPlayer.setImageResource(R.drawable.cardcover);
        }

        //nombre de carte de l'ia
        int nbcardDeckIA = listPlayer.get(1).getPlayerDeck().getListCard().size();
        tv_nbdeckCardIA.setText(String.valueOf(nbcardDeckIA));


        // on fait disparaitre l'image si on a plus de carte dans  le deck de l'ia
        if(nbcardDeckIA <= 0){
            iv_deckIA.setImageResource(R.drawable.caseterrain);

        }else{
            iv_deckIA.setImageResource(R.drawable.cardcover);
        }

        //Toast.makeText(this,"joueur ia :"+ listPlayer.get(1).getPlayerDeck().getListCard().size(),Toast.LENGTH_SHORT).show();
    }
    public void majPv(){
        tv_iaLifePoint.setText(String.valueOf(listPlayer.get(1).getLifepoint()));
        tv_playerLifePoint.setText(String.valueOf(listPlayer.get(0).getLifepoint()));
    }
    public void majMain(){
        listPlayer.get(0).getPlayerMain().majMain(listPlayer.get(0),this);
        listPlayer.get(1).getPlayerMain().majMainNotVisible(listPlayer.get(1),this);
    }

    public void onClickInvocation (View view){
        Main currentplayerMain =currentplayer.getPlayerMain();
        Log.w("invocation", "count : "+currentplayer.getCountInvocationNormale() + " count max : "+ currentplayer.getMaxInvocationNormale());
        if (currentPhase.containsActionByName("InvocationNormale") && currentplayer.equals(listPlayer.get(0))) {
            if (currentplayer.getCountInvocationNormale() < currentplayer.getMaxInvocationNormale()) {
                Action invocation = currentPhase.findActionByName("InvocationNormale");

                if (currentplayerMain.getSelectedCard() != null) {
                    ArrayList<CardInGame>listfiltre =new  ArrayList<>();
                    listfiltre.add(currentplayerMain.getSelectedCard());
                    invocation.getEffect().execute(this,listPlayer,0,1,currentplayer.getPlayerMain(),currentplayer.getPlayerTerrain(),listfiltre);
                    majbtInvocation(currentplayerMain.getSelectedCard());
                    /*
                    /*listPlayer.get(0).getPlayerTerrain().cardToZone(this, listPlayer.get(0).getPlayerMain().getSelectedCard(), iv_terrainMonstreJoueur_1);
                    currentplayerMain.getListCards().remove(listPlayer.get(0).getPlayerMain().getSelectedCard());
                    currentplayerMain.majMain(currentplayer, this);
                    currentplayer.addCountInvocationNormale();*/
                }
            } else {
                Toast.makeText(this, "Vous avez déjà invoquer normalement ce tour", Toast.LENGTH_SHORT).show();
            }
        }


    }
    public void onClickPoser (View view){
        Main currentplayerMain =currentplayer.getPlayerMain();
        Log.w("invocation", "count : "+currentplayer.getCountInvocationNormale() + " count max : "+ currentplayer.getMaxInvocationNormale());
        if (currentPhase.containsActionByName("PoseNormale") && currentplayer.equals(listPlayer.get(0))) {
            if (currentplayerMain.getSelectedCard() != null) {

            if (currentplayerMain.getSelectedCard().getCardType().toString().equals("MONSTRE")) {


                    if (currentplayer.getCountInvocationNormale() < currentplayer.getMaxInvocationNormale()) {
                        Action invocation = currentPhase.findActionByName("PoseNormale");

                        if (currentplayerMain.getSelectedCard() != null) {
                            ArrayList<CardInGame> listfiltre = new ArrayList<>();
                            listfiltre.add(currentplayerMain.getSelectedCard());
                            invocation.getEffect().execute(this, listPlayer, 0, 1, currentplayer.getPlayerMain(), currentplayer.getPlayerTerrain(), listfiltre);
                            majbtInvocation(currentplayerMain.getSelectedCard());
                        /*
                        /*listPlayer.get(0).getPlayerTerrain().cardToZone(this, listPlayer.get(0).getPlayerMain().getSelectedCard(), iv_terrainMonstreJoueur_1);
                        currentplayerMain.getListCards().remove(listPlayer.get(0).getPlayerMain().getSelectedCard());
                        currentplayerMain.majMain(currentplayer, this);
                        currentplayer.addCountInvocationNormale();*/
                        }
                    } else {
                        Toast.makeText(this, "Vous avez déjà invoquer normalement ce tour", Toast.LENGTH_SHORT).show();
                    }
            }else if (currentplayerMain.getSelectedCard().getCardType().toString().equals("MAGIE") || currentplayerMain.getSelectedCard().getCardType().toString().equals("PIEGE")) {
                Action pose = currentPhase.findActionByName("PoseNormale");
                Log.w("invocation", "cardType : "+currentplayerMain.getSelectedCard().getCardType().toString());

                ArrayList<CardInGame> listfiltre = new ArrayList<>();
                listfiltre.add(currentplayerMain.getSelectedCard());
                pose.getEffect().execute(this, listPlayer, 0, 1, currentplayer.getPlayerMain(), currentplayer.getPlayerTerrain(), listfiltre);
                majbtInvocation(currentplayerMain.getSelectedCard());
            }
            }
        }


    }
    public void blinkdeck() {
        Animation animation = new AlphaAnimation(1, 0.40f); //to change visibility from visible to invisible
        animation.setDuration(500); //1 second duration for each animation cycle
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
        iv_deckPlayer.startAnimation(animation);

    }

    public void majbtInvocation(CardInGame aCard){
        if (listPlayer.get(0).getPlayerMain().getFrom().equals("Main")){
            if(aCard.getCardType().toString().equals("MONSTRE")){
                if(listPlayer.get(0).getCountInvocationNormale()<listPlayer.get(0).getMaxInvocationNormale())
                {
                    bt_activer.setVisibility(View.GONE);
                    bt_invocation.setVisibility(View.VISIBLE);
                    bt_poser.setVisibility(View.VISIBLE);
                    bt_attaquer.setVisibility(View.GONE);
                    bt_changerPosition.setVisibility(View.GONE);
                }else{
                    bt_activer.setVisibility(View.GONE);
                    bt_invocation.setVisibility(View.GONE);
                    bt_poser.setVisibility(View.GONE);
                    bt_attaquer.setVisibility(View.GONE);
                    bt_changerPosition.setVisibility(View.GONE);
                }

            }else if(aCard.getCardType().toString().equals("MAGIE")){
                bt_activer.setVisibility(View.GONE);
                bt_invocation.setVisibility(View.GONE);
                bt_poser.setVisibility(View.VISIBLE);
                bt_attaquer.setVisibility(View.GONE);
                bt_changerPosition.setVisibility(View.GONE);
            }
            else if(aCard.getCardType().toString().equals("PIEGE")){
                bt_activer.setVisibility(View.GONE);
                bt_invocation.setVisibility(View.GONE);
                bt_poser.setVisibility(View.VISIBLE);
                bt_attaquer.setVisibility(View.GONE);
                bt_changerPosition.setVisibility(View.GONE);
            }

        }else if(listPlayer.get(0).getPlayerMain().getFrom().equals("Terrain")){
            if(aCard.getCardType().toString().equals("MONSTRE")) {
                bt_activer.setVisibility(View.GONE);
                if(((Monstre)aCard).isHaveChangePosition()){
                    bt_changerPosition.setVisibility(View.GONE);
                }else{
                    bt_changerPosition.setVisibility(View.VISIBLE);
                }
                bt_invocation.setVisibility(View.GONE);
                bt_poser.setVisibility(View.GONE);
                if(currentPhase.getName().equals("BattlePhase")){
                    if(((Monstre) aCard).getCountAtk()<((Monstre) aCard).getMaxcountAtk())
                    {
                        bt_attaquer.setVisibility(View.VISIBLE);
                    }else{
                        bt_attaquer.setVisibility(View.GONE);
                    }

                }else{
                    bt_attaquer.setVisibility(View.GONE);
                }
            }else if(aCard.getCardType().toString().equals("PIEGE")){
                    if (((Piege)aCard).isMalActivation()){
                        bt_activer.setVisibility(View.GONE);
                        bt_invocation.setVisibility(View.GONE);
                        bt_poser.setVisibility(View.GONE);
                        bt_attaquer.setVisibility(View.GONE);
                        bt_changerPosition.setVisibility(View.GONE);
                    }else {
                        bt_activer.setVisibility(View.VISIBLE);
                        bt_invocation.setVisibility(View.GONE);
                        bt_poser.setVisibility(View.GONE);
                        bt_attaquer.setVisibility(View.GONE);
                        bt_changerPosition.setVisibility(View.GONE);
                    }
            }else  if(aCard.getCardType().toString().equals("MAGIE")){
                bt_activer.setVisibility(View.VISIBLE);
                bt_invocation.setVisibility(View.GONE);
                bt_poser.setVisibility(View.GONE);
                bt_attaquer.setVisibility(View.GONE);
                bt_changerPosition.setVisibility(View.GONE);

            }
            //mettre ne changement de position
        }
    }
    public void onClickChangerPosition (View view){
        Main currentplayerMain =currentplayer.getPlayerMain();
        if (currentplayerMain.getSelectedCard() != null) {
            CardInGame aCard =currentplayerMain.getSelectedCard();
            if(aCard.getCardType().toString().equals("MONSTRE")) {
                //currentplayer.getPlayerTerrain().monsterToAtkAnnimation(this,view.getParent().);
            }
        }
    }

    public void onClickAttaque (View view){
        Main currentplayerMain =currentplayer.getPlayerMain();
        if (currentplayerMain.getSelectedCard() != null) {
            CardInGame aCard =currentplayerMain.getSelectedCard();
                if(aCard.getCardType().toString().equals("MONSTRE")) {
                    BattleSystem battleSystem = new BattleSystem();
                    if(listPlayer.get(1).getPlayerTerrain().getCountMonstre() > 0){
                        //select le monstre à attaquer
                        Toast.makeText(this, "Attaque !", Toast.LENGTH_SHORT).show();
                        majPv();
                    }else{
                        //attaque direct
                        battleSystem.Battle(listPlayer,(Monstre) currentplayerMain.getSelectedCard(),null,this);
                        majPv();
                        Toast.makeText(this, "Attaque direct !", Toast.LENGTH_SHORT).show();
                    }
                    ((Monstre)aCard).setCountAtk(((Monstre)aCard).getCountAtk()+1);
                    majbtInvocation(aCard);
                }
            }
    }
}


