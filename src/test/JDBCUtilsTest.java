package test;

import utils.JDBCUtil;

public class JDBCUtilsTest {

    @org.junit.Test
    public void getConnection() {
        System.out.println(JDBCUtil.getConnection());
    }

    @org.junit.Test
    public void closeConection() {
        
    }
}