package dao;

import bean.Car;

import java.sql.Date;
import java.util.List;

/**
 * 用于car表的操作
 * @author yuzhihai
 */
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
        int update = super.update("update car set carname=?,cartype=?,rent=?,price=?,color=?,fk_username=?,rent_date=? where id=?",
                car.getCarname(), car.getCartype(), car.isRent(), car.getPrice(), car.getColor(),car.getFk_username(),car.getRent_date(), car.getId());
        return update==1;
    }
    public boolean addCar(Car car){
        int update = super.update("insert into car(carname,cartype,rent,price,color,fk_username,rent_date)values(?,?,?,?,?,?,?)",
                car.getCarname(), car.getCartype(), car.isRent(), car.getPrice(), car.getColor(), car.getFk_username(),car.getRent_date());
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
        int update = super.update("update car set rent =1, fk_username= ? ,rent_date=? where id= ?" , username,new Date(System.currentTimeMillis()),id);
        return update == 1;
    }

    /**
     * 归还车辆
     * @param id
     * @return 需要支付的租金
     */
    public double returnCarById(int id) {
        Car car = queryCarById(id);
        long currentTime = new Date(System.currentTimeMillis()).getTime();
        long rentTime = car.getRent_date().getTime();
        int desc= (int) (currentTime -rentTime )/(1000*3600*24);
        car.setRent(false);
        car.setFk_username(null);
        car.setRent_date(null);
        updateCarById(car);
        return desc*car.getPrice();
    }
}
