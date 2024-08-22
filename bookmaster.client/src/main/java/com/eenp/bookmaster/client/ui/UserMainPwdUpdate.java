package com.eenp.bookmaster.client.ui;

/*
 * @(#)UserMainPwdUpdate.java 1.0 17/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** UI Actualizacion de contraseña del usuario.
 *
 * @author eliezer.navarro
 * @version 1.0 | 17/08/2024
 * @since 1.0
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.eenp.bookmaster.client.controller.UserController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

public class UserMainPwdUpdate extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPwd1;
	private JTextField txtPwd2;
    
    private final UserController userController = new UserController();
	
	Functions func = new Functions();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserMainPwdUpdate dialog = new UserMainPwdUpdate(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param b 
	 * @param object 
	 */
	public UserMainPwdUpdate(JFrame padre,boolean modal) {
		super(padre,modal);
		
		setTitle("BookMaster | 1.0 | Actualice su contraseña");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
		setBounds(100, 100, 243, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Nueva Contraseña:");
		
		txtPwd1 = new JTextField();
		txtPwd1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Confirme su nueva contraseña:");
		
		txtPwd2 = new JTextField();
		txtPwd2.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtPwd2, Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPwd1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(221, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPwd1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPwd2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(118, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(validarDatos()) {
							try {
								guardarInformacion();
							} catch (URISyntaxException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean validarDatos() {
		if(txtPwd1.getText().equals("") || 
				txtPwd2.getText().equals("")
			) {
			func.showMSG("ERROR","Por favor verifique la información. Debe completar todos los campos.","Usuarios...");
			return false;
		}else {
			if(!txtPwd1.getText().equals(txtPwd2.getText())) {
				func.showMSG("ERROR","Por favor verifique la información. Las contraseñas no coinciden.","Usuarios...");
				return false;
			}else {
				return true;
			}
		}
	}
	
	private void guardarInformacion() throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		User nuevosDatosUsuario = new User();
		nuevosDatosUsuario.setId(UserSession.getInstance().getUsuario().getId());
		nuevosDatosUsuario.setUsername(UserSession.getInstance().getUsuario().getUsername());
		nuevosDatosUsuario.setName(UserSession.getInstance().getUsuario().getName());
		nuevosDatosUsuario.setClaveNE(txtPwd1.getText());
		nuevosDatosUsuario.setPassword(func.retornaHashBCrypt(txtPwd1.getText()));
		//VALIDAR FUNCIONALIDAD Y LOGICA
		ApiResponse<?> response = userController.setUsuarioUpdate(nuevosDatosUsuario);
		if(response.getHttpResponse().getStatusCode() == 200) {
    		func.showMSG("OK","El usuario se actualizo correctamente. Inicie sesión nuevamente usando su nueva clave.","BookMaster...");
    		System.exit(0);
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
	}
}
