package jvd.ir.cooker.RoomDatabase.FoodIngredients;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import jvd.ir.cooker.RoomDatabase.FoodMain.FoodModel2;

@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM ingsmodel2 ")
    List<IngsModel2> getAll();

    @Query("SELECT * FROM ingsmodel2 WHERE uid IN (:userIds)")
    List<IngsModel2> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM ingsmodel2 WHERE food_id =:foodId ")
    List<IngsModel2> findByFoodID(String foodId);

    @Query("SELECT COUNT(uid) FROM ingsmodel2")
    int getDataCount();

    @Insert
    void insertAll(IngsModel2... ings);

    @Delete
    void delete(IngsModel2 user);

    @Query("DELETE FROM ingsmodel2")
    void deleteAll();

    @Query("DELETE FROM ingsmodel2 WHERE food_id=:foodId")
    void deleteById(String foodId);



}
