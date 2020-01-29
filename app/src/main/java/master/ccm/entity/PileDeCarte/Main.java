package master.ccm.entity.PileDeCarte;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import master.ccm.ccm2yugiohproject.R;
import master.ccm.entity.Card;

public class Main extends PileCarte {
    private Card selectedCard;
    private ImageView imageViewSelected ;
    public Main() {
        this.selectedCard = new Card();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
    public void changeSelectedCard(Card p_selectedCard,ImageView iv_card){
        Path path = new Path();
        ObjectAnimator animator;
        if(imageViewSelected != null){
            //annimation pour descendre la carte
            path.moveTo(imageViewSelected.getX(), imageViewSelected.getY()+50f);
            animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
            animator.setDuration(100);
            animator.start();


        }
        // annimation faire monter la carte
        imageViewSelected = iv_card;
        setSelectedCard(p_selectedCard);

        path.moveTo(imageViewSelected.getX(), imageViewSelected.getY()-50f);
        animator = ObjectAnimator.ofFloat(imageViewSelected, View.X, View.Y, path);
        animator.setDuration(100);
        animator.start();


    }
}
