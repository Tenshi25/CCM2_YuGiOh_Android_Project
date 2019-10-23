package master.ccm.Manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import master.ccm.ccm2yugiohproject.Login_Activity;
import master.ccm.ccm2yugiohproject.SignUp_Activity;
import master.ccm.entity.User;

public class UserDBManager {
    private  FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static Boolean userExist ;

    public void AddUser(final User newUser, final SignUp_Activity context) {
        if(!userExist){
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("username", newUser.getUsername());
            userMap.put("password", newUser.getPassword());


            database.collection("User").add(userMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        Log.i("AddUser","L'utilisateur à été ajouter");

                        context.RegisterSuccess(task.getResult().getId(),newUser.getUsername());
                    }else{

                        context.RegistertFail();
                    }
                }
            });
        }else{
            context.RegistertFail();
        }





    }
    public void VerifUserExistBeforeInsert(final User newUser, final SignUp_Activity context) {
        userExist=false;
        database.collection("User").whereEqualTo("username",newUser.getUsername()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() >= 1) {
                    Log.i("task.getResult()",""+task.getResult().size());
                    Log.i("selectUtilisateur","Le nom existe déjà");
                    UserDBManager.setUserExist(true);
                    AddUser(newUser,context);

                }else{
                    UserDBManager.setUserExist(false);
                    AddUser(newUser,context);
                }

            }
        });



    }

    public void ConnectUser (final User user, final Login_Activity context)
    {{
        Log.d("username", ""+user.getUsername());
        Log.d("mot de pass md5", ""+user.getPassword());
        database.collection("User").whereEqualTo("username",user.getUsername()).whereEqualTo("password",user.getPassword()).get(Source.DEFAULT).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() == 1) {
                    Log.d("affichage", ""+task.getResult().size());
                    DocumentSnapshot result = task.getResult().getDocuments().get(0);
                    Log.d("succes affichage", result.getId() + " => " + result.get("username"));
                    context.ConnectSucess(result.getId(),result.get("username").toString());
                } else {
                    context.ConnectionFailed();
                    Log.w("erreur affichage", "Error getting documents.", task.getException());
                }
            }
        });
    }}
    private static void setUserExist(boolean p_userExist) {
        UserDBManager.userExist = p_userExist;

    }
}
