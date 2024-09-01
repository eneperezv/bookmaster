package com.eenp.bookmaster.client.ui;

import java.awt.Dimension;

/*Incluir documentacion*/

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
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

import org.apache.http.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.controller.BookController;
import com.eenp.bookmaster.client.controller.ClientController;
import com.eenp.bookmaster.client.controller.LoanController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Author;
import com.eenp.bookmaster.client.entity.Book;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Loan;
import com.eenp.bookmaster.client.entity.Publisher;
import com.eenp.bookmaster.client.util.Functions;

public class LoanMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable tableLibros;
    private JTable tableClientes;
    private DefaultTableModel tableModelLibros;
    private DefaultTableModel tableModelClientes;
    
    private final BookController bookController = new BookController();
    private final ClientController clientController = new ClientController();
    private final LoanController loanController = new LoanController();
	
	Functions func = new Functions();
	private JButton btnGuardar;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JTextField txtConsultaCliente;
	private JTextField txtConsultaLibroAutor;
	private JLabel lblNewLabel_1;
	private JTextField txtConsultaLibroTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanMain frame = new LoanMain();
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
	public LoanMain() {
		setTitle("BookMaster | 1.0 | Prestamos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 510);
        setLocationRelativeTo(null);

        initialize();
        try {
			cargarDatosLibros();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarDatosLibros() {
		// TODO Auto-generated method stub
		
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		tableModelLibros = new DefaultTableModel();
		tableModelLibros.addColumn("ID");
		tableModelLibros.addColumn("ID_autor");
		tableModelLibros.addColumn("Autor");
		tableModelLibros.addColumn("Titulo");
		tableModelLibros.addColumn("ID_editorial");
		tableModelLibros.addColumn("Editorial");
		tableModelLibros.addColumn("AñoPublicacion");
		tableModelLibros.addColumn("Disponible");
        tableLibros = new JTable(tableModelLibros);
        tableLibros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("fila-sel->"+tableLibros.getSelectedRow());
				//System.out.println("col-sel->"+tableLibros.getSelectedColumn());
			}
		});
        
        TableColumnModel columnModel = tableLibros.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(0); 						//id_libro
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setMaxWidth(0); 						//id_autor
        columnModel.getColumn(1).setMinWidth(0);
        columnModel.getColumn(1).setPreferredWidth(0);
        columnModel.getColumn(4).setMaxWidth(0); 						//id_editorial
        columnModel.getColumn(4).setMinWidth(0);
        columnModel.getColumn(4).setPreferredWidth(0);
        columnModel.getColumn(6).setMaxWidth(0); 						//AñoPublicacion
        columnModel.getColumn(6).setMinWidth(0);
        columnModel.getColumn(6).setPreferredWidth(0);
        
        tableModelClientes = new DefaultTableModel();
        tableModelClientes.addColumn("ID");
        tableModelClientes.addColumn("Nombre");
        tableModelClientes.addColumn("Correo Electrónico");
        tableModelClientes.addColumn("Direccion");
        tableModelClientes.addColumn("Telefono");
        tableClientes = new JTable(tableModelClientes);
        
        TableColumnModel columnModelClientes = tableClientes.getColumnModel();
        columnModelClientes.getColumn(0).setMaxWidth(0); 				//id_cliente
        columnModelClientes.getColumn(0).setMinWidth(0);
        columnModelClientes.getColumn(0).setPreferredWidth(0);
        columnModelClientes.getColumn(3).setMaxWidth(0); 				//direccion
        columnModelClientes.getColumn(3).setMinWidth(0);
        columnModelClientes.getColumn(3).setPreferredWidth(0);
        columnModelClientes.getColumn(4).setMaxWidth(0); 				//telefono
        columnModelClientes.getColumn(4).setMinWidth(0);
        columnModelClientes.getColumn(4).setPreferredWidth(0);
        
        JScrollPane scrollPaneLibros   = new JScrollPane(tableLibros);
		JScrollPane scrollPaneClientes = new JScrollPane(tableClientes);
		
		btnGuardar = new JButton(" Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(validarDatos()) {
        			try {
						guardarInformacion();
					} catch (ParseException | IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
			}
		});
		btnGuardar.setIcon(new ImageIcon(UserMain.class.getResource("/com/eenp/bookmaster/client/images/UIUX_8666542_save_icon.png")));
		
		btnSalir = new JButton(" Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(UserMain.class.getResource("/com/eenp/bookmaster/client/images/UIUX_8666736_x_circle_icon.png")));
		
		lblNewLabel = new JLabel("Busca Cliente:");
		
		txtConsultaCliente = new JTextField();
		txtConsultaCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!txtConsultaCliente.getText().equals("")) {
						try {
							consultarClientes(txtConsultaCliente.getText());
						} catch (ParseException | URISyntaxException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		txtConsultaCliente.setColumns(10);
		
		txtConsultaLibroAutor = new JTextField();
		txtConsultaLibroAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!txtConsultaLibroAutor.getText().equals("")) {
						try {
							consultarLibros(txtConsultaLibroAutor.getText(),"AUTOR");
							txtConsultaLibroTitulo.setText("");
						} catch (ParseException | URISyntaxException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		txtConsultaLibroAutor.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Busca Libro por Autor:");
		
		txtConsultaLibroTitulo = new JTextField();
		txtConsultaLibroTitulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!txtConsultaLibroTitulo.getText().equals("")) {
						try {
							consultarLibros(txtConsultaLibroTitulo.getText(),"TITULO");
							txtConsultaLibroAutor.setText("");
						} catch (ParseException | URISyntaxException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		txtConsultaLibroTitulo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Busca Libro por Titulo");
		lblNewLabel_1_1.setMaximumSize(new Dimension(120, 13));
		lblNewLabel_1_1.setMinimumSize(new Dimension(120, 13));
		lblNewLabel_1_1.setPreferredSize(new Dimension(120, 13));
        
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txtConsultaCliente)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
						.addComponent(scrollPaneClientes, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtConsultaLibroTitulo, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtConsultaLibroAutor, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPaneLibros, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtConsultaCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1_1)
							.addGap(6)
							.addComponent(txtConsultaLibroTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtConsultaLibroAutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneClientes, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPaneLibros, 0, 0, Short.MAX_VALUE)))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void consultarLibros(String valor,String opc) throws ParseException, URISyntaxException, IOException {
		ApiResponse<?> response = null;
		if(opc.equals("TITULO")) {
			response = bookController.findByTitle(valor);
		}else {
			response = bookController.findByAuthor(valor);
		}
		cargarDatosConsultaLibros(response);
	}
	
	@SuppressWarnings("unchecked")
	private void cargarDatosConsultaLibros(ApiResponse<?> response) {
		if(response.getHttpResponse().getStatusCode() == 200) {
			List<Book> libros = (List<Book>) response.getResponse();
			tableModelLibros.setNumRows(0);
    		for (Book libro : libros) {
    			tableModelLibros.addRow(new Object[]{
                		libro.getId(),
                		libro.getAuthor().getId(),
                		libro.getAuthor().getNombre(),
                		libro.getTitulo(),
                		libro.getPublisher().getId(),
                		libro.getPublisher().getNombre(),
                		libro.getAniopublicacion(),
                		libro.getDisponible() == 1 ? "SI" : "NO"
                });
            }
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
	}
	
	private void consultarClientes(String nombre) throws ParseException, URISyntaxException, IOException {
		ApiResponse<?> response = clientController.findClienteByNombre(nombre);
    	if(response.getHttpResponse().getStatusCode() == 200) {
    		@SuppressWarnings("unchecked")
			List<Client> clientes = (List<Client>) response.getResponse();
    		tableModelClientes.setNumRows(0);
    		for (Client cliente : clientes) {
    			tableModelClientes.addRow(new Object[]{
                		cliente.getId(),
                		cliente.getNombre(),
                		cliente.getCorreoelectronico(),
                		cliente.getDireccion(),
                		cliente.getTelefono()
                });
            }
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
	}
	
	private void limpiarCampos() {
    	txtConsultaCliente.setText("");
    	txtConsultaLibroAutor.setText("");
    	txtConsultaLibroTitulo.setText("");
    }

	protected boolean validarDatos() {
		if(tableClientes.getSelectedRow() != -1) {
			if(tableLibros.getSelectedRow() != -1) {
				return true;
			}else {
				func.showMSG("ERROR","Debe seleccionar un libro de la lista.","Prestamos...");
				return false;
			}
		}else {
			func.showMSG("ERROR","Debe seleccionar un autor de la lista.","Prestamos...");
			return false;
		}
	}
	
	protected void guardarInformacion() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		int filaCliente = tableClientes.getSelectedRow();
		int filaLibro   = tableLibros.getSelectedRow();
		Client client = new Client();
		client.setId(Integer.parseInt(tableClientes.getValueAt(filaCliente,0).toString()));
		client.setNombre(tableClientes.getValueAt(filaCliente,1).toString());
		client.setCorreoelectronico(tableClientes.getValueAt(filaCliente,2).toString());
		client.setDireccion(tableClientes.getValueAt(filaCliente,3).toString());
		client.setTelefono(tableClientes.getValueAt(filaCliente,4).toString());
		
		Author author = new Author();
		author.setId(Integer.parseInt(tableLibros.getValueAt(filaLibro,1).toString()));
		author.setNombre(tableLibros.getValueAt(filaLibro,2).toString());
		
		Publisher publisher = new Publisher();
		publisher.setId(Integer.parseInt(tableLibros.getValueAt(filaLibro,4).toString()));
		publisher.setNombre(tableLibros.getValueAt(filaLibro,5).toString());
		
		Book book = new Book();
		book.setId(Integer.parseInt(tableLibros.getValueAt(filaLibro,0).toString()));
		book.setTitulo(tableLibros.getValueAt(filaLibro,3).toString());
		book.setIdautor(Integer.parseInt(tableLibros.getValueAt(filaLibro,1).toString()));
		book.setAuthor(author);
		book.setIdeditorial(Integer.parseInt(tableLibros.getValueAt(filaLibro,4).toString()));
		book.setPublisher(publisher);
		book.setAniopublicacion(Integer.parseInt(tableLibros.getValueAt(filaLibro,6).toString()));
		book.setDisponible(tableLibros.getValueAt(filaLibro,7).toString().equals("SI") ? 1 : 0);
		
		Loan loan = new Loan();
		loan.setId(null);
		loan.setId_libro(book);
		loan.setId_cliente(client);
		loan.setFechaPrestamo(LocalDate.now().toString());
		loan.setFechaDevolucion(LocalDate.now().plusDays(30).toString());
		loan.setEstado("1");
		
		ApiResponse<?> response = loanController.setPrestamoNuevo(loan);
		if(response.getHttpResponse().getStatusCode() == 200) {
    		func.showMSG("OK","El Prestamo se registró correctamente.","BookMaster...");
    		limpiarCampos();
    		cargarDatosLibros();
    		return;
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			limpiarCampos();
			return;
		}
		
	}
}
