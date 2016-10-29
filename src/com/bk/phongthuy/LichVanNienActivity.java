package com.bk.phongthuy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

public class LichVanNienActivity extends Activity implements OnClickListener {
	Button btnChonlich;
	Button btnXemlich;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lichvannien_activity);
		Calendar now = Calendar.getInstance();
		btnChonlich = (Button) findViewById(R.id.btn_chonlich);
		btnChonlich.setText((now.get(Calendar.MONTH)+1) + "/"
				+ now.get(Calendar.YEAR));
		btnChonlich.setOnClickListener(this);

		btnXemlich = (Button) findViewById(R.id.btn_xemlich);
		btnXemlich.setOnClickListener(this);

	}

	// mMonth:4
	// mYear:2016
	private String configData() {
		String data = "";

		String[] ngaysinhtrai = btnChonlich.getText().toString().split("/");

		try {
			data = URLEncoder.encode("mMonth", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[0], "UTF-8");
			data += "&" + URLEncoder.encode("mYear", "UTF-8") + "="
					+ URLEncoder.encode(ngaysinhtrai[1], "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_chonlich:
			Calendar now = Calendar.getInstance();
			DatePickerDialog dpd = DatePickerDialog.newInstance(
					new OnDateSetListener() {

						@Override
						public void onDateSet(DatePickerDialog view, int year,
								int monthOfYear, int dayOfMonth) {
							String date = (++monthOfYear) + "/" + year;
							btnChonlich.setText(date);
						}
					}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
							.get(Calendar.DAY_OF_MONTH));
			dpd.setThemeDark(true);
			dpd.vibrate(true);
			dpd.dismissOnPause(true);
			dpd.showYearPickerFirst(true);
			dpd.setAccentColor(Color.parseColor("#9C27B0"));
			dpd.setTitle("Chọn Lịch Dương Cần Đổi");
			dpd.setCancelable(false);
			dpd.show(getFragmentManager(), "Datepickerdialog");
			break;
		case R.id.btn_xemlich:
			String data = configData();
			Intent i = new Intent(getBaseContext(), ResultActivity.class);
			i.putExtra("service", 1);
			i.putExtra("data", data);
			startActivity(i);
			break;

		default:
			break;
		}

	}

}
