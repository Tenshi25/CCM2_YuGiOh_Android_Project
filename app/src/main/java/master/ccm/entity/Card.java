package master.ccm.entity;

import androidx.annotation.Nullable;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Magie;
import master.ccm.entity.subcard.Monstre;
import master.ccm.entity.subcard.Piege;
import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class Card {
    private String id;
    private String reference;
    private String name;

    private CardType cardType; //monstre, magie, piège
    private CardTypeSub cardSubType; // normal,effet, fusion syncrhro, continue etc...
    private CategorizedType categorized ; // magicien/dragon/guerrier
    private AttributeType attribut; // Divin/lumière,terre
    private String Url;
    private int level;
    private String description;
    private int atk;
    private int def;
    private int limit;
    private int duplicate;
    //recto ou verso
    private String visible;

    public CardInGame transformToCardInGame(Player aPlayer){
        CardInGame aCardInGame = new CardInGame();
        switch (this.getCardType().toString()){

            case "MONSTRE":
                Monstre CardInGameMonstre =new Monstre();
                CardInGameMonstre.setId(this.getId());
                CardInGameMonstre.setName(this.getName());
                CardInGameMonstre.setReference(this.getReference());
                CardInGameMonstre.setCardType(this.getCardType());
                CardInGameMonstre.setCardSubType(this.getCardSubType());
                CardInGameMonstre.setCategorized(this.getCategorized());
                CardInGameMonstre.setAttribut(this.getAttribut());
                CardInGameMonstre.setUrl(this.getUrl());
                CardInGameMonstre.setLevel(this.getLevel());
                CardInGameMonstre.setLevelOrigin(this.getLevel());
                CardInGameMonstre.setDescription(this.getDescription());
                CardInGameMonstre.setAtk(this.getAtk());
                CardInGameMonstre.setAtkOrigin(this.getAtk());
                CardInGameMonstre.setDef(this.getDef());
                CardInGameMonstre.setDefOrigin(this.getDef());
                CardInGameMonstre.setCountAtk(0);
                CardInGameMonstre.setMaxcountAtk(1);
                CardInGameMonstre.setHaveChangePosition(true);
                CardInGameMonstre.setPlayer(aPlayer);
                aCardInGame = CardInGameMonstre;

                break;
            case "MAGIE":
                Magie CardInGameMagie =new Magie();
                CardInGameMagie.setId(this.getId());
                CardInGameMagie.setName(this.getName());
                CardInGameMagie.setReference(this.getReference());
                CardInGameMagie.setCardType(this.getCardType());
                CardInGameMagie.setCardSubType(this.getCardSubType());
                CardInGameMagie.setUrl(this.getUrl());
                CardInGameMagie.setDescription(this.getDescription());
                CardInGameMagie.setPlayer(aPlayer);
                aCardInGame = CardInGameMagie;
                break;
            case "PIEGE":
                Piege CardInGamePiege =new Piege();
                CardInGamePiege.setId(this.getId());
                CardInGamePiege.setName(this.getName());
                CardInGamePiege.setReference(this.getReference());
                CardInGamePiege.setCardType(this.getCardType());
                CardInGamePiege.setCardSubType(this.getCardSubType());
                CardInGamePiege.setUrl(this.getUrl());
                CardInGamePiege.setDescription(this.getDescription());
                CardInGamePiege.setPlayer(aPlayer);
                aCardInGame = CardInGamePiege;
                break;
            default :
                break;
        }

        return aCardInGame;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardTypeSub getCardSubType() {
        return cardSubType;
    }

    public void setCardSubType(CardTypeSub cardSubType) {
        this.cardSubType = cardSubType;
    }

    public CategorizedType getCategorized() {
        return categorized;
    }

    public void setCategorized(CategorizedType categorized) {
        this.categorized = categorized;
    }

    public AttributeType getAttribut() {
        return attribut;
    }

    public void setAttribut(AttributeType attribut) {
        this.attribut = attribut;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(int duplicate) {
        this.duplicate = duplicate;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        if(((Card) obj).getId().equals(this.getId())){
            return  true;
        }
        return false;
    }

}
