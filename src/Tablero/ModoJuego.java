package Tablero;
import Juego.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ModoJuego extends JFrame{
    
    private JLabel info = new JLabel("   Seleccione un Modo de Juego   ");
    private JButton Modo1v1 = new JButton("1 vs 1, libre");
    private JButton Modo1vC = new JButton("1 vs Bot, contrincante");
    private JButton ModoCvC = new JButton("Bot vs Bot, espectador");
    
    public ModoJuego(){
        setUndecorated(true);
        setSize(200,150);
        setResizable(false);
        setVisible(false);
        setLocationRelativeTo(null);
        
        Modo1v1.addMouseListener(new Modo());
        Modo1vC.addMouseListener(new Modo());
        ModoCvC.addMouseListener(new Modo());
    }
    
    public JPanel versus(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.setBorder(new MatteBorder(5,5,5,5,new Color(100,100,100)));
        panel.add(info);
        panel.setBackground(new Color(100,100,100));
        info.setForeground(Color.WHITE);
        panel.add(Modo1v1);
        panel.add(Modo1vC);
        panel.add(ModoCvC);
        return panel;
    }
    
    public void mostrarPanel(){
        setVisible(true);
        getContentPane().removeAll();
        getContentPane().add(versus());
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
     class Modo implements MouseListener{
        @Override public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(Modo1v1)){
                Game.modo1v1();
                setVisible(false);
                MainChess.setDefaultLookAndFeelDecorated(true);
            } else if(e.getSource().equals(Modo1vC)){
                Game.modo1vC();
                setVisible(false);
            } else if(e.getSource().equals(ModoCvC)){
                Game.modoCvC();
                setVisible(false);
            }
        }

        @Override public void mousePressed(MouseEvent e) {}

        @Override public void mouseReleased(MouseEvent e) {}

        @Override public void mouseEntered(MouseEvent e) {}

        @Override public void mouseExited(MouseEvent e) {}
    }
}
