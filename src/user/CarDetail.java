package user;
import utils.JDBCUtil;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * 
 * 这个界面详细信息 用户端
 */
public class CarDetail extends JFrame implements ActionListener{
	
	JTextArea area;
	Box box1,baseBox,box2,baseBox2;
	JButton buttonOfEdit,buttonOfReturn;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String number=null;
	int setEnable =0;
	String information;
	String username = null;
	
	public CarDetail(String number, String username)
	{
		
		init();
		setVisible(true);
		setBounds(500, 200, 620, 360);
		setTitle("用户详细信息界面");
		this.number = number;
		this.username = username;
		setArea();
	}
	
	public  void setArea()//打开界面就把 数据库中的详细信息写入到area中
	{
		con=JDBCUtil.getConnection();
		try {
			stmt = con.createStatement();
			String sql = "select * from car_information where number='"+number+"'; ";
			rs = stmt.executeQuery(sql);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			if(rs.next())
			{
				information = rs.getString("information");
				area.setText(information);
				area.setEnabled(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"没有此编号的车辆的详细信息！");
			}
		} catch (HeadlessException e2) {
			e2.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	void init()
	{
		JLabel label = new JLabel("车辆的详细信息：");
		area = new JTextArea(10, 10);
		buttonOfReturn = new JButton("返回");
		buttonOfReturn.addActionListener(this);
	
		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(8));
		box1.add(label);
		box1.add(area);
		
		box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(8));
		box2.add(buttonOfReturn);
		
		
		baseBox = Box.createHorizontalBox();
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(box1);
		baseBox.add(Box.createHorizontalStrut(10));
		
		baseBox2 = Box.createVerticalBox();
		baseBox2.add(baseBox);
		baseBox2.add(Box.createVerticalStrut(10));
		baseBox2.add(box2);
		baseBox2.add(Box.createVerticalStrut(10));
		
		add(baseBox2);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	 if (source == buttonOfReturn) {
			this.dispose();
			new UserPage(username);
		}
	}
}
