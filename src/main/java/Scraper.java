import org.jsoup.nodes.Document;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scraper {

    private StateManager curState;
    private Utilizador utilizador;


    public static void main(String[] args) {
        Scraper app = new Scraper();
        //app.criar();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Estado.dat"));
            app.curState = (StateManager)ois.readObject();
            ois.close();
            for(Map.Entry<String,Ator> a : app.curState.getAtores().entrySet())
                System.out.println(a);
            //System.out.println(app.curState.pickRandombyGenre("Action"));

        }catch (Exception e) {
            System.out.println("Não está carregado");
        }

    }

    private Scraper() {
    }

    public void criar() {
        try {
            Reader readlogs = new Reader();
            this.curState = new StateManager();
            readlogs.lerdocumentotop250(this.curState);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.dat"));
            oos.writeObject(this.curState);
            oos.flush();
            oos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


        /*
        int count = 1;

        for(Map.Entry<String,Filme> e : leitor.getTop250().entrySet()){
            System.out.println("-----");
            System.out.println(count + " -> " +e.getValue().toString());
            count++;
        }


        leitor.setDocument(document2);
        leitor.setTopPopular(filmestoppopular);
        leitor.lerdocumentotoppopular();

        System.out.println("\n----------------------------------------------\n");

        int count1 = 1;

        for(Map.Entry<String,Filme> e : filmestoppopular.entrySet()){
            System.out.println(count1 + " -> " + e.getValue().toString());
            count1++;
        }

        System.out.println("\n----------------------------------------------\n");

        for(Map.Entry<String,Filme> e : filmestoppopular.entrySet()){
            if(e.getValue().getNruser() == 0)
                System.out.println(e.getValue().toString());
        }*/
}
