package source.mongomodels;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class RentalPoint_mongo {
    @Id
    private Long id;
    private String company;
    private String land;
    private String city;
    private String street;
    private Long house;
    private List<Long> carReferences;
    private List<Long> partnerReferences;

    public RentalPoint_mongo(){}
    public RentalPoint_mongo(Long id, String company, String land, String city, String street, Long house) {
        this.id = id;
        this.company = company;
        this.land = land;
        this.city = city;
        this.street = street;
        this.house = house;
        carReferences = new ArrayList<>();
        partnerReferences = new ArrayList<>();
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

    public List<Long> getCarReferences() {
        return carReferences;
    }

    public void setCarReferences(List<Long> carReferences) {
        this.carReferences = carReferences;
    }

    public List<Long> getPartnerReferences() {
        return partnerReferences;
    }

    public void setPartnerReferences(List<Long> partnerReferences) {
        this.partnerReferences = partnerReferences;
    }
}
