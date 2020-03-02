package master.ccm.entity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import master.ccm.entity.PileDeCarte.Bannisement;
import master.ccm.entity.PileDeCarte.Cimetiere;
import master.ccm.entity.PileDeCarte.Deck;
import master.ccm.entity.PileDeCarte.ExtraDeck;
import master.ccm.entity.PileDeCarte.Main;
import master.ccm.entity.PileDeCarte.Terrain;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;

public class Player {
    private String name;
    private Deck playerDeck;
    private Main playerMain;
    private int numJoueur =0;
    private Terrain playerTerrain = new Terrain();
    private Cimetiere playerCimetiere = new Cimetiere();
    private ExtraDeck playerExtra =  new ExtraDeck();
    private Bannisement playerBannisement = new Bannisement();
    private int countInvocationNormale =0;
    private int MaxInvocationNormale =1;

    private Integer lifepoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(Deck playerDeck) {
        //this.getPlayerDeck().setListCard(initDeckToPlay(playerDeck.getListCard()));
            this.playerDeck = playerDeck;
    }

    public Integer getLifepoint() {
        return lifepoint;
    }

    public void setLifepoint(Integer lifepoint) {
        this.lifepoint = lifepoint;
    }

    public void gainLifepoint(int p_gain) {
        this.lifepoint = this.lifepoint + p_gain;
    }

    public void perteLifepoint(int p_perte) {
        this.lifepoint =  this.lifepoint - p_perte;
        if (this.lifepoint < 0){
            this.lifepoint =0;

        }
    }

    public Terrain getPlayerTerrain() {
        return playerTerrain;
    }

    public void setPlayerTerrain(Terrain playerTerrain) {
        this.playerTerrain = playerTerrain;
    }

    public void initDeckToPlay(List<Card> listCard,Player aplayer){
        ArrayList<Card> deckToPlay = new ArrayList<>();
        for (Card aCard : listCard) {
            for(int i = 0; i < aCard.getDuplicate(); i++){
                deckToPlay.add(aCard);
            }
        }
        ArrayList<CardInGame> deckInGameToPlay = DeckCardtoDeckCardInGame(deckToPlay,aplayer);
        this.playerDeck.setListCard(deckInGameToPlay);
    }

    public Main getPlayerMain() {
        return playerMain;
    }

    public void setPlayerMain(Main playerMain) {
        this.playerMain = playerMain;
    }

    public Cimetiere getPlayerCimetiere() {
        return playerCimetiere;
    }

    public void setPlayerCimetiere(Cimetiere playerCimetiere) {
        this.playerCimetiere = playerCimetiere;
    }

    public ExtraDeck getPlayerExtra() {
        return playerExtra;
    }

    public void setPlayerExtra(ExtraDeck playerExtra) {
        this.playerExtra = playerExtra;
    }

    public Bannisement getPlayerBannisement() {
        return playerBannisement;
    }

    public void setPlayerBannisement(Bannisement playerBannisement) {
        this.playerBannisement = playerBannisement;
    }

    public Player() {
        playerMain = new Main();
    }
    public void setListIvMain(ArrayList<ImageView> listIv){

        this.playerMain.setListIv_main(listIv);
    }

    public int getCountInvocationNormale() {
        return countInvocationNormale;
    }

    public void setCountInvocationNormale(int countInvocationNormale) {
        this.countInvocationNormale = countInvocationNormale;
    }
    public void addCountInvocationNormale() {
            this.countInvocationNormale =this.countInvocationNormale+1;

    }

    public int getMaxInvocationNormale() {
        return MaxInvocationNormale;
    }

    public void setMaxInvocationNormale(int maxInvocationNormale) {
        MaxInvocationNormale = maxInvocationNormale;
    }

    public ArrayList<CardInGame> DeckCardtoDeckCardInGame(ArrayList<Card> p_Deckcard, Player aplayer)
    {
        return (ArrayList<CardInGame>)p_Deckcard.stream()
                .map(element -> element.transformToCardInGame(aplayer))
                .collect(Collectors.toList());
    }

    public int getNumJoueur() {
        return numJoueur;
    }

    public void setNumJoueur(int numJoueur) {
        this.numJoueur = numJoueur;
    }
}
