package master.ccm.entity.Effects;

import android.content.Context;

import java.util.ArrayList;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.entity.Card;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;

public abstract class EffectCard {

    public abstract void execute(Context context, ArrayList<Player> listJoueurs, int joueurCibler, int nb , PileCarte dePileA , PileCarte aPileB, ArrayList<CardInGame> filtre);
}
