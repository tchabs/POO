import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;


public class UMERApp{
    private static UMER um;
    private static Menu menu_principal,menu_registo,menu_motorista,menu_motoristaEmp,
                   menu_cliente,menu_solicitar,menu_veiculo;

    private UMERApp() {}

    /**
     * Função que faz executar toda a aplicação ImoobliáriaApp.
     */
    public static void main(String[] args) throws EmailException, PermissionException, ViaturaException, ParseException{
        String file_name = "snap.data";
        carregarMenus();
        initApp();
        apresentarMenu();
        try {
            um.save();
        }
        catch (IOException e) {
            System.out.println("Não consegui guardar os dados!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Não consegui guardar os dados!");
        }

        System.out.println("Volte sempre!");
    }

    /**
     * Apresenta o menu principal.
     */
    private static void apresentarMenu () throws EmailException, PermissionException, ViaturaException, ParseException{
        int running = 1;

        do {
            menu_principal.executa();
                switch (menu_principal.getOpcao()) {
                    case 1: registarUtilizador();
                            break;
                    case 2: iniciarSessao();
                            break;
                    case 3: menu();
                            break;
                    case 0: running = 0;
                }

        } while (running!=0);

    }

    /**
     * Apresenta o Menu consoante o tipo de utilizador com sessão iniciada.
     */
    private static void menu() throws EmailException, PermissionException, ViaturaException, ParseException{

      if(um.getUtilizadorC() == null)
            running_menu_principal();

        else{
            Utilizador util = um.getUtilizadorC();
            if(util.getClass().getSimpleName().equals("Motorista"))
                running_menu_motorista();
            else if(util.getClass().getSimpleName().equals("Cliente") )
                running_menu_cliente();
            else
                running_menu_motoristaEmp();
        }
    }

    /**
     * Carrega todos os menus para apresentar.
     */
    private static void carregarMenus() {
        String[] principal = { "Registar Utilizador",
                               "Iniciar Sessao",
                               "Lista de Empresas",
                               "Top 10 Clientes",
                               "Top 5 Motoristas"};

        String [] registo = {"Motorista",
                           "Cliente"};

        String [] motorista = {"Adicionar Veiculo",
                              "Associar Veiculo",
                              "Associar Empresa",
                              "Consultar Historico",
                              "Registar Viagem",
                              "Sinalizar Disponibilidade",
                               "Terminar Sessão"};

        String [] motoristaEmp = {"Adicionar Veiculo",
                                 "Associar Veiculo",
                                 "Consultar Historico",
                                 "Lista de Motoristas da Empresa",
                                 "Lista de Viaturas duma Empresa",
                                 "Registar Viagem",
                                 "Sinalizar Disponibilidade",
                                 "Desassociar Empresa",
                                 "Terminar Sessão"};

        String [] cliente = {"Avaliar Motorista",
                            "Consultar Historico",
                            "Solicitar Viagem",
                            "Terminar Sessão"};

        String [] solicitar = {"Solicitar taxi mais próximo",
                            "Solicitar taxi especifico"};

        String [] veiculo = {"Carrinha",
                            "Carro",
                            "Mota"};


        menu_principal = new Menu(principal);
        menu_registo = new Menu(registo);
        menu_motorista = new Menu(motorista);
        menu_motoristaEmp = new Menu(motoristaEmp);
        menu_solicitar = new Menu(solicitar);
        menu_cliente = new Menu(cliente);
        menu_veiculo = new Menu(veiculo);
    }

    /**
     * Carrega o estado da aplicação da última vez que esta foi fechada.
     * @param fich
     */
    private static void initApp(){
        try {
            um = UMER.init();
        }
        catch (IOException e) {
            um = new UMER();
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
        }
        catch (ClassNotFoundException e) {
            um = new UMER();
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            um = new UMER();
            System.out.println("Não consegui ler os dados!\nErro de formato.");
        }
    }

    /**
     * Registo na UMERApp
     */
    private static void registarUtilizador() throws EmailException{
        String email, nome, password, morada, dataNascimento;
        Utilizador user = null;;
        Scanner is = new Scanner(System.in);

        menu_registo.executa();

            System.out.print("Nome: ");
            nome = is.nextLine();
            System.out.print("Email: ");
            email = is.nextLine();
            System.out.print("Password: ");
            password = is.nextLine();
            System.out.print("Morada: ");
            morada = is.nextLine();
            System.out.print("Data de nascimento: ");
            dataNascimento = is.nextLine();

            switch(menu_registo.getOpcao()){
                case 0: fecharSessao();
                case 1: user = new Motorista(email,nome,password,morada,dataNascimento, 0, 0, 0, false);
                        break;
                case 2: user = new Cliente(email,nome,password,morada,dataNascimento);
                        break;
            }
            um.signUp(user);

            is.close();
        }

        /**
     * Inicio de sessão na UMERApp.
     */
    private static void iniciarSessao(){
        Scanner is = new Scanner(System.in);
        String email,password;
        System.out.print("E-mail: ");
        email = is.nextLine();
        System.out.print("Password: ");
        password = is.nextLine();

        try{
            um.signIn(email,password);
        }
        catch(PasswordException e){
            System.out.println("Password errada");
        }
        catch(EmailException e){
            System.out.println("Email nao registado");
        }
        is.close();
    }

    /**
     * Fechar sessão na ImobiliariaApp.
     */
    private static void fecharSessao(){
        um.sigOut();
    }

    private static void running_menu_principal() throws EmailException{
        do{
           menu_principal.executa();

           switch(menu_principal.getOpcao()){
                case 1: registarUtilizador();
                        break;
                case 2: iniciarSessao();
                        break;
                case 3: listaEmpresas();
                        break;
                case 4: top10Clientes();
                        break;
                case 5: top5Motoristas();
                        break;
           }
        }while(menu_principal.getOpcao() != 0);
    }

    private static void running_menu_cliente()throws EmailException, PermissionException, ViaturaException, ParseException{
        do{
            menu_cliente.executa();

            switch(menu_cliente.getOpcao()){
                case 1: avaliaMotorista();
                        break;
                case 2: consultaHistorico();
                        break;
                case 3: running_menu_solicitar();
                        break;
                case 4: fecharSessao();
            }
        }while(menu_cliente.getOpcao() != 0);
    }

    private static void running_menu_veiculo() throws ViaturaException{
        do{
            menu_veiculo.executa();

            switch(menu_veiculo.getOpcao()){
                case 1: solicitaCarrinha();
                        break;
                case 2: solicitaCarro();
                        break;
                case 3: solicitaMota();
                        break;
            }
        }while(menu_cliente.getOpcao() != 0);
    }

    private static void running_menu_motorista() throws ViaturaException, ParseException{
        do{
            menu_motorista.executa();

            switch(menu_motorista.getOpcao()){
                case 1: adicionaVeiculo();
                        break;
                case 2: associaVeiculo();
                        break;
                case 3: associaEmpresa();
                        break;
                case 4: consultaHistorico();
                        break;
                case 5: sinalizaDisp();
                        break;
                case 6: fecharSessao();
            }
        }while(menu_motorista.getOpcao() != 0);
    }

    private static void running_menu_motoristaEmp() throws ViaturaException, ParseException{
        do{
            menu_motoristaEmp.executa();

            switch(menu_motoristaEmp.getOpcao()){
                case 1: adicionaVeiculo();
                        break;
                case 2: associaVeiculo();
                        break;
                case 3: consultaHistorico();
                        break;
                case 4: listaMotoristaEmp();
                        break;
                case 5: listaVeiculoEmp();
                        break;
                case 6: sinalizaDisp();
                        break;
                case 7: desassociaEmpresa();
                        break;
                case 8: fecharSessao();
            }
        }while(menu_motoristaEmp.getOpcao() != 0);
    }

    private static void running_menu_solicitar() throws ViaturaException{
        do{
            menu_solicitar.executa();

            switch(menu_solicitar.getOpcao()){
                case 1: solTaxiProx();
                        break;
                case 2: running_menu_veiculo();
                        break;
            }
        }while(menu_solicitar.getOpcao() != 0);
    }

    private static void top10Clientes(){
       List<Cliente> top = um.getCatU().top10();
       StringBuilder sb = new StringBuilder();
       int i;
       for(i=0; i<10; i++){
         sb.append(i+1).append(top.get(i).getNome()).append("\n");
        }


    }

   private static void top5Motoristas(){
       List<Motorista> top = um.getCatU().top5();
       StringBuilder sb = new StringBuilder();
       int i;
       for(i=0; i<5; i++  ){
         sb.append(i+1).append(top.get(i).getNome()).append("\n");
        }
    }

  private static void sinalizaDisp(){
         Motorista user = (Motorista) um.getUtilizadorC();
         String disponibilidade;

         Scanner is = new Scanner(System.in);

         System.out.print("Disponivel? Sim ou não? \n ");
         disponibilidade = is.nextLine();

         if(disponibilidade.equals("sim")) user.setDisponivel(true);
         else user.setDisponivel(false);
  }

  private static void listaMotoristaEmp(){
      MotoristaE user = (MotoristaE) um.getUtilizadorC();

      if (user instanceof MotoristaE){
          Empresa atual = user.getEmpresa();
          CatUtilizadores b = atual.getMotoristas();
          for(Utilizador a : (b.getCat())){
              System.out.println(a.getNome()+"\n");
          }
      }
  }

  private static void listaVeiculoEmp(){
      MotoristaE user = (MotoristaE) um.getUtilizadorC();

      if (user instanceof MotoristaE){
          Empresa atual = user.getEmpresa();
          CatViaturas b = atual.getViaturas();
          for(Viatura a : (b.getV())){
              System.out.println(a.getMatricula()+"\n");
          }
      }
  }

  private static void desassociaEmpresa(){
      MotoristaE user = (MotoristaE) um.getUtilizadorC();
      user.getEmpresa().getMotoristas().getCat().remove(user);
      user.setEmpresa(null);
  }

  private static void solTaxiProx() throws ViaturaException{
      Utilizador user = um.getUtilizadorC();
      int x, y, w, z;

      Scanner is = new Scanner(System.in);
      System.out.print("A sua localização em X: \n");
      x = is.nextInt();
      System.out.print("A sua localização em Y: \n");
      y = is.nextInt();
      Localizacao l = new Localizacao(x, y);

      user.setLocal(l);

      System.out.print("A localização em X para onde quer ir: \n");
      w = is.nextInt();
      System.out.print("A localização em Y para onde quer ir: \n");
      z = is.nextInt();
      Localizacao f = new Localizacao(w, z);
      Empresa e = null;
      String s = "near by";
      um.callACab(f, s, e);
  }

    private static void avaliaMotorista() throws EmailException, PermissionException{
        //pode nao existir motorista
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o email do motorista:");
        String email = scan.nextLine();
        System.out.println("Classifique o seu motorista (de 0 a 100): ");
        int c = scan.nextInt();
        if(c>0 && c<100){
            Motorista m = (Motorista) um.getCatU().findU(email);
            um.setFeedback(c,m);
        }
        else{
            System.out.println("A avaliação não se encontra dentro dos limites");
        }
    }

   private static void adicionaVeiculo(){
       Scanner scan = new Scanner(System.in);
       System.out.println("Digite a matricula da viatura:");
       String matricula= scan.nextLine();
       System.out.println("Velocidade média da viatura:");
       double velMedia= scan.nextDouble();
       System.out.println("Custo:");
       double custo= scan.nextDouble();
       System.out.println("Fiablidade:");
       double fiablidade = scan.nextDouble();

       Viatura v = new Viatura(matricula, velMedia, custo, fiablidade);
       um.getCatV().insertViatura(v);
    }

  private static void associaVeiculo() throws ViaturaException{
       Motorista user = (Motorista) um.getUtilizadorC();
       Scanner scan = new Scanner(System.in);
       System.out.println("Digite a matricula da viatura a qual se quer associar:");
       String matricula= scan.nextLine();
       Viatura v = um.getCatV().findV(matricula);
       v.setMotorista(user);

  }

   private static void listaEmpresas(){
      List<Empresa> emp = new ArrayList<Empresa>();
      emp = um.getCatE();
      StringBuilder sb = new StringBuilder();
      int i;
      for(i=0;i<emp.size();i++){
          sb.append("Empresa: ").append(emp.get(i).toString()).append("\n").append("\n");
        }
  }

  private static void solicitaCarrinha() throws ViaturaException{
      Utilizador user = um.getUtilizadorC();
      int x, y, w, z;

      Scanner is = new Scanner(System.in);
      System.out.print("A sua localização em X: \n");
      x = is.nextInt();
      System.out.print("A sua localização em Y: \n");
      y = is.nextInt();
      Localizacao l = new Localizacao(x, y);

      System.out.print("A localização em X para onde quer ir: \n");
      w = is.nextInt();
      System.out.print("A localização em Y para onde quer ir: \n");
      z = is.nextInt();
      Localizacao f = new Localizacao(w, z);

      user.setLocal(l);

      um.getCatV().getNearBy(l, "carrinha");
  }
  private static void solicitaCarro() throws ViaturaException{
      Utilizador user = um.getUtilizadorC();
      int x, y, w, z;

      Scanner is = new Scanner(System.in);
      System.out.print("A sua localização em X: \n");
      x = is.nextInt();
      System.out.print("A sua localização em Y: \n");
      y = is.nextInt();
      Localizacao l = new Localizacao(x, y);

      System.out.print("A localização em X para onde quer ir: \n");
      w = is.nextInt();
      System.out.print("A localização em Y para onde quer ir: \n");
      z = is.nextInt();
      Localizacao f = new Localizacao(w, z);

      user.setLocal(l);

      um.getCatV().getNearBy(l, "carro");
  }
  private static void solicitaMota() throws ViaturaException{
      Utilizador user = um.getUtilizadorC();
      int x, y, w, z;

      Scanner is = new Scanner(System.in);
      System.out.print("A sua localização em X: \n");
      x = is.nextInt();
      System.out.print("A sua localização em Y: \n");
      y = is.nextInt();
      Localizacao l = new Localizacao(x, y);

      System.out.print("A localização em X para onde quer ir: \n");
      w = is.nextInt();
      System.out.print("A localização em Y para onde quer ir: \n");
      z = is.nextInt();
      Localizacao f = new Localizacao(w, z);

      user.setLocal(l);

      um.getCatV().getNearBy(l, "moto");
  }

  private static void consultaHistorico() throws ParseException{
       Utilizador user = um.getUtilizadorC();
       Scanner scan = new Scanner(System.in);
       System.out.println("Data de inicio[MM/dd/yyyy]:");
       String dataStringIn = scan.next();
       System.out.println("Data de fim [MM/dd/yyyy]:");
       String dateStringEnd = scan.next();
       SimpleDateFormat sdfi = new SimpleDateFormat("MM/dd/yyyy");
       SimpleDateFormat sdff = new SimpleDateFormat("MM/dd/yyyy");
       Date desiredDateI = sdfi.parse(dataStringIn);
       Date desiredDateF = sdff.parse(dateStringEnd);
       Calendar calendari = Calendar.getInstance();
       Calendar calendarf = Calendar.getInstance();
       calendari.setTime(desiredDateI);
       calendarf.setTime(desiredDateF);
       if(user instanceof Cliente){
           List<Viagem> viagens = um.viagensCliente((Cliente)user, calendari, calendarf);
           for(Viagem v : viagens){
               System.out.println(v.toString());
           }
        }
        else{
          List<Viagem> viagens = um.viagensMotorista((Motorista)user, calendari, calendarf);
         for(Viagem v : viagens){
               System.out.println(v.toString());
           }
        }
    }

  private static void associaEmpresa(){

      Scanner scan = new Scanner(System.in);
      System.out.println("Digite o id da empresa:");
      long id = scan.nextLong();
      for(Empresa p : um.getCatE())
      if(id == p.getId()) {
        p.insertMotoristaE((MotoristaE)um.getUtilizadorC());
       }
    }

}
