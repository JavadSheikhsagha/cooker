package jvd.ir.cooker.Model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cook/getiranianfood.php")
    Single<List<FoodModel>> getIranianFood();

    @GET("cook/getbestfood.php")
    Single<List<FoodModel>> getBestFood();

    @GET("cook/foodimage.php")
    Single<List<ImageModel>> getImages(@Query("food_id") String foodId);

    @GET("cook/getvegan.php")
    Single<List<FoodModel>> getVegan();

    @GET("cook/getfooddetail.php")
    Single<List<FoodModel>> getFoodDetail(@Query("food_id") String foodId);

    @GET("cook/getings.php")
    Single<List<FoodIngredients>> getIng(@Query("food_id") String foodId);

    @GET("cook/getintro.php")
    Single<List<FoodHowTo>> getFoodInstruction(@Query("food_id") String foodId);

    @GET("cook/getcomment.php")
    Single<List<CommentsModel>> getComments(@Query("food_id") String foodId);

    @GET("cook/addcomment.php")
    Single<MessageModel> addComment(@Query("food_id") String foodId,
                                    @Query("comment") String comment,
                                    @Query("user_name") String userName);

    @GET("cook/likecomment.php")
    Single<MessageModel> likeComment(@Query("comment_id") String commentId,
                                     @Query("user_name") String userName);

    @GET("cook/getifliked.php")
    Single<String> getIfCommentLiked(@Query("comment_id") String comment_id,
                                     @Query("user_name") String userName);

    @GET("cook/getcattop.php")
    Single<List<CategoryModel>> getCatTop();

    @GET("cook/getcatlistitems.php")
    Single<List<FoodModel>> getCatListItems(@Query("category") String category);

    @GET("cook/likefood.php")
    Single<MessageModel> likeFood(@Query("food_id") String foodId,
                                  @Query("user_name") String userName);

    @GET("cook/getiffoodlike.php")
    Single<String> getIfFoodLiked(@Query("food_id") String foodId,
                                  @Query("user_name") String userName);

    @GET("cook/userlogin.php")
    Single<MessageModel> userLogin(@Query("user_name") String userName,
                                   @Query("password") String password);

    @GET("cook/userregister.php")
    Single<MessageModel> userRegister(@Query("user_name") String userName,
                                      @Query("password") String Password,
                                      @Query("mobile") String number);

    @GET("cook/getmycomments.php")
    Single<List<CommentsModel>> getMyComments(@Query("user_name") String userName);

    @GET("cook/getlikedcomments.php")
    Single<List<CommentsModel>> getLikedComments(@Query("user_name") String userName);

    @GET("cook/getlikedfoods.php")
    Single<List<FoodModel>> getLikedFoods(@Query("user_name") String userName);

    @GET("cook/reportcomment.php")
    Single<MessageModel> reportComment(@Query("user_name") String userName,
                                       @Query("comment_id") String commentId,
                                       @Query("reason") String reason);

    @GET("cook/getreportcomments.php")
    Single<List<CommentsModel>> getReportedComments(@Query("user_name") String userName);

    @GET("cook/getdrinks.php")
    Single<List<FoodModel>> getDrinks();

    @GET("cook/getdeserts.php")
    Single<List<FoodModel>> getDeserts();

    @GET("cook/getprofile.php")
    Single<ProfileModel> getProfile(@Query("user_name") String userName);

    @GET("cook/updateprofile.php")
    Single<MessageModel> updateProfile(@Query("user_name") String userName,
                                       @Query("mobile") String mobile);

}
