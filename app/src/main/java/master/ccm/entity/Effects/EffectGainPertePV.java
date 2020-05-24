package master.ccm.entity.Effects;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import master.ccm.ccm2yugiohproject.EndGame_activity;
import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.ccm2yugiohproject.R;
import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;

public class EffectGainPertePV extends EffectCard {
    @Override
    public void execute(Context context,ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        Log.i("JSON EffectGainPV ", "joueurCibler : "+joueurCibler);
        Log.i("JSON EffectGainPV ", "nb : "+nb);
        if(joueurCibler<2){
            if(nb<0){
                Log.i("JSON EffectGainPV ", "pv joueurCibler Av: "+ listJoueurs.get(joueurCibler).getLifepoint());
                listJoueurs.get(joueurCibler).perteLifepoint(-nb);
                Log.i("JSON EffectGainPV ", "pv joueurCibler Ap: "+ listJoueurs.get(joueurCibler).getLifepoint());
                if(listJoueurs.get(joueurCibler).getLifepoint() == 0){
                    Intent intent = new Intent(context, EndGame_activity.class);
                    if(joueurCibler == 0){
                        intent.putExtra("labelVictory", R.string.labelDefeat );
                        context.startActivity(intent);
                    }else{
                        intent.putExtra("labelVictory", R.string.labelVictory );
                        context.startActivity(intent);
                    }


                }
            }else{
                listJoueurs.get(joueurCibler).gainLifepoint(nb);
            }

        }else{
            if(nb<0){
                for (Player joueur : listJoueurs) {
                    joueur.perteLifepoint(-nb);
                    Intent intent = new Intent(context, EndGame_activity.class);
                    if(listJoueurs.get(0).getLifepoint() == 0){
                        intent.putExtra("labelVictory", R.string.labelDefeat );
                        context.startActivity(intent);
                    }
                    if(listJoueurs.get(1).getLifepoint() == 0){
                        intent.putExtra("labelVictory", R.string.labelVictory );
                        context.startActivity(intent);
                    }

                }
            }else{
                for (Player joueur : listJoueurs){
                    joueur.gainLifepoint(nb);
                }

            }
        }

    }
}
