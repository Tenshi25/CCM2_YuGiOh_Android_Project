package master.ccm.entity;

public class DeterminedEffect {

    private FilterCard filterCard;

    private String filterPosition;

    private String determinedTypeApplication;

    private String location;

    private String targetOfLocation;

    public FilterCard getFilterCard() {
        return filterCard;
    }

    public void setFilterCard(FilterCard filterCard) {
        this.filterCard = filterCard;
    }

    public String getFilterPosition() {
        return filterPosition;
    }

    public void setFilterPosition(String filterPosition) {
        this.filterPosition = filterPosition;
    }

    public String getDeterminedTypeApplication() {
        return determinedTypeApplication;
    }

    public void setDeterminedTypeApplication(String determinedTypeApplication) {
        this.determinedTypeApplication = determinedTypeApplication;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTargetOfLocation() {
        return targetOfLocation;
    }

    public void setTargetOfLocation(String targetOfLocation) {
        this.targetOfLocation = targetOfLocation;
    }
}
