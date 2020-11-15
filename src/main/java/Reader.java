import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Reader {

    public Reader(){
    }

    public boolean stringContainsNumber(String s ) {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }

    public void lerdocumentotop250(StateManager state) {
        try {
            Document documento1 = Jsoup.connect("http://www.imdb.com/chart/top").get();

            for (Element row : documento1.select("table.chart.full-width tr")) {

                String title = row.select(".titleColumn a").text();
                String rating = row.select(".imdbRating").text();
                String year = row.select(".SecondaryInfo").text().replaceAll("[()]", "");
                String user = row.select("strong").attr("title");
                String novolink = row.select(".titleColumn a").attr("href");
                List<String> generos = new ArrayList<String>();
                List<String> atoresstring = new ArrayList<String>();
                List<String> diretoresstring = new ArrayList<String>();
                List<String> escritoresstring = new ArrayList<String>();
                Filme filme = new Filme();

                if (!(user.equals(""))) {
                    String[] parts = user.split(" ");
                    int i = Integer.parseInt(parts[3].replace(",", ""));
                    filme.setTitulo(title);
                    filme.setAno(Integer.parseInt(year));
                    filme.setRating(Double.parseDouble(rating));
                    filme.setNruser(i);
                }

                try {

                    Document atorlink = Jsoup.connect("http://www.imdb.com" + novolink).get();
                    int countporpagina = 0;

                    String genero = atorlink.select("div.subtext").select("a").text();
                    String generosplit[] = genero.split(" ");

                    for (int i = 0; i < generosplit.length && !(stringContainsNumber(generosplit[i])); i++) {
                        generos.add(generosplit[i]);
                    }

                    filme.setGenero(generos);

                    for (Element t : atorlink.select("div.credit_summary_item")) {


                        int countportabela = 0;
                        Elements dir = t.select("a");
                        for (Element op : dir) {
                            switch (countporpagina) {
                                case 0:
                                    if (countportabela == 0) {
                                        if (state.getDiretores().containsKey(op.text())) {
                                            state.getDiretores().get((op.text())).addFilme(filme.getTitulo());
                                            diretoresstring.add(op.text());
                                        } else {
                                            Diretor diretor1 = new Diretor();
                                            diretor1.setNome(op.text());
                                            diretor1.addFilme(filme.getTitulo());
                                            state.addDiretor(diretor1);
                                            diretoresstring.add(op.text());
                                            countportabela--;
                                        }
                                    }
                                case 1:
                                    if (countportabela == 0 && !op.text().contains("more")) {
                                        if (state.getEscritores().containsKey(op.text())) {
                                            state.getEscritores().get(op.text()).addFilme(filme.getTitulo());
                                            escritoresstring.add(op.text());
                                        } else {
                                            Escritor escritor1 = new Escritor();
                                            escritor1.setNome(op.text());
                                            escritor1.addFilme(filme.getTitulo());
                                            state.addEscritor(escritor1);
                                            escritoresstring.add(op.text());
                                            countportabela--;
                                        }
                                    }
                                case 2:
                                    if (countportabela == 0 && !op.text().contains("more") && !op.text().contains("crew")) {
                                        if (state.getAtores().containsKey(op.text())) {
                                            state.getAtores().get(op.text()).addFilme(filme.getTitulo());
                                            atoresstring.add(op.text());
                                        } else {
                                            Ator ator1 = new Ator();
                                            ator1.setNome(op.text());
                                            ator1.addFilme(filme.getTitulo());
                                            state.addAtor(ator1);
                                            atoresstring.add(op.text());
                                            countportabela--;
                                        }
                                    }
                            }
                            countportabela++;
                        }
                        countporpagina++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                filme.setAtores(atoresstring);
                filme.setDiretores(diretoresstring);
                filme.setEscritores(escritoresstring);

                if (!(title.equals(""))) {
                    state.addFilme250(filme);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
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
