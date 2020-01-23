package master.ccm.entity.Effects;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;

public class EffectGainPertePV extends EffectCard {
    @Override
    public void execute(ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<Card> filtre) {
        if(joueurCibler<2){
            if(nb<0){
                listJoueurs.get(joueurCibler).perteLifepoint(nb);
            }else{
                listJoueurs.get(joueurCibler).gainLifepoint(nb);
            }

        }else{
            if(nb<0){
                for (Player joueur : listJoueurs) {
                    joueur.perteLifepoint(nb);
                }
            }else{
                for (Player joueur : listJoueurs){
                    joueur.gainLifepoint(nb);
                }

            }
        }

    }
}
