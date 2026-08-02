package model.services;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

import java.util.List;

public class ClienteService {

    private final ClienteDao clienteDao = DaoFactory.createClienteDao();

    public void cadastrarCliente(Cliente cliente) {

        if(cliente == null) {
            throw new RuntimeException("Cliente não pode ser nulo!");
        }

        clienteDao.insert(cliente);
    }

    public Cliente buscarCliente(Integer id) {

        if(id == null) {
            System.out.println("Id inválido!");
        }

        return clienteDao.findById(id);
    }

    public void atualizarCliente(Cliente cliente) {

        if(clienteDao.findById(cliente.getId()) == null) {
            System.out.println("Cliente não exite!");
            return;
        }

        clienteDao.update(cliente);
    }

    public void apagarCliente(Integer id) {

        if(clienteDao.findById(id) == null) {
            System.out.println("Id não existente!");
            return;
        }

        clienteDao.deleteById(id);
    }

    public List<Cliente> buscarTodos() {
        return clienteDao.findAll();
    }
}
