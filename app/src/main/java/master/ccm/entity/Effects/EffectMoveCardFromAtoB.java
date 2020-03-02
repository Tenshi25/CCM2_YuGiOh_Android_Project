package master.ccm.entity.Effects;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import master.ccm.entity.PileDeCarte.Cimetiere;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.PileDeCarte.Terrain;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;
import master.ccm.types.CardType;

import static master.ccm.types.CardType.MONSTRE;

public class EffectMoveCardFromAtoB extends EffectCard   {

    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        if(filtre.size() > 0){
            for (CardInGame aCard :filtre){
                Log.d("Effect",""+dePileA.getClass().toString());
                ArrayList<CardInGame> listCardsASupprimer = new ArrayList<>();
                listCardsASupprimer.add(aCard);
                if(dePileA.getClass().equals(Terrain.class)){
                    ((Terrain)dePileA).removeListCard(listCardsASupprimer,context);
                }else{
                    dePileA.getListCards().remove(aCard);
                }
                Log.d("Effect",""+aPileB.getClass().toString());
                if(aPileB.getClass().equals(Cimetiere.class)) {
                    ((Cimetiere)aPileB).addlistCard(aCard,context);
                }else {
                    aPileB.getListCards().add(aCard);
                }
            }
        }
    }
}
