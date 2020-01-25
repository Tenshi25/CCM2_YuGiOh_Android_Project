package master.ccm.entity.PileDeCarte;

import java.util.ArrayList;

import master.ccm.entity.Card;

public class PileCarte {
    protected ArrayList<Card> listCards = new ArrayList<>();

    public ArrayList<Card> getListCards() {
        return listCards;
    }

    public void setListCards(ArrayList<Card> listCards) {
        this.listCards = listCards;
    }
}
