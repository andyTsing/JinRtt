package test.bwie.com.jinrtt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Regist extends AppCompatActivity implements View.OnClickListener {

    private ImageView login_back;
    private EditText phone_sr;
    private RadioButton rb_agree;
    private RadioButton rb_disagree;
    private RadioGroup rg;
    private TextView agree;
    private Button login_next;
    private Handler handler;


    private TextView login_text;

    //回调函数
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                handler.sendEmptyMessage(1);
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    handler.sendEmptyMessage(2);

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    handler.sendEmptyMessage(3);

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    handler.sendEmptyMessage(4);
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();

        SMSSDK.initSDK(Regist.this, "1cfddf7a60648", "5c65efbb1f48005670ce060a57d9648e");//初始化
        SMSSDK.registerEventHandler(eh);



    }

    private void initView() {
        login_back = (ImageView) findViewById(R.id.login_back);
        phone_sr = (EditText) findViewById(R.id.phone_sr);
        rb_agree = (RadioButton) findViewById(R.id.rb_agree);
        rb_disagree = (RadioButton) findViewById(R.id.rb_disagree);
        rg = (RadioGroup) findViewById(R.id.rg);
        agree = (TextView) findViewById(R.id.agree);
        login_next = (Button) findViewById(R.id.login_next);
        login_text = (TextView) findViewById(R.id.login_text);
        login_next.setOnClickListener(Regist.this);
    }
  private void checkphone(){
      handler = new Handler() {
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             if (msg.what == 1)
                 Toast.makeText(Regist.this, "回调完成", Toast.LENGTH_SHORT).show();
             else if (msg.what == 2)
                 Toast.makeText(Regist.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
             else if (msg.what == 3)
                 Toast.makeText(Regist.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
             else if (msg.what == 4)
                 Toast.makeText(Regist.this, "返回支持发送国家验证码", Toast.LENGTH_SHORT).show();
         }
     };




        boolean isChecked = rb_agree.isChecked();
        //如果手机号为空而且协议未选中的话 就吐司请输入手机号
        if (TextUtils.isEmpty(phone_sr.getText().toString().trim()) && isChecked==false){

            Toast.makeText(Regist.this, "请输入手机号", Toast.LENGTH_SHORT).show();

            //如果手机号为空而且协议已选中的话 也吐司请输入手机号
        }else if (TextUtils.isEmpty(phone_sr.getText().toString().trim()) && isChecked){

            Toast.makeText(Regist.this, "请输入手机号", Toast.LENGTH_SHORT).show();

            //如果手机号不是11位数的话 就吐司手机号应为11位数字
        }else if (phone_sr.getText().toString().trim().length()!=11){

            Toast.makeText(Regist.this, "手机号应为11位数字", Toast.LENGTH_SHORT).show();

            //如果手机号不为空而且协议未选中的话 就吐司请同意《今日头条用户协议》
        }else if (!TextUtils.isEmpty(phone_sr.getText().toString().trim()) && isChecked==false){

            Toast.makeText(Regist.this, "请同意《今日头条用户协议》", Toast.LENGTH_SHORT).show();

            //如果手机号不为空而且协议已选中的话 就吐司进入短信验证码页面
        }else if (!TextUtils.isEmpty(phone_sr.getText().toString().trim()) && isChecked){
            SMSSDK.getVerificationCode("86", String.valueOf(phone_sr.getText()));//请求获取短信验证码

            Intent intent = new Intent(Regist.this, SMSCheck.class);
            intent.putExtra("phoneNumber",String.valueOf(phone_sr.getText()));
            startActivity(intent);


    }


    }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_next:
                    checkphone();

                    break;
                case R.id.login_back:
                    finish();
                    break;
                case R.id.agree:
                    break;
                case R.id.phone_sr:
                    break;
                case R.id.login_text:
                    break;
            }
    }
}
