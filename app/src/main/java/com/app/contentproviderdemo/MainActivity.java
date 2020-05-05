package com.app.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
ListView listView;
ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);

        resolver=getContentResolver();
        //1.to get the data from db of any app, data is stored in cursor
        //to get contact list and to get the sorted contact list add 5th parmeter as given instead of null
        Cursor cursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

//        //URI to read the call log info and ass READ_CALL_LOG permission
//        Cursor cursor=resolver.query(CallLog.Calls.CONTENT_URI, null,null,null, null);
//        String[] from=new String[]{CallLog.Calls.CACHED_NAME,CallLog.Calls.NUMBER};



        //3.just need name and number from contact list
        String[] from=new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};

        //4. id's for UI componenets
        int[] to=new int[]{R.id.tv1, R.id.tv2};

        //2.adapter with 5 params so define above 2params
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.cursor_view, cursor, from, to);
            listView.setAdapter(adapter);
    }
}
