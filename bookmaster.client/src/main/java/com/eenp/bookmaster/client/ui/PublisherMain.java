package com.eenp.bookmaster.client.ui;

import java.awt.EventQueue;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PublisherMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
        cargarDatosUsuarios();
	}

	private void cargarDatosUsuarios() {
		// TODO Auto-generated method stub
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
	}

}
