package com.yp.paparazzilive.ui.secondactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yp.paparazzilive.R;

public class LoginActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView mLeftImage;
    private ImageView mRightImage;
    private EditText mUserName;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        mLeftImage = (ImageView) findViewById(R.id.left_image);
        mRightImage = (ImageView) findViewById(R.id.right_image);
        mUserName = (EditText) findViewById(R.id.login_et_username);
        mPassword = (EditText) findViewById(R.id.login_et_password);

        mUserName.setOnTouchListener(this);
        mPassword.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.login_et_password:
                mLeftImage.setImageResource(R.mipmap.ic_22_hide);
                mRightImage.setImageResource(R.mipmap.ic_33_hide);
                break;
            case R.id.login_et_username:
                mLeftImage.setImageResource(R.mipmap.ic_22);
                mRightImage.setImageResource(R.mipmap.ic_33);
                break;
        }

        return false;
    }
}
