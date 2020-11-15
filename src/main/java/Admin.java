import java.util.HashMap;
import java.util.Map;

public class Admin {

    private String username;
    private String password;
    private Map<String,Utilizador> utilizadores;

    public Admin(){
        this.username = "Pedro Novais";
        this.password = "pedroa782";
        this.utilizadores = new HashMap<String, Utilizador>();
    }

    public void removeUti(String a){
        this.utilizadores.remove(a);
    }
}
