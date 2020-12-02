package base;
import java.awt.FlowLayout;
import java.sql.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import admin.*;
import bean.Admin;
import dao.AdminDao;
import dao.UserDao;
import user.*;
import utils.JDBCUtil;

public class Login extends JFrame implements ActionListener{

	JTextField accountField;
	JPasswordField passwordField;
	JRadioButton userRadioButton,adminRadioButton;
	JButton loginButton,registerButton;
	Box box1,box2,box3,box4,basebBox;//账号，密码，两个radiobutton，两个按钮都是用行式盒子布局。 basebox用列式把他们包裹起来。
	Connection con = null;
	
	private AdminDao adminDao=new AdminDao();
	private UserDao userDao=new UserDao();
	
	public Login()
	{
		setLayout(new FlowLayout());
		init();
		setVisible(true);
		setBounds(500, 200, 500, 500);
		setTitle("登陆界面");
		JDBCUtil.initDataSource();
	}
	
	void init()
	{
		
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("账号:"));
		box1.add(Box.createHorizontalStrut(8));
		accountField = new JTextField(15);
		box1.add(accountField);//登陆界面 账号和输入框的一行
		
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("密码:"));
		box2.add(Box.createHorizontalStrut(8));
		passwordField = new JPasswordField(15);
		box2.add(passwordField);//登陆界面密码和输入框的一行
		
		box3 = Box.createHorizontalBox();
		ButtonGroup group = new ButtonGroup();
		userRadioButton = new JRadioButton("用户");
		group.add(userRadioButton);
		userRadioButton.addActionListener(this);
		box3.add(userRadioButton);
		box3.add(Box.createHorizontalStrut(8));
		adminRadioButton = new JRadioButton("管理员");
		group.add(adminRadioButton);
		adminRadioButton.addActionListener(this);
		box3.add(adminRadioButton);//登陆界面 单选框
		
		
		box4 = Box.createHorizontalBox();
		loginButton = new JButton("登陆");
		loginButton.addActionListener(this);
		box4.add(loginButton);
		box4.add(Box.createHorizontalStrut(8));
		registerButton = new JButton("注册");
		registerButton.addActionListener(this);
		box4.add(registerButton);//登陆界面两个按钮
		
		
		basebBox = Box.createVerticalBox();
		basebBox.add(Box.createVerticalStrut(50));
		basebBox.add(box1);
		basebBox.add(Box.createVerticalStrut(10));
		basebBox.add(box2);
		basebBox.add(Box.createVerticalStrut(30));
		basebBox.add(box3);
		basebBox.add(Box.createVerticalStrut(80));
		basebBox.add(box4);//把4个盒子放一个大盒子
		
		add(basebBox);
			
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		if (source == loginButton)//如果点击的是登陆按钮，就会判断radiobutton选择的是什么，做出相应的响应
		{
			if(!userRadioButton.isSelected()&&!adminRadioButton.isSelected())//radiobutton没选择
			{
				JOptionPane.showMessageDialog(null, "请选择身份！");
			}
			else if ("".equals(accountField.getText()) || "".equals(passwordField.getText()))
			{// 判断是否输入了用户名和密码
				JOptionPane.showMessageDialog(null, "登录名和密码不能为空！");
			} 
			else 
			{
				con= JDBCUtil.getConnection();
				if(adminRadioButton.isSelected())//如果选择的是管理员的按钮
				{
				 try {
					 boolean loginSuccess = adminDao.isLoginInfoRight(new Admin(accountField.getText(), passwordField.getText()));
					 if(loginSuccess){
						 this.dispose();
						 new AdminPage();
					 }else{
						 JOptionPane.showMessageDialog(null, "密码错误！");
						 passwordField.setText("");
					 }						 
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} 
				}
				else //选择的是user按钮
				{
					try {
						boolean userExists = userDao.isUserExists(accountField.getText());
						if(userExists){
							boolean userLoginSuccess = userDao.isUserLoginSuccess(accountField.getText(), passwordField.getText());
							if(userLoginSuccess){
								this.dispose();
								new UserPage(accountField.getText());
							}else{
								JOptionPane.showMessageDialog(null, "密码错误！");
								passwordField.setText("");
							}
						}else{
							JOptionPane.showMessageDialog(null, "不存在此账号，请注册！");
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