package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 工具类, 用于获取数据库连接管理
 * @author yuzhihai
 */
public class JDBCUtil {
    private static DataSource dataSource=null;

    static{
        Properties props = new Properties();
        InputStream is = null;
        try {
            is=JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");;
            props.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用于提前初始化数据库连接, 防止用户登录时间过长
     */
    public static void initDataSource(){
        closeConection(getConnection());
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn= dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    
    public static void closeConection(Connection conn){
        try{
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}