package test;

import bean.Administrator;
import dao.AdministratorDao;
import dao.impl.AdministratorDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministratorDaoImplTest {

    @Test
    public void queryAdministratorById() {
        AdministratorDaoImpl administratorDao = new AdministratorDaoImpl();
        Administrator administrator = administratorDao.queryAdministratorById(1);
        System.out.println(administrator);
    }

    @Test
    public void queryAdministratorByEmail() {
        AdministratorDaoImpl administratorDao = new AdministratorDaoImpl();
        Administrator administrator = administratorDao.queryAdministratorByEmail("2133@qq.ocm");
        System.out.println(administrator);
    }

    @Test
    public void updateAdministratorById() {
        AdministratorDaoImpl administratorDao = new AdministratorDaoImpl();
        Administrator administrator = new Administrator(1, "w2ww@bb.com", "toms", "disd");
        administratorDao.updateAdministratorById(administrator);
        System.out.println(administrator);
    }
}