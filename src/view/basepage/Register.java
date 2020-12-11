package view.basepage;
import dao.UserDao;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * @author yuzhihai
 */
public class 	Register extends JFrame implements ActionListener {

	JTextField field1;
	JPasswordField field2,field3;
	JButton buttonOfRegister,buttonOfReturn;
	Box box1,box2,box3,box4,baseBox;
	
	private UserDao userDao=new UserDao();
	
	public Register()
	{
		setLayout(new FlowLayout());
		init();
		setVisible(true);
		setBounds(500, 200, 500, 480);
		setTitle("ע�����");
	}
	
	void init()
	{
		box1= Box.createHorizontalBox();
		box1.add(new JLabel("���û���:"));
		box1.add(Box.createHorizontalStrut(8));
		field1 = new JTextField(15);
		box1.add(field1);
		
		box2= Box.createHorizontalBox();
		box2.add(new JLabel("��   �� :"));
		box2.add(Box.createHorizontalStrut(8));
		field2 = new JPasswordField(15);
		box2.add(field2);
		
		box3= Box.createHorizontalBox();
		box3.add(new JLabel("�ٴ�����:"));
		box3.add(Box.createHorizontalStrut(8));
		field3 = new JPasswordField(15);
		box3.add(field3);
		
		box4= Box.createHorizontalBox();
		buttonOfRegister = new JButton("ע��");
		buttonOfRegister.addActionListener(this);
		buttonOfReturn = new JButton("����");
		buttonOfReturn.addActionListener(this);
		box4.add(buttonOfRegister);
		box4.add(Box.createHorizontalStrut(5));
		box4.add(buttonOfReturn);
		
		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(50));
		baseBox.add(box1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(box3);
		baseBox.add(Box.createVerticalStrut(200));
		baseBox.add(box4);
		
		add(baseBox);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int success = 1;//����Ѿ������˴��˺ţ���ֵΪ0����Ϊ���봴���˺ŷ����Ľ�������
		if(source == buttonOfRegister)
		{
			// �ж��Ƿ��������û���������
			if ("".equals(field1.getText()) || "".equals(field2.getText())|| "".equals(field3.getText()) )
			{
				JOptionPane.showMessageDialog(null, "����д������");
			} else{
				boolean userExists = userDao.isUserExists(field1.getText());
				if(userExists){
					JOptionPane.showMessageDialog(null, "���˺��Ѿ����ڣ�");
					field1.setText("");
					success = 0;
				}else{
					if(field2.getText().equals(field3.getText())&&success==1){
						boolean registerSuccess = userDao.addUser(field1.getText(), field2.getText());
						
						if(registerSuccess) {
							JOptionPane.showMessageDialog(null, "ע��ɹ���");
						} else{
							JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
						}
						this.dispose();
						new Login();
					}else if(success==1){
						JOptionPane.showMessageDialog(null,"������������벻ƥ��!" );
					}
				}
			}
		}else if(source == buttonOfReturn)	{
			this.dispose();
			new Login();
		}
	}
}
