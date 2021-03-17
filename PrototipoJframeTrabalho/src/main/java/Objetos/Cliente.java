 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author deive
 */
public class Cliente {

    //falta a senha ou vai ser o cpf mesmo
    static int contId = 0; // usado pra achar a ficha 

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tipoPlano;
    private String nome;
    private String cpf;
    private Date dataMatricula;
    private Date vencimento;
    private int id;
    private Boolean statusMatricula;
    private double valorPlano;
    private String telefone;
    private int desconto;
    private String sexo;
    private double valorParcela = 0;
    private int numeroParcelas = 1;
    private Ficha ficha; //#########

    //Construtor de um cliente novo
    public Cliente(String tipoPlano, String nome, String cpf, Date dataMatricula, String telefone, String sexo) throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataMatricula);
        cal.add(Calendar.MONTH, 1);
        this.tipoPlano = tipoPlano;
        this.nome = nome;
        this.cpf = cpf;
        this.dataMatricula = dataMatricula;
        this.telefone = telefone;
        vencimento = cal.getTime();
        contId++;
        this.id = contId;
        this.statusMatricula = true;
        valorPlano = 80;
        desconto = 0;
        this.ficha = new Ficha(cpf); //##############
        Recepcionista.lista.add(this);
        if (sexo == "2") {
            this.sexo = "Masculino";
        }else this.sexo="Feminimo";

    }

    //Construtor de um cliente já cadastrado
    public Cliente(String tipoPlano, String nome, String cpf, Date dataMatricula, Date vencimento,
            int id, Boolean statusMatricula, double valorPlano, String telefone, String sexo) throws IOException {
        this.tipoPlano = tipoPlano;
        this.nome = nome;
        this.cpf = cpf;
        this.dataMatricula = dataMatricula;
        this.vencimento = vencimento;
        this.id = id;
        this.statusMatricula = statusMatricula;
        this.valorPlano = valorPlano;
        contId = id;
        this.telefone = telefone;
        desconto = 0;
        this.sexo = sexo;
        this.ficha = new Ficha(cpf); //##############
        Recepcionista.lista.add(this);
        if (id > contId) {
            contId = id;
        }

    }

    public Cliente() {
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataMatricula() {
        return sdf.format(dataMatricula);
    }

    public double getValorPlano() {
        return valorPlano;
    }

    public void setValorPlano(double valorPlano) {
        this.valorPlano = valorPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(Boolean statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    public String getVencimento() {
        return sdf.format(vencimento);
    }

    public void updateVencimento() {//atualiza data do pagamento
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(this.getDataMatricula()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH, numeroParcelas);
        vencimento = cal.getTime();
    }

    public void writeFile() {
        String dados = this.toString();
        String path = Recepcionista.clientesBD.getAbsolutePath();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(dados);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return tipoPlano + "|" + nome + "|" + cpf + "|" + getDataMatricula() + "|" + getVencimento() + "|"
                + id + "|" + statusMatricula + "|" + this.valorPlano + "|" + telefone + "|" + sexo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    private void CriarFicha(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getcpf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
