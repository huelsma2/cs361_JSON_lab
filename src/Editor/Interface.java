package Editor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.google.gson.Gson;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Interface {

	private JFrame frame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JLabel lblDepartment;
	private JTextField departmentField;
	private JLabel lblPhone;
	private JTextField phoneField;
	private JButton btnSubmit;
	private JButton btnExit;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnOther;
	private DirectoryProxy newProxy = new DirectoryProxy();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setTitle("Lab 8 Interface");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel headerLabel = new JLabel("Please enter your information below");
		headerLabel.setOpaque(true);
		headerLabel.setBackground(Color.LIGHT_GRAY);
		headerLabel.setBounds(0, 0, 444, 47);
		headerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		frame.getContentPane().add(headerLabel);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 135, 80, 14);
		frame.getContentPane().add(lblFirstName);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(90, 132, 120, 20);
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(304, 132, 120, 20);
		frame.getContentPane().add(lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(234, 135, 70, 14);
		frame.getContentPane().add(lblLastName);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 188, 80, 14);
		frame.getContentPane().add(lblDepartment);
		
		departmentField = new JTextField();
		departmentField.setColumns(10);
		departmentField.setBounds(90, 185, 120, 20);
		frame.getContentPane().add(departmentField);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(234, 188, 70, 14);
		frame.getContentPane().add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(304, 185, 120, 20);
		frame.getContentPane().add(phoneField);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(90, 53, 80, 23);
		frame.getContentPane().add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(182, 53, 80, 23);
		frame.getContentPane().add(rdbtnFemale);
		
		rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(274, 53, 80, 23);
		frame.getContentPane().add(rdbtnOther);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnMale);
		buttonGroup.add(rdbtnFemale);
		buttonGroup.add(rdbtnOther);
		buttonGroup.setSelected(rdbtnMale.getModel(), true);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mr.", "Ms.", "Mrs.", "Dr.", "Col.", "Prof."}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(12, 89, 80, 20);
		frame.getContentPane().add(comboBox);
		
		btnSubmit = new JButton("Submit (send)");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = firstNameField.getText();
				String lname = lastNameField.getText();
				String dept = departmentField.getText();
				String phone = phoneField.getText();
				String gender;
				if(rdbtnMale.isSelected()){
					gender = "Male";
				}else if(rdbtnFemale.isSelected()){
					gender = "Female";
				}else{
					gender = "Other";
				}
				String title = (String) comboBox.getSelectedItem().toString();
				if(firstNameField.getText().length() == 0 || lastNameField.getText().length() == 0
						|| departmentField.getText().length() == 0 || phoneField.getText().length() == 0) return;
				ArrayList<Employee> p = new ArrayList<Employee>();
				Gson g = new Gson();
				p.add( new Employee(fname, lname, dept, phone, gender, title));
				String out = g.toJson(p);
				out = "ADD " + out;
				newProxy.add(out);
				
				firstNameField.setText("");
				lastNameField.setText("");
				departmentField.setText("");
				phoneField.setText("");
				comboBox.setSelectedIndex(0);
				buttonGroup.setSelected(rdbtnMale.getModel(), true);
				if(newProxy.serverReceived()){
					JOptionPane.showMessageDialog(null, "Server received message!");
				}else{
					JOptionPane.showMessageDialog(null, "Server did not receive message.");
				}
			}
		});
		btnSubmit.setBounds(10, 227, 120, 23);
		frame.getContentPane().add(btnSubmit);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(304, 227, 120, 23);
		frame.getContentPane().add(btnExit);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(12, 58, 80, 14);
		frame.getContentPane().add(lblGender);
	}
}
