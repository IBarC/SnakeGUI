package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import mainapp.MainApp;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NivDif {

	private JFrame frNivDif;
	private JLabel lbltextoTitulo;
	private JButton btnFacil;
	private JButton btnIntermedio;
	private JButton btnDificil;
	private JButton btnImposible;

	/**
	 * Create the application.
	 */
	public NivDif() {
		initialize();
		this.frNivDif.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frNivDif = new JFrame();
		frNivDif.getContentPane().setBackground(new Color(255, 255, 204));
		frNivDif.setBounds(100, 100, 580, 300);
		frNivDif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frNivDif.getContentPane().setLayout(null);

		configureComponents();
		configureListeners();
	}

	private void configureComponents() {
		lbltextoTitulo = new JLabel("Selecciona el nivel de dificultad");
		lbltextoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbltextoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbltextoTitulo.setBounds(138, 11, 280, 72);
		frNivDif.getContentPane().add(lbltextoTitulo);

		btnFacil = new JButton("Facil");
		btnFacil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFacil.setBounds(217, 93, 113, 31);
		frNivDif.getContentPane().add(btnFacil);

		btnIntermedio = new JButton("Intermedio");
		btnIntermedio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIntermedio.setBounds(217, 135, 113, 31);
		frNivDif.getContentPane().add(btnIntermedio);

		btnDificil = new JButton("Dif\u00EDcil");
		btnDificil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDificil.setBounds(217, 177, 113, 31);
		frNivDif.getContentPane().add(btnDificil);

		btnImposible = new JButton("Imposible");
		btnImposible.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnImposible.setBounds(217, 221, 113, 31);
		frNivDif.getContentPane().add(btnImposible);
	}

	private void configureListeners() {
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frNivDif.setVisible(false);
				try {
					var l = new Lanzar(20);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnIntermedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frNivDif.setVisible(false);
				try {
					var l = new Lanzar(10);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frNivDif.setVisible(false);
				try {
					var l = new Lanzar(5);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnImposible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frNivDif.setVisible(false);
				try {
					var l = new Lanzar(1);
					l.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
