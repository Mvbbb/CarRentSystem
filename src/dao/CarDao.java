package dao;

import bean.Car;

import java.util.List;

public class CarDao extends BaseDao{
    public List<Car> listAllCar(){
        List<Car> cars = super.queryForList(Car.class, "Select * from car");
        return cars;
    }
    
    public boolean deleteCarById(int id){
        int update = super.update("delete from car where id=?", id);
        return update==1;
    }
    public boolean carExist(int id){
        Car car = super.queryForOne(Car.class, "select * from car where id=?", id);
        return car!=null;
    }
    public Car queryCarById(int id){
        Car car = queryForOne(Car.class, "select * from car where id=?", id);
        return car;
    }
    public boolean updateCarById(Car car){
        int update = super.update("update car set carname=?,cartype=?,rent=?,price=?,color=?,fk_username=? where id=?",
                car.getCarname(), car.getCartype(), car.isRent(), car.getPrice(), car.getColor(),car.getFk_username(), car.getId());
        return update==1;
    }
    public boolean addCar(Car car){
        int update = super.update("insert into car(carname,cartype,rent,price,color,fk_username)values(?,?,?,?,?,?)",
                car.getCarname(), car.getCartype(), car.isRent(), car.getPrice(), car.getColor(), car.getFk_username());
        return update==1;
    }

    public List<Car> listAllCarRentable() {
        List<Car> cars = super.queryForList(Car.class, "Select * from car where rent=false");
        return cars;
    }

    public List<Car> userRentInfo(String username) {
        List<Car> cars = super.queryForList(Car.class, "Select * from car where fk_username= ? and rent=true",username);
        return cars;
    }

    public boolean rentCarById(int id,String username) {
        int update = super.update("update car set rent =1,fk_username= ? where id= ?" , username,id);
        return update == 1;
    }
}
