import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.SourceDataLine;

public class Formiga{
    Posicao pos = new Posicao();
    Map<Integer,Posicao> caminho = new HashMap<Integer,Posicao>();
    Map<Integer,Posicao> caminhoComida = new HashMap<Integer,Posicao>();
    private static ArrayList<Map<Integer,Posicao>> caminhosComida = new ArrayList<Map<Integer,Posicao>>();
    private int vx, vy, chave=0,chaveIndex=-1;
    private int valorFormiga;

    private Status status;
    public enum Status{
        PROCURA_COMIDA,
        ENCONTROU_COMIDA,
        RASTREOU_COMIDA,
        LARGOU_COMIDA
    }

    public Formiga(int x, int y, int valorFormiga){
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
        g.drawString(""+valorFormiga+"", pos.x, pos.y);
        g.fillOval(pos.x,pos.y,5,10);
    }

    public void caminha(){
        if(status.ordinal() == 1){ //encontrou comida
            for(int i=caminho.size()-1;i>=0;i--){
                if(caminho.get(chaveIndex)==caminho.get(i)){
                    caminhoComida.put(chaveIndex,pos);
                    pos.x = caminho.get(i-1).x;
                    pos.y = caminho.get(i-1).y;
                    chaveIndex = i-1;
                    break;
                }else if(i==0){
                    pos.x = caminho.get(caminho.size()-1).x;
                    pos.y = caminho.get(caminho.size()-1).y;
                    chaveIndex = getKey(caminho.size()-1);
                }
            }
        }else if(status.ordinal() == 2){
            for(int i=0;i<=caminho.size()-1;i++){
                if(caminho.get(chaveIndex)==caminho.get(i)){
                    if(i==caminho.size()-1){
                        status = Status.ENCONTROU_COMIDA;
                        return;
                    }
                    pos.x = caminho.get(i+1).x;
                    pos.y = caminho.get(i+1).y;
                    chaveIndex = i+1;
                    return;
                }
            }
        }else if(status.ordinal()== 3){
            caminhosComida.add(caminhoComida);
            this.status = Status.PROCURA_COMIDA;

        }else{
            if(caminhosComida.contains(caminho)){
                status = Status.RASTREOU_COMIDA;
                this.chaveIndex = 0;
                return;
            }
            if(caminhosComida.size()>0){
                for(Map<Integer,Posicao> c : caminhosComida){
                    for(int i=0; i<=c.size()-1;i++){
                        if((c.get(i).x==pos.x && c.get(i).y==pos.y)){
                            this.caminho = c;
                            this.chaveIndex = i;
                            status = Status.RASTREOU_COMIDA;
                            return;
                        }
                    }
                }
            }
            Posicao posAux = new Posicao();
            posAux.x= pos.x;
            posAux.y = pos.y;
            caminho.put(chave++,posAux);
            int random_int = (int)Math.floor(Math.random()*(2-1+ 1)+1);
            vx = (int)(Math.random()*(4+1)+-3);
            vy = (int)(Math.random()*(4+1)+-3);
            if(pos.x<0 || pos.x>= 790){
            vx *= -1;
            }
            if(pos.y<0 || pos.y>= 790){
            vy *= -1;
            }
            if(random_int==1){
                pos.x -= vx;
                pos.y -= vy;
            }else{
                pos.x += vx;
                pos.y += vy;
            }
        
        }
        

        
    }

    public void setStatus(Status status){
        this.status = status;
    }
    public Status getStatus(){
        return status;
    }

    public int getKey(int key){
        if(caminho.containsKey(key))
        {
            return key;
        }
    return 0;
}
}