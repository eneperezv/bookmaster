package com.eenp.bookmaster.client.ui;

/*
 * @(#)BookMain.java 1.0 21/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** UI Principal del control de Libros
 *
 * @author eliezer.navarro
 * @version 1.0 | 21/08/2024
 * @since 1.0
 */

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.http.ParseException;

import com.eenp.bookmaster.client.controller.AuthorController;
import com.eenp.bookmaster.client.controller.BookController;
import com.eenp.bookmaster.client.controller.PublisherController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Author;
import com.eenp.bookmaster.client.entity.Book;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Publisher;
import com.eenp.bookmaster.client.util.Functions;
import javax.swing.JComboBox;

public class BookMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private final BookController bookController = new BookController();
    
    private final AuthorController authorController = new AuthorController();
    private final PublisherController publisherController = new PublisherController();
	
	Functions func = new Functions();
	private JButton btnGuardar;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JTextField txtTitulo;
	private JTextField txtAnioPublicacion;
	private JLabel lblNewLabel_1;
	private JComboBox cmbAutores;
	private JComboBox cmbEditoriales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookMain frame = new BookMain();
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
	public BookMain() {
		setTitle("BookMaster | 1.0 | Libros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 768, 500);
        setLocationRelativeTo(null);

        initialize();
        try {
			cargarDatosLibros();
		} catch (ParseException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarDatosLibros() throws ParseException, URISyntaxException, IOException {
		ApiResponse<?> response = bookController.getLibros();
    	if(response.getHttpResponse().getStatusCode() == 200) {
			List<Book> libros = (List<Book>) response.getResponse();
    		tableModel.setNumRows(0);
    		for (Book libro : libros) {
                tableModel.addRow(new Object[]{
                		libro.getId(),
                		libro.getAuthor().getNombre(),
                		libro.getTitulo(),
                		libro.getPublisher().getNombre(),
                		libro.getAniopublicacion()
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
        tableModel.addColumn("Autor");
        tableModel.addColumn("Titulo");
        tableModel.addColumn("Editorial");
        tableModel.addColumn("Año Publicación");

        table = new JTable(tableModel);
        
        TableColumnModel columnModel = table.getColumnModel();
        
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        
        JScrollPane scrollPane = new JScrollPane(table);
		
		btnGuardar = new JButton(" Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(validarCampos()) {
        			guardarInformacion();
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
		
		lblNewLabel = new JLabel("Titulo:");
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		
		txtAnioPublicacion = new JTextField();
		txtAnioPublicacion.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Año de Publicación:");
		
		JLabel lblNewLabel_2 = new JLabel("Autor:");
		
		JLabel lblNewLabel_3 = new JLabel("Editorial:");
		
		cmbAutores = new JComboBox();
		
		cmbEditoriales = new JComboBox();
		
		try {
			cargarDatosInicial();
		} catch (ParseException | URISyntaxException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cmbAutores, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtTitulo, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAnioPublicacion, 212, 212, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbEditoriales, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAnioPublicacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmbAutores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbEditoriales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	@SuppressWarnings("unchecked")
	private void cargarDatosInicial() throws ParseException, URISyntaxException, IOException {
		cmbAutores.removeAllItems();
		cmbAutores.addItem("Seleccione uno.");
		cmbEditoriales.removeAllItems();
		cmbEditoriales.addItem("Seleccione uno.");
		ApiResponse<?> responseAutores = authorController.getAutores();
    	if(responseAutores.getHttpResponse().getStatusCode() == 200) {
			List<Author> autores = (List<Author>) responseAutores.getResponse();
    		tableModel.setNumRows(0);
    		for (Author autor : autores) {
    			cmbAutores.addItem(autor.getId() + "|" + autor.getNombre());
            }
		}else {
			ErrorDetails errorDetails = (ErrorDetails) responseAutores.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
    	ApiResponse<?> responseEditoriales = publisherController.getEditoriales();
    	if(responseEditoriales.getHttpResponse().getStatusCode() == 200) {
			List<Publisher> editoriales = (List<Publisher>) responseEditoriales.getResponse();
    		tableModel.setNumRows(0);
    		for (Publisher editorial : editoriales) {
    			cmbEditoriales.addItem(editorial.getId() + "|" + editorial.getNombre());
                tableModel.addRow(new Object[]{
                		editorial.getId(),
                		editorial.getNombre()
                });
            }
		}else {
			ErrorDetails errorDetails = (ErrorDetails) responseEditoriales.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
	}
	
	private void limpiarCampos() {
    	txtTitulo.setText("");
    	txtAnioPublicacion.setText("");
    	try {
			cargarDatosInicial();
		} catch (ParseException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void guardarInformacion() {
		// TODO Auto-generated method stub
		
	}

	protected boolean validarCampos() {
		// TODO Auto-generated method stub
		return false;
	}
}
