package com.eenp.bookmaster.client.ui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.eenp.bookmaster.client.controller.AuthorController;
import com.eenp.bookmaster.client.controller.BookController;
import com.eenp.bookmaster.client.controller.PublisherController;
import com.eenp.bookmaster.client.util.Functions;
import java.awt.Component;

public class LoanMain extends JFrame {

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

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Titulo");
        tableModel.addColumn("Editorial");
        tableModel.addColumn("Año Publicación");
        tableModel.addColumn("Disponible");

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
        			try {
						guardarInformacion();
					} catch (ParseException e1) {
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
		
		lblNewLabel = new JLabel("Consultar Cliente:");
		
		txtConsultaCliente = new JTextField();
		txtConsultaCliente.setColumns(10);
		
		txtConsultaLibroAutor = new JTextField();
		txtConsultaLibroAutor.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Consultar Libro por Autor:");
		
		try {
			cargarDatosInicial();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		
		txtConsultaLibroTitulo = new JTextField();
		txtConsultaLibroTitulo.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Consultar Libro por Titulo");
        
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtConsultaCliente, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtConsultaLibroAutor, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtConsultaLibroTitulo, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtConsultaCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtConsultaLibroAutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1_1)
							.addGap(6)
							.addComponent(txtConsultaLibroTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void cargarDatosInicial() {
		
	}
	
	private void limpiarCampos() {
    	txtConsultaCliente.setText("");
    	txtConsultaLibroAutor.setText("");
    	try {
			cargarDatosInicial();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected boolean validarCampos() {
		if(txtConsultaCliente.getText().equals("") || 
				txtConsultaLibroAutor.getText().equals("") || 
				cmbAutores.getSelectedItem().toString().equals("Seleccione uno.") || 
				cmbEditoriales.getSelectedItem().toString().equals("Seleccione uno.")
					){
				func.showMSG("ERROR","Por favor verifique la información. Debe completar todos los campos","Libros");
				return false;
			}else {
				return true;
			}
	}
	
	protected void guardarInformacion() {
		
	}
}
