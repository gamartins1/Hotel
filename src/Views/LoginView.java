package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import DAO.LoginDAO;
import persistencia.Banco;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txUser;
	
	int xx, xy;
	private JPasswordField txSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setName("loginview");
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
				int dialog = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog(null, "Deseja Encerrar?", "Exit", dialog);
				if(result == 0) {
					System.exit(0);
				}
			}
		});
		lbl_fechar.setIcon(new ImageIcon(LoginView.class.getResource("/icones/icon-fechar.png")));
		lbl_fechar.setBounds(750, 234, 50, 40);
		contentPane.add(lbl_fechar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 800, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_img1 = new JLabel("");
		lbl_img1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbl_img1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		lbl_img1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				LoginView.this.setLocation(x - xx, y - xy);
			}
		});
		lbl_img1.setBounds(96, -124, 610, 423);
		lbl_img1.setIcon(new ImageIcon(LoginView.class.getResource("/imagens/img1.jpg")));
		panel.add(lbl_img1);
		
		Button buttonEntrar = new Button("ENTRAR");
		buttonEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = null, senha = null, id_recepcionista = null;
				
				user = txUser.getText();
				senha = txSenha.getText();
				if (user.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo de usuário, atente-se ao formato padrão de usuário");
				}
				else if (senha.contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo de senha");
				}
				else
				{
					try {
						id_recepcionista = user.substring(5);
					}
					catch (StringIndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(null, "Erro ao efetuar a validação do campo de usuário, verifique se ele está seguindo o padrão de usuário");
					}
					try {
						LoginDAO login = new LoginDAO();
						if(login.logar(id_recepcionista, senha) == true) {
							LoginView.this.dispose();
							new MenuView().setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Erro ao efetuar login, verifique o usuário e a senha");
						}
					}
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Ocorreu um erro ao estabelecer conexão com o banco de dados");
						ex.printStackTrace();
					}
				}
			}
		});
		buttonEntrar.setForeground(Color.WHITE);
		buttonEntrar.setBackground(new Color(0, 139, 139));
		buttonEntrar.setBounds(229, 538, 160, 40);
		contentPane.add(buttonEntrar);
		
		txUser = new JTextField();
		txUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txUser.getText().isEmpty()) {
					
				}
				else {
					try {
						String user = txUser.getText().toUpperCase();
						if (!txUser.getText().substring(0, 5).toUpperCase().equals("RECEP") && !txUser.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Talvez seu login esteja errado hein...");
						}	
					}
					catch (StringIndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(null, "Talvez seu login esteja errado hein...");
					}
				}
			}
		});
		txUser.setToolTipText("Digite seu usuario (RECEP...)");
		txUser.setForeground(new Color(0, 0, 0));
		txUser.setBounds(229, 381, 350, 30);
		contentPane.add(txUser);
		txUser.setColumns(10);
		
		JSeparator separator_0 = new JSeparator();
		separator_0.setBounds(229, 413, 350, 2);
		contentPane.add(separator_0);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(229, 362, 56, 16);
		contentPane.add(lblUsuario);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(229, 489, 350, 2);
		contentPane.add(separator_1);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setBounds(229, 438, 56, 16);
		contentPane.add(lblSenha);
		
		Button buttonCadastrar = new Button("CADASTRAR");
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView.this.dispose(); //fecha a janela 
				new ClienteView().setVisible(true); //abre a janela cliente
			}
		});
		buttonCadastrar.setForeground(Color.WHITE);
		buttonCadastrar.setBackground(new Color(0, 139, 139));
		buttonCadastrar.setBounds(419, 538, 160, 40);
		contentPane.add(buttonCadastrar);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogin.setBounds(361, 280, 94, 40);
		contentPane.add(lblLogin);
		
		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setIcon(new ImageIcon(LoginView.class.getResource("/icones/icon-login.png")));
		lblIconLogin.setBounds(319, 275, 50, 50);
		contentPane.add(lblIconLogin);
		
		txSenha = new JPasswordField();
		txSenha.setToolTipText("Digite sua senha");
		txSenha.setBounds(229, 457, 350, 30);
		contentPane.add(txSenha);
	}
}
