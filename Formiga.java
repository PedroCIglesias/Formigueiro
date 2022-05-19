import java.awt.*;
import java.util.ArrayList;

public class Formiga{
    Posicao pos;
    ArrayList<Posicao> caminho;
    private int vx, vy;
    
    private Status status;
    public enum Status{
        PROCURA_COMIDA,
        ENCONTROU_COMIDA,
        SEGUE_COMIDA,
        VOLTA_FORMIGUEIRO
    }

    public Formiga(int x, int y){
        this.pos.x= x+55;
        this.pos.y= y+55;
        this.status = Status.PROCURA_COMIDA;

        vx = (int)(Math.random()*(10+1));
        vy = (int)(Math.random()*(10+1)+-5);
    }

    public void paint(Graphics g){
        if(status.ordinal()==0){
            g.setColor(Color.RED);
        }else
            g.setColor(Color.GREEN);

        caminho.add(pos);
        int random_int = (int)Math.floor(Math.random()*(2-1+ 1)+1);
        if(random_int==1){
            pos.x -=(int)(Math.random()*(13+1)+-7);
            pos.y -= (int)(Math.random()*(13+1)+-7);
        }else{
            pos.x +=(int)(Math.random()*(13+1)+-7);
            pos.y += (int)(Math.random()*(13+1)+-7);
        }

        if(pos.x<0 || pos.x>= 790){
            vx *= -1;
        }
        if(pos.y<0 || pos.y>= 790){
            vy *= -1;
        }

        g.fillOval(pos.x,pos.y,10,10);
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public Status getStatus(){
        return status;
    }
}