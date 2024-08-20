package com.eenp.bookmaster.client.ui;

/*
 * @(#)Start.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * UI inicial del proyecto donde se ingresa usuario y clave para el inicio de sesión
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.eenp.bookmaster.client.controller.UserController;
import com.eenp.bookmaster.client.entity.ApiResponse;
import com.eenp.bookmaster.client.entity.ErrorDetails;
import com.eenp.bookmaster.client.entity.Token;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Start extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	
	private final UserController userController = new UserController();
	
	Functions func = new Functions();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
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
	public Start() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Start.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
		setTitle("BookMaster | 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Start.class.getResource("/com/eenp/bookmaster/client/images/logo_main_test.png")));
		lblNewLabel_1.setBounds(400, 0, 400, 563);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 91, 300, 361);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("  Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionSalir();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Start.class.getResource("/com/eenp/bookmaster/client/images/exit2.png")));
		
		JButton btnNewButton = new JButton("  Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					accionIniciarSesion();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Start.class.getResource("/com/eenp/bookmaster/client/images/Apply.png")));
		
		txtClave = new JPasswordField();
		txtClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						accionIniciarSesion();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			}
		});
		
		JLabel lblClave = new JLabel("Clave:");
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenid@");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(lblClave, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(txtClave, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblClave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(104)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		System.out.println("-->"+func.retornaHashBCrypt("addenp")+"<--");
	}
	
	public void accionSalir() {
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}
	
	public void accionIniciarSesion() throws URISyntaxException {
		String usuario = this.txtUsuario.getText();
		String clave   = this.txtClave.getText();
		if(usuario.length() == 0) {
			func.showMSG("ERROR","Debe ingresar el usuario","BookMaster...");
			return;
		}
		if(clave.length() == 0) {
			func.showMSG("ERROR","Debe ingresar su clave","BookMaster...");
			return;
		}
		validarLogin(usuario,clave);
	}
	
	public void validarLogin(String usuario, String clave) throws URISyntaxException {
		User user = new User();
		user.setUsername(usuario);
		user.setPassword(clave);
		ApiResponse<?> resToken = userController.getToken(user);
		if(resToken.getHttpResponse().getStatusCode() == 200) {
			Token token = (Token) resToken.getResponse();
			System.out.println("TOKEN->"+token.toString());
			user.setToken(token.getToken());
			ApiResponse<?> response = userController.getDatosUsuario(user);
			if(response.getHttpResponse().getStatusCode() == 200) {
				User userAuth = (User) response.getResponse();
				System.out.println("USER-FINAL->"+userAuth.toString());
				userAuth.setClaveNE(clave);
				if(func.checkPassword(clave,userAuth.getPassword())) {
					UserSession.getInstance().setUsuario(userAuth);
					openMainWindow();
				}else {
					func.showMSG("ERROR","Clave incorrecta.","Error...");
					return;
				}
			}else {
				ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
				func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
						errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
				return;
			}
			
			
			
			/*
			User user = (User) response.getResponse();
			user.setClaveNE(clave);
			if(func.checkPassword(clave,user.getPassword())) {
				UserSession.getInstance().setUsuario(user);
				openMainWindow();
			}else {
				func.showMSG("ERROR","Clave incorrecta.","Error...");
				return;
			}
			*/
		}else {
			ErrorDetails errorDetails = (ErrorDetails) resToken.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
		
		
		
		
		
		/*
		if(response.getHttpResponse().getStatusCode() == 200) {
			User user = (User) response.getResponse();
			user.setClaveNE(clave);
			if(func.checkPassword(clave,user.getPassword())) {
				UserSession.getInstance().setUsuario(user);
				openMainWindow();
			}else {
				func.showMSG("ERROR","Clave incorrecta.","Error...");
				return;
			}
		}else {
			ErrorDetails errorDetails = (ErrorDetails) response.getResponse();
			func.showMSG("ERROR","Ha ocurrido un error al procesar la solicitud\n\nDetalles: " +
					errorDetails.getMessage() + "|" + errorDetails.getDetails(),"BookMaster...");
			return;
		}
		*/
	}
	
	public void openMainWindow() throws URISyntaxException {
		Main link = new Main();
		link.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}
}
