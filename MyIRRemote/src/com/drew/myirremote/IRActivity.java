package com.drew.myirremote;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;

public class IRActivity extends Activity {
	private SparseArray<String> codes;
	private Object irService;
	private Method irWrite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ir);

		codes = new SparseArray<String>();

		codes.put(
				R.id.irRemoteTvPower,
				"0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f "
						+ "0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f "
						+ "0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 "
						+ "0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 "
						+ "0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f "
						+ "0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 "
						+ "00a9 00a8 0015 0015 0015 0e6e");
		codes.put(
				R.id.irRemoteXboxPower,
				"0000 0073 0000 0022 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0020 0010 0010 0020 0010 0010 0010 09AC");

		codes.put(
				R.id.irRemoteXboxPause,
				"0000 0073 0000 0020 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0020 0020 0010 0010 0010 "
						+ "0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0020 0020 0010 0010 0020 0010 09AC");

		codes.put(
				R.id.irRemoteXboxPlay,
				"0000 0073 0000 0021 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0020 0020 0010 0010 0010 "
						+ "0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0020 0010 0010 0010 0010 0010 09AC");

		codes.put(
				R.id.irRemoteXboxA,
				"0000 0073 0000 0020 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0020 0020 0010 0010 0010 "
						+ "0010 0020 0020 0020 0010 0010 0010 0010 0020 0010 0010 0020 "
						+ "0010 0010 0020 0010 0010 0020 0010 09AC");

		codes.put(
				R.id.irRemoteXboxX,
				"0000 0073 0000 0020 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0020 0020 0010 0010 0010 "
						+ "0010 0020 0020 0020 0010 0010 0010 0010 0020 0010 0010 0020 "
						+ "0020 0020 0010 0010 0010 0010 0010 09AC");

		codes.put(
				R.id.irRemoteXboxB,
				"0000 0073 0000 001F 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0020 0020 0010 0010 0010 "
						+ "0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 0020 0020 "
						+ "0010 0010 0020 0020 0020 09BC");

		codes.put(
				R.id.irRemoteXboxY,
				"0000 0073 0000 0020 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0020 0020 0010 0010 0010 "
						+ "0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 0020 0020 "
						+ "0010 0010 0020 0010 0010 0020 0010 09AC");

		codes.put(
				R.id.irRemoteXboxUp,
				"0000 0073 0000 0022 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0020 0010 0010 0010 0010 0010 0010 0020 0010 09AC");

		codes.put(
				R.id.irRemoteXboxLeft,
				"0000 0073 0000 0022 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 "
						+ "0020 0020 0010 0010 0010 0010 0010 0010 0010 0010 0010 09AC");

		codes.put(
				R.id.irRemoteXboxRight,
				"0000 0073 0000 0021 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 "
						+ "0020 0020 0010 0010 0010 0010 0010 0010 0020 09BC");

		codes.put(
				R.id.irRemoteXboxDown,
				"0000 0073 0000 0022 0060 0020 0010 0010 0010 0010 0010 0020 "
						+ "0010 0020 0030 0020 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0020 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0010 0020 0020 0020 0010 0010 0010 0010 0010 0010 "
						+ "0010 0010 0020 0010 0010 0010 0010 0010 0010 0010 0010 09BC");

		irService = this.getSystemService("irda");
		Class c = irService.getClass();
		Class p[] = { String.class };
		try {
			irWrite = c.getMethod("write_irsend", p);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ir, menu);
		return true;
	}

	public void irSend(View view) {
		String data = codes.get(view.getId());
		if (data != null) {
			try {
				data = hex2dec(data);
				irWrite.invoke(irService, data);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private String hex2dec(String irData) {
		List<String> list = new ArrayList<String>(Arrays.asList(irData
				.split(" ")));
		list.remove(0); // dummy
		int frequency = Integer.parseInt(list.remove(0), 16); // frequency
		list.remove(0); // seq1
		list.remove(0); // seq2

		for (int i = 0; i < list.size(); i++) {
			list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
		}

		frequency = (int) (1000000 / (frequency * 0.241246));
		list.add(0, Integer.toString(frequency));

		irData = "";
		for (String s : list) {
			irData += s + ",";
		}
		return irData;
	}
}
