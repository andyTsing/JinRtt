package test.bwie.com.jinrtt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PhoneLogin extends AppCompatActivity implements View.OnClickListener {

    private ImageView login_bac;
    private EditText ed_phone;
    private EditText ed_pwd;
    private TextView regist_num;
    private TextView forget_pwd;
    private Button phone_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();


    }

    private void initView() {
        login_bac = (ImageView) findViewById(R.id.login_bac);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        regist_num = (TextView) findViewById(R.id.regist_num);
        forget_pwd = (TextView) findViewById(R.id.forget_pwd);
        phone_login = (Button) findViewById(R.id.phone_login);

        login_bac.setOnClickListener(this);
        phone_login.setOnClickListener(this);
        forget_pwd.setOnClickListener(this);
        regist_num.setOnClickListener(this);
        ed_pwd.setOnClickListener(this);
        ed_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_login:
                finish();
                break;
            case R.id.regist_num:
                Intent intent = new Intent(PhoneLogin.this, Regist.class);
                startActivity(intent);
                break;
        }
    }



}
