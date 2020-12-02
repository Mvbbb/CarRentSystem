package bean;

public class Admin {
    private String adminName;
    private String admin_password;

    public Admin() {
    }

    public Admin(String adminName, String admin_password) {
        this.adminName = adminName;
        this.admin_password = admin_password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }
}
