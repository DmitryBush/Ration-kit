package For_Products.Product;

public class Product  {
    public float kilocalories, protein, fats, carbohydrates, max_gramm, cur_count_gramm;
    public String Name;
    public Original original;
    public Type_Product Type_product;



    public enum Original{
        Animal,
        Vegetable
    }

    public enum Type_Product{
        Garnish,
        Adition,
        Basic
    }


    public void Take_PFC_In_Base(String _name, float _protein, float _fats, float _carbohydrates,
                                 Original _original, Type_Product _type, float _max_gramm) {

        Name = _name;
        kilocalories = _protein*4 + _carbohydrates*4 + _fats*9;
        protein =  _protein;
        fats = _fats;
        carbohydrates = _carbohydrates;
        original = _original;
        Type_product = _type;
        max_gramm = _max_gramm;
    }

    public float getKilocalories() {
        return kilocalories;
    }

    public void setKilocalories()
    {
        this.kilocalories = protein*4 + carbohydrates*4 + fats*9;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getMax_gramm() {
        return max_gramm;
    }

    public void setMax_gramm(float max_gramm) {
        this.max_gramm = max_gramm;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public Type_Product getType_product() {
        return Type_product;
    }

    public void setType_product(Type_Product type_product) {
        Type_product = type_product;
    }
}
