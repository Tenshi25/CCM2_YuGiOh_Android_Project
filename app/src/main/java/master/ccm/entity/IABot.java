package master.ccm.entity;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import master.ccm.entity.Effects.EffectMoveCardFromAtoB;
import master.ccm.entity.PileDeCarte.Main;
import master.ccm.entity.subcard.CardInGame;

public class IABot {
    private Player Iabot;
    private EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
    private Context context;
    private ArrayList<Player> listPlayer;
    public void init (Context p_context, ArrayList<Player> p_listPlayer, Player p_player ){
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
}
