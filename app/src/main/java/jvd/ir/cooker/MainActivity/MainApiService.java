package jvd.ir.cooker.MainActivity;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.ApiProvider;
import jvd.ir.cooker.Model.ApiService;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;

public class MainApiService {

    ApiService apiService= ApiProvider.apiProvider();

    public Single<List<FoodModel>> getIranianFood(){
        return apiService.getIranianFood();
    }

    public Single<List<FoodModel>> getBestFood(){
        return apiService.getBestFood();
    }

    public Single<List<ImageModel>> getImages(String foodId){
        return apiService.getImages(foodId);
    }

    public Single<List<FoodModel>> getVegan(){
        return apiService.getVegan();
    }

    public Single<List<CategoryModel>> getCatTop(){
        return apiService.getCatTop();
    }

    public Single<List<FoodModel>> getDrinks(){
        return apiService.getDrinks();
    }

    public Single<List<FoodModel>> getDeserts(){
        return apiService.getDeserts();
    }

    public Single<List<ImageModel>> getInstagramLink(){
        return apiService.getImages("-1");
    }



}
