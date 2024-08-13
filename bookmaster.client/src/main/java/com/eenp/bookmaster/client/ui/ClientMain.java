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

public class ClientMain extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private final ClientController clientController = new ClientController();
	
	Functions func = new Functions();

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
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("CorreoE");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Dirección");

        table = new JTable(tableModel);
        
        TableColumnModel columnModel = table.getColumnModel();
        
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        
        JScrollPane scrollPane = new JScrollPane(table);

        GroupLayout groupLayout = new GroupLayout(contentPane);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(232, Short.MAX_VALUE)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
        );
        contentPane.setLayout(groupLayout);
    }

    private void cargarDatosClientes() throws URISyntaxException {
    	ApiResponse<?> response = clientController.getClientes();
    	if(response.getHttpResponse().getStatusCode() == 200) {
    		@SuppressWarnings("unchecked")
			List<Client> clientes = (List<Client>) response.getResponse();
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

}
