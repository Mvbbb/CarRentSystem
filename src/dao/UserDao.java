package dao;

import bean.User;

import java.util.List;

/**
 * 维护用户信息管理
 */
public  interface UserDao {
    
    /**
     * 按照邮箱查找用户
     * @param email
     * @return
     */
    User queryUserByEmail(String email);

    /**
     * 保存一个用户到数据库
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 按照ID查找用户
     * @param id
     * @return
     */
    User queryUserById(int id);

    /**
     * 按照用户名查找用户集
     * @param username
     * @return
     */
    List<User> queryUserByUserName(String username);

    /**
     * 按照传入User的id更新数据库中的该User
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 被删除的用户的ID
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * 返回所有的用户
     * @return
     */
    List<User> queryAllUsers();
}
