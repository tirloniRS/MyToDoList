package util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by alexsandro-rs on 27/03/2015.
 */
public class Mensagem {
    public static void Msg(Activity activity, String msg){
        Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
    }
}
