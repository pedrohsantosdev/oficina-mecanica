package model.services;

import model.dao.DaoFactory;
import model.dao.VeiculoDao;
import model.entities.Cliente;
import model.entities.Veiculo;

import java.util.List;

public class VeiculoService {

    private final VeiculoDao veiculoDao = DaoFactory.createVeiuloDao();

    public void cadastrarVeiculo(Veiculo veiculo) {

        if(veiculo == null) {
            throw new RuntimeException("Veículo não pode ser nulo!");
        }

        veiculoDao.insert(veiculo);
    }

    public Veiculo buscarVeiculo(Integer id) {

        if(id == null) {
            throw new RuntimeException("Id inválido!");
        }

        return veiculoDao.findById(id);
    }

    public void atualizarVeiculo(Veiculo veiculo) {

        if(veiculoDao.findById(veiculo.getId()) == null) {
            throw new RuntimeException("Esse veículo não existe!");
        }

        veiculoDao.update(veiculo);
    }

    public void deletarVeiculo(Integer id) {

        if(veiculoDao.findById(id) == null) {
            throw new RuntimeException("Veículo não existe!");
        }

        veiculoDao.deleteById(id);
    }

    public Veiculo buscarPlaca(String placa) {

        if(placa == null) {
            throw new RuntimeException("Placa não existe!");
        }

        return veiculoDao.findByPlaca(placa);
    }

    public List<Veiculo> buscarCliente(Cliente cliente) {

        if(cliente == null) {
            throw new RuntimeException("Cliente inválido!");
        }

        return veiculoDao.findByCliente(cliente);
    }

    public List<Veiculo> buscarTodos() {

        return veiculoDao.findAll();
    }
}
