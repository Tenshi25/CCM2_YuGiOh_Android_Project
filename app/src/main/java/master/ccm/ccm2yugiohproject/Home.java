package master.ccm.ccm2yugiohproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.utils.NavigationMenuUtils;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.PileDeCarte.Deck;
import master.ccm.entity.Score;
import master.ccm.manager.DeckDBManager;
import master.ccm.manager.ScoreDBManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private TextView numberOfWin;
    private TextView numberOfLoss;
    private ScoreDBManager scoreDBManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        numberOfWin = findViewById(R.id.home_win_number);
        numberOfLoss = findViewById(R.id.home_loss_number);

        scoreDBManager = new ScoreDBManager();
        scoreDBManager.selectScore(this, "never");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
        bottomNavigation.setSelectedItemId(R.id.navigation_home);
    }

    public void selectAllUserDeckFini(ArrayList<Deck> listDeck) {
        CurrentUser.getInstance().setDeckList(listDeck);
    }

    public void modifyScore(Score newScore){
        numberOfWin.setText(String.valueOf(newScore.getWin()));
        numberOfLoss.setText(String.valueOf(newScore.getLoss()));
    }
}
