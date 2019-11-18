package master.ccm.manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import master.ccm.ccm2yugiohproject.Login_Activity;
import master.ccm.ccm2yugiohproject.Profile_activity;
import master.ccm.ccm2yugiohproject.SignUp_Activity;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.User;

public class UserDBManager {
    private  FirebaseFirestore database = FirebaseFirestore.getInstance();
    private static Boolean userExist ;

    public void AddUser(final User newUser, final SignUp_Activity context) {
        if(!userExist){
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("username", newUser.getUsername());
            userMap.put("password", newUser.getPassword());
            userMap.put("pseudo", newUser.getUsername());


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
                Log.d("task.getResult().size", ""+task.getResult().size());
                if (task.getResult().size() == 1) {
                    String pseudo ="";
                    Log.d("affichage", ""+task.getResult().size());
                    DocumentSnapshot result = task.getResult().getDocuments().get(0);
                    Log.d("succes affichage", result.getId() + " => " + result.get("username"));
                    if(result.get("pseudo").toString().length()!=0){
                         pseudo = result.get("pseudo").toString();
                    }

                    context.ConnectSucess(result.getId(),result.get("username").toString(),pseudo);
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

    public void updateUserWithoutPassword(final String p_pseudo, final Profile_activity context) {
        DocumentReference washingtonRef = database.collection("User").document(CurrentUser.getInstance().getId());

        washingtonRef
                .update("pseudo", p_pseudo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("update sucess", "DocumentSnapshot successfully updated!");
                        context.OnUpdateSucess(p_pseudo);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("update Fail", "Error updating document", e);
                    }
                });

    }
    public void updateUserWithPassword(final  String p_pseudo,String p_password,final Profile_activity context) {
        DocumentReference washingtonRef = database.collection("User").document(CurrentUser.getInstance().getId());

        washingtonRef
                .update("pseudo", p_pseudo,
                        "password",p_password)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("update sucess", "DocumentSnapshot successfully updated!");
                        context.OnUpdateSucess(p_pseudo);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("update Fail", "Error updating document", e);
                    }
                });

    }
}
