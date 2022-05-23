import java.awt.*;
import java.util.ArrayList;


public class Formigueiro {
    private int qnt_formigas;
    private ArrayList<Formiga> formigas = new ArrayList<Formiga>();
    int x,y;

    public Formigueiro (int qnt_formigas){
        this.qnt_formigas = qnt_formigas;
        x = 500;
        y = 500;
        //x = (int)(Math.random()*790+0);
        //y = (int)(Math.random()*590+0);
        inicilizaFormigas();
    }

    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x,y,100,100);
    }
    private void inicilizaFormigas(){
        for(int i=0;i<=qnt_formigas;i++){
            formigas.add(new Formiga(x,y));
        }
    }
    public void largaComida(Formiga f){
        Rectangle formigueiro = new Rectangle(x,y,100,100);
        Rectangle formiga = new Rectangle(f.pos.x,f.pos.y,10,10);

        if(formiga.intersects(formigueiro)){
            f.setStatus(Formiga.Status.PROCURA_COMIDA);
        }
    }
    public ArrayList<Formiga> getFormigas(){
        return formigas;
    }
}
