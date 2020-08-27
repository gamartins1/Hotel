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
import javax.swing.border.LineBorder;
import java.awt.Window.Type;
import java.awt.Dimension;

public class RecepcionistaView extends JFrame {

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
					RecepcionistaView frame = new RecepcionistaView();
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
	public RecepcionistaView() {
		setName("recepcionista");
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
		lbl_fechar.setBounds(750, 240, 50, 40);
		lbl_fechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				RecepcionistaView.this.dispose(); //fecha a janela 
				new MenuView().setVisible(true); //abre a janela menu
			}
		});
		contentPane.setLayout(null);
		lbl_fechar.setIcon(new ImageIcon(RecepcionistaView.class.getResource("/icones/icon-fechar.png")));
		contentPane.add(lbl_fechar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 237);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_img2 = new JLabel("");
		lbl_img2.setIcon(new ImageIcon(RecepcionistaView.class.getResource("/imagens/img4.jpg")));
		lbl_img2.setBounds(96, -59, 610, 423);
		panel.add(lbl_img2);
		
		Button buttonInserir = new Button("INSERIR");
		buttonInserir.setBounds(133, 537, 160, 40);
		buttonInserir.setForeground(Color.WHITE);
		buttonInserir.setBackground(new Color(0, 139, 139));
		contentPane.add(buttonInserir);
		
		txtField1 = new JTextField();
		txtField1.setBounds(40, 356, 350, 30);
		txtField1.setForeground(new Color(0, 0, 0));
		txtField1.setText("Digite seu nome");
		contentPane.add(txtField1);
		txtField1.setColumns(10);
		
		JSeparator separator_0 = new JSeparator();
		separator_0.setBounds(40, 387, 350, 2);
		contentPane.add(separator_0);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(40, 338, 228, 16);
		contentPane.add(lblNome);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(40, 451, 350, 2);
		contentPane.add(separator_1);
		
		txtField2 = new JTextField();
		txtField2.setBounds(40, 419, 350, 30);
		txtField2.setText("Digite seu documento");
		txtField2.setForeground(new Color(0, 0, 0));
		txtField2.setColumns(10);
		contentPane.add(txtField2);
		
		JLabel lblSenha = new JLabel("CPF");
		lblSenha.setBounds(40, 399, 228, 16);
		contentPane.add(lblSenha);
		
		Button buttonEditar = new Button("EDITAR");
		buttonEditar.setBounds(323, 537, 160, 40);
		buttonEditar.setForeground(Color.WHITE);
		buttonEditar.setBackground(new Color(0, 139, 139));
		contentPane.add(buttonEditar);
		
		JLabel lblCliente = new JLabel("RECEPCIONISTA");
		lblCliente.setBounds(307, 280, 228, 40);
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblCliente);
		
		JLabel lblIconCliente = new JLabel("");
		lblIconCliente.setBounds(270, 270, 50, 50);
		lblIconCliente.setIcon(new ImageIcon(RecepcionistaView.class.getResource("/icones/icons-recepcao.png")));
		contentPane.add(lblIconCliente);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(40, 518, 350, 2);
		contentPane.add(separator_2);
		
		txtField3 = new JTextField();
		txtField3.setBounds(40, 486, 350, 30);
		txtField3.setText("Digite a data de nascimento");
		txtField3.setForeground(Color.BLACK);
		txtField3.setColumns(10);
		contentPane.add(txtField3);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setBounds(40, 466, 228, 16);
		contentPane.add(lblDataDeNascimento);
		
		txtField4 = new JTextField();
		txtField4.setBounds(419, 356, 350, 30);
		txtField4.setText("Digite seu email");
		txtField4.setForeground(Color.BLACK);
		txtField4.setColumns(10);
		contentPane.add(txtField4);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(419, 387, 350, 2);
		contentPane.add(separator_3);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(419, 338, 228, 16);
		contentPane.add(lblEmail);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(419, 451, 350, 2);
		contentPane.add(separator_4);
		
		txtField5 = new JTextField();
		txtField5.setBounds(419, 419, 350, 30);
		txtField5.setText("Digite seu telefone");
		txtField5.setForeground(Color.BLACK);
		txtField5.setColumns(10);
		contentPane.add(txtField5);
		
		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setBounds(419, 399, 228, 16);
		contentPane.add(lblTelefone);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(419, 518, 350, 2);
		contentPane.add(separator_5);
		
		txtField6 = new JTextField();
		txtField6.setBounds(419, 486, 350, 30);
		txtField6.setText("Digite o cargo da recepcionista");
		txtField6.setForeground(Color.BLACK);
		txtField6.setColumns(10);
		contentPane.add(txtField6);
		
		JLabel lbl_cargo = new JLabel("CARGO");
		lbl_cargo.setBounds(419, 466, 228, 16);
		contentPane.add(lbl_cargo);
		
		Button buttonDeletar = new Button("DELETAR");
		buttonDeletar.setBounds(513, 537, 160, 40);
		buttonDeletar.setForeground(Color.WHITE);
		buttonDeletar.setBackground(new Color(0, 139, 139));
		contentPane.add(buttonDeletar);
	}
}
