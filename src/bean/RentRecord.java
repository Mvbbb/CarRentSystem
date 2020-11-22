package bean;

import java.sql.Date;

public class RentRecord {
    private int rent_id;
    private Date rent_date;
    private int fk_u_id;
    private int fk_c_id;
    private boolean rent_isreturn;
    private Date rent_return_date;

    public RentRecord() {
    }

    public RentRecord(Date rent_date, int fk_u_id, int fk_c_id, boolean rent_isreturn, Date rent_return_date) {
        this.rent_date = rent_date;
        this.fk_u_id = fk_u_id;
        this.fk_c_id = fk_c_id;
        this.rent_isreturn = rent_isreturn;
        this.rent_return_date = rent_return_date;
    }
    

    public RentRecord(int rent_id, Date rent_date, int fk_u_id, int fk_c_id, boolean rent_isreturn, Date rent_return_date) {
        this.rent_id = rent_id;
        this.rent_date = rent_date;
        this.fk_u_id = fk_u_id;
        this.fk_c_id = fk_c_id;
        this.rent_isreturn = rent_isreturn;
        this.rent_return_date = rent_return_date;
    }

    public int getRent_id() {
        return rent_id;
    }

    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }

    public Date getRent_date() {
        return rent_date;
    }

    public void setRent_date(Date rent_date) {
        this.rent_date = rent_date;
    }

    public int getFk_u_id() {
        return fk_u_id;
    }

    public void setFk_u_id(int fk_u_id) {
        this.fk_u_id = fk_u_id;
    }

    public int getFk_c_id() {
        return fk_c_id;
    }

    public void setFk_c_id(int fk_c_id) {
        this.fk_c_id = fk_c_id;
    }

    public boolean isRent_isreturn() {
        return rent_isreturn;
    }

    public void setRent_isreturn(boolean rent_isreturn) {
        this.rent_isreturn = rent_isreturn;
    }

    public Date getRent_return_date() {
        return rent_return_date;
    }

    public void setRent_return_date(Date rent_return_date) {
        this.rent_return_date = rent_return_date;
    }

    @Override
    public String toString() {
        return "RentRecord{" +
                "rent_id=" + rent_id +
                ", rent_date=" + rent_date +
                ", fk_u_id=" + fk_u_id +
                ", fk_c_id=" + fk_c_id +
                ", rent_isreturn=" + rent_isreturn +
                ", rent_return_date=" + rent_return_date +
                '}';
    }
}
