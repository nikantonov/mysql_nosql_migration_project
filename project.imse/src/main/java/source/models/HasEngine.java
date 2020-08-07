package source.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HasEngine implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;


    @OneToOne
    @JoinColumn(name="engine_id")
    private Engine engine;

    public HasEngine() {}
    public HasEngine(Car car, Engine engine) {
        this.car = car;
        this.engine = engine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
