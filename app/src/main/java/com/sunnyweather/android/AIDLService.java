package com.sunnyweather.android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    private final String TAG = "Server";
    private List<Book> bookList;

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bookList = new ArrayList<>();
        initData();
    }

    private void initData() {
        Book book1 = new Book("AAAAA");
        Book book2 = new Book("BBBBB");
        Book book3 = new Book("CCCCC");
        Book book4 = new Book("DDDDD");
        Book book5 = new Book("EEEEE");
        Book book6 = new Book("FFFFF");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
    }

    private final BookController.Stub stub = new BookController.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.e("ClientMainActivity", "Server getBookList");
            return bookList;
        }

        @Override
        public void addBookInOut(Book book) throws RemoteException {
            if (book != null){

                Log.e("ClientMainActivity", "Server addBookInOut");
                book.setName("服务器改了新书的名字 InOut");
                bookList.add(book);
            }else {
                Log.e(TAG,"接收到一个空对象 InOut");
            }
        }
    };
}
