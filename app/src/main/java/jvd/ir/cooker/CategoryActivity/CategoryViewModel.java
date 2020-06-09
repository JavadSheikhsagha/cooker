package jvd.ir.cooker.CategoryActivity;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.FoodModel;

public class CategoryViewModel {

    CategoryRepository repository=new CategoryRepository();

    public Single<List<FoodModel>> getCatListItems(String category){
        return repository.getCatListItems(category);
    }
}
