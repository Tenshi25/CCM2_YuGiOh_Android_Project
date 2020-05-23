package master.ccm.entity;

public class CardJson {
    private String id;
    private String monstre;
    private int atk;
    private int def;
    private String position;

    public String getName() {
        return monstre;
    }

    public void setName(String name) {
        this.monstre = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
