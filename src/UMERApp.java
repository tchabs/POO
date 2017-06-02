
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

public class UMERApp{
    private static UMER um;
    private static Menu menu_principal,menu_registo,menu_motorista,menu_motoristaEmp,
                   menu_cliente,menu_solicitar,menu_veiculo,menu_logado;
                   
    private UMERApp() {}
    
    /**
     * Função que faz executar toda a aplicação ImoobliáriaApp.
     */
    public static void main(String[] args) {
        String file_name = "snap.data";
        carregarMenus();
        initApp(file_name);
        apresentarMenu();
        try {
            um.save();
        }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
        }
        System.out.println("Volte sempre!");
    }

    /**
     * Apresenta o menu principal.
     */
    private static void apresentarMenu(){
        int running = 1;

        do {
            if(um.getUtilizadorC() != null){
                menu_logado.executa();
                switch(menu_logado.getOpcao()){
                    case 1: menu();
                            break;
                    case 2: fecharSessao();
                            break;
                    case 0: running = 0;
                }

            }
            else{
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
            }
        } while (running!=0);

    }

    /**
     * Apresenta o Menu consoante o tipo de utilizador com sessão iniciada.
     */
    private static void menu(){

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
        String[] logado = {"Menu",
                        "Fechar sessão"};
        String[] principal = {"Iniciar Sessão",
                               "Registar Utilizador",
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
        
        menu_logado = new Menu(logado);
        menu_principal = new Menu(principal);
        menu_registo = new Menu(registo);
        menu_motorista = new Menu(motorista);
        menu_motoristaEmp = new Menu(motoristaEmp);
        menu_cliente = new Menu(cliente);
        menu_veiculo = new Menu(veiculo);
    }

    /**
     * Carrega o estado da aplicação da última vez que esta foi fechada.
     * @param fich
     */
    private static void initApp(String fich){
        try {
            um = UMER.leObj(fich);
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
    private static void registarUtilizador(){
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
                case 0: input.close();
                        System.out.println("Cancelou");
                case 1: user = new Motorista(email,nome,password,morada,dataNascimento, 0, 0, 0, false);
                        break;
                case 2: user = new Cliente(email,nome,password,morada,dataNascimento);
                        break;
            }
            try{
                um.registarUtilizador(user);
            }
            catch(UtilizadorExistenteException e){
                System.out.println("Este utizador já existe!");
            }
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
        catch(SemAutorizacaoException e){
            System.out.println(e.getMessage());
        }

        is.close();
    }

    /**
     * Fechar sessão na ImobiliariaApp.
     */
    private static void fecharSessao(){
        um.sigOut();
    }

    private static void running_menu_principal(){
        do{
           menu_principal.executa();

           switch(menu_principal.getOpcao()){
                case 1: iniciarSessao();
                        break;
                case 2: registarUtilizador();
                        break;
               /*case 3: listaEmpresas();
                        break;
                case 4: top10Clientes();
                        break;
                case 5: top5Motoristas();
                        break;*/
           }
        }while(menu_principal.getOpcao() != 0);
    }

    private static void running_menu_cliente(){
        do{
            menu_cliente.executa();

            switch(menu_cliente.getOpcao()){
                /*case 1: avaliaMotorista();
                        break;
                case 2: consultaHistorico();
                        break;*/
                case 3: running_menu_solicita();
                        break;
                case 4: um.fecharSessao();
            }
        }while(menu_cliente.getOpcao() != 0);
    }

    private static void running_menu_motorista(){
        do{
            menu_motorista.executa();
            /*
            switch(menu_motorista.getOpcao()){
                case 1: adicionaVeiculo();
                        break;
                case 2: associaVeiculo();
                        break;
                case 3: associaEmpresa();
                        break;
                case 4: consultaHistorico();
                        break;
                case 5: registaViagem();
                        break;
                case 6: sinalizaDisp();
                        break;
                case 7: um.fechaSessao();
            }*/
        }while(menu_motorista.getOpcao() != 0);
    }

    private static void running_menu_motoristaEmp(){
        do{
            menu_motoristaEmp.executa();
            /*
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
                case 6: registaViagem();
                        break;
                case 7: sinalizaDisp();
                        break;
                case 8: desassociaEmpresa();
                        break;
                case 9: um.fechaSessao();
            }*/
        }while(menu_motoristaEmp.getOpcao() != 0);
    }

    private static void running_menu_solicita(){
        do{
            menu_solicita.executa();
            /*
            switch(menu_solicitar.getOpcao()){
                case 1: solTaxiProx();
                        break;
                case 2: solTaxiEsp();
                        break;
            }*/
        }while(menu_solicita.getOpcao() != 0);
    }

  private static void top10Clientes(){
       List<Cliente> top = top10();
       StringBuilder sb = new StringBuilder();
       int i;
       for(i=0; i<10; i++  ){
         sb.append(i+1).append(top.get(i).getNome()).append("\n");  
        }
       
  }
  
  private static void registaViagem(){
          Utilizador user = getUtilizadorC();
          
          Scanner is = new Scanner(System.in);
          int classificacao, coordXinicial, coordYinicial, coordXfinal, coordYfinal;
          double tempo, preco;
          Calendar inicioT, fimT;
          System.out.print("Localização inicial em X: \n");
          coordXinicial = is.nextInt();
          System.out.print("Localização inicial em Y: \n ");
          coordYinicial = is.nextInt();
          System.out.print("Localização final em X: \n");
          coordXfinal = is.nextInt();
          System.out.print("Localização final em Y: \n ");
          coordYfinal = is.nextInt();
          System.out.print("Classificação: \n ");
          classificacao = is.nextInt();
          System.out.print("Tempo de viagem: \n ");
          tempo = is.nextDouble();
          System.out.print("Preço da viagem: \n ");
          preco = is.nextDouble();
          System.out.print("Data do inicio da viagem: \n ");
          inicioT = is.nextDouble();
          System.out.print("Data do fim da viagem: \n ");
          fimT = is.nextDouble();
          /*ESTA CENA ESTA MAL*/ fimT = is.nextDouble();
          
          Localizacao inicio = Localizacao(coordXinicial, coordYinicial);
          Localizacao fim = Localizacao(coordXfinal, coordYfinal);
          Viagem v = Viagem(inicio, fim, classificacao, tempo, preco, inicioT, fimT);
          
          user.catViagens.add(v.clone());
  }
      
  private static void sinalizaDisp(){
         Utilizador user = um.getUtilizadorC();
         String disponibilidade;
          
         Scanner is = new Scanner(System.in);
         System.out.print("Disponivel? Sim ou não? \n ");
         disponibilidade = is.nextLine;
         
         if(disponibilidade.equals("sim")) user.setDisponivel(true);
         else user.setDisponivel(false);
  }
  
  private static void listaMotoristaEmp(){
      Utilizador user = um.getUtilizadorC();
      
      if (user instanceof MotoristaE){
          Empresa atual = user.getEmpresa();
          CatUtilizadores b = atual.getMotoristas();
          for(Utilizador a : (b.catalog)){
              System.out.println(a.getNome()+"\n");
          }
      }
  }
  
  private static void listaVeiculoEmp(){
      Utilizador user = um.getUtilizadorC();
      
      if (user instanceof MotoristaE){
          Empresa atual = user.getEmpresa();
          CatViaturas b = atual.getViaturas();
          for(Viatura a : (b.catalog)){
              System.out.println(a.getMatricula()+"\n");
          }
      }
  }
  
  private static void desassociaEmpresa(){
  
  }
}