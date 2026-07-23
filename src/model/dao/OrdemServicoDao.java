package model.dao;

import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.Veiculo;
import model.entities.StatusOrdem;

import java.util.List;

public interface OrdemServicoDao {

    void insert(OrdemServico ordemServico);
    void update(OrdemServico ordemServico);
    void deleteById(Integer id);
    OrdemServico findById(Integer id);
    List<OrdemServico> findByVeiculo(Veiculo veiculo);
    List<OrdemServico> findByCliente(Cliente cliente);
    List<OrdemServico> findByStatus(StatusOrdem status);
    List<OrdemServico> findAll();
}
