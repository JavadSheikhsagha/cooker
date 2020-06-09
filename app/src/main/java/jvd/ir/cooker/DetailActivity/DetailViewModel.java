package jvd.ir.cooker.DetailActivity;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.cooker.Model.FoodHowTo;
import jvd.ir.cooker.Model.FoodIngredients;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;
import jvd.ir.cooker.Model.MessageModel;

public class DetailViewModel {

    DetailRepository repository;

    public DetailViewModel(Context context){
        repository=new DetailRepository(context);
    }

    public Single<List<FoodModel>> getFoodDetail(String foodId){
        return repository.getFoodDetail(foodId);
    }

    public Single<List<ImageModel>> getImage(String foodId){
        return repository.getImages(foodId);
    }

    public Single<List<FoodIngredients>> getIng(String foodId){
        return repository.getIng(foodId);
    }

    public Single<List<FoodHowTo>> getFoodInstruction(String foodId){
        return repository.getFoodInstruction(foodId);
    }

    public Single<List<FoodModel>> getOtherFood(){
        return repository.getOtherFood();
    }

    public Single<MessageModel> likeFood(String foodId, String userName){
        return repository.likeFood(foodId,userName);
    }

    public Single<String > getIfFoodLiked(String foodId,String userName){
        return repository.getIfFoodLiked(foodId, userName);
    }

    public String getUserName(){
        return repository.getUserName();
    }

}
