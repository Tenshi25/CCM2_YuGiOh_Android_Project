package master.ccm.entity.PileDeCarte;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import master.ccm.ccm2yugiohproject.R;
import master.ccm.entity.subcard.CardInGame;

public class Cimetiere extends PileCarte {
    private ImageView iv_lastCard;
    private TextView tv_countCards;

    public void majIvCimetiere(Context context){
        if(this.listCards.size()>0){
            CardInGame lastcard = this.listCards.get(this.listCards.size()-1);
            Picasso.with(context).load(lastcard.getUrl()).error(R.drawable.cardunknow).into(this.iv_lastCard);

        }else{
            Picasso.with(context).load(R.drawable.caseterrain).error(R.drawable.cardunknow).into(this.iv_lastCard);
        }
    }
    public void addlistCard(CardInGame aCard,Context context){
        Log.d("tv_cime",""+aCard.getName());
        Log.d("tv_cime",""+aCard.getUrl());
        this.listCards.add(aCard);
        Log.d("tv_cime",""+tv_countCards.getId());
        this.tv_countCards.setText(String.valueOf(this.listCards.size()));
        majIvCimetiere(context);
    }
    public ImageView getIv_lastCard() {
        return iv_lastCard;
    }

    public void setIv_lastCard(ImageView iv_lastCard) {
        Log.d("tv_cime",""+iv_lastCard.getId());
        this.iv_lastCard = iv_lastCard;
    }

    public TextView getTv_countCards() {
        return tv_countCards;
    }

    public void setTv_countCards(TextView tv_countCards) {
        this.tv_countCards = tv_countCards;
    }
}
