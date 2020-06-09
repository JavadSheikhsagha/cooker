package jvd.ir.cooker.MainActivity;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.LoginActivity.UserLoginInfo;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;

public class MainRepository {

    MainApiService apiService=new MainApiService();

    UserLoginInfo userLoginInfo;

    public MainRepository(Context context){
        userLoginInfo=new UserLoginInfo(context);
    }

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

    public String getUserName(){
        return userLoginInfo.getUserLoginInfo();
    }

    public Single<List<FoodModel>> getDrinks(){
        return apiService.getDrinks();
    }

    public Single<List<FoodModel>> getDeserts(){
        return apiService.getDeserts();
    }


}
