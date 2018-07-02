package com.fpt.edu.demoreadwritefile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    public static final String MY_FOLDER = "/phongdv/images";


    public static boolean writeImageToSD(Bitmap image, String fileName, Context context) throws IOException {
        // Get full path of folder
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER;
        Log.i("WriteSD", fullPath);
        File dirs = new File(fullPath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        File myFile = new File(fullPath, fileName);
        if (!myFile.exists()) {
            myFile.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(myFile);
        image.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
        return true;
    }


    public static boolean writeImageToInternalMemory(Bitmap image, String name, Context context) throws IOException {

        FileOutputStream fos = context.openFileOutput(name, Context.MODE_PRIVATE);
        image.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
        return true;


    }

    public static boolean isSDReady() {
        boolean isSDCardReady = false;
        String sdCardStage = Environment.getExternalStorageState();
        if (sdCardStage.equals(Environment.MEDIA_MOUNTED) || sdCardStage.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }
        return false;
    }


    public static Bitmap readFile(String fileName, Context context) throws FileNotFoundException {
        if (!isSDReady()) {
            return null;
        }
        Bitmap img = null;
        try {
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER + "/"+fileName;
            img = BitmapFactory.decodeFile(fullPath);
            return img;
        } catch (Exception e) {
            Log.e("demoReadWriteFile","Can't not read file form SD card");
        }
        try{
        File imageFile = context.getFileStreamPath(fileName);
        FileInputStream s = new FileInputStream(imageFile);
        img= BitmapFactory.decodeStream(s);
        //read image from SD
        return img;
        }catch (Exception e){
            return  null;

        }

    }

    public static boolean deleteImage(String fileName, Context context){

        context.deleteFile(fileName);
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER + "/test.png";
        File file = new File(fullPath);
        file.delete();
        return  true;

    }




}
