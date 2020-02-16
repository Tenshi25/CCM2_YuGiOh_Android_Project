package master.ccm.entity.PileDeCarte;

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

public class Main extends PileCarte {
    private Card selectedCard;
    private ImageView imageViewSelected ;
    private ImageView imageViewZoom;
    private TextView descCardZoom;
    private ArrayList<ImageView> listIv_main;

    public Main( ) {
        this.selectedCard = new Card();
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

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
    public void changeSelectedCard(Card p_selectedCard,ImageView iv_card, Context context) {
        Path path = new Path();
        ObjectAnimator animator;

        if (imageViewSelected == null) {
            path = new Path();
            // annimation faire monter la carte
            imageViewSelected = iv_card;
            setSelectedCard(p_selectedCard);

            path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() - 40f);
            animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
            animator.setDuration(100);
            animator.start();


        } else {
            if (imageViewSelected.getId() != iv_card.getId()) {
                //annimation pour descendre la carte déjà séléctionne
                path = new Path();
                path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() + 40f);
                animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
                animator.setDuration(100);
                animator.start();


                // annimation faire monter la carte

                setSelectedCard(p_selectedCard);
                path = new Path();
                path.moveTo(iv_card.getX(), iv_card.getY() - 40f);
                animator = ObjectAnimator.ofFloat(iv_card, View.X, View.Y, path);
                animator.setDuration(100);
                animator.start();
                imageViewSelected = iv_card;

            }
        }
        Picasso.with(context).load(selectedCard.getUrl()).error(R.drawable.cardunknow).into(this.imageViewZoom);
        descCardZoom.setText(selectedCard.getDescription());
    }

            public void majMain(Player player, Context context){
                ArrayList<Card> mainJoueur = player.getPlayerMain().getListCards();

                for (int i=0; i < 9/*mainJoueur.size()*/; i++ )
                {
                    Log.i("i : ", "taille : " + i);
                    Log.i("mainJoueur.size", "taille : " + mainJoueur.size());
                    Log.i("listIv_main.size", "taille : " + listIv_main.size());
                    if(i< mainJoueur.size()){

                        listIv_main.get(i).setVisibility(View.VISIBLE);
                        Picasso.with(context).load(mainJoueur.get(i).getUrl()).error(R.drawable.cardunknow).into(listIv_main.get(i));
                    }else{
                        listIv_main.get(i).setVisibility(View.INVISIBLE);
                    }


                }

            }
            public void majMainNotVisible(Player player, Context context){

                ArrayList<Card> mainIA =player.getPlayerMain().getListCards();
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
}
