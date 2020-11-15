import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ator implements Serializable {
    private String nome;
    private List<String> filmes;

    public  Ator(){
        this.nome = "";
        this.filmes = new ArrayList<String>();
    }

    public Ator(String nome, List<String> filmes) {
        this.nome = nome;
        this.filmes = filmes;
    }

    public Ator(String nome) {
        this.nome = nome;
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
        return "Ator{" +
                "nome='" + nome + '\'' +
                ", filmes=" + filmes +
                '}';
    }
}
