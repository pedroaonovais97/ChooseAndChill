import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Scanner;

public class Scraper {

    private StateManager curState;
    private Utilizador utilizador;
    private Admin administrador;
    private Menu appMenu;
    private static int count = 0;


    public static void main(String[] args) {
        Scraper app = new Scraper();
        //app.criar();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Estado.dat"));
            app.curState = (StateManager)ois.readObject();
            ois.close();
            for(Map.Entry<String,Ator> a : app.curState.getAtores().entrySet())
                System.out.println(a);
            //System.out.println(app.curState.pickRandombyGenre("Action"));

        }catch (Exception e) {
            System.out.println("Não está carregado");
        }
    }

    private Scraper() {
    }

    /*
    public void criar() {
        try {
            Reader readlogs = new Reader();
            this.curState = new StateManager();
            readlogs.lerdocumentotop250(this.curState);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.dat"));
            oos.writeObject(this.curState);
            oos.flush();
            oos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
/*
    public void run(boolean loggedIn){
        do{
            if(!loggedIn)
                loggedIn = this.
        }
    }*/

    public boolean menuActions(){
        boolean login = false;

        this.appMenu.mMenu();
        switch (this.appMenu.getOpt()){
            case 1:
                Scanner input = new Scanner(System.in);
                System.out.println("Email: ");
                String aux = input.nextLine();
                if(aux.equals("Pedro Novais"))
                    login = loginA(aux);
                else
                    login = loginU(aux);
                if(login = true)
                    System.out.println("Login aceite");
                else
                    System.out.println("Login não aceite");
                break;
            case 2:


        }
    }

    public boolean loginU(String a){
        Scanner input = new Scanner(System.in);
        System.out.println("Password: ");
        String password = input.nextLine();

        this.utilizador = this.curState.getUtilizadores().get(a);
        return (this.utilizador.validaCredenciais(a,password));
    }

    public boolean loginA(String a){
        Scanner input = new Scanner(System.in);
        System.out.println("Password: ");
        String password = input.nextLine();

        this.administrador = this.curState.getAdministrador();
        return(administrador.validaCredenciais(a,password));
    }

    public void register(){
        Scanner input = new Scanner(System.in);
        System.out.println("Email: ");
        String email = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();

        Utilizador u = new Utilizador();
        u.setUsername(email);
        u.setPassword(password);

        

        this.curState.addUtilizador(u);
    }
}
