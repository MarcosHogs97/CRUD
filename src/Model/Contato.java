package Model;

import DAO.ContatoDAO;

import javax.swing.*;
import java.util.List;

public class Contato {
    private int ID;
    private String NOME;
    private int IDADE;
    private java.sql.Date DATACADASTRO;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public int getIDADE() {
        return IDADE;
    }

    public void setIDADE(int IDADE) {
        this.IDADE = IDADE;
    }

    public java.sql.Date getDATACADASTRO() {
        return DATACADASTRO;
    }

    public void setDATACADASTRO(java.sql.Date DATACADASTRO) {
        this.DATACADASTRO = DATACADASTRO;
    }

    public String validarNome(String jpm1){
        String nome = null;
        while (true){
            JOptionPane.showMessageDialog(null, "Por favor, insira o nome do contato.");
            nome = JOptionPane.showInputDialog(null, jpm1, "Nome Contato", JOptionPane.PLAIN_MESSAGE);
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O nome do contato deve ser preenchido!");
            }else{
                return  nome;
            }
        }
    }

    public int validarIdade(){
        String idadePV = null;
        int idade = 0;
        while (true){
            JOptionPane.showMessageDialog(null, "Por favor, insira a idade do contato.");
            idadePV = JOptionPane.showInputDialog(null, "Cadastar idade do Contato:", "Idade Contato", JOptionPane.PLAIN_MESSAGE);
            if (idadePV.isEmpty()) {
                JOptionPane.showMessageDialog(null, "A idade do contato deve ser preenchida!");
            } else if (!isNumeroInteiro(idadePV)) {
                JOptionPane.showMessageDialog(null, "A idade do contato deve ser um número inteiro!");
            } else {
                idade = Integer.parseInt(idadePV);
                if (idade <= 0) {
                    JOptionPane.showMessageDialog(null, "A idade do contato deve ser um número positivo!");
                } else {
                    break; // Saia do loop se a idade for válida
                }
            }
        }
        return idade;
    }
    public int validarID(){
        String idTemp = null;
        int id = 0;
        while (true){
            JOptionPane.showMessageDialog(null, "Por favor, insira o ID do contato.");
            idTemp = JOptionPane.showInputDialog(null, "Deletar por ID do Contato:", "ID Contato", JOptionPane.PLAIN_MESSAGE);
            if (idTemp.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O ID do contato deve ser preenchida!");
            } else if (!isNumeroInteiro(idTemp)) {
                JOptionPane.showMessageDialog(null, "O ID do contato deve ser um número inteiro!");
            } else {
                id = Integer.parseInt(idTemp);
                if (id < 0) {
                    JOptionPane.showMessageDialog(null, "O ID do contato deve ser um número igual ou maior que 0!");
                } else {
                    break; // Saia do loop se a ID for válida
                }
            }
        }
        return id;
    }

    protected int validarValoresInt(String texto) {
        String input = JOptionPane.showInputDialog(texto);
        if (isNumeroInteiro(input)) {
            input = input.replace(",", ".");
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }

    protected boolean isNumeroInteiro(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void listarContatos(ContatoDAO contatoDAO){
        StringBuilder mensagem = new StringBuilder("Lista de contatos cadastrados:\n");

        List<Contato> contatos = contatoDAO.getContato();
        for (Contato c : contatos) {
            mensagem.append("Contato ID: ").append(c.getID()).append("\nNome: ").append(c.getNOME()).append("\nIdade: ").append(c.getIDADE()).append("\nData: ").append(c.getDATACADASTRO()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }
}

