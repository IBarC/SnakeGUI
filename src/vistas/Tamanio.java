package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tamanio {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JButton btnPequenio;
	private JButton btnMediano;
	private JButton btnGrande;
	public int velocidad;

	/**
	 * Create the application.
	 */
	public Tamanio(int vel) {
		this.velocidad = vel;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.setBounds(100, 100, 580, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		configureComponents();
		configureListeners();
	}
	
	private void configureComponents() {
		lblNewLabel = new JLabel("Selecciona un tama\u00F1o para el mapa");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(123, 11, 314, 72);
		frame.getContentPane().add(lblNewLabel);

		btnPequenio = new JButton("Peque\u00F1o");
		btnPequenio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPequenio.setBounds(217, 93, 113, 31);
		frame.getContentPane().add(btnPequenio);

		btnMediano = new JButton("Mediano");
		btnMediano.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMediano.setBounds(217, 147, 113, 31);
		frame.getContentPane().add(btnMediano);

		btnGrande = new JButton("Grande");
		btnGrande.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrande.setBounds(217, 200, 113, 31);
		frame.getContentPane().add(btnGrande);
	}
	
	private void configureListeners() {
		int vel = this.velocidad;
		btnPequenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					var l = new Lanzar(vel, 400, 500);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnMediano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var l = new Lanzar(vel, 800, 900);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var l = new Lanzar(vel, 1200, 1300);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
