package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.manager.DeckDBManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DeckDBManager deckDBManager = new DeckDBManager();
        deckDBManager.selectUserDecks(this);

    }
    public void onClickQuit(View view){
        finish();
    }
    public void onClickDuel(View view){
        Intent intent = new Intent(this, ConfStartDuel_activity.class);
        startActivity(intent);
        finish();
    }
    public void onClickToDeckList(View view){
        Intent intent = new Intent(this, MenuDeckList_Activity.class);
        startActivity(intent);
        finish();
    }

    public void onClickProfile_menu(View view){
        Intent intent = new Intent(this, Profile_activity.class);
        startActivity(intent);
        finish();
    }

    public void selectAllUserDeckFini(ArrayList<Deck> listDeck) {
        CurrentUser.getInstance().setDeckList(listDeck);
    }
}
