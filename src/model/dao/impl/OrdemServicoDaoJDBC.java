package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.OrdemServicoDao;
import model.entities.Cliente;
import model.entities.OrdemServico;
import model.entities.StatusOrdem;
import model.entities.Veiculo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrdemServicoDaoJDBC implements OrdemServicoDao {

    private Connection conn;

    public OrdemServicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(OrdemServico ordemServico) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "INSERT INTO ordem_servico " +
                            "(veiculo_id, data_entrada, data_saida, problema, " +
                            "diagnostico, valor, status_ordem) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, ordemServico.getVeiculo().getId());
            st.setDate(2, java.sql.Date.valueOf(ordemServico.getDataEntrada()));
            st.setDate(3, java.sql.Date.valueOf(ordemServico.getDataSaida()));
            st.setString(4, ordemServico.getProblema());
            st.setString(5, ordemServico.getDiagnostico());
            st.setDouble(6, ordemServico.getValor());
            st.setString(7, ordemServico.getStatusOrdem().name());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
               ResultSet rs = st.getGeneratedKeys();

                if(rs.next()) {
                    int id = rs.getInt(1);
                    ordemServico.setId(id);
                }

                DB.closeResultSet(rs);
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
    public void update(OrdemServico ordemServico) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE ordem_servico " +
                            "SET " +
                            "veiculo_id = ?, data_entrada = ?, data_saida = ?, " +
                            "problema = ?, diagnostico = ?, valor = ?, status_ordem = ? " +
                            "WHERE id = ?"
            );

            st.setInt(1, ordemServico.getVeiculo().getId());
            st.setDate(2, java.sql.Date.valueOf(ordemServico.getDataEntrada()));
            st.setDate(3, java.sql.Date.valueOf(ordemServico.getDataSaida()));
            st.setString(4, ordemServico.getProblema());
            st.setString(5, ordemServico.getDiagnostico());
            st.setDouble(6, ordemServico.getValor());
            st.setString(7, ordemServico.getStatusOrdem().name());
            st.setInt(8, ordemServico.getId());

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
                    "DELETE FROM ordem_servico " +
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
