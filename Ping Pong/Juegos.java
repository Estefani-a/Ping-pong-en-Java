import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Juegos<bolita> extends javax.swing.JFrame implements KeyListener {
	
	
	//private final BufferStrategy estrategia;
	
	

	private JPanel panel;
	private Bola bolita;
	private Raqueta raquetita1;
	private Raqueta2 raquetita2;
	private int golpes,golpes2, c;
	private JLabel labelcontador;
	private JLabel fondo;
	private JLabel labelcontador2;

	/**
	 * Launch the application.
	 * @throws InterruptedException  //OJO AQUI 
	 */
	public static void main(String[] args) throws InterruptedException {
		            Juegos frame = new Juegos();
		            frame.setVisible(true);
		            
					
					while(true){
						frame.repaint();
						frame.moverMundo();
					    Thread.sleep(180);///AQUI ME DARA UN ERROR LE DOY A LA X Y ELIJO EL 1ERO
					    
					}
	}

	

	/**
	 * Constructor de la clase juego.
	 */
	public Juegos() {
		setTitle("Ping Pong ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 640, 450);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		////////para poner fondo
		((JPanel) getContentPane()).setOpaque(false);
        ImageIcon imagen = new ImageIcon(this.getClass().getResource("mesapinpong1.jpg"));
        /////////////////////////
        
        
        labelcontador = new JLabel("0");
        labelcontador.setForeground(Color.WHITE);
        labelcontador.setFont(new Font("Monospaced", Font.BOLD, 17));
        labelcontador.setBounds(93, 0, 73, 18);
        panel.add(labelcontador);
        
        labelcontador2 = new JLabel("0");
        labelcontador2.setForeground(Color.WHITE);
        labelcontador2.setFont(new Font("Monospaced", Font.BOLD, 17));
        labelcontador2.setBounds(485, 2, 56, 16);
        panel.add(labelcontador2);
        fondo = new JLabel();
        fondo.setIcon(imagen);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, 640, 450);
        getContentPane().add(fondo, BorderLayout.CENTER);
        this.setSize(640, 484);
        this.setLocationRelativeTo(null);
		bolita=new Bola(getWidth(),getHeight());
		raquetita1=new Raqueta(getHeight());
		raquetita2=new Raqueta2(getHeight());
		addKeyListener(this);
		golpes=0;
		golpes2=0;
		//Sonido.FONDO.loop();
		//createBufferStrategy(2);
		//estrategia = this.getBufferStrategy();
		
		
	}
	public void  paint(Graphics g ){
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,(RenderingHints.VALUE_ANTIALIAS_ON));
		g2d.setColor(Color.GREEN);
		bolita.pintarBola(g2d);
		g2d.setColor(Color.RED);
		raquetita1.pintarRaqueta1(g2d);
		g2d.setColor(Color.YELLOW);
		raquetita2.pintarRaqueta2(g2d);
		
		
	}
	public void moverMundo(){
		bolita.moverBola();
		if(colision()){
		bolita.rebotaBola();
			golpes=golpes+1;
			//Sonido.REBOTEBOLA.play();
			labelcontador.setText(String.valueOf(golpes/2));
			
		}
		if(colision2()){
			bolita.rebotaBola();
			golpes2=golpes2+1;
			//Sonido.REBOTEBOLA.play();
			labelcontador2.setText(String.valueOf(golpes2/2));
			
		}
		if(bolita.TocaFondo()){
			
			gameOver();
		}
		
	}
	public boolean colision(){
		return bolita.limiteBola().intersects(raquetita1.limiteRaqueta1());
	}
	public boolean colision2(){
		return bolita.limiteBola().intersects(raquetita2.limiteRaqueta2());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A){
		raquetita1.moverRaquetaarriba1();
		}
		if(e.getKeyCode()==KeyEvent.VK_Z){
		raquetita1.moverRaquetaabajo1();
		}
		//////////////////////////////////////////
		if(e.getKeyCode()==KeyEvent.VK_UP){
			raquetita2.moverRaquetaarriba2();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN){
			raquetita2.moverRaquetaabajo2();
			}
			 

			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void gameOver() {
		//Sonido.FONDO.stop();
		//Sonido.GAMEOVER.play();
		if(golpes>golpes2){
			JOptionPane.showMessageDialog(this, "El jugador 1 gan�", "Game Over", JOptionPane.YES_NO_OPTION);
			System.exit(0);
		}else{
			if(golpes<golpes2){
		JOptionPane.showMessageDialog(this, "El jugador 2 gan�", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(0);
			}else{
				JOptionPane.showMessageDialog(this, "Empate", "Game Over", JOptionPane.YES_NO_OPTION);
				System.exit(0);
			}
		}
	}
}
