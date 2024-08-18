package com.eenp.bookmaster.client.ui;

/*
 * @(#)Main.java 1.0 07/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/** UI Principal de la aplicación
 *
 * @author eliezer.navarro
 * @version 1.0 | 07/08/2024
 * @since 1.0
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.eenp.bookmaster.client.service.UserSession;
import com.eenp.bookmaster.client.util.Functions;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JButton btnNewButton;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu mnuMainMaestros;
	private JMenuItem mnuUsuarios;

	Functions func = new Functions();
	private JMenuItem mnuMainSalir;
	private JMenuItem mnuClientes;
	private JLabel lblNewLabel;
	
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
		
		if(UserSession.getInstance().getUsuario().getClaveNE().equals("123456")) {
			UserMainPwdUpdate link = new UserMainPwdUpdate(null,true);
			link.setVisible(true);
		}
	}

	private void initObjects() {
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//UserMainPwdUpdate link = new UserMainPwdUpdate(null,true);
				//link.setVisible(true);
			}
		});
		panel = new JPanel();
		lblUsuario = new JLabel("");
		menuBar = new JMenuBar();
		mnuMainMaestros = new JMenu("Maestros");
		mnuUsuarios = new JMenuItem("Usuarios");
		mnuClientes = new JMenuItem("Clientes");
		mnuMainSalir = new JMenuItem("Salir");
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/eenp/bookmaster/client/images/book.png")));
		setTitle("BookMaster | 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnuMainMaestros);
		menuBar.add(mnuMainSalir);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		lblNewLabel = new JLabel("New label");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addContainerGap(881, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(85)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(846, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(230)
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
		mnuUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMain link = new UserMain();
				link.setVisible(true);
			}
		});
		mnuClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientMain link = new ClientMain();
				link.setVisible(true);
			}
		});
		mnuMainSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
	}
	
	private void initData() {
		// PAGINA PARA LOS ICONOS
		// https://www.iconfinder.com/search/icons?family=feather
		mnuUsuarios.setIcon(new ImageIcon(Main.class.getResource("/com/eenp/bookmaster/client/images/UIUX_8666609_user_icon.png")));
		mnuMainMaestros.add(mnuUsuarios);
		mnuClientes.setIcon(new ImageIcon(Main.class.getResource("/com/eenp/bookmaster/client/images/UIUX_8666755_users_group_icon.png")));
		mnuMainMaestros.add(mnuClientes);
		mnuMainSalir.setIcon(new ImageIcon(Main.class.getResource("/com/eenp/bookmaster/client/images/UIUX_8666757_lock_security_icon.png")));
		lblUsuario.setText(UserSession.getInstance().getUsuario().getNombre() + " | " + UserSession.getInstance().getUsuario().getUsuario());
		lblUsuario.setSize(lblUsuario.getPreferredSize());
	}

	public void obtenerConfiguracion() throws URISyntaxException {
		//ApiServiceConfig configuracion = ApiServiceConfig.obtenerInstancia();
		//String url = configuracion.obtenerValor("API_URL");
		//System.out.println("url->"+url);
		//System.out.println("usuario->"+UserSession.getInstance().getUsuario().toString());
		
	}
}
