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
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class EffectPoseNormal extends EffectCard {

    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        if (filtre.size() == 1) {
            CardInGame aCard = filtre.get(0);
            if (aCard.getCardType().toString().equals("MONSTRE")) {
                ((Monstre) aCard).setPosition("DEF");
                aCard.setVisible(false);
                if (((Monstre) aCard).getLevel() < 5) {
                    Player invocateur = listJoueurs.get(joueurCibler);

                    //mise à jour de la main du joueur

                    Terrain terrainInvocateur = invocateur.getPlayerTerrain();
                    ImageView zone_Iv_vide = terrainInvocateur.getZoneVide("MONSTRE");
                    if (zone_Iv_vide != null) {
                        Main mainInvocateur = invocateur.getPlayerMain();
                        if(joueurCibler == 0) {
                            mainInvocateur.deselectedCard(context);
                        }
                        terrainInvocateur.cardPoserToZone(context, aCard, zone_Iv_vide);

                        mainInvocateur.getListCards().remove(aCard);
                        if(joueurCibler == 0) {
                            mainInvocateur.majMain(invocateur, context);
                        }else{
                            mainInvocateur.majMainNotVisible(invocateur,context);
                        }
                        terrainInvocateur.monsterToDefAnnimation(context, zone_Iv_vide);

                        invocateur.addCountInvocationNormale();
                    }

                } else {
                    //monstre + 4 etoile à sacrifice
                }

            }else if(aCard.getCardType().toString().equals("MAGIE") || aCard.getCardType().toString().equals("PIEGE")){
                Player invocateur = listJoueurs.get(joueurCibler);
                Terrain terrainInvocateur = invocateur.getPlayerTerrain();
                ImageView zone_Iv_vide = terrainInvocateur.getZoneVide("MAGIEPIEGE");
                if (zone_Iv_vide != null) {
                    Main mainInvocateur = invocateur.getPlayerMain();
                    if(joueurCibler == 0) {
                        mainInvocateur.deselectedCard(context);
                    }
                    terrainInvocateur.cardPoserToZone(context, aCard, zone_Iv_vide);

                    mainInvocateur.getListCards().remove(aCard);
                    if(joueurCibler == 0) {
                        mainInvocateur.majMain(invocateur, context);
                    }else{
                        mainInvocateur.majMainNotVisible(invocateur, context);
                    }
                }else {
                    Toast.makeText(context, "Vous n'avez plus de zone libre", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
