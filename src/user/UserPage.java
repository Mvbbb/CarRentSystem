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
     * �û��˵Ľ���
     */
    JTable table;
    JLabel label1, label2;
    Object a[][];
    Object name[] = {"���", "����", "�ͺ�", "�Ƿ�����", "�۸�", "��ɫ"};
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
        setTitle("�û�����");
        this.username = username;
    }

    void init() {
        label1 = new JLabel("����������Ϣ���ϵͳ");
        buttonOfKe = new JButton("   �� �� �� �� ��   ");
        buttonOfKe.addActionListener(this);
        buttonOfInfoInput = new JButton("   ȫ���ĳ���   ");
        buttonOfInfoInput.addActionListener(this);
        buttonOfConfirm = new JButton("    ȷ	            ��       ");
        buttonOfConfirm.addActionListener(this);
        buttonOfLogout = new JButton("   ��   ��   ��   ¼   ");
        buttonOfLogout.addActionListener(this);
        buttonOfMine = new JButton("   ��   ��   ��   ��   ");
        buttonOfMine.addActionListener(this);
        label2 = new JLabel("������Ҫ���õ�������");
        field = new JTextField();
        a = new Object[50][6];
        table = new JTable(a, name);//����Ĵ���
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
        box2.add(box1);   //��ߵİ�ť������ box����

        jPanel4 = new JPanel();
        jPanel5 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(box2, BorderLayout.NORTH);//����ߵİ�ť���ַŵ�jpanel4�С�

        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(label1, BorderLayout.NORTH);
        jPanel5.add(scrollPane, BorderLayout.CENTER);//�ѱ�� ��jpanel5��

        this.setLayout(new BorderLayout());
        add(jPanel5, BorderLayout.EAST);
        add(jPanel4, BorderLayout.WEST);//���������panel�ŵ���������
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonOfKe)//����������ó����İ�ť
        {
            displayRentable();
        } else if (source == buttonOfInfoInput)//�����Ϣ�����ť
        {
            infoDisplay();
        } else if (source == buttonOfConfirm)//��� ���õ�ȷ����
        {
            if ("".equals(field.getText()))//�Ƿ�Ϊ��
            {
                JOptionPane.showMessageDialog(null, "���������ó����ı�ţ�");
            } else//��Ϊ��
            {
                String idRegx = "^[0-9]\\d*$";
                if (!field.getText().matches(idRegx)) {
                    JOptionPane.showMessageDialog(null, "����������");
                } else{
                    int id=Integer.parseInt(field.getText());
                    if(!carDao.carExist(id)){
                        JOptionPane.showMessageDialog(null, "�����ڸó�");
                    }else{
                        Car car = carDao.queryCarById(id);
                        if(car.isRent()){
                            JOptionPane.showMessageDialog(null, "�ó��Ѿ�������");
                        }else{
                            boolean rentSucc= carDao.rentCarById(id,username);
                            if(rentSucc==true){
                                JOptionPane.showMessageDialog(null, "�ɹ�����");
                            }else{
                                JOptionPane.showMessageDialog(null, "�⳵ʧ��");
                            }
                        }
                    }
                }
            }
        } else if (source == buttonOfLogout) {
            this.dispose();
            new Login();
        } else if (source == buttonOfMine)//�������ĳ�����Ϣ
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
            //  {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ"};
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
            JOptionPane.showMessageDialog(null, "���Ѿ������ã�");
            return;
        } else {
            for (int j = 0; j < cars.size(); j++) {
                //  {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ"};
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
            //  {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ"};
            Car c = cars.get(j);
            a[j][0] = c.getId();
            a[j][1] = c.getCarname();
            a[j][2] = c.getCartype();
            a[j][3] = c.isRent()==true?"��":"��";
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();
    }
}
