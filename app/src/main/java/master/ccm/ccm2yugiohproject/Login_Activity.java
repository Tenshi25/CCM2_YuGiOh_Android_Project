package master.ccm.ccm2yugiohproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Login_Activity extends AppCompatActivity {
    private Button Gcloud_Button;
    private Button login_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Gcloud_Button =findViewById(R.id.bt_GLogin);
        login_Button =findViewById(R.id.bt_login);

        //login_Button.setOnClickListener();


    }
    public void OnClickToSignIn(View view){
        Log.i("Sign In", "Sign In");
        Intent intent = new Intent(this, SignUp_Activity.class);
        startActivity(intent);
        finish();
    }

    public void OnClickLogin(View view){
        Log.i("Login", "login");
    }


    public void onClickGLogin(View view){
        Log.i("Login", "Glogin");
    }

}
