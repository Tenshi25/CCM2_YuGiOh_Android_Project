package master.ccm.entity.Effects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import master.ccm.ccm2yugiohproject.EndGame_activity;
import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.ccm2yugiohproject.R;
import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;

public class EffectPioche extends  EffectCard {



    @Override
    public void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb, PileCarte dePileA, PileCarte aPileB, ArrayList<CardInGame> filtre) {
        if(joueurCibler<2){
            if(nb>0){
                List<CardInGame> listCards = listJoueurs.get(joueurCibler).getPlayerDeck().drawCard(nb);
                if(listCards.size() == 0){
                    Intent intent = new Intent(context, EndGame_activity.class);
                    if(joueurCibler == 0){

                            intent.putExtra("labelVictory", R.string.labelDefeat );
                            context.startActivity(intent);

                    }else{
                            intent.putExtra("labelVictory", R.string.labelVictory);
                            context.startActivity(intent);

                    }



                }else{
                    for (CardInGame aCard : listCards) {
                        listJoueurs.get(joueurCibler).getPlayerMain().getListCards().add(aCard);
                    }
                }
            }

        }else{
            if(nb>0){
                for (Player joueur : listJoueurs) {
                    List<CardInGame> listCards =  joueur.getPlayerDeck().drawCard(nb);
                    if(listCards.size() == 0){
                        Intent intent = new Intent(context, EndGame_activity.class);
                        if(listJoueurs.get(0).getPlayerDeck().getListCard().size() == 0){
                            intent.putExtra("labelVictory", R.string.labelDefeat );
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                        if(listJoueurs.get(1).getPlayerDeck().getListCard().size() == 0){
                            intent.putExtra("labelVictory", R.string.labelVictory );
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    }else{
                    for (CardInGame aCard : listCards) {
                        joueur.getPlayerMain().getListCards().add(aCard);
                    }
                    }
                }
            }
        }
    }

}
