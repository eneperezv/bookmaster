package com.eenp.bookmaster.client.ui;

/*
 * @(#)PublisherMain.java 1.0 17/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** UI Principal del control de Editoriales
 *
 * @author eliezer.navarro
 * @version 1.0 | 17/08/2024
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

import com.eenp.bookmaster.client.controller.PublisherController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Publisher;
import com.eenp.bookmaster.client.util.Functions;

public class PublisherMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private final PublisherController publisherController = new PublisherController();
	
	Functions func = new Functions();
	private JButton btnGuardar;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublisherMain frame = new PublisherMain();
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
	public PublisherMain() {
		setTitle("BookMaster | 1.0 | Editoriales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 768, 500);
        setLocationRelativeTo(null);

        initialize();
        try {
			cargarDatosEditoriales();
		} catch (URISyntaxException | ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarDatosEditoriales() throws URISyntaxException, ParseException, IOException {
		ApiResponse<?> response = publisherController.getEditoriales();
    	if(response.getHttpResponse().getStatusCode() == 200) {
			List<Publisher> editoriales = (List<Publisher>) response.getResponse();
    		tableModel.setNumRows(0);
    		for (Publisher editorial : editoriales) {
                tableModel.addRow(new Object[]{
                		editorial.getId(),
                		editorial.getNombre()
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
        tableModel.addColumn("Editorial");

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
		
		lblNewLabel = new JLabel("Nombre de la Casa Editorial:");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
        
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
	
	private void limpiarCampos() {
    	txtNombre.setText("");
    }
	
	private boolean validarCampos() {
		if(txtNombre.getText().equals("")){
			func.showMSG("ERROR","Por favor verifique la información. Debe completar todos los campos","Editoriales...");
			return false;
		}else {
			return true;
		}
	}
	
	private void guardarInformacion() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		Publisher publisher = new Publisher();
		publisher.setId(null);
		publisher.setNombre(txtNombre.getText());
		
		ApiResponse<?> response = publisherController.setEditorialNuevo(publisher);
		if(response.getHttpResponse().getStatusCode() == 201) {
    		func.showMSG("OK","La Casa Editorial se creó correctamente.","BookMaster...");
    		limpiarCampos();
    		try {
    			cargarDatosEditoriales();
    			limpiarCampos();
			} catch (ParseException | URISyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
