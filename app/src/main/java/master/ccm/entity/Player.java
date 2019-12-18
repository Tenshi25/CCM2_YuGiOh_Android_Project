package master.ccm.entity;

public class Player {
    private String name;
    private Deck playerDeck;
    private Integer lifepoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    public Integer getLifepoint() {
        return lifepoint;
    }

    public void setLifepoint(Integer lifepoint) {
        this.lifepoint = lifepoint;
    }

    public void gainLifepoint(int p_gain) {
        this.lifepoint = this.lifepoint + p_gain;
    }

    public void perteLifepoint(int p_perte) {
        this.lifepoint =  this.lifepoint - p_perte;
        if (this.lifepoint < 0){
            this.lifepoint =0;
        }
    }

}
