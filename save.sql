CREATE TABLE rentalPoint
(
   id INTEGER NOT NULL AUTO_INCREMENT,
   company VARCHAR(50) NOT NULL,
   land VARCHAR(50) NOT NULL,
   city VARCHAR(50) NOT NULL,
   street VARCHAR(50) NOT NULL,
   house INTEGER NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE car
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  maker VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  body VARCHAR(50) NOT NULL,
  rental_id INTEGER NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (rental_id) REFERENCES rentalPoint(id)
);

CREATE TABLE carInformationCard
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  numberOfRents INTEGER,
  km DOUBLE,
  currentPlace VARCHAR(50) NOT NULL,
  car_id INTEGER NOT NULL,
  PRIMARY KEY(id, car_id),
  FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE
);

CREATE TABLE engine
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  car_id INTEGER NOT NULL,
  type VARCHAR(50) NOT NULL,
  PS INTEGER NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE
);

CREATE TABLE user
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE rent
(
  user_id INTEGER NOT NULL,
  car_id INTEGER NOT NULL,
  start DATE NOT NULL,
  end DATE NOT NULL,
  PRIMARY KEY(user_id, car_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (car_id) REFERENCES car(id)
);
CREATE TABLE hasEngine
(
  car_id INTEGER NOT NULL,
  engine_id INTEGER NOT NULL,
  PRIMARY KEY(car_id, engine_id),
  FOREIGN KEY (car_id) REFERENCES car(id),
  FOREIGN KEY (engine_id) REFERENCES engine(id)
);

CREATE TABLE partnerOf
(
  rental_id1 INTEGER NOT NULL,
  rental_id2 INTEGER NOT NULL,
  PRIMARY KEY(rental_id1, rental_id2),
  FOREIGN KEY (rental_id1) REFERENCES rentalPoint(id),
  FOREIGN KEY (rental_id2) REFERENCES rentalPoint(id)
);

