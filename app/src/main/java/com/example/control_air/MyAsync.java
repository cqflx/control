package com.example.control_air;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAsync extends AsyncTask {

    EditText wendu;
    EditText shidu;
    EditText wendu2;
    EditText shidu2;
    EditText yanwu;
    TextView light1;
    ListView lv;
    Context context;

    public MyAsync(EditText wendu, EditText shidu,EditText wendu2, EditText shidu2, EditText yanwu,TextView light1, ListView lv, Context context) {
        this.wendu = wendu;
        this.shidu = shidu;
        this.wendu2 = wendu2;
        this.shidu2 = shidu2;
        this.yanwu = yanwu;
        this.light1 = light1;
        this.lv = lv;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            Socket socket=new Socket("192.168.4.1",8888);
            InputStream is=socket.getInputStream();
            byte []data=new byte[1024];
            int count=is.read(data);
            String tmp=new String(data,0,count);
            System.out.println(tmp);
            is.close();
            socket.close();
            return  tmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        String  data=(String) o;
        String  LED1Status;
        wendu.setText(data.substring(1,3)+"C");
        shidu.setText(data.substring(3,5)+"%");
        wendu2.setText(data.substring(5,7)+"C");
        shidu2.setText(data.substring(7,9)+"%");
        yanwu.setText(data.substring(5,7)+"%");
        LED1Status=data.substring(9,10);
        if (LED1Status.equals("1")){
            light1.setBackgroundResource(R.drawable.light_off);
        }
        else
            light1.setBackgroundResource(R.drawable.light_on);

        MyDbHelper myDbHelper=new MyDbHelper(context);

        SQLiteDatabase db=myDbHelper.getWritableDatabase();

        db.execSQL("insert into data (wendu,shidu,wendu2,shidu2,yanwu) values(?,?,?,?,?)",new Object[]{wendu.getText(),shidu.getText(),wendu2.getText(),shidu2.getText(),yanwu.getText()});
        db=myDbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from data",null);
        List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        while(cursor.moveToNext())
        {
            System.out.println(cursor.getString(1));
            HashMap<String,String> tmp=new HashMap<String, String>();
            tmp.put("wendu",cursor.getString(1));
            tmp.put("shidu",cursor.getString(2));
            tmp.put("wendu2",cursor.getString(3));
            tmp.put("shidu2",cursor.getString(4));
            tmp.put("yanwu",cursor.getString(5));
            list.add(tmp);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(context,list,R.layout.item,new String[]{"wendu","shidu","wendu2","shidu2","yanwu"},new int[]{R.id.wendu,R.id.shidu,R.id.wendu2,R.id.shidu2,R.id.yanwu});
        lv.setAdapter(simpleAdapter);
    }
}
