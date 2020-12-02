package dao;

import bean.User;

public class UserDao extends BaseDao{
    public boolean isUserExists(String username){
        User user = super.queryForOne(User.class, "select * from user where username=?", username);
        return user!=null;
    }
    public boolean isUserLoginSuccess(String username,String password){
        User user = super.queryForOne(User.class, "select * from user where username=? and user_password=?", username, password);
        return user!=null;
    }

    public boolean addUser(String username, String password) {
        int update = super.update("insert into user(username,user_password)values(?,?)", username, password);
        return update==1;
    }
}
