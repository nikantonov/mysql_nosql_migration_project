package source.mongomodels;

import java.sql.Date;

public class Rent_mongo {
    private java.util.Date start;
    private java.util.Date end;
    private Long car_reference;

    public Rent_mongo(){}
    public Rent_mongo(java.sql.Date start, java.sql.Date end, Long car_reference) {
        this.start = start;
        this.end = end;
        this.car_reference = car_reference;
    }

    public java.util.Date getStart() {
        return start;
    }

    public void setStart(java.sql.Date start) {
        this.start = start;
    }

    public java.util.Date getEnd() {
        return end;
    }

    public void setEnd(java.sql.Date end) {
        this.end = end;
    }

    public Long getCar_reference() {
        return car_reference;
    }

    public void setCar_reference(Long car_reference) {
        this.car_reference = car_reference;
    }
}
