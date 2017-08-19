package in.bichuti.com.undo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String themename;
    RelativeLayout Rl;
    Drawable dr1;
    List<String> readtheme=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rl=(RelativeLayout)findViewById(R.id.R1);
        dr1= ContextCompat.getDrawable(this,R.drawable.game2);
     Button b1 = (Button)findViewById(R.id.NewGame);
        Button b3 = (Button)findViewById(R.id.Continue);
        Button b4 = (Button)findViewById(R.id.Settings);

        readtheme=FileRW.fileread("Undotheme.txt",this.getBaseContext());
        String themevalue=readtheme.get(0);
        //System.out.println("cool");
        //System.out.println(themevalue);
        {
            if (themevalue.equals("one")) {
                Rl.setBackground(ContextCompat.getDrawable(this, R.drawable.dp1));

            }
            if (themevalue.equals("two")) {
                //System.out.println("cool");
                Rl.setBackground(ContextCompat.getDrawable(this, R.drawable.flback));

            }
            if (themevalue.equals("three")) {
                Rl.setBackground(ContextCompat.getDrawable(this, R.drawable.game2));

            }
        }

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(MainActivity.this, GameTheme.class);
                startActivity(activityChangeIntent);

            }
        });
     b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent activityChangeIntent = new Intent(MainActivity.this, GameOptions.class);
             startActivity(activityChangeIntent);
             deleteFile("UnDo.txt");
         }
     });

        Button b2=(Button)findViewById(R.id.Resume);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int requestCode = getIntent().getExtras().getInt("requestCode");
                if(requestCode==2){
                Intent intent=new Intent();

                setResult(2,intent);
                finish();}
                else
            {

                Toast.makeText(getApplicationContext(), "Nothing to resume",
                        Toast.LENGTH_LONG).show();
            }
            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=getBaseContext().getFileStreamPath("UnDo.txt");
                String Finalline="";

                if (file.exists()){
                    try {
                        FileInputStream fis= getBaseContext().openFileInput("UnDo.txt");
                        InputStreamReader isr=new InputStreamReader(fis);
                        BufferedReader bf=new BufferedReader(isr);
                        StringBuilder sb=new StringBuilder();
                        String line;
                        while((line=bf.readLine())!=null){
                            sb.append(line);
                        }
                        Finalline=sb.toString();
                    }catch (Exception e){};

                    String [] dtr=Finalline.split(",");
                    String gametype=dtr[0];
                    int comcount=Integer.valueOf(dtr[1]);
                    int youcount=Integer.valueOf(dtr[2]);
                    int gcount=Integer.valueOf(dtr[3]);
                    Intent activityChangeIntent = new Intent(MainActivity.this, GameActivity.class);
                    activityChangeIntent.putExtra("Gametype",gametype);
                    activityChangeIntent.putExtra("comwincount",comcount);
                    activityChangeIntent.putExtra("uwincount",youcount);
                    activityChangeIntent.putExtra("globalcount",gcount);
                    startActivity(activityChangeIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "Nothing to Continue",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
       //Theme decision

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