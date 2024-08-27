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
        setBounds(100, 100, 768, 500);
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
		} catch (ParseException e1) {
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
	private void cargarDatosInicial() {
		
	}
	
	private void limpiarCampos() {
    	txtTitulo.setText("");
    	txtAnioPublicacion.setText("");
    	try {
			cargarDatosInicial();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected boolean validarCampos() {
		if(txtTitulo.getText().equals("") || 
				txtAnioPublicacion.getText().equals("") || 
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
