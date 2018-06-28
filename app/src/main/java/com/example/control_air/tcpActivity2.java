package com.example.control_air;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.widget.Toast.LENGTH_LONG;

public class tcpActivity2 extends Activity {
    Button gobut = null;
    Button enterbut = null;
    EditText IP=null;
    EditText PORT=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcp);
        IP=(EditText)findViewById(R.id.editIp);
        IP.setText("192.168.4.1");
        PORT=(EditText)findViewById(R.id.editport);
        PORT.setText("8888");
        enterbut=(Button)findViewById(R.id.conn);//获取ID号
        enterbut.setOnClickListener(new enterclick());
        gobut = (Button) findViewById(R.id.buttongo);//获取ID号
        gobut.setOnClickListener(new goclick());
    }
    public class enterclick implements OnClickListener {
        @Override
        public void onClick(View arg0) {
            String strIP,strPORT;
            strIP=IP.getText().toString();
            strPORT=PORT.getText().toString();
            if(strIP.equals("192.168.4.1")&&strPORT.equals("8888"))
            {
                enterbut.setBackgroundResource(R.drawable.btnopen);
                Toast.makeText(tcpActivity2.this,"服务器连接成功！"	, 0).show();

            }
            else
                Toast.makeText(tcpActivity2.this,"服务器连接失败！"	, 0).show();
        }
    }
    public class goclick implements OnClickListener {
        @Override
        public void onClick(View arg0) {
            Intent intent = new Intent(tcpActivity2.this, dataActivty.class);
            startActivity(intent);
        }
    }
}