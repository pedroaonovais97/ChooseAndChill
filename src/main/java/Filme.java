public class Filme {
    String titulo;
    String ano;
    String rating;
    String nruser;

    public Filme(String titulo, String ano, String rating, String nruser) {
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

    public String getNruser() {
        return nruser;
    }

    public void setNruser(String nruser) {
        this.nruser = nruser;
    }
    /*
    public void formataNrUser(String ){
        String desformatado = this.nruser;
        String parts[] = desformatado.split(" ");
        this.nruser = parts[3];
    }*/

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", ano='" + ano + '\'' +
                ", rating='" + rating + '\'' +
                ", nruser='" + nruser + '\'' +
                '}';
    }
}
