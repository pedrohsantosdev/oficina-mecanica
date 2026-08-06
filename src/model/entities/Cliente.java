package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String telefone, String email) {
        validarNome(nome);
        validarCpf(cpf);
        validarTelefone(telefone);
        validarEmail(email);

        this.id = id;
        this.nome = nome.trim();
        this.cpf = cpf.replaceAll("\\D", "");
        this.telefone = telefone.replaceAll("\\D", "");
        this.email = email.trim();
    }

    private void validarNome(String nome) {

        if(nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
    }

   private void validarCpf(String cpf) {

        if(cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("Cpf é obrigatório!");
        }

        cpf = cpf.replaceAll("[^0-9]", "");

        if(cpf.length() != 11) {
            throw new IllegalArgumentException("Cpf inválido!");
        }

    }

    private void validarTelefone(String telefone) {

        if(telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone é obrigatório!");
        }

        telefone = telefone.replaceAll("\\D", "");

        if(telefone.length() != 10 && telefone.length() != 11) {
            throw new IllegalArgumentException("Telefone inválido!");
        }

    }

    private void validarEmail(String email) {

        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório!");
        }

        email = email.trim();

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("E-mail inválido!");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf.replaceAll("\\D", "");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone.replaceAll("\\D", "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return """
            ================ CLIENTE ================
            Id: %d
            Nome: %s
            CPF: %s
            Telefone: %s
            E-mail: %s
            =========================================
            """.formatted(
                id,
                nome,
                cpf,
                telefone,
                email
        );
    }
}
