package com.bk.phongthuy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TuoiVoChongActivity extends Activity {
	
	private Spinner cbbTuoi;
	private Spinner cbbGioiTinh;
	private String[] gioitinh = { "Nam", "Nữ" };
	private String[] listTuoi;
	private Button btnXemKetQua;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuoivochong_activity);
		listTuoi = getResources().getStringArray(R.array.list_tuoi);
		cbbTuoi = (Spinner) findViewById(R.id.cbb_tuoi);
		cbbGioiTinh = (Spinner) findViewById(R.id.cbb_gioitinh);
		init();
		btnXemKetQua = (Button) findViewById(R.id.btn_xemketqua);
		btnXemKetQua.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String data = configData();
				Intent i = new Intent(getBaseContext(), ResultActivity.class);
				i.putExtra("service", 7);
				i.putExtra("data", data);
				startActivity(i);
			}
		});
	}

	// tuoihap:Bính Dần
	// gioitinh:0
	private String configData() {
		String data = "";

		try {
			data = URLEncoder.encode("tuoihap", "UTF-8")
					+ "="
					+ URLEncoder.encode(cbbTuoi.getSelectedItem().toString()
							.trim(), "UTF-8");
			data += "&"
					+ URLEncoder.encode("gioitinh", "UTF-8")
					+ "="
					+ URLEncoder.encode((cbbGioiTinh.getSelectedItem()
							.toString().equals("Nam") ? 0 : 1)
							+ "", "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	private void init() {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, gioitinh);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cbbGioiTinh.setAdapter(dataAdapter);

		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listTuoi);
		dataAdapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cbbTuoi.setAdapter(dataAdapter2);

	}

}
