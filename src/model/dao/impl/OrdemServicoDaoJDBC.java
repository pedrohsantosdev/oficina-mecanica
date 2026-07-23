package model.dao.impl;

import model.dao.OrdemServicoDao;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.StatusOrdem;
import model.entities.Veiculo;

import java.sql.Connection;
import java.util.List;

public class OrdemServicoDaoJDBC implements OrdemServicoDao {

    private Connection conn;

    public OrdemServicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(OrdemServico ordemServico) {

    }

    @Override
    public void update(OrdemServico ordemServico) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public OrdemServico findById(Integer id) {
        return null;
    }

    @Override
    public List<OrdemServico> findByVeiculo(Veiculo veiculo) {
        return List.of();
    }

    @Override
    public List<OrdemServico> findByCliente(Cliente cliente) {
        return List.of();
    }

    @Override
    public List<OrdemServico> findByStatus(StatusOrdem status) {
        return List.of();
    }

    @Override
    public List<OrdemServico> findAll() {
        return List.of();
    }
}
