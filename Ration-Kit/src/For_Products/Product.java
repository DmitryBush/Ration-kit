package For_Products;

public class Product  {

    private float kilocalories, protein, fats, carbohydrates;

    Original original;

    private enum Original{
        Animal,
        Vegetable
    }

    private enum Type_Product{
        Garnish,
        Adition,
        Basic
    }


    public void Take_PFC_In_Base(float _kilocalories, float _protein, float _fats , float _carbohydrates, Original _original) {

        kilocalories = _kilocalories;
        protein =  _protein;
        fats = _fats;
        carbohydrates = _carbohydrates;
        original = _original;
    }

}
