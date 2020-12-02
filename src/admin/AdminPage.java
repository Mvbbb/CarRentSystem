package admin;
import java.awt.BorderLayout;
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

import base.*;
import bean.Car;
import dao.CarDao;

public class AdminPage extends JFrame implements ActionListener {

	/*
	 * ����Ա�˵Ľ���
	 */
	JTable table;
	JLabel label1, label2, label3 ;
	Object a[][];
	Object name[] = {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ","�⻧��"};
	JButton buttonOfInfoInput, buttonOfInfoBrowser, buttonOfDelete, buttonOfLogout, buttonOfDetail, buttonOfUpdate;
	Box box1, box2;
	JTextField field, field2;
	JPanel jPanel4, jPanel5;
	
	///////////
	private CarDao carDao=new CarDao();
	///////////

	public AdminPage() {

		init();
		setVisible(true);
		setBounds(500, 200, 625, 490);
		setTitle("����Ա����");
		infoDisplay();
	}

	void init() {
		label1 = new JLabel("����������Ϣ����ϵͳ");
		buttonOfInfoInput = new JButton("  ������Ϣ¼��  ");
		buttonOfInfoInput.addActionListener(this);
		buttonOfInfoBrowser = new JButton("  ������Ϣˢ��  ");
		buttonOfInfoBrowser.addActionListener(this);
		buttonOfDelete = new JButton("    ɾ	            ��      ");
		buttonOfDelete.addActionListener(this);
		buttonOfLogout = new JButton("  ��   ��   ��   ¼  ");
		buttonOfLogout.addActionListener(this);
		buttonOfUpdate = new JButton("    ��	           ��      ");
		buttonOfUpdate.addActionListener(this);
		buttonOfDetail = new JButton("  ��   ϸ   ��   Ϣ  ");
		buttonOfDetail.addActionListener(this);
		label2 = new JLabel("��ɾ����Ϣ��ţ�");
		label3 = new JLabel("���޸���Ϣ�ı�ţ�");
		field = new JTextField();
		field2 = new JTextField();

		a = new Object[500][7];
		table = new JTable(a, name);//����Ĵ���
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);

		box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(20));
		box1.add(buttonOfInfoInput);
		box1.add(Box.createVerticalStrut(10));
		box1.add(buttonOfInfoBrowser);
		box1.add(Box.createVerticalStrut(15));
		box1.add(label2);
		box1.add(Box.createVerticalStrut(5));
		box1.add(field);
		box1.add(Box.createVerticalStrut(5));
		box1.add(buttonOfDelete);
		box1.add(Box.createVerticalStrut(25));
		box1.add(label3);
		box1.add(Box.createVerticalStrut(5));
		box1.add(field2);
		box1.add(Box.createVerticalStrut(5));
		box1.add(buttonOfUpdate);
		box1.add(Box.createVerticalStrut(40));
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

	public void infoDisplay()//��Ϣ����ķ�������Ϊɾ�����ݺ��ˢ��һ�£��Զ����ô˺�����
	{
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				a[i][j]="";
			}
		}
		List<Car> cars = carDao.listAllCar();
		for(int j=0;j<cars.size();j++){
			//  {"���","����","�ͺ�","�Ƿ�����","�۸�","��ɫ","�⻧��"};
			Car c = cars.get(j);
			a[j][0]=c.getId();
			a[j][1]=c.getCarname();
			a[j][2]=c.getCartype();
			a[j][3]=c.isRent()==true?"��":"��";
			a[j][4]=c.getPrice();
			a[j][5]=c.getColor();
			a[j][6]=c.getFk_username();
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOfInfoInput)//�����Ϣ�޸İ�ť
		{
			this.dispose();
			new Add();
		} else if (source == buttonOfInfoBrowser)//�����Ϣ�����ť
		{
			infoDisplay();
		} else if (source == buttonOfUpdate)//����޸İ�ť
		{
			if ("".equals(field2.getText())) {
				JOptionPane.showMessageDialog(null, "�����޸ĳ��͵ı�ţ�");
			} else {
				String idRegx="^[0-9]\\d*$";
				if(field2.getText().matches(idRegx)){
					boolean carExist = carDao.carExist(Integer.parseInt(field2.getText()));
					if(carExist==false){
						JOptionPane.showMessageDialog(null, "������������");
					}else {
						this.dispose();
						new Update(field2.getText());
					}
				}else{
					JOptionPane.showMessageDialog(null, "����������");
				}
			}
		} 
		else if (source == buttonOfDelete)//���ɾ����ť
		{
			if ("".equals(field.getText())) {
				JOptionPane.showMessageDialog(null, "������ɾ�������ı�ţ�");
			} else {
				String idRegx = "^[0-9]\\d*$";
				if (!field.getText().matches(idRegx)) {
					JOptionPane.showMessageDialog(null, "����������");
				} else {
					int id = Integer.parseInt(field.getText());
					boolean b = carDao.carExist(id);
					if (b) {
						Car car = carDao.queryCarById(id);
						int n = JOptionPane.showConfirmDialog(this, "ȷ��ɾ���˳�����Ϣ��", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION);//ȷ���ı���
						if (n == JOptionPane.YES_OPTION) {
							boolean rent = car.isRent();
							if (rent) {
								int m = JOptionPane.showConfirmDialog(this, "�˳������ڱ����ã��Ƿ�ɾ����", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION);//ȷ���ı���
								if (m == JOptionPane.YES_OPTION) {
									carDao.deleteCarById(id);
									field.setText("");
									JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
									repaint();
									infoDisplay();
								} else {
									field.setText("");
									JOptionPane.showMessageDialog(null, "��ȡ����ɾ������");
									repaint();
									infoDisplay();
								}
							} else {
								carDao.deleteCarById(id);
								field.setText("");
								JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
								repaint();
								infoDisplay();
							}
						} else if (n == JOptionPane.NO_OPTION) {
						}
					} else {
						JOptionPane.showMessageDialog(null, "û�д˱�ŵĳ�����Ϣ��");
					}
				}
			}
		} else if (source == buttonOfLogout)//�˳�
		{
			this.dispose();
			new Login();
		}
	}
}
