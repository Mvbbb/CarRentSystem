package user;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import base.*;
import bean.Car;
import dao.CarDao;
import utils.JDBCUtil;

public class UserPage extends JFrame implements ActionListener {

    /*
     *
     * 用户端的界面
     */
    JTable table;
    JLabel label1, label2;
    Object a[][];
    Object name[] = {"编号", "名称", "型号", "是否被租用", "价格", "颜色"};
    JButton buttonOfKe, buttonOfInfoInput, buttonOfConfirm, buttonOfLogout, buttonOfMine;
    Box box1, box2;
    JTextField field, field2;
    JPanel jPanel4, jPanel5;

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String username = null;

    //////
    CarDao carDao = new CarDao();
    //////

    public UserPage(String username) {

        init();
        setVisible(true);
        setBounds(500, 200, 640, 420);
        setTitle("用户界面");
        this.username = username;
    }

    void init() {
        label1 = new JLabel("汽车租赁信息浏览系统");
        buttonOfKe = new JButton("   可 租 用 车 辆   ");
        buttonOfKe.addActionListener(this);
        buttonOfInfoInput = new JButton("   全部的车辆   ");
        buttonOfInfoInput.addActionListener(this);
        buttonOfConfirm = new JButton("    确	            定       ");
        buttonOfConfirm.addActionListener(this);
        buttonOfLogout = new JButton("   退   出   登   录   ");
        buttonOfLogout.addActionListener(this);
        buttonOfMine = new JButton("   我   租   的   车   ");
        buttonOfMine.addActionListener(this);
        label2 = new JLabel("输入需要租用的汽车：");
        field = new JTextField();
        a = new Object[50][6];
        table = new JTable(a, name);//组件的创建
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        box1 = Box.createVerticalBox();
        box1.add(Box.createVerticalStrut(20));
        box1.add(buttonOfKe);
        box1.add(Box.createVerticalStrut(10));
        box1.add(buttonOfInfoInput);
        box1.add(Box.createVerticalStrut(15));
        box1.add(label2);
        box1.add(Box.createVerticalStrut(5));
        box1.add(field);
        box1.add(Box.createVerticalStrut(5));
        box1.add(buttonOfConfirm);
        box1.add(Box.createVerticalStrut(15));

        box1.add(buttonOfMine);
        box1.add(Box.createVerticalStrut(10));
        box1.add(buttonOfLogout);

        box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(10));
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
        if (source == buttonOfKe)//点击可以租用车辆的按钮
        {
            displayRentable();
        } else if (source == buttonOfInfoInput)//点击信息浏览按钮
        {
            infoDisplay();
        } else if (source == buttonOfConfirm)//点击 租用的确定键
        {
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
                            }else{
                                JOptionPane.showMessageDialog(null, "租车失败");
                            }
                        }
                    }
                }
            }
        } else if (source == buttonOfLogout) {
            this.dispose();
            new Login();
        } else if (source == buttonOfMine)//点击我租的车辆信息
        {
            myRentInfo();
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
            a[j][3] = c.isRent();
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();

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
                a[j][3] = c.isRent();
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
            a[j][3] = c.isRent()==true?"是":"否";
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();
    }
}
