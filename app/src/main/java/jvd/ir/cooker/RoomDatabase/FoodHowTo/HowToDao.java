package jvd.ir.cooker.RoomDatabase.FoodHowTo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsModel2;

@Dao
public interface HowToDao {

    @Query("SELECT * FROM howtomodel2")
    List<HowToModel2> getAll();

    @Query("SELECT * FROM howtomodel2 WHERE uid IN (:userIds)")
    List<HowToModel2> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM howtomodel2 WHERE food_id =:foodId ")
    List<HowToModel2> findByFoodID(String foodId);

    @Query("SELECT COUNT(uid) FROM howtomodel2")
    int getDataCount();

    @Insert
    void insertAll(HowToModel2... ings);

    @Delete
    void delete(HowToModel2 user);

    @Query("DELETE FROM howtomodel2")
    void deleteAll();

    @Query("DELETE FROM howtomodel2 WHERE food_id=:foodId")
    void deleteById(String foodId);
}
