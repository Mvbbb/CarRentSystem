package test;

import bean.Car;
import dao.impl.CarDaoImpl;
import org.junit.Test;

import java.util.List;
import java.util.zip.CheckedOutputStream;

import static org.junit.Assert.*;

public class CarDaoImplTest {

    @Test
    public void addCar() {
        Car car = new Car(8,"testcar1", "testcolor", "sd-12", 1299);
        CarDaoImpl carDao = new CarDaoImpl();
        int i = carDao.addCar(car);
        System.out.println(i);
        List<Car> carList = carDao.ListAllCars();
        for (Car car1 : carList) {
            System.out.println(car1);
        }
    }

    @Test
    public void updateCarById() {
        CarDaoImpl carDao = new CarDaoImpl();
        int i = carDao.updateCarById(new Car(5, "testcar1", "testcolor", "sd-12", 9899));
        List<Car> carList = carDao.ListAllCars();
        for (Car car1 : carList) {
            System.out.println(car1);
        }
    }

    @Test
    public void deleteCarById() {
        CarDaoImpl carDao = new CarDaoImpl();
        int i = carDao.deleteCarById(5);
        List<Car> carList = carDao.ListAllCars();
        for (Car car1 : carList) {
            System.out.println(car1);
        }
    }

    @Test
    public void queryCarByNameOrType() {
        CarDaoImpl carDao = new CarDaoImpl();
        List<Car> carList = carDao.queryCarByNameOrType("c91");
        for (Car car : carList) {
            System.out.println(car);
        }
    }

    @Test
    public void queryCarById() {
        CarDaoImpl carDao = new CarDaoImpl();
        Car car = carDao.queryCarById(4);
        System.out.println(car);
    }

    @Test
    public void listAllCars() {
        CarDaoImpl carDao = new CarDaoImpl();
        List<Car> carList = carDao.ListAllCars();
        for (Car car1 : carList) {
            System.out.println(car1);
        }
    }
}