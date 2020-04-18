package master.ccm.entity.PileDeCarte;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.subcard.CardInGame;

public class PileCarte {
    protected ArrayList<CardInGame> listCards = new ArrayList<>();

    public ArrayList<CardInGame> getListCards() {
        return listCards;
    }

    public void setListCards(ArrayList<CardInGame> listCards) {
        this.listCards = listCards;
    }
}
