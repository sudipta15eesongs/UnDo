package in.bichuti.com.undo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Moumita on 30-05-2017.
 */

public class FileRW {

    public static void filewrite(String filename,String txtmsg,Context context){

        try {
            FileOutputStream fileout = context.openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(txtmsg);
            outputWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public  static List<String> fileread(String filename,Context context) {


        File file = context.getFileStreamPath(filename);
        String Finalline = "";
       List<String> readdata=new ArrayList<String>();

        if (file.exists()) {
            try {
                FileInputStream fis = context.openFileInput(filename);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bf = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
                Finalline = sb.toString();
            } catch (Exception e) {
            }
            ;
            String [] dtr=Finalline.split(",");
            readdata=(Arrays.asList(dtr));

        }
        else{readdata.add("None");};

        return readdata;

    }
}
