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
import DAO.ReservaDAO;
import Exceptions.DataInvalidaException;
import ValueObjects.Reserva;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Window.Type;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;

public class ConsultaReservaView extends JFrame {

	private JPanel contentPane;
	
	int xx, xy;
	private JTable table;
	private JTable tbReservas;
	private JTextField txCliente;

	DefaultTableModel model;
	
	DateTimeFormatter formatBanco = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatVisualizacao = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private List<Reserva> globLstReservas = new ArrayList<Reserva>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaReservaView frame = new ConsultaReservaView();
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
	public ConsultaReservaView() {

		setName("consultareservaview");
		setUndecorated(true);
		setType(Type.UTILITY);
		setResizable(false);
		setMinimumSize(new Dimension(860, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lbl_fechar = new JLabel("x");
		lbl_fechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConsultaReservaView.this.dispose(); //fecha a janela 
				new MenuView().setVisible(true); //abre a janela menu
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 349, 749, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		tbReservas = new JTable();
		scrollPane.setViewportView(tbReservas);
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cliente", "Tipo Quarto", "Data Entrada", "Data Saida", "Qtd. Dias", "Valor Total", "Res. Utilizada", "Forma Pgto."
			}
		));
		tbReservas.getColumnModel().getColumn(0).setPreferredWidth(215);
		tbReservas.getColumnModel().getColumn(1).setPreferredWidth(103);
		tbReservas.getColumnModel().getColumn(3).setPreferredWidth(67);
		tbReservas.getColumnModel().getColumn(4).setPreferredWidth(59);
		tbReservas.getColumnModel().getColumn(5).setPreferredWidth(64);
		tbReservas.getColumnModel().getColumn(6).setPreferredWidth(82);
		model = (DefaultTableModel) tbReservas.getModel();
		tbReservas.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_fechar.setIcon(new ImageIcon(ConsultaReservaView.class.getResource("/icones/icon-fechar.png")));
		lbl_fechar.setBounds(800, 237, 50, 40);
		contentPane.add(lbl_fechar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 850, 237);
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
				ConsultaReservaView.this.setLocation(x - xx, y - xy);
			}
		});
		lbl_img1.setBounds(96, -124, 610, 423);
		lbl_img1.setIcon(new ImageIcon(ConsultaReservaView.class.getResource("/imagens/img7.jpg")));
		panel.add(lbl_img1);
		
		Button buttonBuscas = new Button("BUSCAR");

		buttonBuscas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonBuscas.setForeground(Color.WHITE);
		buttonBuscas.setBackground(new Color(0, 139, 139));
		buttonBuscas.setBounds(329, 537, 160, 40);
		contentPane.add(buttonBuscas);
		
		JLabel lblLogin = new JLabel("CONSULTA RESERVA");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogin.setBounds(271, 279, 290, 40);
		contentPane.add(lblLogin);
		
		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setIcon(new ImageIcon(ConsultaReservaView.class.getResource("/icones/icons-resultado.png")));
		lblIconLogin.setBounds(229, 274, 50, 50);
		contentPane.add(lblIconLogin);
		
		txCliente = new JTextField();
		txCliente.setToolTipText("Digite seu usuario");
		txCliente.setForeground(Color.BLACK);
		txCliente.setColumns(10);
		txCliente.setBounds(750, 371, 100, 30);
		contentPane.add(txCliente);
		
		JSeparator separator_0_1 = new JSeparator();
		separator_0_1.setBounds(740, 399, 120, 2);
		contentPane.add(separator_0_1);
		
		JLabel lblCodigoReserva = new JLabel("CLIENTE");
		lblCodigoReserva.setBounds(759, 355, 120, 16);
		contentPane.add(lblCodigoReserva);
		
		JLabel lbl_ReservaTotal = new JLabel("TOTAL RESERVAS");
		lbl_ReservaTotal.setBounds(750, 458, 120, 16);
		contentPane.add(lbl_ReservaTotal);
		
		JLabel lbl_n_reservas = new JLabel("---");
		lbl_n_reservas.setBounds(750, 490, 120, 16);
		contentPane.add(lbl_n_reservas);
		
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					popularCamposReserva(" ORDER BY tb_reserva.data_entrada_reserva ASC");
					lbl_n_reservas.setText(String.valueOf(model.getRowCount()));
				}
				catch (SQLException | ParseException | DataInvalidaException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		buttonBuscas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txCliente.getText().equals("")) {
						popularCamposReserva("  ORDER BY tb_reserva.data_entrada_reserva ASC");
						lbl_n_reservas.setText(String.valueOf(model.getRowCount()));
					}
					else {
						popularCamposReserva(" HAVING tb_pessoa.nome_pessoa LIKE '" + txCliente.getText()+ "%' OR tb_reserva.id_cliente_reserva = '" + txCliente.getText().trim() + "' ORDER BY tb_reserva.data_entrada_reserva ASC");
						lbl_n_reservas.setText(String.valueOf(model.getRowCount()));
					}
				}
				catch (SQLException | ParseException | DataInvalidaException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	private void popularCamposReserva(String criterio) throws SQLException, ParseException, DataInvalidaException {
		
		ReservaDAO dao = new ReservaDAO();
		
		globLstReservas = dao.buscaReservas(criterio);
		model.setRowCount(0);
		
		if (globLstReservas.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma hospedagem foi encontrada");
		}
		else {
			for (int i = 0; i < globLstReservas.size(); i++) {
				String dataEntrada = null;
				String dataSaida = null;
				String sDataEntrada = null, sDataSaida = null;
				long diffInMillies;
				long totalDias;
				double valorTotal;
				 
				if (globLstReservas.get(i).getEntrada() != null || globLstReservas.get(i).getEntrada() != null) {
					dataEntrada = String.valueOf(globLstReservas.get(i).getEntrada());	
					dataSaida = String.valueOf(globLstReservas.get(i).getSaida());
					
				    sDataEntrada = LocalDate.parse(dataEntrada, formatBanco).format(formatVisualizacao);
				    sDataSaida = LocalDate.parse(dataSaida, formatBanco).format(formatVisualizacao);
				    
				    Date dDataEntrada = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(sDataEntrada));
					Date dDataSaida = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(sDataSaida));
				    
					diffInMillies = Math.abs(dDataSaida.getTime() - dDataEntrada.getTime());
				    totalDias = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				    
				    if (totalDias == 0) {
				    	totalDias ++;
				    }
				    valorTotal = totalDias * globLstReservas.get(i).getQuarto().getValorDiaria();
				}
				else {
					totalDias = 0;
					valorTotal = 0;
				}
				String utilizada = "Não utilizada";
				
				if(globLstReservas.get(i).isUtilizada()) {
					utilizada = "Utilizada";
				}
				Object[] linha = {globLstReservas.get(i).getCliente().getNome(), globLstReservas.get(i).getQuarto().getTipoQuarto().getDescricao(), 
						sDataEntrada, sDataSaida, totalDias, valorTotal, utilizada, globLstReservas.get(i).getFormaPagamento()};
				 
				model.addRow(linha);
			}
		}	
	}
}
