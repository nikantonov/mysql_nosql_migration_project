package source.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PartnerOf implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @OneToOne
    private RentalPoint rentalPoint1;


    @OneToOne
    private RentalPoint rentalPoint2;

    public PartnerOf() {}

    public PartnerOf(RentalPoint rentalPoint1, RentalPoint rentalPoint2) {
        this.rentalPoint1 = rentalPoint1;
        this.rentalPoint2 = rentalPoint2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RentalPoint getRentalPoint1() {
        return rentalPoint1;
    }

    public void setRentalPoint1(RentalPoint rentalPoint1) {
        this.rentalPoint1 = rentalPoint1;
    }

    public RentalPoint getRentalPoint2() {
        return rentalPoint2;
    }

    public void setRentalPoint2(RentalPoint rentalPoint2) {
        this.rentalPoint2 = rentalPoint2;
    }
}
