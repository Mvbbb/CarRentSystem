package test;

import bean.FixRecord;
import dao.impl.FixRecordDaoImpl;
import org.junit.Test;

import java.sql.Date;
import java.util.List;


public class FixRecordDaoImplTest {

    @Test
    public void addFixRecord() {
        FixRecord fixRecord = new FixRecord(new Date(System.currentTimeMillis()), "发动非障", 4, 3, 123);
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        fixRecordDao.addFixRecord(fixRecord);
        List<FixRecord> fixRecords = fixRecordDao.listAllFixRecord();
        for (FixRecord f : fixRecords) {
            System.out.println(f);
        }
    }

    @Test
    public void updateFixRecordById() {
        FixRecord fixRecord = new FixRecord(123,new Date(System.currentTimeMillis()), "发fefeifei", 4, 3, 93);
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        fixRecordDao.updateFixRecordById(fixRecord);
        List<FixRecord> fixRecords = fixRecordDao.listAllFixRecord();
        for (FixRecord f : fixRecords) {
            System.out.println(f);
        }
    }

    @Test
    public void deleteFixRecordById() {
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        fixRecordDao.deleteFixRecordById(17);
        List<FixRecord> fixRecords = fixRecordDao.listAllFixRecord();
        for (FixRecord f : fixRecords) {
            System.out.println(f);
        }
    }

    @Test
    public void listAllFixRecord() {
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        List<FixRecord> fixRecords = fixRecordDao.listAllFixRecord();
        for (FixRecord f : fixRecords) {
            System.out.println(f);
        }
    }

    @Test
    public void queryFixRecordByCarId() {
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        List<FixRecord> fixRecords = fixRecordDao.queryFixRecordByCarId(4);
        for (FixRecord f : fixRecords) {
            System.out.println(f);
        }
    }

    @Test
    public void queryFixRecordByUserId() {
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        List<FixRecord> fixRecords = fixRecordDao.queryFixRecordByUserId(3);
        for (FixRecord f : fixRecords) {
            System.out.println(f);
        }
    }

    @Test
    public void queryFixRecordById() {
        FixRecordDaoImpl fixRecordDao = new FixRecordDaoImpl();
        FixRecord fixRecord = fixRecordDao.queryFixRecordById(13);
        System.out.println(fixRecord);
    }
}