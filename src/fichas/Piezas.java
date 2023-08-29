package fichas;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Piezas {
    private static Map<String,ImageIcon> iconos;
    private Image peonB = new ImageIcon(getClass().getResource("wp.png")).getImage();
    private Image alfilB = new ImageIcon(getClass().getResource("wb.png")).getImage();
    private Image torreB = new ImageIcon(getClass().getResource("wr.png")).getImage();
    private Image caballoB = new ImageIcon(getClass().getResource("wn.png")).getImage();
    private Image reinaB = new ImageIcon(getClass().getResource("wq.png")).getImage();
    private Image reyB = new ImageIcon(getClass().getResource("wk.png")).getImage();
    private Image peonN = new ImageIcon(getClass().getResource("bp.png")).getImage();
    private Image alfilN = new ImageIcon(getClass().getResource("bb.png")).getImage();
    private Image torreN = new ImageIcon(getClass().getResource("br.png")).getImage();
    private Image caballoN = new ImageIcon(getClass().getResource("bn.png")).getImage();
    private Image reinaN = new ImageIcon(getClass().getResource("bq.png")).getImage();
    private Image reyN = new ImageIcon(getClass().getResource("bk.png")).getImage(); 
    
    public Piezas(){
        iconos = new HashMap<>();
        iconos.put("pb",new ImageIcon(peonB.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("ab",new ImageIcon(alfilB.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("tb",new ImageIcon(torreB.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("cb",new ImageIcon(caballoB.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("rb",new ImageIcon(reinaB.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("kb",new ImageIcon(reyB.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("pn",new ImageIcon(peonN.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("an",new ImageIcon(alfilN.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("tn",new ImageIcon(torreN.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("cn",new ImageIcon(caballoN.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("rn",new ImageIcon(reinaN.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
        iconos.put("kn",new ImageIcon(reyN.getScaledInstance(70,70, Image.SCALE_SMOOTH)));
    }
    
    public static Map<String, ImageIcon> getNombreIcono() {
        return iconos;
    }
    
}
