package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void onClickQuit(View view){
        finish();
    }
    public void onClickToDeckList(View view){
        Intent intent = new Intent(this, MenuDeckList_Activity.class);
        startActivity(intent);
        finish();
    }
}
