package com.eenp.bookmaster.client.ui;

/*
 * @(#)ClientMain.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** UI Principal del control de Clientes
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.eenp.bookmaster.client.controller.ClientController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.Client;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ClientMain extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private final ClientController clientController = new ClientController();
	
	Functions func = new Functions();
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtDireccion;
	private JButton btnGuardar;
	private JButton btnSalir;

    public static void main(String[] args) {
    	
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMain().setVisible(true);
            }
        });
    }

    public ClientMain() {
        setTitle("BookMaster | 1.0 | Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);

        initialize();
        try {
			cargarDatosClientes();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void initialize() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Correo Electrónico");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Dirección");

        table = new JTable(tableModel);
        
        TableColumnModel columnModel = table.getColumnModel();
        
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        JLabel lblNewLabel = new JLabel("Nombre:");
        
        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Teléfono:");
        
        txtTelefono = new JTextField();
        txtTelefono.setColumns(10);
        
        txtCorreo = new JTextField();
        txtCorreo.setColumns(10);
        
        lblNewLabel_2 = new JLabel("Correo Electrónico:");
        
        lblNewLabel_3 = new JLabel("Dirección:");
        
        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);
        
        btnGuardar = new JButton(" Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(validarCampos()) {
        			try {
						guardarInformacion();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
        btnGuardar.setIcon(new ImageIcon(ClientMain.class.getResource("/com/eenp/bookmaster/client/images/Save2.png")));
        
        btnSalir = new JButton(" Salir");
        btnSalir.setIcon(new ImageIcon(ClientMain.class.getResource("/com/eenp/bookmaster/client/images/exit2.png")));
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		dispose();
        	}
        });

        GroupLayout groupLayout = new GroupLayout(contentPane);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap(2)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtTelefono))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(txtCorreo, 322, 322, Short.MAX_VALUE)
        				.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtDireccion))
        			.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
        		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblNewLabel)
        						.addComponent(lblNewLabel_2))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtCorreo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblNewLabel_1)
        						.addComponent(lblNewLabel_3))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
        );
        contentPane.setLayout(groupLayout);
    }

    private void cargarDatosClientes() throws URISyntaxException {
    	ApiResponse<?> response = clientController.getClientes();
    	if(response.getHttpResponse().getStatusCode() == 200) {
    		@SuppressWarnings("unchecked")
			List<Client> clientes = (List<Client>) response.getResponse();
    		tableModel.setNumRows(0);
    		for (Client cliente : clientes) {
                tableModel.addRow(new Object[]{
                		cliente.getId(),
                		cliente.getNombre(),
                		cliente.getCorreoelectronico(),
                		cliente.getTelefono(),
                		cliente.getDireccion()
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
    	txtNombre.setText("");
    	txtTelefono.setText("");
    	txtCorreo.setText("");
    	txtDireccion.setText("");
    }

	private boolean validarCampos() {
		if(txtNombre.getText().equals("") || 
			txtTelefono.getText().equals("") || 
			txtCorreo.getText().equals("") || 
			txtDireccion.getText().equals("")
				){
			func.showMSG("ERROR","Por favor verifique la información. Debe completar todos los campos","Clientes");
			return false;
		}else {
			return true;
		}
	}
	
	public void guardarInformacion() throws URISyntaxException {
		Client cliente = new Client();
		cliente.setId(null);
		cliente.setNombre(txtNombre.getText());
		cliente.setTelefono(txtTelefono.getText());
		cliente.setDireccion(txtDireccion.getText());
		cliente.setCorreoelectronico(txtCorreo.getText());
		
		ApiResponse<?> response = clientController.setClienteNuevo(cliente);
		if(response.getHttpResponse().getStatusCode() == 201) {
    		func.showMSG("OK","El cliente se creó correctamente.","BookMaster...");
    		limpiarCampos();
    		cargarDatosClientes();
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
