package source.mongomodels;

public class Engine_mongo {
    private String type;
    private int PS;

    public Engine_mongo(){}
    public Engine_mongo(String type,int PS) {
        this.type = type;
        this.PS = PS;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPS() {
        return PS;
    }

    public void setPS(int PS) {
        this.PS = PS;
    }
}
