package view.userpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.basepage.*;
import bean.Car;
import dao.CarDao;

/**
 * @author yuzhihai
 */
public class UserPage extends JFrame implements ActionListener {

    JTable table;
    JLabel label1, label2,label3;
    Object a[][];
    Object name[] = {"编号", "名称", "型号", "被租用", "价格", "颜色"};
    JButton buttonOfRentable, buttonOfAll, buttonOfConfirm1,buttonOfConfirm2, buttonOfLogout, buttonOfMine;
    Box box1, box2;
    JTextField field,field2;
    JPanel jPanel4, jPanel5;
    String username = null;

    CarDao carDao = new CarDao();

    public UserPage(String username) {

        init();
        setVisible(true);
        setBounds(500, 200, 670, 500);
        setTitle("用户界面");
        this.username = username;
    }

    void init() {
        label1 = new JLabel("汽车租赁信息浏览系统");
        buttonOfRentable = new JButton("可租用车辆");
        
        buttonOfRentable.addActionListener(this);
        buttonOfAll = new JButton("全部的车辆");
        buttonOfAll.addActionListener(this);
        buttonOfConfirm1 = new JButton("确定");
        buttonOfConfirm1.addActionListener(this);
        buttonOfConfirm2 = new JButton("确定");
        buttonOfConfirm2.addActionListener(this);
        buttonOfLogout = new JButton("退出登录");
        buttonOfLogout.addActionListener(this);
        buttonOfMine = new JButton("我租用的车");
        buttonOfMine.addActionListener(this);
        label2 = new JLabel("租用哪辆：");
        field = new JTextField();
        label3=new JLabel("退租哪辆: ");
        field2=new JTextField();
        a = new Object[50][6];
        table = new JTable(a, name);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(20));
        box1.add(buttonOfRentable);
        box1.add(Box.createVerticalStrut(10));
        box1.add(buttonOfAll);
        box1.add(Box.createVerticalStrut(15));
        box1.add(buttonOfMine);
        box1.add(Box.createVerticalStrut(10));
        
        box1.add(label2);
        box1.add(Box.createVerticalStrut(5));
        box1.add(field);
        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOfConfirm1);
        box1.add(Box.createVerticalStrut(15));
        
        box1.add(label3);
        box1.add(Box.createVerticalStrut(5));
        box1.add(field2);
        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOfConfirm2);
        box1.add(Box.createVerticalStrut(15));

        
        box1.add(buttonOfLogout);

        box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(20));
        box2.add(box1);   //左边的按钮部分用 box布局

        jPanel4 = new JPanel();
        jPanel5 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(box2, BorderLayout.NORTH);//把左边的按钮部分放到jpanel4中。

        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(label1, BorderLayout.NORTH);
        jPanel5.add(scrollPane, BorderLayout.CENTER);//把表格 放jpanel5里

        this.setLayout(new BorderLayout());
        add(jPanel5, BorderLayout.EAST);
        add(jPanel4, BorderLayout.WEST);//把两个大的panel放到窗口里面
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonOfRentable)
        {
            // 点击可租用车辆按钮
            displayRentable();
        } else if (source == buttonOfAll)
        {
            // 点击全部信息按钮
            infoDisplay();
        } else if (source == buttonOfConfirm1)
        {
            //点击 租用的确定键
           rentCar();
        } else if (source == buttonOfLogout) {
            this.dispose();
            new Login();
        } else if (source == buttonOfMine)
        {
            //点击我租的车辆信息
            myRentInfo();
        }else if(source==buttonOfConfirm2){
            // 点击退租按钮
            returnCar();
        }
    }

    public void returnCar() {
        if ("".equals(field2.getText()))//是否为空
        {
            JOptionPane.showMessageDialog(null, "请输入租用车辆的编号！");
        } else//不为空
        {
            String idRegx = "^[0-9]\\d*$";
            if (!field2.getText().matches(idRegx)) {
                JOptionPane.showMessageDialog(null, "请输入数字");
            } else{
                int id=Integer.parseInt(field2.getText());
                if(!carDao.carExist(id)){
                    JOptionPane.showMessageDialog(null, "不存在该车");
                }else{
                    Car car = carDao.queryCarById(id);
                    if(!car.isRent()){
                        JOptionPane.showMessageDialog(null, "该车还未被租用");
                    }else if(!this.username.equals(car.getFk_username())){
                        JOptionPane.showMessageDialog(null, "您还未租用该车");
                    }
                    else {
                        double price = carDao.returnCarById(id);
                        JOptionPane.showMessageDialog(null, "退租成功,您需要支付"+price+"元");
                        field2.setText("");
                    }
                }
            }
        }
    }

    public void myRentInfo() {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = "";
            }
        }
        List<Car> cars = carDao.userRentInfo(username);
        for (int j = 0; j < cars.size(); j++) {
            //  {"编号","名称","型号","是否被租用","价格","颜色"};
            Car c = cars.get(j);
            a[j][0] = c.getId();
            a[j][1] = c.getCartype();
            a[j][2] = c.getCartype();
            a[j][3] = c.isRent() ?"是":"否";
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();

    }
    public void rentCar(){
        if ("".equals(field.getText()))//是否为空
        {
            JOptionPane.showMessageDialog(null, "请输入租用车辆的编号！");
        } else//不为空
        {
            String idRegx = "^[0-9]\\d*$";
            if (!field.getText().matches(idRegx)) {
                JOptionPane.showMessageDialog(null, "请输入数字");
            } else{
                int id=Integer.parseInt(field.getText());
                if(!carDao.carExist(id)){
                    JOptionPane.showMessageDialog(null, "不存在该车");
                }else{
                    Car car = carDao.queryCarById(id);
                    if(car.isRent()){
                        JOptionPane.showMessageDialog(null, "该车已经被租用");
                    }else{
                        boolean rentSucc= carDao.rentCarById(id,username);
                        if(rentSucc==true){
                            JOptionPane.showMessageDialog(null, "成功租赁");
                            field.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "租车失败");
                        }
                    }
                }
            }
        }
    }
    public void displayRentable() {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = "";
            }
        }
        List<Car> cars = carDao.listAllCarRentable();
        if (cars.size() == 0) {
            JOptionPane.showMessageDialog(null, "都已经被租用！");
            return;
        } else {
            for (int j = 0; j < cars.size(); j++) {
                //  {"编号","名称","型号","是否被租用","价格","颜色"};
                Car c = cars.get(j);
                a[j][0] = c.getId();
                a[j][1] = c.getCartype();
                a[j][2] = c.getCartype();
                a[j][3] = c.isRent() ?"是":"否";
                a[j][4] = c.getPrice();
                a[j][5] = c.getColor();
            }
            repaint();

        }
    }

    public void infoDisplay() {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = "";
            }
        }
        List<Car> cars = carDao.listAllCar();
        for (int j = 0; j < cars.size(); j++) {
            //  {"编号","名称","型号","是否被租用","价格","颜色"};
            Car c = cars.get(j);
            a[j][0] = c.getId();
            a[j][1] = c.getCarname();
            a[j][2] = c.getCartype();
            a[j][3] = c.isRent() ?"是":"否";
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();
    }
}
