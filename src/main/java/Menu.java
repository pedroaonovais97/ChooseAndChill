import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
        private List<String> utilizadorChoices;
        private List<String> adminChoices;
        private List<String> menuChoices;
        private int opt;

        public Menu(String[] uc, String[] ac, String[] mc){
            this.opt = -1;
            this.menuChoices = Arrays.asList(mc);
            this.adminChoices = Arrays.asList(ac);
            this.utilizadorChoices = Arrays.asList(uc);
        }

        public int getOpt(){
            return this.opt;
        }

        private void setOPt(int x){
            this.opt = x;
        }

        public void mMenu() {
            do {
                System.out.println("*** Menu ***");
                showMOptions();
                readOption(this.menuChoices.size());
            } while (this.opt == -1);
        }

        public void utiMenu(){
            do{
                System.out.println("*** Menu de Utilizador ***");
                showUtioptions();
                readOption(this.utilizadorChoices.size());
            }while(this.opt == -1);
        }

        public void adminMenu(){
            do{
                System.out.println("*** Menu de Administrador ***");
                showAdminoptions();
                readOption(this.adminChoices.size());
            }while(this.opt == -1);
        }

        public void showUtioptions(){
            for(int i = 0; i < this.utilizadorChoices.size(); i ++){
                System.out.printf("%d-", i+1);
                System.out.println(this.utilizadorChoices.get(i));
            }
            System.out.println("0-Exit");
        }

        public void showAdminoptions(){
            for(int i = 0; i < this.adminChoices.size(); i ++){
                System.out.printf("%d-", i+1);
                System.out.println(this.adminChoices.get(i));
            }
            System.out.println("0-Exit");
        }

        public void showMOptions(){

            for(int i = 0; i < this.menuChoices.size(); i ++){
                System.out.printf("%d-", i+1);
                System.out.println(this.menuChoices.get(i));
            }
            System.out.println("0-Exit");
        }

        public void readOption(int size){

            try{
                Scanner in = new Scanner(System.in);
                System.out.print("Escolha: ");
                this.opt = in.nextInt();
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
            if(this.opt < 0 || this.opt > size) this.opt = -1;
        }
}
