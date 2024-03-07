package Database;

import For_Products.Product;

import java.util.List;

public class AdapterDatabase extends Database implements IGetterData
{
    @Override
    public <T> List<T> GetData()
    {
        var resultSet = Select();
        // Обрабатываем каждую строку результата
//        while (resultSet.next())
//        {
//            String Name = resultSet.getString("name_products");
//            float Protein = resultSet.getFloat("protein");
//            float Fat = resultSet.getFloat("fat");
//            float Carbonohydrates = resultSet.getFloat("carbonohydrates");
//            int ID = resultSet.getInt("id");
//            boolean Vegetable = resultSet.getBoolean("vegetable");
//            boolean Garnish = resultSet.getBoolean("garnish");
//            boolean Adition = resultSet.getBoolean("adition");
//            boolean Basic = resultSet.getBoolean("basic");
//            int Limit = resultSet.getInt("max_count");
//            Product.Type_Product type_product = null;
//            Product.Original _original = null;
//
//            if (Vegetable == true) {
//                _original = Product.Original.Vegetable;
//            } else {
//                _original = Product.Original.Animal;
//            }
//
//            if (Garnish == true) {
//                type_product = Product.Type_Product.Garnish;
//            } else if (Adition == true) {
//                type_product = Product.Type_Product.Adition;
//            } else if (Basic == true) {
//                type_product = Product.Type_Product.Basic;
//            }
//
//            Product _product = new Product();
//            _product.Take_PFC_In_Base(Name, Protein, Fat, Carbonohydrates, _original, type_product, Limit);
//
//            if (type_product == Product.Type_Product.Garnish) {
//                Garnish_Products.add(_product);
//            } else if (type_product == Product.Type_Product.Adition) {
//                Adition_Products.add(_product);
//            } else if (type_product == Product.Type_Product.Basic) {
//                Basic_Products.add(_product);
//            }
//        }
        return null;
    }
}
