package com.example.shopping.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Arrays;

public class NotifyTask extends AsyncTask <String, Integer, String>{
    private boolean Flag=false;
    private String ThreadId;
    private Context context;

    public NotifyTask(Context context){
    this.context =context;
    }


    @Override
    protected String doInBackground(String... strings) {
        System.out.println(Arrays.toString(strings));
        Toast.makeText(context, Arrays.toString(strings), Toast.LENGTH_SHORT).show();
        return null;
    }

//    public static void main(String[] args) {
//        NotifyTask notifyTask = new NotifyTask(MyApplication.getContext());
//        notifyTask.doInBackground("do something  in background");
//    }
}
