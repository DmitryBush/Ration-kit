package Database;

import java.sql.*;
import java.util.Properties;

public class Database
{
    Properties authorization;
    Connection connection;

    public Database()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connect();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    private void Connect() throws SQLException
    {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        authorization = new Properties();
        authorization.put("user", "postgres");
        authorization.put("password", "postgres");

        connection = DriverManager.getConnection(url, authorization);
    }

    public ResultSet Select()
    {
        String sql = "SELECT name_products, protein, fat, carbonohydrates, id, vegetable, garnish, adition, basic, max_count FROM public.products";
        try (Statement _statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE))
        {
            return _statement.executeQuery(sql);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
