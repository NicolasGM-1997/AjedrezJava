package Tablero;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import Juego.Movimientos;
public class Tablero extends JFrame{
    
    public static JButton _8x8[][] = new JButton[8][8];
    private JPanel panel = new JPanel();
    private Movimientos mov = new Movimientos();
    public static fichas.Piezas fichas = new fichas.Piezas();
    public static Map<String, ImageIcon> nombreIconos = fichas.getNombreIcono();
    
    public Tablero(){
        panel.setVisible(true);
        panel.setBorder(new MatteBorder(15,15,15,15,new Color(80,80,80)));
        panel.setLayout(new BorderLayout());
        panel.add(pintarTablero(),BorderLayout.CENTER);
        fichas();
        mov.asignarEventos();
        repaint();    
    }
    
    public JPanel pintarTablero(){
        JPanel tablero = new JPanel();
        tablero.setVisible(true);
        tablero.setLayout(new GridLayout(8,8));
        for (int i = 0; i < _8x8.length; i++) {
            for (int j = 0; j < _8x8[i].length; j++) {
                _8x8[i][j] = new JButton("");
                tablero.add(_8x8[i][j]);
                _8x8[i][j].setSize(10,100);
                
            }
        }
        for (int i = 0; i < _8x8.length; i++) {
            for (int j = 0; j < _8x8[i].length; j++) {
                if(i%2==0){
                    _8x8[i][j].setBackground(new Color(150,60,60));
                    j++;
                } else{
                    _8x8[i][j+1].setBackground(new Color(150,60,60));
                    j++;
                }
            }
        }
        for (int i = 0; i < _8x8.length; i++) {
            for (int j = 0; j < _8x8[i].length; j++) {
                if(i%2!=0){
                    _8x8[i][j].setBackground(new Color(215,215,50));
                    j++;
                } else{
                    _8x8[i][j+1].setBackground(new Color(215,215,50));
                    j++;
                }
            }
        }
        return tablero;
    }
    
    public void fichas(){
        for (int i = 0; i < _8x8.length; i++) {
            for (int j = 0; j < _8x8[i].length; j++) {
                if(i==1){
                    _8x8[i][j].setIcon(nombreIconos.get("pn"));
                }
                if(i==0){
                    if(j==0 || j==7){
                        _8x8[i][j].setIcon(nombreIconos.get("tn"));
                    } else if(j==1 || j==6){
                        _8x8[i][j].setIcon(nombreIconos.get("cn"));
                    } else if(j==2 || j==5){
                        _8x8[i][j].setIcon(nombreIconos.get("an"));
                    } else if(j==3){
                        _8x8[i][j].setIcon(nombreIconos.get("rn"));
                    } else {
                        _8x8[i][j].setIcon(nombreIconos.get("kn"));
                    }
                }
                
                if(i==6){
                    _8x8[i][j].setIcon(nombreIconos.get("pb"));
                }
                if(i==7){
                    if(j==0 || j==7){
                        _8x8[i][j].setIcon(nombreIconos.get("tb"));
                    } else if(j==1 || j==6){
                        _8x8[i][j].setIcon(nombreIconos.get("cb"));
                    } else if(j==2 || j==5){
                        _8x8[i][j].setIcon(nombreIconos.get("ab"));
                    } else if(j==3){
                        _8x8[i][j].setIcon(nombreIconos.get("rb"));
                    } else {
                        _8x8[i][j].setIcon(nombreIconos.get("kb"));
                    }
                }
            }
        }
    }
    
    public void borrarTablero(){
        for (int i = 0; i < _8x8.length; i++) {
            for (int j = 0; j < _8x8[i].length; j++) {
                _8x8[i][j].setIcon(null);
            }
        }
    }
    
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }    
    
}
