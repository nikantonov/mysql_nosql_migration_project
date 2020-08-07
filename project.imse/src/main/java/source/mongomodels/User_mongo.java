package source.mongomodels;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class User_mongo {
    @Id
    private Long id;

    private String name;
    private String login;
    private String password;
    private List<Rent_mongo> rents;

    public User_mongo(){}
    public User_mongo(Long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        rents = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rent_mongo> getRents() {
        return rents;
    }

    public void setRents(List<Rent_mongo> rents) {
        this.rents = rents;
    }
}
