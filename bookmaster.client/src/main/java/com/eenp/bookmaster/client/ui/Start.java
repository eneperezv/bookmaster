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
 * Clase inicial del proyecto donde se ingresa usuario y clave para el inicio de sesión
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.conn.HttpHostConnectException;

import com.eenp.bookmaster.client.controller.UserController;
import com.eenp.bookmaster.client.entity.User;
import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

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
		setBounds(100, 100, 332, 205);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnNewButton.setBounds(10, 114, 245, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionSalir();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Start.class.getResource("/com/eenp/bookmaster/client/images/exit2.png")));
		btnNewButton_1.setBounds(265, 114, 45, 45);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(10, 10, 300, 13);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(10, 33, 300, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(10, 62, 300, 13);
		contentPane.add(lblClave);
		
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
		txtClave.setBounds(10, 85, 300, 19);
		contentPane.add(txtClave);

		//System.out.println("-->"+func.retornaMD5("admin")+"<--");
		//System.out.println("-->"+func.retornaHashBCrypt("addenp")+"<--");
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
		User user = userController.obtenerDatosUsuario(usuario,clave);
		user.setClaveNE(clave);
		if(user != null) {
			if(func.checkPassword(clave,user.getClave())) {
				UserSession.getInstance().setUsuario(user);
				openMainWindow();
			}else {
				func.showMSG("ERROR","Clave incorrecta.","Error...");
				return;
			}
		}else {
			func.showMSG("ERROR","Usuario no reconocido.","Error...");
			return;
		}
	}
	
	public void openMainWindow() throws URISyntaxException {
		Main link = new Main();
		link.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}
}
