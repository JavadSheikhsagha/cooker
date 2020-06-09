package jvd.ir.cooker.DetailActivity;

import java.util.List;
import io.reactivex.Single;
import jvd.ir.cooker.Model.ApiProvider;
import jvd.ir.cooker.Model.ApiService;
import jvd.ir.cooker.Model.FoodHowTo;
import jvd.ir.cooker.Model.FoodIngredients;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;
import jvd.ir.cooker.Model.MessageModel;

public class DetailApiService {

    ApiService apiService= ApiProvider.apiProvider();

    public Single<List<FoodModel>> getFoodDetail(String foodId){
        return apiService.getFoodDetail(foodId);
    }

    public Single<List<ImageModel>> getImages(String foodId){
        return apiService.getImages(foodId);
    }

    public Single<List<FoodIngredients>> getIng(String foodId){
        return apiService.getIng(foodId);
    }

    public Single<List<FoodHowTo>> getFoodInstruction(String foodId){
        return apiService.getFoodInstruction(foodId);
    }

    public Single<List<FoodModel>> getOtherFood(){
        return apiService.getIranianFood();
    }

    public Single<MessageModel> likeFood(String foodId,String userName){
        return apiService.likeFood(foodId,userName);
    }

    public Single<String > getIfFoodLiked(String foodId,String userName){
        return apiService.getIfFoodLiked(foodId, userName);
    }
}
