package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO <ValueObjects> {
    
    public boolean adiciona(ValueObjects dado)
            throws SQLException;
    
    public boolean remove(ValueObjects dado)
            throws SQLException;
    
    public boolean atualiza(ValueObjects dado)
            throws SQLException;
    
    public ValueObjects busca(ValueObjects dado)
            throws SQLException;
    
    public List<ValueObjects> listar(String criterio)
            throws SQLException;
}
