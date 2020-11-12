import java.util.ArrayList;
import java.util.List;

public class Diretor {
    private String nome;
    private List<String> filmes;

    public  Diretor(){
        this.nome = "";
        this.filmes = new ArrayList<String>();
    }

    public Diretor(String nome, List<String> filmes) {
        this.nome = nome;
        this.filmes = filmes;
    }

    public Diretor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addFilme(String a){
        this.filmes.add(a);
    }

    @Override
    public String toString() {
        return "Diretor{" +
                "nome='" + nome + '\'' +
                ", filmes=" + filmes +
                '}';
    }
}


