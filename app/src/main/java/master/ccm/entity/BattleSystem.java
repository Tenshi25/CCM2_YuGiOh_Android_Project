package master.ccm.entity;

import android.content.Context;
import android.media.effect.Effect;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.entity.Effects.EffectCard;
import master.ccm.entity.Effects.EffectGainPertePV;
import master.ccm.entity.Effects.EffectMoveCardFromAtoB;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class BattleSystem {

    private ArrayList<EffectCard>listEffets = new ArrayList<>();
    public void Battle(ArrayList<Player> listPlayer, Monstre monstreA, Monstre monstreB, Game_activity context){
            if(monstreB == null){
                BattleAtkDirect(listPlayer,monstreA,context);
            }else{
                if(monstreA != null){
                    BattleMonster(listPlayer,monstreA,monstreB,context);
                }
            }
    }
    public void BattleMonster(ArrayList<Player> listPlayer, Monstre monstreA, Monstre monstreB, Game_activity context){
        EffectGainPertePV effectGainPertePV =new EffectGainPertePV();
        EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
        int sommeDegat =0;
        ArrayList<CardInGame> filtre = new ArrayList<>();
        //

        if (monstreA != null && monstreB != null && monstreA.getPlayer() != null && monstreB.getPlayer()!= null && monstreA.getPosition() != null && monstreB.getPosition() != null) {
            monstreA.setHaveChangePosition(true);
            Log.i("JSON BattleMonster ", "" + monstreA.getName());
            Log.i("JSON BattleMonster ", "" + monstreA.getPosition());
            Log.i("JSON BattleMonster ", "" + monstreA.getPlayer().getName());
            Log.i("JSON BattleMonster ", "" + monstreB.getName());
            Log.i("JSON BattleMonster ", "" + monstreB.getPosition());
            Log.i("JSON BattleMonster ", "" + monstreB.getPlayer().getName());
            if (monstreB.getPosition().equals("DEF")) {
                if (monstreA.getAtk() > monstreB.getDef()) {
                    filtre.add(monstreB);
                    effectMoveCardFromAtoB.execute(context, null, 0, 0, monstreB.getPlayer().getPlayerTerrain(), monstreB.getPlayer().getPlayerCimetiere(), filtre);
                    //le monstre b est detruit
                } else if (monstreA.getAtk() < monstreB.getDef()) {
                    monstreB.setVisible(true);
                    monstreB.getPlayer().getPlayerTerrain().monsterToDefAnnimation(context, monstreB.getPlayer().getPlayerTerrain().getImageViewFromCard(monstreB));
                    sommeDegat = monstreA.getAtk() - monstreB.getDef();
                    Toast.makeText(context, monstreA.getPlayer().getName() + " perds " + -sommeDegat + " point de vie !", Toast.LENGTH_SHORT).show();
                    effectGainPertePV.execute(context, listPlayer, getIntPlayer(monstreA.getPlayer()), sommeDegat, null, null, null);

                    // aucun des monstres est detruit
                } else if (monstreA.getAtk() == monstreB.getDef()) {

                    // aucun des monstres est detruit
                }

            } else if (monstreB.getPosition().equals("ATK")) {
                if (monstreA.getAtk() > monstreB.getAtk()) {
                    Log.i("JSON BattleMonster ", "Combat entre Monstres");
                    Log.i("JSON BattleMonster ", "playerName" + monstreB.getPlayer().getName());
                    filtre.add(monstreB);
                    sommeDegat = monstreB.getAtk() - monstreA.getAtk();
                    Log.i("JSON BattleMonster ", "somme degat : " + sommeDegat);
                    Log.i("JSON BattleMonster ", "MB NumJoueur : " + monstreB.getPlayer().getNumJoueur());
                    Log.i("JSON BattleMonster ", "listPlayer : " + listPlayer.size());
                    //Toast.makeText(context,monstreB.getPlayer().getName()+" perds "+ sommeDegat+" point de vie !",Toast.LENGTH_SHORT).show();
                    effectGainPertePV.execute(context, listPlayer, monstreB.getPlayer().getNumJoueur(), sommeDegat, null, null, null);
                    Log.i("JSON BattleMonster", "playerName2" + monstreB.getPlayer().getName());
                    effectMoveCardFromAtoB.execute(context, null, monstreB.getPlayer().getNumJoueur(), 0, monstreB.getPlayer().getPlayerTerrain(), monstreB.getPlayer().getPlayerCimetiere(), filtre);
                    //le monstre b est detruit
                    Log.i("JSON BattleMonster", "Fin");
                } else if (monstreA.getAtk() < monstreB.getAtk()) {

                    sommeDegat = monstreA.getAtk() - monstreB.getAtk();
                    Toast.makeText(context, monstreA.getPlayer().getName() + " perds " + -sommeDegat + " point de vie !", Toast.LENGTH_SHORT).show();
                    effectGainPertePV.execute(context, listPlayer, getIntPlayer(monstreA.getPlayer()), sommeDegat, null, null, null);
                    filtre.add(monstreA);
                    //monstre A est detruit
                    effectMoveCardFromAtoB.execute(context, null, 0, 0, monstreA.getPlayer().getPlayerTerrain(), monstreA.getPlayer().getPlayerCimetiere(), filtre);

                } else if (monstreA.getAtk() == monstreB.getAtk()) {

                    filtre.add(monstreB);
                    effectMoveCardFromAtoB.execute(context, null, 0, 0, monstreB.getPlayer().getPlayerTerrain(), monstreB.getPlayer().getPlayerCimetiere(), filtre);
                    filtre.clear();
                    filtre.add(monstreA);
                    effectMoveCardFromAtoB.execute(context, null, 0, 0, monstreA.getPlayer().getPlayerTerrain(), monstreA.getPlayer().getPlayerCimetiere(), filtre);
                    //les deux sont detruits
                }
            }
            if (sommeDegat < 0) {
                context.majPv();
            }
        }
    }
    public int getIntPlayer(Player aplayer){
        if(aplayer.getName().contains("ia")){
            return 1;
        }else {
            return 0;
        }

    }
    public void BattleAtkDirect(ArrayList<Player> listPlayer, Monstre monstreA, Context context){
        EffectGainPertePV effectGainPertePV =new EffectGainPertePV();
        effectGainPertePV.execute(context,listPlayer,getDifIntPlayer(monstreA.getPlayer()),-monstreA.getAtk(),null,null,null);
    }
    public int getDifIntPlayer(Player aplayer){
        if(aplayer.getName().contains("ia")){
            return 0;
        }else {
            return 1;
        }

    }

}
