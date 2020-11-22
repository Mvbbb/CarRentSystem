package dao;

import bean.RentRecord;

import java.util.List;

/**
 * 维护租车信息管理和还车信息管理
 */
public interface RentRecordDao {

    /**
     * 增加借出/归还记录
     * @param rentRecord
     * @return
     */
    int addRentRecord(RentRecord rentRecord);

    /**
     * 列出所有借出/归还记录
     * @return
     */
    List<RentRecord> listAllRentRecord();

    /**
     * 列出指定车辆的借出/归还记录
     * @return
     */
    List<RentRecord> listAllRentRecordByCarId(int carId);

    /**
     * 列出指定用户的借出/归还记录
     * @return
     */
    List<RentRecord> listAllRentRecordByUserId(int userId);

    /**
     * 按照借出/归还记录编号删除记录
     * @param id
     * @return
     */
    int deleteRentRecordById(int id);

    /**
     * 按照借出/归还记录更新记录
     * @param rentRecord
     * @return
     */
    int updateRentRecordById(RentRecord rentRecord);
    
}
