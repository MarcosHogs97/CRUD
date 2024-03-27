package Aplicacao;

import DAO.ContatoDAO;
import Model.Contato;

import javax.swing.*;
import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Cria um objeto Scanner para entrada de dados do usuário
        Scanner scanner = new Scanner(System.in);

        // Instancia um objeto ContatoDAO para interagir com o banco de dados
        ContatoDAO dao = new ContatoDAO();
        // Cria um objeto Contato para armazenar os dados do novo contato
        Contato contato = new Contato();

       while (true){
           //opcoes de menu
           String[] options = {"Cadastrar Contato", "Exbir lista de Contatos", "Atualizar Contato", "Deletar Contato", "Sair"};
           int escolha = JOptionPane.showOptionDialog(null, "Escolha oque deseja fazer.", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
           // Fecha o Scanner para liberar recursos
           switch (escolha) {
               case 0:
                   //cadastrar contato
                   contato.setNOME(contato.validarNome("Cadastrar nome do Contato:"));
                   contato.setIDADE(contato.validarIdade());
                   contato.setDATACADASTRO(new Date(System.currentTimeMillis()));
                   dao.save(contato);
                   break;

               case 1:
                   //lista os contatos
                    contato.listarContatos(dao);
                   break;

               case 2:
                   //updade dos contatos
                   contato.setNOME(contato.validarNome("Atualizar nome do Contato:"));
                   contato.setIDADE(contato.validarIdade());
                   contato.setDATACADASTRO(new Date(System.currentTimeMillis()));
                   contato.setID(contato.validarID());
                   dao.update(contato);
                   break;

               case 3:
                   //deletar contato
                   dao.deleteById(contato.validarID());
                   break;

               case 4:
                   JOptionPane.showMessageDialog(null,"Ate logo");
                   System.exit(0);
                   break;
               default:
                   JOptionPane.showMessageDialog(null, "Opção Inválida");
           }
           scanner.close();
       }
    }
}