package com.koohpar.oghli.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by behnaz on 10/29/2017.
 */
public interface AppConstants {
    //10.1.7.31:8280
//    String BASE_URL = "https://service.sepehrnet.com:8243";
    String BASE_URL = "http://91.98.51.254:8004/CWService/";
    //188.75.127.132:8243

    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String PREF_NAME = "noor_pref";
    String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    boolean LOGTRUE = true;
    String REQUEST_OOGHLI = "12345678-1234-4321-8765-ba9876543210";


    ////
    int MY_PERMISSIONS_REQUEST_CAMERA = 101;
    int MY_PERMISSIONS_REQUEST_LOCATION = 102;
    // Headers
    String HEADER_SECURITY_KEY = "SignData";
    String HEADER_TOKEN = "Token";
    String HEADER_INVOICE_NUMBER = "InvoiceNumber";

}

