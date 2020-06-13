package jvd.ir.cooker.CategoryActivity;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;

public class CategoryRepository {

    private CategoryApiService apiService=new CategoryApiService();


    public Single<List<FoodModel>> getCatListItems(String category){
        return apiService.getCatListItems(category);
    }

    public Single<List<CategoryModel>> getAllCats(){
        return apiService.getAllCats();
    }
}
