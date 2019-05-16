import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Main extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField capacity;
	private JTextField hourlyFee;

	private int kapasite;
	private double saatlikucret;
	private AutoPark otopark;
	private JTextField textField;
	private JTextField textField_1;
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setTitle("OTOPARK SÝSTEMÝ");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 0, 837, 817);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		JPanel otopark_panel = new JPanel();
		otopark_panel.setBackground(Color.DARK_GRAY);
		otopark_panel.setBounds(89, 10, 638, 103);
		contentPane.add(otopark_panel);
		otopark_panel.setLayout(null);
		
		capacity = new JTextField();
		capacity.setBounds(109, 10, 216, 33);
		otopark_panel.add(capacity);
		capacity.setColumns(10);
		
		hourlyFee = new JTextField();
		hourlyFee.setBounds(109, 53, 216, 33);
		otopark_panel.add(hourlyFee);
		hourlyFee.setColumns(10);
		
		JLabel lblKapasite = new JLabel("Kapasite:");
		lblKapasite.setForeground(Color.WHITE);
		lblKapasite.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKapasite.setBounds(10, 8, 83, 33);
		otopark_panel.add(lblKapasite);
		
		JLabel lblSaatlikcret = new JLabel("Saatlik \u00FCcret:");
		lblSaatlikcret.setForeground(Color.WHITE);
		lblSaatlikcret.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSaatlikcret.setBounds(10, 51, 89, 33);
		otopark_panel.add(lblSaatlikcret);
		
		JButton btnNewButton_2 = new JButton("Otopark Olu\u015Ftur");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(384, 25, 194, 53);
		otopark_panel.add(btnNewButton_2);
		
		JPanel panel_genel = new JPanel();
		panel_genel.setBackground(SystemColor.activeCaption);
		panel_genel.setBounds(89, 169, 638, 594);
		contentPane.add(panel_genel);
		panel_genel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(228, 10, 400, 180);
		panel_genel.add(panel);
		panel.setBackground(Color.ORANGE);
		panel.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Ara\u00E7 Giri\u015F");
		btnNewButton.setBounds(10, 33, 200, 120);
		panel_genel.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		panel_genel.setVisible(false);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(capacity.getText().isEmpty() || hourlyFee.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Lütfen bütün alanlarý doldurunuz. ");
				}else {
					kapasite=Integer.parseInt(capacity.getText());
					saatlikucret=Double.parseDouble(hourlyFee.getText());
					otopark= new AutoPark(saatlikucret,kapasite);
					hourlyFee.setEnabled(false);
					capacity.setEnabled(false);
					btnNewButton_2.setVisible(false);
					panel_genel.setVisible(true);
					
				}				
			}
		});
		
		
		
		JButton btnNewButton_1 = new JButton("X");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				panel.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton_1.setBounds(346, 0, 54, 38);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("PLAKA:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 47, 88, 22);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(108, 47, 163, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u00D6ZEL ARA\u00C7 MI?");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnNewRadioButton.setBounds(108, 75, 163, 21);
		panel.add(rdbtnNewRadioButton);
		panel.setVisible(false);
		
		
		
		JButton btnGiriYap = new JButton("G\u0130R\u0130\u015E YAP");
		btnGiriYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Plaka alaný boþ geçilemez");
				}else {
					boolean isOfficial;
					if(rdbtnNewRadioButton.getSelectedObjects()!=null) {
						isOfficial=true;
					}
					else {
						isOfficial=false;
					}
					if(otopark.isParked(textField.getText())) {
						JOptionPane.showMessageDialog(null, "Araç zaten park edilmiþ.");
					}
					else if(otopark.vehicleEnters(textField.getText(), Time.getTime(), isOfficial)==true) {
							JOptionPane.showMessageDialog(null, "Araç giriþ yaptý.");
							textField.setText("");
					}else {
							JOptionPane.showMessageDialog(null, "Araç giriþ yapamadý.");
					}											
				}			
			}			
		});
		btnGiriYap.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGiriYap.setBounds(108, 119, 163, 43);
		panel.add(btnGiriYap);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 127));
		panel_1.setBounds(228, 222, 400, 180);
		panel_genel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JButton btnNewButton_3 = new JButton("Ara\u00E7 \u00C7\u0131k\u0131\u015F");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_3.setBounds(10, 241, 200, 120);
		panel_genel.add(btnNewButton_3);
		
		
		
		
		
		JLabel label = new JLabel("PLAKA:");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(10, 43, 88, 22);
		panel_1.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(108, 43, 163, 22);
		panel_1.add(textField_1);
		
		JButton btnkYap = new JButton("\u00C7IKI\u015E YAP");
		btnkYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Time cikis=new Time(23,00);
				Time cikis=Time.getTime();
				if(textField_1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, " Plaka alaný boþ geçilemez");
				}else if(otopark.vehicleExits(textField_1.getText(),cikis)==true){
					JOptionPane.showMessageDialog(null, "Araç çýkýþ yaptý.");
					textField_1.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Araç çýkýþ yapamadý.");
				}			
			}
		});
		btnkYap.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnkYap.setBounds(108, 99, 163, 43);
		panel_1.add(btnkYap);
		

		
		
		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 24));
		button.setBounds(345, 0, 55, 38);
		panel_1.add(button);
		
		JButton btnGnlkKazan = new JButton("G\u00DCNL\u00DCK KAZAN\u00C7");
		btnGnlkKazan.setForeground(new Color(255, 0, 0));
		btnGnlkKazan.setBackground(new Color(205, 133, 63));
		btnGnlkKazan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "GÜNLÜK KAZANÇ:"+df2.format(otopark.getIncomeDaily()));
			}
		});
		btnGnlkKazan.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnGnlkKazan.setBounds(292, 507, 336, 65);
		panel_genel.add(btnGnlkKazan);
		
		JButton btnNewButton_4 = new JButton("Park Kay\u0131tlar\u0131");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				otopark.getParkRecords();
			}
		});
		btnNewButton_4.setBounds(10, 424, 255, 73);
		panel_genel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\u00C7\u0131k\u0131\u015F Yapan Ara\u00E7lar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				otopark.getExitRecords();
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_5.setBounds(292, 424, 336, 73);
		panel_genel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("\u00DCye Ara\u00E7lar");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				otopark.getSubscribedVehicle();
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_6.setBounds(10, 507, 255, 65);
		panel_genel.add(btnNewButton_6);
		
		JLabel lblNewLabel_1 = new JLabel("NOT:ARA\u00C7LARIN G\u0130R\u0130\u015E,\u00C7IKI\u015E VE \u00DCYEL\u0130K BA\u015ELANGI\u00C7LARI S\u0130STEM SAAT\u0130NDEN OTOMAT\u0130K OLARAK ALINMAKTADIR.");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBackground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 123, 783, 36);
		contentPane.add(lblNewLabel_1);
			
	}
}
