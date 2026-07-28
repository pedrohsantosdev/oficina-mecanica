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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT " +
                            "o.*, v.id AS VeiculoId, v.placa, v.marca, v.modelo, v.ano, " +
                            "v.cor, v.quilometragem, c.id AS ClienteId, c.nome, c.cpf, c.telefone, c.email " +
                            "FROM ordem_servico o " +
                            "INNER JOIN veiculo v ON o.veiculo_id = v.id " +
                            "INNER JOIN cliente c ON v.cliente_id = c.id " +
                            "WHERE o.id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                Cliente c = instanciarCliente(rs);
                Veiculo v = instanciarVeiculo(rs, c);
                OrdemServico o = instanciarOrdemServico(rs, v);
                return o;
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
    public List<OrdemServico> findByVeiculo(Veiculo veiculo) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT " +
                            "o.*, v.id AS VeiculoId, v.placa, v.marca, v.modelo, v.ano, " +
                            "v.cor, v.quilometragem, c.id AS ClienteId, c.nome, c.cpf, c.telefone, c.email " +
                            "FROM ordem_servico o " +
                            "INNER JOIN veiculo v ON o.veiculo_id = v.id " +
                            "INNER JOIN cliente c ON v.cliente_id = c.id " +
                            "WHERE v.id = ?"
            );

            st.setInt(1, veiculo.getId());

            rs = st.executeQuery();

            List<OrdemServico> list = new ArrayList<>();

            Cliente c = null;
            Veiculo v = null;

                while (rs.next()) {

                    if(v == null) {

                        c = instanciarCliente(rs);
                        v = instanciarVeiculo(rs, c);

                    }

                    OrdemServico o = instanciarOrdemServico(rs, v);
                    list.add(o);

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

    private Cliente instanciarCliente(ResultSet rs) throws SQLException {

        Cliente cliente = new Cliente();

        cliente.setId(rs.getInt("ClienteId"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));

        return cliente;

    }

    private Veiculo instanciarVeiculo(ResultSet rs, Cliente c) throws SQLException {

        Veiculo veiculo = new Veiculo();

        veiculo.setId(rs.getInt("VeiculoId"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setQuilometragem(rs.getInt("quilometragem"));
        veiculo.setCliente(c);

        return veiculo;

    }

    private OrdemServico instanciarOrdemServico(ResultSet rs, Veiculo v) throws SQLException {

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setId(rs.getInt("id"));
        ordemServico.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
        ordemServico.setDataSaida(rs.getDate("data_saida").toLocalDate());
        ordemServico.setProblema(rs.getString("problema"));
        ordemServico.setDiagnostico(rs.getString("diagnostico"));
        ordemServico.setValor(rs.getDouble("valor"));
        ordemServico.setStatusOrdem(StatusOrdem.valueOf(rs.getString("status_ordem")));
        ordemServico.setVeiculo(v);

        return ordemServico;

    }
}
