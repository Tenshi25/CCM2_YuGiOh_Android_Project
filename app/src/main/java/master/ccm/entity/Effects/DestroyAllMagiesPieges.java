package master.ccm.entity.Effects;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;

public class DestroyAllMagiesPieges extends EffectCard {
    @Override
    public void execute(Context context,ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        Log.i("JSON DestroyAllMonsters ", "destroy all monsters");
        if (joueurCibler == 2) {
            ArrayList<CardInGame> allcardForAPlayer = new ArrayList<>();
            for (Player player : listJoueurs) {

                for (int index = 0; index < player.getPlayerTerrain().getTableauZoneMagiePiege().length; index++){
                    if ( player.getPlayerTerrain().getTableauZoneMagiePiege()[index] != null) {
                        allcardForAPlayer.add(player.getPlayerTerrain().getTableauZoneMagiePiege()[index]);
                    }
                }

                if (allcardForAPlayer.size() > 0) {
                    EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
                    effectMoveCardFromAtoB.execute(context, listJoueurs, joueurCibler, nb, player.getPlayerTerrain(), player.getPlayerCimetiere(), allcardForAPlayer);
                }

                allcardForAPlayer = new ArrayList<>();
            }
        } else {
            ArrayList<CardInGame> allcardForAPlayer = new ArrayList<>();
            Player player = listJoueurs.get(joueurCibler);

            for (int index = 0; index < player.getPlayerTerrain().getTableauZoneMagiePiege().length; index++){
                if ( player.getPlayerTerrain().getTableauZoneMagiePiege()[index] != null) {
                    allcardForAPlayer.add(player.getPlayerTerrain().getTableauZoneMagiePiege()[index]);
                }
            }

            if (allcardForAPlayer.size() > 0) {
                EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
                effectMoveCardFromAtoB.execute(context, listJoueurs, joueurCibler, nb, player.getPlayerTerrain(), player.getPlayerCimetiere(), allcardForAPlayer);
            }

        }

    }
}
