package test.bwie.com.jinrtt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private ImageView gd_login_back;
    private Button gd_btn_phonelogin;
    private Button gd_btn_zhucenew;
    private TextView gd_textno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gengduo_login);
        initView();


    }

    private void initView() {
        gd_login_back = (ImageView) findViewById(R.id.gd_login_back);
        gd_login_back.setOnClickListener(this);
        gd_btn_phonelogin = (Button) findViewById(R.id.gd_btn_phonelogin);
        gd_btn_phonelogin.setOnClickListener(this);
        gd_btn_zhucenew = (Button) findViewById(R.id.gd_btn_zhucenew);
        gd_btn_zhucenew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gd_btn_phonelogin:
                Intent intent = new Intent(this, PhoneLogin.class);
                startActivity(intent);
                break;
            case R.id.gd_btn_zhucenew:

                break;
        }
    }
}
