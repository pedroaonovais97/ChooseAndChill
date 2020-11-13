import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String titulo;
    private int ano;
    private Double rating;
    private int nruser;
    private List<String> genero;
    private List<Ator> atores;
    private List<Escritor> escritores;
    private List<Diretor> diretores;

    public Filme(String titulo, int ano, Double rating, int nruser, List<String> genero, List<Ator> atores, List<Escritor> escritores, List<Diretor> diretores) {
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

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public List<Escritor> getEscritores() {
        return escritores;
    }

    public void setEscritores(List<Escritor> escritores) {
        this.escritores = escritores;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<Diretor> diretores) {
        this.diretores = diretores;
    }

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
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
