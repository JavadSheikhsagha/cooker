package jvd.ir.cooker.RoomDatabase.FoodMain;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {


    @Query("SELECT * FROM foodmodel2 ")
    List<FoodModel2> getAll();

    @Query("SELECT * FROM foodmodel2 WHERE uid IN (:userIds)")
    List<FoodModel2> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM foodmodel2 WHERE title LIKE :title  ")
    FoodModel2 findByName(String title);

    @Insert
    void insertAll(FoodModel2... foods);

    @Delete
    void delete(FoodModel2 user);

    @Query("DELETE FROM foodmodel2")
    void deleteAll();

    @Query("DELETE FROM foodmodel2 WHERE food_id = :foodId")
    void deleteById(String foodId);




}
