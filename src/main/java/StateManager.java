import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StateManager implements Serializable {

    private Map<String,Filme> top250;
    private Map<String,Filme> topPopular;
    private Map<String,Filme> totalfilmes;
    private Map<String,Ator> atores;
    private Map<String,Diretor> diretores;
    private Map<String,Escritor> escritores;
    private Map<String,Utilizador> utilizadores;

    public StateManager(){
        this.top250 = new HashMap<String, Filme>();
        this.topPopular = new HashMap<String, Filme>();
        this.totalfilmes = new HashMap<String, Filme>();
        this.atores = new HashMap<String, Ator>();
        this.diretores = new HashMap<String, Diretor>();
        this.escritores = new HashMap<String, Escritor>();
        this.utilizadores = new HashMap<String, Utilizador>();
    }

    public StateManager(Map<String, Filme> top250, Map<String, Filme> topPopular, Map<String, Ator> atores, Map<String,
            Diretor> diretores, Map<String, Escritor> escritores, Map<String,Utilizador> utilizadores) {
        this.top250 = top250;
        this.topPopular = topPopular;
        this.atores = atores;
        this.diretores = diretores;
        this.escritores = escritores;
        this.utilizadores = utilizadores;
    }

    public Map<String, Filme> getTop250() {
        return top250;
    }

    public void setTop250(Map<String, Filme> top250) {
        this.top250 = top250;
    }

    public Map<String, Filme> getTopPopular() {
        return topPopular;
    }

    public void setTopPopular(Map<String, Filme> topPopular) {
        this.topPopular = topPopular;
    }

    public Map<String, Ator> getAtores() {
        return atores;
    }

    public void setAtores(Map<String, Ator> atores) {
        this.atores = atores;
    }

    public Map<String, Diretor> getDiretores() {
        return diretores;
    }

    public void setDiretores(Map<String, Diretor> diretores) {
        this.diretores = diretores;
    }

    public Map<String, Escritor> getEscritores() {
        return escritores;
    }

    public void setEscritores(Map<String, Escritor> escritores) {
        this.escritores = escritores;
    }

    public Map<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public Map<String, Filme> getTotalfilmes() {
        return totalfilmes;
    }

    public void setTotalfilmes(Map<String, Filme> totalfilmes) {
        this.totalfilmes = totalfilmes;
    }

    public void addFilme250(Filme filme){
        if(!(this.top250.containsKey(filme.getTitulo())))
            this.top250.put(filme.getTitulo(), filme);
        if(!(this.totalfilmes.containsKey(filme.getTitulo())))
            this.totalfilmes.put(filme.getTitulo(), filme);
    }

    public void addFilmePopular(Filme filme){
        if(!(this.topPopular.containsKey(filme.getTitulo())))
            this.topPopular.put(filme.getTitulo(), filme);
        if(!(this.totalfilmes.containsKey(filme.getTitulo())))
            this.totalfilmes.put(filme.getTitulo(), filme);
    }

    public void addAtor(Ator ator){
        this.atores.put(ator.getNome(),ator);
    }

    public void addDiretor(Diretor dir){
        this.diretores.put(dir.getNome(),dir);
    }

    public void addEscritor(Escritor esc){
        this.escritores.put(esc.getNome(),esc);
    }

    public void addUtilizador(Utilizador u){
        this.utilizadores.put(u.getUsername(),u);
    }

    @Override
    public String toString() {
        return "StateManager{" +
                "top250=" + top250 +
                ", topPopular=" + topPopular +
                ", atores=" + atores +
                ", diretores=" + diretores +
                ", escritores=" + escritores +
                ", utilizadores=" + utilizadores +
                '}';
    }
}
