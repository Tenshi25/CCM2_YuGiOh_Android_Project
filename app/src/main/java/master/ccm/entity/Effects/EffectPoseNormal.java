package master.ccm.entity.Effects;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.Main;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.PileDeCarte.Terrain;
import master.ccm.entity.Player;

public class EffectPoseNormal extends EffectCard {

    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<Card> filtre) {
        if (filtre.size() == 1){
            Card aCard =filtre.get(0);
            if(aCard.getLevel()<5){
                Player invocateur =listJoueurs.get(joueurCibler);

                //mise à jour de la main du joueur


                Terrain terrainInvocateur =invocateur.getPlayerTerrain();
                ImageView zone_Iv_vide = terrainInvocateur.getZoneVide("MONSTRE");
                if(zone_Iv_vide != null){
                    Main mainInvocateur =invocateur.getPlayerMain() ;
                    mainInvocateur.deselectedCard(context);
                    terrainInvocateur.cardPoserToZone(context,aCard, zone_Iv_vide);

                    mainInvocateur.getListCards().remove(aCard);
                    mainInvocateur.majMain(invocateur,context);
                    terrainInvocateur.monsterToDefAnnimation(context, zone_Iv_vide);

                    invocateur.addCountInvocationNormale();
                }else{
                    Toast.makeText(context,"Vous n'avez plus de zone libre", Toast.LENGTH_SHORT).show();
                }

            }else{

            }

        }

    }
}
