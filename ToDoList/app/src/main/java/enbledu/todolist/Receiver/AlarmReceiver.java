package enbledu.todolist.Receiver;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.WindowManager;

import enbledu.todolist.R;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(R.string.notice);
        builder.setMessage(msg);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        Dialog dialog=builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        MediaPlayer player;
        player = MediaPlayer.create(context, R.raw.back);
        player.setLooping(true);
        player.start();
    }


}