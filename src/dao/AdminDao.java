package dao;

import bean.Admin;

/**
 * 用于Admin表的操作
 * @author yuzhihai
 */
public class AdminDao extends BaseDao {
    public boolean isLoginInfoRight(Admin admin){
        Admin a = super.queryForOne(Admin.class, "select * from admin where adminname = ? and admin_password=?", admin.getAdminName(), admin.getAdmin_password());
        return a!=null;
    }
}
