package master.ccm.entity.Effects;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;

public abstract class EffectCard {

    public abstract void execute(ArrayList<Player> listJoueurs, int joueurCibler, int nb , PileCarte dePileA , PileCarte aPileB, ArrayList<Card> filtre);
}
