/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author deive
 */
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Recepcionista implements Pagamento {

    static Scanner sc = new Scanner(System.in);
   
    //identificaçao
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String nome;
    private String senha;

    static List<Cliente> lista = new ArrayList<>();

    //construtores
    public Recepcionista(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Recepcionista() {
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    static public  List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    //implementaçao metodos interface
    /**
     *
     * @param cliente
     * @throws ParseException
     */
    public double retornaValorPagamento(Cliente cliente) {
        return cliente.getValorPlano();

    }

    /**
     *
     * @param cliente
     */
    static public void pagar(Cliente cliente) {
        
        if (cliente.getTipoPlano() == "Mensal" || cliente.getTipoPlano() == "mensal") {
            System.out.println("Forma de pagamento a vista ");
            //confirmar o pagamento do cliente
            cliente.updateVencimento();

        }

        if (cliente.getTipoPlano() == "Trimestral" || cliente.getTipoPlano() == "trimestral") {
            if (cliente.getNumeroParcelas() == 0) {
                System.out.println("Digite o numero de parcelas para pagamento :");
                int i = sc.nextInt();
                cliente.setNumeroParcelas(i);
                double d = cliente.getValorPlano() - (cliente.getValorPlano() * 0.05);
                cliente.setValorParcela(d / i);

                cliente.updateVencimento();

            }
        }
        if (cliente.getTipoPlano() == "Semestral" || cliente.getTipoPlano() == "semestral") {

            if (cliente.getNumeroParcelas() == 0) {
                System.out.println("Digite o numero de parcelas para pagamento (no maximo 4):");
                int i = sc.nextInt();
                cliente.setNumeroParcelas(i);
                double d1 =cliente.getValorPlano() - (cliente.getValorPlano() * 0.10);
                cliente.setValorParcela(d1 / i);

                cliente.updateVencimento();

            }

        }

        if (cliente.getTipoPlano() == "Anual" || cliente.getTipoPlano() == "anual") {

            if (cliente.getNumeroParcelas() == 0) {
                System.out.println("Digite o numero de parcelas para pagamento (no maximo 6):");
                int i = sc.nextInt();
                cliente.setNumeroParcelas(i);
                double d2 = cliente.getValorPlano() - (cliente.getValorPlano() * 0.15);
                cliente.setValorParcela(d2 / i);

                cliente.updateVencimento();

            }

        }
    }

}