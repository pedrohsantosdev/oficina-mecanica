package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ClienteDao;
import model.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDao {

    private Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente cliente) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "INSERT INTO cliente " +
                            "(nome, cpf, telefone, email) " +
                            "VALUES " +
                            "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS

            );

            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getCpf());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getEmail());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if(rs.next()) {
                    int id = rs.getInt(1);
                    cliente.setId(id);
                }

                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Erro na inserção do cliente!");
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
    public void update(Cliente cliente) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE cliente " +
                            "SET " +
                            "nome = ?, cpf = ?, telefone = ?, email = ? " +
                            "WHERE id = ?"
            );

            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getCpf());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getEmail());
            st.setInt(5, cliente.getId());

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
                    "DELETE FROM cliente " +
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
    public Cliente findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT * FROM cliente " +
                            "WHERE id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                Cliente c = iniciateCliente(rs);
                return c;
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
    public List<Cliente> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT * FROM cliente " +
                            "ORDER BY nome"
            );

            rs = st.executeQuery();

            List<Cliente> list = new ArrayList<>();

            while (rs.next()) {

                Cliente c = iniciateCliente(rs);
                list.add(c);

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

    private Cliente iniciateCliente(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }
}
