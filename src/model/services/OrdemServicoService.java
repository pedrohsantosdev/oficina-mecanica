package model.services;

import model.dao.DaoFactory;
import model.dao.OrdemServicoDao;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.StatusOrdem;
import model.entities.Veiculo;

import java.util.List;

public class OrdemServicoService {

    private final OrdemServicoDao ordemServicoDao = DaoFactory.createOrdemServicoDao();

    public void abrirOrdem(OrdemServico ordemServico) {

        if(ordemServico == null) {
            throw new  IllegalArgumentException("Ordem de serviço inválida!");
        }

        if(ordemServico.getDataSaida().isBefore(ordemServico.getDataEntrada())) {
            throw new  IllegalArgumentException("Não é possível sair antes da entrada!");
        }

        ordemServicoDao.insert(ordemServico);
    }

    public void atualizarOrdemServico(OrdemServico ordemServico) {

        if(ordemServico == null) {
            throw new  IllegalArgumentException("Ordem de serviço inválida!");
        }

        if(ordemServico.getDataSaida().isBefore(ordemServico.getDataEntrada())) {
            throw new  IllegalArgumentException("Não é possível sair antes da entrada!");
        }

        OrdemServico existente = ordemServicoDao.findById(ordemServico.getId());

        if(existente == null) {
            throw new  IllegalArgumentException("Ordem de serviço não foi encontrada!");
        }

        ordemServicoDao.update(ordemServico);
    }

    public void deletarOrdemServico(Integer id) {

        if(id == null) {
            throw new  IllegalArgumentException("Id inválido!");
        }

        OrdemServico ordemServico = buscarOrdemServico(id);

        if(ordemServico == null) {
            throw new  IllegalArgumentException("Ordem de serviço não existe!");
        }

        ordemServicoDao.deleteById(id);
    }

    public OrdemServico buscarOrdemServico(Integer id) {

        if(id == null) {
            throw new IllegalArgumentException("Id inválido!");
        }

        OrdemServico ordemServico = ordemServicoDao.findById(id);

        if(ordemServico == null) {
            throw new IllegalArgumentException("Ordem de serviço não existe!");
        }

        return ordemServicoDao.findById(id);

    }

    public List<OrdemServico> buscarOrdemPorVeiculo(Veiculo veiculo) {

        if(veiculo == null) {
            throw new IllegalStateException("Veículo inválido!");
        }

        return ordemServicoDao.findByVeiculo(veiculo);

    }

    public List<OrdemServico> buscarOrdemPorCliente(Cliente cliente) {

        if(cliente == null) {
            throw new RuntimeException("Cliente não existe!");
        }

        return ordemServicoDao.findByCliente(cliente);
    }

    public List<OrdemServico> buscarOrdemPorStatus(StatusOrdem status) {

        if(status == null) {
            throw new IllegalStateException("Status vazio!");
        }

        return ordemServicoDao.findByStatus(status);

    }

    public List<OrdemServico> buscarTodos() {

        return ordemServicoDao.findAll();
    }
}
