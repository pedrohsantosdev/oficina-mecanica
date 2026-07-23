package model.entities;

import java.util.Objects;

public class Veiculo {

    private Integer id;
    private Cliente cliente;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private Integer quilometragem;

    public Veiculo() {
    }

    public Veiculo(Integer id, Cliente cliente, String placa, String marca, String modelo, Integer ano, String cor, Integer quilometragem) {
        this.id = id;
        this.cliente = cliente;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.quilometragem = quilometragem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id) && Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa);
    }

    @Override
    public String toString() {
        return "Veiculo - " +
                "Id: " + id + " | " +
                "Cliente: " + cliente.getNome() + " | " +
                "Placa: " + placa + " | " +
                "Marca: " + marca + " | " +
                "Modelo: " + modelo + " | " +
                "Ano: " + ano + " | " +
                "Cor: " + cor + " | " +
                "Quilometragem: " + quilometragem;
    }
}
