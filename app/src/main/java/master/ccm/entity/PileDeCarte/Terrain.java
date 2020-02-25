package master.ccm.entity.PileDeCarte;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

import master.ccm.ccm2yugiohproject.R;
import master.ccm.entity.Card;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;
import master.ccm.entity.subcard.Piege;

public class Terrain extends PileCarte {
    //private Map<Card,> zone;
    //private ArrayList<ImageView> listImage;

    private ImageView[] tableauZoneMonstreImageView = new ImageView[5];
    private ImageView[] tableauZoneMagiePiegeImageView = new ImageView[5];

    private CardInGame[] tableauZoneMonstre = new CardInGame[5];
    private CardInGame[] tableauZoneMagiePiege = new CardInGame[5];

    //private ArrayList <Map> listMapTerrainMonstre;
    //private ArrayList <Map> listMapTerrainMagiePiege;

    public void  cardToZone(Context context, CardInGame aCard, ImageView zoneImage) {

        /*if(aCard.getCardType().equals("MONSTRE") )
        {*/
            for (int i = 0; i < 5; i++) {
                if(tableauZoneMonstreImageView[i].getId() == zoneImage.getId()){
                    tableauZoneMonstre[i] =aCard;
                    Picasso.with(context).load(aCard.getUrl()).error(R.drawable.cardunknow).into(tableauZoneMonstreImageView[i]);

                }
            }
        /*}else{
            for (int i = 0; i < 5; i++) {
                if(tableauZoneMagiePiegeImageView[i].getId() == zoneImage.getId()){
                    tableauZoneMagiePiege[i] =aCard;
                    Picasso.with(context).load(aCard.getUrl()).error(R.drawable.cardunknow).into(tableauZoneMagiePiegeImageView[i]);
                }
            }
        }*/
    }


    public void  cardPoserToZone(Context context,CardInGame aCard, ImageView zoneImage) {
        if(aCard.getCardType().toString().equals("MONSTRE")){
            for (int i = 0; i < 5; i++) {
                if(tableauZoneMonstreImageView[i].getId() == zoneImage.getId()){
                    tableauZoneMonstre[i] =aCard;
                    Picasso.with(context).load(R.drawable.cardcover).error(R.drawable.cardunknow).into(tableauZoneMonstreImageView[i]);

                }
            }
        }else{
            for (int i = 0; i < 5; i++) {
                if(tableauZoneMagiePiegeImageView[i].getId() == zoneImage.getId()){
                    tableauZoneMagiePiege[i] =aCard;
                    Picasso.with(context).load(R.drawable.cardcover).error(R.drawable.cardunknow).into(tableauZoneMagiePiegeImageView[i]);

                }
            }
        }

    }

    public void  outofZone(Context context, ImageView zoneImage) {
            for (int i = 0; i < 5; i++) {
                if (tableauZoneMonstreImageView[i].getId() == zoneImage.getId()) {
                    tableauZoneMonstre[i] = null;
                    Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.cardunknow).into(tableauZoneMonstreImageView[i]);

                }
            }
            for (int i = 0; i < 5; i++) {
                if(tableauZoneMagiePiegeImageView[i].getId() == zoneImage.getId()){
                    tableauZoneMagiePiege[i] = null;
                    Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.cardunknow).into(tableauZoneMagiePiegeImageView[i]);
                }
            }
    }
    /*public Map getZonevide(String typeCard) {
        switch (typeCard){
            case "Monstre" :
                for (Map aMap : listMapTerrainMonstre) {

                }
                break ;
            case "MagiePiege" :
                break ;
            default:
                break ;
        }
    }*/

    public CardInGame[] getTableauZoneMonstre() {
        return tableauZoneMonstre;
    }
    public CardInGame getCardZoneMonstre(int index) {
        return tableauZoneMonstre[index];
    }

    public void setTableauZoneMonstre(CardInGame[] tableauZoneMonstre) {
        this.tableauZoneMonstre = tableauZoneMonstre;
    }

    public CardInGame[] getTableauZoneMagiePiege() {
        return tableauZoneMagiePiege;
    }
    public CardInGame getCardZoneMagiePiege(int index) {
        return tableauZoneMagiePiege[index];
    }
    public void setTableauZoneMagiePiege(CardInGame[] tableauZoneMagiePiege) {
        this.tableauZoneMagiePiege = tableauZoneMagiePiege;
    }

    @Override
    public void setListCards(ArrayList<CardInGame> listCards) {
        super.setListCards(listCards);
    }

    public ImageView[] getTableauZoneMonstreImageView() {
        return tableauZoneMonstreImageView;
    }

    public void setTableauZoneMonstreImageView(ImageView[] tableauZoneMonstreImageView) {
        this.tableauZoneMonstreImageView = tableauZoneMonstreImageView;
    }

    public ImageView[] getTableauZoneMagiePiegeImageView() {
        return tableauZoneMagiePiegeImageView;
    }

    public void setTableauZoneMagiePiegeImageView(ImageView[] tableauZoneMagiePiegeImageView) {
        this.tableauZoneMagiePiegeImageView = tableauZoneMagiePiegeImageView;
    }
    public ImageView getZoneVide(String type){
        if (type.equals("MONSTRE")){
            for (int i = 0; i < 5; i++) {
                //Log.w("invocation", "count : "+tableauZoneMonstre[i] + " count max : "+ tableauZoneMonstre[i].getName());
                if(tableauZoneMonstre[i] ==null){
                    return tableauZoneMonstreImageView[i];
                }
            }
            return null;
        }
        else{
            for (int i = 0; i < 5; i++) {
                if(tableauZoneMagiePiege[i] == null){
                    return tableauZoneMagiePiegeImageView[i];
                }
            }
            return null;
        }
    }

    public void monsterToDefAnnimation(Context context, ImageView imageView){
        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstreImageView[i].getId() == imageView.getId()){
                tableauZoneMonstreImageView[i].setRotation(90);
            }
        }


    }
    public void monsterToAtkAnnimation(Context context, ImageView imageView){

        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstreImageView[i].getId() == imageView.getId()){
                tableauZoneMonstreImageView[i].setRotation(-90);
            }
        }

    }
    public void removeListCard(ArrayList<CardInGame> listCardaRemove, Context context){
        for (CardInGame aCard :listCardaRemove){
            if(aCard.getCardType().toString().equals("MONSTRE")){
                for (int i = 0; i < 5; i++) {
                    if(aCard.getId().equals(tableauZoneMonstre[i].getId()) ){
                        tableauZoneMonstre[i] = null;
                        viderimageView(tableauZoneMonstreImageView[i],context);
                    }
                }
            }else {
                for (int i = 0; i < 5; i++) {
                    if(aCard.getId().equals(tableauZoneMagiePiege[i].getId()) ){
                        tableauZoneMagiePiege[i] = null;
                        viderimageView(tableauZoneMagiePiegeImageView[i],context);
                    }
                }
            }
        }


    }
    public void removeaCard(CardInGame aCard, Context context){
            if(aCard.getCardType().toString().equals("MONSTRE")){
                for (int i = 0; i < 5; i++) {
                    if(aCard.getId().equals(tableauZoneMonstre[i].getId()) ){
                        tableauZoneMonstre[i] = null;
                        viderimageView(tableauZoneMonstreImageView[i],context);
                    }
                }
            }else {
                for (int i = 0; i < 5; i++) {
                    if(aCard.getId().equals(tableauZoneMagiePiege[i].getId()) ){
                        tableauZoneMagiePiege[i] = null;
                        viderimageView(tableauZoneMagiePiegeImageView[i],context);
                    }
                }
            }


    }
    public void viderimageView(ImageView imageView, Context context){
        Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.cardunknow).into(imageView);

    }
    public int getCountMonstre(){
        int sommeMonstre =0 ;
        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstre[i] != null ){
                sommeMonstre ++;
            }
        }
        return sommeMonstre;
    }
    public void desetCountAtk (){
        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstre[i] != null ){
                ((Monstre) tableauZoneMonstre[i]).setCountAtk(0);
            }
        }

    }
    public void desetMalActivation (){
        for (int i = 0; i < 5; i++) {
            if(tableauZoneMagiePiege[i] != null ){
                ((Piege)tableauZoneMagiePiege[i]).setMalActivation(false);
            }
        }
    }


}
