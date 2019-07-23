/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.koohpar.oghli.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koohpar.oghli.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;

public final class CommonUtils implements AppConstants {

    private static Dialog dialog;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String checkErrorCode(String code) {
        if (code.equalsIgnoreCase("0"))
            return "OK";
        else if (code.equalsIgnoreCase("1"))
            return "Header Is Not Valid";
        else if (code.equalsIgnoreCase("2"))
            return "Token Has No Permission";
        else if (code.equalsIgnoreCase("3"))
            return "SignData Is Not Valid";
        else if (code.equalsIgnoreCase("4"))
            return "Result Error";
        else if (code.equalsIgnoreCase("5"))
            return "InvoiceNumber Is not Valid";
        else if (code.equalsIgnoreCase("6"))
            return "ServiceWSException";
        else if (code.equalsIgnoreCase("7"))
            return "Your IP Is Not Trusted";
        else if (code.equalsIgnoreCase("8"))
            return "Your Encrypted Text Is Not Valid";
        else if (code.equalsIgnoreCase("10"))
            return "درخواست انجام نشد";
        else if (code.equalsIgnoreCase("00"))
            return "تراکنش با موفقیت به انجام رسیده است";
        else if (code.equalsIgnoreCase("01"))
            return "از انجام تراکنش صرفنظر شد";
        else if (code.equalsIgnoreCase("02"))
            return "تراکنش اصلی اصلاح شده است";
        else if (code.equalsIgnoreCase("03"))
            return "پذیرنده فروشگاهی نامعتبر است";
        else if (code.equalsIgnoreCase("04"))
            return "از انجام تراکنش صرفنظر شد.کارت توسط دستگاه ضبط شود";
        else if (code.equalsIgnoreCase("07"))
            return "به دلیل شرایط ویژه کارت توسط دستگاه ضبط می شود";
        else if (code.equalsIgnoreCase("09"))
            return "درخواست درحال انجام است";
        else if (code.equalsIgnoreCase("12"))
            return "تراکنش نامعتبر است";
        else if (code.equalsIgnoreCase("13"))
            return "مبلغ تراکنش اصلاحیه معتبر نمی باشد. ناهمخوانی با تراکنش اصلی";
        else if (code.equalsIgnoreCase("14"))
            return "اطلاعات کارت یافت نشد";
        else if (code.equalsIgnoreCase("19"))
            return "تراکنش دوباره فرستاده شود";
        else if (code.equalsIgnoreCase("23"))
            return "کارمزد ارسالی پذیرنده غیر قابل قبول است";
        else if (code.equalsIgnoreCase("25"))
            return "تراکنش اصلی یافت نشد";
        else if (code.equalsIgnoreCase("30"))
            return "فرمت پیام دارای اشکال است";
        else if (code.equalsIgnoreCase("31"))
            return "پذیرنده توسط سوئیچ پشتیبانی نمی شود";
        else if (code.equalsIgnoreCase("33"))
            return "کارت منقضی شده است";
        else if (code.equalsIgnoreCase("34"))
            return "تراکنش اصلی (در تراکنش اصلاحیه) موفق نبوده است";
        else if (code.equalsIgnoreCase("41"))
            return "کارت مفقود شده است. کارت توسط دستگاه ضبط شود";
        else if (code.equalsIgnoreCase("43"))
            return "کارت مسروقه است. کارت توسط دستگاه ضبط شود";
        else if (code.equalsIgnoreCase("44"))
            return "داده صورتحساب اعتباری موجود نیست";
        else if (code.equalsIgnoreCase("51"))
            return "موجودی حساب کافی نیست";
        else if (code.equalsIgnoreCase("54"))
            return "تاریخ استفاده از کارت به پایان رسیده است.کارت توسط دستگاه ضبط شود";
        else if (code.equalsIgnoreCase("55"))
            return "رمز صحیح نیست";
        else if (code.equalsIgnoreCase("56"))
            return "تائید کارت با موفقیت انجام نشد.CVV صحیح نیست";
        else if (code.equalsIgnoreCase("57"))
            return "دارنده کارت مجاز به انجام این تراکنش نیست";
        else if (code.equalsIgnoreCase("58"))
            return "انجام تراکنش مربوطه توسط پایانه انجام دهنده تراکنش مجاز نیست";
        else if (code.equalsIgnoreCase("59"))
            return "تراکنش مظنون به تقلب است";
        else if (code.equalsIgnoreCase("61"))
            return "مبلغ تراکنش از حد مجاز بیشتر است";
        else if (code.equalsIgnoreCase("62"))
            return "کارت محدود شده است";
        else if (code.equalsIgnoreCase("63"))
            return "تمهیدات امنیتی نقش شده است";
        else if (code.equalsIgnoreCase("65"))
            return "تعداد دفعات انجام تراکنش از حد مجاز بیشتر است";
        else if (code.equalsIgnoreCase("66"))
            return "حساب متصل به کارت بسته است";
        else if (code.equalsIgnoreCase("75"))
            return "تعداد دفعات ورود رمز غلط بیش از حد مجاز است. کارت توسط دستگاهضبط شود";
        else if (code.equalsIgnoreCase("77"))
            return "روز مالی تراکنش نامعتبر است";
        else if (code.equalsIgnoreCase("78"))
            return "کارت فعال نیست";
        else if (code.equalsIgnoreCase("79"))
            return "شماره حساب متصل به کارت نامعتبر یا دارای اشکال است";
        else if (code.equalsIgnoreCase("80"))
            return "تراکنش موفق عمل نکرده است";
        else if (code.equalsIgnoreCase("84"))
            return "صادرکننده کارت فعال نیست";
        else if (code.equalsIgnoreCase("86"))
            return "مقصد تراکنش در حالت signed off است";
        else if (code.equalsIgnoreCase("88"))
            return "کد اعتبار سنجی پیام نادرست است";
        else if (code.equalsIgnoreCase("90"))
            return "سیستم در حال انجام عملیات پایان روز است";
        else if (code.equalsIgnoreCase("91"))
            return "مدت زمان انتظار برای دریافت پاسخ از مقصد تراکنش پایان یافته است";
        else if (code.equalsIgnoreCase("92"))
            return "مسیری برای ارسال تراکنش به مقصد یافت نشد";
        else if (code.equalsIgnoreCase("93"))
            return "صادرکننده یا سوئیچ مقصد فعال نیست";
        else if (code.equalsIgnoreCase("94"))
            return "تراکنش تکراری است";
        else if (code.equalsIgnoreCase("96"))
            return "خطای داخلی";
        else if (code.equalsIgnoreCase("97"))
            return "فرآیند تغییر کلید در حال انجام است";
        return "ok";

    }

    public static String convertJSONString(String data) {
        data = data.replace("\\", "");
        return data;
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
//        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getServiceCo(int code) {
        switch (code) {
            case 1:
                return "آب";
            case 2:
                return "برق";
            case 3:
                return "گاز";
            case 4:
                return "تلفن ثابت";
            case 5:
                return "تلفن همراه";
            case 6:
                return "عوارض شهرداری";
            case 8:
                return "مالیات";
            case 9:
                return "جرائم راهنمایی";
            default:
                return "نامشخص";
        }
    }

    public static boolean validateMelliCode(String melliCode) {

        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};

        if (melliCode.trim().isEmpty()) {
//            Toast.makeText(context, "Melli Code is empty", Toast.LENGTH_LONG).show();
            return false; // Melli Code is empty
        } else if (melliCode.length() != 10) {
//            Toast.makeText(context, "Melli Code must be exactly 10 digits", Toast.LENGTH_LONG).show();
            return false; // Melli Code is less or more than 10 digits
        } else if (Arrays.asList(identicalDigits).contains(melliCode)) {
//            Toast.makeText(context, "MelliCode is not valid (Fake MelliCode)", Toast.LENGTH_LONG).show();
            return false; // Fake Melli Code
        } else {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(melliCode.charAt(i)) * (10 - i);
            }

            int lastDigit;
            int divideRemaining = sum % 11;

            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }

            if (Character.getNumericValue(melliCode.charAt(9)) == lastDigit) {
                //Toast.makeText(context, "MelliCode is valid", Toast.LENGTH_LONG).show();
                return true;
            } else {
                //Toast.makeText(context, "MelliCode is not valid", Toast.LENGTH_LONG).show();
                return false; // Invalid MelliCode
            }
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
            return phoneNumber.matches("^[+]?[0-9]{10,14}$");
            // ^[+]?[0-9]{10,13}$
        }
    }

    public static boolean isTextContainOnlyNumber(String text) {
        return text.matches("^[+]?[0-9]+");
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    protected static Spannable wrapInCustomfont(Context context, String myText) {
        final Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + "iran_sans.ttf");
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(typeface);
        SpannableString spannable = new SpannableString(myText);
        spannable.setSpan(typefaceSpan, 0, myText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public static void showSingleButtonAlert(Context context, String title, String msg, String buttonTitle, final IL listner) {
        try {
            TextView tvAlertTitle, tvAlertMessage;
            RelativeLayout btnAlertOK;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_alert, null);
            tvAlertTitle = (TextView) dialogAlert.findViewById(R.id.tv_alert_title);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnAlertOK = (RelativeLayout) dialogAlert.findViewById(R.id.btn_alert_ok);
            dialogAlert.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);
            btnAlertOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMessageButtonAlert(Context context, String title, String msg, int image, final IL listner) {
        try {
            TextView tvAlertTitle, tvAlertMessage;
            RelativeLayout btnAlertOK;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
            tvAlertTitle = (TextView) dialogAlert.findViewById(R.id.tv_alert_title);
            ImageView imgg = (ImageView) dialogAlert.findViewById(R.id.img);
            imgg.setImageResource(image);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnAlertOK = (RelativeLayout) dialogAlert.findViewById(R.id.btn_alert_ok);
            dialogAlert.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            if (TextUtils.isEmpty(title))
                tvAlertTitle.setVisibility(View.GONE);
            else {
                tvAlertTitle.setVisibility(View.VISIBLE);
                tvAlertTitle.setText(title);
            }

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);

//            btnAlertOK.setText(TextUtils.isEmpty(buttonTitle) ? StringLanguage..btn_ok) : buttonTitle);
            btnAlertOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showTwoButtonAlert(Context context, String msg, String buttonOk, String btnCancel, final IL listner) {
        try {
            TextView tvAlertMessage;
            ImageView btnAlertCancel;
            Button btnOk, btCancel;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_two_alert, null);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            btnOk = (Button) dialogAlert.findViewById(R.id.btnOk);
            btnOk.setText(buttonOk);
            btCancel = (Button) dialogAlert.findViewById(R.id.btnCancel);
            btCancel.setText(btnCancel);
            btnAlertCancel = (ImageView) dialogAlert.findViewById(R.id.btn_alert_cancel);

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);
            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onCancel();
                }
            });
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            btnAlertCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onCancel();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRightCardNumber(String cardNumber) {

        String ss[] = cardNumber.split("-");
        String s = "";
        for (int i = 0; i < ss.length; i++) {
            s = s.concat(ss[i]);
        }
        return s;
    }

    public static String getRightAmount(String amount) {

        String ss[] = amount.split(",");
        String s = "";
        for (int i = 0; i < ss.length; i++) {
            s = s.concat(ss[i]);
        }
        return s;
    }

    public interface IL {
        void onSuccess();

        void onCancel();
    }

    public static void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public static boolean isDialogOpen() {
        if (dialog != null)
            return dialog.isShowing();
        else
            return false;
    }

    public static void showDialogWithView(Context activity, final View view, final IL listner) {
        try {
            dismissDialog();
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            WindowManager.LayoutParams windowLayout = dialog.getWindow().getAttributes();

            windowLayout.gravity = Gravity.CENTER;
            dialog.show();
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        if (listner != null)
                            listner.onCancel();
                    }
                    return false;
                }
            });
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showDialogWithView(Context activity, final View view, final IL listner, boolean touchOutSide) {
        try {
            dismissDialog();
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(touchOutSide);
            WindowManager.LayoutParams windowLayout = dialog.getWindow().getAttributes();
            windowLayout.gravity = Gravity.CENTER;
            dialog.show();
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        if (listner != null)
                            listner.onCancel();
                    }
                    return false;
                }
            });
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int compareTime(String firstTime, String secondTime) {
        String[] time = firstTime.split(":");
        String[] time2 = secondTime.split(":");
        if (Integer.parseInt(time[0]) > Integer.parseInt(time2[0]))
            return 1;
        else if (Integer.parseInt(time[0]) < Integer.parseInt(time2[0]))
            return -1;
        else {
            if (Integer.parseInt(time[1]) > Integer.parseInt(time2[1]))
                return 1;
            else if (Integer.parseInt(time[1]) < Integer.parseInt(time2[1]))
                return -1;
            else
                return 0;
        }
    }

    public static int compareDate(String firstDate, String secondDate) {
        String[] date = firstDate.split("/");
        String[] date2 = secondDate.split("/");
        if (Integer.parseInt(date[0]) > Integer.parseInt(date2[0]))
            return 1;
        else if (Integer.parseInt(date[0]) < Integer.parseInt(date2[0]))
            return -1;
        else {
            if (Integer.parseInt(date[1]) > Integer.parseInt(date2[1]))
                return 1;
            else if (Integer.parseInt(date[1]) < Integer.parseInt(date2[1]))
                return -1;
            else {
                if (Integer.parseInt(date[2]) > Integer.parseInt(date2[2]))
                    return 1;
                else if (Integer.parseInt(date[2]) < Integer.parseInt(date2[2]))
                    return -1;
                else
                    return 0;
            }
        }
    }
}
