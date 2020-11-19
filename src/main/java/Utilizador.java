import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utilizador implements Serializable {

    private String username;
    private String password;
    private Map<String,Filme> filmes;
    private Map<String,Integer> atores;
    private Map<String,Integer> escritores;
    private Map<String,Integer> diretores;
    private Map<String,Integer> generos;

    public Utilizador(String username, String password, Map<String, Filme> filmes,
                      Map<String, Integer> atores, Map<String, Integer> escritores, Map<String,
            Integer> diretores, Map<String, Integer> generos) {
        this.username = username;
        this.password = password;
        this.filmes = filmes;
        this.atores = atores;
        this.escritores = escritores;
        this.diretores = diretores;
        this.generos = generos;
    }

    public Utilizador(){
        username = "";
        password = "";
        filmes = new HashMap<String, Filme>();
        atores = new HashMap<String, Integer>();
        escritores = new HashMap<String, Integer>();
        diretores = new HashMap<String, Integer>();
        generos = new HashMap<String, Integer>();
    }

    public Map<String, Integer> getGeneros() {
        return generos;
    }

    public void setGeneros(Map<String, Integer> generos) {
        this.generos = generos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(Map<String, Filme> filmes) {
        this.filmes = filmes;
    }

    public Map<String, Integer> getAtores() {
        return atores;
    }

    public void setAtores(Map<String, Integer> atores) {
        this.atores = atores;
    }

    public Map<String, Integer> getEscritores() {
        return escritores;
    }

    public void setEscritores(Map<String, Integer> escritores) {
        this.escritores = escritores;
    }

    public Map<String, Integer> getDiretores() {
        return diretores;
    }

    public void setDiretores(Map<String, Integer> diretores) {
        this.diretores = diretores;
    }

    public void addFilme(Filme a){
        this.filmes.put(a.getTitulo(),a);
    }

    public void removeFilme(String a){
        this.filmes.remove(a);
    }

    public void atualizaInfo() {
        for (Map.Entry<String, Filme> a : filmes.entrySet()) {
            for (String at : a.getValue().getAtores()) {
                if (atores.containsKey(at)) {
                    int i = this.atores.get(at);
                    i++;
                    this.atores.put(at, i);
                } else {
                    this.atores.put(at, 1);
                }
            }
        }

        for (Map.Entry<String, Filme> a : filmes.entrySet()) {
            for (String at : a.getValue().getDiretores()) {
                if (diretores.containsKey(at)) {
                    int i = diretores.get(at);
                    i++;
                    diretores.put(at, i);
                } else {
                    diretores.put(at, 1);
                }
            }
        }

        for (Map.Entry<String, Filme> a : filmes.entrySet()) {
            for (String at : a.getValue().getEscritores()) {
                if (escritores.containsKey(at)) {
                    int i = escritores.get(at);
                    i++;
                    escritores.put(at, i);
                } else {
                    escritores.put(at, 1);
                }
            }
        }

        for (Map.Entry<String, Filme> a : this.filmes.entrySet()) {
            for (String at : a.getValue().getGenero()) {
                if (this.generos.containsKey(at)) {
                    int i = this.generos.get(at);
                    i++;
                    this.generos.put(at, i);
                } else {
                    this.generos.put(at, 1);
                }
            }
        }
    }

    public ArrayList<String> atorFavorito(){
        int count = 0;
        ArrayList<String> ator = new ArrayList<String>();
        for(Map.Entry<String,Integer> e : this.atores.entrySet()){
            if(e.getValue() > count){
                count = e.getValue();
            }
        }

        for(Map.Entry<String,Integer> e : this.atores.entrySet()){
            if(e.getValue() == count){
                ator.add(e.getKey());
            }
        }
        return ator;
    }

    public String generoFavorito(){
        int count = 0;
        String genero = new String();
        for(Map.Entry<String,Integer> e : this.generos.entrySet()){
            if(e.getValue() > count){
                count = e.getValue();
                genero = String.valueOf(e.getKey());
            }
        }
        return genero;
    }

    public String escritorFavorito(){
        int count = 0;
        String escritor = new String();
        for(Map.Entry<String,Integer> e : this.escritores.entrySet()){
            if(e.getValue() > count){
                count = e.getValue();
                escritor = String.valueOf(e.getKey());
            }
        }
        return escritor;
    }

    public String diretorFavorito(){
        int count = 0;
        String diretor = new String();
        for(Map.Entry<String,Integer> e : this.diretores.entrySet()){
            if(e.getValue() > count){
                count = e.getValue();
                diretor = String.valueOf(e.getKey());
            }
        }
        return diretor;
    }

    public boolean validaCredenciais(String user, String pass){
        if(this.username.equals(user) && this.password.equals(pass))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", filmes=" + filmes +
                ", atores=" + atores +
                ", escritores=" + escritores +
                ", diretores=" + diretores +
                '}';
    }
}
