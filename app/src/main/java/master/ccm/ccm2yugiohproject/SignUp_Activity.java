package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.Manager.UserDBManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        newUsername = editText_username.getText().toString();
        newUserPassworld = editText_password.getText().toString();
        newUserPassworld = md5(newUserPassworld);
        userDBManager.AddUser(newUsername,newUserPassworld);

    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
