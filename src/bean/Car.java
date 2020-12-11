package bean;

import java.sql.Date;

/**
 * @author yuzhihai
 */
public class Car {
    private int id;
    private String carname;
    private String cartype;
    private boolean rent;
    private double price;
    private String color;
    private String fk_username;
    private Date rent_date;
    
    public Car() {
    }

    public Car(String carname, String cartype, boolean rent, double price, String color, String fk_username,Date rent_date) {
        this.carname = carname;
        this.cartype = cartype;
        this.rent = rent;
        this.price = price;
        this.color = color;
        this.fk_username = fk_username;
        this.rent_date = rent_date;
    }


    public Car(int id, String carname, String cartype, boolean rent, double price, String color, String fk_username,Date rent_date) {
        this.id = id;
        this.carname = carname;
        this.cartype = cartype;
        this.rent = rent;
        this.price = price;
        this.color = color;
        this.fk_username = fk_username;
        this.rent_date = rent_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFk_username() {
        return fk_username;
    }

    public void setFk_username(String fk_username) {
        this.fk_username = fk_username;
    }

    public Date getRent_date() {
        return rent_date;
    }

    public void setRent_date(Date rent_date) {
        this.rent_date = rent_date;
    }
}
