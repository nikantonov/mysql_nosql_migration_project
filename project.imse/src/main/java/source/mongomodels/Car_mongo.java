package source.mongomodels;
import org.springframework.data.annotation.Id;

public class Car_mongo {
    @Id
    private Long id;

    private String maker;
    private String model;
    private String body;
    private Carinfo_mongo car_info;
    private Engine_mongo engine;

    public Car_mongo(){}
    public Car_mongo(Long id, String maker, String model, String body, Carinfo_mongo car_info, Engine_mongo engine) {
        this.id = id;
        this.maker = maker;
        this.model = model;
        this.body = body;
        this.car_info = car_info;
        this.engine = engine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Carinfo_mongo getCar_info() {
        return car_info;
    }

    public void setCar_info(Carinfo_mongo car_info) {
        this.car_info = car_info;
    }

    public Engine_mongo getEngine() {
        return engine;
    }

    public void setEngine(Engine_mongo engine) {
        this.engine = engine;
    }
}
