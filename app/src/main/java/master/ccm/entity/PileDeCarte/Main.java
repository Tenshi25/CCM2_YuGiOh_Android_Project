package master.ccm.entity.PileDeCarte;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.view.View;
import android.widget.ImageView;

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
    private ArrayList<ImageView> listIv_main;

    public Main() {
        this.selectedCard = new Card();
        this.listIv_main = new ArrayList<ImageView>();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
    public void changeSelectedCard(Card p_selectedCard,ImageView iv_card) {
        Path path = new Path();
        ObjectAnimator animator;

        if (imageViewSelected == null) {
            path = new Path();
            // annimation faire monter la carte
            imageViewSelected = iv_card;
            setSelectedCard(p_selectedCard);

            path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() - 50f);
            animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
            animator.setDuration(100);
            animator.start();


        } else {
            if (imageViewSelected.getId() != iv_card.getId()) {
                //annimation pour descendre la carte déjà séléctionne
                path = new Path();
                path.moveTo(imageViewSelected.getX(), imageViewSelected.getY() + 50f);
                animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
                animator.setDuration(100);
                animator.start();


                // annimation faire monter la carte

                setSelectedCard(p_selectedCard);
                path = new Path();
                path.moveTo(iv_card.getX(), iv_card.getY() - 50f);
                animator = ObjectAnimator.ofFloat(iv_card, View.X, View.Y, path);
                animator.setDuration(100);
                animator.start();
                imageViewSelected = iv_card;
            }
        }
    }


            /*

            switch (mainJoueur.size()){
                case 1:
                    //new LoadImageTask(mainJoueur.get(0).getUrl(), iv_mainPlayerCard_5).execute();

                    Picasso.with(this)
                            .load(mainJoueur.get(0).getUrl())
                            .error(R.drawable.cardunknow)
                            .into(iv_mainPlayerCard_1);
                    break;
                case 2:
                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);

                /*new LoadImageTask(mainJoueur.get(0).getUrl(), iv_mainPlayerCard_5).execute();
                new LoadImageTask(mainJoueur.get(1).getUrl(), iv_mainPlayerCard_6).execute();*/
                   /*break;
                case 3:
                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    break;
                case 4:

                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    break;
                case 5:
                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    Picasso.with(this).load(mainJoueur.get(4).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_5);

                    break;
                case 6:

                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    Picasso.with(this).load(mainJoueur.get(4).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_5);
                    Picasso.with(this).load(mainJoueur.get(5).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_6);


                    break;
                case 7:

                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    Picasso.with(this).load(mainJoueur.get(4).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_5);
                    Picasso.with(this).load(mainJoueur.get(5).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_6);
                    Picasso.with(this).load(mainJoueur.get(6).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_7);

                    break;
                case 8:

                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    Picasso.with(this).load(mainJoueur.get(4).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_5);
                    Picasso.with(this).load(mainJoueur.get(5).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_6);
                    Picasso.with(this).load(mainJoueur.get(6).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_7);
                    Picasso.with(this).load(mainJoueur.get(7).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_8);


                    break;
                case 9:

                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    Picasso.with(this).load(mainJoueur.get(4).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_5);
                    Picasso.with(this).load(mainJoueur.get(5).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_6);
                    Picasso.with(this).load(mainJoueur.get(6).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_7);
                    Picasso.with(this).load(mainJoueur.get(7).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_8);
                    Picasso.with(this).load(mainJoueur.get(8).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_9);

                    break;
                case 10:
                    Picasso.with(this).load(mainJoueur.get(0).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_1);
                    Picasso.with(this).load(mainJoueur.get(1).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_2);
                    Picasso.with(this).load(mainJoueur.get(2).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_3);
                    Picasso.with(this).load(mainJoueur.get(3).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_4);
                    Picasso.with(this).load(mainJoueur.get(4).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_5);
                    Picasso.with(this).load(mainJoueur.get(5).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_6);
                    Picasso.with(this).load(mainJoueur.get(6).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_7);
                    Picasso.with(this).load(mainJoueur.get(7).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_8);
                    Picasso.with(this).load(mainJoueur.get(8).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_9);
                    Picasso.with(this).load(mainJoueur.get(9).getUrl()).error(R.drawable.cardunknow).into(iv_mainPlayerCard_10);

                    break;
            }
            ArrayList<Card> mainIA =listPlayer.get(1).getPlayerMain().getListCards();
            switch (mainIA.size()){
                case 1:
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    break;
                case 2:
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;
                case 3:
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;
                case 4:

                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;
                case 5:
                    iv_mainIACard_3.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;
                case 6:
                    iv_mainIACard_3.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_8.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;

                case 7:
                    iv_mainIACard_2.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_3.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_8.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;

                case 8:

                    iv_mainIACard_2.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_3.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_8.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_9.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;

                case 9:

                    iv_mainIACard_1.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_2.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_3.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_8.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_9.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;
                case 10:

                    iv_mainIACard_1.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_2.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_3.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_4.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_5.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_6.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_7.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_8.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_9.setImageDrawable(getDrawable(R.drawable.cardcover));
                    iv_mainIACard_10.setImageDrawable(getDrawable(R.drawable.cardcover));

                    break;
            } */
            public void majMain(Player player, Context context){
                ArrayList<Card> mainJoueur = player.getPlayerMain().getListCards();

                for (int i=0; i < 9/*mainJoueur.size()*/; i++ )
                {
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
