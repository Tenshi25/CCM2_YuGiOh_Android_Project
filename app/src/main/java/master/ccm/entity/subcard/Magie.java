package master.ccm.entity.subcard;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.Effects.EffectCard;

public class Magie extends CardInGame {
    private  boolean malActivation = false;
    private  boolean jouabletourAdversaire = false;

    public boolean isMalActivation() {
        return malActivation;
    }

    public void setMalActivation(boolean malActivation) {
        this.malActivation = malActivation;
    }

    public boolean isJouabletourAdversaire() {
        return jouabletourAdversaire;
    }

    public void setJouabletourAdversaire(boolean jouabletourAdversaire) {
        this.jouabletourAdversaire = jouabletourAdversaire;
    }
}
