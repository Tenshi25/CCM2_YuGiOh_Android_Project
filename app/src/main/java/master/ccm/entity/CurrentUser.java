package master.ccm.entity;

import java.util.ArrayList;

import master.ccm.entity.PileDeCarte.Deck;

public class CurrentUser {

    private String id = "0";
    private String name = "NULL";
    private String pseudo;
    private Deck mainDeck;
    private ArrayList<Deck> deckList=new ArrayList<>();


    private static CurrentUser sui = null;

    public static synchronized CurrentUser getInstance(){
        if(null == sui){
            sui = new CurrentUser();
        }
        return sui;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
        //Log.i("setId", id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public ArrayList<Deck> getDeckList() {
        return deckList;
    }

    public void setDeckList(ArrayList<Deck> deckList) {
        this.deckList = deckList;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<String> getNamesDeckList() {
        ArrayList<String> listDecksName = new ArrayList<>();
        for (Deck aDeck : deckList){
            listDecksName.add(aDeck.getName());
        }
        return listDecksName;
    }
    public Deck getDeckByID(String id){
        Deck theDeck = new Deck();
        for (Deck adeck :deckList){
            if(adeck.getId().equals(id)){
                theDeck =adeck;
            }
        }
        return theDeck;
    }
}
