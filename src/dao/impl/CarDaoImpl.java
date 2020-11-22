package dao.impl;

import bean.Car;
import dao.BaseDao;
import dao.CarDao;

import java.util.List;

public class CarDaoImpl extends BaseDao implements CarDao {
    @Override
    public int addCar(Car car) {
        int update = super.update("INSERT INTO car_info(c_id,c_name,c_color,c_type,c_price) VALUES(?,?,?,?,?);",
                car.getC_id(), car.getC_name(), car.getC_color(), car.getC_type(), car.getC_price());
        return update;
    }

    @Override
    public int updateCarById(Car car) {
        int update = super.update("UPDATE car_info SET c_name = ? , c_color = ?, c_type = ?, c_price= ?, WHERE c_id = ?;",
                car.getC_name(), car.getC_color(), car.getC_type(),car.getC_price(),  car.getC_id());
        return update;
    }

    @Override
    public int deleteCarById(int id) {
        int update = super.update("DELETE FROM car_info where c_id= ?;", id);
        return update;
    }

    @Override
    public List<Car> queryCarByNameOrType(String nameOrType) {
        List<Car> carList = super.queryForList(Car.class, "SELECT c_id,c_name,c_color,c_type,c_price, FROM car_info where c_name LIKE '%"+nameOrType+"%' or c_type LIKE '%"+nameOrType+"%' ;");
        return carList;
    }

    @Override
    public Car queryCarById(int id) {
        Car car = super.queryForOne(Car.class, "SELECT c_id, c_name, c_color, c_type, c_price,  FROM car_info where c_id=?;", id);
        return car;
    }

    @Override
    public List<Car> ListAllCars() {
        List<Car> carList = super.queryForList(Car.class, "SELECT c_id, c_name, c_color, c_type, c_price FROM car_info ;");
        return carList;
    }
}