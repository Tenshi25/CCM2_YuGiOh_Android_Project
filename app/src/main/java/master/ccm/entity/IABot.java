package master.ccm.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

import master.ccm.entity.Effects.EffectMoveCardFromAtoB;
import master.ccm.entity.subcard.CardInGame;

public class IABot {
    private Player Iabot;
    private EffectMoveCardFromAtoB effectMoveCardFromAtoB = new EffectMoveCardFromAtoB();
    private Context context;
    private ArrayList<Player> listPlayer;
    public void init (Context p_context, ArrayList<Player> p_listPlayer, Player p_player){
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
}
