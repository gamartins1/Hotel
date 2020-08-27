package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URI;

import javax.swing.border.LineBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Window.Type;

public class MenuView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //inserido automatico pra não dar erro

	private JPanel contentPane;
	
	int xx, xy;
	
	public void OpenInternet() {
		try {
			Desktop.getDesktop().browse(URI.create("www.google.com"));
		}
		catch (Exception e) {
			JOptionPane.showConfirmDialog(this, e);
	}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
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
	public MenuView() {
		setName("menuview");
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
				int result = JOptionPane.showConfirmDialog(null, "Deseja sair do Menu?", "Exit", dialog);
				if(result == 0) {
					MenuView.this.dispose(); //fecha a janela 
					new LoginView().setVisible(true); //abre a janela login
				}	
			}
		});
		lbl_fechar.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icon-fechar.png")));
		lbl_fechar.setBounds(750, 240, 50, 40);
		contentPane.add(lbl_fechar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 800, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_img2 = new JLabel("");
		lbl_img2.setHorizontalAlignment(SwingConstants.CENTER);
		
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
				MenuView.this.setLocation(x - xx, y - xy);
			}
		});
		lbl_img2.setBounds(96, -36, 610, 295);
		lbl_img2.setIcon(new ImageIcon(MenuView.class.getResource("/imagens/img6.jpg")));
		panel.add(lbl_img2);
		
		JLabel lbl_menu = new JLabel("MENU");
		lbl_menu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_menu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl_menu.setBounds(355, 280, 92, 40);
		contentPane.add(lbl_menu);
		
		JLabel lbl_icon_menu = new JLabel("");
		lbl_icon_menu.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-menu.png")));
		lbl_icon_menu.setBounds(313, 275, 50, 50);
		contentPane.add(lbl_icon_menu);
		
		JLabel lbl_menu_cliente = new JLabel("");
		lbl_menu_cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_menu_cliente.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,255,255)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuView.this.dispose();
				new ClienteView().setVisible(true); //abre a janela cadastro cliente
			}
		});
		lbl_menu_cliente.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				lbl_menu_cliente.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139,139,139)));
			}
		});
		lbl_menu_cliente.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-cliente.png")));
		lbl_menu_cliente.setBorder(null);
		lbl_menu_cliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_menu_cliente.setBounds(104, 342, 160, 100);
		contentPane.add(lbl_menu_cliente);
		
		JLabel lbl_menu_recepcionista = new JLabel("");
		lbl_menu_recepcionista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_menu_recepcionista.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,255,255)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new RecepcionistaView().setVisible(true); //abre a janela cadastro recepcionista
			}
		});
		lbl_menu_recepcionista.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lbl_menu_recepcionista.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139,139,139)));
			}
		});
		lbl_menu_recepcionista.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-recepcao.png")));
		lbl_menu_recepcionista.setBorder(null);
		lbl_menu_recepcionista.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_menu_recepcionista.setBounds(298, 342, 160, 100);
		contentPane.add(lbl_menu_recepcionista);
		
		JLabel lbl_menu_veiculo = new JLabel("");
		lbl_menu_veiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_menu_veiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,255,255)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new VeiculoView().setVisible(true); //abre a janela cadastro veiculo
			}
		});
		lbl_menu_veiculo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lbl_menu_veiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139,139,139)));
			}
		});
		lbl_menu_veiculo.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-veiculo.png")));
		lbl_menu_veiculo.setBorder(null);
		lbl_menu_veiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_menu_veiculo.setBounds(104, 480, 160, 100);
		contentPane.add(lbl_menu_veiculo);
		
		JLabel lbl_menu_hosp = new JLabel("");
		lbl_menu_hosp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_menu_hosp.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,255,255)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new HospedagemView().setVisible(true); //abre a janela cadastro hospedagem
			}
		});
		lbl_menu_hosp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lbl_menu_hosp.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139,139,139)));
			}
		});
		lbl_menu_hosp.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-hotel.png")));
		lbl_menu_hosp.setBorder(null);
		lbl_menu_hosp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_menu_hosp.setBounds(298, 480, 160, 100);
		contentPane.add(lbl_menu_hosp);
		
		JLabel lbl_consultaHosp = new JLabel("");
		lbl_consultaHosp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_consultaHosp.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,255,255)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new ConsultaHospedagemView().setVisible(true); //abre a janela consulta hospedagem
			}
		});
		lbl_consultaHosp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lbl_consultaHosp.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139,139,139)));
			}
		});
		lbl_consultaHosp.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-resultado.png")));
		lbl_consultaHosp.setBorder(null);
		lbl_consultaHosp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_consultaHosp.setBounds(492, 480, 160, 100);
		contentPane.add(lbl_consultaHosp);
		
		JLabel lbllabel_cliente = new JLabel("CLIENTE");
		lbllabel_cliente.setBorder(null);
		lbllabel_cliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_cliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllabel_cliente.setBounds(109, 414, 140, 20);
		contentPane.add(lbllabel_cliente);
		
		JLabel lbllabel_recepcionista = new JLabel("RECEPCIONISTA");
		lbllabel_recepcionista.setBorder(null);
		lbllabel_recepcionista.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_recepcionista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllabel_recepcionista.setBounds(307, 414, 140, 20);
		contentPane.add(lbllabel_recepcionista);
		
		JLabel lbllabel_veiculo = new JLabel("VEICULO");
		lbllabel_veiculo.setBorder(null);
		lbllabel_veiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_veiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllabel_veiculo.setBounds(143, 553, 80, 20);
		contentPane.add(lbllabel_veiculo);
		
		JLabel lbllabel_hospedagem = new JLabel("HOSPEDAGEM");
		lbllabel_hospedagem.setBorder(null);
		lbllabel_hospedagem.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_hospedagem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllabel_hospedagem.setBounds(303, 553, 140, 20);
		contentPane.add(lbllabel_hospedagem);
		
		JLabel lbllabel_consulta = new JLabel("CONSULTA HOSPEDA");
		lbllabel_consulta.setBorder(null);
		lbllabel_consulta.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_consulta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllabel_consulta.setBounds(501, 553, 150, 20);
		contentPane.add(lbllabel_consulta);
		
		JLabel lbl_internet = new JLabel("");
		lbl_internet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OpenInternet();
			}
		});
		lbl_internet.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-internet.png")));
		lbl_internet.setBounds(10, 244, 50, 40);
		contentPane.add(lbl_internet);
		
		JLabel lbl_util_3 = new JLabel("");
		lbl_util_3.setBounds(0, 433, 50, 40);
		contentPane.add(lbl_util_3);
		
		JLabel lbl_consultaReserva = new JLabel("");
		lbl_consultaReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				lbl_consultaReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,255,255)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new ConsultaReservaView().setVisible(true); //abre a janela consulta reserva
			}
		});
		lbl_consultaReserva.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				lbl_consultaReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139,139,139)));
			}
		});
		lbl_consultaReserva.setIcon(new ImageIcon(MenuView.class.getResource("/icones/icons-resultado.png")));
		lbl_consultaReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_consultaReserva.setBorder(null);
		lbl_consultaReserva.setBounds(492, 342, 160, 100);
		contentPane.add(lbl_consultaReserva);
		
		JLabel lbllabel_consultaReserva = new JLabel("CONSULTA RESERVA");
		lbllabel_consultaReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabel_consultaReserva.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllabel_consultaReserva.setBorder(null);
		lbllabel_consultaReserva.setBounds(501, 415, 150, 20);
		contentPane.add(lbllabel_consultaReserva);
	}
}
