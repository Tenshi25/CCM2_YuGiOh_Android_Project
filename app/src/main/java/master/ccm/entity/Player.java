package master.ccm.entity;

import java.util.ArrayList;
import java.util.List;

import master.ccm.entity.PileDeCarte.Bannisement;
import master.ccm.entity.PileDeCarte.Cimetiere;
import master.ccm.entity.PileDeCarte.Deck;
import master.ccm.entity.PileDeCarte.ExtraDeck;
import master.ccm.entity.PileDeCarte.Main;

public class Player {
    private String name;
    private Deck playerDeck;
    private Main playerMain = new Main();
    private Cimetiere playerCimetiere = new Cimetiere();
    private ExtraDeck playerExtra =  new ExtraDeck();
    private Bannisement playerBannisement = new Bannisement();

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
    public void initDeckToPlay(List<Card> listCard){
        ArrayList<Card> deckToPlay = new ArrayList<Card>();
        for (Card aCard : listCard) {
            for(int i = 0; i < aCard.getDuplicate(); i++){
                deckToPlay.add(aCard);
            }
        }
        this.playerDeck.setListCard(deckToPlay);
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
}
