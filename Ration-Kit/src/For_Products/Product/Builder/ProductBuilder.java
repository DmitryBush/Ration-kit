package For_Products.Product.Builder;

import For_Products.Product.Product;

public interface ProductBuilder
{
    BuilderProductClass SetName(String name);
    BuilderProductClass SetOriginal(Product.Original original);
    BuilderProductClass SetTypeProduct(Product.Type_Product typeProduct);
    BuilderProductClass SetProtein(float protein);
    BuilderProductClass SetFats(float fats);
    BuilderProductClass SetCarbohydrates(float carbohydrates);
    BuilderProductClass SetMaxGramm(float max_gramm);
    Product BuildProduct();
}
