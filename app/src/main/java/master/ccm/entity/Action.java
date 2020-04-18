package master.ccm.entity;

import master.ccm.entity.Effects.EffectCard;

public class Action {
    private String nom ;
    private EffectCard effect;

    public Action(String nom, EffectCard effect) {
        this.nom = nom;
        this.effect = effect;
    }

    public Action() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public EffectCard getEffect() {
        return effect;
    }

    public void setEffect(EffectCard effect) {
        this.effect = effect;
    }
}
