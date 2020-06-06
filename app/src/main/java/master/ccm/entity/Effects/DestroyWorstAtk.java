package master.ccm.entity.Effects;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class DestroyWorstAtk extends EffectCard {
    @Override
    public void execute(Context context,ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        Log.i("JSON DestroyWorstAtk ", "joueurCibler : "+joueurCibler);
        Log.i("JSON DestroyWorstAtk ", "nb : "+nb);
        if(joueurCibler<2){
                Player playerTargeted = listJoueurs.get(joueurCibler);
                CardInGame cardToDestroy = null;
                CardInGame[] arrayOfCards = playerTargeted.getPlayerTerrain().getTableauZoneMonstre();
                for (int index = 0; index < arrayOfCards.length -1; index++){
                    if (arrayOfCards[index] != null && arrayOfCards[index].isVisible()){
                        Monstre monstreToDestroy = (Monstre) arrayOfCards[0];
                        if (cardToDestroy == null) {
                            cardToDestroy = monstreToDestroy;
                        } else {
                            if (((Monstre) cardToDestroy).getAtk() > monstreToDestroy.getAtk()){
                                cardToDestroy = monstreToDestroy;
                            }
                        }
                    }
                }

            if (cardToDestroy != null){
                EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
                ArrayList<CardInGame> list = new ArrayList<>();
                list.add(cardToDestroy);
                effectMoveCardFromAtoB.execute(context, listJoueurs, joueurCibler, nb, playerTargeted.getPlayerTerrain(), playerTargeted.getPlayerCimetiere(), list);
            }


        }else{

        }

    }
}
