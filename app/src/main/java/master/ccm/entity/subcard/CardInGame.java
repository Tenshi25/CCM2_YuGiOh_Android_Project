package master.ccm.entity.subcard;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.effect.Effect;
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
    private ArrayList<EffectCard> effects = new ArrayList<>();
    private List<Effect> effectsExplaination;

    private Player player;


    private String description;

    private int limit;
    private int duplicate;
    //recto ou verso
    private boolean visible = false;
    private String idNumberInGame = null;

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getIdNumberInGame() {
        return idNumberInGame;
    }

    public void setIdNumberInGame(String idNumberInGame) {
        this.idNumberInGame = idNumberInGame;
    }

    public List<Effect> getEffectsExplaination() {
        return effectsExplaination;
    }

    public void setEffectsExplaination(List<Effect> effectsExplaination) {
        this.effectsExplaination = effectsExplaination;
    }
}
