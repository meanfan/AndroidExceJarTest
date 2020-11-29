package com.example.loadjartest.remote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

import static android.content.Context.TELEPHONY_SERVICE;

public class A {
    public static void main(String[] args) {
        return;
    }
    public String acquire(String type){
        return "getType"+type;
    }

    @SuppressLint("MissingPermission")
    public String getIMEI(Context context){
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                imei = tm.getDeviceId();
            }else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                Method method = tm.getClass().getMethod("getImei");
                imei = (String) method.invoke(tm);
            }else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    public String getServerAddr(Context context){
        return decryptData(context,"192.168.137.1");
    }

    public int getServerPort(Context context){
        return Integer.parseInt(decryptData(context,"3847"));
    }
    public String decryptData(Context context, String str){

        //TODO Decrypt Data using native function
        return str;
    }
}
