package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.Manager.UserDBManager;
import master.ccm.entity.Outils;
import master.ccm.entity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUp_Activity extends AppCompatActivity {
    private EditText editText_username ;
    private EditText editText_password ;

    private String newUsername ;
    private String newUserPassworld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editText_username = findViewById(R.id.ET_userneame_SignUp);
        editText_password = findViewById(R.id.ET_password_signUp);
    }

    public void OnClickReturnSignIn(View view) {
        Intent intent = new Intent(this, Login_Activity.class);
        startActivity(intent);
        finish();
    }

    public void OnClickSignUp(View view) {
        UserDBManager userDBManager = new UserDBManager();
        User newUser = new User();
        newUser.setUsername(editText_username.getText().toString());
        Outils outils = new Outils();
        newUser.setPassword(outils.md5(editText_password.getText().toString()));
        userDBManager.VerifUserExistBeforeInsert(newUser, this);


    }



    public void RegisterSuccess(String id, String username) {
        Toast.makeText(this,"Vous êtes connécté",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    public void RegistertFail() {
        Toast.makeText(this,"Le login est déjà utilisé",Toast.LENGTH_SHORT).show();
    }



}
