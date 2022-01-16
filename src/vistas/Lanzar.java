package vistas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controles.ControlTeclado;
import utils.MyButtonListener;
import utils.TableroJuego;

public class Lanzar extends Thread {

	public int velocidad;
	private int contador;
	private MySnakeFrame frame;
	private JPanel mainPanel;
	private TableroJuego tablero;
	private JPanel botonera;
	private JLabel puntos;
	private JLabel puntosNum;
	private JButton start;
	private JButton pause;
	private ControlTeclado miControlador;

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public Lanzar(int velocidad) throws InterruptedException {
		this.velocidad = velocidad;
		//initialize();
	}

	private void initialize() throws InterruptedException {
		this.frame = new MySnakeFrame();

		configureComponents();
	}

	@Override
	public void run() {
		this.frame = new MySnakeFrame();
		try {
			configureComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void configureComponents() throws InterruptedException {

		// asignamos el tamaño a nuestra ventana, y hacemos que se cierre cuando nos
		// pulsan
		// la X de cerrar la ventana
		this.frame.setSize(600, 600);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Ahora creamos los componentes y los ponemos en la frame (ventana).

		// El panel de fondo. Rellena el frame, y sirve de contenedor del tablero y de
		// la botonera.
		this.mainPanel = new JPanel(new BorderLayout());

		// Ahora creamos el tablero. Recordamos: no deja de ser un panel un poquito
		// "especial"
		this.tablero = new TableroJuego();

		// Les damos las propiedades a nuestro tablero. Su color, tamaño y borde
		this.tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		this.tablero.setBackground(new java.awt.Color(208, 237, 211));
		this.tablero.setSize(600, 400);

		// Le damos un enlace al tablero para que sepa quién es su frame (ventana) y
		// así
		// sepa
		// quién contiene la serpiente y quién controla el juego...
		this.tablero.setSnakeFrame(frame);

		// Ahora el turno de la botonera. Tendrá los dos botones y las etiquetas de
		// texto
		this.botonera = new JPanel();
		this.botonera.setBorder(BorderFactory.createLineBorder(Color.black));
		this.botonera.setBackground(new java.awt.Color(150, 150, 150));

		// Ahora definimos las dos etiquetas para los puntos.
		this.puntos = new JLabel();
		this.puntos.setText("Puntos: ");
		this.puntos.setBackground(new java.awt.Color(190, 190, 190));

		this.puntosNum = new JLabel();
		this.puntosNum.setText("0");
		this.puntosNum.setBackground(new java.awt.Color(190, 190, 190));

		// turno de los botones de empezar y pausar/continuar
		this.start = new JButton();
		this.start.setSize(50, 20);
		this.start.setText("Start");
		this.start.addActionListener(new MyButtonListener(frame, tablero));

		this.pause = new JButton();
		this.pause.setSize(50, 20);
		this.pause.setText("Pause");
		this.pause.addActionListener(new MyButtonListener(frame, tablero));

		// Preparamos el control del teclado
		this.miControlador = new ControlTeclado();
		this.miControlador.setSnakeFrame(frame); // le damos al controlador de teclado un enlace el frame principal
		this.tablero.addKeyListener(miControlador); // le decimos al tablero que el teclado es cosa de nuestro
													// controlador
		this.tablero.setFocusable(true); // permitimos que el tablero pueda coger el foco.

		// Añadimos los componentes uno a uno, cada uno en su contenedor, y al final el
		// panel principal
		// se añade al frame principal.
		this.botonera.add(start);
		this.botonera.add(pause);
		this.botonera.add(puntos);
		this.botonera.add(puntosNum);

		this.mainPanel.add(botonera, BorderLayout.PAGE_END);
		this.mainPanel.add(tablero, BorderLayout.CENTER);
		this.frame.add(mainPanel);

		this.frame.setVisible(true); // activamos la ventana principal para que sea "pintable"

		this.contador = 0; // nuestro control de los pasos del tiempo. Cada vez que contador cuenta un
							// paso, pasan 10ms

		while (true) { // por siempre jamás (hasta que nos cierren la ventana) estamos controlando el
						// juego.

			// actualizamos el estado del juego
			if (this.contador % velocidad == 0) { // cada 200ms nos movemos o crecemos...
				if (this.contador == 60) { // Cada 600ms crecemos y reseteamos el contador
					this.contador = 0;
					this.frame.tocaCrecer();
					// hemos crecido... actualizamos puntos.
					puntosNum.setText(Integer.toString(frame.getSerpiente().getPuntos()));
				} else { // a los 200 y 400 ms nos movemos...
					this.contador++;
					this.frame.tocaMoverse();
				}
				this.frame.comprobarEstado(tablero.getHeight(), tablero.getWidth()); // comprobamos si hemos muerto o
																						// no.

			} else { // Cada vez que no hay que moverse o crecer, simplemente contamos...
				this.contador++;
			}

			// hemos terminado?? mostramos msg
			if (this.frame.mostrarFin()) {
				JOptionPane.showMessageDialog(this.frame,
						"Se acabo vaquero, has conseguido " + this.puntosNum.getText() + " puntos");
			}

			// Repintamos
			this.tablero.repaint();

			// Esperamos para dar tiempo al thread de repintado a pintar.

			Thread.sleep(10);

		}
	}

}
