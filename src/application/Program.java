package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.StatusOrdem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        ClienteDao clienteDao = DaoFactory.createClienteDao();

        List<Cliente> list = clienteDao.findAll();

        System.out.println(list);

    }
}
