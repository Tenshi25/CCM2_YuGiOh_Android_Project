package master.ccm.entity.Effects;

import android.content.Context;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;

public class EffectDefausser extends EffectCard {


    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<Card> filtre) {
        switch (joueurCibler) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default :
                break;
        }
    }
}
