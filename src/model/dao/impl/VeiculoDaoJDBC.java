package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VeiculoDao;
import model.entities.Cliente;
import model.entities.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE veiculo " +
                            "SET " +
                            "cliente_id = ?, placa = ?, marca = ?, modelo = ?, ano = ?, cor = ?, quilometragem = ? " +
                            "WHERE id = ?"
            );

            st.setInt(1, veiculo.getCliente().getId());
            st.setString(2, veiculo.getPlaca());
            st.setString(3, veiculo.getMarca());
            st.setString(4, veiculo.getModelo());
            st.setInt(5, veiculo.getAno());
            st.setString(6, veiculo.getCor());
            st.setInt(7, veiculo.getQuilometragem());
            st.setInt(8, veiculo.getId());

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "DELETE FROM veiculo " +
                            "WHERE id = ?"
            );

            st.setInt(1, id);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Veiculo findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT v.*, c.id AS ClienteId, c.nome, c.cpf, c.telefone, c.email " +
                            "FROM veiculo v " +
                            "JOIN cliente c "+
                            "ON v.cliente_id = c.id " +
                            "WHERE v.id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                Cliente cliente = instaciarCliente(rs);
                Veiculo veiculo = instaciarVeiculo(rs, cliente);
                return veiculo;
            }
            else {
                return null;
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Veiculo findByPlaca(String placa) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT v.*, c.nome, c.cpf, c.telefone, c.email " +
                            "FROM veiculo v " +
                            "JOIN cliente c "+
                            "ON v.cliente_id = c.id " +
                            "WHERE v.placa = ?"
            );

            st.setString(1, placa);

            rs = st.executeQuery();

            if(rs.next()) {
                Cliente cliente = instaciarCliente(rs);
                Veiculo veiculo = instaciarVeiculo(rs, cliente);
                return veiculo;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Veiculo> findByCliente(Cliente cliente) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT v.*, c.nome, c.cpf, c.telefone, c.email " +
                            "FROM veiculo v " +
                            "JOIN cliente c "+
                            "ON v.cliente_id = c.id " +
                            "WHERE v.cliente_id = ?"
            );

            st.setInt(1, cliente.getId());

            rs = st.executeQuery();

            List<Veiculo> list = new ArrayList<>();
            Map<Integer, Cliente> map = new HashMap<>();

            while (rs.next()) {

                Cliente c = map.get(rs.getInt("cliente_id"));

                if(c == null) {

                    c = instaciarCliente(rs);
                    map.put(rs.getInt("cliente_id"), c);

                }

                Veiculo v = instaciarVeiculo(rs, c);
                list.add(v);
            }

            return list;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Veiculo> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT v.*, c.nome, c.cpf, c.telefone, c.email " +
                            "FROM veiculo v " +
                            "JOIN cliente c " +
                            "ON v.cliente_id = c.id"
            );

            rs = st.executeQuery();

            List<Veiculo> list = new ArrayList<>();

            while (rs.next()) {

                Cliente cliente = instaciarCliente(rs);
                Veiculo veiculo = instaciarVeiculo(rs, cliente);
                list.add(veiculo);

            }

            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Cliente instaciarCliente(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("ClienteId"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }

    private Veiculo instaciarVeiculo(ResultSet rs, Cliente cliente) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getInt("id"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setQuilometragem(rs.getInt("quilometragem"));
        veiculo.setCliente(cliente);
        return veiculo;
    }
}
