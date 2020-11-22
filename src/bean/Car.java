package bean;

public class Car {
    private int c_id;
    private String c_name;
    private String c_color;
    private String c_type;
    private double c_price;

    public Car() {
    }

    public Car(String c_name, String c_color, String c_type, double c_price) {
        this.c_name = c_name;
        this.c_color = c_color;
        this.c_type = c_type;
        this.c_price = c_price;
    }

    public Car(int c_id, String c_name, String c_color, String c_type, double c_price) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_color = c_color;
        this.c_type = c_type;
        this.c_price = c_price;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_color() {
        return c_color;
    }

    public void setC_color(String c_color) {
        this.c_color = c_color;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public double getC_price() {
        return c_price;
    }

    public void setC_price(double c_price) {
        this.c_price = c_price;
    }
    
    

    @Override
    public String toString() {
        return "Car{" +
                "c_id=" + c_id +
                ", c_name='" + c_name + '\'' +
                ", c_color='" + c_color + '\'' +
                ", c_type='" + c_type + '\'' +
                ", c_price=" + c_price +
                '}';
    }
}
