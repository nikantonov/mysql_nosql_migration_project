package source.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private java.sql.Date start;
    private java.sql.Date end;

    public Rent() {}

    public Rent(Car car, User user, java.sql.Date start, java.sql.Date end) {
        this.car = car;
        this.user = user;
        this.start = start;
        this.end = end;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
