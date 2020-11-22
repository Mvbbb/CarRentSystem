package dao.impl;

import bean.User;
import dao.BaseDao;
import dao.UserDao;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByEmail(String email) {
        User user = super.queryForOne(User.class,"SELECT u_id,u_name,u_email,u_password FROM user_info WHERE u_email=?;",email);
        return user;
    }

    @Override
    public int saveUser(User user) {
        // 如果数据库中存在该用户
        User existUser = queryUserByEmail(user.getU_email());
        if(existUser!=null) return -1;
        
        int update = super.update("INSERT INTO user_info(u_id,u_name,u_email,u_password)VALUES(?,?,?,?);", user.getU_id(), user.getU_name(), user.getU_email(), user.getU_password());
        return update;
    }

    @Override
    public User queryUserById(int id) {
        User user = super.queryForOne(User.class, "SELECT u_id,u_name,u_email,u_password FROM user_info where u_id=?;", id);
        return user;
    }

    @Override
    public List<User> queryUserByUserName(String username) {
        List<User> userList = super.queryForList(User.class, "SELECT u_id,u_name,u_email,u_password FROM user_info where u_name=?;", username);
        return userList;
    }

    @Override
    public int updateUserById(User user) {
        User existUser = queryUserByEmail(user.getU_email());
        if(existUser!=null) return -1;
        
        int update = super.update(" UPDATE user_info SET u_name=?,u_email=?,u_password=? WHERE u_id=?;",user.getU_name(),user.getU_email(),user.getU_password(), user.getU_id());
        return update;
    }

    @Override
    public int deleteUserById(int id) {
        int update = super.update("DELETE FROM user_info WHERE u_id=?;",id);
        return update;
    }

    @Override
    public List<User> queryAllUsers() {
        List<User> userList = super.queryForList(User.class, "SELECT u_id, u_name,u_email ,u_password FROM user_info ;");
        return userList;
    }
}
