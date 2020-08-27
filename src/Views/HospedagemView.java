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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import DAO.ClienteDAO;
import DAO.HospedagemDAO;
import DAO.QuartoDAO;
import DAO.ReservaDAO;
import Exceptions.DataInvalidaException;
import ValueObjects.Cliente;
import ValueObjects.Hospedagem;
import ValueObjects.Quarto;
import ValueObjects.Recepcionista;
import ValueObjects.Reserva;
import ValueObjects.TipoQuarto;
import persistencia.Banco;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class HospedagemView extends JFrame {

	private JPanel contentPane;
	private JTextField txDataEntrada;
	
	int xx, xy;
	Button buttonInserir = new Button("INSERIR");
	private JTextField txValorTotal;
	private JTextField txCliente;
	private JTextField txCodigoCliente;
	private JTextField txDataSaida;
	private JComboBox cbTipoQuarto;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private Map<Integer, TipoQuarto> mapQuartos = new HashMap<Integer, TipoQuarto>();
	private Cliente globCliente = new Cliente();
	private Recepcionista globRecepcionista = new Recepcionista();
	private TipoQuarto globTipoQuarto = new TipoQuarto();
	private Quarto globQuarto = new Quarto(globTipoQuarto);
	private Hospedagem globHospedagem = new Hospedagem(globCliente, globQuarto);
	private Reserva globReserva = new Reserva(globCliente, globRecepcionista, globQuarto);
	private double valorDiaria = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospedagemView frame = new HospedagemView();
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
	public HospedagemView() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					HospedagemDAO dao = new HospedagemDAO();
					mapQuartos = dao.listarTiposQuartos();
					cbTipoQuarto.removeAll();
					for (int i = 1; i < mapQuartos.size(); i ++) {
						cbTipoQuarto.addItem(mapQuartos.get(i).getDescricao());	
					}

				}
				catch (SQLException ex) {
				}
			}
		});
		setName("hospedagemview");
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
				HospedagemView.this.dispose(); //fecha a janela 
				new MenuView().setVisible(true); //abre a janela menu
			}
		});
		
		JLabel lbl_calculadora = new JLabel("");
		lbl_calculadora.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Runtime rt = Runtime.getRuntime();
					Process p = rt.exec("calc");
					p.waitFor();
				} catch (Exception e2) {
				}
			}
		});
		lbl_calculadora.setIcon(new ImageIcon(HospedagemView.class.getResource("/icones/icons-calculadora.png")));
		lbl_calculadora.setBounds(0, 243, 50, 40);
		contentPane.add(lbl_calculadora);
		lbl_fechar.setIcon(new ImageIcon(HospedagemView.class.getResource("/icones/icon-fechar.png")));
		lbl_fechar.setBounds(750, 240, 50, 40);
		contentPane.add(lbl_fechar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 800, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_img2 = new JLabel("");
		
		lbl_img2.setBounds(96, -36, 610, 295);
		lbl_img2.setIcon(new ImageIcon(HospedagemView.class.getResource("/imagens/img5.jpg")));
		panel.add(lbl_img2);
		
		buttonInserir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonInserir.setForeground(Color.WHITE);
		buttonInserir.setBackground(new Color(0, 139, 139));
		buttonInserir.setBounds(133, 537, 160, 40);
		contentPane.add(buttonInserir);
		
		JSeparator separator_0 = new JSeparator();
		separator_0.setBounds(40, 384, 350, 2);
		contentPane.add(separator_0);
		
		JLabel lbl_reserva = new JLabel("RESERVA");
		lbl_reserva.setBounds(40, 332, 228, 16);
		contentPane.add(lbl_reserva);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(419, 384, 350, 2);
		contentPane.add(separator_1);
		
		try {
			txDataEntrada = new JFormattedTextField(new MaskFormatter("##-##-####"));
			txDataEntrada.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calcularValorDiaria();
				}
			});
		} catch (ParseException e2) {
		}
		txDataEntrada.setText("");
		txDataEntrada.setForeground(new Color(0, 0, 0));
		txDataEntrada.setColumns(10);
		txDataEntrada.setBounds(419, 352, 160, 30);
		contentPane.add(txDataEntrada);
		
		JLabel lbl_data_entrada = new JLabel("DATA ENTRADA");
		lbl_data_entrada.setBounds(419, 332, 160, 16);
		contentPane.add(lbl_data_entrada);
		
		Button buttonEditar = new Button("EDITAR");

		buttonEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonEditar.setForeground(Color.WHITE);
		buttonEditar.setBackground(new Color(0, 139, 139));
		buttonEditar.setBounds(323, 537, 160, 40);
		contentPane.add(buttonEditar);
		
		JLabel lbl_hospedagem = new JLabel("HOSPEDAGEM");
		lbl_hospedagem.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_hospedagem.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl_hospedagem.setBounds(330, 280, 190, 40);
		contentPane.add(lbl_hospedagem);
		
		JLabel lbl_icon_hospedagem = new JLabel("");
		lbl_icon_hospedagem.setIcon(new ImageIcon(HospedagemView.class.getResource("/icones/icons-hotel.png")));
		lbl_icon_hospedagem.setBounds(290, 270, 50, 50);
		contentPane.add(lbl_icon_hospedagem);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(419, 515, 350, 2);
		contentPane.add(separator_2);
		
		txValorTotal = new JTextField();
		txValorTotal.setText("R$0,00");
		txValorTotal.setForeground(Color.BLACK);
		txValorTotal.setColumns(10);
		txValorTotal.setBounds(419, 483, 350, 30);
		contentPane.add(txValorTotal);
		
		JLabel lbl_valorTotal = new JLabel("VALOR TOTAL");
		lbl_valorTotal.setBounds(419, 463, 160, 16);
		contentPane.add(lbl_valorTotal);
		
		txCliente = new JTextField();
		txCliente.setForeground(Color.BLACK);
		txCliente.setColumns(10);
		txCliente.setBounds(40, 418, 160, 30);
		contentPane.add(txCliente);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(40, 450, 350, 2);
		contentPane.add(separator_3);
		
		JLabel lbl_cliente = new JLabel("CLIENTE");
		lbl_cliente.setBounds(40, 397, 160, 16);
		contentPane.add(lbl_cliente);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(40, 515, 350, 2);
		contentPane.add(separator_4);
		
		JLabel lbl_tipo_qto = new JLabel("TIPO QUARTO");
		lbl_tipo_qto.setBounds(40, 463, 160, 16);
		contentPane.add(lbl_tipo_qto);
		
		Button buttonDeletar = new Button("DELETAR");

		buttonDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonDeletar.setForeground(Color.WHITE);
		buttonDeletar.setBackground(new Color(0, 139, 139));
		buttonDeletar.setBounds(513, 537, 160, 40);
		contentPane.add(buttonDeletar);
		
		JLabel lbl_codCliente = new JLabel("CODIGO");
		lbl_codCliente.setBounds(230, 397, 130, 16);
		contentPane.add(lbl_codCliente);
		
		txCodigoCliente = new JTextField();
		txCodigoCliente.setForeground(Color.BLACK);
		txCodigoCliente.setColumns(10);
		txCodigoCliente.setBounds(230, 418, 160, 30);
		contentPane.add(txCodigoCliente);
		
		try {
			txDataSaida = new JFormattedTextField(new MaskFormatter("##-##-####"));
			txDataSaida.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calcularValorDiaria();
				}
			});
		} catch (ParseException e2) {
		}
		
		txDataSaida.setForeground(Color.BLACK);
		txDataSaida.setColumns(10);
		txDataSaida.setBounds(609, 352, 160, 30);
		contentPane.add(txDataSaida);
		
		JLabel lbl_data_entrada_1 = new JLabel("DATA SA\u00CDDA");
		lbl_data_entrada_1.setBounds(609, 332, 160, 16);
		contentPane.add(lbl_data_entrada_1);
		
		JComboBox cbFormaPagamento = new JComboBox();

		cbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"", "Dinheiro", "D\u00E9bito", "Cr\u00E9dito"}));
		cbFormaPagamento.setBounds(419, 418, 350, 30);
		contentPane.add(cbFormaPagamento);
		
		JLabel lbl_tipoPagto = new JLabel("FORMA PAGAMENTO");
		lbl_tipoPagto.setBounds(419, 397, 160, 16);
		contentPane.add(lbl_tipoPagto);
		
		cbTipoQuarto = new JComboBox();

		cbTipoQuarto.setBounds(40, 488, 160, 20);
		contentPane.add(cbTipoQuarto);
		
		JLabel lbl_codCliente_1 = new JLabel("QUARTO");
		lbl_codCliente_1.setBounds(230, 464, 130, 16);
		contentPane.add(lbl_codCliente_1);
		
		JLabel lbl_n_quarto = new JLabel("---");
		lbl_n_quarto.setBounds(230, 491, 130, 16);
		contentPane.add(lbl_n_quarto);
		
		JRadioButton rb_sim = new JRadioButton("Sim");

		rb_sim.setHorizontalAlignment(SwingConstants.LEFT);
		rb_sim.setBackground(SystemColor.window);
		rb_sim.setBounds(50, 354, 50, 23);
		contentPane.add(rb_sim);
		
		JRadioButton rb_nao = new JRadioButton("N\u00E3o");
		rb_nao.setSelected(true);

		rb_nao.setHorizontalAlignment(SwingConstants.LEFT);
		rb_nao.setBackground(Color.WHITE);
		rb_nao.setBounds(109, 354, 50, 23);
		contentPane.add(rb_nao);
		
		buttonInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(calcularValorDiaria()) {
					try {
						Cliente cliente = new Cliente();
						cliente.setId(Integer.valueOf(txCodigoCliente.getText()));
						cliente.setNome(String.valueOf(txCliente.getText()));
						
						TipoQuarto tipoQuarto = new TipoQuarto();
						tipoQuarto.setDescricao(String.valueOf(cbTipoQuarto.getSelectedItem()));
						
						Quarto quarto = new Quarto(tipoQuarto);
						quarto.setNumero(Integer.valueOf(lbl_n_quarto.getText()));
						
						Hospedagem hospedagem = new Hospedagem(cliente, quarto);
						hospedagem.setFormaPagamento(String.valueOf(cbFormaPagamento.getSelectedItem()));
						
						hospedagem.setReserva(globReserva);
									    				
						Date dEntrada = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.parse(txDataEntrada.getText(), format).format(dtf));
						Date dSaida = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.parse(txDataSaida.getText(), format).format(dtf));

						hospedagem.setEntrada(dEntrada);
						hospedagem.setSaida(dSaida);
						
						HospedagemDAO dao = new HospedagemDAO();
						if (dao.adiciona(hospedagem)) {
							JOptionPane.showMessageDialog(null, "Hospedagem registrada com sucesso");
						}
					}
					catch (SQLException ex) {
						ex.printStackTrace();
					} 
					catch (ParseException e1) {
						e1.printStackTrace();
					}
					catch (DataInvalidaException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Erro ao inserir devido a data, verifique as datas desejadas");
				}
			}
		});
		
		rb_sim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rb_nao.isSelected()) {
					rb_nao.setSelected(false);
					
				}
			}
		});
		
		rb_nao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rb_sim.isSelected()) {
					rb_sim.setSelected(false);
				}
			}
		});
		
		cbTipoQuarto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cbTipoQuarto.removeAll();
				for (int i = 1; i < mapQuartos.size(); i ++) {
					cbTipoQuarto.addItem(mapQuartos.get(i).getDescricao());	
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					TipoQuarto tipoQuarto = new TipoQuarto();
					QuartoDAO dao = new QuartoDAO();
					
					String criterio = " HAVING tb_tipo_quarto.descricao_quarto = '" + cbTipoQuarto.getSelectedItem() + "' AND tb_quarto.quarto_disponivel = 1 ORDER BY tb_quarto.id_quarto";
					globQuarto = dao.listarQuartosDisponiveis(criterio);
					
					if (globQuarto.getNumero() == 0 ) {
						JOptionPane.showMessageDialog(null, "Não há quartos disponíveis nesse padrão");
						lbl_n_quarto.setText("Não há quartos disponíveis nesse padrão");
					}
					else {
						lbl_n_quarto.setText(String.valueOf(globQuarto.getNumero()));
						valorDiaria = globQuarto.getValorDiaria();
					}					
				}
				catch (SQLException ex) {
				}
			}
		});
		
		txCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!txCliente.getText().trim().equals("")) {
					if (rb_sim.isSelected()) {
						try {
							ReservaDAO dao = new ReservaDAO();
							String criterio = "HAVING tb_pessoa.nome_pessoa = '" + txCliente.getText() + "' AND tb_reserva.reserva_utilizada = 0 ORDER BY tb_reserva.data_entrada_reserva;";
							globReserva = dao.listarReservaCliente(criterio);
							
							if (globReserva.getId() > 0) {
							   						    
							    String sDataEntrada = String.valueOf(globReserva.getEntrada());
							    String sDataSaida = String.valueOf(globReserva.getSaida());
							    
							    String dataEntrada = LocalDate.parse(sDataEntrada, dtf).format(format);
							    String dataSaida = LocalDate.parse(sDataSaida, dtf).format(format);
								
								txCodigoCliente.setText(String.valueOf(globReserva.getCliente().getId()));
								txDataEntrada.setText(String.valueOf(dataEntrada));
								txDataSaida.setText(String.valueOf(dataSaida));
								valorDiaria = globReserva.getQuarto().getValorDiaria();
								calcularValorDiaria();
								
								//cbFormaPagamento.addItem(String.valueOf(globReserva.getFormaPagamento()));
								cbFormaPagamento.setSelectedItem(String.valueOf(globReserva.getFormaPagamento()));
								cbTipoQuarto.setSelectedItem(String.valueOf(globReserva.getQuarto().getTipoQuarto().getDescricao()));
								lbl_n_quarto.setText(String.valueOf(globReserva.getQuarto().getNumero()));
								buttonEditar.setEnabled(false);
								buttonDeletar.setEnabled(false);
							}
							else {
								JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma reserva em aberto pra esse cliente");
								buttonEditar.setEnabled(true);
								buttonDeletar.setEnabled(true);
							}
							
						}
						catch(SQLException x) {
							x.printStackTrace();
						}
					}
					else {
						//Buscar hospedagem
						try {
							String criterio = null;
							ClienteDAO clienteDAO = new ClienteDAO();
							criterio = " WHERE tb_pessoa.nome_pessoa = '" + txCliente.getText() +"'";
							if(clienteDAO.buscaCliente(criterio)) {
								criterio = " HAVING tb_pessoa.nome_pessoa = '" + txCliente.getText() + "' AND tb_hospedagem.hospedagem_utilizada = 0 ORDER BY tb_hospedagem.data_entrada_hospedagem  ASC;";
								HospedagemDAO dao = new HospedagemDAO();
								globHospedagem = dao.buscaHospedagemCliente(criterio);
								
								if (globHospedagem.getId() > 0) {
		   						    
								    String sDataEntrada = String.valueOf(globHospedagem.getEntrada());
								    String sDataSaida = String.valueOf(globHospedagem.getSaida());
								    
								    String dataEntrada = LocalDate.parse(sDataEntrada, dtf).format(format);
								    String dataSaida = LocalDate.parse(sDataSaida, dtf).format(format);
									
									txCodigoCliente.setText(String.valueOf(globHospedagem.getCliente().getId()));
									txDataEntrada.setText(String.valueOf(dataEntrada));
									txDataSaida.setText(String.valueOf(dataSaida));
									valorDiaria = globHospedagem.getQuarto().getValorDiaria();
									calcularValorDiaria();
									
									cbFormaPagamento.addItem(String.valueOf(globHospedagem.getFormaPagamento()));
									cbFormaPagamento.setSelectedItem(String.valueOf(globHospedagem.getFormaPagamento()));
									cbTipoQuarto.addItem(String.valueOf(globHospedagem.getQuarto().getTipoQuarto().getDescricao()));
									lbl_n_quarto.setText(String.valueOf(globHospedagem.getQuarto().getNumero()));
												
									buttonInserir.setEnabled(false);
									buttonEditar.setEnabled(true);
									buttonDeletar.setEnabled(true);
								}
								else {
									JOptionPane.showMessageDialog(null, "Hospedagem não encontrada pra esse cliente");
									buttonEditar.setEnabled(false);
									buttonDeletar.setEnabled(false);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com esse nome, caso não seja cadastrado favor seguir com o cadastro do cliente");
							}
							
						}
						catch( SQLException | DataInvalidaException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				txCliente.setText("");
			}
		});
		txCodigoCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (!txCodigoCliente.getText().trim().equals("")) {
					if (rb_sim.isSelected()) {
						try {
							ReservaDAO dao = new ReservaDAO();
							String criterio = "HAVING tb_cliente.id_cliente = '" + txCodigoCliente.getText().trim() + "'AND tb_reserva.reserva_utilizada = 0 ORDER BY tb_reserva.data_entrada_reserva;";
							globReserva = dao.listarReservaCliente(criterio);
							
							if (globReserva.getId() > 0) {						   						    
							    String sDataEntrada = String.valueOf(globReserva.getEntrada());
							    String sDataSaida = String.valueOf(globReserva.getSaida());
							    
							    String dataEntrada = LocalDate.parse(sDataEntrada, dtf).format(format);
							    String dataSaida = LocalDate.parse(sDataSaida, dtf).format(format);
							   							
								txCliente.setText(String.valueOf(globReserva.getCliente().getNome()));
								txDataEntrada.setText(String.valueOf(dataEntrada));
								txDataSaida.setText(String.valueOf(dataSaida));
								valorDiaria = globReserva.getQuarto().getValorDiaria();
								calcularValorDiaria();
								
								cbFormaPagamento.addItem(String.valueOf(globReserva.getFormaPagamento()));
								cbFormaPagamento.setSelectedItem(String.valueOf(globReserva.getFormaPagamento()));
								cbTipoQuarto.addItem(String.valueOf(globReserva.getQuarto().getTipoQuarto().getDescricao()));
								lbl_n_quarto.setText(String.valueOf(globReserva.getQuarto().getNumero()));
								
								buttonInserir.setEnabled(true);
								buttonDeletar.setEnabled(false);
								buttonEditar.setEnabled(false);
							}
							else {
								JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma reserva em aberto pra esse cliente");
								
								buttonInserir.setEnabled(true);
								buttonEditar.setEnabled(true);
								buttonDeletar.setEnabled(true);
							}
							
						}
						catch(SQLException x) {
							x.printStackTrace();
						}
					}
					else {
						//Buscar hospedagem
						try {
							ClienteDAO clienteDAO = new ClienteDAO();
							String criterio;
							criterio = " WHERE tb_hospedagem.id_cliente = '" + txCodigoCliente.getText() +"'";
							if(clienteDAO.buscaCliente(criterio)) {
								criterio = " HAVING tb_hospedagem.id_cliente = '" + txCodigoCliente.getText().trim() + "' AND tb_hospedagem.hospedagem_utilizada = 0 ORDER BY tb_hospedagem.data_entrada_hospedagem ASC;";
								HospedagemDAO dao = new HospedagemDAO();
								globHospedagem = dao.buscaHospedagemCliente(criterio);
																    
							    String sDataEntrada = String.valueOf(globHospedagem.getEntrada());
							    String sDataSaida = String.valueOf(globHospedagem.getSaida());
							    
							    String dataEntrada = LocalDate.parse(sDataEntrada, dtf).format(format);
							    String dataSaida = LocalDate.parse(sDataSaida, dtf).format(format);
							    
								if (globHospedagem.getId() > 0) {							
									txCodigoCliente.setText(String.valueOf(globHospedagem.getCliente().getId()));
									txCliente.setText(String.valueOf(globHospedagem.getCliente().getNome()));
									txDataEntrada.setText(String.valueOf(dataEntrada));
									txDataSaida.setText(String.valueOf(dataSaida));
									valorDiaria = globHospedagem.getQuarto().getValorDiaria();
									calcularValorDiaria();
									
									cbFormaPagamento.addItem(String.valueOf(globHospedagem.getFormaPagamento()));
									cbFormaPagamento.setSelectedItem(String.valueOf(globHospedagem.getFormaPagamento()));
									cbTipoQuarto.addItem(String.valueOf(globHospedagem.getQuarto().getTipoQuarto().getDescricao()));
									lbl_n_quarto.setText(String.valueOf(globHospedagem.getQuarto().getNumero()));
												
									buttonEditar.setEnabled(true);
									buttonDeletar.setEnabled(true);
								}
								else {
									JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma hospedagem em aberto pra esse cliente");
									buttonEditar.setEnabled(false);
									buttonDeletar.setEnabled(false);
								}
							}
						}
						catch( SQLException | DataInvalidaException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		
		cbFormaPagamento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularValorDiaria();
			}
		});
		
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
				HospedagemView.this.setLocation(x - xx, y - xy);
			}
		});

		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date dataEntrada = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(txDataEntrada.getText()));
					Date dataSaida = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(txDataSaida.getText()));
					
					globHospedagem.setEntrada(dataEntrada);
					globHospedagem.setSaida(dataSaida);
					globHospedagem.setFormaPagamento((String) cbFormaPagamento.getSelectedItem());
					globHospedagem.getQuarto().setNumero(Integer.valueOf(lbl_n_quarto.getText()));
					
					HospedagemDAO dao = new HospedagemDAO();
					
					if(dao.atualiza(globHospedagem)) {
						JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
					}
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (DataInvalidaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		buttonDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HospedagemDAO dao = new HospedagemDAO();
					dao.remove(globHospedagem);
					JOptionPane.showMessageDialog(null, "Hospedagem excluída com sucesso");
					limparCampos();
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		
	}
	
	private void limparCampos() {
		txCliente.setText("");
		txCodigoCliente.setText("");
		txDataEntrada.setText("");
		txDataSaida.setText("");
		txValorTotal.setText("R$00,00");
		cbTipoQuarto.setSelectedItem("");
	}
	private boolean calcularValorDiaria () {
		boolean sucesso = false;
		if(!txDataEntrada.getText().equals("  -  -    ") && !txDataSaida.getText().equals("  -  -    ")) {
			try {
				Date dataEntrada = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(txDataEntrada.getText()));
				Date dataSaida = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(txDataSaida.getText()));
				
				if(dataSaida.getTime() < dataEntrada.getTime()) {
					JOptionPane.showMessageDialog(null, "A data de entrada dever ser anterior a data de saída");
					buttonInserir.setEnabled(false);
					
					sucesso = false;
				}
				else {
					long diffInMillies = Math.abs(dataSaida.getTime() - dataEntrada.getTime());
				    long totalDias = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				    
				    if (totalDias == 0) {
				    	totalDias += 1;
				    }
				    double valorTotal = totalDias * valorDiaria;
				    txValorTotal.setText(String.valueOf(valorTotal));
				    buttonInserir.setEnabled(false);
				    sucesso = true;
				}

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return sucesso;
	}
}
