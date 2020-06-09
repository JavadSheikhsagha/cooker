package jvd.ir.cooker.DetailActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.gson.internal.$Gson$Preconditions;

import jvd.ir.cooker.R;

public class HelpDifficultyDialog extends DialogFragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.img_difficulty_help_dialog, container, false);

        setupViews();

        return view;
    }

    private void setupViews() {

        Button btnClose = view.findViewById(R.id.btn_helpDifficulty_close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
