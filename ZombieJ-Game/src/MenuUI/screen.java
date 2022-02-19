package MenuUI;
 


import javax.swing.JFrame;

public class screen extends JFrame{


	
    public static JFrame frame;
    public static Startscreen startscreen;
    public static Settings settings;
    public static Help help;
   
   
    
    public screen() {
		 
		 frame = new JFrame("ZombieJGame");
		 this.startscreen= new Startscreen();
		 this.settings= new Settings();
		 this.help=new Help();
		 
		 
		 frame.setSize(800,600);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //beendet Prozess falls geschlossen wird
		// frame.setUndecorated(true);
		 
		 frame.requestFocus();
		 frame.add(startscreen);
		 frame.pack();
		 frame.setResizable(false);
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
		 
	 }
}
