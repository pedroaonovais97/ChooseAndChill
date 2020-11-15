import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Escritor implements Serializable {
    private String nome;
    private List<String> filmes;

    public Escritor(){
        this.nome = "";
        this.filmes = new ArrayList<String>();
    }

    public Escritor(String nome, List<String> filmes) {
        this.nome = nome;
        this.filmes = filmes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<String> filmes) {
        this.filmes = filmes;
    }

    public void addFilme(String a){
        if(!(this.filmes.contains(a)))
            this.filmes.add(a);
    }

    @Override
    public String toString() {
        return "Escritor{" +
                "nome='" + nome + '\'' +
                ", filmes=" + filmes +
                '}';
    }
}
