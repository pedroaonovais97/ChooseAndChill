import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.StringReader;
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

        for (Element row : this.document.select("table.chart.full-width tr")) {
            String title = row.select(".titleColumn a").text();
            String rating = row.select(".imdbRating").text();
            String year = row.select(".SecondaryInfo").text().replaceAll("[()]","");
            String user = row.select("strong").attr("title");
            if(!(user.equals(""))) {
                String[] parts = user.split(" ");
                int i = Integer.parseInt(parts[3].replace(",",""));
                Filme filme = new Filme(title, Integer.parseInt(year), rating, i);
                this.top250.put(title, filme);
            }
        }
    }

    public void lerdocumentotoppopular() {

        for (Element row : this.document.select("table.chart.full-width tr")) {
            String title = row.select(".titleColumn a").text();
            String rating = row.select(".imdbRating").text();
            String year = row.select(".SecondaryInfo").text().replaceAll("[()]","");
            String user = row.select("strong").attr("title");
            if(!(user.equals(""))) {
                String[] parts = user.split(" ");
                int i = Integer.parseInt(parts[3].replace(",",""));
                String[] parts1 = year.split(" ");
                int o = Integer.parseInt(parts1[0]);
                Filme filme = new Filme(title, o, rating, i);
                this.topPopular.put(title, filme);
            }else if(!(title.equals(""))){
                String[] parts1 = year.split(" ");
                if(!(parts1[0].equals(""))){
                    int o = Integer.parseInt(parts1[0]);
                    Filme filme = new Filme(title, o, rating, 0);
                    this.topPopular.put(title, filme);
                }else{
                    int o = Integer.parseInt(parts1[1]);
                    if(o < 1800)
                        o = 0;
                    Filme filme = new Filme(title, o, rating, 0);
                    this.topPopular.put(title, filme);
                }
            }
        }
    }

}
