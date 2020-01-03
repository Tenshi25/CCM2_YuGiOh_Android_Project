package master.ccm.entity;

import java.util.ArrayList;

public class PileCarte {
    protected ArrayList<Card> listCards = new ArrayList<>();

    public ArrayList<Card> getListCards() {
        return listCards;
    }

    public void setListCards(ArrayList<Card> listCards) {
        this.listCards = listCards;
    }
}
