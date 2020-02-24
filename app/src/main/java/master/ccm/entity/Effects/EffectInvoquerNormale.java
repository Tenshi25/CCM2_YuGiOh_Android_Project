package master.ccm.entity.Effects;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.Main;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.PileDeCarte.Terrain;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class EffectInvoquerNormale extends EffectCard {

    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        if (filtre.size() == 1){
            CardInGame aCard =filtre.get(0);
            if(aCard.getCardType().toString().equals("MONSTRE"))
            {
                if( ((Monstre) aCard).getLevel()<5){
                    Player invocateur =listJoueurs.get(joueurCibler);

                    //mise à jour de la main du joueur


                    Terrain terrainInvocateur =invocateur.getPlayerTerrain();
                    ImageView zone_Iv_vide = terrainInvocateur.getZoneVide("MONSTRE");
                    if(zone_Iv_vide != null){
                        Main mainInvocateur =invocateur.getPlayerMain() ;
                        mainInvocateur.deselectedCard(context);

                        terrainInvocateur.cardToZone(context,aCard, zone_Iv_vide);
                        mainInvocateur.getListCards().remove(aCard);
                        mainInvocateur.majMain(invocateur,context);
                        invocateur.addCountInvocationNormale();

                    }else{
                        Toast.makeText(context,"Vous n'avez plus de zone libre", Toast.LENGTH_SHORT).show();
                    }

                }else{

                }
            }


        }

    }
}
