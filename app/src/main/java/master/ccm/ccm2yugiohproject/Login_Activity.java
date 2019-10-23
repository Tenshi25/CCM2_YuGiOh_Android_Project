package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.Manager.UserDBManager;
import master.ccm.entity.Outils;
import master.ccm.entity.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login_Activity extends AppCompatActivity {
    private Button Gcloud_Button;
    private Button login_Button;

    private EditText editText_username ;
    private EditText editText_password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Gcloud_Button =findViewById(R.id.bt_GLogin);
        login_Button =findViewById(R.id.bt_login);

        //login_Button.setOnClickListener();
        editText_username= findViewById(R.id.ET_Name);
        editText_password= findViewById(R.id.ET_Password);

    }
    public void OnClickToSignIn(View view){
        Log.i("Sign In", "Sign In");
        Intent intent = new Intent(this, SignUp_Activity.class);
        startActivity(intent);
        finish();
    }

    public void OnClickLogin(View view){
        Log.i("Login", "login");
        UserDBManager userDBManager = new UserDBManager();
        User userToConnect = new User();
        userToConnect.setUsername(editText_username.getText().toString());
        Outils outils = new Outils();
        userToConnect.setPassword(outils.md5(editText_password.getText().toString()));
        userDBManager.ConnectUser(userToConnect, this);
    }


    public void onClickGLogin(View view){
        Log.i("Login", "Glogin");
    }

    public void ConnectSucess(String id, String username) {
        Toast.makeText(this,"la connexion est un succès",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void ConnectionFailed() {
        Toast.makeText(this,"Erreur de connexion",Toast.LENGTH_SHORT).show();
    }
}
