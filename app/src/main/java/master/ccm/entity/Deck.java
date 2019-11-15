package master.ccm.entity;

import java.util.List;

public class Deck {
    private String id;
    private String name;
    private String description;
    private String idUser;

    private List<Card> listCard;

    public List<Card> getListCard() {
        return listCard;
    }

    public void setListCard(List<Card> listCard) {
        this.listCard = listCard;
    }

    public void AddCard(Card aCard){
        listCard.add(aCard);
    }
    public void RemoveCard(Card aCard){
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
}