package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OrdemServico implements Serializable {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;
    private Veiculo veiculo;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String problema;
    private String diagnostico;
    private Double valor;
    private StatusOrdem statusOrdem;

    public OrdemServico() {
    }

    public OrdemServico(Integer id, Veiculo veiculo, LocalDate dataEntrada, LocalDate dataSaida, String problema, String diagnostico, Double valor, StatusOrdem statusOrdem) {
        validarVeiculo(veiculo);
        validarDataEntrada(dataEntrada);
        validarDataSaida(dataSaida);
        validarProblema(problema);
        validarDiagnostico(diagnostico);
        validarValor(valor);
        validarStatus(statusOrdem);

        this.id = id;
        this.veiculo = veiculo;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.problema = problema;
        this.diagnostico = diagnostico;
        this.valor = valor;
        this.statusOrdem = statusOrdem;
    }

    public void validarVeiculo(Veiculo veiculo) {

        if(veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo!");
        }
    }

    public void validarDataEntrada(LocalDate dataEntrada) {

        if(dataEntrada == null) {
            throw new IllegalArgumentException("Data inválida!");
        }

    }

    public void validarDataSaida(LocalDate dataSaida) {

        if(dataSaida == null) {
            throw new IllegalArgumentException("Data inválida!");
        }

    }

    public void validarProblema(String problema) {

        if(problema == null) {
            throw new IllegalArgumentException("Problema não pode ser nulo!");
        }

    }

    public void validarDiagnostico(String diagnostico) {

        if(diagnostico == null) {
            throw new IllegalArgumentException("Diagnóstico não pode ser nulo!");
        }

    }

    public void validarValor(Double valor) {

        if(valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo!");
        }

        if(valor <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
    }

    public void validarStatus(StatusOrdem statusOrdem) {

        if(statusOrdem == null) {
            throw new IllegalArgumentException("Status não pode ser nulo!");
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        validarVeiculo(veiculo);
        this.veiculo = veiculo;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        validarDataEntrada(dataEntrada);
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        validarDataSaida(dataSaida);
        this.dataSaida = dataSaida;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        validarProblema(problema);
        this.problema = problema;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        validarDiagnostico(diagnostico);
        this.diagnostico = diagnostico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        validarValor(valor);
        this.valor = valor;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        validarStatus(statusOrdem);
        this.statusOrdem = statusOrdem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrdemServico - " +
                "Id: " + id + " | " +
                "Cliente: " + veiculo.getCliente().getNome() + " | " +
                "Veiculo: " + veiculo.getPlaca() + " | " +
                "Data Entrada: " + dataEntrada.format(dtf) + " | " +
                "Data Saida: " + dataSaida.format(dtf) + " | " + "\n" +
                "Problema: " + problema + " | " +
                "Diagnostico: " + diagnostico + " | " +
                "Valor: " + valor + " | " +
                "Status Ordem: " + statusOrdem;
    }
}
