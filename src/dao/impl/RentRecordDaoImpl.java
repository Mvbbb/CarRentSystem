package dao.impl;

import bean.RentRecord;
import dao.BaseDao;
import dao.RentRecordDao;

import java.util.List;

public class RentRecordDaoImpl extends BaseDao implements RentRecordDao{
    @Override
    public int addRentRecord(RentRecord rentRecord) {
        int update = super.update("INSERT INTO rent_record(rent_id,rent_date,fk_u_id,fk_c_id,rent_isreturn,rent_return_date)VALUES(?,?,?,?,?,?);",
                rentRecord.getRent_id(), rentRecord.getRent_date(), rentRecord.getFk_u_id(), rentRecord.getFk_c_id(), rentRecord.isRent_isreturn(), rentRecord.getRent_return_date());
        return update;
    }

    @Override
    public List<RentRecord> listAllRentRecord() {
        List<RentRecord> rentRecords = super.queryForList(RentRecord.class, "SELECT rent_id,rent_date,fk_u_id,fk_c_id,rent_isreturn,rent_return_date FROM rent_record;");
        return rentRecords;
    }

    @Override
    public List<RentRecord> listAllRentRecordByCarId(int carId) {
        List<RentRecord> rentRecords = super.queryForList(RentRecord.class, "SELECT rent_id,rent_date,fk_u_id,fk_c_id,rent_isreturn,rent_return_date FROM rent_record where fk_c_id=?;", carId);
        return rentRecords;
    }

    @Override
    public List<RentRecord> listAllRentRecordByUserId(int userId) {
        List<RentRecord> rentRecords = super.queryForList(RentRecord.class, "SELECT rent_id,rent_date,fk_u_id,fk_c_id,rent_isreturn,rent_return_date FROM rent_record where fk_u_id=?;", userId);
        return rentRecords;
    }

    @Override
    public int deleteRentRecordById(int id) {
        int update = super.update("DELETE FROM rent_record WHERE rent_id=?;", id);
        return update;
    }

    @Override
    public int updateRentRecordById(RentRecord rentRecord) {
        int update = super.update("UPDATE rent_record SET rent_date=?,fk_u_id=?,fk_c_id=?,rent_isreturn=?,rent_return_date=? WHERE rent_id=?;",
                rentRecord.getRent_date(), rentRecord.getFk_u_id(), rentRecord.getFk_c_id(), rentRecord.isRent_isreturn(), rentRecord.getRent_return_date(), rentRecord.getRent_id());
        return  update;
    }
}
