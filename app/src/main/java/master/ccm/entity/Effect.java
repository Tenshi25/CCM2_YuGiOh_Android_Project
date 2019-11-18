package master.ccm.entity;

import master.ccm.types.EffectType;

public class Effect {
    private EffectType action;

    private String quota;

    private String locationFrom;

    private String locationTo;

    private String locationActiveEffect;

    private String target;

    private int costPv;

    private int costPvPercent;

    private boolean isAutoActivable;

    private boolean isAlwaysActivable;

    private boolean isPassive;

    private String whenIsActivable;

    private FilterCard filter;

    private DeterminedEffect determinedEffect;

    public EffectType getAction() {
        return action;
    }

    public void setAction(EffectType action) {
        this.action = action;
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

    public boolean isAutoActivable() {
        return isAutoActivable;
    }

    public void setAutoActivable(boolean autoActivable) {
        isAutoActivable = autoActivable;
    }

    public boolean isAlwaysActivable() {
        return isAlwaysActivable;
    }

    public void setAlwaysActivable(boolean alwaysActivable) {
        isAlwaysActivable = alwaysActivable;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
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
}
