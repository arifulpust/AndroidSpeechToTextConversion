package net.simplifiedcoding.speechtotext.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dream71 on 02/11/2017.
 */

public class ToastyMsg {
    public static void Success(Context context, String message)
    {
        es.dmoral.toasty.Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
    }
    public static void Error(Context context, String message)
    {

        es.dmoral.toasty.Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
    }
    public static void Warning(Context context, String message)
    {

        es.dmoral.toasty.Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show();

    }
    public static void Info(Context context, String message)
    {

        es.dmoral.toasty.Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();
    }

}
