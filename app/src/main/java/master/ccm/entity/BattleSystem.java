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
import master.ccm.ccm2yugiohproject.utils.BuilderEffectUtils;
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
                    if (!monstreB.isVisible()) {
                        flipEffectActivateInBattle(monstreB);
                    }
                    effectMoveCardFromAtoB.execute(context, null, 0, 0, monstreB.getPlayer().getPlayerTerrain(), monstreB.getPlayer().getPlayerCimetiere(), filtre);
                    //le monstre b est detruit
                } else if (monstreA.getAtk() < monstreB.getDef()) {
                    sommeDegat = monstreA.getAtk() - monstreB.getDef();
                    Toast.makeText(context, monstreA.getPlayer().getName() + " perds " + -sommeDegat + " point de vie !", Toast.LENGTH_SHORT).show();
                    if (!monstreB.isVisible()) {
                        flipEffectActivateInBattle(monstreB);
                    }
                    monstreB.setVisible(true);
                    monstreB.getPlayer().getPlayerTerrain().monsterToDefAnnimation(context, monstreB.getPlayer().getPlayerTerrain().getImageViewFromCard(monstreB));
                    effectGainPertePV.execute(context, listPlayer, getIntPlayer(monstreA.getPlayer()), sommeDegat, null, null, null);

                    // aucun des monstres est detruit
                } else if (monstreA.getAtk() == monstreB.getDef()) {
                    // aucun des monstres est detruit
                    if (!monstreB.isVisible()) {
                        flipEffectActivateInBattle(monstreB);
                    }
                    monstreB.setVisible(true);
                    monstreB.getPlayer().getPlayerTerrain().monsterToDefAnnimation(context, monstreB.getPlayer().getPlayerTerrain().getImageViewFromCard(monstreB));
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

    public void flipEffectActivateInBattle(Monstre aCard){
        // flip effect
        if (aCard.getEffects().size() > 0){
            BuilderEffectUtils builderEffectUtils = new BuilderEffectUtils();
            for (int i=0; i< aCard.getEffects().size(); i++){
                if (aCard.getEffectsExplaination().get(i).isFlip()){
                    if (aCard.getEffectsExplaination().get(i).getDeterminedEffect() == null) {
                        aCard.getEffects().get(i).execute(Game_activity.myContext, Game_activity.myContext.getAllPLayers(),
                                builderEffectUtils.knowJoueurCible(aCard.getEffectsExplaination().get(i), aCard.getPlayer()),
                                builderEffectUtils.knowQuota(aCard.getEffectsExplaination().get(i)),
                                builderEffectUtils.knowPileA(aCard.getEffectsExplaination().get(i)),
                                builderEffectUtils.knowPileB(aCard.getEffectsExplaination().get(i)),
                                builderEffectUtils.knowFilterCard(aCard.getEffectsExplaination().get(i), Game_activity.myContext.getAllPLayers())
                        );
                    } else {
                        int multiplier = 0;
                        int quota = builderEffectUtils.knowQuota(aCard.getEffectsExplaination().get(i));
                        multiplier = Game_activity.myContext.getMultiplerValue(aCard.getEffectsExplaination().get(i).getDeterminedEffect());
                        quota = quota * multiplier;
                        aCard.getEffects().get(i).execute(Game_activity.myContext, Game_activity.myContext.getAllPLayers(),
                                builderEffectUtils.knowJoueurCible(aCard.getEffectsExplaination().get(i), aCard.getPlayer()),
                                quota,
                                builderEffectUtils.knowPileA(aCard.getEffectsExplaination().get(i)),
                                builderEffectUtils.knowPileB(aCard.getEffectsExplaination().get(i)),
                                builderEffectUtils.knowFilterCard(aCard.getEffectsExplaination().get(i), Game_activity.myContext.getAllPLayers())
                        );
                    }
                    Game_activity.myContext.majPv();
                    Game_activity.myContext.majMain();
                    Game_activity.myContext.majNBPlayerDeckCard();
                }
            }
        }

    }

}
