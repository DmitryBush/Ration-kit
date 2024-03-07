package Database;

import For_Products.Product.Product;

import java.sql.ResultSet;
import java.util.List;

public interface IReceived
{
    List<Product> Select();
}
