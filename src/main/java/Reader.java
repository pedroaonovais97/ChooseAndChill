import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            String novolink = row.select(".titleColumn a").attr("href");

            try{

                Document atorlink = Jsoup.connect("http://www.imdb.com" + novolink).get();
                int countporpagina = 0;
                List<String> atores = new ArrayList<String>();
                List<String> diretores = new ArrayList<String>();
                List<String> escritores = new ArrayList<String>();

                for(Element t : atorlink.select("div.credit_summary_item")){

                    int countportabela = 0;
                    Elements dir = t.select("a");

                    for(Element op : dir){
                        //System.out.println(op.text() + countportabela + countporpagina);
                        switch(countporpagina){
                            case 0:
                                if(countportabela == 0){
                                    diretores.add(op.text());
                                    countportabela--;
                                }
                            case 1:
                                if(countportabela == 0 && !op.text().contains("more")){
                                    escritores.add(op.text());
                                    countportabela--;
                                }
                            case 2:
                                if(countportabela == 0 && !op.text().contains("more") && !op.text().contains("crew")){
                                    atores.add(op.text());
                                    countportabela--;
                                }
                        }
                        countportabela++;
                    }
                    countporpagina++;
                }
                System.out.println(diretores);
                System.out.println(escritores);
                System.out.println(atores);
            }catch(IOException e){
                e.printStackTrace();
            }

            if(!(user.equals(""))) {
                String[] parts = user.split(" ");
                int i = Integer.parseInt(parts[3].replace(",",""));
                Ator a = new Ator("Pedro");
                List<Ator> listaatores = new ArrayList<Ator>();
                listaatores.add(a);
                Filme filme = new Filme(title, Integer.parseInt(year), rating, i,listaatores);
                this.top250.put(title, filme);
            }
            System.out.println("--");
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
                Ator a = new Ator("Pedro");
                List<Ator> listaatores = new ArrayList<Ator>();
                listaatores.add(a);
                Filme filme = new Filme(title, o, rating, i,listaatores);
                this.topPopular.put(title, filme);
            }else if(!(title.equals(""))){
                String[] parts1 = year.split(" ");
                if(!(parts1[0].equals(""))){
                    int o = Integer.parseInt(parts1[0]);
                    Ator a = new Ator("Pedro");
                    List<Ator> listaatores = new ArrayList<Ator>();
                    listaatores.add(a);
                    Filme filme = new Filme(title, o, rating, 0,listaatores);
                    this.topPopular.put(title, filme);
                }else{
                    int o = Integer.parseInt(parts1[1]);
                    if(o < 1800)
                        o = 0;
                    Ator a = new Ator("Pedro");
                    List<Ator> listaatores = new ArrayList<Ator>();
                    listaatores.add(a);
                    Filme filme = new Filme(title, o, rating, 0,listaatores);
                    this.topPopular.put(title, filme);
                }
            }
        }
    }

}
