import java.util.List;

public class Filme {
    String titulo;
    int ano;
    String rating;
    int nruser;
    List<Ator> atores;

    public Filme(String titulo, int ano, String rating, int nruser, List<Ator> atores) {
        this.titulo = titulo;
        this.ano = ano;
        this.rating = rating;
        this.nruser = nruser;
        this.atores = atores;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", rating='" + rating + '\'' +
                ", nruser=" + nruser +
                ", atores=" + atores +
                '}';
    }
}
