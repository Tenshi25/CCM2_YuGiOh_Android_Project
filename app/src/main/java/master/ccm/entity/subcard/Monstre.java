package master.ccm.entity.subcard;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.types.AttributeType;
import master.ccm.types.CategorizedType;

public class Monstre extends CardInGame {
    private int atk;
    private int def;
    private int level;
    private CategorizedType categorized ; // magicien/dragon/guerrier
    private AttributeType attribut; // Divin/lumière,terre
    //attaque defense

    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

}
