package com.bk.phongthuy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;

public class ResultActivity extends Activity {
	private WebView viewResult;
	private ImageView imv;
	public String data = "";
	public int service = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_activity);
		Intent i = getIntent();
		data = i.getStringExtra("data");
		service = i.getIntExtra("service", -1);
		viewResult = (WebView) findViewById(R.id.textview);
		imv = (ImageView) findViewById(R.id.btn_image);
		viewResult.setVisibility(View.GONE);
		startAnimation();
		new Asystasktest().execute();

		Log.e("tuyenpx", "tuyenpx : " + " data = " + data + " service = "
				+ service + " url " + getService(service));

	}

	private void startAnimation() {
		Animation animation = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.rotationanimation);
		animation.setRepeatCount(-1);
		animation.setRepeatMode(1);
		imv.startAnimation(animation);
	}

	private void stopAnimation() {
		imv.clearAnimation();
	}

	private String getService(int service) {
		String url = "";
		switch (service) {
		case 0:// Xem Bói Tình Yêu
			url = "http://www.blogphongthuy.com/dataphongthuy/tinhyeu.php";
			break;
		case 1:// Xem Lịch Vạn Niên
			url = "http://www.blogphongthuy.com/dataphongthuy/vannien.php";
			break;
		case 2:// Xem Sim số đẹp
			url = "http://www.blogphongthuy.com/dataphongthuy/simdep.php";
			break;
		case 3:// Xem ngay tot Xau :
			url = "http://www.blogphongthuy.com/dataphongthuy/xemngay.php";
			break;
		case 4: // Xem Thoi Van :
			url = "http://www.blogphongthuy.com/dataphongthuy/thoivan.php";
			break;
		case 5: // Xem Nam Sinh Con :
			url = "http://www.blogphongthuy.com/dataphongthuy/namsinhcon.php";
			break;
		case 6: // Xem Sao Chieu Menh :
			url = "http://www.blogphongthuy.com/dataphongthuy/saomenh.php";
			break;
		case 7: // Xem Tuoi Vo Chong
			url = "http://www.blogphongthuy.com/dataphongthuy/tuoivochong.php";
			break;
		case 8: // Tuổi Xây nhà
			url = "http://www.blogphongthuy.com/dataphongthuy/tuoixaynha.php";
			break;
		default:
			break;
		}

		return url;
	}

	class Asystasktest extends AsyncTask<Object, Void, String> {
		@Override
		protected String doInBackground(Object... params) {
			String text = "";
			try {
				URL url = new URL(getService(service).trim());
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(
						conn.getOutputStream());
				wr.write(data);
				wr.flush();

				// Get the server response

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;

				// Read Server Response
				while ((line = reader.readLine()) != null) {
					// Append server response in string
					sb.append(line + "\n");
				}

				text = sb.toString();
				Log.e("tuyenpx", "tuyenpx" + text);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("tuyenpx", "tuyenpx" + e.toString());
			}

			return text;
		}

		@SuppressLint("SetJavaScriptEnabled")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			viewResult.setVisibility(View.VISIBLE);
			viewResult.getSettings().setJavaScriptEnabled(true);
			viewResult.loadDataWithBaseURL(null, result, "text/html", "utf-8",
					null);
			imv.setVisibility(View.GONE);
			stopAnimation();

		}

	}

}
