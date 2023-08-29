package Juego;
import Tablero.Tablero;
import java.awt.event.*;
import java.awt.*;
import static java.lang.Thread.*;
import javax.swing.*;

public class Movimientos extends JFrame{
    
    public static int fila = 0, col = 0, click = 0;
    public Color vacio = new Color(10,160,160);
    public Color capturar = new Color(50,220,30);
    public Color bloqueo = new Color(250,20,20);
    public Color enroque = new Color(100,100,100);
    public ImageIcon parcial;
    public int parcialY, parcialX;
    public Coronacion coronar = new Coronacion();
    
    public void asignarEventos(){
        for (int i = 0; i < Tablero._8x8.length; i++) {
            for (int j = 0; j < Tablero._8x8[i].length; j++) {
                Tablero._8x8[i][j].addMouseListener(new Piezas());
            } 
        }
    }
    
    class Piezas implements MouseListener{

        @Override public void mouseClicked(MouseEvent e) {
            for (int i=0, f=7; i < Tablero._8x8.length; i++, f--) {
                for (int j=0; j < Tablero._8x8[i].length; j++) {
                    if(e.getSource().equals(Tablero._8x8[i][j])){
                        click++;
                        if(Tablero._8x8[i][j].getIcon()!=null && click==1){
                            fila = i;
                            col = j;
                            MovimientosPosibles();
                        } else if(click>1){
                            fila = i;
                            col = j;
                            click=0;
                            MoverPieza();
                            alPaso();
                            if(Tablero._8x8[fila][col].getIcon()==null){
                                if(parcial.equals(Tablero.nombreIconos.get("kn"))){
                                    Enroque(Tablero.nombreIconos.get("tn"));
                                } else if(parcial.equals(Tablero.nombreIconos.get("kb"))){
                                    Enroque(Tablero.nombreIconos.get("tb"));
                                } 
                            }
                            if(Tablero._8x8[fila][col].getIcon()!=null && (parcial.equals(Tablero.nombreIconos.get("pb")) || 
                                    parcial.equals(Tablero.nombreIconos.get("pn")))){
                                if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("pb")) && fila==0){
                                    coronar.mostrarPanel(fila);
                                } else if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("pn")) && fila==7){
                                    coronar.mostrarPanel(fila);
                                }
                            }
                            ReestablecerColor();
                        }
                    }
                } 
            }
        }
        
        public void MovimientosPosibles(){
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("pb"))){
                parcial = Tablero.nombreIconos.get("pb");
                parcialY = fila;
                parcialX = col;
                if(col==0){
                    if(Tablero._8x8[fila-1][col+1].getIcon()!=null){
                        Amigo_Rival((fila-1),(col+1));
                        if(Tablero._8x8[fila-1][col].getIcon()!=null){
                            ColorBloqueado((fila-1),col);
                        } else {
                            ColorVacio(fila-1,col);
                        }
                    }
                    if(Tablero._8x8[fila-1][col].getIcon()!=null){
                        ColorBloqueado((fila-1),col);
                    } else if(fila==6){
                        if(Tablero._8x8[fila-2][col].getIcon()!=null){
                            ColorVacio(fila-1,col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila-i),col);
                            }
                        }
                    } else {
                        if(fila<6){
                            ColorVacio((fila-1),col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila-i),col);
                            }
                        }  
                    }
                } else if(col==7){
                    if(Tablero._8x8[fila-1][col-1].getIcon()!=null){
                        Amigo_Rival((fila-1),col-1);
                        if(Tablero._8x8[fila-1][col].getIcon()!=null){
                            ColorBloqueado((fila-1),col);
                        } else {
                            ColorVacio(fila-1,col);
                        }
                    }
                    if(Tablero._8x8[fila-1][col].getIcon()!=null){
                        ColorBloqueado((fila-1),col);
                    } else if(fila==6){
                        if(Tablero._8x8[fila-2][col].getIcon()!=null){
                            ColorVacio(fila-1,col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila-i),col);
                            }
                        }
                    } else {
                        if(fila<6){
                            ColorVacio((fila-1),col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila-1),col);
                            }
                        }  
                    }
                } else {
                    if(fila==6){
                        if(Tablero._8x8[fila-1][col-1].getIcon()!=null){
                            Amigo_Rival((fila-1),col-1);
                            if(Tablero._8x8[fila-1][col+1].getIcon()!=null){
                                Amigo_Rival((fila-1),col+1);
                            }
                        }
                        if(Tablero._8x8[fila-1][col].getIcon()!=null){
                            ColorBloqueado((fila-1),col);
                        } else if(Tablero._8x8[fila-2][col].getIcon()!=null){
                            ColorVacio(fila-1,col);
                        }else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila-i),col);
                            }
                        }
                    } else if(fila==3 && (Tablero._8x8[fila][col-1].getIcon()!=null &&
                            Tablero._8x8[fila-1][col-1].getIcon()==null)){
                        if(Tablero._8x8[fila][col-1].getIcon().equals(Tablero.nombreIconos.get("pn"))){
                            ColorEnroque((fila-1),(col-1));
                            if(Tablero._8x8[fila][col+1].getIcon()!=null &&
                                    Tablero._8x8[fila-1][col+1].getIcon()==null){
                                if(Tablero._8x8[fila][col+1].getIcon().equals(Tablero.nombreIconos.get("pn"))){
                                    ColorEnroque((fila-1),(col+1));
                                    if(Tablero._8x8[fila-1][col].getIcon()==null){
                                        ColorVacio((fila-1),col);
                                    } else {
                                        ColorBloqueado((fila-1),col);
                                    }
                                }
                            } else {
                                if(Tablero._8x8[fila-1][col+1].getIcon()!=null){
                                    Amigo_Rival((fila-1),(col+1));
                                } 
                                if(Tablero._8x8[fila-1][col].getIcon()==null){
                                    ColorVacio((fila-1),col);
                                } else {
                                    ColorBloqueado((fila-1),col);
                                }
                            }
                        } 
                    } else if(fila==3 && (Tablero._8x8[fila][col+1].getIcon()!=null &&
                            Tablero._8x8[fila-1][col+1].getIcon()==null)){
                        if(Tablero._8x8[fila][col+1].getIcon().equals(Tablero.nombreIconos.get("pn"))){
                            ColorEnroque((fila-1),(col+1));
                            if(Tablero._8x8[fila][col-1].getIcon()!=null &&
                                    Tablero._8x8[fila-1][col-1].getIcon()==null){
                                if(Tablero._8x8[fila][col+1].getIcon().equals(Tablero.nombreIconos.get("pn"))){
                                    ColorEnroque((fila-1),(col-1));
                                    if(Tablero._8x8[fila-1][col].getIcon()==null){
                                        ColorVacio((fila-1),col);
                                    } else {
                                        ColorBloqueado((fila-1),col);
                                    }
                                }
                            } else {
                                if(Tablero._8x8[fila-1][col-1].getIcon()!=null){
                                    Amigo_Rival((fila-1),(col-1));
                                } 
                                if(Tablero._8x8[fila-1][col].getIcon()==null){
                                    ColorVacio((fila-1),col);
                                } else {
                                    ColorBloqueado((fila-1),col);
                                }
                            }
                        } 
                    } else if(Tablero._8x8[fila-1][col-1].getIcon()!=null){
                        Amigo_Rival((fila-1),col-1);
                        if(Tablero._8x8[fila-1][col+1].getIcon()!=null){
                            Amigo_Rival((fila-1),col+1);
                            if(Tablero._8x8[fila-1][col].getIcon()!=null){
                                ColorBloqueado((fila-1),col);
                            } else {
                                ColorVacio(fila-1,col);
                            }
                        } else {
                            if(Tablero._8x8[fila-1][col].getIcon()!=null){
                                ColorBloqueado((fila-1),col);
                            } else {
                                ColorVacio(fila-1,col);
                            }
                        }
                    } else if(Tablero._8x8[fila-1][col+1].getIcon()!=null){
                        Amigo_Rival((fila-1),col+1);
                        if(Tablero._8x8[fila-1][col].getIcon()!=null){
                            ColorBloqueado((fila-1),col);
                        } else {
                            ColorVacio(fila-1,col);
                        }
                    } else if(Tablero._8x8[fila-1][col].getIcon()!=null){
                        ColorBloqueado((fila-1),col);
                    } else if(fila<6){
                        ColorVacio(fila-1,col);
                    }
                }
            }
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("pn"))){
                parcial = Tablero.nombreIconos.get("pn");
                parcialY = fila;
                parcialX = col;
                if(col==0){
                    if(Tablero._8x8[fila+1][col+1].getIcon()!=null){
                        Amigo_Rival((fila+1),(col+1));
                        if(Tablero._8x8[fila+1][col].getIcon()!=null){
                            ColorBloqueado((fila+1),col);
                        } else {
                            ColorVacio(fila+1,col);
                        }
                    }
                    if(Tablero._8x8[fila+1][col].getIcon()!=null){
                        ColorBloqueado((fila+1),col);
                    } else if(fila==1){
                        if(Tablero._8x8[fila+2][col].getIcon()!=null){
                            ColorVacio(fila+1,col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila+i),col);
                            }
                        }
                    } else {
                        if(fila>1){
                            ColorVacio((fila+1),col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila+i),col);
                            }
                        }  
                    }
                } else if(col==7){
                    if(Tablero._8x8[fila+1][col-1].getIcon()!=null){
                        Amigo_Rival((fila+1),col-1);
                        if(Tablero._8x8[fila+1][col].getIcon()!=null){
                            ColorBloqueado((fila+1),col);
                        } else {
                            ColorVacio(fila+1,col);
                        }
                    }
                    if(Tablero._8x8[fila+1][col].getIcon()!=null){
                        ColorBloqueado((fila+1),col);
                    } else if(fila==1){
                        if(Tablero._8x8[fila+2][col].getIcon()!=null){
                            ColorVacio(fila+1,col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila+i),col);
                            }
                        }
                    } else {
                        if(fila>1){
                            ColorVacio((fila+1),col);
                        } else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila+i),col);
                            }
                        }  
                    }
                } else {
                    if(fila==1){
                        if(Tablero._8x8[fila+1][col-1].getIcon()!=null){
                            Amigo_Rival((fila+1),col-1);
                            if(Tablero._8x8[fila+1][col+1].getIcon()!=null){
                                Amigo_Rival((fila+1),col+1);
                            }
                        }
                        if(Tablero._8x8[fila+1][col].getIcon()!=null){
                            ColorBloqueado((fila+1),col);
                        } else if(Tablero._8x8[fila+2][col].getIcon()!=null){
                            ColorVacio(fila+1,col);
                        }else {
                            for (int i = 1; i < 3; i++) {
                                ColorVacio((fila+i),col);
                            }
                        }

                    } else if(fila==4 && (Tablero._8x8[fila][col-1].getIcon()!=null &&
                            Tablero._8x8[fila+1][col-1].getIcon()==null)){
                        if(Tablero._8x8[fila][col-1].getIcon().equals(Tablero.nombreIconos.get("pb"))){
                            ColorEnroque((fila+1),(col-1));
                            if(Tablero._8x8[fila][col+1].getIcon()!=null &&
                                    Tablero._8x8[fila+1][col+1].getIcon()==null){
                                if(Tablero._8x8[fila][col+1].getIcon().equals(Tablero.nombreIconos.get("pb"))){
                                    ColorEnroque((fila+1),(col+1));
                                    if(Tablero._8x8[fila+1][col].getIcon()==null){
                                        ColorVacio((fila+1),col);
                                    } else {
                                        ColorBloqueado((fila+1),col);
                                    }
                                }
                            } else {
                                if(Tablero._8x8[fila+1][col+1].getIcon()!=null){
                                    Amigo_Rival((fila+1),(col+1));
                                } 
                                if(Tablero._8x8[fila+1][col].getIcon()==null){
                                    ColorVacio((fila+1),col);
                                } else {
                                    ColorBloqueado((fila+1),col);
                                }
                            }
                        } 
                    } else if(fila==4 && (Tablero._8x8[fila][col+1].getIcon()!=null &&
                            Tablero._8x8[fila+1][col+1].getIcon()==null)){
                        if(Tablero._8x8[fila][col+1].getIcon().equals(Tablero.nombreIconos.get("pb"))){
                            ColorEnroque((fila+1),(col+1));
                            if(Tablero._8x8[fila][col-1].getIcon()!=null &&
                                    Tablero._8x8[fila+1][col-1].getIcon()==null){
                                if(Tablero._8x8[fila][col+1].getIcon().equals(Tablero.nombreIconos.get("pb"))){
                                    ColorEnroque((fila+1),(col-1));
                                    if(Tablero._8x8[fila+1][col].getIcon()==null){
                                        ColorVacio((fila+1),col);
                                    } else {
                                        ColorBloqueado((fila+1),col);
                                    }
                                }
                            } else {
                                if(Tablero._8x8[fila+1][col-1].getIcon()!=null){
                                    Amigo_Rival((fila+1),(col-1));
                                } 
                                if(Tablero._8x8[fila+1][col].getIcon()==null){
                                    ColorVacio((fila+1),col);
                                } else {
                                    ColorBloqueado((fila+1),col);
                                }
                            }
                        } 
                    } else if(Tablero._8x8[fila+1][col-1].getIcon()!=null){
                        Amigo_Rival((fila+1),col-1);
                        if(Tablero._8x8[fila+1][col+1].getIcon()!=null){
                            Amigo_Rival((fila+1),col+1);
                            if(Tablero._8x8[fila+1][col].getIcon()!=null){
                                ColorBloqueado((fila+1),col);
                            } else {
                                ColorVacio(fila+1,col);
                            }
                        } else {
                            if(Tablero._8x8[fila+1][col].getIcon()!=null){
                                ColorBloqueado((fila+1),col);
                            } else {
                                ColorVacio(fila+1,col);
                            }
                        }
                    } else if(Tablero._8x8[fila+1][col+1].getIcon()!=null){
                        Amigo_Rival((fila+1),col+1);
                        if(Tablero._8x8[fila+1][col].getIcon()!=null){
                            ColorBloqueado((fila-1),col);
                        } else {
                            ColorVacio(fila+1,col);
                        }
                    } else if(Tablero._8x8[fila+1][col].getIcon()!=null){
                        ColorBloqueado((fila+1),col);
                    } else if(fila>1){
                        ColorVacio(fila+1,col);
                    }
                }
            }            
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("kn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("kb"))){
                parcial = (ImageIcon) Tablero._8x8[fila][col].getIcon();
                parcialY = fila;
                parcialX = col;
                if(fila==0 && (col>0 && col<7)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila),(col+i));
                    }
                    for (int i = -1; i < 2; i++) {
                        SeleccionPieza((fila+1),(col+i));
                    }
                } else if(fila==7 && (col>0 && col<7)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila),(col+i));
                    }
                    for (int i = -1; i < 2; i++) {
                        SeleccionPieza((fila-1),(col+i));
                    }
                } else if((fila>0 && fila<7) && (col>0 && col<7)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila),(col+i));
                    }
                    for (int i = -1; i < 2; i++) {
                        SeleccionPieza((fila-1),(col+i));
                    }
                    for (int i = -1; i < 2; i++) {
                        SeleccionPieza((fila+1),(col+i));
                    }
                } else if(col==0 && (fila>0 && fila<7)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col));
                    }
                    for (int i = -1; i < 2; i++) {
                        SeleccionPieza((fila+i),(col+1));
                    }
                } else if(col==7 && (fila>0 && fila<7)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col));
                    }
                    for (int i = -1; i < 2; i++) {
                        SeleccionPieza((fila+i),(col-1));
                    }
                } else if(fila==0 && col==0){
                    SeleccionPieza((fila),(col+1));
                    SeleccionPieza((fila+1),(col));
                    SeleccionPieza((fila+1),(col+1));
                } else if(fila==0 && col==7){
                    SeleccionPieza((fila),(col-1));
                    SeleccionPieza((fila+1),(col));
                    SeleccionPieza((fila+1),(col-1));
                } else if(fila==7 && col==0){
                    SeleccionPieza((fila),(col+1));
                    SeleccionPieza((fila-1),(col));
                    SeleccionPieza((fila-1),(col+1));
                } else if(fila==7 && col==7){
                    SeleccionPieza((fila),(col-1));
                    SeleccionPieza((fila-1),(col));
                    SeleccionPieza((fila-1),(col-1));
                }
                if(parcial.equals(Tablero.nombreIconos.get("kn")) && col==4 &&
                        (Tablero._8x8[0][col-4].getIcon().equals(Tablero.nombreIconos.get("tn"))) &&
                        Tablero._8x8[0][col-3].getIcon()==null && Tablero._8x8[0][col-2].getIcon()==null &&
                        Tablero._8x8[0][col-1].getIcon()==null){
                    ColorEnroque(0,(col-2));
                } 
                if(parcial.equals(Tablero.nombreIconos.get("kn")) && col==4 &&
                        (Tablero._8x8[0][col+3].getIcon().equals(Tablero.nombreIconos.get("tn"))) &&
                        Tablero._8x8[0][col+2].getIcon()==null && Tablero._8x8[0][col+1].getIcon()==null){
                    ColorEnroque(0,(col+2));
                }
                if(parcial.equals(Tablero.nombreIconos.get("kb")) && col==4 &&
                        (Tablero._8x8[7][col-4].getIcon().equals(Tablero.nombreIconos.get("tb"))) &&
                        Tablero._8x8[7][col-3].getIcon()==null && Tablero._8x8[7][col-2].getIcon()==null &&
                        Tablero._8x8[7][col-1].getIcon()==null){
                    ColorEnroque(7,(col-2));
                } 
                if(parcial.equals(Tablero.nombreIconos.get("kb")) && col==4 &&
                        (Tablero._8x8[7][col+3].getIcon().equals(Tablero.nombreIconos.get("tb"))) &&
                        Tablero._8x8[7][col+2].getIcon()==null && Tablero._8x8[7][col+1].getIcon()==null){
                    ColorEnroque(7,(col+2));
                }  
            }
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("cn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("cb"))){
                parcial = (ImageIcon) Tablero._8x8[fila][col].getIcon();
                parcialY = fila;
                parcialX = col;
                if(fila==0 && col==1){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                    }
                    SeleccionPieza((fila+1),(col+2));
                } else if(fila==0 && col==6){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                    }
                    SeleccionPieza((fila+1),(col-2));
                } else if(fila==0 && col==0){
                    SeleccionPieza((fila+2),(col+1));
                    SeleccionPieza((fila+1),(col+2));
                } else if(fila==0 && col==7){
                    SeleccionPieza((fila+2),(col-1));
                    SeleccionPieza((fila+1),(col-2));
                } else if (fila==1 && col==1){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                        SeleccionPieza((fila+i),(col+2));
                    }
                } else if (fila==1 && col==6){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                        SeleccionPieza((fila+i),(col-2));
                    }
                } else if (fila==1 && col==0){
                    SeleccionPieza((fila+2),(col+1));
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col+2));
                    }
                } else if (fila==1 && col==7){
                    SeleccionPieza((fila+2),(col-1));
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col-2));
                    }
                } else if(fila==0 && (col>1 && col<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                        SeleccionPieza((fila+1),(col+(i*2)));
                    }
                } else if(fila==1 && (col>1 && col<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                        SeleccionPieza((fila+1),(col+(i*2)));
                        SeleccionPieza((fila-1),(col+(i*2)));
                    }
                } else if(col==0 && (fila>1 && fila<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col+2));
                        SeleccionPieza((fila+(i*2)),(col+1));
                    }
                } else if(col==1 && (fila>1 && fila<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col+2));
                        SeleccionPieza((fila+(i*2)),(col+1));
                        SeleccionPieza((fila+(i*2)),(col-1));
                    }
                } if(fila==7 && col==1){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila-2),(col+i));
                    }
                    SeleccionPieza((fila-1),(col+2));
                } else if(fila==7 && col==6){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila-2),(col+i));
                    }
                    SeleccionPieza((fila-1),(col-2));
                } else if(fila==7 && col==0){
                    SeleccionPieza((fila-2),(col+1));
                    SeleccionPieza((fila-1),(col+2));
                } else if(fila==7 && col==7){
                    SeleccionPieza((fila-2),(col-1));
                    SeleccionPieza((fila-1),(col-2));
                } else if (fila==6 && col==1){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila-2),(col+i));
                        SeleccionPieza((fila+i),(col+2));
                    }
                } else if (fila==6 && col==6){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila-2),(col+i));
                        SeleccionPieza((fila+i),(col-2));
                    }
                } else if (fila==6 && col==0){
                    SeleccionPieza((fila-2),(col+1));
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col+2));
                    }
                } else if (fila==6 && col==7){
                    SeleccionPieza((fila-2),(col-1));
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col-2));
                    }
                } else if(fila==7 && (col>1 && col<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila-2),(col+i));
                        SeleccionPieza((fila-1),(col+(i*2)));
                    }
                } else if(fila==6 && (col>1 && col<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila-2),(col+i));
                        SeleccionPieza((fila+1),(col+(i*2)));
                        SeleccionPieza((fila-1),(col+(i*2)));
                    }
                } else if(col==7 && (fila>1 && fila<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col-2));
                        SeleccionPieza((fila+(i*2)),(col-1));
                    }
                } else if(col==6 && (fila>1 && fila<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+i),(col-2));
                        SeleccionPieza((fila+(i*2)),(col+1));
                        SeleccionPieza((fila+(i*2)),(col-1));
                    }
                } else if((fila>1 && fila<6) && (col>1 && col<6)){
                    for (int i = -1; i < 2; i+=2) {
                        SeleccionPieza((fila+2),(col+i));
                        SeleccionPieza((fila-2),(col+i));
                        SeleccionPieza((fila+i),(col+2));
                        SeleccionPieza((fila+i),(col-2));
                    }
                }
            }
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("an")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("ab"))){
                MovAlfil();
            }
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("tb")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("tn"))){
                MovTorre();
            }
            
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("rn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("rb"))){
                MovTorre();
                MovAlfil();
            }
        }
        
        public void MoverPieza(){
            if(Tablero._8x8[fila][col].getBackground().equals(vacio) || 
                    Tablero._8x8[fila][col].getBackground().equals(capturar)){
                Tablero._8x8[parcialY][parcialX].setIcon(null);
                Tablero._8x8[fila][col].setIcon(parcial);
            }  
        }
        
        public void MovTorre(){
            parcial = (ImageIcon) Tablero._8x8[fila][col].getIcon();
            parcialY = fila;
            parcialX = col;

            if(fila==0){
                for (int i = 1; i < 8; i++) {
                    if(!SeleccionPieza((fila+i),(col))){
                        break;
                    }
                }
            } else if(fila==1){
                SeleccionPieza((fila-1),(col));
                for (int i = 1; i < 7; i++) {
                    if(!SeleccionPieza((fila+i),(col))){
                        break;
                    }
                }
            } else if(fila==2){
                for (int i = 1; i < 3; i++) {
                    if(!SeleccionPieza((fila-i),(col))){
                        break;
                    }
                }
                for (int i = 1; i < 6; i++) {
                    if(!SeleccionPieza((fila+i),(col))){
                        break;
                    }
                }
            } else if(fila==3){
                for (int i = 1; i < 4; i++) {
                    if(!SeleccionPieza((fila-i),(col))){
                        break;
                    }
                }
                for (int i = 1; i < 5; i++) {
                    if(!SeleccionPieza((fila+i),(col))){
                        break;
                    }
                }
            } else if(fila==4){
                for (int i = 1; i < 4; i++) {
                    if(!SeleccionPieza((fila+i),(col))){
                        break;
                    }
                }
                for (int i = 1; i < 5; i++) {
                    if(!SeleccionPieza((fila-i),(col))){
                        break;
                    }
                }
            } else if(fila==5){
                for (int i = 1; i < 3; i++) {
                    if(!SeleccionPieza((fila+i),(col))){
                        break;
                    }
                }
                for (int i = 1; i < 6; i++) {
                    if(!SeleccionPieza((fila-i),(col))){
                        break;
                    }
                }
            } else if(fila==6){
                SeleccionPieza((fila+1),(col));
                for (int i = 1; i < 7; i++) {
                    if(!SeleccionPieza((fila-i),(col))){
                        break;
                    }
                }
            } else if(fila==7){
                for (int i = 1; i < 8; i++) {
                    if(!SeleccionPieza((fila-i),(col))){
                        break;
                    }
                }
            }

            if(col==0){
                for (int i = 1; i < 8; i++) {
                    if(!SeleccionPieza((fila),(col+i))){
                        break;
                    }
                }
            } else if(col==1){
                SeleccionPieza((fila),(col-1));
                for (int i = 1; i < 7; i++) {
                    if(!SeleccionPieza((fila),(col+i))){
                        break;
                    }
                }
            } else if(col==2){
                for (int i = 1; i < 3; i++) {
                    if(!SeleccionPieza((fila),(col-i))){
                        break;
                    }
                }
                for (int i = 1; i < 6; i++) {
                    if(!SeleccionPieza((fila),(col+i))){
                        break;
                    }
                }
            } else if(col==3){
                for (int i = 1; i < 4; i++) {
                    if(!SeleccionPieza((fila),(col-i))){
                        break;
                    }
                }
                for (int i = 1; i < 5; i++) {
                    if(!SeleccionPieza((fila),(col+i))){
                        break;
                    }
                }
            } else if(col==4){
                for (int i = 1; i < 4; i++) {
                    if(!SeleccionPieza((fila),(col+i))){
                        break;
                    }
                }
                for (int i = 1; i < 5; i++) {
                    if(!SeleccionPieza((fila),(col-i))){
                        break;
                    }
                }
            } else if(col==5){
                for (int i = 1; i < 3; i++) {
                    if(!SeleccionPieza((fila),(col+i))){
                        break;
                    }
                }
                for (int i = 1; i < 6; i++) {
                    if(!SeleccionPieza((fila),(col-i))){
                        break;
                    }
                }
            } else if(col==6){
                SeleccionPieza((fila),(col+1));
                for (int i = 1; i < 7; i++) {
                    if(!SeleccionPieza((fila),(col-i))){
                        break;
                    }
                }
            } else if(col==7){
                for (int i = 1; i < 8; i++) {
                    if(!SeleccionPieza((fila),(col-i))){
                        break;
                    }
                }
            }
        }
        
        public void MovAlfil(){
            parcial = (ImageIcon) Tablero._8x8[fila][col].getIcon();
            parcialY = fila;
            parcialX = col;
            
            if(col==0){
                if(fila==0){
                    for (int i = 1; i < 8; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col+1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    for (int i = 1; i < 8; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                }
            } else if(col==1){
                if(fila==0){
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col-1));
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    SeleccionPieza((fila-1),(col-1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    SeleccionPieza((fila-1),(col-1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    SeleccionPieza((fila-1),(col-1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    SeleccionPieza((fila-1),(col-1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col-1));
                    SeleccionPieza((fila+1),(col+1));
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                }
            } else if(col==2){
                if(fila==0){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col+1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                }
            } else if(col==3){
                if(fila==0){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col+1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                }
            } else if(col==4){
                if(fila==0){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col+1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                }
            } else if(col==5){
                if(fila==0){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col+1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col+i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                }
            } else if(col==6){
                if(fila==0){
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col-1));
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col+1));
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila-1),(col+1));
                    SeleccionPieza((fila+1),(col+1));
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    SeleccionPieza((fila-1),(col+1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                }
            } else if(col==7){
                if(fila==0){
                    for (int i = 1; i < 8; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==1){
                    SeleccionPieza((fila-1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==2){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==3){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==4){
                    for (int i = 1; i < 4; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 5; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==5){
                    for (int i = 1; i < 3; i++) {
                        if(!SeleccionPieza((fila+i),(col-i))){
                            break;
                        }
                    }
                    for (int i = 1; i < 6; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==6){
                    SeleccionPieza((fila+1),(col-1));
                    for (int i = 1; i < 7; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                } else if(fila==7){
                    for (int i = 1; i < 8; i++) {
                        if(!SeleccionPieza((fila-i),(col-i))){
                            break;
                        }
                    }
                }
            }
        }
        
        public boolean SeleccionPieza(int posY, int posX){
            if(Tablero._8x8[posY][posX].getIcon()==null){
                ColorVacio(posY,posX);
                return true;
            } else {
                Amigo_Rival(posY,posX);
                return false;
            }
        }
        
        public void Enroque(ImageIcon icon){
            if(Tablero._8x8[fila][col].getBackground().equals(enroque)){
                Tablero._8x8[parcialY][parcialX].setIcon(null);
                if(col<4){
                    Tablero._8x8[fila][0].setIcon(null);
                    Tablero._8x8[fila][col].setIcon(parcial);
                    Tablero._8x8[fila][col+1].setIcon(icon);
                } else if(col>4){
                    Tablero._8x8[fila][7].setIcon(null);
                    Tablero._8x8[fila][col].setIcon(parcial);
                    Tablero._8x8[fila][col-1].setIcon(icon);
                }
                
            }
        }
        
        public void alPaso(){
            if(Tablero._8x8[fila][col].getBackground().equals(enroque)){
                Tablero._8x8[parcialY][parcialX].setIcon(null);
                if(parcialY==3){
                    Tablero._8x8[fila][col].setIcon(parcial);
                    Tablero._8x8[fila+1][col].setIcon(null);
                } else if(parcialY==4){
                    Tablero._8x8[fila][col].setIcon(parcial);
                    Tablero._8x8[fila-1][col].setIcon(null);
                }
            }
        }
        
        public void ColorVacio(int y_inicial,int x_inicial){
            Tablero._8x8[y_inicial][x_inicial].setBackground(vacio);
        }
        
        public void ColorRival(int y_inicial,int x_inicial){
            Tablero._8x8[y_inicial][x_inicial].setBackground(capturar);
        }
        
        public void ColorBloqueado(int y_inicial,int x_inicial){
            Tablero._8x8[y_inicial][x_inicial].setBackground(bloqueo);
        }
        
        public void ColorEnroque(int y_inicial,int x_inicial){
            Tablero._8x8[y_inicial][x_inicial].setBackground(enroque);
        }
        
        public void ReestablecerColor(){
            for (int i = 0; i < Tablero._8x8.length; i++) {
                for (int j = 0; j < Tablero._8x8[i].length; j++) {
                    if(i%2==0){
                        Tablero._8x8[i][j].setBackground(new Color(150,60,60));
                        j++;
                    } else{
                        Tablero._8x8[i][j+1].setBackground(new Color(150,60,60));
                        j++;
                    }
                }
            }
            for (int i = 0; i < Tablero._8x8.length; i++) {
                for (int j = 0; j < Tablero._8x8[i].length; j++) {
                    if(i%2!=0){
                        Tablero._8x8[i][j].setBackground(new Color(215,215,50));
                        j++;
                    } else{
                        Tablero._8x8[i][j+1].setBackground(new Color(215,215,50));
                        j++;
                    }
                }
            }
        }
        
        public void Amigo_Rival(int posY,int posX){
            if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("pb")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("tb")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("cb")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("ab")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("rb")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("kb"))){
                if(Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("pb")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("tb")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("cb")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("ab")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("rb")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("kb"))){
                    ColorBloqueado(posY,posX);
                } else {
                    ColorRival(posY,posX);
                }
            } else if(Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("pn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("tn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("cn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("an")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("rn")) ||
                    Tablero._8x8[fila][col].getIcon().equals(Tablero.nombreIconos.get("kn"))){
                if(Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("pn")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("tn")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("cn")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("an")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("rn")) ||
                        Tablero._8x8[posY][posX].getIcon().equals(Tablero.nombreIconos.get("kn"))){
                    ColorBloqueado(posY,posX);
                } else {
                    ColorRival(posY,posX);
                }
            }
        }
        
        @Override public void mousePressed(MouseEvent e) {}
            
        @Override public void mouseReleased(MouseEvent e) {}
            
        @Override public void mouseEntered(MouseEvent e) {}

        @Override public void mouseExited(MouseEvent e) {}
    }
    
}
