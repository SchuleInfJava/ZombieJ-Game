package MenuUI;

import javax.swing.JFrame;
import config.GamePanel;

public class Screen extends JFrame{

	public static JFrame frame;
    public static Startscreen startscreen;
    public static Help help;
    public static boolean screen=true;
       
    
    public Screen() {
		 
		 frame = new JFrame("ZombieJGame");
		 this.startscreen= new Startscreen();
		 this.help=new Help();
		 
		 frame.setSize(800,600);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //beendet Prozess falls geschlossen wird
		 frame.requestFocus();
		 frame.add(startscreen);
		 frame.pack();
		 frame.setResizable(false);
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
	 }
}
