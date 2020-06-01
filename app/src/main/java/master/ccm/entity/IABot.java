package master.ccm.entity;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.ccm2yugiohproject.utils.BuilderEffectUtils;
import master.ccm.entity.Effects.EffectMoveCardFromAtoB;
import master.ccm.entity.PileDeCarte.Main;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class IABot {
    private Player Iabot;
    private EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
    private Game_activity context;
    private ArrayList<Player> listPlayer;
    public void init (Game_activity p_context, ArrayList<Player> p_listPlayer, Player p_player){
        Iabot = p_player;
        context= p_context;
        listPlayer = p_listPlayer;

    }

    public void DeffauseIA(){
        Random rand = new Random();
        int indexcarte = rand.nextInt(Iabot.getPlayerMain().getListCards().size()-1);
        CardInGame aCard = Iabot.getPlayerMain().getListCards().get(indexcarte);

        ArrayList<CardInGame> filtre = new ArrayList<>();
        filtre.add(aCard);
        effectMoveCardFromAtoB.execute(context,listPlayer,aCard.getPlayer().getNumJoueur(),1,Iabot.getPlayerMain(),Iabot.getPlayerCimetiere(),filtre);
    }

    public void invocationIA(ArrayList<CardInGame> listMonstre, Phase currentPhase) {
        Random rand = new Random();
        int action = rand.nextInt(2);
        if (listMonstre.size() > 0) {
            int nbinvocation = rand.nextInt(listMonstre.size());
            CardInGame cardSelectRand = listMonstre.get(nbinvocation);
            Main currentplayerMain = Iabot.getPlayerMain();
            Log.w("action", "action : " + action);
            switch (action) {
                case 0:
                    //invocation atk
                    if (currentPhase.containsActionByName("InvocationNormale") && Iabot.equals(listPlayer.get(1))) {
                        if (Iabot.getCountInvocationNormale() < Iabot.getMaxInvocationNormale()) {
                            Action invocation = currentPhase.findActionByName("InvocationNormale");
                            ArrayList<CardInGame> listfiltre = new ArrayList<>();
                            listfiltre.add(cardSelectRand);
                            invocation.getEffect().execute(context, listPlayer, 1, 1, Iabot.getPlayerMain(), Iabot.getPlayerTerrain(), listfiltre);
                            //majbtAction(currentplayerMain.getSelectedCard());


                            Monstre leMonstre = (Monstre) cardSelectRand;

                            if (leMonstre.getEffects().size() > 0){
                                BuilderEffectUtils builderEffectUtils = new BuilderEffectUtils();
                                for (int i=0; i< leMonstre.getEffects().size(); i++){
                                    if (leMonstre.getEffectsExplaination().get(i).isAutoActivable()){

                                        if (leMonstre.getEffectsExplaination().get(0).getDeterminedEffect() == null) {
                                            leMonstre.getEffects().get(i).execute(Game_activity.myContext, listPlayer,
                                                    builderEffectUtils.knowJoueurCible(leMonstre.getEffectsExplaination().get(i), leMonstre.getPlayer()),
                                                    builderEffectUtils.knowQuota(leMonstre.getEffectsExplaination().get(i)),
                                                    builderEffectUtils.knowPileA(leMonstre.getEffectsExplaination().get(i)),
                                                    builderEffectUtils.knowPileB(leMonstre.getEffectsExplaination().get(i)),
                                                    builderEffectUtils.knowFilterCard(leMonstre.getEffectsExplaination().get(i), listPlayer)
                                            );
                                        } else {
                                            int multiplier = 0;
                                            int quota = builderEffectUtils.knowQuota(leMonstre.getEffectsExplaination().get(0));
                                            multiplier = Game_activity.myContext.getMultiplerValue(leMonstre.getEffectsExplaination().get(0).getDeterminedEffect());
                                            quota = quota * multiplier;
                                            leMonstre.getEffects().get(0).execute(Game_activity.myContext, listPlayer,
                                                    builderEffectUtils.knowJoueurCible(leMonstre.getEffectsExplaination().get(0), leMonstre.getPlayer()),
                                                    quota,
                                                    builderEffectUtils.knowPileA(leMonstre.getEffectsExplaination().get(0)),
                                                    builderEffectUtils.knowPileB(leMonstre.getEffectsExplaination().get(0)),
                                                    builderEffectUtils.knowFilterCard(leMonstre.getEffectsExplaination().get(0), listPlayer)
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
                case 1:
                    //invocation def
                    if (currentPhase.containsActionByName("PoseNormale") && Iabot.equals(listPlayer.get(1))) {
                        if (Iabot.getCountInvocationNormale() < Iabot.getMaxInvocationNormale()) {
                            Action invocation = currentPhase.findActionByName("PoseNormale");

                            if (currentplayerMain.getSelectedCard() != null) {
                                ArrayList<CardInGame> listfiltre = new ArrayList<>();
                                listfiltre.add(cardSelectRand);
                                //Log.w("ia invoq poser", "ia invoq poser : "+action);
                                invocation.getEffect().execute(context, listPlayer, 1, 1, Iabot.getPlayerMain(), Iabot.getPlayerTerrain(), listfiltre);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
    public void AttaqueIA(String idMonstreAttaquant,String idMonstreDeffenseur){
                Log.i("JSON idMonstreAttaquant /  idMonstreDeffenseur", ""+idMonstreAttaquant+" / "+idMonstreDeffenseur);
                BattleSystem battleSystem = new BattleSystem();
                ArrayList<Monstre> listMonstreIA = listPlayer.get(1).getPlayerTerrain().getMonsterOnTerrain();
                Monstre monstreAttaquant = new Monstre();
                for( Monstre aMonstre : listMonstreIA ) {
                    //Log.i("JSON aMonstre id ", ""+aMonstre.getIdNumberInGame());
                    if (aMonstre.getIdNumberInGame().equals(idMonstreAttaquant)) {
                        Log.i("JSON MonstreATK ", ""+aMonstre.getName());
                        monstreAttaquant =aMonstre;
                    }
                }
                ArrayList<Monstre> listMonstrePlayer = listPlayer.get(0).getPlayerTerrain().getMonsterOnTerrain();
                Monstre monstreDef = new Monstre();
                for( Monstre aMonstre : listMonstrePlayer ) {
                    //Log.i("JSON aMonstre id ", ""+aMonstre.getIdNumberInGame());
                    if (aMonstre.getIdNumberInGame().equals(idMonstreDeffenseur)) {
                        Log.i("JSON monstreDef ", ""+aMonstre.getName());
                        monstreDef =aMonstre;
                    }
                }
                Log.i("JSON MonstreATK ", ""+monstreAttaquant.getName());
                if(monstreAttaquant != null && monstreAttaquant.getCountAtk() != null && monstreAttaquant.getMaxcountAtk() != null) {
                    if (monstreAttaquant.getCountAtk() < monstreAttaquant.getMaxcountAtk()) {
                        ((Monstre) monstreAttaquant).setCountAtk(((Monstre) monstreAttaquant).getCountAtk() + 1);
                        battleSystem.Battle(listPlayer, (Monstre) monstreAttaquant, monstreDef, context);

                        context.majPv();
                    }
                }
            }

    public void AttaqueDirectIA(Monstre monstreAttaquant){
        if(monstreAttaquant.getCountAtk() < monstreAttaquant.getMaxcountAtk()) {
            BattleSystem battleSystem = new BattleSystem();
            //attaque direct
            battleSystem.Battle(listPlayer, (Monstre) monstreAttaquant, null, context);
            context.majPv();
            ((Monstre) monstreAttaquant).setCountAtk(((Monstre) monstreAttaquant).getCountAtk() + 1);
        }
    }


}
