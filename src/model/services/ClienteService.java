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
        return clienteDao.findById(id);
    }

    public void atualizarCliente(Cliente cliente) {

        if(clienteDao.findById(cliente.getId()) == null) {
            throw new RuntimeException("Cliente não existe!");
        }

        clienteDao.update(cliente);
    }

    public void apagarCliente(Integer id) {

        if(clienteDao.findById(id) == null) {
            throw new RuntimeException("Id não existente!");
        }

        clienteDao.deleteById(id);
    }

    public List<Cliente> buscarTodos() {
        return clienteDao.findAll();
    }
}
