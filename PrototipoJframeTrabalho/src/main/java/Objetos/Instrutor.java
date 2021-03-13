/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deive
 */
public class Instrutor {

    private String funcao;
    private String nome;
    private String senha;
    private String cpf;
    private String data;
    private String sexo;
    private String contato;
    static List<Cliente> lista = new ArrayList<>();

    //construtores

    public Instrutor(String nome, String senha, String cpf, String data, String sexo, String contato) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.data = data;
        this.sexo = sexo;
        this.contato = contato;
    }
    

    public Instrutor() {
    }

    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getData() {
        return data;
    }

    public String getSexo() {
        return sexo;
    }

    public String getContato() {
        return contato;
    }

     public void writeFile(File file) {
        String dados = this.toString();
        String path = file.getAbsolutePath();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(dados);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}