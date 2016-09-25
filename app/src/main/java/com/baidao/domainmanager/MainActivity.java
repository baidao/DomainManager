package com.baidao.domainmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.baidao.domain.DomainUtil;
import com.baidao.domainmanager.api.NoteApi;
import com.baidao.domainmanager.data.Note;
import com.baidao.domainmanager.domainConfig.HxServerDomainType;
import com.baidao.retrofitadapter.YtxSubscriber;
import com.baidao.retrofitadapter.exception.RetrofitException;
import com.baidao.servicefactory.ServiceFactory;
import com.google.gson.Gson;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchEnvironment(View view) {
        boolean debug = !DomainUtil.isDebug();
        DomainUtil.setIsDebug(debug);
        ServiceFactory.getInstance().clearCache();
    }

    public void retrieveNotes(View view) {
        NoteApi noteApi = ServiceFactory.getInstance().create(HxServerDomainType.NOTE, NoteApi.class);
        noteApi.getNotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new YtxSubscriber<List<Note>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    protected void onFailed(RetrofitException e) {
                        if (e.getKind() == RetrofitException.Kind.HTTP) {
                            int resCode = e.getResponse().code();
                            Log.e(TAG, "===resCode:" + resCode);
                        }
                        Log.e(TAG, "===retrieve notes error ", e);
                    }

                    @Override
                    public void onNext(List<Note> notes) {
                        Log.d(TAG, "===retrieve notes success: " + new Gson().toJson(notes));
                    }
                });
    }
}
