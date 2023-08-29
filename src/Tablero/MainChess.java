package Tablero;
import javax.swing.*;
import java.awt.event.*;
public class MainChess extends JFrame{
    
    public static Tablero chess = new Tablero();
    public JMenuBar nav;
    public JMenu game;
    public JMenuItem nuevo, rendirse, salir;
    public ModoJuego vs = new ModoJuego();
    
    public MainChess(){
        super("Chess 1");
        setSize(625,625);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(chess.getPanel());
        navegacion();
        revalidate();   
    }
    
    public void navegacion(){
        nav = new JMenuBar();
        game = new JMenu("Juego");
        nuevo = new JMenuItem("Nuevo");
        rendirse = new JMenuItem("Rendirse");
        salir = new JMenuItem("Salir");
        nuevo.addActionListener(new EventosIniciales());
        rendirse.addActionListener(new EventosIniciales());
        salir.addActionListener(new EventosIniciales());
        nav.add(game);
        game.add(nuevo);
        game.add(rendirse);
        game.add(salir);
        setJMenuBar(nav);
    }
    
    class EventosIniciales implements ActionListener{

        @Override public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(nuevo)){
                vs.mostrarPanel();
                getContentPane().setEnabled(false);
            } else if(e.getSource().equals(rendirse)){
                int res = JOptionPane.showConfirmDialog(null,"Desea Rendirse","",
                        JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(res==JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Usted se ha rendido");
                    chess.borrarTablero();
                    chess.fichas();
                    chess.revalidate();
                    vs.mostrarPanel();
                }  
            } else if(e.getSource().equals(salir)){
                System.exit(0);
            }
        }
        
    }
    
    public static void main(String[] args) {
        new MainChess();    
    }
}
