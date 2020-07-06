package jvd.ir.cooker.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.cooker.Comments.ActivityComments;
import jvd.ir.cooker.Comments.SignInDialog;
import jvd.ir.cooker.MainActivity.MainRvAdapter;
import jvd.ir.cooker.MainActivity.MainSliderAdapter;
import jvd.ir.cooker.Model.FoodHowTo;
import jvd.ir.cooker.Model.FoodIngredients;
import jvd.ir.cooker.Model.FoodModel;
import jvd.ir.cooker.Model.ImageModel;
import jvd.ir.cooker.Model.MessageModel;
import jvd.ir.cooker.R;
import jvd.ir.cooker.RoomDatabase.BuyList.BuyListAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodHowTo.HowToAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodHowTo.HowToModel2;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsModel2;
import jvd.ir.cooker.RoomDatabase.FoodMain.AppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodMain.FoodModel2;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle1, txtTitle2, txtUser, txtLike, txtTime, txtCategory, txtPeople, txtDifficulty, txtBookmark;

    ImageView imgBack, imgLike, imgDifficulty, imgTime, imgPeople, imgHelpDifficulty, imgHelpTime, imgHelpPeople, imgBookmark, imgHelpBookmark;

    Button btnComments, btnShareFood;

    NestedScrollView scrollView;

    RecyclerView rvIngredients, rvHowTo, rvOther;

    RelativeLayout relDetailSplash;

    SpinKitView spinKitView;

    DetailViewModel viewModel;

    ss.com.bannerslider.Slider slider;

    String FOOD_ID, FOOD_IMG, FOOD_NAME, USER_NAME, CHEF_ID;

    int positioning, image, saved = 0;

    List<FoodIngredients> foodies;

    List<FoodHowTo> foodHowies;

    FoodIngredients foodIngredients2 = new FoodIngredients();

    int PEOPLE = 3;
    // TODO: 4/29/2020 PEOPLE NUMBER

    String LIKED = "0";

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FOOD_ID = getIntent().getStringExtra("food_id");
        FOOD_IMG = getIntent().getStringExtra("food_img");

        setupViews();

        USER_NAME = viewModel.getUserName();

        getFoodImage();

        getIfFoodLiked();

        getFoodDetail();

        getFoodIngredients();

        getFoodInstruction();

        getOtherFood();

        shareFood();

        getIfSaved();

    }

    private void getIfSaved() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<FoodModel2> foodModel2 = new ArrayList<>();
                foodModel2 = AppDatabase.getInstance(getApplicationContext()).foodies().getAll();
                for (int i = 0; i < foodModel2.size(); i++) {
                    String food_id = foodModel2.get(i).getFoodId();
                    if (food_id.equals(FOOD_ID)) {
                        imgBookmark.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_bookmark_black_24dp));
                        saved = 1;
                    }
                }
            }
        });
        thread.start();
    }

    private void shareFood() {

        btnShareFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleShare = txtTitle1.getText().toString();
                String peopleShare = txtPeople.getText().toString();
                String peoples = "برای یک نفر";
                String difficultyShare = txtDifficulty.getText().toString();
                String timeShare = txtTime.getText().toString();

                String ingredient = "";
                List<String> ings = new ArrayList<>();

                for (int i = 0; i < foodies.size(); i++) {
                    ingredient = foodies.get(i).getIngTitle()
                            + " :  " + foodies.get(i).getAmount()
                            + "  " + foodies.get(i).getUnit();
                    ings.add(ingredient);
                }

                String foodHow = "";
                List<String> foodHows = new ArrayList<>();

                for (int i = 0; i < foodHowies.size(); i++) {
                    String warn = "";
                    if (!foodHowies.get(i).getWarn().equals("")) {
                        warn = " هشدار : " + foodHowies.get(i).getWarn()+"\n";
                    }
                    foodHow = foodHowies.get(i).getTodo()
                            + " \n " + "\t" + warn;
                    int position = i + 1;
                    foodHows.add(position + "_ " + foodHow);
                }

                String delim = "\n";

                String App = "کوکر";
                // TODO: 5/9/2020 APP NAME AND ADDRESS


                String endShare = "\n" +
                        "نام غذا : " +
                        titleShare +
                        "\n" + peoples +
                        "\n" + difficultyShare +
                        "\n" + " زمان مورد نیاز : " + timeShare +
                        "\n\n" +
                        "\n" + " مواد لازم : " + "\n" +
                        TextUtils.join(delim, ings) +
                        "\n\n" +
                        "\n" + " دستور پخت : " + "\n" +
                        TextUtils.join(delim, foodHows);


                Log.i("LOG11", "onClick: " + endShare);
                //Toast.makeText(DetailActivity.this, endShare, Toast.LENGTH_SHORT).show();
                String shareBody = endShare;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "عنوان");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));

            }
        });
    }

    private void getIfFoodLiked() {

        viewModel.getIfFoodLiked(FOOD_ID, USER_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        if (s.equals("0")) {
                            LIKED = "0";
                        }
                        if (s.equals("1")) {
                            LIKED = "1";
                            imgLike.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_like_black_24dp));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG6", "onError: " + e.toString());
                    }
                });
    }

    private void getOtherFood() {

        viewModel.getOtherFood()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {
                        MainRvAdapter adapter = new MainRvAdapter(DetailActivity.this, foodModels, new MainRvAdapter.setOnFoodClick() {
                            @Override
                            public void onClick(FoodModel foodModel) {
                                Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
                                intent.putExtra("food_id", foodModel.getId());
                                intent.putExtra("food_img", foodModel.getImage());
                                startActivity(intent);
                            }
                        });

                        rvOther.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getFoodInstruction() {

        viewModel.getFoodInstruction(FOOD_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodHowTo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodHowTo> foodHowTos) {
                        foodHowies = foodHowTos;
                        InstructionRvAdapter adapter = new InstructionRvAdapter(DetailActivity.this, foodHowTos);
                        rvHowTo.setAdapter(adapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getFoodImage() {

        viewModel.getImage(FOOD_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ImageModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ImageModel> imageModels) {
                        MainSliderAdapter adapter = new MainSliderAdapter(imageModels);

                            slider.setAdapter(adapter);
                            slider.setSelectedSlide(imageModels.size());

                    }

                    @Override
                    public void onError(Throwable e) {
                        List<ImageModel> imgs = new ArrayList<>();
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImage(FOOD_IMG);
                        imgs.add(imageModel);
                        slider.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn)
                                .duration(700)
                                .playOn(slider);
                        MainSliderAdapter adapter1 = new MainSliderAdapter(imgs);
                        slider.setAdapter(adapter1);
                    }
                });
    }

    private void getFoodDetail() {

        viewModel.getFoodDetail(FOOD_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodModel> foodModels) {

                        txtTitle1.setText(foodModels.get(0).getTitle());
                        txtTitle2.setText(foodModels.get(0).getTitle());
                        txtCategory.setText(foodModels.get(0).getCategory());
                        txtLike.setText(foodModels.get(0).getLikes());
                        txtUser.setText(foodModels.get(0).getCheefId());
                        txtTime.setText(foodModels.get(0).getmTime() + " دقیقه ");
                        FOOD_NAME = foodModels.get(0).getTitle();
                        CHEF_ID = foodModels.get(0).getCheefId();

                        int timeSpent = Integer.parseInt(foodModels.get(0).getmTime());

                        if (timeSpent < 31) {
                            imgTime.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorGreenLight));
                        } else if (timeSpent > 30 && timeSpent < 61) {
                            imgTime.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorGreenDark));
                        } else if (timeSpent > 60 && timeSpent < 91) {
                            imgTime.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorPrimary));
                        } else if (timeSpent > 90 && timeSpent < 121) {
                            imgTime.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorPrimaryDark));
                        } else if (timeSpent > 120) {
                            imgTime.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorRed));
                        }

                        int difficulty = Integer.parseInt(foodModels.get(0).getmDifficulty());

                        if (difficulty == 1) {
                            txtDifficulty.setText(" ساده");
                            imgDifficulty.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorGreenLight));
                        }
                        if (difficulty == 2) {
                            txtDifficulty.setText("آسان ");
                            imgDifficulty.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorGreenDark));
                        }
                        if (difficulty == 3) {
                            txtDifficulty.setText(" معمولی");
                            imgDifficulty.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorPrimary));
                        }
                        if (difficulty == 4) {
                            txtDifficulty.setText(" سخت");
                            imgDifficulty.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorRed));
                        }
                        if (difficulty == 5) {
                            txtDifficulty.setText(" سخت");
                            imgDifficulty.setColorFilter(ContextCompat.getColor(DetailActivity.this, R.color.colorRed));
                        }

                        YoYo.with(Techniques.FadeOut)
                                .delay(700)
                                .duration(700)
                                .playOn(relDetailSplash);
                        YoYo.with(Techniques.FadeOut)
                                .delay(900)
                                .duration(700)
                                .playOn(spinKitView);

                        scrollView.setNestedScrollingEnabled(true);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(DetailActivity.this, "مشکلی در ارتباط رخ داده است.", Toast.LENGTH_SHORT).show();
                        scrollView.setNestedScrollingEnabled(false);
                    }
                });
    }

    private void getFoodIngredients() {

        viewModel.getIng(FOOD_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FoodIngredients>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<FoodIngredients> foodIngredients) {
                        foodies = foodIngredients;
                        foodIngredients2 = foodIngredients.get(0);
                        IngredientsAdapter adapter = new IngredientsAdapter(DetailActivity.this, foodIngredients, 1);
                        rvIngredients.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {

        viewModel = new DetailViewModel(this);

        scrollView = findViewById(R.id.scroll_detail_nested);
        scrollView.setNestedScrollingEnabled(false);

        slider = findViewById(R.id.slider_detail);
        slider.setVisibility(View.INVISIBLE);

        relDetailSplash = findViewById(R.id.rel_detailSplash);
        spinKitView = findViewById(R.id.spin_detailSplash_loading);

        txtTitle1 = findViewById(R.id.txt_detail_title1);
        txtTitle2 = findViewById(R.id.txt_detail_title2);
        txtCategory = findViewById(R.id.txt_detail_category);
        txtUser = findViewById(R.id.txt_detail_user);
        txtTime = findViewById(R.id.txt_detail_time);
        txtLike = findViewById(R.id.txt_detail_like);
        txtPeople = findViewById(R.id.txt_detail_people);
        txtDifficulty = findViewById(R.id.txt_detail_difficulty);
        txtBookmark = findViewById(R.id.txt_detail_bookmark);

        imgBack = findViewById(R.id.img_detail_back);
        imgTime = findViewById(R.id.img_detain_time);
        imgLike = findViewById(R.id.img_detail_like);
        imgDifficulty = findViewById(R.id.img_detail_hardness);
        imgPeople = findViewById(R.id.img_detail_people);
        imgHelpDifficulty = findViewById(R.id.img_detail_helpDifficulty);
        imgHelpTime = findViewById(R.id.img_detail_helpTime);
        imgBookmark = findViewById(R.id.img_detail_bookmark);
        imgHelpBookmark = findViewById(R.id.img_detail_helpBookmark);

        btnComments = findViewById(R.id.btn_detail_comments);
        btnShareFood = findViewById(R.id.btn_detail_startCooking);

        rvIngredients = findViewById(R.id.rv_detail_ing);
        rvHowTo = findViewById(R.id.rv_detail_howTo);
        rvOther = findViewById(R.id.rv_detail_others);

        txtTitle2.setVisibility(View.GONE);
        imgBack.setVisibility(View.GONE);
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
                finish();
            }
        });

        rvIngredients.setLayoutManager(new LinearLayoutManager(this));
        rvHowTo.setLayoutManager(new LinearLayoutManager(this));
        rvOther.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        rvOther.setFocusable(false);
        rvHowTo.setFocusable(false);
        rvIngredients.setFocusable(false);

        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ActivityComments.class);
                intent.putExtra("food_id", FOOD_ID);
                intent.putExtra("food_name", FOOD_NAME);
                startActivity(intent);
            }
        });

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

        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!USER_NAME.equals("")){
                    if (LIKED.equals("0")) {
                        viewModel.likeFood(FOOD_ID, USER_NAME)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<MessageModel>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        compositeDisposable.add(d);
                                    }

                                    @Override
                                    public void onSuccess(MessageModel messageModel) {
                                        int likes = Integer.parseInt(txtLike.getText().toString());
                                        likes++;
                                        txtLike.setText(likes + "");
                                        YoYo.with(Techniques.Shake)
                                                .repeat(0)
                                                .duration(500)
                                                .playOn(txtLike);
                                        YoYo.with(Techniques.Shake)
                                                .repeat(0)
                                                .duration(500)
                                                .playOn(imgLike);
                                        imgLike.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_like_black_24dp));
                                        LIKED = "1";
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("LOG8", "onError: " + e.toString());
                                    }
                                });
                    }
                } else {
                    SignInDialog dialog = new SignInDialog();
                    dialog.show(getSupportFragmentManager(),null);
                }


            }
        });


        imgBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saved == 0) {
                    addToBookmark();
                    Toast.makeText(DetailActivity.this, "به ذخیره شده ها اضافه شد.", Toast.LENGTH_SHORT).show();
                    imgBookmark.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_bookmark_black_24dp));
                    saved = 1;
                }
                if (saved == 0) {
                    Toast.makeText(DetailActivity.this, "قبلا این غذا را به ذخیره شده ها اضافه کرده بودید.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        imgHelpBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpBookmarkDialog dialog = new HelpBookmarkDialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });

    }

    private void addToBuyList() {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < foodies.size(); i++) {

                    IngsModel2 model21 = new IngsModel2();

                    model21.setFoodId(FOOD_ID);
                    model21.setUnit(foodies.get(i).getUnit());
                    model21.setTitle(foodies.get(i).getIngTitle());
                    model21.setAmount(foodies.get(i).getAmount());

                    BuyListAppDatabase.getInstance(getApplicationContext()).BuyList().insertAll(model21);
                }
            }
        });

        thread1.start();
    }

    private void addToBookmark() {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                final FoodModel2 model2 = new FoodModel2();

                model2.setTitle(txtTitle1.getText().toString());
                model2.setTime(txtTime.getText().toString());
                model2.setCategory(txtCategory.getText().toString());
                model2.setChefId(CHEF_ID);
                model2.setLikes(txtLike.getText().toString());
                model2.setImage(FOOD_IMG);
                model2.setFoodId(FOOD_ID);
                model2.setDifficulty(txtDifficulty.getText().toString());

                AppDatabase.getInstance(getApplicationContext()).foodies().insertAll(model2);


                for (int i = 0; i < foodies.size(); i++) {
                    IngsModel2 model21 = new IngsModel2();

                    model21.setFoodId(FOOD_ID);
                    model21.setAmount(foodies.get(i).getAmount());
                    model21.setTitle(foodies.get(i).getIngTitle());
                    model21.setUnit(foodies.get(i).getUnit());

                    IngsAppDatabase.getInstance(getApplicationContext()).ings().insertAll(model21);

                }

                for (int i = 0; i < foodHowies.size(); i++) {
                    HowToModel2 model212 = new HowToModel2();

                    model212.setFoodId(FOOD_ID);
                    model212.setWarn(foodHowies.get(i).getWarn());
                    model212.setTodo(foodHowies.get(i).getTodo());

                    HowToAppDatabase.getInstance(getApplicationContext()).howToDao().insertAll(model212);
                }


            }
        });
        thread1.start();
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
                                    imgBack.setClickable(true);

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
                                    imgBack.setClickable(false);

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
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

}
