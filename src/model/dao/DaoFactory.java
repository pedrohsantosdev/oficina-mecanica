package model.dao;

import db.DB;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.OrdemServicoDaoJDBC;
import model.dao.impl.VeiculoDaoJDBC;

public class DaoFactory {

    public static ClienteDaoJDBC createClienteDao() {
        return new ClienteDaoJDBC(DB.getConnection());
    }

    public static VeiculoDaoJDBC createVeiuloDao() {
        return new VeiculoDaoJDBC(DB.getConnection());
    }

    public static OrdemServicoDaoJDBC createOrdemServicoDao() {
        return new OrdemServicoDaoJDBC(DB.getConnection());
    }
}
