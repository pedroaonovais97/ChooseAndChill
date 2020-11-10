import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class Scraper {
    public static void main(String[] args) throws Exception{

        Reader leitor = new Reader();
        Map<String,Filme> filmes = new HashMap<String, Filme>();
        Document document = leitor.getDocumentoTop250();

        leitor.setDocument(document);
        leitor.setLista(filmes);
        leitor.lerdocumentotop250();

        for(Map.Entry<String,Filme> e : filmes.entrySet()){
            System.out.println(e.getValue().toString());
        }
    }
}
