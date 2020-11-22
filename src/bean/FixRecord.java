package bean;

import java.sql.Date;

public class FixRecord {
    private int f_id;
    private Date f_date;
    private String f_problem;
    private int fk_c_id;
    private int fk_u_id;
    private double f_price;

    public FixRecord() {
    }

    public FixRecord(Date f_date, String f_problem, int fk_c_id, int fk_u_id, double f_price) {
        this.f_date = f_date;
        this.f_problem = f_problem;
        this.fk_c_id = fk_c_id;
        this.fk_u_id = fk_u_id;
        this.f_price = f_price;
    }

    public FixRecord(int f_id, Date f_date, String f_problem, int fk_c_id, int fk_u_id, double f_price) {
        this.f_id = f_id;
        this.f_date = f_date;
        this.f_problem = f_problem;
        this.fk_c_id = fk_c_id;
        this.fk_u_id = fk_u_id;
        this.f_price = f_price;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public Date getF_date() {
        return f_date;
    }

    public void setF_date(Date f_date) {
        this.f_date = f_date;
    }

    public String getF_problem() {
        return f_problem;
    }

    public void setF_problem(String f_problem) {
        this.f_problem = f_problem;
    }

    public int getFk_c_id() {
        return fk_c_id;
    }

    public void setFk_c_id(int fk_c_id) {
        this.fk_c_id = fk_c_id;
    }

    public int getFk_u_id() {
        return fk_u_id;
    }

    public void setFk_u_id(int fk_u_id) {
        this.fk_u_id = fk_u_id;
    }

    public double getF_price() {
        return f_price;
    }

    public void setF_price(double f_price) {
        this.f_price = f_price;
    }

    @Override
    public String toString() {
        return "FixRecord{" +
                "f_id=" + f_id +
                ", f_date=" + f_date +
                ", f_problem='" + f_problem + '\'' +
                ", fk_c_id=" + fk_c_id +
                ", fk_u_id=" + fk_u_id +
                ", f_price=" + f_price +
                '}';
    }
}
