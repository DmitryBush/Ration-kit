package For_Products.Product.Builder;

import For_Products.Product.Product;

public class BuilderProductClass implements ProductBuilder
{
    private Product _product = new Product();
    @Override
    public BuilderProductClass SetName(String name)
    {
        _product.setName(name);
        return this;
    }

    @Override
    public BuilderProductClass SetOriginal(Product.Original original)
    {
        _product.setOriginal(original);
        return this;
    }

    @Override
    public BuilderProductClass SetTypeProduct(Product.Type_Product typeProduct)
    {
        _product.setType_product(typeProduct);
        return this;
    }

    @Override
    public BuilderProductClass SetProtein(float protein)
    {
        _product.setProtein(protein);
        return this;
    }

    @Override
    public BuilderProductClass SetFats(float fats)
    {
        _product.setFats(fats);
        return this;
    }

    @Override
    public BuilderProductClass SetCarbohydrates(float carbohydrates)
    {
        _product.setCarbohydrates(carbohydrates);
        return this;
    }

    @Override
    public BuilderProductClass SetMaxGramm(float max_gramm)
    {
        _product.setMax_gramm(max_gramm);
        return this;
    }

    @Override
    public Product BuildProduct()
    {
        _product.setKilocalories();
        return _product;
    }
}
