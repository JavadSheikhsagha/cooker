package jvd.ir.cooker.DrawerMenuItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import jvd.ir.cooker.R;
import jvd.ir.cooker.RoomDatabase.BuyList.BuyListAppDatabase;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsModel2;
import jvd.ir.cooker.RoomDatabase.FoodIngredients.IngsRvAdapter;

public class BuyListActivity extends AppCompatActivity {

    ImageView imgBack;

    Button btnShare, btnDelete;

    RecyclerView recyclerView;

    List<IngsModel2> ingies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        setupViews();

        getBuyList();
    }

    private void getBuyList() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<IngsModel2> ingsModel2s;
                ingsModel2s = BuyListAppDatabase.getInstance(getApplicationContext()).BuyList().getAll();
                ingies = ingsModel2s;
                IngsRvAdapter adapter = new IngsRvAdapter(getApplicationContext(), ingsModel2s, 3);
                recyclerView.setAdapter(adapter);
            }
        });
        thread.start();
    }

    private void setupViews() {

        imgBack = findViewById(R.id.img_buyList_back);

        btnDelete = findViewById(R.id.btn_buyList_delete);
        btnShare = findViewById(R.id.btn_buyList_share);

        recyclerView = findViewById(R.id.rv_buyList_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setFocusable(false);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromBuyList();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareBuyList();
            }
        });
    }

    private void shareBuyList() {
        String title = " لیست خرید ";
        List<String> ings = new ArrayList<>();
        for (int i = 0; i < ingies.size(); i++) {
            String ingredient = ingies.get(i).getTitle() +
                    "  " + ingies.get(i).getAmount() +
                    "  " + ingies.get(i).getUnit();
            ings.add(ingredient);
        }

        String delim = "\n";

        String appName = "";
        String Share = title + "\n\n" +
                TextUtils.join(delim, ings) +
                "\n" +
                appName;

        String shareBody = Share;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "عنوان");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));

    }

    private void deleteFromBuyList() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BuyListAppDatabase.getInstance(getApplicationContext()).BuyList().deleteAll();
                finish();
            }
        });
        thread.start();
    }
}
