package For_Products.Product;

public interface ProductBuilder
{
    void SetName(String name);
    void SetOriginal(Product.Original original);
    void SetTypeProduct(Product.Type_Product typeProduct);
    void SetKilocalories(float kilocalories);
    void SetProtein(float protein);
    void SetFats(float fats);
    void SetCarbohydrates(float carbohydrates);
    void SetMaxGramm(float max_gramm);
    Product BuildProduct();
}
