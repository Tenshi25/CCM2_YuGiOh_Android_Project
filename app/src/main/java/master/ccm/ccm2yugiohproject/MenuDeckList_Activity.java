package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.Deck;

import android.os.Bundle;

import java.util.ArrayList;

public class MenuDeckList_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_deck_list);
    }

    public void selectAllUserDeckFini(ArrayList<Deck> listDeck) {

    }
}
