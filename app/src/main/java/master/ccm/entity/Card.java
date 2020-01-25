package master.ccm.entity;

import androidx.annotation.Nullable;
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
