package com.example.savefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 输入框
		edit = (EditText) findViewById(R.id.edit);
		Animation anim=AnimationUtils.loadAnimation(this, R.anim.edittext_in_anim);
		
		edit.setAnimation(anim);
		String text = loadData();
		if(text!=null){
			edit.setText(text);
			edit.setSelection(text.length());
			Toast.makeText(getApplicationContext(), "数据还原成功！",
					Toast.LENGTH_SHORT).show();
			
		}
	}

	// 页面销毁的时候对edittext中的信息进行保存
	@Override
	protected void onDestroy() {
		super.onDestroy();
		String textString = edit.getText().toString();
		//if (!TextUtils.isEmpty(textString)) {
			save(textString);
		//}
	}

	// 将存储的信息从文件中读取出来
	private String loadData() {
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try {
			in = openFileInput("editData");
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content.toString();
	}

	// 将edittext中的信息保存到文件中
	private void save(String textString) {
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = openFileOutput("editData", MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(textString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
