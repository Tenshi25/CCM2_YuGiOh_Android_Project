package master.ccm.entity;

import java.util.ArrayList;

public class Phase {
    private String name;
    private ArrayList<Action> listAction;

    public Phase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Action> getListAction() {
        return listAction;
    }

    public void setListAction(ArrayList<Action> listAction) {
        this.listAction = listAction;
    }
}
