package dao;

import bean.Car;

import java.util.List;

/**
 * 维护车辆信息管理
 */
public interface CarDao {
    /**
     * 增加一辆车
     * @param car
     * @return
     */
    int addCar(Car car);

    /**
     * 更新车辆信息
     * @param car
     * @return
     */
    int updateCarById(Car car);

    /**
     * 删除车辆
     * @param id
     * @return
     */
    int deleteCarById(int id);

    /**
     * 按照车型号或者名称查找车辆
     * @param nameOrType
     * @return
     */
    List<Car> queryCarByNameOrType(String nameOrType);

    /**
     * 按照车辆ID查找车辆
     * @param id
     * @return
     */
    Car queryCarById(int id);

    /**
     * 查询全部车辆
     * @return
     */
    List<Car> ListAllCars();

    
}
