import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reader {

    private Document document;
    private Map<String,Filme> top250;
    private Map<String,Filme> topPopular;

    public Reader() {
        this.document = null;
        this.top250 = new HashMap<String, Filme>();
        this.topPopular = new HashMap<String, Filme>();
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Map<String, Filme> gettop250() {
        return top250;
    }

    public void setTop250(Map<String, Filme> top250) {
        this.top250 = top250;
    }

    public void setTopPopular(Map<String, Filme> topPopular){
        this.topPopular = topPopular;
    }

    public Document getDocumentoTop250() throws IOException {
        return Jsoup.connect("http://www.imdb.com/chart/top").get();
    }

    public Document getDocumentoTopPopular() throws  IOException{
        return Jsoup.connect("http://www.imdb.com/chart/moviemeter").get();
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
                int i = Integer.parseInt(parts[3].replace(",",""));
                Filme filme = new Filme(title, year, rating, i);
                this.top250.put(title, filme);
            }
        }
    }

    public void lerdocumentotoppopular() {
        String parts[] = new String[5];

        for (Element row : this.document.select("table.chart.full-width tr")) {
            String title = row.select(".titleColumn a").text();
            String rating = row.select(".imdbRating").text();
            String year = row.select(".SecondaryInfo").text();
            String user = row.select("strong").attr("title");
            if(!(user.equals(""))) {
                parts = user.split(" ");
                int i = Integer.parseInt(parts[3].replace(",",""));
                Filme filme = new Filme(title, year, rating, i);
                this.topPopular.put(title, filme);
            }else if(user.equals("") && !(title.equals(""))){
                Filme filme = new Filme(title, year, rating, 0);
                this.topPopular.put(title, filme);
            }
        }
    }

}
