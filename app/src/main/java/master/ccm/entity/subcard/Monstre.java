package master.ccm.entity.subcard;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.types.AttributeType;
import master.ccm.types.CategorizedType;

public class Monstre extends CardInGame {
    private int atk;
    private int atkOrigin;
    private int def;
    private int defOrigin;
    private int level;
    private int levelOrigin;
    private CategorizedType categorized ; // magicien/dragon/guerrier
    private AttributeType attribut; // Divin/lumière,terre
    //attaque defense

    private String position;
    private boolean haveChangePosition;
    private Integer countAtk;
    private Integer MaxcountAtk;

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

    public int getAtkOrigin() {
        return atkOrigin;
    }

    public void setAtkOrigin(int atkOrigin) {
        this.atkOrigin = atkOrigin;
    }



    public int getLevelOrigin() {
        return levelOrigin;
    }

    public void setLevelOrigin(int levelOrigin) {
        this.levelOrigin = levelOrigin;
    }

    public int getDefOrigin() {
        return defOrigin;
    }

    public void setDefOrigin(int defOrigin) {
        this.defOrigin = defOrigin;
    }

    public Integer getCountAtk() {
        return countAtk;
    }

    public void setCountAtk(Integer countAtk) {
        this.countAtk = countAtk;
    }

    public Integer getMaxcountAtk() {
        return MaxcountAtk;
    }

    public void setMaxcountAtk(Integer maxcountAtk) {
        MaxcountAtk = maxcountAtk;
    }

    public boolean isHaveChangePosition() {
        return haveChangePosition;
    }

    public void setHaveChangePosition(boolean haveChangePosition) {
        this.haveChangePosition = haveChangePosition;
    }
}
