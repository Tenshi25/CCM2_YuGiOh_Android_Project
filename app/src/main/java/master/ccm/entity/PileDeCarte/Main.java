package master.ccm.entity.PileDeCarte;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import master.ccm.ccm2yugiohproject.R;
import master.ccm.entity.Card;
import master.ccm.entity.Player;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class Main extends PileCarte {
    private CardInGame selectedCard;
    private ImageView imageViewSelected ;
    private ImageView imageViewZoom;
    private TextView descCardZoom;
    private ArrayList<ImageView> listIv_main;
    private String from = "Main"; // main, terrain

    public Main( ) {
        this.selectedCard = new CardInGame();
        this.listIv_main = new ArrayList<ImageView>();
    }

    public ImageView getImageViewZoom() {
        return imageViewZoom;
    }

    public void setImageViewZoom(ImageView imageViewZoom) {
        this.imageViewZoom = imageViewZoom;
    }

    public TextView getDescCardZoom() {
        return descCardZoom;
    }

    public void setDescCardZoom(TextView descCardZoom) {
        this.descCardZoom = descCardZoom;
    }

    public CardInGame getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(CardInGame selectedCard) {
        this.selectedCard = selectedCard;
    }
    public void changeSelectedCard(CardInGame p_selectedCard, ImageView iv_card, Context context, String p_from) {
        if (p_from.equals("Main")){
            Path path = new Path();
            ObjectAnimator animator;

            if (imageViewSelected == null) {
                path = new Path();
                // annimation faire monter la carte
                imageViewSelected = iv_card;
                setSelectedCard(p_selectedCard);
                //this.setFrom(from);

                this.setFrom("Main");
                path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() - 40f);
                animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
                animator.setDuration(100);
                animator.start();


            } else {
                if (imageViewSelected.getId() != iv_card.getId()) {
                    if(getFrom().equals("Main")) {
                        //annimation pour descendre la carte déjà séléctionne
                        path = new Path();
                        path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() + 40f);
                        animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
                        animator.setDuration(100);
                        animator.start();
                    }

                    // annimation faire monter la carte

                    setSelectedCard(p_selectedCard);
                    path = new Path();
                    path.moveTo(iv_card.getX(), iv_card.getY() - 40f);
                    animator = ObjectAnimator.ofFloat(iv_card, View.X, View.Y, path);
                    animator.setDuration(100);
                    animator.start();
                    this.setFrom("Main");
                    imageViewSelected = iv_card;

                }
            }
            Picasso.with(context).load(selectedCard.getUrl()).error(R.drawable.cardunknow).into(this.imageViewZoom);
            descCardZoom.setText(selectedCard.getDescription());
        }else if (p_from.equals("Terrain")) {
            if (imageViewSelected != null) {
                if(getFrom().equals("Main")){
                    //annimation pour descendre la carte déjà séléctionne
                    Path path = new Path();
                    path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() + 40f);
                    Animator animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
                    animator.setDuration(100);
                    animator.start();
                }
            }
            imageViewSelected = iv_card;
            setSelectedCard(p_selectedCard);
            this.setFrom("Terrain");
            Picasso.with(context).load(selectedCard.getUrl()).error(R.drawable.cardunknow).into(this.imageViewZoom);
            descCardZoom.setText(selectedCard.getDescription());
        }
    }

            public void majMain(Player player, Context context){
                ArrayList<CardInGame> mainJoueur = player.getPlayerMain().getListCards();

                for (int i=0; i < 9/*mainJoueur.size()*/; i++ )
                {
                    Log.i("i : ", "taille : " + i);
                    Log.i("mainJoueur.size", "taille : " + mainJoueur.size());
                    Log.i("listIv_main.size", "taille : " + listIv_main.size());
                    //Log.i("listIv_main.getUrl", "getUrl : " + mainJoueur.get(i).getUrl());
                    if(i< mainJoueur.size()){

                        listIv_main.get(i).setVisibility(View.VISIBLE);
                        Picasso.with(context).load(mainJoueur.get(i).getUrl()).error(R.drawable.cardunknow).into(listIv_main.get(i));
                    }else{
                        listIv_main.get(i).setVisibility(View.INVISIBLE);
                    }


                }

            }
            public void majMainNotVisible(Player player, Context context){

                ArrayList<CardInGame> mainIA =player.getPlayerMain().getListCards();
                for (int i=0; i < 10/*mainIA.size()*/; i++ )
                {
                    if(i< mainIA.size()){
                        listIv_main.get(i).setVisibility(View.VISIBLE);
                        listIv_main.get(i).setImageResource(R.drawable.cardcover);
                    }else{
                        listIv_main.get(i).setVisibility(View.INVISIBLE);
                    }
                }
            }

            public List<ImageView> getListIv_main() {
                return listIv_main;
            }

            public void setListIv_main(ArrayList<ImageView> listIv_main) {
                this.listIv_main = listIv_main;
            }
            public void deselectedCard(Context context){

                //annimation pour descendre la carte déjà séléctionne
                Path path = new Path();
                path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() + 40f);
                Animator animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
                animator.setDuration(100);
                animator.start();

                this.setFrom("Terrain");

            }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }/*
    public void changeSelectedCardTerrain(Card p_selectedCard,ImageView iv_card, Context context) {

        imageViewSelected = iv_card;
        setSelectedCard(p_selectedCard);
        this.setFrom("Terrain");
        Picasso.with(context).load(selectedCard.getUrl()).error(R.drawable.cardunknow).into(this.imageViewZoom);
        descCardZoom.setText(selectedCard.getDescription());
    }*/
    public ArrayList<CardInGame> getMonsterInvocable(){
        ArrayList<CardInGame> listInvocable = new ArrayList<CardInGame>();
        for (CardInGame aCard : listCards)
        {
            if(aCard.getCardType().toString().equals("MONSTRE"))
            {
                if(((Monstre)aCard).getLevel()<5) {
                    listInvocable.add(aCard);
                }

            }

        }
        return listInvocable;
    }
}
