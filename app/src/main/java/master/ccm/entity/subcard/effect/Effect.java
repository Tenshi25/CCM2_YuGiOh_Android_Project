package master.ccm.entity.subcard.effect;

import java.util.List;

import master.ccm.types.EffectType;

public class Effect {

    private EffectType action;

    private String subAction;

    private String quota;

    private String locationFrom;

    private String locationTo;

    private String locationActiveEffect;

    private String target;

    private int costPv;

    private int costPvPercent;

    private boolean autoActivable;

    private boolean alwaysActivable;

    private boolean passive;

    private boolean flip;

    private String whenIsActivable;

    private FilterCard filter;

    private DeterminedEffect determinedEffect;

    private DeScore deScore;

    private PieceScore pieceScore;

    private List<MultipleDeScore> multipleDeScores;

    private List<MultiplePieceScore> multiplePieceScores;

    public EffectType getAction() {
        return action;
    }

    public void setAction(EffectType action) {
        this.action = action;
    }

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public String getLocationActiveEffect() {
        return locationActiveEffect;
    }

    public void setLocationActiveEffect(String locationActiveEffect) {
        this.locationActiveEffect = locationActiveEffect;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public DeterminedEffect getDeterminedEffect() {
        return determinedEffect;
    }

    public void setDeterminedEffect(DeterminedEffect determinedEffect) {
        this.determinedEffect = determinedEffect;
    }

    public FilterCard getFilter() {
        return filter;
    }

    public void setFilter(FilterCard filter) {
        this.filter = filter;
    }

    public String getWhenIsActivable() {
        return whenIsActivable;
    }

    public void setWhenIsActivable(String whenIsActivable) {
        this.whenIsActivable = whenIsActivable;
    }

    public int getCostPv() {
        return costPv;
    }

    public void setCostPv(int costPv) {
        this.costPv = costPv;
    }

    public int getCostPvPercent() {
        return costPvPercent;
    }

    public void setCostPvPercent(int costPvPercent) {
        this.costPvPercent = costPvPercent;
    }

    public DeScore getDeScore() {
        return deScore;
    }

    public void setDeScore(DeScore deScore) {
        this.deScore = deScore;
    }

    public PieceScore getPieceScore() {
        return pieceScore;
    }

    public void setPieceScore(PieceScore pieceScore) {
        this.pieceScore = pieceScore;
    }

    public List<MultipleDeScore> getMultipleDeScores() {
        return multipleDeScores;
    }

    public void setMultipleDeScores(List<MultipleDeScore> multipleDeScores) {
        this.multipleDeScores = multipleDeScores;
    }

    public List<MultiplePieceScore> getMultiplePieceScores() {
        return multiplePieceScores;
    }

    public void setMultiplePieceScores(List<MultiplePieceScore> multiplePieceScores) {
        this.multiplePieceScores = multiplePieceScores;
    }

    public boolean isAutoActivable() {
        return autoActivable;
    }

    public void setAutoActivable(boolean autoActivable) {
        this.autoActivable = autoActivable;
    }

    public boolean isAlwaysActivable() {
        return alwaysActivable;
    }

    public void setAlwaysActivable(boolean alwaysActivable) {
        this.alwaysActivable = alwaysActivable;
    }

    public boolean isPassive() {
        return passive;
    }

    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }
}
