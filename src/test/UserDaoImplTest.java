package test;

import bean.User;
import dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void queryUserByEmail() {
        User user = new UserDaoImpl().queryUserByEmail("3212618781@qq.com");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        User user=new User("user1","32q3qwe2@qq.com","userpass1");
        int saveUser = new UserDaoImpl().saveUser(user);
        System.out.println(saveUser);
        User user1 = new UserDaoImpl().queryUserByEmail(user.getU_email());
        System.out.println(user1);
    }

    @Test
    public void queryUserById() {
        User user = new UserDaoImpl().queryUserById(1);
        System.out.println(user);
    }

    @Test
    public void queryUserByUserName() {
        List<User> userList = new UserDaoImpl().queryUserByUserName("user1");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUserById() {
        UserDaoImpl userDao = new UserDaoImpl();
        int i = userDao.updateUserById(new User(1, "te1", "3232w@qq.com", "eiwjrijfe"));
        System.out.println(i);

        User user = userDao.queryUserById(1);
        System.out.println(user);
    }
    
    @Test
    public void deleteUserById(){
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.deleteUserById(2);
        List<User> userList = userDao.queryAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void queryAllUsers(){
        List<User> userList = new UserDaoImpl().queryAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}