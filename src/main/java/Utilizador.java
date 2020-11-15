import java.util.List;
import java.util.Map;

public class Utilizador {

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

    public void atualizaInfo(){
        for(Map.Entry<String,Filme> a : this.filmes.entrySet()){
            for(String at : a.getValue().getAtores()){
                if(this.atores.containsKey(at)){
                    int i = this.atores.get(at);
                    i++;
                    this.atores.put(at,i);
                }
                else{
                    this.atores.put(at,1);
                }
            }
        }

        for(Map.Entry<String,Filme> a : this.filmes.entrySet()){
            for(String at : a.getValue().getDiretores()){
                if(this.diretores.containsKey(at)){
                    int i = this.diretores.get(at);
                    i++;
                    this.diretores.put(at,i);
                }
                else{
                    this.diretores.put(at,1);
                }
            }
        }

        for(Map.Entry<String,Filme> a : this.filmes.entrySet()){
            for(String at : a.getValue().getEscritores()){
                if(this.escritores.containsKey(at)){
                    int i = this.escritores.get(at);
                    i++;
                    this.escritores.put(at,i);
                }
                else{
                    this.escritores.put(at,1);
                }
            }
        }

        for(Map.Entry<String,Filme> a : this.filmes.entrySet()){
            for(String at : a.getValue().getGenero()){
                if(this.generos.containsKey(at)){
                    int i = this.generos.get(at);
                    i++;
                    this.generos.put(at,i);
                }
                else{
                    this.generos.put(at,1);
                }
            }
        }
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
