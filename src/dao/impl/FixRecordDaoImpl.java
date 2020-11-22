package dao.impl;

import bean.FixRecord;
import dao.BaseDao;
import dao.FixRecordDao;

import java.util.List;

public class FixRecordDaoImpl extends BaseDao implements FixRecordDao {
    @Override
    public int addFixRecord(FixRecord fixRecord) {
        int update = super.update("INSERT INTO fix_record(f_id,f_date,f_problem,fk_c_id,fk_u_id,f_price)VALUES(?,?,?,?,?,?);",
                fixRecord.getF_id(), fixRecord.getF_date(), fixRecord.getF_problem(), fixRecord.getFk_c_id(), fixRecord.getFk_u_id(), fixRecord.getF_price());
        return update;
    }

    @Override
    public int updateFixRecordById(FixRecord fix) {
        int update = super.update("UPDATE fix_record SET f_date=?,f_problem=?,fk_c_id=?,fk_u_id=?,f_price=? WHERE f_id=?;",
                fix.getF_date(), fix.getF_problem(), fix.getFk_c_id(), fix.getFk_u_id(), fix.getF_price(), fix.getF_id());
        return update;
    }

    @Override
    public int deleteFixRecordById(int id) {
        int update = super.update("DELETE FROM fix_record WHERE f_id=?;", id);
        return update;
    }

    @Override
    public List<FixRecord> listAllFixRecord() {
        List<FixRecord> fixRecords = super.queryForList(FixRecord.class, "SELECT f_id,f_date,f_problem,fk_c_id,fk_u_id,f_price FROM fix_record ;");
        return fixRecords;
    }

    @Override
    public List<FixRecord> queryFixRecordByCarId(int carId) {
        List<FixRecord> fixRecords = super.queryForList(FixRecord.class, "SELECT f_id,f_date,f_problem,fk_c_id,fk_u_id,f_price FROM fix_record WHERE fk_c_id=?;", carId);
        return fixRecords;
    }

    @Override
    public List<FixRecord> queryFixRecordByUserId(int userId) {
        List<FixRecord> fixRecords = super.queryForList(FixRecord.class, "SELECT f_id,f_date,f_problem,fk_c_id,fk_u_id,f_price FROM fix_record WHERE fk_u_id=?;", userId);
        return fixRecords;
    }

    @Override
    public FixRecord queryFixRecordById(int id) {
        FixRecord fixRecord = super.queryForOne(FixRecord.class, "SELECT f_id,f_date,f_problem,fk_c_id,fk_u_id,f_price FROM fix_record WHERE f_id=?;", id);
        return fixRecord;
    }
}
