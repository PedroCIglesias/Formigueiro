import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.awt.geom.Ellipse2D;

import javax.sound.sampled.SourceDataLine;

public class Formiga{
    Posicao pos = new Posicao(0,0);
    Map<Integer,Posicao> caminhoFormigueiro = new HashMap<Integer,Posicao>();
    Map<Integer,Posicao> caminhoComida = new HashMap<Integer,Posicao>();
    private static ArrayList<Map<Integer,Posicao>> caminhosComida = new ArrayList<Map<Integer,Posicao>>();
    private double vx, vy;
    private int valorFormiga,chave=0,chaveIndex=-1;;

    private Status status;
    public enum Status{
        PROCURA_COMIDA,
        ENCONTRA_COMIDA,
        RASTREA_COMIDA,
        LARGA_COMIDA
    }

    public Formiga(double x, double y, int valorFormiga){
        this.pos.x= x+55;
        this.pos.y= y+55;
        this.status = Status.PROCURA_COMIDA;
        this.valorFormiga = valorFormiga;
        vx = (int)(Math.random()*(1+1)+-1);
        vy = (int)(Math.random()*(1+1)+-1);
    }

    public void paint(Graphics g){
        switch(status.ordinal()){
            case 0: 
                g.setColor(Color.RED);
                break;
            case 1:
                g.setColor(Color.GREEN);
                break;
            case 2:
                g.setColor(Color.BLUE);
                break;
        } 
        this.caminha();
        Graphics2D gg = (Graphics2D)g;
        Ellipse2D.Double shape = new Ellipse2D.Double(pos.x, pos.y, 5, 10);
        gg.drawString(""+valorFormiga,(float)pos.x,(float)pos.y);
        gg.setPaint(Color.RED);
        gg.fill(shape);
        gg.draw(shape);
        //g.fillOval(pos.x,pos.y,5,10);
    }

    public void caminha(){
        if(status.ordinal() == 1){ //encontrou comida
            for(int i=caminhoFormigueiro.size()-1;i>=0;i--){
                if(caminhoFormigueiro.get(chaveIndex)==caminhoFormigueiro.get(i)){
                    caminhoComida.put(chaveIndex,pos);
                    pos.x = caminhoFormigueiro.get(i-1).x;
                    pos.y = caminhoFormigueiro.get(i-1).y;
                    chaveIndex = i-1;
                    break;
                }else if(i==0){
                    pos.x = caminhoFormigueiro.get(caminhoFormigueiro.size()-1).x;
                    pos.y = caminhoFormigueiro.get(caminhoFormigueiro.size()-1).y;
                    chaveIndex = getKey(caminhoFormigueiro.size()-1);
                }
            }
        }else if(status.ordinal() == 2){
            for(int i=0;i<=caminhoFormigueiro.size()-1;i++){
                if(caminhoFormigueiro.get(chaveIndex)==caminhoFormigueiro.get(i)){
                    if(i==caminhoFormigueiro.size()-1){
                        status = Status.ENCONTRA_COMIDA;
                        return;
                    }
                    pos.x = caminhoFormigueiro.get(i+1).x;
                    pos.y = caminhoFormigueiro.get(i+1).y;
                    chaveIndex = i+1;
                    return;
                }
            }
        }else if(status.ordinal()== 3){
            caminhosComida.add(caminhoComida);
            this.status = Status.PROCURA_COMIDA;

        }else{
            if(caminhosComida.contains(caminhoFormigueiro)){
                status = Status.RASTREA_COMIDA;
                this.chaveIndex = 0;
                return;
            }
            if(caminhosComida.size()>0){
                for(Map<Integer,Posicao> c : caminhosComida){
                    for(int i=0; i<=c.size()-1;i++){
                        if((c.get(i).x==pos.x && c.get(i).y==pos.y)){
                            this.caminhoFormigueiro = c;
                            this.chaveIndex = i;
                            status = Status.RASTREA_COMIDA;
                            return;
                        }
                    }
                }
            }
            caminhoFormigueiro.put(chave++,new Posicao(pos.x,pos.y));
            int random_int = (int)Math.floor(Math.random()*(2-1+ 1)+1);
            double sin= Math.sin(1000*pos.x);
            double sin2= Math.sin(Math.PI*pos.x);
            sin = (sin + sin2);
            vx = sin;

            double siny= Math.sin(2*pos.y);
            double siny2= Math.sin(Math.PI*pos.y);
            siny = (siny + siny2);
            vy = siny;

            pos.x -= vx;
            pos.y -= 2;
            //vx = (int)(Math.random()*(4+1)+-3);
            //vy = (int)(Math.random()*(4+1)+-3);
            if(pos.x<0 || pos.x>= 790){
            vx *= -1;
            }
            if(pos.y<0 || pos.y>= 790){
            vy *= -1;
            }
            /*if(random_int==1){
                pos.x -= vx;
                pos.y -= vy;
            }else{
                pos.x += vx;
                pos.y += vy;
            }*/
        }
        

       
    } 
    public void caminha2(){
        switch(status.ordinal()){
            case 0:
                caminhoFormigueiro.put(chave++,new Posicao(pos.x,pos.y));
                int vaiVolta = (int)Math.floor(Math.random()*(2-1+ 1)+1);
                vx = (int)(Math.random()*(4+1)+-3);
                vy = (int)(Math.random()*(4+1)+-3);
                if(pos.x<0 || pos.x>= 790){
                vx *= -1;
                }
                if(pos.y<0 || pos.y>= 790){
                vy *= -1;
                }
                if(vaiVolta==1){
                    pos.x -= vx;
                    pos.y -= vy;
                }else{
                    pos.x += vx;
                    pos.y += vy;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public void setStatus(Status status){
        this.status = status;
    }
    public Status getStatus(){
        return status;
    }

    public int getKey(int key){
        if(caminhoFormigueiro.containsKey(key))
        {
            return key;
        }
    return 0;
}
}