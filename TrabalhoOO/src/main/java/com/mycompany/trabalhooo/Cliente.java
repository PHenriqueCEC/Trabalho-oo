/*Deiverson Mourão Alves Pedroso (201965123A) 
Deyvison Gregório Dias   (201835017)
Pedro Henrique Almeida Cardoso Reis (201835039)
Yuri de Oliveira (201835010)*/


package com.mycompany.trabalhooo;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Cliente {

    static int contId = 0; // Vai ser usada pra achar a ficha
    static int contMatriculas = 0; //  Ativas

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int id;
    private String nome;
    private int cpf;
    private Date dataMatricula;
    private Boolean statusMatricula;
    private Date vencimento;
    private String senha;
    private double mensalidade;
    private List <Ficha> ficha = new ArrayList<>(); //Necessario colocar isso?

    
    //!!!! Colocar Ficha depois !!!!
    //Construtor de um cliente novo
    public Cliente(String nome,int cpf, Date dataMatricula,String senha) {
        contId++;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataMatricula);
        cal.add(Calendar.MONTH, 1);
        this.nome = nome;
        this.cpf = cpf;  
        this.dataMatricula = dataMatricula; 
        vencimento = cal.getTime();
        this.id = contId;
        this.statusMatricula = true;
        this.senha = senha;
        mensalidade = 80.0;
    }
    
    //Construtor de um cliente que já existe, com o construtor de cima, dados como vencimento e statusMatricula
    //poderiam se perder. Usado quando lê os dados de um arquivo
    
    //!!!!!Ajustar o arquivo txt pra incluir a mensalidade nesse construtor, talvez seja necessário mudar na classe Program também!!!!
    public Cliente(String nome, int cpf, Date dataMatricula, Date vencimento, int id, Boolean statusMatricula,String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataMatricula = dataMatricula;
        this.vencimento = vencimento;
        this.id = id;
        this.statusMatricula = statusMatricula;
        this.senha = senha;
        
    }

    public int getId() {
        return id;
    }

    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getDataMatricula() {
        return sdf.format(dataMatricula);
    }

    public String getVencimento() {
        return sdf.format(vencimento);
    }

    public Boolean getStatusMatricula() {
        return statusMatricula;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatusMatricula(Boolean statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    public String getSenha() {
        return senha;
    }

    /*
    public void setSenha(String senha) {
        this.senha = senha;
    }
   */
    
    
    public void updateVencimento(){//atualiza pagamento
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataMatricula);
        cal.add(Calendar.MONTH, 1);
        vencimento = cal.getTime();
    }
    
    
    
    
    //Fazer alguma verificação
    public void updateSenha(String novaSenha){
        
    }
    
    public void writeFile(File file){//Testando como transferir os dados pro arquivo
        String dados = nome + "," + cpf + "," + getDataMatricula() + "," + getVencimento() + "," + 
               id + "," + statusMatricula + "," + senha;
        String path = file.getAbsolutePath();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))){
            bw.write(dados);
            bw.newLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString(){//Facilitar visualização, colocar mais dados depois
        return "Nome: " + nome + " Cpf: " + cpf + " Vencimento: " + vencimento;
    }
}
