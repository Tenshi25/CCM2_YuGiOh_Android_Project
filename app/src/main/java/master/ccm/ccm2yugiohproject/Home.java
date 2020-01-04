package master.ccm.ccm2yugiohproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.utils.NavigationMenuUtils;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.manager.DeckDBManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DeckDBManager deckDBManager = new DeckDBManager();
        deckDBManager.selectUserDecks(this);

        bottomNavigation = findViewById(R.id.id_home_bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                return true;
                            case R.id.navigation_deck:
                                NavigationMenuUtils.onClickToDeckList(Home.this);
                                return true;
                            case R.id.navigation_party:
                                NavigationMenuUtils.onClickDuel(Home.this);
                                return true;
                            case R.id.navigation_setting:
                                NavigationMenuUtils.onClickProfile(Home.this);
                                return true;
                        }
                        return false;
                    }
                });

    }

    public void selectAllUserDeckFini(ArrayList<Deck> listDeck) {
        CurrentUser.getInstance().setDeckList(listDeck);
    }
}
