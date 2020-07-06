package jvd.ir.cooker.MainActivity;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.CategoryModel;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;

public class MainViewModel {

    MainRepository repository;

    public MainViewModel(Context context){
        repository=new MainRepository(context);
    }

    public Single<List<FoodModel>> getIranianFood(){
        return repository.getIranianFood();
    }

    public Single<List<FoodModel>> getBestFood(){
        return repository.getBestFood();
    }

    public Single<List<ImageModel>> getImages(String foodId){
        return repository.getImages(foodId);
    }

    public Single<List<FoodModel>> getVegan(){
        return repository.getVegan();
    }

    public Single<List<CategoryModel>> getCatTop(){
        return repository.getCatTop();
    }

    public String getUserName(){
        return repository.getUserName();
    }

    public Single<List<FoodModel>> getDrinks(){
        return repository.getDrinks();
    }

    public Single<List<FoodModel>> getDeserts(){
        return repository.getDeserts();
    }

    public Single<List<ImageModel>> getInstagramLink(){
        return repository.getInstagramLink();
    }

}
