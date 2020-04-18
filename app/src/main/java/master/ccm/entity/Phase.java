package master.ccm.entity;

import java.util.ArrayList;

public class Phase {
    private String name;
    private ArrayList<Action> listAction = new ArrayList<Action>();

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

    public Action findActionByName(String p_actionName){
        Action theAction =new Action();
        for (Action aAction : listAction){
            if(aAction.getNom().equals(p_actionName)){
                return aAction;
            }

        }
        return null;
    }
    public boolean containsActionByName(String p_actionName){
        Action theAction =new Action();
        for (Action aAction : listAction){
            if(aAction.getNom().equals(p_actionName)){
                return true;
            }

        }
        return false;
    }
}
