import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Scraper {

    private StateManager curState;
    private Utilizador utilizador;
    private Admin administrador;
    private Menu appMenu;
    private boolean logadoU = false;
    private boolean logadoA = false;
    private static int count = 0;


    public static void main(String[] args) {
        Scraper app = new Scraper();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Estado.dat"));
            app.curState = (StateManager)ois.readObject();
            ois.close();
        }catch (Exception e) {
            System.out.println("Não está carregado");
        }
        app.run(false);
        //app.criar();
    }

    private void criar(){
        try{
            this.curState = new StateManager();
            Reader readlogs = new Reader();
            readlogs.lerdocumentotop250(this.curState);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.dat"));
            oos.writeObject(this.curState);
            oos.flush();
            oos.close();
            System.out.println("Feito");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Scraper() {
        String[] mOps = {"Login", "Registar", "Top 250 filmes", "Filmes Populares", "Read Logs", "Testes"};
        String[] uOps = {"Sugerir Filme","Ator Favorito","Género Favorito","Escritor Favorito","Diretor Favorito","Logout"};
        String[] aOps = {"Atualizar Dados","Apagar Utilizador","Logout"};
        this.curState = new StateManager();
        this.utilizador = null;
        this.administrador = this.curState.getAdministrador();
        this.appMenu = new Menu(uOps,aOps,mOps);
    }

    public void run(boolean loggedIn)
    {
        do
        {
            if(!loggedIn)
                loggedIn = this.menuActions();

            else if(logadoU){
                loggedIn = this.userActions();
            }
            else if(logadoA){
                loggedIn = this.adminActions();
            }
        }while(this.appMenu.getOpt() != 0);
    }

    public boolean userActions(){
        boolean login = true;

        this.appMenu.utiMenu();
        switch (this.appMenu.getOpt()){
            case 1:
                System.out.println(this.curState.sugereFilme(this.utilizador));
                break;
            case 2:
                System.out.println(this.utilizador.atorFavorito());
                break;
            case 3:
                System.out.println(this.utilizador.generoFavorito());
                break;
            case 4:
                System.out.println(this.utilizador.escritorFavorito());
                break;
            case 5:
                System.out.println(this.utilizador.diretorFavorito());
                break;
            case 0:
                System.out.println("A sair...");
                save();
                login = false;
                this.logadoU = false;
                break;
        }
        return login;
    }

    public boolean adminActions(){
        boolean login = true;

        this.appMenu.adminMenu();
        switch(this.appMenu.getOpt()){
            case 1:
                Reader readlogs = new Reader();
                readlogs.lerdocumentotop250(this.curState);
                save();
                break;
            case 2:
                System.out.println("Insira o nome do Utilizador: ");
                Scanner input = new Scanner(System.in);
                String nome = input.nextLine();
                this.curState.getUtilizadores().remove(nome);
                break;
            case 3:
                save();
                login = false;
                this.logadoA = false;
                break;
            case 0:
                System.out.println("A sair...");
                save();
                login = false;
                this.logadoA = false;
                break;
        }
        return login;
    }

    public boolean menuActions(){
        boolean login = false;

        this.appMenu.mMenu();
        switch (this.appMenu.getOpt()){
            case 1:
                Scanner input = new Scanner(System.in);
                System.out.println("Username: ");
                String aux = input.nextLine();
                if(aux.equals("Pedro Novais Admin")){
                    login = loginA(aux);
                    this.logadoA = true;
                }
                else{
                    login = loginU(aux);
                    this.logadoU = true;
                }
                if(login = true)
                    System.out.println("Login aceite");
                else
                    System.out.println("Login não aceite");
                break;
            case 2:
                register();
                break;
            case 3:
                this.curState.display250();
                break;
            case 4:
                this.curState.displayPopular();
                break;
            case 0:
                save();
                System.out.println("A sair...");
                break;
        }
        return login;
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
        Utilizador u = new Utilizador();

        Scanner input = new Scanner(System.in);
        System.out.println("Email: ");
        String email = input.nextLine();
        u.setUsername(email);

        System.out.println("Password: ");
        String password = input.nextLine();
        u.setPassword(password);

        System.out.println("Três dos seus filmes favoritos:");
        String filme1 = input.nextLine();
        String filme2 = input.nextLine();
        String filme3 = input.nextLine();

        Filme primeiro = this.curState.getTop250().get(filme1);
        System.out.println(primeiro.getTitulo());
        Filme segundo = this.curState.getTop250().get(filme2);
        System.out.println(segundo.getTitulo());
        Filme terceiro = this.curState.getTop250().get(filme3);
        System.out.println(terceiro.getTitulo());

        Map<String,Filme> mapa = new HashMap<String, Filme>();
        mapa.put(filme1,primeiro);
        mapa.put(filme2,segundo);
        mapa.put(filme3,terceiro);
        System.out.println(mapa);

        u.setFilmes(mapa);
        System.out.println(u.getFilmes());
        u.atualizaInfo();
        System.out.println(u.getAtores());

        this.curState.addUtilizador(u);
    }

    public void save()
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado.dat"));
            oos.writeObject(this.curState);
            oos.flush();
            oos.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
