package jvd.ir.cooker.RoomDatabase.FoodIngredients;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import jvd.ir.cooker.RoomDatabase.FoodMain.AppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodMain.FoodDao;
import jvd.ir.cooker.RoomDatabase.FoodMain.FoodModel2;

@Database(entities = {IngsModel2.class}, version = 1)
public abstract class IngsAppDatabase extends RoomDatabase {

    private static IngsAppDatabase instance;

    public abstract IngredientsDao ings();

    public static IngsAppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, IngsAppDatabase.class, "database-name-ings").allowMainThreadQueries().build();
        }
        return instance;
    }

}
