package For_Products.Product;

public class BuilderProductClass implements ProductBuilder
{
    private Product _product = new Product();
    @Override
    public void SetName(String name)
    {
        _product.setName(name);
    }

    @Override
    public void SetOriginal(Product.Original original)
    {
        _product.setOriginal(original);
    }

    @Override
    public void SetTypeProduct(Product.Type_Product typeProduct)
    {
        _product.setType_product(typeProduct);
    }

    @Override
    public void SetKilocalories(float kilocalories)
    {
        _product.setKilocalories(kilocalories);
    }

    @Override
    public void SetProtein(float protein)
    {
        _product.setProtein(protein);
    }

    @Override
    public void SetFats(float fats)
    {
        _product.setFats(fats);
    }

    @Override
    public void SetCarbohydrates(float carbohydrates)
    {
        _product.setCarbohydrates(carbohydrates);
    }

    @Override
    public void SetMaxGramm(float max_gramm)
    {
        _product.setMax_gramm(max_gramm);
    }

    @Override
    public Product BuildProduct()
    {
        return _product;
    }
}
