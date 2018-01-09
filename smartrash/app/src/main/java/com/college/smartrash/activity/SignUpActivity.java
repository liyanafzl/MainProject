package com.college.smartrash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.college.smartrash.Model.SignModel;
import com.college.smartrash.constant.Appconst;
import com.college.smartrash.listener.listener.UiUpdateListener;
import com.college.smartrash.response.BasicResponse;
import com.college.smartrash.service.SignManageService;

/**
 * Created by Faizal on 3/19/2017.
 */
public class SignUpActivity extends AppCompatActivity implements Appconst{

    private EditText signup_name;
    private EditText signup_mob;
    private EditText signup_loctype;
    private EditText signup_ad1;
    private EditText signup_ad2;
    private EditText signup_ad3;
    private EditText signup_city;
    private EditText signup_pin;
    private EditText signup_email;
    private EditText signup_pass;
    private EditText signup_confirm;

    private Button signup_button;

  private ImageView header_back;
     ImageView header_back_noti;

    String str_name;
    String str_mob;
    String str_loctype;
    String str_ad1;
    String str_ad2;
    String str_ad3;
    String str_city;
    String str_pin;
    String str_email;
    String str_pass;
    String str_confirm;
    String str_addr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_name = (EditText) findViewById(R.id.signup_name);
        signup_mob=(EditText)findViewById(R.id.signup_mob);
        signup_loctype=(EditText)findViewById(R.id.signup_loctype);
        signup_ad1= (EditText) findViewById(R.id.signup_ad1);
        signup_ad2=(EditText)findViewById(R.id.signup_ad2);
        signup_ad3=(EditText)findViewById(R.id.signup_ad3);
        signup_city=(EditText)findViewById(R.id.signup_city);
        signup_pin=(EditText)findViewById(R.id.signup_pin);

        signup_email=(EditText)findViewById(R.id.signup_email);
        signup_pass=(EditText)findViewById(R.id.signup_pass);
        signup_confirm=(EditText)findViewById(R.id.signup_confirm);

        signup_button=(Button)findViewById(R.id.signup_button);

        header_back_noti=(ImageView)findViewById(R.id.header_back_noti);
        header_back_noti.setVisibility(View.GONE);



        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name = signup_name.getText().toString();
                str_mob = signup_mob.getText().toString();
                str_loctype=signup_loctype.getText().toString();
                str_ad1 = signup_ad1.getText().toString();
                str_ad2 = signup_ad2.getText().toString();
                str_ad3 = signup_ad3.getText().toString();
                str_city = signup_city.getText().toString();
                str_pin = signup_pin.getText().toString();
                str_email = signup_email.getText().toString();
                str_pass = signup_pass.getText().toString();
                str_confirm = signup_confirm.getText().toString();
                str_addr=str_ad1+str_ad2+str_ad3+str_city+str_pin;
                if(!(str_pass.equals(str_confirm)))
                {
                    Toast.makeText(SignUpActivity.this, "Password doesn't match ", Toast.LENGTH_SHORT).show();
                }
                SignModel sign=new SignModel();
                sign.setUser_name(str_name);
                sign.setUser_password(str_pass);
                sign.setUser_address(str_addr);
                sign.setUser_mobile(str_mob);
                sign.setUser_email(str_email);
                sign.setUser_loctype(str_loctype);


                new SignManageService(SignUpActivity.this,REST_SERVICE_Signup,sign,uiLogin);


            }
        });
        header_back=(ImageView)findViewById(R.id.header_back);

        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
            }

        });
    }

    private UiUpdateListener uiLogin = new UiUpdateListener() {

        @Override
        public void onSuccess(BasicResponse res) {

            try {

                if (res.errorCode == CODE_COMMUNICATION_SUCCESS) {

                    Intent j=new Intent(SignUpActivity.this,HomeActivity.class);
                    startActivity(j);

                }

            } catch (Exception e) {

            }

        } ;

        @Override
        public void onError(BasicResponse res) {


            Toast.makeText(getApplicationContext(), res.getErrorMessage(),
                    Toast.LENGTH_LONG).show();


        }
    };
}





