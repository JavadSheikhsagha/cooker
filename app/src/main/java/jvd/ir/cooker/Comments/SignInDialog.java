package jvd.ir.cooker.Comments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import jvd.ir.cooker.LoginActivity.LoginActivity;
import jvd.ir.cooker.R;

public class SignInDialog extends DialogFragment {

    Button btnSignIn;

    View view;

    setOnLoginClick onLoginClick;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in_dialog,container,false);
        setupViews();
        return view;
    }

    private void setupViews() {

        btnSignIn = view.findViewById(R.id.btn_signInDialog_signIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick.onClick();
            }
        });
    }

    public void setOnLoginClick(setOnLoginClick onLoginClick) {
        this.onLoginClick = onLoginClick;
    }

    public interface setOnLoginClick{
        void onClick();
    }
}
