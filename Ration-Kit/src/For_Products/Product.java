package For_Products;

public class Product  {

    public float kilocalories, protein, fats, carbohydrates, max_gramm;
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


    public void Take_PFC_In_Base(String _name, float _protein, float _fats, float _carbohydrates, Original _original, Type_Product _type, float _max_gramm) {

        Name = _name;
        kilocalories = _protein*4 + _carbohydrates*4 + _fats*9;
        protein =  _protein;
        fats = _fats;
        carbohydrates = _carbohydrates;
        original = _original;
        Type_product = _type;
        max_gramm = _max_gramm;
    }




}
