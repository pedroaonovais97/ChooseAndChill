public class Filme {
    String titulo;
    String ano;
    String rating;
    int nruser;

    public Filme(String titulo, String ano, String rating, int nruser) {
        this.titulo = titulo;
        this.ano = ano;
        this.rating = rating;
        this.nruser = nruser;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
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

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", ano='" + ano + '\'' +
                ", rating='" + rating + '\'' +
                ", nruser=" + nruser +
                '}';
    }
}
