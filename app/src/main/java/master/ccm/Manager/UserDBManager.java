package master.ccm.Manager;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class UserDBManager {
    private static FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void AddUser(String newUsername, String newUserPassword) {
        if(!VerifUserExist(newUsername,newUserPassword)){
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userName", newUsername);
            userMap.put("passord", newUserPassword);


            database.collection("User").add(userMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        Log.i("AddUser","L'utilisateur à été ajouter");
                    }
                }
            });
        }





    }
    public boolean VerifUserExist(String newUsername, String newUserPassword) {
        return false;
    }
}
