package jvd.ir.cooker.RoomDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jvd.ir.cooker.DetailActivity.DetailActivity;
import jvd.ir.cooker.DetailActivity.HelpDifficultyDialog;
import jvd.ir.cooker.DetailActivity.HelpTimeDialog;
import jvd.ir.cooker.DetailActivity.IngredientsAdapter;
import jvd.ir.cooker.Profile.BookmarkFoods.BookmarkFoodsActivity;
import jvd.ir.cooker.R;
import jvd.ir.cooker.RoomDatabase.FoodHowTo.HowToAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodHowTo.HowToModel2;
import jvd.ir.cooker.RoomDatabase.FoodHowTo.HowToRvAdapter;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsModel2;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsRvAdapter;
import jvd.ir.cooker.RoomDatabase.FoodMain.AppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodMain.FoodModel2;

public class RoomDetailActivity extends AppCompatActivity {

    TextView txtTitle1, txtTitle2, txtUser, txtTime, txtCategory, txtPeople, txtDifficulty, txtSaved;

    ImageView imgBack, imgDifficulty, imgTime, imgPeople, imgHelpDifficulty, imgHelpTime, imgHelpPeople;

    Button btnComments, btnShareFood, btnDelete;

    NestedScrollView scrollView;

    RecyclerView rvIngredients, rvHowTo, rvOther;

    ImageView image;

    String FOOD_ID, FOOD_IMG, FOOD_NAME, USER_NAME, CHEF_ID;

    int positioning;

    int PEOPLE = 3;

    Thread thread, thread2, thread3, thread4;

    FoodModel2 foodModel2 = new FoodModel2();

    List<IngsModel2> ingsModel2s = new ArrayList<>();

    List<HowToModel2> howToModel2s = new ArrayList<>();

    IngsRvAdapter adapter;

    setOnDeleteClick onDeleteClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        FOOD_ID = getIntent().getStringExtra("food_id");
        FOOD_IMG = getIntent().getStringExtra("food_img");
        FOOD_NAME = getIntent().getStringExtra("food_title");

        setupViews();

        getMainDataFromDB();

        getIngsFromDB();

        getHowToFromDB();

    }

    private void iconsInDetailActivity() {

        int timeSpent = Integer.parseInt(foodModel2.getTime());

        if (timeSpent < 31) {
            imgTime.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorGreenLight));
        } else if (timeSpent > 30 && timeSpent < 61) {
            imgTime.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorGreenDark));
        } else if (timeSpent > 60 && timeSpent < 91) {
            imgTime.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorPrimary));
        } else if (timeSpent > 90 && timeSpent < 121) {
            imgTime.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorPrimaryDark));
        } else if (timeSpent > 120) {
            imgTime.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorRed));
        }

        int difficulty = Integer.parseInt(foodModel2.getDifficulty());

        if (difficulty == 1) {
            txtDifficulty.setText(" ساده");
            imgDifficulty.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorGreenLight));
        }
        if (difficulty == 2) {
            txtDifficulty.setText("آسان ");
            imgDifficulty.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorGreenDark));
        }
        if (difficulty == 3) {
            txtDifficulty.setText(" معمولی");
            imgDifficulty.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorPrimary));
        }
        if (difficulty == 4) {
            txtDifficulty.setText(" سخت");
            imgDifficulty.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorRed));
        }
        if (difficulty == 5) {
            txtDifficulty.setText(" سخت");
            imgDifficulty.setColorFilter(ContextCompat.getColor(RoomDetailActivity.this, R.color.colorRed));
        }

    }

    private void getHowToFromDB() {

        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                int amountT = HowToAppDatabase.getInstance(getApplicationContext()).howToDao().getDataCount();
                for (int i = 0; i < amountT; i++) {
                    howToModel2s = HowToAppDatabase.getInstance(getApplicationContext()).howToDao().findByFoodID(FOOD_ID);
                }

                HowToRvAdapter adapter = new HowToRvAdapter(getApplicationContext(), howToModel2s);
                rvHowTo.setAdapter(adapter);
            }
        });
        thread3.start();
    }

    private void getIngsFromDB() {

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                int amountT = IngsAppDatabase.getInstance(getApplicationContext()).ings().getDataCount();
                for (int i = 0; i < amountT; i++) {
                    ingsModel2s = IngsAppDatabase.getInstance(getApplicationContext()).ings().findByFoodID(FOOD_ID);
                }
                adapter = new IngsRvAdapter(getApplicationContext(), ingsModel2s,1);
                rvIngredients.setAdapter(adapter);

            }
        });
        thread2.start();
    }

    private void getMainDataFromDB() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                foodModel2 = AppDatabase.getInstance(getApplicationContext()).foodies().findByName(FOOD_NAME);

                txtTitle1.setText(foodModel2.getTitle());
                txtTitle2.setText(foodModel2.getTitle());
                txtCategory.setText(foodModel2.getCategory());
                txtDifficulty.setText(foodModel2.getDifficulty());
                txtTime.setText(foodModel2.getTime());
                txtUser.setText(foodModel2.getChefId());

            }
        });

        thread.start();

    }

    private void setupViews() {

        scrollView = findViewById(R.id.scroll_detailRoom_nested);

        image = findViewById(R.id.img_detailRoom);

        txtTitle1 = findViewById(R.id.txt_detailRoom_title1);
        txtTitle2 = findViewById(R.id.txt_detailRoom_title2);
        txtCategory = findViewById(R.id.txt_detailRoom_category);
        txtUser = findViewById(R.id.txt_detailRoom_user);
        txtTime = findViewById(R.id.txt_detailRoom_time);
        txtPeople = findViewById(R.id.txt_detailRoom_people);
        txtDifficulty = findViewById(R.id.txt_detailRoom_difficulty);
        txtSaved = findViewById(R.id.txt_detailRoom_saved);

        imgBack = findViewById(R.id.img_detailRoom_back);
        imgTime = findViewById(R.id.img_detainRoom_time);
        imgDifficulty = findViewById(R.id.img_detailRoom_hardness);
        imgPeople = findViewById(R.id.img_detailRoom_people);
        imgHelpDifficulty = findViewById(R.id.img_detailRoom_helpDifficulty);
        imgHelpTime = findViewById(R.id.img_detailRoom_helpTime);

        btnDelete = findViewById(R.id.btn_detailRoom_delete);

        rvIngredients = findViewById(R.id.rv_detailRoom_ing);
        rvHowTo = findViewById(R.id.rv_detailRoom_howTo);

        txtTitle2.setVisibility(View.GONE);
        imgBack.setVisibility(View.GONE);
        txtSaved.setVisibility(View.GONE);
        imgBack.setClickable(false);
        positioning = 0;

        txtPeople.setText("برای " + PEOPLE + " نفر ");

        if (PEOPLE > 0 & PEOPLE < 4) {
            imgPeople.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenLight));
        } else if (PEOPLE >= 4) {
            imgPeople.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenDark));
        }

        scrollThingy();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomDetailActivity.this, BookmarkFoodsActivity.class));
                finish();
            }
        });

        rvIngredients.setLayoutManager(new LinearLayoutManager(this));
        rvHowTo.setLayoutManager(new LinearLayoutManager(this));
        rvHowTo.setFocusable(false);
        rvIngredients.setFocusable(false);

        imgHelpTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpTimeDialog dialog = new HelpTimeDialog();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        imgHelpDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpDifficultyDialog dialog = new HelpDifficultyDialog();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thread4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        IngsAppDatabase.getInstance(getApplicationContext()).ings().deleteById(FOOD_ID);
                        HowToAppDatabase.getInstance(getApplicationContext()).howToDao().deleteById(FOOD_ID);
                        AppDatabase.getInstance(getApplicationContext()).foodies().deleteById(FOOD_ID);
                    }
                });
                thread4.start();
                startActivity(new Intent(RoomDetailActivity.this, BookmarkFoodsActivity.class));
                finish();
            }
        });

    }

    public interface setOnDeleteClick {
        void onClick(String foodName);
    }

    private void scrollThingy() {

        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, final int scrollY, int oldScrollX, final int oldScrollY) {
                Log.i("LOG3", "onScrollChange: " + scrollY);

                Thread thread;

                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if (scrollY > 30 && scrollY > oldScrollY && positioning == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtTitle2.setVisibility(View.VISIBLE);
                                    imgBack.setVisibility(View.VISIBLE);
                                    txtSaved.setVisibility(View.VISIBLE);
                                    imgBack.setClickable(true);

                                    YoYo.with(Techniques.FadeInUp)
                                            .duration(500)
                                            .repeat(0)
                                            .playOn(txtSaved);
                                    YoYo.with(Techniques.FadeInUp)
                                            .duration(500)
                                            .repeat(0)
                                            .playOn(txtTitle2);

                                    YoYo.with(Techniques.FadeInUp)
                                            .duration(500)
                                            .repeat(0)
                                            .playOn(imgBack);
                                    positioning = 1;
                                }
                            });

                        }
                        if (scrollY < 30 && scrollY < oldScrollY && positioning == 1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtTitle2.setVisibility(View.GONE);
                                    imgBack.setVisibility(View.GONE);
                                    txtSaved.setVisibility(View.GONE);
                                    imgBack.setClickable(false);

                                    YoYo.with(Techniques.FadeOutDown)
                                            .repeat(0)
                                            .duration(500)
                                            .playOn(txtSaved);
                                    YoYo.with(Techniques.FadeOutDown)
                                            .duration(300)
                                            .repeat(0)
                                            .playOn(txtTitle2);

                                    YoYo.with(Techniques.FadeOut)
                                            .duration(300)
                                            .repeat(0)
                                            .playOn(imgBack);
                                    positioning = 0;
                                }
                            });

                        }

                    }
                });
                thread.start();

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RoomDetailActivity.this, BookmarkFoodsActivity.class));
        finish();
        super.onBackPressed();
    }
}
