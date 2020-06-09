package jvd.ir.cooker.RoomDatabase.FoodHowTo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngredientsDao;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsModel2;

@Database(entities = {HowToModel2.class}, version = 1)
public abstract class HowToAppDatabase extends RoomDatabase {

    private static HowToAppDatabase instance;

    public abstract HowToDao howToDao();

    public static HowToAppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, HowToAppDatabase.class, "database-name-howto").allowMainThreadQueries().build();
        }
        return instance;
    }

}
