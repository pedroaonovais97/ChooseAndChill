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
    private final Menu appMenu;
    private boolean logadoU;
    private boolean logadoA;

    public static void main(String[] args) {
        Scraper app = new Scraper();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Estado.dat"));
            app.curState = (StateManager)ois.readObject();
            ois.close();
        }catch (Exception e) {
            System.out.println("Não está carregado");
        }
        app.run();
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
        String[] mOps = {"Login", "Registar", "Top 250 filmes", "Filmes Populares","Print"};
        String[] uOps = {"Sugerir Filme","Adicionar Filme aos Vistos","Remover Filme dos Vistos","Ator Favorito"
                ,"Género Favorito","Escritor Favorito","Diretor Favorito","Ver Lista de Filmes","Logout"};
        String[] aOps = {"Atualizar Dados","Apagar Utilizador","Reset Programa","Logout","Ver Lista De Utilizadores"};
        this.curState = new StateManager();
        this.utilizador = null;
        this.administrador = this.curState.getAdministrador();
        this.logadoA = false;
        this.logadoU = false;
        this.appMenu = new Menu(uOps,aOps,mOps);
    }

    public void run()
    {
        do
        {
            if(!this.logadoU && !this.logadoA)
                this.menuActions();

            else if(this.logadoU){
                this.userActions();
            }
            else {
                this.adminActions();
            }
        }while(this.appMenu.getOpt() != 0);
    }

    public void userActions(){

        this.appMenu.utiMenu();
        switch (this.appMenu.getOpt()){
            case 1:
                Filme e = this.curState.sugereFilme(this.utilizador);
                System.out.println(e.getTitulo());
                System.out.println("Já viu este filme ?");
                Scanner input = new Scanner(System.in);
                System.out.println("1.Sim 2.Não");
                String nome;
                nome = e.getTitulo();
                while (input.nextInt() == 1) {
                    Filme o = this.curState.sugereFilme(this.utilizador);
                    nome = o.getTitulo();
                    System.out.println(o.getTitulo());
                    System.out.println("1.Sim 2.Não");
                }
                this.utilizador.addFilme(this.curState.getTop250().get(nome));
                break;
            case 2:
                Scanner input1 = new Scanner(System.in);
                System.out.println("Insira o filme que viu: ");
                Filme ou = this.curState.getTop250().get(input1.nextLine());
                this.utilizador.addFilme(ou);
                break;
            case 3:
                Scanner input2 = new Scanner(System.in);
                System.out.println("Insira o nome do filme que quer remover");
                String teste = input2.nextLine();
                if(this.utilizador.getFilmes().containsKey(teste)){
                    this.utilizador.removeFilme(teste);
                    System.out.println("Removido");
                    break;
                }else{
                    System.out.println("Filme Não Encontrado");
                    break;
                }
            case 4:
                System.out.println(this.utilizador.atorFavorito());
                break;
            case 5:
                System.out.println(this.utilizador.generoFavorito());
                break;
            case 6:
                System.out.println(this.utilizador.escritorFavorito());
                break;
            case 7:
                System.out.println(this.utilizador.diretorFavorito());
                break;
            case 8:
                for(Map.Entry<String,Filme> ff : this.utilizador.getFilmes().entrySet())
                    System.out.println(ff.getKey());
                break;
            case 9:
                this.logadoU = false;
                System.out.println("Logged Out");
                break;
            case 0:
                System.out.println("A sair...");
                save();
                break;
        }
    }

    public void adminActions(){

        this.appMenu.adminMenu();
        switch(this.appMenu.getOpt()){
            case 1:
                Reader readlogs = new Reader();
                readlogs.lerdocumentotop250(this.curState);
                break;
            case 2:
                System.out.println("Insira o nome do Utilizador: ");
                Scanner input = new Scanner(System.in);
                String nome = input.nextLine();
                this.curState.getUtilizadores().remove(nome);
                break;
            case 3:
                criar();
            case 4:
                this.logadoA = false;
                System.out.println("Logged Out");
                break;
            case 5:
                System.out.println(this.curState.getUtilizadores());
                break;
            case 0:
                System.out.println("A sair...");
                save();
                break;
        }
    }

    public void menuActions(){

        this.appMenu.mMenu();
        switch (this.appMenu.getOpt()){
            case 1:
                Scanner input = new Scanner(System.in);
                System.out.println("Username: ");
                String aux = input.nextLine();
                if((aux.equals("Pedro Novais Admin")) || this.curState.getUtilizadores().containsKey(aux)) {
                    if (aux.equals("Pedro Novais Admin")) {
                        loginA(aux);
                    } else {
                        loginU(aux);
                    }
                }else{
                    System.out.println("Utilizador Não Encontrado !");
                }
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
            case 5:
                System.out.println(this.curState.getTop250().get("O Padrinho"));
                System.out.println(this.curState.getTop250().get("O Padrinho: Parte II"));
                System.out.println(this.curState.getTop250().get("O Cavaleiro das Trevas"));
                break;
            case 0:
                save();
                System.out.println("A sair...");
                break;
        }
    }

    public void loginU(String a){
        Scanner input = new Scanner(System.in);
        System.out.println("Password: ");
        String password = input.nextLine();

        if(this.curState.getUtilizadores().get(a).validaCredenciais(a, password)){
            System.out.println("Login Aceite");
            this.utilizador = this.curState.getUtilizadores().get(a);
            this.logadoU = true;
        }else{
            System.out.println("Password Errada");
            this.logadoU = false;
        }
    }

    public void loginA(String a){
        Scanner input = new Scanner(System.in);
        System.out.println("Password: ");
        String password = input.nextLine();

        if(administrador.validaCredenciais(a, password)){
            System.out.println("Login Aceite");
            this.administrador = this.curState.getAdministrador();
            this.logadoA = true;
        }else{
            System.out.println("Password Errada");
            this.logadoA = false;
        }
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
        Filme segundo = this.curState.getTop250().get(filme2);
        Filme terceiro = this.curState.getTop250().get(filme3);

        Map<String,Filme> mapa = new HashMap<>();
        mapa.put(filme1,primeiro);
        mapa.put(filme2,segundo);
        mapa.put(filme3,terceiro);

        u.setFilmes(mapa);
        u.atualizaInfo();

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
