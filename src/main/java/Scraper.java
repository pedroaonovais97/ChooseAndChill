import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class Scraper {
    public static void main(String[] args) throws Exception{

        Reader leitor = new Reader();

        Map<String,Filme> filmestop250 = new HashMap<String, Filme>();
        Map<String,Filme> filmestoppopular = new HashMap<String, Filme>();

        Document document1 = leitor.getDocumentoTop250();
        Document document2 = leitor.getDocumentoTopPopular();

        leitor.setDocument(document1);
        leitor.setTop250(filmestop250);
        leitor.lerdocumentotop250();

        int count = 1;

        for(Map.Entry<String,Filme> e : filmestop250.entrySet()){
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
        }
    }
}
