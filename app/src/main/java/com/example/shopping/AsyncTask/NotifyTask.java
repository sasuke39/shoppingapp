package com.example.shopping.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;

public class NotifyTask extends AsyncTask <String, Integer, String>{
    private boolean Flag=false;
    private String ThreadId;
    private Context context;

    public NotifyTask(Context context){
    this.context =context;
    }


    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
