package jvd.ir.cooker.RoomDatabase.FoodMain;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodModel2.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract FoodDao foodies();

    public static AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "database-name").allowMainThreadQueries().build();
        }
        return instance;
    }
}
