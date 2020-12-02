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
	 * 管理员端的界面
	 */
	JTable table;
	JLabel label1, label2, label3 ;
	Object a[][];
	Object name[] = {"编号","名称","型号","是否被租用","价格","颜色","租户名"};
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
		setTitle("管理员界面");
		infoDisplay();
	}

	void init() {
		label1 = new JLabel("汽车租赁信息管理系统");
		buttonOfInfoInput = new JButton("  汽车信息录入  ");
		buttonOfInfoInput.addActionListener(this);
		buttonOfInfoBrowser = new JButton("  汽车信息刷新  ");
		buttonOfInfoBrowser.addActionListener(this);
		buttonOfDelete = new JButton("    删	            除      ");
		buttonOfDelete.addActionListener(this);
		buttonOfLogout = new JButton("  退   出   登   录  ");
		buttonOfLogout.addActionListener(this);
		buttonOfUpdate = new JButton("    修	           改      ");
		buttonOfUpdate.addActionListener(this);
		buttonOfDetail = new JButton("  详   细   信   息  ");
		buttonOfDetail.addActionListener(this);
		label2 = new JLabel("待删除信息编号：");
		label3 = new JLabel("待修改信息的编号：");
		field = new JTextField();
		field2 = new JTextField();

		a = new Object[500][7];
		table = new JTable(a, name);//组件的创建
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

	public void infoDisplay()//信息浏览的方法，因为删除数据后会刷新一下，自动调用此函数。
	{
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				a[i][j]="";
			}
		}
		List<Car> cars = carDao.listAllCar();
		for(int j=0;j<cars.size();j++){
			//  {"编号","名称","型号","是否被租用","价格","颜色","租户名"};
			Car c = cars.get(j);
			a[j][0]=c.getId();
			a[j][1]=c.getCarname();
			a[j][2]=c.getCartype();
			a[j][3]=c.isRent()==true?"是":"否";
			a[j][4]=c.getPrice();
			a[j][5]=c.getColor();
			a[j][6]=c.getFk_username();
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonOfInfoInput)//点击信息修改按钮
		{
			this.dispose();
			new Add();
		} else if (source == buttonOfInfoBrowser)//点击信息浏览按钮
		{
			infoDisplay();
		} else if (source == buttonOfUpdate)//点击修改按钮
		{
			if ("".equals(field2.getText())) {
				JOptionPane.showMessageDialog(null, "输入修改车型的编号！");
			} else {
				String idRegx="^[0-9]\\d*$";
				if(field2.getText().matches(idRegx)){
					boolean carExist = carDao.carExist(Integer.parseInt(field2.getText()));
					if(carExist==false){
						JOptionPane.showMessageDialog(null, "不存在这辆车");
					}else {
						this.dispose();
						new Update(field2.getText());
					}
				}else{
					JOptionPane.showMessageDialog(null, "请输入数字");
				}
			}
		} 
		else if (source == buttonOfDelete)//点击删除按钮
		{
			if ("".equals(field.getText())) {
				JOptionPane.showMessageDialog(null, "请输入删除车辆的编号！");
			} else {
				String idRegx = "^[0-9]\\d*$";
				if (!field.getText().matches(idRegx)) {
					JOptionPane.showMessageDialog(null, "请输入数字");
				} else {
					int id = Integer.parseInt(field.getText());
					boolean b = carDao.carExist(id);
					if (b) {
						Car car = carDao.queryCarById(id);
						int n = JOptionPane.showConfirmDialog(this, "确定删除此车辆信息？", "确认对话框", JOptionPane.YES_NO_OPTION);//确认文本框
						if (n == JOptionPane.YES_OPTION) {
							boolean rent = car.isRent();
							if (rent) {
								int m = JOptionPane.showConfirmDialog(this, "此车辆正在被租用，是否删除？", "确认对话框", JOptionPane.YES_NO_OPTION);//确认文本框
								if (m == JOptionPane.YES_OPTION) {
									carDao.deleteCarById(id);
									field.setText("");
									JOptionPane.showMessageDialog(null, "删除成功！");
									repaint();
									infoDisplay();
								} else {
									field.setText("");
									JOptionPane.showMessageDialog(null, "您取消了删除操作");
									repaint();
									infoDisplay();
								}
							} else {
								carDao.deleteCarById(id);
								field.setText("");
								JOptionPane.showMessageDialog(null, "删除成功！");
								repaint();
								infoDisplay();
							}
						} else if (n == JOptionPane.NO_OPTION) {
						}
					} else {
						JOptionPane.showMessageDialog(null, "没有此编号的车辆信息！");
					}
				}
			}
		} else if (source == buttonOfLogout)//退出
		{
			this.dispose();
			new Login();
		}
	}
}
