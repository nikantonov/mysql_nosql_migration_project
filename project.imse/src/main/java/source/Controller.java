package source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import source.models.*;
import source.mongomodels.*;
import source.mongorepositories.CarMongoRepository;
import source.mongorepositories.RentalPointMongoRepository;
import source.mongorepositories.UserMongoRepository;
import source.repositories.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Controller
public class Controller {
    private Filler filler = new Filler();
    private List<RentalPoint> rentalpoints = filler.fillRental();
    private List<Car> cars = filler.fillCar();
    private List<Engine> engines = filler.fillEngine();
    private List<User> users = filler.fillUsers();
    Set<Car_mongo> possible_cars;

    @Autowired
    RentalPointRepository rentalRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarInformationCardRepository carInfoRepository;

    @Autowired
    EngineRepository engineRepository;

    @Autowired
    HasEngineRepository hasEngineRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    PartnerOfRepository partnerRepository;

    @Autowired
    CarMongoRepository carMongoRepository;

    @Autowired
    RentalPointMongoRepository rentalPointMongoRepository;

    @Autowired
    UserMongoRepository userMongoRepository;

    private User current_user;
    private String renting_result;
    private String current_land;
    private String current_city;
    private Car current_car;
    private Car_mongo current_car_mongo;
    private Engine current_engine;
    private java.sql.Date start_d;
    private java.sql.Date end_d;
    private StringModel citiesModel;
    private StringModel carModel;
    private StringModel engineModel;

    @GetMapping("/start")
    public String start() {
        return "Start";
    }

    @GetMapping("/migrate")
    public String migrate(){
        Iterable<Car> cars = carRepository.findAll();
        for (Car car : cars){
            CarInformationCard card = carInfoRepository.findByCar(car);
            Engine engine = new Engine();
            Iterable<HasEngine> hasEngine = hasEngineRepository.findByCar(car);
            for(HasEngine has : hasEngine){
                if (has.getCar().getId().equals(car.getId())){
                    engine = has.getEngine();
                }
            }
            Carinfo_mongo info = new Carinfo_mongo(card.getNumberOfRents(),  card.getKm(), card.getCurrent_Place());
            Engine_mongo engine_mongo = new Engine_mongo(engine.getType(), engine.getPS());
            Car_mongo car_m = new Car_mongo(car.getId(), car.getMaker(), car.getModel(), car.getBody(), info, engine_mongo);
            carMongoRepository.insert(car_m);
        }

        Iterable<RentalPoint> rentalPoints = rentalRepository.findAll();
        for (RentalPoint rp : rentalPoints){
            RentalPoint_mongo rpm = new RentalPoint_mongo(rp.getId(), rp.getCompany(), rp.getLand(), rp.getCity(), rp.getStreet(), rp.getHouse());
            List<Long> list_cars = new ArrayList<>();
            List<Long> list_partners = new ArrayList<>();
            for (Car car : cars){
                if(car.getRentalPoint().getId().equals(rp.getId())){
                    list_cars.add(car.getId());
                }
            }
            Iterable<PartnerOf> partners = partnerRepository.findAll();
            for (PartnerOf partner : partners){
                if (partner.getRentalPoint1().getId().equals(rpm.getId())){
                    list_partners.add(partner.getRentalPoint2().getId());
                }
                if (partner.getRentalPoint2().getId().equals(rpm.getId())){
                    list_partners.add(partner.getRentalPoint1().getId());
                }
            }
            rpm.setCarReferences(list_cars);
            rpm.setPartnerReferences(list_partners);
            rentalPointMongoRepository.insert(rpm);
        }

        Iterable<User> users = userRepository.findAll();
        for (User user : users){
            User_mongo user_m = new User_mongo(user.getId(), user.getName(), user.getLogin(), user.getPassword());
            Iterable<Rent> rents = rentRepository.findAll();
            List<Rent_mongo> rents_m = new ArrayList<>();
            for(Rent rent : rents){
                if(rent.getUser().getId().equals(user.getId())){
                    java.sql.Date start = rent.getStart();
                    java.sql.Date end = rent.getEnd();
                    Rent_mongo rm = new Rent_mongo(start, end, rent.getCar().getId());
                    rents_m.add(rm);
                }
            }
            user_m.setRents(rents_m);
            userMongoRepository.insert(user_m);
        }
        return "MainPage";
    }

    //FOR TESTING ONLY
    @GetMapping("/cabinet")
    public String cabinet() {
        return "Cabinet";
    }

    @GetMapping("/rent")
    public String rent() {
        return "Renting";
    }

    @GetMapping("/report")
    public String report() {
        return "Report";
    }

    @RequestMapping("/validate/country/report/mongo")
    public String validateReportMongo(
            @RequestParam(value = "country", required = false) String country,
            Model model
    ) {
        int count = 0;
        List<RentalPoint_mongo> points = rentalPointMongoRepository.findAll();
        User_mongo user = userMongoRepository.findById(current_user.getId());
        List<Rent_mongo> rents = user.getRents();
        List<Long> references = new ArrayList<>();
        for (Rent_mongo rent : rents){
            references.add(rent.getCar_reference());
        }
        for (RentalPoint_mongo rental : points){
            for(Long ref : rental.getCarReferences()){
                if(rental.getLand().equals(country)){
                    for (Long ref_2 : references){
                        if(ref.equals(ref_2)){
                            count++;
                        }
                    }
                }
            }
        }
        String name = current_user.getName();
        String login = current_user.getLogin();
        String count_s = Integer.toString(count);
        String answer = " User " +  name + " with login " + login + " has rented cars in the land " + country + " " +count_s + " time ";
        StringModel answer_sm = new StringModel(answer);
        model.addAttribute("answer", answer_sm);
        return "ReportResult";
    }

    @RequestMapping("/validate/country/report")
    public String validateReport(
            @RequestParam(value = "country", required = false) String country,
            Model model
    ) {
        Iterable<RentalPoint> rental_points = rentalRepository.findAll();
        int count = 0;
        for (RentalPoint rp : rental_points){
            if(rp.getLand().equals(country)){
                Iterable<Rent> rents = rentRepository.findAll();
                for(Rent r : rents){
                    if(r.getUser().getLogin().equals(current_user.getLogin())){
                        if(r.getCar().getRentalPoint().getLand().equals(country)){
                            count = count + 1;
                        }
                    }
                }
                String name = current_user.getName();
                String login = current_user.getLogin();
                String count_s = Integer.toString(count);
                String answer = " User " +  name + " with login " + login + " has rented cars in the land " + country + " " +count_s + " time ";
                StringModel answer_sm = new StringModel(answer);
                model.addAttribute("answer", answer_sm);
                return "ReportResult";

            }
        }

        return "Report";
    }


    @RequestMapping("/validate/country/mongo")
    public String validateCountryMongo(
            @RequestParam(value = "country", required = false) String country,
            Model model
    ) {
        List<RentalPoint_mongo> points = rentalPointMongoRepository.findAll();
        Set<String> possible_cities = new HashSet<>();
        for (RentalPoint_mongo rental : points){
            if(rental.getLand().equals(country)){
                String cities = " ";
                for(RentalPoint_mongo rp : points){
                    if(rp.getLand().equals(country)) {
                        possible_cities.add(rp.getCity());
                    }
                }
                renting_result = country;
                current_land = country;
                for(String s : possible_cities){
                    cities = cities+s+" ";
                }
                citiesModel = new StringModel(cities);
                model.addAttribute("city", citiesModel);
                return "City";
            }
        }
        return "Renting";
    }

    @RequestMapping("/validate/country")
    public String validateCountry(
            @RequestParam(value = "country", required = false) String country,
            Model model
    ) {
        Iterable<RentalPoint> rental_points = rentalRepository.findAll();
        Set<String> possible_cities = new HashSet<>();
        for (RentalPoint rp : rental_points){
            if(rp.getLand().equals(country)){
                String cities = " ";
                for(RentalPoint rp2 : rental_points){
                    if(rp2.getLand().equals(country)) {
                        possible_cities.add(rp2.getCity());
                    }
                }
                renting_result = country;
                current_land = country;
                for(String s : possible_cities){
                    cities = cities+s+" ";
                }
                citiesModel = new StringModel(cities);
                model.addAttribute("city", citiesModel);
                return "City";
            }
        }
        return "Renting";
    }

    @RequestMapping("/validate/date/mongo")
    public String validateDateMongo(
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end,
            Model model
    ) {
        try {
            java.sql.Date start_date = java.sql.Date.valueOf(start);
            start_d = start_date;
        }catch(Exception e){
            return "Date";
        }
        try {
            java.sql.Date end_date = java.sql.Date.valueOf(end);
            end_d = end_date;
        }catch(Exception e){
            return "Date";
        }
        if (!end_d.after(start_d)){
            return "Date";
        }
        Carinfo_mongo info = current_car_mongo.getCar_info();
        info.setNumberOfRents(info.getNumberOfRents()+1);
        current_car_mongo.setCar_info(info);
        Rent_mongo rent = new Rent_mongo(start_d, end_d, current_car_mongo.getId());
        User_mongo user = userMongoRepository.findById(current_user.getId());
        List<Rent_mongo> rents = user.getRents();
        rents.add(rent);
        user.setRents(rents);
        userMongoRepository.save(user);
        String maker = current_car_mongo.getMaker();
        String model_c = current_car_mongo.getModel();
        String type = current_car_mongo.getEngine().getType();
        String result = " You have rented a " + maker + " " + model_c + " car with "+ type + " engine in " + current_city + " , " + current_land + " from " + start + " to " + end;
        StringModel s_result = new StringModel(result);
        model.addAttribute("result", s_result);
        return "Result";
    }

    @RequestMapping("/validate/date")
    public String validateDate(
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end,
            Model model
    ) {
        try {
            java.sql.Date start_date = java.sql.Date.valueOf(start);
            start_d = start_date;
        }catch(Exception e){
            return "Date";
        }
        try {
            java.sql.Date end_date = java.sql.Date.valueOf(end);
            end_d = end_date;
        }catch(Exception e){
            return "Date";
        }
        if (!end_d.after(start_d)){
            return "Date";
        }
        Rent rent = new Rent(current_car, current_user,start_d,end_d);
        rentRepository.save(rent);
        CarInformationCard car_info = carInfoRepository.findByCar(current_car);
        car_info.setNumberOfRents(car_info.getNumberOfRents()+1);
        carInfoRepository.save(car_info);
        String maker = current_car.getMaker();
        String model_c = current_car.getModel();
        String type = current_engine.getType();
        String result = " You have rented a " + maker + " " + model_c + " car with "+ type + " engine in " + current_city + " , " + current_land + " from " + start + " to " + end;
        StringModel s_result = new StringModel(result);
        model.addAttribute("result", s_result);
        return "Result";
    }

    @RequestMapping("/validate/engine")
    public String validateEngine(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "PS", required = false) String PS,
            Model model
    ) {
        Iterable<Engine> engines = engineRepository.findByType(type);
        int PS_int;
        try {
            PS_int = Integer.parseInt(PS);
        }catch(Exception e){
            model.addAttribute("engine", engineModel);
            return "Engine";
        }
        Iterable<HasEngine> has_engines = hasEngineRepository.findByCar(current_car);
        for(Engine engine : engines){
            if(engine.getPS() == PS_int){
                for(HasEngine hasEngine : has_engines){
                    if(hasEngine.getEngine().equals(engine)){
                        current_engine = engine;
                        return "Date";
                    }
                }
            }
        }
        model.addAttribute("engine", engineModel);
        return "Engine";
    }

    @RequestMapping("/validate/car/mongo")
    public String validateCarMongo(
            @RequestParam(value = "maker", required = false) String maker,
            @RequestParam(value = "model", required = false) String model,
            Model model1
    ) {
        for (Car_mongo car: possible_cars){
            if(car.getMaker().equals(maker)){
                if(car.getModel().equals(model)){
                    current_car_mongo = car;
                    Engine_mongo engine = car.getEngine();
                    String engines = engine.getType() +" "+ engine.getPS();
                    engineModel = new StringModel(engines);
                    model1.addAttribute("engine", engineModel);
                    return "Date";
                }
            }
        }
        model1.addAttribute("car", carModel);
        return "Car";
    }

    @RequestMapping("/validate/car")
    public String validateCar(
            @RequestParam(value = "maker", required = false) String maker,
            @RequestParam(value = "model", required = false) String model,
            Model model1
    ) {
        Iterable<Car> cars = carRepository.findByMaker(maker);
        for (Car car: cars){
            if(car.getModel().equals(model)){
                if(car.getRentalPoint().getCity().equals(current_city)){
                    current_car = car;
                    Iterable<HasEngine> has_engine = hasEngineRepository.findByCar(car);
                    String engines = new String();
                    for(HasEngine hasEngine: has_engine){
                        String type = hasEngine.getEngine().getType();
                        int PS_int = hasEngine.getEngine().getPS();
                        String PS = Integer.toString(PS_int);
                        engines = engines + type + " " + PS + "* * * *";
                        engineModel = new StringModel(engines);
                    }
                    model1.addAttribute("engine", engineModel);
                    return "Engine";
                }
            }
        }
        model1.addAttribute("car", carModel);
        return "Car";

    }

    @RequestMapping("/validate/city/mongo")
    public String validateCityMongo(
            @RequestParam(value = "city", required = false) String city,
            Model model
    ) {
        List<RentalPoint_mongo> rental_points = rentalPointMongoRepository.findAll();
        String cars_string = " ";
        possible_cars = new HashSet<>();
        for (RentalPoint_mongo rp : rental_points){
            if(rp.getCity().equals(city)){
                for(RentalPoint_mongo point: rental_points){
                    if(point.getCity().equals(city)){
                        List<Long> indexes = point.getCarReferences();
                        for (Long l : indexes){
                            Car_mongo car = carMongoRepository.findById(l);
                            possible_cars.add(car);
                        }
                    }
                }
                for(Car_mongo car : possible_cars){
                    String car_maker = car.getMaker();
                    String car_model = car.getModel();
                    cars_string = cars_string + " " + car_maker + " " + car_model + "* * * *";
                }
                carModel = new StringModel(cars_string);
                renting_result = renting_result+" "+city;
                current_city = city;
                model.addAttribute("car", carModel);
                return "Car";
            }
        }
        model.addAttribute("city", citiesModel);
        return "City";
    }

    @RequestMapping("/validate/city")
    public String validateCity(
            @RequestParam(value = "city", required = false) String city,
            Model model
    ) {
        Iterable<RentalPoint> rental_points = rentalRepository.findAll();
        String cars_string = " ";
        Set<Car> possible_cars = new HashSet<>();
        for (RentalPoint rp : rental_points){
            if(rp.getCity().equals(city)){
                Iterable<Car> cars = carRepository.findAll();
                for(Car car: cars){
                    if(car.getRentalPoint().getCity().equals(city)){
                        possible_cars.add(car);
                    }
                }
                for(Car car : possible_cars){
                    String car_maker = car.getMaker();
                    String car_model = car.getModel();
                    cars_string = cars_string + " " + car_maker + " " + car_model + "* * * *";
                }
                carModel = new StringModel(cars_string);
                renting_result = renting_result+" "+city;
                current_city = city;
                model.addAttribute("car", carModel);
                return "Car";
            }
        }
        model.addAttribute("city", citiesModel);
        return "City";
    }

    @RequestMapping("/validate")
    public String validateUser(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "password", required = false) String password,
            Model model
    ) {
        User user = userRepository.findByLogin(login);
        if(user != null){
            model.addAttribute("login", user.getLogin());
            model.addAttribute("name", user.getName());
            if(user.getPassword().equals(password)){
                current_user = user;
                return "MainPage";
            }
        }
        return "Cabinet";

    }

    @RequestMapping("/fill")
    public String fill() {
        for (RentalPoint rp : rentalpoints) {
            rentalRepository.save(rp);
        }

        for (Car car : cars) {
            carRepository.save(car);
        }

        Iterable<Car> cars1 = carRepository.findAll();
        for (Car car : cars1){
            if(car.getId() != null) {
                CarInformationCard carInfo = new CarInformationCard(car.getId()+1,car, 0, 0, filler.getRandomRental(rentalpoints).getCity());
                if(carInfo.getId() != null) {
                    carInfoRepository.save(carInfo);
                }
            }
        }

        for(Engine engine : engines){
            engineRepository.save(engine);
        }

        Iterable<Engine> engines = engineRepository.findAll();
        List<Engine> engines_rand = new ArrayList<>();
        for (Engine engine : engines){
            engines_rand.add(engine);
        }
        for (Car car : cars1){
            if(car.getId() != null){
                HasEngine has = new HasEngine(car, filler.getRandomEngine(engines_rand));
                hasEngineRepository.save(has);
            }
        }

        for(User user : users){
            userRepository.save(user);
        }

        Iterable<Car> cars_rand = carRepository.findAll();
        Iterable<User> users_rand = userRepository.findAll();
        List<Car> cars_rand2 = new ArrayList<>();
        for (Car car : cars_rand){
            cars_rand2.add(car);
        }
        List<User> users_rand2 = new ArrayList<>();
        for (User user : users_rand){
            users_rand2.add(user);
        }

        for(int i = 0; i < 2000; i++){
            String str = "2019-01-01";
            java.sql.Date date=java.sql.Date.valueOf(str);
            Rent rent = new Rent(filler.getRandomCar(cars_rand2), filler.getRandomUser(users_rand2), date, date);
            rentRepository.save(rent);

        }

        Iterable<RentalPoint> rental_points = rentalRepository.findAll();
        List<RentalPoint> rental_rand = new ArrayList<>();
        for (RentalPoint rental : rental_points){
            rental_rand.add(rental);
        }

        for(int i = 0; i < 10; i++){
            PartnerOf po = new PartnerOf(filler.getRandomRental(rental_rand), filler.getRandomRental(rental_rand));
            partnerRepository.save(po);
        }

        return "Cabinet";

    }
}
