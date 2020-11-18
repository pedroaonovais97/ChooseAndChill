import java.util.HashMap;
import java.util.Map;

public class Admin{

    private String username;
    private String password;
    private Map<String,Utilizador> utilizadores;

    public Admin(){
        this.username = "Pedro Novais";
        this.password = "pedroa782";
        this.utilizadores = new HashMap<String, Utilizador>();
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

    public Map<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public void removeUti(String a){
        this.utilizadores.remove(a);
    }

    public boolean validaCredenciais(String user, String pass){
        if(this.username.equals(user) && this.password.equals(pass))
            return true;
        else
            return false;
    }
}
