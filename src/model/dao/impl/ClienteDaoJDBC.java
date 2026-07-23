package model.dao.impl;

import model.dao.ClienteDao;
import model.entities.Cliente;

import java.sql.Connection;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDao {

    private Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente cliente) {

    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Cliente findById(Integer id) {
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        return List.of();
    }
}
