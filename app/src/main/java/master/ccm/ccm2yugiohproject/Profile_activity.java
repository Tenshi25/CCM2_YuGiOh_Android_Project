package master.ccm.ccm2yugiohproject;

import androidx.appcompat.app.AppCompatActivity;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Outils;
import master.ccm.manager.UserDBManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Profile_activity extends AppCompatActivity {
    private EditText et_pseudo,et_password,et_v_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        et_pseudo = findViewById(R.id.et_pseudo_profile);
        et_password = findViewById(R.id.et_password_profile);
        et_v_password = findViewById(R.id.et_password_verif_profile);

        CurrentUser currentUser = CurrentUser.getInstance();
        if(currentUser.getPseudo().length()!=0) {
            et_pseudo.setText(currentUser.getPseudo());
        }


    }
    public void onClickRetourProfile(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();

    }
    public void onClickModifyProfile(View view) {
       String newPassword = et_password.getText().toString();
        String newVPassword = et_v_password.getText().toString();
       if (newPassword.length() != 0 && newVPassword.length() != 0){
           if (newPassword.equals(newVPassword))
           {
               if(et_pseudo.getText().toString().length()!=0){
                   String newPseudo = et_pseudo.getText().toString();

                   Outils outils = new Outils();
                   newPassword = outils.md5(newPassword);

                   UserDBManager userDBManager = new UserDBManager();

                   userDBManager.updateUserWithPassword(newPseudo,newPassword,this);
               }else{
                   Toast.makeText(this,"Votre pseudo est vide",Toast.LENGTH_SHORT).show();
               }

           }else{
               Toast.makeText(this,"Vous n'avez pas saisi deux fois le  même mot de passe",Toast.LENGTH_SHORT).show();
           }

       }else if (newPassword.toString().length()==0 && newPassword.toString().length()==0){
           if(et_pseudo.getText().toString().length()!=0){
               String newPseudo = et_pseudo.getText().toString();


               UserDBManager userDBManager = new UserDBManager();

               userDBManager.updateUserWithoutPassword(newPseudo,this);
           }else{
               Toast.makeText(this,"Votre pseudo est vide",Toast.LENGTH_SHORT).show();
           }

       }

    }

    public void OnUpdateSucess(String p_pseudo) {
        CurrentUser.getInstance().setPseudo(p_pseudo);
        Toast.makeText(this,"Votre compte à été modifier avec succées",Toast.LENGTH_SHORT).show();
    }
}
