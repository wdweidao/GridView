package com.example.lenovo.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView image1;
    private Button button;
    private static final int REQUSER_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 = (ImageView)findViewById(R.id.image1);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,secondActivity.class);
                startActivityForResult(intent,REQUSER_CODE);   //需要传intent，requestcode
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUSER_CODE && resultCode==Activity.RESULT_OK){
            int imageId = data.getIntExtra("imageID",R.drawable.ic_launcher_background);
            Log.i("resultIimageId---->", String.valueOf(imageId));
            image1.setImageResource(imageId);
            Log.i("fasdf","fasdfasd");
        }
    }
}
