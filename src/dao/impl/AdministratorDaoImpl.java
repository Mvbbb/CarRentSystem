package dao.impl;

import bean.Administrator;
import dao.AdministratorDao;
import dao.BaseDao;

public class AdministratorDaoImpl extends BaseDao implements AdministratorDao {
    @Override
    public Administrator queryAdministratorById(int id) {
        Administrator administrator = super.queryForOne(Administrator.class, "SELECT a_id,a_email,a_name,a_password FROM admin_info WHERE a_id=?;", id);
        return administrator;
    }

    @Override
    public Administrator queryAdministratorByEmail(String email) {
        Administrator administrator = super.queryForOne(Administrator.class, "SELECT a_id,a_email,a_name,a_password FROM admin_info WHERE a_email=?;", email);
        return administrator;
    }

    @Override
    public int updateAdministratorById(Administrator administrator) {
        int update = super.update("UPDATE admin_info SET a_email=?,a_name=?,a_password=? WHERE a_id=?;",
                administrator.getA_email(), administrator.getA_name(), administrator.getA_password(), administrator.getA_id());
        return update;
    }
}
