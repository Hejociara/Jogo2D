package graficos;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game  extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static JFrame jframe;
	private Thread thread;
	private boolean isRunning = true; 
	// DECLARA  the variables ALTURA, LARGURA E COMPRIMENTO
	private static int WIDTH = 160;
	private static int  HEIGHT = 120;
	private static int SCALE= 3;
	
	public Game() { //m�todo construtor de classe
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
	}
	private void initFrame() {
		jframe = new JFrame ("JOGO"); // inicializar frame com a tela chamando jogo
		jframe.add(this); 
		jframe.setResizable(false); // tira a permiss�o do usu�rio de maximizar ou diminuir a janela
		jframe.pack();
		jframe.setLocationRelativeTo(null); //posi��o da tela e o centro � zero, colocaremos null
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//comando de fechar o jogo
		jframe.setVisible(true); // deixar vis�vel
	}

	public static void main(String[] args) {
		Game game = new Game(); //inicializa��o do objeto
		game.start();
			}
	public synchronized void start() {// me�todo de sincroniza��o`
		thread = new Thread(this);//inicializar a thread
		isRunning = true; // precisamos que seja true para que o looping possa acontecer
		thread.start(); //thread  vai chamar o start
	}
	public synchronized void stop() {
	}
	public void tick() { // tick � sempre antes do render
	}
	public void render() {
	}
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0f;//vari�vel que delimita o tamanho
		double ms = 100000000 / amountOfTicks; 
		double delta = 0; 
		int frames = 0; //contador
		double timer  = System.currentTimeMillis();//vari�vel que chama
		while (isRunning) {
			long now = System.nanoTime();
			delta+=(now - lastTime) / ms; 
			lastTime = now;
			if(delta > 1) { // se a conta do now - lastTime for maior q 1
				tick();
				render();
				frames++;
				delta --;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " +  frames);
				frames = 0;
				timer += 1000;
			}
			
			System.out.println("last time");
		}
	
	}

}
