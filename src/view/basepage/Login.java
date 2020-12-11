package view.basepage;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import utils.SwingUtil;
import view.adminpage.*;
import bean.Admin;
import dao.AdminDao;
import dao.UserDao;
import view.userpage.*;
import utils.JDBCUtil;

/**
 * @author yuzhihai
 */
public class Login extends JFrame implements ActionListener{

	JTextField accountField;
	JPasswordField passwordField;
	JRadioButton userRadioButton,adminRadioButton;
	JButton loginButton,registerButton;
	Box box1,box2,box3,box4,basebBox;
	Connection con = null;
	
	private AdminDao adminDao=new AdminDao();
	private UserDao userDao=new UserDao();
	
	
	public Login()
	{
		setLayout(new FlowLayout());
		init();
		setVisible(true);
		setBounds(500, 200, 500, 500);
		setTitle("��½����");
		JDBCUtil.initDataSource();
	}
	
	void init()
	{
		
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("�˺�:"));
		box1.add(Box.createHorizontalStrut(8));
		accountField = new JTextField(15);
		box1.add(accountField);//��½���� �˺ź�������һ��
		
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("����:"));
		box2.add(Box.createHorizontalStrut(8));
		passwordField = new JPasswordField(15);
		box2.add(passwordField);//��½���������������һ��
		
		box3 = Box.createHorizontalBox();
		ButtonGroup group = new ButtonGroup();
		userRadioButton = new JRadioButton("�û�");
		group.add(userRadioButton);
		userRadioButton.addActionListener(this);
		box3.add(userRadioButton);
		box3.add(Box.createHorizontalStrut(8));
		adminRadioButton = new JRadioButton("����Ա");
		group.add(adminRadioButton);
		adminRadioButton.addActionListener(this);
		box3.add(adminRadioButton);//��½���� ��ѡ��
		
		
		box4 = Box.createHorizontalBox();
		loginButton = new JButton("��½");
		loginButton.addActionListener(this);
		box4.add(loginButton);
		box4.add(Box.createHorizontalStrut(8));
		registerButton = new JButton("ע��");
		registerButton.addActionListener(this);
		box4.add(registerButton);//��½����������ť
		
		
		basebBox = Box.createVerticalBox();
		basebBox.add(Box.createVerticalStrut(50));
		basebBox.add(box1);
		basebBox.add(Box.createVerticalStrut(10));
		basebBox.add(box2);
		basebBox.add(Box.createVerticalStrut(30));
		basebBox.add(box3);
		basebBox.add(Box.createVerticalStrut(80));
		basebBox.add(box4);//��4�����ӷ�һ�������
		
		add(basebBox);
			
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		if (source == loginButton)//���������ǵ�½��ť���ͻ��ж�radiobuttonѡ�����ʲô��������Ӧ����Ӧ
		{
			if(!userRadioButton.isSelected()&&!adminRadioButton.isSelected())//radiobuttonûѡ��
			{
				JOptionPane.showMessageDialog(null, "��ѡ����ݣ�");
			}
			else if ("".equals(accountField.getText()) || "".equals(passwordField.getText()))
			{// �ж��Ƿ��������û���������
				JOptionPane.showMessageDialog(null, "��¼�������벻��Ϊ�գ�");
			} 
			else 
			{
				if(adminRadioButton.isSelected())//���ѡ����ǹ���Ա�İ�ť
				{
				 try {
					 boolean loginSuccess = adminDao.isLoginInfoRight(new Admin(accountField.getText(), passwordField.getText()));
					 if(loginSuccess){
						 this.dispose();
						 new AdminPage();
					 }else{
						 JOptionPane.showMessageDialog(null, "�������");
						 passwordField.setText("");
					 }						 
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} 
				}
				else //ѡ�����user��ť
				{
					try {
						boolean userExists = userDao.isUserExists(accountField.getText());
						if(userExists){
							boolean userLoginSuccess = userDao.isUserLoginSuccess(accountField.getText(), passwordField.getText());
							if(userLoginSuccess){
								this.dispose();
								new UserPage(accountField.getText());
							}else{
								JOptionPane.showMessageDialog(null, "�������");
								passwordField.setText("");
							}
						}else{
							JOptionPane.showMessageDialog(null, "�����ڴ��˺ţ���ע�ᣡ");
							accountField.setText("");
							passwordField.setText("");
						}
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						}
				}
		  }
		}
		else if(source == registerButton)
		{
			this.dispose();
			new Register();
		}
	}
}