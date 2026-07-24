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

        Cliente cliente2 = new Cliente(2, "Maria", "12345678912", "(33)99999-9989",
                "maria@gmail.com");

        ClienteDao clienteDao = DaoFactory.createClienteDao();

        clienteDao.deleteById(2);

    }
}
