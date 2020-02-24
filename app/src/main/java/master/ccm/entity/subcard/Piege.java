package master.ccm.entity.subcard;

import master.ccm.entity.Card;

public class Piege extends CardInGame {
    private  boolean malActivation = true;

    public boolean isMalActivation() {
        return malActivation;
    }

    public void setMalActivation(boolean malActivation) {
        this.malActivation = malActivation;
    }
}
