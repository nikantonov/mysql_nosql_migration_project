package source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.models.*;
import source.repositories.CarRepository;
import source.repositories.RentalPointRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Filler {
    private List<RentalPoint> rentalpoints = new ArrayList<>();
    private List<Car> cars= new ArrayList<>();
    private List<Engine> engines = new ArrayList<>();
    private List<User> users = new ArrayList<>();



    public List<RentalPoint> fillRental() {
        String land1 = "Germany";
        List<String> cities1 = new ArrayList<>();
        cities1.add("Berlin");
        cities1.add("Bremen");
        cities1.add("Hamburg");
        cities1.add("Kiel");
        cities1.add("Munich");
        cities1.add("Cologne");
        cities1.add("Dresden");
        cities1.add("Dortmund");

        for (int i = 0; i < 100; i++){
            Long house=Long.valueOf(i);
            RentalPoint rental = new RentalPoint("company"+i, land1, this.getRandom(cities1), "street"+i, house);
            rentalpoints.add(rental);
        }

        String land2 = "Austria";
        List<String> cities2 = new ArrayList<>();
        cities2.add("Vienna");
        cities2.add("Graz");
        cities2.add("Linz");
        cities2.add("Eisenstadt");
        cities2.add("Salzburg");
        cities2.add("Innsbruck");
        cities2.add("Bregenz");
        cities2.add("Klagenfurt");

        for (int i = 0; i < 100; i++){
            Long house=Long.valueOf(i);
            RentalPoint rental = new RentalPoint("company"+i, land2, this.getRandom(cities2), "street"+i, house);
            rentalpoints.add(rental);
        }

        String land3 = "France";
        List<String> cities3 = new ArrayList<>();
        cities3.add("Paris");
        cities3.add("Bordeaux");
        cities3.add("Marseille");
        cities3.add("Rennes");
        cities3.add("Lille");
        cities3.add("Lyon");
        cities3.add("Metz");
        cities3.add("Reims");

        for (int i = 0; i < 100; i++){
            Long house=Long.valueOf(i);
            RentalPoint rental = new RentalPoint("company"+i, land3, this.getRandom(cities3), "street"+i, house);
            rentalpoints.add(rental);
        }
        String land4 = "Spain";
        List<String> cities4 = new ArrayList<>();
        cities4.add("Madrid");
        cities4.add("Barcelona");
        cities4.add("Bilbao");
        cities4.add("Valencia");
        cities4.add("Sevilla");
        cities4.add("Granada");
        cities4.add("Cordoba");
        cities4.add("Girona");

        for (int i = 0; i < 100; i++){
            Long house=Long.valueOf(i);
            RentalPoint rental = new RentalPoint("company"+i, land4, this.getRandom(cities4), "street"+i, house);
            rentalpoints.add(rental);
        }

        String land5 = "Italy";
        List<String> cities5 = new ArrayList<>();
        cities5.add("Rome");
        cities5.add("Milan");
        cities5.add("Turin");
        cities5.add("Naples");
        cities5.add("Genoa");
        cities5.add("Verona");
        cities5.add("Udine");
        cities5.add("Palermo");

        for (int i = 0; i < 100; i++){
            Long house=Long.valueOf(i);
            RentalPoint rental = new RentalPoint("company"+i, land5, this.getRandom(cities5), "street"+i, house);
            rentalpoints.add(rental);
        }
        return rentalpoints;
    }

    public List<Car> fillCar(){
        for(int i = 0; i < 1000; i++){
            Car car = new Car("Maker"+i, "Model"+i, "Body"+i, this.getRandomRental(rentalpoints));
            cars.add(car);
        }
        return cars;
    }

    public List<Engine> fillEngine(){
        for(int i = 0; i < 100; i++){
            Engine engine = new Engine("Diesel", 150);
            engines.add(engine);
        }
        return engines;
    }

    public List<User> fillUsers(){
        for(int i = 0; i < 500; i++){
            User user = new User("Name"+i,"Login"+i, "Password"+i);
            users.add(user);
        }
        return users;
    }



    public String getRandom(List<String> city)
    {
        Random rand = new Random();
        return city.get(rand.nextInt(city.size()));
    }

    public RentalPoint getRandomRental(List<RentalPoint> rentals)
    {
        Random rand = new Random();
        return rentals.get(rand.nextInt(rentals.size()));
    }

    public Engine getRandomEngine(List<Engine> engines)
    {
        Random rand = new Random();
        return engines.get(rand.nextInt(engines.size()));
    }

    public Car getRandomCar(List<Car> cars)
    {
        Random rand = new Random();
        return cars.get(rand.nextInt(cars.size()));
    }

    public User getRandomUser(List<User> users)
    {
        Random rand = new Random();
        return users.get(rand.nextInt(users.size()));
    }
}
