package master.ccm.manager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

import master.ccm.ccm2yugiohproject.Home;
import master.ccm.entity.CurrentUser;
import master.ccm.entity.Score;

public class ScoreDBManager {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void initScore() {

        Map<String, Object> playerScore = new HashMap<>();
        playerScore.put("username", CurrentUser.getInstance().getName());
        playerScore.put("win", 0);
        playerScore.put("loss", 0);

        database.collection("Score").document(CurrentUser.getInstance().getId()).set(playerScore);
    }

    public void updateScoreEndGame(Score score) {

        Map<String, Object> playerScore = new HashMap<>();
        playerScore.put("username", score.getUsername());
        playerScore.put("win", score.getWin());
        playerScore.put("loss", score.getLoss());

        database.collection("Score").document(CurrentUser.getInstance().getId()).set(playerScore);
    }


    public void selectScore(final Home context, String action) {
        database.collection("Score").document(CurrentUser.getInstance().getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot result = task.getResult();
                            if (result.exists()){
                                Score score = new Score();
                                score.setUsername(result.get("username").toString());
                                score.setWin(Integer.parseInt(result.get("win").toString()));
                                score.setLoss(Integer.parseInt(result.get("loss").toString()));

                                if (action.equals("victory")){
                                    score.setWin(score.getWin()+1);
                                    updateScoreEndGame(score);
                                } else {
                                    if (action.equals("defeat")){
                                        score.setLoss(score.getLoss()+1);
                                        updateScoreEndGame(score);
                                    } else {
                                        context.modifyScore(score);
                                    }
                                }

                            } else {
                                initScore();
                            }

                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
