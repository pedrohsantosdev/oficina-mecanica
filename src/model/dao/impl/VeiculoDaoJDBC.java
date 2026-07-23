package model.dao.impl;

import model.dao.VeiculoDao;
import model.entities.Cliente;
import model.entities.Veiculo;

import java.sql.Connection;
import java.util.List;

public class VeiculoDaoJDBC implements VeiculoDao {

    private Connection conn;

    public VeiculoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Veiculo veiculo) {

    }

    @Override
    public void update(Veiculo veiculo) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Veiculo findById(Integer id) {
        return null;
    }

    @Override
    public Veiculo findByPlaca(String placa) {
        return null;
    }

    @Override
    public List<Veiculo> findByCliente(Cliente cliente) {
        return List.of();
    }

    @Override
    public List<Veiculo> findAll() {
        return List.of();
    }
}
