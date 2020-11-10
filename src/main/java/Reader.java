import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Map;

public class Reader {

    private Document document;
    private Map<String,Filme> lista;

    public Reader() {
    }

    public Reader(Document document, Map<String, Filme> lista) {
        this.document = document;
        this.lista = lista;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Map<String, Filme> getLista() {
        return lista;
    }

    public void setLista(Map<String, Filme> lista) {
        this.lista = lista;
    }

    public Document getDocumentoTop250() throws IOException {
        return Jsoup.connect("http://www.imdb.com/chart/top").get();
    }

    public void lerdocumentotop250() {
        String parts[] = new String[5];

        for (Element row : this.document.select("table.chart.full-width tr")) {
            String title = row.select(".titleColumn a").text();
            String rating = row.select(".imdbRating").text();
            String year = row.select(".SecondaryInfo").text();
            String user = row.select("strong").attr("title");
            if(!(user.equals(""))) {
                parts = user.split(" ");
                Filme filme = new Filme(title, year, rating, parts[3]);
                this.lista.put(title, filme);
            }
            Filme filme = new Filme(title, year, rating, parts[3]);
         }
    }
}
