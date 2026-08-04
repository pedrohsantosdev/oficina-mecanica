package model.services;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

import java.util.List;

public class ClienteService {

    private final ClienteDao clienteDao = DaoFactory.createClienteDao();

    public void cadastrarCliente(Cliente cliente) {

        if(cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }

        clienteDao.insert(cliente);
    }

    public Cliente buscarCliente(Integer id) {

        if(id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo!");
        }

        Cliente cliente = clienteDao.findById(id);

        if(cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        return cliente;
    }

    public void atualizarCliente(Cliente cliente) {

        if(cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }

        if(cliente.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo!");
        }

        Cliente existente = clienteDao.findById(cliente.getId());

        if(existente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        clienteDao.update(cliente);
    }

    public void apagarCliente(Integer id) {

        if(id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo!");
        }

        Cliente cliente = clienteDao.findById(id);

        if(cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        clienteDao.deleteById(id);
    }

    public List<Cliente> buscarTodos() {

        return clienteDao.findAll();
    }
}
