package model.entities;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public class Veiculo implements Serializable {

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
        validarCliente(cliente);
        validarPlaca(placa);
        validarMarca(marca);
        validarModelo(modelo);
        validarAno(ano);
        validarCor(cor);
        validarQuilometragem(quilometragem);

        this.id = id;
        this.cliente = cliente;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.quilometragem = quilometragem;
    }

    private void validarCliente(Cliente cliente) {

        if(cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }
    }

    private void validarPlaca(String placa) {

        if(placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa não deve ser nula!");
        }

        placa = placa.trim().toUpperCase();

        String regex = "^[A-Z]{3}-?\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$";

        if(!placa.matches(regex)) {
            throw new IllegalArgumentException("Placa inválida!");
        }

    }

    private void validarMarca(String marca) {

        if(marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Marca não pode ser nula!");
        }

    }

    private void validarModelo(String modelo) {

        if(modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo não pode ser nulo!");
        }

    }

    private void validarAno(Integer ano) {

        if(ano == null) {
            throw new IllegalArgumentException("Ano do veículo é obrigatório!");
        }

        int anoAtual = Year.now().getValue();

        if(ano < 1900 || ano > anoAtual + 1) {
            throw new IllegalArgumentException("Ano inválido!");
        }
    }

    private void validarCor(String cor) {

        if(cor == null || cor.isBlank()) {
            throw new IllegalArgumentException("Cor não pode ser nula!");
        }
    }

    private void validarQuilometragem(Integer quilometragem) {

        if(quilometragem == null) {
            throw new IllegalArgumentException("Quilometragem não pode ser nula!");
        }

        if(quilometragem < 0) {
            throw new IllegalArgumentException("Quilometragem inválida!");
        }
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
        validarCliente(cliente);
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        validarPlaca(placa);
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        validarMarca(marca);
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        validarModelo(modelo);
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        validarAno(ano);
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        validarCor(cor);
        this.cor = cor;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        validarQuilometragem(quilometragem);
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
