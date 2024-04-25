package ForProducts.Product.Builder;

import ForProducts.Product.Original;
import ForProducts.Product.Product;
import ForProducts.Product.Type_Product;

public interface ProductBuilder
{
    BuilderProductClass SetName(String name);
    BuilderProductClass SetOriginal(Original original);
    BuilderProductClass SetTypeProduct(Type_Product typeProduct);
    BuilderProductClass SetProtein(float protein);
    BuilderProductClass SetFats(float fats);
    BuilderProductClass SetCarbohydrates(float carbohydrates);
    BuilderProductClass SetMaxGramm(float max_gramm);
    Product BuildProduct();
}
