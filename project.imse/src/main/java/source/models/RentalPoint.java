package source.models;

import javax.persistence.*;

@Entity
public class RentalPoint {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String company;
    private String land;
    private String city;
    private String street;
    private Long house;

    public RentalPoint(){}
    public RentalPoint(String company, String land, String city, String street, Long house) {
        this.company = company;
        this.land = land;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getHouse() {
        return house;
    }

    public void setHouse(Long house) {
        this.house = house;
    }
}
