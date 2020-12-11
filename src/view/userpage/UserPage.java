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
    Object name[] = {"���", "����", "�ͺ�", "������", "�۸�", "��ɫ"};
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
        setTitle("�û�����");
        this.username = username;
    }

    void init() {
        label1 = new JLabel("����������Ϣ���ϵͳ");
        buttonOfRentable = new JButton("�����ó���");
        
        buttonOfRentable.addActionListener(this);
        buttonOfAll = new JButton("ȫ���ĳ���");
        buttonOfAll.addActionListener(this);
        buttonOfConfirm1 = new JButton("ȷ��");
        buttonOfConfirm1.addActionListener(this);
        buttonOfConfirm2 = new JButton("ȷ��");
        buttonOfConfirm2.addActionListener(this);
        buttonOfLogout = new JButton("�˳���¼");
        buttonOfLogout.addActionListener(this);
        buttonOfMine = new JButton("�����õĳ�");
        buttonOfMine.addActionListener(this);
        label2 = new JLabel("����������");
        field = new JTextField();
        label3=new JLabel("��������: ");
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
        if (source == buttonOfRentable)
        {
            // ��������ó�����ť
            displayRentable();
        } else if (source == buttonOfAll)
        {
            // ���ȫ����Ϣ��ť
            infoDisplay();
        } else if (source == buttonOfConfirm1)
        {
            //��� ���õ�ȷ����
           rentCar();
        } else if (source == buttonOfLogout) {
            this.dispose();
            new Login();
        } else if (source == buttonOfMine)
        {
            //�������ĳ�����Ϣ
            myRentInfo();
        }else if(source==buttonOfConfirm2){
            // ������ⰴť
            returnCar();
        }
    }

    public void returnCar() {
        if ("".equals(field2.getText()))//�Ƿ�Ϊ��
        {
            JOptionPane.showMessageDialog(null, "���������ó����ı�ţ�");
        } else//��Ϊ��
        {
            String idRegx = "^[0-9]\\d*$";
            if (!field2.getText().matches(idRegx)) {
                JOptionPane.showMessageDialog(null, "����������");
            } else{
                int id=Integer.parseInt(field2.getText());
                if(!carDao.carExist(id)){
                    JOptionPane.showMessageDialog(null, "�����ڸó�");
                }else{
                    Car car = carDao.queryCarById(id);
                    if(!car.isRent()){
                        JOptionPane.showMessageDialog(null, "�ó���δ������");
                    }else if(!this.username.equals(car.getFk_username())){
                        JOptionPane.showMessageDialog(null, "����δ���øó�");
                    }
                    else {
                        double price = carDao.returnCarById(id);
                        JOptionPane.showMessageDialog(null, "����ɹ�,����Ҫ֧��"+price+"Ԫ");
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
            //  {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ"};
            Car c = cars.get(j);
            a[j][0] = c.getId();
            a[j][1] = c.getCartype();
            a[j][2] = c.getCartype();
            a[j][3] = c.isRent() ?"��":"��";
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();

    }
    public void rentCar(){
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
                            field.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "�⳵ʧ��");
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
            JOptionPane.showMessageDialog(null, "���Ѿ������ã�");
            return;
        } else {
            for (int j = 0; j < cars.size(); j++) {
                //  {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ"};
                Car c = cars.get(j);
                a[j][0] = c.getId();
                a[j][1] = c.getCartype();
                a[j][2] = c.getCartype();
                a[j][3] = c.isRent() ?"��":"��";
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
            a[j][3] = c.isRent() ?"��":"��";
            a[j][4] = c.getPrice();
            a[j][5] = c.getColor();
        }
        repaint();
    }
}
