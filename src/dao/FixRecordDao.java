package dao;

import bean.FixRecord;

import java.util.List;

/**
 * 维护修车信息管理
 */
public interface FixRecordDao {
    /**
     * 添加维修记录
     * @param fixRecord
     * @return
     */
    int addFixRecord(FixRecord fixRecord);

    /**
     * 根据ID更新维修记录
     * @param fix
     * @return
     */
    int updateFixRecordById(FixRecord fix);

    /**
     * 通过维修编号删除维修记录
     * @param id
     * @return
     */
    int deleteFixRecordById(int id);

    /**
     * 列出所有的维修记录
     * @return
     */
    List<FixRecord> listAllFixRecord();

    /**
     * 按照车辆编号查找维修记录
     * @return
     */
    List<FixRecord> queryFixRecordByCarId(int carId);

    /**
     * 按照用户编号查找维修记录
     * @return
     */
    List<FixRecord> queryFixRecordByUserId(int userId);

    /**
     * 按照维修记录编号查找维修记录
     * @return
     */
    FixRecord queryFixRecordById(int id);
}
