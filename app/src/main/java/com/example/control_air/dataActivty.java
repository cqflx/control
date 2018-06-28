package com.example.control_air;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class dataActivty extends Activity {
	EditText wendu=null;//温度1
	EditText shidu=null;//湿度1
	EditText wendu2=null;
	EditText shidu2=null;
	EditText yanwu=null;
	TextView light1=null;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data);
		wendu= (EditText) findViewById(R.id.edittemp1);
		shidu= (EditText) findViewById(R.id.edithumi1);
		wendu2= (EditText) findViewById(R.id.edittemp2);
		shidu2= (EditText) findViewById(R.id.edithumi2);
		yanwu= (EditText) findViewById(R.id.editsmk);
		light1= (TextView) findViewById(R.id.light1);
		lv= (ListView) findViewById(R.id.lv);
		MyAsync myAsync=new MyAsync(wendu,shidu,wendu2,shidu2,yanwu,light1,lv,this);
		myAsync.execute();
	}
}