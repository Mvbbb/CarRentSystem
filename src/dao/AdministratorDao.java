package dao;

import bean.Administrator;


public interface AdministratorDao {
    /**
     * 按照管理员编号查找
     * @return
     */
    Administrator queryAdministratorById(int id);
    
    /**
     * 按照管理员邮箱查找
     * @return
     */
    Administrator queryAdministratorByEmail(String email);

    /**
     * 按照管理员编号修改管理员信息
     * @return
     */
    int updateAdministratorById(Administrator administrator);
}
