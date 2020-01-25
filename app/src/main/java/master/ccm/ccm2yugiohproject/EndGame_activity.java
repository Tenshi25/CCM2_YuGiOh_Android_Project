package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.PileDeCarte.Deck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGame_activity extends AppCompatActivity {
    private TextView labelvictory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent= getIntent();
        Bundle extrasData = intent.getExtras();

        labelvictory = findViewById(R.id.tv_labelVictory);
        if (extrasData.get("labelVictory") != null){

            labelvictory.setText(getString(extrasData.getInt("labelVictory")));
        }
    }
    public void onClickretour_endgame(View view) {
        Intent intentto = new Intent(this, ConfStartDuel_activity.class);
        startActivity(intentto);
        finish();
    }
}
