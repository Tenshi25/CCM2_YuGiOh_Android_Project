package master.ccm.entity.subcard.effect;

import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class FilterCard {

    private String reference;

    private String name;

    private CardType cardType;

    private CardTypeSub cardSubType;

    private CategorizedType categorized;

    private AttributeType attribute;

    private String description;

    private int atk;

    private int def;

    private int level;

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

    public AttributeType getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeType attribute) {
        this.attribute = attribute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
