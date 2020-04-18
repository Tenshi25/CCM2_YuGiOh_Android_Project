package master.ccm.entity;

import java.util.ArrayList;

public class Game {
    private int nbTurn;
    private int nbplayer;
    private Player currentplayer;
    //private Evenement Chaine;
    private ArrayList<Player> listPlayer = new ArrayList<>();
    private ArrayList<Phase> listePhase = new ArrayList<>();

    public void gameStart(ArrayList<User> p_listUser, int p_lifepoint) {
        for (User user : p_listUser) {
            Player aPlayer = new Player();
            aPlayer.setName(user.getUsername());
            aPlayer.setLifepoint(p_lifepoint);
            nbplayer = listPlayer.size();
            listPlayer.add(aPlayer);
        }


    }
    public void nextTurn(){
        nbTurn = nbTurn +1;
        int playerTurn = nbTurn % nbplayer;
        currentplayer = listPlayer.get(playerTurn);
    }
}
