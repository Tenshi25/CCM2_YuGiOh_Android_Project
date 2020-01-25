package master.ccm.entity.Effects;

import java.util.ArrayList;
import java.util.List;

import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;

public class EffectPioche extends  EffectCard {



    @Override
    public void execute(ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<Card> filtre) {
        if(joueurCibler<2){
            if(nb>0){
                List<Card> listCards = listJoueurs.get(joueurCibler).getPlayerDeck().drawCard(nb);
                for (Card aCard : listCards) {
                    listJoueurs.get(joueurCibler).getPlayerMain().getListCards().add(aCard);
                }
            }

        }else{
            if(nb>0){
                for (Player joueur : listJoueurs) {
                    List<Card> listCards =  joueur.getPlayerDeck().drawCard(nb);
                    for (Card aCard : listCards) {
                        joueur.getPlayerMain().getListCards().add(aCard);
                    }
                }
            }
        }
    }

}
