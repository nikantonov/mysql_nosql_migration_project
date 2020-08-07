package source.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CarInfoID.class)
public class CarInformationCard implements Serializable {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Id
    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;

    private int numberOfRents;
    private double km;
    private String current_Place;

    public CarInformationCard() { }

    public CarInformationCard(Long id, Car car, int numberOfRents, double km, String current_place) {
        this.id = id;
        this.car = car;
        this.numberOfRents = numberOfRents;
        this.km = km;
        this.current_Place = current_place;
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

    public int getNumberOfRents() {
        return numberOfRents;
    }

    public void setNumberOfRents(int numberOfRents) {
        this.numberOfRents = numberOfRents;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getCurrent_Place() {
        return current_Place;
    }

    public void setCurrent_Place(String current_Place) {
        this.current_Place = current_Place;
    }
}


