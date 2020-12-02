
package admin;
import bean.Car;
import dao.CarDao;
import utils.JDBCUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/*
 * 信息录入界面
 */

public class Update extends JFrame implements ActionListener {

	JTextField field1,field2,field3,field4,field5,field6;

	Box box1,box2,box3,box4,box5,box6,box7,baseBox;
	JButton buttonOfConfirm,buttonOfReset,buttonOfCancel;
	CarDao carDao=new CarDao();
	String number;
	public Update(String number)
	{
		
		init();
		setVisible(true);
		setBounds(550, 200, 550, 280);
		setTitle("车辆信息修改界面 编号为:"+number);
		this.number = number;
		setText();
	}

	private void setText() {
		Car car = carDao.queryCarById(Integer.parseInt(number));
		field1.setText(car.getCarname());
		field2.setText(car.getCartype());
		field3.setText(Double.toString(car.getPrice()));
		field4.setText(car.getColor());
		field5.setText(car.isRent()?"是":"否");
		field6.setText(car.getFk_username());
	}

	void init()
	{
		JLabel label1 = new JLabel(" 名        称  : ");
		JLabel label2 = new JLabel(" 车        型  : ");
		JLabel label3 = new JLabel(" 租        金  : ");
		JLabel label4 = new JLabel(" 颜        色  : ");
		JLabel label5 = new JLabel("已租用(是/否):");
		JLabel label6 = new JLabel(" 租户(假如已经租用)  : ");


		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field4 = new JTextField();
		field5 = new JTextField();
		field6 = new JTextField();

		buttonOfConfirm = new JButton("提交");
		buttonOfConfirm.addActionListener(this);
		buttonOfReset = new JButton("重置");
		buttonOfReset.addActionListener(this);
		buttonOfCancel = new JButton("取消");
		buttonOfCancel.addActionListener(this);

		box1 = Box.createHorizontalBox();
		box1.add(Box.createHorizontalStrut(8));
		box1.add(label1);
		box1.add(Box.createHorizontalStrut(8));
		box1.add(field1);
		box1.add(Box.createHorizontalStrut(8));

		box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(8));
		box2.add(label2);
		box2.add(Box.createHorizontalStrut(8));
		box2.add(field2);
		box2.add(Box.createHorizontalStrut(8));

		box3 = Box.createHorizontalBox();
		box3.add(Box.createHorizontalStrut(8));
		box3.add(label3);
		box3.add(Box.createHorizontalStrut(8));
		box3.add(field3);
		box3.add(Box.createHorizontalStrut(8));

		box4 = Box.createHorizontalBox();
		box4.add(Box.createHorizontalStrut(8));
		box4.add(label4);
		box4.add(Box.createHorizontalStrut(8));
		box4.add(field4);
		box4.add(Box.createHorizontalStrut(8));

		box5 = Box.createHorizontalBox();
		box5.add(Box.createHorizontalStrut(8));
		box5.add(label5);
		box5.add(Box.createHorizontalStrut(8));
		box5.add(field5);
		box5.add(Box.createHorizontalStrut(8));

		box6 = Box.createHorizontalBox();
		box6.add(Box.createHorizontalStrut(8));
		box6.add(label6);
		box6.add(Box.createHorizontalStrut(8));
		box6.add(field6);
		box6.add(Box.createHorizontalStrut(8));

		box7 = Box.createHorizontalBox();
		box7.add(Box.createHorizontalStrut(8));
		box7.add(buttonOfConfirm);
		box7.add(Box.createHorizontalStrut(8));
		box7.add(buttonOfCancel);
		box7.add(Box.createHorizontalStrut(8));
		box7.add(buttonOfReset);
		box7.add(Box.createHorizontalStrut(8));

		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(15));
		baseBox.add(box1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box3);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box4);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box5);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box6);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box7);
		baseBox.add(Box.createVerticalStrut(15));
		add(baseBox);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		String carname=field1.getText();
		String cartype = field2.getText() ;
		String price=field3.getText();
		String color=field4.getText();
		String rent=field5.getText();
		String fk_username=field6.getText();

		if(source == buttonOfConfirm)
		{
			if("".equals(carname)||"".equals(cartype)||"".equals(price)||"".equals(color)||"".equals(rent))
			{
				JOptionPane.showMessageDialog(null, "请填写完整！");
			} else {
				boolean isrent = "是".equals(rent);
				String usernameToAdd= isrent&&!"".equals(fk_username)?fk_username:null;
				boolean isAddSuccess = carDao.updateCarById(new Car(Integer.parseInt(number),carname, cartype,isrent , Double.parseDouble(price), color, usernameToAdd));
				if(isAddSuccess){
					JOptionPane.showMessageDialog(null, "修改成功！");
					this.dispose();
					new AdminPage();
				}else{
					JOptionPane.showMessageDialog(null, "修改失败, 请检查该租户是否存在");
				}
			}
		}
		else if(source == buttonOfCancel)
		{
			this.dispose();
			new AdminPage();
		}
		else if(source == buttonOfReset)
		{
			field1.setText("");
			field2.setText("");
			field3.setText("");
			field5.setText("");
			field6.setText("");
		}
	}
}
