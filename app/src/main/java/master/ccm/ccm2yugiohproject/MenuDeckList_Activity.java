package master.ccm.ccm2yugiohproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import master.ccm.ccm2yugiohproject.utils.NavigationMenuUtils;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.manager.DeckDBManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MenuDeckList_Activity extends AppCompatActivity {

    private ArrayList<Deck> deckList ;
    private ListView listView;
    private List<String> tableauChaines = new ArrayList<String>();
    private Deck selectedDeck;
    private Deck[] tabDeck;
    private BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_deck_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //on récupére le recyclerView
        listView = (ListView) findViewById(R.id.id_lv_listDeck);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDeck = deckList.get(position);
                OnDeckClick(selectedDeck);

            }

        });

        DeckDBManager userDBManager =new DeckDBManager();
        userDBManager.selectUserDecks(this);

        bottomNavigation = findViewById(R.id.id_deck_list_bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        NavigationMenuUtils.onClickHome(MenuDeckList_Activity.this);
                        return true;
                    case R.id.navigation_deck:
                        return true;
                    case R.id.navigation_party:
                        NavigationMenuUtils.onClickDuel(MenuDeckList_Activity.this);
                        return true;
                    case R.id.navigation_setting:
                        NavigationMenuUtils.onClickProfile(MenuDeckList_Activity.this);
                        return true;
                }
                return false;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.navigation_deck);

    }
    public void OnDeckClick(Deck p_deck) {
        Toast.makeText(this,"deck : " + p_deck.getName(),Toast.LENGTH_SHORT).show();
    }
    public void selectAllUserDeckFini(ArrayList<Deck> listDeck) {
        CurrentUser.getInstance().setDeckList(listDeck);
        RemplirListView(listDeck);
    }

    public void onClickAdd(View view) {
            Intent intent = new Intent(this, AddNewDeck_Activity.class);
            startActivity(intent);
            finish();

    }

    public void onClickModifyDeck(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Deck deckAModifier = deckList.get(position);

        Intent intent = new Intent(this, DeckBuilder_Activity.class);
        intent.putExtra("idDeck",deckAModifier.getId());
        intent.putExtra("deckName",deckAModifier.getName());
        intent.putExtra("deckDesc",deckAModifier.getDescription());

        startActivity(intent);
        finish();

    }

    public void RemplirListView(ArrayList<Deck> p_deckList) {

        Log.i("logNomTailleListeDeck", "taille : " + p_deckList.size());

        deckList = p_deckList;
        int cpt = 0;
        tabDeck = new Deck[p_deckList.size()];
        Log.i("logNomTailleTabDeck", "taille : " + tabDeck.length);

        for (Deck oneDeck : deckList) {
            tabDeck[cpt] = oneDeck;
            Log.i("logDeck", cpt + " name : " + oneDeck.getName());
            cpt++;
        }

        ArrayAdapter<Deck> monArrayAdapter = new ArrayAdapter<Deck>(this, R.layout.line_deck_menu, tabDeck){
            private int vraiPosition=0;
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("logDeckPos", "pos : "+ position);

                Deck aDeck = tabDeck[position];

                if(convertView == null){
                    convertView = getLayoutInflater()
                            .inflate(R.layout.line_deck_menu, parent, false);
                }
                TextView nameDeck = (TextView) convertView.findViewById(R.id.tv_lineCardDeck_name);
                TextView descDeck = (TextView) convertView.findViewById(R.id.tv_lineDeck_desc);
                if(aDeck.getName()!= null){
                    nameDeck.setText(aDeck.getName());
                }
                if(aDeck.getDescription()!= null){
                    descDeck.setText(aDeck.getDescription());
                }
                Log.i("logNomUser","logNameUser : "+aDeck.getName()+" mail : "+aDeck.getName());
                //
                vraiPosition++;
                return convertView;
            }
        };
        listView.setAdapter(monArrayAdapter);

    }

    public void onClickDeleteDeck(View view) {

        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Deck deckASupprimer = deckList.get(position);
        createAndShowDialog(deckASupprimer,"Supression du deck : " +deckASupprimer.getName());
    }

    public void deleteDeckSucess() {
        Toast.makeText(this,"Le deck à bien été supprimer",Toast.LENGTH_SHORT).show();
    }

    public void deleteDeckFail() {
        Toast.makeText(this,"Erreur ! le deck n'a pas été supprimer",Toast.LENGTH_SHORT).show();
    }
    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }
    private void createAndShowDialog(final Deck decktoDel, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.alert_Supp_message);
        builder.setPositiveButton(R.string.alert_Supp_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // FIRE ZE MISSILES!
                deleteDeck(decktoDel);

            }
        })
                .setNegativeButton(R.string.alert_Supp_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.setTitle(title);
        builder.create().show();
    }

    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    private void deleteDeck(Deck deckASupprimer){

        DeckDBManager deckDBManager =  new DeckDBManager();
        deckDBManager.deleteDeck(deckASupprimer.getId(),this);
        deckList.remove(deckASupprimer);
        if (CurrentUser.getInstance().getDeckList().contains(deckASupprimer.getId())){
            CurrentUser.getInstance().getDeckList().remove(deckASupprimer.getId());
        }
        RemplirListView(deckList);
    }
}
