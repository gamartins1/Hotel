package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import java.awt.Choice;
import java.awt.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Window.Type;
import java.awt.Dimension;

public class VeiculoView extends JFrame {

	private JPanel contentPane;
	private JTextField txtField1;
	private JTextField txtField2;
	
	int xx, xy;
	private JTextField txtField3;
	private JTextField txtField4;
	private JTextField txtField5;
	private JTextField txtField6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VeiculoView frame = new VeiculoView();
					frame.setUndecorated(true); //inserido para rodar sem a DAO
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VeiculoView() {
		setName("veiculoview");
		setUndecorated(true);
		setType(Type.UTILITY);
		setResizable(false);
		setMinimumSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lbl_fechar = new JLabel("x");
		lbl_fechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				VeiculoView.this.dispose(); //fecha a janela 
				new MenuView().setVisible(true); //abre a janela menu
			}
		});
		lbl_fechar.setIcon(new ImageIcon(VeiculoView.class.getResource("/icones/icon-fechar.png")));
		lbl_fechar.setBounds(750, 240, 50, 40);
		contentPane.add(lbl_fechar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 800, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_img2 = new JLabel("");
		
		lbl_img2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		lbl_img2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				VeiculoView.this.setLocation(x - xx, y - xy);
			}
		});
		lbl_img2.setBounds(96, -100, 610, 423);
		lbl_img2.setIcon(new ImageIcon(VeiculoView.class.getResource("/imagens/img3.jpg")));
		panel.add(lbl_img2);
		
		Button buttonInserir = new Button("INSERIR");
		buttonInserir.setForeground(Color.WHITE);
		buttonInserir.setBackground(new Color(0, 139, 139));
		buttonInserir.setBounds(133, 537, 160, 40);
		contentPane.add(buttonInserir);
		
		txtField1 = new JTextField();
		txtField1.setForeground(new Color(0, 0, 0));
		txtField1.setText("Digite a marca do veiculo");
		txtField1.setBounds(40, 356, 350, 30);
		contentPane.add(txtField1);
		txtField1.setColumns(10);
		
		JSeparator separator_0 = new JSeparator();
		separator_0.setBounds(40, 387, 350, 2);
		contentPane.add(separator_0);
		
		JLabel lbl_marca = new JLabel("MARCA");
		lbl_marca.setBounds(40, 338, 228, 16);
		contentPane.add(lbl_marca);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(40, 451, 350, 2);
		contentPane.add(separator_1);
		
		txtField2 = new JTextField();
		txtField2.setText("Digite modelo do veiculo");
		txtField2.setForeground(new Color(0, 0, 0));
		txtField2.setColumns(10);
		txtField2.setBounds(40, 419, 350, 30);
		contentPane.add(txtField2);
		
		JLabel lbl_modelo = new JLabel("MODELO");
		lbl_modelo.setBounds(40, 399, 228, 16);
		contentPane.add(lbl_modelo);
		
		Button buttonEditar = new Button("EDITAR");
		buttonEditar.setForeground(Color.WHITE);
		buttonEditar.setBackground(new Color(0, 139, 139));
		buttonEditar.setBounds(323, 537, 160, 40);
		contentPane.add(buttonEditar);
		
		JLabel lblVeiculo = new JLabel("VEICULO");
		lblVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeiculo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblVeiculo.setBounds(360, 280, 126, 40);
		contentPane.add(lblVeiculo);
		
		JLabel lblIconVeiculo = new JLabel("");
		lblIconVeiculo.setIcon(new ImageIcon(VeiculoView.class.getResource("/icones/icons-veiculo.png")));
		lblIconVeiculo.setBounds(315, 275, 50, 50);
		contentPane.add(lblIconVeiculo);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(40, 518, 350, 2);
		contentPane.add(separator_2);
		
		txtField3 = new JTextField();
		txtField3.setText("Digite o ano do veiculo");
		txtField3.setForeground(Color.BLACK);
		txtField3.setColumns(10);
		txtField3.setBounds(40, 486, 160, 30);
		contentPane.add(txtField3);
		
		JLabel lbl_ano = new JLabel("ANO");
		lbl_ano.setBounds(40, 466, 160, 16);
		contentPane.add(lbl_ano);
		
		txtField4 = new JTextField();
		txtField4.setText("Digite a placa do veiculo");
		txtField4.setForeground(Color.BLACK);
		txtField4.setColumns(10);
		txtField4.setBounds(419, 356, 350, 30);
		contentPane.add(txtField4);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(419, 387, 350, 2);
		contentPane.add(separator_3);
		
		JLabel lbl_placa = new JLabel("PLACA");
		lbl_placa.setBounds(419, 338, 228, 16);
		contentPane.add(lbl_placa);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(419, 451, 350, 2);
		contentPane.add(separator_4);
		
		JLabel lbl_tipo = new JLabel("TIPO");
		lbl_tipo.setBounds(419, 399, 228, 16);
		contentPane.add(lbl_tipo);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(419, 518, 350, 2);
		contentPane.add(separator_5);
		
		txtField5 = new JTextField();
		txtField5.setText("Informe o proprietario do veiculo");
		txtField5.setForeground(Color.BLACK);
		txtField5.setColumns(10);
		txtField5.setBounds(419, 486, 350, 30);
		contentPane.add(txtField5);
		
		JLabel lbl_proprietario = new JLabel("PROPRIETARIO");
		lbl_proprietario.setBounds(419, 466, 228, 16);
		contentPane.add(lbl_proprietario);
		
		Button buttonDeletar = new Button("DELETAR");
		buttonDeletar.setForeground(Color.WHITE);
		buttonDeletar.setBackground(new Color(0, 139, 139));
		buttonDeletar.setBounds(513, 537, 160, 40);
		contentPane.add(buttonDeletar);
		
		txtField6 = new JTextField();
		txtField6.setText("Digite a cor do veiculo");
		txtField6.setForeground(Color.BLACK);
		txtField6.setColumns(10);
		txtField6.setBounds(230, 486, 160, 30);
		contentPane.add(txtField6);
		
		JLabel lbl_ano_1 = new JLabel("COR");
		lbl_ano_1.setBounds(230, 466, 160, 16);
		contentPane.add(lbl_ano_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Bicicleta", "Carro", "Motocicleta"}));
		comboBox.setBounds(419, 423, 350, 30);
		contentPane.add(comboBox);
	}
}
