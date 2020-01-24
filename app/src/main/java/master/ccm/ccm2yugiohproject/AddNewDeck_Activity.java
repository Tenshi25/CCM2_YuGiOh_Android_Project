package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Deck;
import master.ccm.manager.DeckDBManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewDeck_Activity extends AppCompatActivity {
    private TextView et_deckName;
    private TextView et_deckDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_deck_);

        et_deckName = findViewById(R.id.et_create_deckName);
        et_deckDesc = findViewById(R.id.et_create_deckDesc);
    }

    public void onClickReturn(View view) {
        Intent intent = new Intent(this, MenuDeckList_Activity.class);
        startActivity(intent);
        finish();
    }

    public void onClickAddDeck(View view) {
        DeckDBManager deckDBManager = new DeckDBManager();
        Deck newDeck = new Deck();
        newDeck.setName(et_deckName.getText().toString());
        newDeck.setDescription(et_deckDesc.getText().toString());
        newDeck.setIdUser(CurrentUser.getInstance().getId());
        deckDBManager.BeforeAddDeck(newDeck,this);
    }

    public void DeckAlreadyExists() {
        Toast.makeText(this,"Un de vos deck porte déjà ce nom",Toast.LENGTH_LONG).show();
    }

    public void AddDeckSucess(Deck newDeck) {
        Toast.makeText(this,"Le deck à été ajouter avec succées",Toast.LENGTH_LONG).show();
        CurrentUser.getInstance().getDeckList().add(newDeck);

        Intent intent = new Intent(this, MenuDeckList_Activity.class);
        startActivity(intent);
        finish();
    }

    public void AddDeckFail() {
        Toast.makeText(this,"Echec ! le deck n'a pas été ajouté",Toast.LENGTH_LONG).show();
    }
}
