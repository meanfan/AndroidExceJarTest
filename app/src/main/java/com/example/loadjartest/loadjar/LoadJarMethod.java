package com.example.loadjartest.loadjar;

import android.content.Context;
import android.os.FileUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;

import dalvik.system.DexClassLoader;

public class LoadJarMethod {
    public static final String TAG = "LoadJarMethod";
    public static final String JAR_FILENAME = "remote.jar";
    public static final String CLASS_NAME = "com.example.ctrlme.remote.A";
    public static final String METHOD_NAME = "acquire";

    public static String acquire(Context context) {
        try {
            File targetFile = copyJar(context,JAR_FILENAME,JAR_FILENAME);
            DexClassLoader dexClassLoader = new DexClassLoader(targetFile.getAbsolutePath(),context.getDir("dex",Context.MODE_PRIVATE).getAbsolutePath(),null,context.getClassLoader());
            Class<?> aClazz = dexClassLoader.loadClass(CLASS_NAME);
            Method method = aClazz.getMethod("acquire", String.class);
            method.setAccessible(true);
            Object retObj = method.invoke(aClazz.newInstance(),"Hello");
            if(retObj instanceof String){
                return (String)retObj;
            }else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    // dx --dex --output=./remote_dex.jar  ./remote.jar
    private static File copyJar(Context context, String srcAssetFileName, String targetFileName) throws IOException {
        InputStream inputStream = context.getAssets().open(srcAssetFileName);
        String targetFilePath = context.getFilesDir() +File.separator +targetFileName;
        File file = new File(targetFilePath);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int byteCount;
        while ((byteCount = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, byteCount);
        }
        fos.flush();
        inputStream.close();
        fos.close();
        return file;
    }
}
