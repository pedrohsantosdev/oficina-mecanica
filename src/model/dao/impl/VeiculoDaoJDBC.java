package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VeiculoDao;
import model.entities.Cliente;
import model.entities.Veiculo;

import java.sql.*;
import java.util.List;

public class VeiculoDaoJDBC implements VeiculoDao {

    private Connection conn;

    public VeiculoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Veiculo veiculo) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "INSERT INTO veiculo " +
                            "(cliente_id, placa, marca, modelo, ano, cor, quilometragem) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, veiculo.getCliente().getId());
            st.setString(2, veiculo.getPlaca());
            st.setString(3, veiculo.getMarca());
            st.setString(4, veiculo.getModelo());
            st.setInt(5, veiculo.getAno());
            st.setString(6, veiculo.getCor());
            st.setInt(7, veiculo.getQuilometragem());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if(rs.next()) {
                    int id = rs.getInt(1);
                    veiculo.setId(id);
                }

                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Erro na inserção do veículo!");
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }

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
