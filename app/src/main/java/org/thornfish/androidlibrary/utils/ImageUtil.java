package org.thornfish.androidlibrary.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/27.
 */

public class ImageUtil {



    public static String saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "/temp/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "netpic" + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appDir.getPath()+"/"+fileName;
    }

}
