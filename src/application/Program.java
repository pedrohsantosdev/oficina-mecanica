package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.StatusOrdem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(1, "Pedro", "12345678911", "(33)99999-9988",
                "pedro@gmail.com");

        ClienteDao clienteDao = DaoFactory.createClienteDao();

        cliente.setNome("Pedro Henrique");
        clienteDao.update(cliente);
        System.out.println("Atualizado com sucesso!");

    }
}
