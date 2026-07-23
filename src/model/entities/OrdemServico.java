package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OrdemServico {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;
    private Veiculo veiculo;
    private LocalDate data_entrada;
    private LocalDate data_saida;
    private String problema;
    private String diagnostico;
    private Double valor;
    private statusOrdem statusOrdem;

    public OrdemServico() {
    }

    public OrdemServico(Integer id, Veiculo veiculo, LocalDate data_entrada, LocalDate data_saida, String problema, String diagnostico, Double valor, statusOrdem statusOrdem) {
        this.id = id;
        this.veiculo = veiculo;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
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

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDate getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
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

    public statusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(statusOrdem statusOrdem) {
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
                "Veiculo: " + veiculo.getPlaca() + " | " +
                "Data Entrada: " + data_entrada.format(dtf) + " | " +
                "Data Saida: " + data_saida.format(dtf) + " | " + "\n" +
                "Problema: " + problema + " | " +
                "Diagnostico: " + diagnostico + " | " +
                "Valor: " + valor + " | " +
                "Status Ordem: " + statusOrdem;
    }
}
