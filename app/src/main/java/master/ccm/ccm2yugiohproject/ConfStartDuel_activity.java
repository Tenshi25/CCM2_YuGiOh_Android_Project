package master.ccm.ccm2yugiohproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.utils.NavigationMenuUtils;
import master.ccm.ccm2yugiohproject.utils.SoundMusicUtils;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.PileDeCarte.Deck;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ConfStartDuel_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Integer selected_lifePoint =0;
    private String selected_ia;
    private Deck selected_player_deck;
    private Deck selected_ia_deck;

    private Spinner spinnerLifePoint;
    private Spinner spinnerIA;
    private Spinner spinnerPlayerDecks;
    private Spinner spinnerIAListeDeck;
    private BottomNavigationView bottomNavigation;


    private ArrayList<Deck> listDeck = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_start_duel);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //spinner lifepoint
        spinnerLifePoint = (Spinner) findViewById(R.id.id_choice_lifepoint);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arraylifepoint, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerLifePoint.setAdapter(adapter);
        spinnerLifePoint.setOnItemSelectedListener(this);

        //spinner choix ia
        spinnerIA = (Spinner) findViewById(R.id.id_choice_ia);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterIA = ArrayAdapter.createFromResource(this,
                R.array.array_ia, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterIA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerIA.setAdapter(adapterIA);
        spinnerIA.setOnItemSelectedListener(this);

        selected_lifePoint  = Integer.parseInt(spinnerLifePoint.getSelectedItem().toString());
        selected_ia= spinnerIA.getSelectedItem().toString();

        listDeck = CurrentUser.getInstance().getDeckList();

        // playerDeck
        spinnerPlayerDecks = (Spinner) findViewById(R.id.id_choice_deck_player);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Deck> adapterPlayerDecks = new ArrayAdapter<Deck>(this,android.R.layout.simple_spinner_dropdown_item, listDeck);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        adapterPlayerDecks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerPlayerDecks.setAdapter(adapterPlayerDecks);
        spinnerPlayerDecks.setOnItemSelectedListener(this);

        // IADeck
        spinnerIAListeDeck = (Spinner) findViewById(R.id.id_choice_deck_ia);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Deck> adapterIADecks = new ArrayAdapter<Deck>(this,android.R.layout.simple_spinner_dropdown_item, listDeck);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        adapterIADecks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerIAListeDeck.setAdapter(adapterIADecks);
        spinnerIAListeDeck.setOnItemSelectedListener(this);


        selected_lifePoint  = Integer.parseInt(spinnerLifePoint.getSelectedItem().toString());
        selected_ia= spinnerIA.getSelectedItem().toString();
        selected_player_deck = (Deck) spinnerPlayerDecks.getSelectedItem();
        selected_ia_deck = (Deck) spinnerIAListeDeck.getSelectedItem();

        bottomNavigation = findViewById(R.id.id_conf_duel_bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        NavigationMenuUtils.onClickHome(ConfStartDuel_activity.this);
                        return true;
                    case R.id.navigation_deck:
                        NavigationMenuUtils.onClickToDeckList(ConfStartDuel_activity.this);
                        return true;
                    case R.id.navigation_party:
                        return true;
                    case R.id.navigation_setting:
                        NavigationMenuUtils.onClickProfile(ConfStartDuel_activity.this);
                        return true;
                }
                return false;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.navigation_party);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.id_choice_lifepoint :
                //Your Action Here.
                selected_lifePoint  = Integer.parseInt(spinnerLifePoint.getSelectedItem().toString());
                break;
            case R.id.id_choice_ia :
                //Your Another Action Here.
                selected_ia= spinnerIA.getSelectedItem().toString();
                break;
            case R.id.id_choice_deck_player :
                //Your Another Action Here.
                selected_player_deck = (Deck) spinnerPlayerDecks.getSelectedItem();
                break;
            case R.id.id_choice_deck_ia :
                //Your Another Action Here.
                selected_ia_deck = (Deck) spinnerIAListeDeck.getSelectedItem();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onClickDuelStart(View view){
        Intent intent = new Intent(this, Game_activity.class);


        if (selected_ia_deck != null && selected_player_deck != null) {
            intent.putExtra("idIADeck", selected_ia_deck.getId());
            intent.putExtra("idPlayerDeck", selected_player_deck.getId());
            intent.putExtra("lifepoint", selected_lifePoint);
            intent.putExtra("typeIA", selected_ia);

            startActivity(intent);

            SoundMusicUtils.launchSoundMusic(this, R.raw.passionate_duelist_theme, true, 0.5);
        }else{
            Toast.makeText(this,"Vous devez d'abord crée un deck",Toast.LENGTH_SHORT).show();
        }
    }
}
