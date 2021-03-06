/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/*
Deyvison Gregório Dias 201835017
Deiverson Mourão Alves Pedroso 201965123A
Pedro Henrique Almeida Cardoso Reis 201835039
*/
import static Objetos.Administrador.listaRece;
import static Objetos.Main.clientes;
import static Objetos.Main.cpfCliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recepcionista implements Pagamento {

    static Scanner sc = new Scanner(System.in);

    public static File clientesBD = new File("clientes.txt");
    public static String pathCliente = clientesBD.getAbsolutePath();

    //identificaçao
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String nome;
    private String senha;
    private String cpf;
    private String data;
    private String sexo;
    private String contato;

    static public List<Cliente> lista = new ArrayList<>();//achar a implementaçao;

    //construtores
    public Recepcionista(String nome, String senha, String cpf, String data, String sexo, String contato) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.data = data;
        this.sexo = sexo;
        this.contato = contato;

    }

    public Recepcionista() {
        clientesBD = new File("clientes.txt");
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

    static public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public void setnull() {
        this.nome = null;
        this.senha = null;
        this.cpf = null;
        this.data = null;
        this.sexo = null;
        this.contato = null;

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
    @Override
    public void pagar(Cliente cliente) {

        cliente.updateVencimento();

    }

    public void writeFile() {
        String dados = this.coverteEMString();
        String path = Administrador.func.getAbsolutePath();
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(dados);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LerTxtCliente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (clientesBD.exists()) {
            try ( BufferedReader br = new BufferedReader(new FileReader(pathCliente))) {
                String line = br.readLine();
                while (line != null) {
                    String[] fields = line.split("\\|");

                    if (fields.equals("Mensal")) {
                        Cliente c = new Cliente(fields[0], //tipo plano
                                fields[1], //nome
                                fields[2], //cpf
                                sdf.parse(fields[3]),//matrícula
                                sdf.parse(fields[4]), //vencimento
                                Integer.parseInt(fields[5]), //id
                                Boolean.parseBoolean(fields[6]), //status pagamento
                                Double.parseDouble(fields[7]), //mensalidade       
                                fields[8], //telefone
                                fields[9]);//sexo
                        Main.clientes.put(fields[2], c);

                    } else if (fields[0].equals("Trimestral")) {
                        Cliente c = new ClienteTrimestral(fields[0], //tipo plano
                                fields[1], //nome
                                fields[2], //cpf
                                sdf.parse(fields[3]), //matrícula
                                sdf.parse(fields[4]), //vencimento
                                Integer.parseInt(fields[5]), //id
                                Boolean.parseBoolean(fields[6]), //status pagamento
                                Double.parseDouble(fields[7]), //mensalidade    
                                fields[8], //telefone
                                fields[9]);
                        Main.clientes.put(fields[2], c); // cpf como chave
                        //sexo

                    } else if (fields[0].equals("Semestral")) {
                        Cliente c = new ClienteSemestral(fields[0], //tipo plano
                                fields[1], //nome
                                fields[2], //cpf
                                sdf.parse(fields[3]), //matrícula
                                sdf.parse(fields[4]), //vencimento
                                Integer.parseInt(fields[5]), //id
                                Boolean.parseBoolean(fields[6]), //status pagamento
                                Double.parseDouble(fields[7]), //mensalidade    
                                fields[8], //telefone
                                fields[9]);//sexo

                        Main.clientes.put(fields[2], c); // cpf como chave

                    } else if (fields[0].equals("Anual")) {
                        Cliente c = new ClienteAnual(fields[0], //tipo plano
                                fields[1], //nome
                                fields[2], //cpf
                                sdf.parse(fields[3]), //matrícula
                                sdf.parse(fields[4]), //vencimento
                                Integer.parseInt(fields[5]), //id
                                Boolean.parseBoolean(fields[6]), //status pagamento
                                Double.parseDouble(fields[7]), //mensalidade    
                                fields[8], //telefone
                                fields[9]);//sexo
                        Main.clientes.put(fields[2], c); // cpf como chave
                    }

                    Main.cpfCliente.put(fields[1], fields[2]);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //cria o arquivo se não exister nenhum
            try {
                clientesBD.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String coverteEMString() {
        return ("Recepcionista" + "|" + getNome() + "|" + getSenha() + "|" + getCpf() + "|"
                + getData() + "|" + getSexo() + "|" + getContato());
    }

    public static void anulaarq() {
        clientesBD.delete();
        File func = new File("clientes.txt");
    }

}
