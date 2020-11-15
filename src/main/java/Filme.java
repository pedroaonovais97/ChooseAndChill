import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Filme implements Serializable {
    private String titulo;
    private int ano;
    private Double rating;
    private int nruser;
    private List<String> genero;
    private List<String> atores;
    private List<String> escritores;
    private List<String> diretores;

    public Filme(String titulo, int ano, Double rating, int nruser, List<String> genero, List<String> atores, List<String> escritores, List<String> diretores) {
        this.titulo = titulo;
        this.ano = ano;
        this.rating = rating;
        this.nruser = nruser;
        this.genero = genero;
        this.atores = atores;
        this.escritores = escritores;
        this.diretores = diretores;
    }

    public Filme() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getNruser() {
        return nruser;
    }

    public void setNruser(int nruser) {
        this.nruser = nruser;
    }

    public List<String> getGenero() {
        return genero;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }

    public List<String> getEscritores() {
        return escritores;
    }

    public void setEscritores(List<String> escritores) {
        this.escritores = escritores;
    }

    public List<String> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<String> diretores) {
        this.diretores = diretores;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public boolean containsGenero(String a){
        if(this.genero.contains(a)){
            return true;
        }else{
            return false;
        }
    }

    public boolean containsAtor(String a){
        if(this.atores.contains(a)){
            return true;
        }else{
            return false;
        }
    }

    public boolean containsDiretor(String a){
        if(this.diretores.contains(a)){
            return true;
        }else{
            return false;
        }
    }

    public boolean containsEscritor(String a){
        if(this.escritores.contains(a)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", rating=" + rating +
                ", nruser=" + nruser +
                ", genero=" + genero +
                ", atores=" + atores +
                ", escritores=" + escritores +
                ", diretores=" + diretores +
                '}';
    }
}
