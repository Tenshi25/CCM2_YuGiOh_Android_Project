package master.ccm.entity.Effects;

import android.content.Context;

import java.util.ArrayList;

import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.PileDeCarte.Terrain;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;

public class EffectMoveCardFromAtoB extends EffectCard   {

    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        if(filtre.size() > 0){
            for (CardInGame aCard :filtre){
                if(dePileA.getClass().toString().equals("Terrain")){
                    ((Terrain)dePileA).removeaCard(aCard,context);
                }else{
                    dePileA.getListCards().remove(aCard);
                }

                if(aPileB.getClass().toString().equals("Terrain")) {
                    ((Terrain)dePileA).removeaCard(aCard,context);
                }else {
                    aPileB.getListCards().add(aCard);
                }
            }
        }
    }
}
