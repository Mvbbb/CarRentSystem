package test;

import bean.RentRecord;
import dao.impl.RentRecordDaoImpl;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class RentRecordDaoImplTest {

    @Test
    public void addRentRecord() {
        RentRecordDaoImpl rentRecordDao = new RentRecordDaoImpl();
        int rentRecord = rentRecordDao.addRentRecord(new RentRecord(new Date(System.currentTimeMillis()), 5, 2, true, new Date(System.currentTimeMillis())));
        System.out.println(rentRecord);
        List<RentRecord> rentRecordList = rentRecordDao.listAllRentRecord();
        for (RentRecord record : rentRecordList) {
            System.out.println(record);
        }
    }

    @Test
    public void listAllRentRecord() {
        RentRecordDaoImpl rentRecordDao = new RentRecordDaoImpl();
        List<RentRecord> rentRecordList = rentRecordDao.listAllRentRecord();
        for (RentRecord record : rentRecordList) {
            System.out.println(record);
        }
    }

    @Test
    public void listAllRentRecordByCarId() {
        RentRecordDaoImpl rentRecordDao = new RentRecordDaoImpl();
        List<RentRecord> rentRecordList = rentRecordDao.listAllRentRecordByCarId(1);
        for (RentRecord record : rentRecordList) {
            System.out.println(record);
        }
    }

    @Test
    public void listAllRentRecordByUserId() {
        RentRecordDaoImpl rentRecordDao = new RentRecordDaoImpl();
        List<RentRecord> rentRecordList = rentRecordDao.listAllRentRecordByUserId(3);
        for (RentRecord record : rentRecordList) {
            System.out.println(record);
        }
    }

    @Test
    public void deleteRentRecordById() {
        RentRecordDaoImpl rentRecordDao = new RentRecordDaoImpl();
        int i = rentRecordDao.deleteRentRecordById(3);
        System.out.println(i);
        List<RentRecord> rentRecords = rentRecordDao.listAllRentRecord();
        for (RentRecord rentRecord : rentRecords) {
            System.out.println(rentRecord);
        }
    }

    @Test
    public void updateRentRecordById() {
        RentRecordDaoImpl rentRecordDao = new RentRecordDaoImpl();
        int i = rentRecordDao.updateRentRecordById(new RentRecord(2,new Date(System.currentTimeMillis()-219019210), 3, 2, true, new Date(System.currentTimeMillis()-999999)));
        System.out.println(i);
        List<RentRecord> rentRecords = rentRecordDao.listAllRentRecord();
        for (RentRecord rentRecord : rentRecords) {
            System.out.println(rentRecord);
        }
    }
}