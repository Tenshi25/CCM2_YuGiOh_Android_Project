package master.ccm.entity;

import android.content.Context;
import android.media.effect.Effect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import master.ccm.entity.Effects.EffectCard;
import master.ccm.entity.Effects.EffectGainPertePV;
import master.ccm.entity.Effects.EffectMoveCardFromAtoB;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class BattleSystem {
    private Monstre monstreA;
    private Monstre monstreB;

    private ArrayList<EffectCard>listEffets = new ArrayList<>();
    public void Battle(ArrayList<Player> listPlayer, Monstre monstreA, Monstre monstreB, Context context){
            if(monstreB == null){
                BattleAtkDirect(listPlayer,monstreA,context);
            }else{
                BattleMonster(listPlayer,monstreA,monstreB,context);
            }
    }
    public void BattleMonster(ArrayList<Player> listPlayer, Monstre monstreA, Monstre monstreB, Context context){
        EffectGainPertePV effectGainPertePV =new EffectGainPertePV();
        EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
        int sommeDegat =0;
        ArrayList<CardInGame> filtre = new ArrayList<>();
        //
        if(monstreB.getPosition().equals("DEF")){
            if(monstreA.getAtk() > monstreB.getDef()){
                filtre.add(monstreB);
                effectMoveCardFromAtoB.execute(context,null,0,0,monstreA.getPlayer().getPlayerTerrain(),monstreA.getPlayer().getPlayerCimetiere(),filtre);
                //le monstre b est detruit
            }else if (monstreA.getAtk() < monstreB.getDef()){

                sommeDegat =monstreA.getAtk() - monstreB.getDef();
                effectGainPertePV.execute(context,listPlayer,getIntPlayer(monstreA.getPlayer()),sommeDegat,null,null,null);

                // aucun des monstres est detruit
            }else if (monstreA.getAtk() == monstreB.getDef()){

                // aucun des monstres est detruit
            }
        }else if (monstreB.getPosition().equals("ATK")){
            if(monstreA.getAtk() > monstreB.getAtk()){
                filtre.add(monstreB);
                sommeDegat =monstreB.getAtk() - monstreA.getAtk();
                effectGainPertePV.execute(context,listPlayer,getIntPlayer(monstreB.getPlayer()),sommeDegat,null,null,null);

                effectMoveCardFromAtoB.execute(context,null,0,0,monstreB.getPlayer().getPlayerTerrain(),monstreB.getPlayer().getPlayerCimetiere(),filtre);
                //le monstre b est detruit
            }else if (monstreA.getAtk() < monstreB.getAtk()){

                sommeDegat =monstreA.getAtk() - monstreB.getAtk();
                effectGainPertePV.execute(context,listPlayer,getIntPlayer(monstreA.getPlayer()),sommeDegat,null,null,null);
                filtre.add(monstreA);
                //monstre A est detruit
                effectMoveCardFromAtoB.execute(context,null,0,0,monstreA.getPlayer().getPlayerTerrain(),monstreA.getPlayer().getPlayerCimetiere(),filtre);

            }else if (monstreA.getAtk() == monstreB.getAtk()){
                filtre.add(monstreB);
                effectMoveCardFromAtoB.execute(context,null,0,0,monstreB.getPlayer().getPlayerTerrain(),monstreB.getPlayer().getPlayerCimetiere(),filtre);
                filtre.clear();
                filtre.add(monstreA);
                effectMoveCardFromAtoB.execute(context,null,0,0,monstreA.getPlayer().getPlayerTerrain(),monstreA.getPlayer().getPlayerCimetiere(),filtre);
                //les deux sont detruits
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
