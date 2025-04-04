package easv.dk.eventticketsystem.dal.db;

import java.sql.Connection;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DBConnection {
    public Connection getConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("2025IntEventTicketSystem");
        ds.setServerName("EASV-DB4");
        ds.setPortNumber(1433);
        ds.setUser("CSe2024b_e_13");
        ds.setPassword("CSe2024bE13!24");
        ds.setTrustServerCertificate(true);
        return ds.getConnection();
    }
}
