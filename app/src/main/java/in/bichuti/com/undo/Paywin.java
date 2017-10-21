package in.bichuti.com.undo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Paywin extends AppCompatActivity {

    int pay1num,pay2num,ggcount;
    boolean yesno;
    ImageView V1;
    TextView T1,T2,T3,S1,S2;
    Button B1,B2;
    String pmsg,gamety;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playwin);
        V1=(ImageView)findViewById(R.id.Winpic);
        T1=(TextView) findViewById(R.id.Winmsg) ;
        T2=(TextView) findViewById(R.id.Msg);
        T3=(TextView) findViewById(R.id.Score) ;
        S1=(TextView) findViewById(R.id.Player1score);
        S2=(TextView) findViewById(R.id.Player2score);
        B1=(Button) findViewById(R.id.cont) ;
        B2=(Button) findViewById(R.id.NG) ;
        Drawable D1= ContextCompat.getDrawable(this,R.drawable.sab2);
        Drawable D2= ContextCompat.getDrawable(this,R.drawable.win2);
        Intent ii=getIntent();
        Bundle b=ii.getExtras();

        if(b!=null){
            pay1num=(int)b.get("comwincount");
            pay2num=(int)b.get("uwincount");
            yesno=(boolean)b.get("wincheck");
            ggcount=(int)b.get("globalcount");
             pmsg=b.getString("msg1");
            gamety=b.getString("Gametype");
            byte[] bm=b.getByteArray("pic1") ;
            Bitmap bmp = BitmapFactory.decodeByteArray(bm, 0, bm.length);
            ImageView Im=(ImageView)findViewById(R.id.Resultimage);
            Im.setImageBitmap(bmp);
            //String str1=getColoredSpanned("Score:","#FFFFFF");
        //    String str2=getColoredSpanned(Integer.toString(pay1num),"#FF0000" );
         //   String str3=getColoredSpanned("|","#FFFFFF");
         //   String str4=getColoredSpanned(Integer.toString(pay2num),"##0000FF" );
         //   String str=str1+str2+str3+str4;
          //  T3.setText(Html.fromHtml(str), TextView.BufferType.SPANNABLE);
            S1.setText(Integer.toString(pay1num));
            S1.setTextColor(Color.parseColor("#FF0000"));
            S2.setText(Integer.toString(pay2num));
            S2.setTextColor(Color.parseColor("#0000FF"));
        }
        String txtmsg=gamety+","+String.valueOf(pay1num)+","+String.valueOf(pay2num)+","+
                String.valueOf(ggcount);
        //System.out.println(txtmsg);
        try {
            FileOutputStream fileout = getBaseContext().openFileOutput("UnDo.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(txtmsg);
            outputWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }


         if(gamety.equals("Normal")&& ggcount==5){
            if( pay2num>pay1num){
                T1.setText("Congratulations!!!");
                T2.setText("Level Completed!!");
                T2.setTextSize(30);
                T2.setTextColor(Color.parseColor("#00FFFF"));

                V1.setImageDrawable(D2);
                String lvmsg="1";
                levelcomplete(lvmsg);
            }else{
                T1.setText("OOPS!!!");
                T2.setText("You have lost this game");
                T2.setTextSize(30);
                T2.setTextColor(Color.parseColor("#FF7F50"));



                V1.setImageDrawable(D1);
            }
            B1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it1=new Intent(Paywin.this,GameOptions.class);
                    startActivity(it1);
                    finish();
                }
            });
        }else if(gamety.equals("Time")&& ggcount==5){

            if( pay2num>pay1num){
                T1.setText("Congratulations!!!");
                T2.setText("Level Completed!");
                T2.setTextSize(30);
                T2.setTextColor(Color.parseColor("#00FFFF"));
                V1.setImageDrawable(D2);
                String lvmsg="12";
                levelcomplete(lvmsg);
            }else{
                T1.setText("OOPS!!!");
                T2.setText("You have lost this game");
                T2.setTextSize(30);
                T2.setTextColor(Color.parseColor("#FF7F50"));
                V1.setImageDrawable(D1);
            }
            B1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it1=new Intent(Paywin.this,GameOptions.class);
                    startActivity(it1);
                    finish();
                }
            });
        }else if(gamety.equals("Final")&& ggcount==3){
            if( pay2num>pay1num){
                T1.setText("Congratulations!!!");
                T2.setText("Level Completed!!");
                T2.setTextSize(30);
                T2.setTextColor(Color.parseColor("#00FFFF"));
                V1.setImageDrawable(D2);
                String lvmsg="123";
                levelcomplete(lvmsg);
            }else{
                T1.setText("OOPS!!!");
                T2.setText("You have lost this game");
                T2.setTextSize(30);
                T2.setTextColor(Color.parseColor("#FF7F50"));
                V1.setImageDrawable(D1);
            }
            B1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it1=new Intent(Paywin.this,GameOptions.class);
                    startActivity(it1);
                    finish();
                }
            });
        }else {
            if (yesno==false){

                if(pmsg.equals("Timeout")){
                    T1.setText("OOPS!!!TimeOut!!!");
                }else{
                    T1.setText("OOPS!!!");
                }
                V1.setImageDrawable(D1);

                T2.setText("You have lost this match");
                T2.setTextColor(Color.parseColor("#F6358A"));
            }else{

                V1.setImageDrawable(D2);
                T1.setText("Congratulations!!!");
                T2.setText("You have won this match");

            }
            B1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it1=new Intent(Paywin.this,GameActivity.class);
                    it1.putExtra("comwincount",pay1num);
                    it1.putExtra("uwincount",pay2num);
                    it1.putExtra("globalcount",ggcount);
                    it1.putExtra("Gametype",gamety);
                    startActivity(it1);
                    finish();
                    //System.out.println("Hi");
                }
            });
        }


        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1=new Intent(Paywin.this,GameOptions.class);
                String newgame="999";
                levelcomplete(newgame);
                startActivity(it1);
                finish();
            }
        });



    }
    public void levelcomplete(String lvlmsg){

        try {
            FileOutputStream fileout1 = openFileOutput("UnDoLevel.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter1 = new OutputStreamWriter(fileout1);
            outputWriter1.write(lvlmsg);
            outputWriter1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
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
