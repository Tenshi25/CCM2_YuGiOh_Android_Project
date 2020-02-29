package master.ccm.entity;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.entity.PileDeCarte.PileCarte;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;
import master.ccm.types.CardType;

public class Selection {
    private boolean isSeclectionPhase = false;
    private ArrayList<ImageView> listImageViewSelected = new ArrayList<>();
    private ArrayList<CardInGame> listCardInGameSelected= new ArrayList<>();
    private ArrayList<Player> listPlayers ;
    BattleSystem battleSystem = new BattleSystem();
    private String cardType = "";
    private String pileCarte = "";
    private String target = "";
    private int count = 0;
    private ImageView firstSelected;

    public void addTolistImageView(Context context, ImageView imageView, String from, ArrayList<Player> listPlayer, Player player){
        Toast.makeText(context,pileCarte,Toast.LENGTH_SHORT).show();
        CardInGame aCard;
        if(from.equals(pileCarte)) {
            for (Player aPlayer:listPlayer) {
                if(aPlayer.getPlayerTerrain().getCardFromImageView(imageView) != null ){
                    aCard =aPlayer.getPlayerTerrain().getCardFromImageView(imageView);
                    if(aCard != null){
                        if(aCard.getCardType().toString().equals(this.cardType)){
                            listImageViewSelected.add(imageView);
                            //listCardInGameSelected.add(aCard);
                            this.count--;
                            Toast.makeText(context,"count --",Toast.LENGTH_SHORT).show();
                            if (this.count < 1) {
                                finSelection(context,player);
                            }
                        }else{
                            Toast.makeText(context,"Cette carte ne correspond pas à la selection",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }



        }
    }
    public void removeoflistImageView(ImageView imageView){

        listImageViewSelected.remove(imageView);
        this.count++;
    }

    public void InitSelection(int cpt, String p_cardType, String p_pileCarte,String p_target,ImageView selectedImageView,  ArrayList<Player> p_listJoueurs){
        this.count = cpt ;
        this.cardType = p_cardType;
        this.pileCarte = p_pileCarte;
        this.target = p_target;
        this.firstSelected =selectedImageView;
        this.setSeclectionPhase(true);
        this.listPlayers = p_listJoueurs;

    }
    public void finSelection(Context context,Player player){

        if(target.equals("Battle")){

            if (this.listImageViewSelected.size() == 1){

                Monstre monstreA = (Monstre) player.getPlayerTerrain().getCardFromImageView(firstSelected) ;
                Monstre monstreB =(Monstre) listPlayers.get(1).getPlayerTerrain().getCardFromImageView(this.listImageViewSelected.get(0));
                Toast.makeText(context,""+monstreB.getName(),Toast.LENGTH_SHORT).show();
                battleSystem.Battle(listPlayers, monstreA,monstreB,context);
            }
            this.setSeclectionPhase(false);
            listImageViewSelected.clear();
            ((Game_activity) context).majPv();
            //Game_activity game_activity = new Game_activity();
            //game_activity.majPv();
        }

    }

    public ImageView getFirstSelected() {
        return firstSelected;
    }

    public void setFirstSelected(ImageView firstSelected) {
        this.firstSelected = firstSelected;
    }

    public boolean isSeclectionPhase() {
        return isSeclectionPhase;
    }

    public void setSeclectionPhase(boolean seclectionPhase) {
        isSeclectionPhase = seclectionPhase;
    }
}
