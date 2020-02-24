package master.ccm.entity.subcard;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.types.AttributeType;
import master.ccm.types.CardType;
import master.ccm.types.CardTypeSub;
import master.ccm.types.CategorizedType;

public class CardInGame {
    private String id;
    private String reference;
    private String name;
    private CardType cardType; //monstre, magie, piège
    private CardTypeSub cardSubType; // normal,effet, fusion syncrhro, continue etc...
    private String url;
    private ArrayList<EffectCard >effects = new ArrayList<>();


    private String description;

    private int limit;
    private int duplicate;
    //recto ou verso
    private String visible;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if(((CardInGame) obj).getId().equals(this.getId())){
            return  true;
        }
        return false;
    }
    public ArrayList<EffectCard> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<EffectCard> effects) {
        this.effects = effects;
    }
}
