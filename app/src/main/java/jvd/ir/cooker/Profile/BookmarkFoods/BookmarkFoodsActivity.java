package jvd.ir.cooker.Profile.BookmarkFoods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import jvd.ir.cooker.R;
import jvd.ir.cooker.RoomDatabase.FoodMain.AppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodMain.FoodModel2;
import jvd.ir.cooker.RoomDetailActivity.RoomDetailActivity;

public class BookmarkFoodsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ImageView imgBack;

    List<FoodModel2> foods;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_foods);

        setupViews();

        getAllFromDB();
    }

    private void getAllFromDB() {

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                foods= AppDatabase.getInstance(getApplicationContext()).foodies().getAll();

                BookmarkFoodAdapter adapter=new BookmarkFoodAdapter(foods);
                adapter.setOnFoodClick(new BookmarkFoodAdapter.setOnFoodClick() {
                    @Override
                    public void onclick(FoodModel2 model) {
                        Intent intent =new Intent(BookmarkFoodsActivity.this, RoomDetailActivity.class);
                        intent.putExtra("food_title",model.getTitle());
                        intent.putExtra("food_id",model.getFoodId());
                        intent.putExtra("food_img",model.getImage());
                        startActivity(intent);
                        finish();
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        thread.start();

    }

    private void setupViews() {

        recyclerView = findViewById(R.id.rv_bookmarkFoods_rv);
        imgBack = findViewById(R.id.img_bookmarkFoods_back);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
