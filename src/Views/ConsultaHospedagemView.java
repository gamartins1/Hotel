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
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.HospedagemDAO;
import Exceptions.DataInvalidaException;
import ValueObjects.Cliente;
import ValueObjects.Hospedagem;
import ValueObjects.Quarto;
import ValueObjects.TipoQuarto;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ConsultaHospedagemView extends JFrame {

	private JPanel contentPane;
	
	int xx, xy;
	private JTable tbHospedagens;
	private JTextField txCliente;

	DateTimeFormatter formatBanco = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatVisualizacao = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private TipoQuarto globTipoQuarto = new TipoQuarto();
	private Quarto globQuarto = new Quarto(globTipoQuarto);
	private Cliente globCliente = new Cliente();
	private Hospedagem globHospedagem = new Hospedagem(globCliente, globQuarto);
	
	private List<Hospedagem> globListHospedagem;

	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaHospedagemView frame = new ConsultaHospedagemView();
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
	public ConsultaHospedagemView() {
		
		setName("consultahospedagemview");
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
				ConsultaHospedagemView.this.dispose(); //fecha a janela 
				new MenuView().setVisible(true); //abre a janela menu
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 349, 677, 195);
		contentPane.add(scrollPane);
		
		tbHospedagens = new JTable();
		scrollPane.setViewportView(tbHospedagens);
		tbHospedagens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedagens.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cliente", "Tipo Quarto", "Data Entrada", "Data Saida", "Qtd. Dias", "Valor Total", "Hosp. Utilizada"
			}
		));
		model = (DefaultTableModel) tbHospedagens.getModel();
		tbHospedagens.getColumnModel().getColumn(0).setPreferredWidth(215);
		tbHospedagens.getColumnModel().getColumn(1).setPreferredWidth(103);
		tbHospedagens.getColumnModel().getColumn(3).setPreferredWidth(67);
		tbHospedagens.getColumnModel().getColumn(4).setPreferredWidth(59);
		tbHospedagens.getColumnModel().getColumn(5).setPreferredWidth(64);
		tbHospedagens.getColumnModel().getColumn(6).setPreferredWidth(82);
		tbHospedagens.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_fechar.setIcon(new ImageIcon(ConsultaHospedagemView.class.getResource("/icones/icon-fechar.png")));
		lbl_fechar.setBounds(750, 240, 50, 40);
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
				ConsultaHospedagemView.this.setLocation(x - xx, y - xy);
			}
		});
		lbl_img1.setBounds(96, -124, 610, 423);
		lbl_img1.setIcon(new ImageIcon(ConsultaHospedagemView.class.getResource("/imagens/img7.jpg")));
		panel.add(lbl_img1);
		
		Button buttonBuscas = new Button("BUSCAR");

		buttonBuscas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonBuscas.setForeground(Color.WHITE);
		buttonBuscas.setBackground(new Color(0, 139, 139));
		buttonBuscas.setBounds(229, 550, 160, 40);
		contentPane.add(buttonBuscas);
		
		Button buttonImprimir = new Button("IMPRIMIR");
		buttonImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonImprimir.setForeground(Color.WHITE);
		buttonImprimir.setBackground(new Color(0, 139, 139));
		buttonImprimir.setBounds(419, 550, 160, 40);
		contentPane.add(buttonImprimir);
		
		JLabel lblLogin = new JLabel("CONSULTA HOSPEDAGEM");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogin.setBounds(271, 279, 320, 40);
		contentPane.add(lblLogin);
		
		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setIcon(new ImageIcon(ConsultaHospedagemView.class.getResource("/icones/icons-resultado.png")));
		lblIconLogin.setBounds(229, 274, 50, 50);
		contentPane.add(lblIconLogin);
		
		txCliente = new JTextField();
		txCliente.setToolTipText("Digite seu usuario");
		txCliente.setForeground(Color.BLACK);
		txCliente.setColumns(10);
		txCliente.setBounds(679, 367, 120, 30);
		contentPane.add(txCliente);
		
		JSeparator separator_0_1 = new JSeparator();
		separator_0_1.setBounds(680, 395, 120, 2);
		contentPane.add(separator_0_1);
		
		JLabel lblCodigoReserva = new JLabel("CLIENTE");
		lblCodigoReserva.setToolTipText("Digite o nome ou o c\u00F3digo do cliente");
		lblCodigoReserva.setBounds(687, 349, 50, 16);
		contentPane.add(lblCodigoReserva);
		
		JLabel lbl_ReservaTotal = new JLabel("TOTAL HOSPEDAGEM");
		lbl_ReservaTotal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbl_ReservaTotal.setBounds(679, 454, 120, 16);
		contentPane.add(lbl_ReservaTotal);
		
		JLabel lbl_total_reservas = new JLabel("---");
		lbl_total_reservas.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		lbl_total_reservas.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbl_total_reservas.setBounds(680, 481, 110, 16);
		contentPane.add(lbl_total_reservas);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					popularCamposHospedagem(" ORDER BY tb_hospedagem.data_entrada_hospedagem ASC");
					lbl_total_reservas.setText(String.valueOf(model.getRowCount()));
				}
				catch (SQLException | ParseException | DataInvalidaException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		buttonBuscas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String criterio;
				if(txCliente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum cliente inserido pra realizar a busca, a lista de hospedagens será atualizada...");
					criterio = " ORDER BY tb_hospedagem.data_entrada_hospedagem ASC";
				}
				else {
					criterio = " HAVING tb_pessoa.nome_pessoa = '" + txCliente.getText() + "%' OR tb_cliente.id_cliente = '" + txCliente.getText() + "' ORDER BY tb_hospedagem.data_entrada_hospedagem ASC";
				}
				try {
					popularCamposHospedagem(criterio);
					lbl_total_reservas.setText(String.valueOf(model.getRowCount()));
				}
				catch (SQLException | ParseException | DataInvalidaException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	private void popularCamposHospedagem(String criterio) throws SQLException, ParseException, DataInvalidaException {
		
		HospedagemDAO dao = new HospedagemDAO();
		
		globListHospedagem = dao.listarHospedagens(criterio);
		model.setRowCount(0);
		/*
		for (int i = 0; i < model.getRowCount(); i ++) {
			model.removeRow(i);
		}
		*/
		if (globListHospedagem.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma hospedagem foi encontrada");
		}
		else {
			for (int i = 0; i < globListHospedagem.size(); i++) {
				String dataEntrada = null;
				String dataSaida = null;
				 String sDataEntrada = null, sDataSaida = null;
				 long diffInMillies;
				 long totalDias;
				 double valorTotal;
				 
				if (globListHospedagem.get(i).getEntrada() != null || globListHospedagem.get(i).getEntrada() != null) {
					dataEntrada = String.valueOf(globListHospedagem.get(i).getEntrada());	
					dataSaida = String.valueOf(globListHospedagem.get(i).getSaida());
					
				    sDataEntrada = LocalDate.parse(dataEntrada, formatBanco).format(formatVisualizacao);
				    sDataSaida = LocalDate.parse(dataSaida, formatBanco).format(formatVisualizacao);
				    
				    Date dDataEntrada = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(sDataEntrada));
					Date dDataSaida = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(sDataSaida));
				    
					diffInMillies = Math.abs(dDataSaida.getTime() - dDataEntrada.getTime());
				    totalDias = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				    
				    if (totalDias == 0) {
				    	totalDias ++;
				    }
				    valorTotal = totalDias * globListHospedagem.get(i).getQuarto().getValorDiaria();
				}
				else {
					totalDias = 0;
					valorTotal = 0;
				}
				String utilizada = "Não utilizada";
				
				if(globHospedagem.isHospedagem_utilizada()) {
					utilizada = "Utilizada";
				}
				Object[] linha = {globListHospedagem.get(i).getCliente().getNome(), globListHospedagem.get(i).getQuarto().getTipoQuarto().getDescricao(), 
						sDataEntrada, sDataSaida, totalDias, valorTotal, utilizada};
				 
				model.addRow(linha);
			}
		}	
	}
	
}
