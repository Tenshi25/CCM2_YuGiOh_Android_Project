package master.ccm.entity.PileDeCarte;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.subcard.CardInGame;

public class Deck extends PileCarte {
    private String id;
    private String name;
    private String description;
    private String idUser;

    private ArrayList<CardInGame> listCard = new ArrayList<CardInGame>();

    public List<CardInGame> getListCard() {
        return listCard;
    }

    public void setListCard(ArrayList<CardInGame> listCard) {
        this.listCard = listCard;
    }

    public void AddCard(CardInGame aCard){
        listCard.add(aCard);
    }
    public void RemoveCard(CardInGame aCard){
        listCard.remove(aCard);
    }
    public void SelectCard(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    @Override
    public String toString() {
        return name;
    }

    // Mélanger deck
    public void shuffleDeck(){
        Log.i("Remi_DECK", "Avant Shuffle : " + getListCard().get(0).getName() + " " + getListCard().get(1).getName() + " " + getListCard().get(2).getName());
        Collections.shuffle(this.getListCard());
        Log.i("Remi_DECK", "Apres Shuffle : " + getListCard().get(0).getName() + " " + getListCard().get(1).getName() + " " + getListCard().get(2).getName());
    }
    // Piocher X carte
    public List<CardInGame> drawCard(int numberCard){
        List<CardInGame> listCardDraw = new ArrayList<CardInGame>();
        for(int i = 0; i < numberCard; i++){
            if(!noMoreDeckCard()) {
                listCardDraw.add(this.getListCard().get(0));
                this.getListCard().remove(0);
            }
        }
        return listCardDraw;
    }

    // Nombre de carte dans le deck
    public int countDeckCard(){
        return this.listCard.size();
    }
    // Test si plus de carte
    public boolean noMoreDeckCard(){
        return countDeckCard() == 0;
    }

}