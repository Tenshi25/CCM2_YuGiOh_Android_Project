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
                tableauZoneMonstreImageView[i].setRotation(-90);
            }CardInGame aCard =getCardFromImageView(tableauZoneMonstreImageView[i]);
            if(aCard != null){
                Log.d("visible",""+aCard.isVisible());
                if(aCard.isVisible()){
                    Picasso.with(context).load(aCard.getUrl()).error(R.drawable.cardunknow).into(tableauZoneMonstreImageView[i]);
                }
            }
        }


    }
    public void monsterToAtkAnnimation(Context context, ImageView imageView){

        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstreImageView[i].getId() == imageView.getId()){
                tableauZoneMonstreImageView[i].setRotation(0);
                CardInGame aCard =getCardFromImageView(tableauZoneMonstreImageView[i]);
                if(aCard != null){
                    Log.d("visible",""+aCard.isVisible());
                    if(aCard.isVisible()){
                        Picasso.with(context).load(aCard.getUrl()).error(R.drawable.cardunknow).into(tableauZoneMonstreImageView[i]);
                    }
                }

            }
        }

    }
    public void removeListCard(ArrayList<CardInGame> listCardaRemove, Context context){
        Log.i("JSON Terrain ", "Debut Remove ");
        for (CardInGame aCard :listCardaRemove){
            if(aCard.getCardType().toString().equals("MONSTRE")){
                for (int i = 0; i < 5; i++) {
                    Log.i("JSON Terrain ", "a Card "+ aCard.getName());
                    Log.i("JSON Terrain ", "number aCard "+ aCard.getIdNumberInGame());
                    if(tableauZoneMonstre[i] != null){
                        Log.i("JSON Terrain ", "number tableauZoneMonstre[i]"+ tableauZoneMonstre[i].getIdNumberInGame());

                        if(aCard.getIdNumberInGame().equals(tableauZoneMonstre[i].getIdNumberInGame())){
                            Log.i("JSON Terrain ", "tableauZoneMonstre i "+ tableauZoneMonstre[i].getIdNumberInGame());
                            Log.i("JSON Terrain ", "tableauZoneMonstre i "+ tableauZoneMonstre[i].getName());
                            Log.i("JSON Terrain ", "aCard  "+ aCard.getIdNumberInGame());
                            Log.i("JSON Terrain ", "aCard "+ aCard.getName());
                            tableauZoneMonstre[i] = null;

                            //monsterToAtkAnnimation(context,tableauZoneMonstreImageView[i]);
                            Log.i("JSON Terrain ", "Fin Animation ");
                            ToAtkAndViderimageView(tableauZoneMonstreImageView[i],context);

                            //Picasso.with(context).load(aCard.getUrl()).error(R.drawable.cardunknow).into(tableauZoneMonstreImageView[i]);
                            //Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.caseterrain).into(tableauZoneMonstreImageView[i]);
                            Log.i("JSON Terrain ", "Fin ");
                            break;
                        }
                    }

                }
            }else {
                for (int i = 0; i < 5; i++) {
                    if(aCard == tableauZoneMagiePiege[i] ){
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
                    if(tableauZoneMonstre[i] != null) {
                        if (aCard.equals(tableauZoneMonstre[i])) {
                            tableauZoneMonstre[i] = null;
                            viderimageView(tableauZoneMonstreImageView[i], context);
                            if(((Monstre)aCard).getPosition().equals("DEF")){
                                monsterToAtkAnnimation(context,tableauZoneMonstreImageView[i]);
                            }
                        }
                    }
                }
            }else {
                for (int i = 0; i < 5; i++) {
                    if(tableauZoneMonstre[i] != null) {
                        if (aCard.equals(tableauZoneMagiePiege[i])) {
                            tableauZoneMagiePiege[i] = null;
                            viderimageView(tableauZoneMagiePiegeImageView[i], context);
                        }
                    }
                }
            }


    }
    public void viderimageView(ImageView imageView, Context context){
        Log.i("JSON Terrain ", "vider Picasso : " +imageView.getId());
        Log.i("JSON Terrain ", "vider Picasso context: " +context.getClass().getName());
        Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.cardunknow).into(imageView);

    }
    public void ToAtkAndViderimageView(ImageView imageView, Context context){
        Log.i("JSON Terrain ", "vider Picasso : " +imageView.getId());
        Log.i("JSON Terrain ", "vider Picasso context: " +context.getClass().getName());
        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstreImageView[i].getId() == imageView.getId()){
                //tableauZoneMonstreImageView[i].setRotation(0);
                tableauZoneMonstreImageView[i].setImageDrawable(context.getResources().getDrawable(R.drawable.caseterrain));
                //Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.caseterrain).into(tableauZoneMonstreImageView[i]);
                tableauZoneMonstreImageView[i].setRotation(0);
            }
        }

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
                if (tableauZoneMagiePiege[i].getCardType().toString().equals("PIEGE")) {
                    ((Piege) tableauZoneMagiePiege[i]).setMalActivation(false);
                }
            }
        }
    }
    public void desetChangePosition (){
        for (int i = 0; i < 5; i++) {
            if(tableauZoneMonstre[i] != null ){
                ((Monstre) tableauZoneMonstre[i]).setHaveChangePosition(false);
            }
        }

    }
    public CardInGame getCardFromImageView(ImageView imageView){
            for (int i = 0; i < 5; i++) {
                if(imageView.getId() ==tableauZoneMonstreImageView[i].getId()){
                    return tableauZoneMonstre[i];
                }
            }
            for (int i = 0; i < 5; i++) {
                if(imageView.getId() == tableauZoneMagiePiegeImageView[i].getId()){
                    return tableauZoneMagiePiege[i];
                }
            }
            return null;
    }
    public ImageView getImageViewFromCard(CardInGame aCard){
        for (int i = 0; i < 5; i++) {
            if(aCard.equals(tableauZoneMonstre[i])){

                return tableauZoneMonstreImageView[i];
            }
        }
        for (int i = 0; i < 5; i++) {
            if(aCard.equals(tableauZoneMonstre[i]) ){
                return tableauZoneMonstreImageView[i];
            }
        }
        return null;
    }
    public ArrayList<Monstre> getMonsterOnTerrain(){
        ArrayList<Monstre>listMonstreTerrain = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if((Monstre) getCardZoneMonstre(i) !=  null) {
                listMonstreTerrain.add((Monstre) getCardZoneMonstre(i));
            }
        }
        return listMonstreTerrain;
    }



}
