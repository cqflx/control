package com.example.control_air;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity {
	  Button but=null;//����һ�������ؼ�
	  EditText name=null;
	  EditText password=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.loginTxtName);
        password=(EditText)findViewById(R.id.loginTxtPassword);
        name.setText("123456");
        password.setText("123456");
        but=(Button)findViewById(R.id.loginBtnOk);//��ȡID��
        but.setOnClickListener(new setclick());
    }
    public class setclick implements OnClickListener
    {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String strname,strword;
			strname=name.getText().toString();
			strword=password.getText().toString();
			if(strname.equals("123456")&&strword.equals("123456"))
			{
				Intent  intent=new Intent();
				//����Intent����Ҫ������Activity
				intent.setClass(MainActivity.this, tcpActivity2.class);
				//ͨ��Intent������������һ��Activity
				MainActivity.this.startActivity(intent);
				Toast.makeText(MainActivity.this,"�û�����ȷ"	, 0).show();
			}
			else
				Toast.makeText(MainActivity.this,"�û�����������������飡"	, 0).show();
		}   	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
