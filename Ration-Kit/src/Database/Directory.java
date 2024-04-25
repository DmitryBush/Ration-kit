package Database;

import ForProducts.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Directory
{
    private final List<Product> Basic_Products;
    private final List<Product> Garnish_Products;
    private final List<Product> Addition_Products;

    public Directory()
    {
        Database db = new Database("jdbc:postgresql://localhost:5432/postgres",
                "SELECT * FROM products");

        Basic_Products = new ArrayList<>(db.Select("select * from products where basic = true"));
        Garnish_Products = new ArrayList<>(db.Select("select * from products where garnish = true"));
        Addition_Products = new ArrayList<>(db.Select("select * from products where adition = true"));
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
