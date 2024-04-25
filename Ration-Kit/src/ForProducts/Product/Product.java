package ForProducts.Product;

import java.util.Objects;

public class Product  {
    private float kilocalories, protein, fats, carbohydrates, max_gramm, cur_count_gramm;
    private String Name;
    private Original original;
    private Type_Product Type_product;

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

    public float getCur_count_gramm() {
        return cur_count_gramm;
    }

    public void setCur_count_gramm(float cur_count_gramm) {
        this.cur_count_gramm = cur_count_gramm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Float.compare(kilocalories, product.kilocalories) == 0 && Float.compare(protein, product.protein) == 0
                && Float.compare(fats, product.fats) == 0 && Float.compare(carbohydrates, product.carbohydrates) == 0
                && Float.compare(max_gramm, product.max_gramm) == 0
                && Float.compare(cur_count_gramm, product.cur_count_gramm) == 0
                && Objects.equals(Name, product.Name)
                && original == product.original
                && Type_product == product.Type_product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kilocalories, protein, fats, carbohydrates,
                max_gramm, cur_count_gramm, Name, original, Type_product);
    }

    @Override
    public String toString() {
        return "Продукт: " + Name + "\n" +
                "БЖУ продукта: " + protein + " " + fats + " " + carbohydrates + "\n" +
                "Вегетарианский ли продукт: " + original + "\n" +
                "Количество данного продукта: " + cur_count_gramm + "\n";
    }
}
