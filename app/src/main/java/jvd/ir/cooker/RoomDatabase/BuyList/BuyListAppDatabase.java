package jvd.ir.cooker.RoomDatabase.BuyList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngredientsDao;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsModel2;

@Database(entities = {IngsModel2.class}, version = 1)
public abstract class BuyListAppDatabase extends RoomDatabase {

    private static BuyListAppDatabase instance;

    public abstract BuyListDao BuyList();

    public static BuyListAppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context,BuyListAppDatabase.class,"database-name-buy-list").allowMainThreadQueries().build();
        }
        return instance;
    }
}
