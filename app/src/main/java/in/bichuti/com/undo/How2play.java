package in.bichuti.com.undo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class How2play extends AppCompatActivity {
    String lstatus;
    ImageView mv1;
    TextView mg1,mg2,mg3,mg4;

    RelativeLayout RL_F;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2play);
       RL_F=(RelativeLayout)findViewById(R.id.GO_how) ;
        mg1=(TextView)findViewById(R.id.M1);
        mg2=(TextView)findViewById(R.id.M2);
        mg3=(TextView)findViewById(R.id.M3);
        mg4=(TextView)findViewById(R.id.M4);
        String str1="1.You have to place all your pieces in a single line apart from the base line";
        String str2="2.There are two base lines and 9 points are available in the board.You have to place your pieces within these points";
        String str3="3.At a single chance, you can move one piece to one connecting point only. When you drag your piece, all the corresponding"+
                " empty points will turn into green";
        String str4="4. All the points are connected through stright lines. You can drop your piece into the point which turns into green only";
        mg1.setText(str1);
        mg1.setTextSize(15);
        mg2.setText(str2);
        mg2.setTextSize(15);
        mg3.setText(str3);
        mg3.setTextSize(15);
        mg4.setText(str4);
        mg4.setTextSize(15);



    }
    ;






    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
