package jvd.ir.cooker.Model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("getiranianfood.php")
    Single<List<FoodModel>> getIranianFood();

    @GET("getbestfood.php")
    Single<List<FoodModel>> getBestFood();

    @GET("foodimage.php")
    Single<List<ImageModel>> getImages(@Query("food_id") String foodId);

    @GET("getvegan.php")
    Single<List<FoodModel>> getVegan();

    @GET("getfooddetail.php")
    Single<List<FoodModel>> getFoodDetail(@Query("food_id") String foodId);

    @GET("getings.php")
    Single<List<FoodIngredients>> getIng(@Query("food_id") String foodId);

    @GET("getintro.php")
    Single<List<FoodHowTo>> getFoodInstruction(@Query("food_id") String foodId);

    @GET("getcomment.php")
    Single<List<CommentsModel>> getComments(@Query("food_id") String foodId);

    @GET("addcomment.php")
    Single<MessageModel> addComment(@Query("food_id") String foodId,
                                    @Query("comment") String comment,
                                    @Query("user_name") String userName);

    @GET("likecomment.php")
    Single<MessageModel> likeComment(@Query("comment_id") String commentId,
                                     @Query("user_name") String userName);

    @GET("getifliked.php")
    Single<String> getIfCommentLiked(@Query("comment_id") String comment_id,
                                     @Query("user_name") String userName);

    @GET("getcattop.php")
    Single<List<CategoryModel>> getCatTop();

    @GET("getcatlistitems.php")
    Single<List<FoodModel>> getCatListItems(@Query("category") String category);

    @GET("likefood.php")
    Single<MessageModel> likeFood(@Query("food_id") String foodId,
                                  @Query("user_name") String userName);

    @GET("getiffoodlike.php")
    Single<String> getIfFoodLiked(@Query("food_id") String foodId,
                                  @Query("user_name") String userName);

    @GET("userlogin.php")
    Single<MessageModel> userLogin(@Query("user_name") String userName,
                                   @Query("password") String password);

    @GET("userregister.php")
    Single<MessageModel> userRegister(@Query("user_name") String userName,
                                      @Query("password") String Password,
                                      @Query("mobile") String number);

    @GET("getmycomments.php")
    Single<List<CommentsModel>> getMyComments(@Query("user_name") String userName);

    @GET("getlikedcomments.php")
    Single<List<CommentsModel>> getLikedComments(@Query("user_name") String userName);

    @GET("getlikedfoods.php")
    Single<List<FoodModel>> getLikedFoods(@Query("user_name") String userName);

    @GET("reportcomment.php")
    Single<MessageModel> reportComment(@Query("user_name") String userName,
                                       @Query("comment_id") String commentId,
                                       @Query("reason") String reason);

    @GET("getreportcomments.php")
    Single<List<CommentsModel>> getReportedComments(@Query("user_name") String userName);

    @GET("getdrinks.php")
    Single<List<FoodModel>> getDrinks();

    @GET("getdeserts.php")
    Single<List<FoodModel>> getDeserts();

    @GET("getprofile.php")
    Single<ProfileModel> getProfile(@Query("user_name") String userName);

    @GET("updateprofile.php")
    Single<MessageModel> updateProfile(@Query("user_name") String userName,
                                       @Query("mobile") String mobile);

}
