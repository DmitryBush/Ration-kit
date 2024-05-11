package Database;

import ForProducts.Product.Product;
import ForProducts.Product.Type_of_Diet;
import Human.Human;

import java.util.ArrayList;
import java.util.List;

public class Directory
{
    private static volatile Directory instance = null;
    private final List<Product> Basic_Products;
    private final List<Product> Garnish_Products;
    private final List<Product> Addition_Products;

    private Directory()
    {
        Database db = new Database("jdbc:postgresql://localhost:5432/postgres",
                "SELECT * FROM products");

        Basic_Products = new ArrayList<>(db.Select("select * from products where basic = true"));
        Garnish_Products = new ArrayList<>(db.Select("select * from products where garnish = true"));
        Addition_Products = new ArrayList<>(db.Select("select * from products where adition = true"));
    }

    public static Directory GetInstance()
    {
        if (instance == null)
        {
            synchronized (Directory.class)
            {
                instance = new Directory();
            }
        }
        return instance;
    }

    public List<Product> getBasic_Products() {
        return Basic_Products;
    }

    public List<Product> getGarnish_Products() {
        return Garnish_Products;
    }

    public List<Product> getAddition_Products() {
        return Addition_Products;
    }
}
