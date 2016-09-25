package com.baidao.domainmanager.api;

import com.baidao.domainmanager.data.Note;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hexi on 16/9/25.
 */

public interface NoteApi {

    @GET("notes")
    public Observable<List<Note>> getNotes();
}
