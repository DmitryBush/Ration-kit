package Database;

import ForProducts.Product.Builder.BuilderProductClass;
import ForProducts.Product.Original;
import ForProducts.Product.Product;
import ForProducts.Product.TypeProduct;

import java.sql.*;
import java.util.*;

public class Database implements Iterable<Product>
{
    String url, sql;
    private int size = 0;
    Properties authorization;
    Connection connection;

    public Database(String url, String sql)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connect(url);
            this.sql = sql;
            GetSize();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    private void Connect(String url) throws SQLException
    {
        this.url = url;
        authorization = new Properties();
        authorization.put("user", "postgres");
        authorization.put("password", "postgres");

        connection = DriverManager.getConnection(url, authorization);
    }
    public List<Product> Select(String sql)
    {
        try (Statement _statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE))
        {
            var table = _statement.executeQuery(sql);
            List<Product> list = new LinkedList<>();
            table.beforeFirst();
            while (table.next())
            {
                list.add(new BuilderProductClass().SetName(table.getString("name_products"))
                        .SetOriginal(table.getBoolean("vegetable") ? Original.Vegetable : Original.Animal)
                        .SetTypeProduct(table.getBoolean("garnish") ? TypeProduct.Garnish
                                : table.getBoolean("adition") ? TypeProduct.Addition
                                : table.getBoolean("basic") ? TypeProduct.Basic : null)
                        .SetProtein(table.getFloat("protein"))
                        .SetFats(table.getFloat("fat"))
                        .SetCarbohydrates(table.getFloat("carbonohydrates"))
                        .SetMaxGramm(table.getInt("max_count")).BuildProduct());
            }
            table.close();
            _statement.close();
            return list;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public int GetSize()
    {
        if (size == 0)
        {
            try (Statement _statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE))
            {
                var table = _statement.executeQuery(sql);
                table.last();
                size = table.getRow();
                table.beforeFirst();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        return size;
    }

    @Override
    public Iterator<Product> iterator()
    {
        return new Iterator<>()
        {
            Statement _statement;
            ResultSet table;

            {
                try
                {
                    _statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    table = _statement.executeQuery(sql);
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean hasNext()
            {
                try
                {
                    if (table.next())
                        return true;
                    else
                    {
                        table.beforeFirst();
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
                    return new BuilderProductClass().SetName(table.getString("name_products"))
                            .SetOriginal(table.getBoolean("vegetable") ? Original.Vegetable
                                    : Original.Animal)
                            .SetTypeProduct(table.getBoolean("garnish") ? TypeProduct.Garnish
                                    : table.getBoolean("adition") ? TypeProduct.Addition
                                    : table.getBoolean("basic") ? TypeProduct.Basic : null)
                            .SetProtein(table.getFloat("protein"))
                            .SetFats(table.getFloat("fat"))
                            .SetCarbohydrates(table.getFloat("carbonohydrates"))
                            .SetMaxGramm(table.getInt("max_count")).BuildProduct();
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void remove()
            {
                throw new RuntimeException();
            }
        };
    }
}
