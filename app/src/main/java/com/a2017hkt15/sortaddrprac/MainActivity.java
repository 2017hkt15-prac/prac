package com.a2017hkt15.sortaddrprac;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button goInputButton = (Button)findViewById(R.id.Button_input);
        goInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Runtime Permission 관련
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                                MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_LOCATION);
                    }
                    else {
                        goToNextActivity();
                    }
                }
                else {
                    //Input Activity 실행
                    goToNextActivity();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_NETWORK_LOCATION:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goToNextActivity();
                } else {
                    new AlertDialog.Builder(this).setTitle("경고")
                            .setMessage("내 위치정보를 사용할 수 없습니다.")
                            .setNeutralButton("닫기",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            goToNextActivity();
                                        }
                                    }).show();
                }
        }
    }

    private void goToNextActivity() {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }
}
