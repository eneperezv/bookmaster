package com.eenp.bookmaster.client.ui;

import java.awt.Color;

/*
 * @(#)Main.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase principal del proyecto donde se incluyen llaman las funcionalidades del sistema
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URISyntaxException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.eenp.bookmaster.client.data.ApiServiceConfig;
import com.eenp.bookmaster.client.service.UserSession;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JButton btnNewButton;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws URISyntaxException 
	 */
	public Main() throws URISyntaxException {
		obtenerConfiguracion();

		initObjects();
		initData();
		initComponents();
		initEvents();
	}

	private void initObjects() {
		btnNewButton = new JButton("New button");
		panel = new JPanel();
		lblUsuario = new JLabel("");
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
		setTitle("BookMaster | 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addContainerGap(881, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 485, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void initEvents() {
		// TODO Auto-generated method stub
		
	}
	
	private void initData() {
		lblUsuario.setText(UserSession.getInstance().getUsuario().getNombre() + " | " + UserSession.getInstance().getUsuario().getUsuario());
		lblUsuario.setSize(lblUsuario.getPreferredSize());
	}

	public void obtenerConfiguracion() throws URISyntaxException {
		ApiServiceConfig configuracion = ApiServiceConfig.obtenerInstancia();
		String url = configuracion.obtenerValor("API_URL");
		System.out.println("url->"+url);
		System.out.println("usuario->"+UserSession.getInstance().getUsuario().toString());
		
	}
}
