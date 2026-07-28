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
        this.id = id;
        this.veiculo = veiculo;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.problema = problema;
        this.diagnostico = diagnostico;
        this.valor = valor;
        this.statusOrdem = statusOrdem;
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
        this.veiculo = veiculo;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
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
