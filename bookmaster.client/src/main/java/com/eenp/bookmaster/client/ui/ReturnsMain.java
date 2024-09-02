package com.eenp.bookmaster.client.ui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.controller.LoanController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Book;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Loan;
import com.eenp.bookmaster.client.util.Functions;

public class ReturnsMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private final LoanController loanController = new LoanController();

	Functions func = new Functions();
	private JButton btnGuardar;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JTextField txtCliente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnsMain frame = new ReturnsMain();
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
	public ReturnsMain() {
		setTitle("BookMaster | 1.0 | Devoluciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 768, 500);
        setLocationRelativeTo(null);

        initialize();
	}

	@SuppressWarnings("unchecked")
	private void cargarDatosPrestamosByCliente(String cliente) throws ParseException, URISyntaxException, IOException {
		ApiResponse<?> response = loanController.getPrestamosByCliente(cliente);
    	if(response.getHttpResponse().getStatusCode() == 200) {
			List<Loan> prestamos = (List<Loan>) response.getResponse();
    		tableModel.setNumRows(0);
    		for (Loan loan : prestamos) {
				tableModel.addRow(new Object[]{
            		loan.getId(),
            		loan.getId_cliente().getId(),
            		loan.getId_cliente().getNombre(),
            		loan.getId_libro().getId(),
            		loan.getId_libro().getTitulo(),
            		loan.getFechaPrestamo()
                });
            }
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("ID_cliente");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("ID_libro");
        tableModel.addColumn("Titulo");
        tableModel.addColumn("FechaPrestamo");

        table = new JTable(tableModel);
        
        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(0).setMaxWidth(0);						//id_prestamo
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setMaxWidth(0);						//id_cliente
        columnModel.getColumn(1).setMinWidth(0);
        columnModel.getColumn(1).setPreferredWidth(0);
        columnModel.getColumn(3).setMaxWidth(0);						//id_libro
        columnModel.getColumn(3).setMinWidth(0);
        columnModel.getColumn(3).setPreferredWidth(0);
        
        JScrollPane scrollPane = new JScrollPane(table);
		
		btnGuardar = new JButton(" Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(validarCampos()) {
        			try {
						guardarInformacion();
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
			}
		});
		btnGuardar.setIcon(new ImageIcon(UserMain.class.getResource("/com/eenp/bookmaster/client/images/Save2.png")));
		
		btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(UserMain.class.getResource("/com/eenp/bookmaster/client/images/exit2.png")));
		
		lblNewLabel = new JLabel("Buscar por Cliente:");
		
		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!txtCliente.getText().equals("")) {
						try {
							cargarDatosPrestamosByCliente(txtCliente.getText());
						} catch (ParseException | URISyntaxException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		txtCliente.setColumns(10);
        
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 551, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(58))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void guardarInformacion() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		int filaSel = table.getSelectedRow();
		Client client = new Client();
		client.setId(Integer.parseInt(table.getValueAt(filaSel,1).toString()));
		
		Book book = new Book();
		book.setId(Integer.parseInt(table.getValueAt(filaSel,3).toString()));

		Loan loan = new Loan();
		loan.setId(Integer.parseInt(table.getValueAt(filaSel,0).toString()));
		loan.setId_cliente(client);
		loan.setId_libro(book);
		
		ApiResponse<?> response = loanController.setPrestamoUpdate(loan);
		if(response.getHttpResponse().getStatusCode() == 200) {
    		func.showMSG("OK","El Prestamo se registró correctamente.","BookMaster...");
    		limpiarCampos();
    		return;
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			limpiarCampos();
			return;
		}
	}

	private void limpiarCampos() {
		tableModel.setNumRows(0);
		txtCliente.setText("");
	}

	private boolean validarCampos() {
		if(table.getSelectedRow() != -1) {
			return true;
		}else {
			func.showMSG("ERROR","Debe seleccionar un prestamo de la lista.","Prestamos...");
			return false;
		}
	}

}
