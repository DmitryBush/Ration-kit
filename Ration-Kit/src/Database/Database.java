package Database;

import For_Products.Product.Product;

import java.sql.*;
import java.util.*;

public class Database implements IReceived, Iterable<Product>
{
    private int size = 0;
    Properties authorization;
    Connection connection;
    ResultSet table = null;

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
        String sql = "SELECT * FROM products";
        try (Statement _statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE))
        {
            table = _statement.executeQuery(sql);
            table.last();
            size = table.getRow();
            table.beforeFirst();
            return table;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public int GetSize()
    {
        return size;
    }

    @Override
    public Iterator<Product> iterator()
    {
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                try
                {
                    if (table.next())
                        return true;
                    else
                    {
                        table.first();
                        return false;
                    }
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Product next()
            {
                try
                {
                    List<String> row = new LinkedList<>();
                    for (var i = 1; i < table.getMetaData().getColumnCount(); i++)
                        row.add(table.getString(i));


                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void remove()
            {
                Iterator.super.remove();
            }
        };
    }
}
