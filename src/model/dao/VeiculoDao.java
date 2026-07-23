package model.dao;

import model.entities.Cliente;
import model.entities.Veiculo;

import java.util.List;

public interface VeiculoDao {

    void insert(Veiculo veiculo);
    void update(Veiculo veiculo);
    void deleteById(Integer id);
    Veiculo findById(Integer id);
    Veiculo findByPlaca(String placa);
    List<Veiculo> findByCliente(Cliente cliente);
    List<Veiculo> findAll();
}
