package view.adminpage;
import bean.Car;
import dao.CarDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * @author yuzhihai
 */
public class AddCarPage extends JFrame implements ActionListener {

	JTextField field1,field2,field3,field5,field6,field7,field8;
	Box box1,box2,box3,box5,box6,box7,baseBox,box8,box9;
	JButton buttonofConfirm,buttonOfReset,buttonofCancel;
	CarDao carDao=new CarDao();
	
	public AddCarPage()
	{
		init();
		setVisible(true);
		setBounds(550, 200, 550, 420);
		setTitle("������Ϣ¼�����");
	}

	void init() {
		
		JLabel label1 = new JLabel(" ��        ��  : ");
		JLabel label2 = new JLabel(" ��        ��  : ");
		JLabel label3 = new JLabel(" ��        ��  : ");
		JLabel label5 = new JLabel(" ��        ɫ  : ");
		JLabel label6 = new JLabel(" ������(��/��)  : ");
		JLabel label7 = new JLabel(" �⻧(������)   : ");
		JLabel label8 = new JLabel(" ��������(������): ");
		
		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field5 = new JTextField();
		field6 = new JTextField();
		field7 = new JTextField();
		field8 = new JTextField();

		buttonofConfirm = new JButton("�ύ");
		buttonOfReset = new JButton("����");
		buttonofCancel = new JButton("ȡ��");
		buttonofConfirm.addActionListener(this);
		buttonofCancel.addActionListener(this);
		buttonOfReset.addActionListener(this);

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

		box8 = Box.createHorizontalBox();
		box8.add(Box.createHorizontalStrut(8));
		box8.add(label7);
		box8.add(Box.createHorizontalStrut(8));
		box8.add(field7);
		box8.add(Box.createHorizontalStrut(8));

		box9 = Box.createHorizontalBox();
		box9.add(Box.createHorizontalStrut(8));
		box9.add(label8);
		box9.add(Box.createHorizontalStrut(8));
		box9.add(field8);
		box9.add(Box.createHorizontalStrut(8));

		box7 = Box.createHorizontalBox();
		box7.add(Box.createHorizontalStrut(8));
		box7.add(buttonofConfirm);
		box7.add(Box.createHorizontalStrut(8));
		box7.add(buttonofCancel);
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
		baseBox.add(box5);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box6);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box8);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box9);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box7);
		baseBox.add(Box.createVerticalStrut(15));

		add(baseBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		//1����,2����,3���,5��ɫ,6�Ƿ����,7�⻧
		String carname=field1.getText();
		String cartype = field2.getText() ;
		String price=field3.getText();
		String color=field5.getText();
		String rent=field6.getText();
		String fk_username=field7.getText();
		String date=field8.getText(); 
		// ����У���û�����ļ۸�
		String priceRegx="^(([1-9]+[0-9]*.{1}[0-9]+)|([0].{1}[1-9]+[0-9]*)|([1-9][0-9]*)|([0][.][0-9]+[1-9]*))$";
		// ����У���û����������
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(source == buttonofConfirm)
		{
			if("".equals(carname)||"".equals(cartype)||"".equals(price)||"".equals(color)||"".equals(rent))
			{
				JOptionPane.showMessageDialog(null, "����д������");
			} 
			while(!price.matches(priceRegx)){
				JOptionPane.showMessageDialog(null, "��������ʽ!");
				return;
			} 
			
				boolean isrent = "��".equals(rent);
				String usernameToAdd= isrent&&!"".equals(fk_username)?fk_username:null;
				Date date1=null;
				if(isrent) {
					try {
						date1 = new Date(dateFormat.parse(date).getTime());
						if(date1.getTime()>System.currentTimeMillis()){
							JOptionPane.showMessageDialog(null, "��������");
							return;
						}
					} catch (ParseException parseException) {
						JOptionPane.showMessageDialog(null, "�������ڸ�ʽ!");
						return;
					}
				}
				boolean isAddSuccess= carDao.addCar(new Car(carname, cartype,isrent , Double.parseDouble(price), color, usernameToAdd,date1));
				if(isAddSuccess){
					JOptionPane.showMessageDialog(null, "¼��ɹ���");
					this.dispose();
					new AdminPage();
				}else{
					JOptionPane.showMessageDialog(null, "¼��ʧ��, ������⻧�Ƿ����");
				}
			
		}
		else if(source == buttonofCancel)
		{
			this.dispose();
			new AdminPage();
		}
		else if(source == buttonOfReset)
		{
			field1.setText("");
			field2.setText("");
			field3.setText("");
			field8.setText("");
			field5.setText("");
			field6.setText("");
			field7.setText("");
		}
	}
}
