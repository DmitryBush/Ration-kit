package Database;

import For_Products.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Directory
{
    private List<Product> Basic_Products;
    private List<Product> Garnish_Products;
    private List<Product> Addition_Products;

    public Directory()
    {
        Database db = new Database("jdbc:postgresql://localhost:5432/postgres",
                "SELECT * FROM products");

        Basic_Products = new ArrayList<>(db.Select("select * from products where basic = true"));
        Garnish_Products = new ArrayList<>(db.Select("select * from products where garnish = true"));
        Addition_Products = new ArrayList<>(db.Select("select * from products where adition = true"));
    }
}
