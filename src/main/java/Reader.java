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
import java.util.regex.Pattern;

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

    public boolean stringContainsNumber( String s ) {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }

    public void lerdocumentotop250() {

        for (Element row : this.document.select("table.chart.full-width tr")) {

            String title = row.select(".titleColumn a").text();
            String rating = row.select(".imdbRating").text();
            String year = row.select(".SecondaryInfo").text().replaceAll("[()]","");
            String user = row.select("strong").attr("title");
            String novolink = row.select(".titleColumn a").attr("href");
            List<Ator> atores = new ArrayList<Ator>();
            List<Diretor> diretores = new ArrayList<Diretor>();
            List<Escritor> escritores = new ArrayList<Escritor>();
            List<String> generos = new ArrayList<String>();
            Filme filme = new Filme();

            if(!(user.equals(""))) {
                String[] parts = user.split(" ");
                int i = Integer.parseInt(parts[3].replace(",",""));
                filme.setTitulo(title);
                filme.setAno(Integer.parseInt(year));
                filme.setRating(Double.parseDouble(rating));
                filme.setNruser(i);
            }

            try{

                Document atorlink = Jsoup.connect("http://www.imdb.com" + novolink).get();
                int countporpagina = 0;

                String genero = atorlink.select("div.subtext").select("a").text();
                String generosplit[] = genero.split(" ");

                for(int i = 0; i< generosplit.length && !(stringContainsNumber(generosplit[i])) ; i++){
                    generos.add(generosplit[i]);
                }

                filme.setGenero(generos);

                for(Element t : atorlink.select("div.credit_summary_item")){


                    int countportabela = 0;
                    Elements dir = t.select("a");
                    for(Element op : dir){
                        switch(countporpagina){
                            case 0:
                                if(countportabela == 0){
                                    Diretor diretor1 = new Diretor();
                                    diretor1.setNome(op.text());
                                    diretores.add(diretor1);
                                    countportabela--;
                                }
                            case 1:
                                if(countportabela == 0 && !op.text().contains("more")){
                                    Escritor escritor1 = new Escritor();
                                    escritor1.setNome(op.text());
                                    escritores.add(escritor1);
                                    countportabela--;
                                }
                            case 2:
                                if(countportabela == 0 && !op.text().contains("more") && !op.text().contains("crew")){
                                    Ator ator1 = new Ator();
                                    ator1.setNome(op.text());
                                    atores.add(ator1);
                                    countportabela--;
                                }
                        }
                        countportabela++;
                    }
                    countporpagina++;
                }

            }catch(IOException e){
                e.printStackTrace();
            }
            for(Ator a : atores){
                a.addFilme(filme.getTitulo());
            }
            for(Diretor a : diretores)
                a.addFilme(filme.getTitulo());
            for(Escritor a : escritores)
                a.addFilme(filme.getTitulo());

            filme.setAtores(atores);
            filme.setDiretores(diretores);
            filme.setEscritores(escritores);

            if(!(title.equals(""))){
                this.top250.put(title, filme);
                System.out.println(filme.toString());
            }
        }
    }

/*
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
    }*/

}
