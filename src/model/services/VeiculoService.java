package model.services;

import model.dao.DaoFactory;
import model.dao.VeiculoDao;
import model.entities.Cliente;
import model.entities.Veiculo;

import java.util.List;

public class VeiculoService {

    private final VeiculoDao veiculoDao = DaoFactory.createVeiculoDao();

    public void cadastrarVeiculo(Veiculo veiculo) {

        if(veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo!");
        }

        if(veiculo.getCliente() == null) {
            throw new IllegalArgumentException("Cliente inválido!");
        }

        veiculoDao.insert(veiculo);
    }

    public Veiculo buscarVeiculo(Integer id) {

        if(id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo!");
        }

        Veiculo veiculo = veiculoDao.findById(id);

        if(veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado!");
        }

        return veiculo;
    }

    public void atualizarVeiculo(Veiculo veiculo) {

        if(veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo!");
        }

        if(veiculo.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo!");
        }

        Veiculo existente = veiculoDao.findById(veiculo.getId());

        if(existente == null) {
            throw new IllegalArgumentException("Veículo não encontrado!");
        }

        veiculoDao.update(veiculo);
    }

    public void deletarVeiculo(Integer id) {

        if(id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo!");
        }

        Veiculo veiculo = veiculoDao.findById(id);

        if(veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado!");
        }

        veiculoDao.deleteById(id);
    }

    public Veiculo buscarPlaca(String placa) {

        if(placa == null) {
            throw new IllegalArgumentException("Placa não existe!");
        }

        Veiculo veiculo = veiculoDao.findByPlaca(placa);

        if(veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado!");
        }

        return veiculo;
    }

    public List<Veiculo> buscarVeiculosPorCliente(Cliente cliente) {

        if(cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }

        return veiculoDao.findByCliente(cliente);
    }

    public List<Veiculo> buscarTodos() {

        return veiculoDao.findAll();
    }
}
