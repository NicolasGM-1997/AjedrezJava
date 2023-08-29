package Juego;
import Tablero.Tablero;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Coronacion extends JFrame {
    
    private JButton AlfilN = new JButton(Tablero.nombreIconos.get("an"));
    private JButton AlfilB = new JButton(Tablero.nombreIconos.get("ab"));
    private JButton TorreN = new JButton(Tablero.nombreIconos.get("tn"));
    private JButton TorreB = new JButton(Tablero.nombreIconos.get("tb"));
    private JButton CaballoN = new JButton(Tablero.nombreIconos.get("cn"));
    private JButton CaballoB = new JButton(Tablero.nombreIconos.get("cb"));
    private JButton ReinaN = new JButton(Tablero.nombreIconos.get("rn"));
    private JButton ReinaB = new JButton(Tablero.nombreIconos.get("rb"));
    
    
    public Coronacion(){
        setUndecorated(true);
        setSize(150,150);
        setResizable(false);
        setVisible(false);
        setLocationRelativeTo(null);
        asignarEventos();
    }
    
    public JPanel cambioPieza(int pos){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.setBorder(new MatteBorder(5,5,5,5,new Color(100,100,100)));
        if(pos==7){
            panel.add(AlfilN);
            panel.add(CaballoN);
            panel.add(TorreN);
            panel.add(ReinaN);
        } else if(pos==0){
            panel.add(AlfilB);
            panel.add(CaballoB);
            panel.add(TorreB);
            panel.add(ReinaB);
        }
        return panel;
    }
    
    public void mostrarPanel(int pos){
        setVisible(true);
        getContentPane().removeAll();
        getContentPane().add(cambioPieza(pos));
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    public void asignarEventos(){
        AlfilN.addMouseListener(new Coronar());
        TorreN.addMouseListener(new Coronar());
        CaballoN.addMouseListener(new Coronar());
        ReinaN.addMouseListener(new Coronar());
        AlfilB.addMouseListener(new Coronar());
        TorreB.addMouseListener(new Coronar());
        CaballoB.addMouseListener(new Coronar());
        ReinaB.addMouseListener(new Coronar());
    }
    
    class Coronar implements MouseListener{
        @Override public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(AlfilN)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("an"));
                setVisible(false);
            } else if(e.getSource().equals(TorreN)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("tn"));
                setVisible(false);
            } else if(e.getSource().equals(CaballoN)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("cn"));
                setVisible(false);
            } else if(e.getSource().equals(ReinaN)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("rn"));
                setVisible(false);
            } else if(e.getSource().equals(AlfilB)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("ab"));
                setVisible(false);
            } else if(e.getSource().equals(TorreB)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("tb"));
                setVisible(false);
            } else if(e.getSource().equals(CaballoB)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("cb"));
                setVisible(false);
            } else if(e.getSource().equals(ReinaB)){
                Tablero._8x8[Movimientos.fila][Movimientos.col].setIcon(Tablero.nombreIconos.get("rb"));
                setVisible(false);
            }
        }

        @Override public void mousePressed(MouseEvent e) {}

        @Override public void mouseReleased(MouseEvent e) {}

        @Override public void mouseEntered(MouseEvent e) {}

        @Override public void mouseExited(MouseEvent e) {}
    }
    
}
