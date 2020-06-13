package jvd.ir.cooker.CategoryActivity;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.ApiProvider;
import jvd.ir.cooker.Model.ApiService;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;

public class CategoryApiService {

    private ApiService apiService= ApiProvider.apiProvider();

    public Single<List<FoodModel>> getCatListItems(String category){
        return apiService.getCatListItems(category);
    }

    public Single<List<CategoryModel>> getAllCats(){
        return apiService.getCatTop();
    }
}
