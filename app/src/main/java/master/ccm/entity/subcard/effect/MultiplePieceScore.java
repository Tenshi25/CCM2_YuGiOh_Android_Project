package master.ccm.entity.subcard.effect;

public class MultiplePieceScore {

    private int minResultPiece;
    private int maxResultPiece;
    private String position;
    private Effect effect;

    public int getMinResultPiece() {
        return minResultPiece;
    }

    public void setMinResultPiece(int minResultPiece) {
        this.minResultPiece = minResultPiece;
    }

    public int getMaxResultPiece() {
        return maxResultPiece;
    }

    public void setMaxResultPiece(int maxResultPiece) {
        this.maxResultPiece = maxResultPiece;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
