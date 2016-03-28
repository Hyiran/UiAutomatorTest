package com.togic.livevideo.test.resultparser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import android.util.Log;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.togic.testlivetv.TestLivetv;

public class CheckResult extends UiAutomatorTestCase
		implements IResultKeyword {

	private static final String TAG = "AutoTestLog";
	private boolean tc;

	@Override
	public boolean checkText(String text, String state) {
		Log.d(TAG, "text check: " + text.toString());
		
		UiObject stringtext = new UiObject(new UiSelector().textContains(text));
		boolean tc = stringtext.exists();
		if (state.equals("exist")) {
			Log.d(TAG, "*****************" + tc);
			return tc;
		} else {
			Log.d(TAG, "--------------------------" + tc);
			return !tc;
		}
	}

	public boolean checkId(String id, String state) {
		/*
		int[] location = new int[2];
		UiObject stringid = new UiObject(new UiSelector().resourceId(id));
        boolean tc = stringid.exists();
		Log.d(TAG, "id_check: " + location.toString());
		if (state.equals("exist")) {
			return tc;
		} else {
			return !tc;
		}
		*/
		return false;
	}

	public String checkActivity() {

		@SuppressWarnings("deprecation")
		String currentActivity = TestLivetv.device.getCurrentActivityName().toString();
		Log.d(TAG,
				"current activity : "
						+ currentActivity.split("@")[0].toString());
		return currentActivity.split("@")[0].toString();
	}

	@Override
	public boolean checkToast(String toast, String state) {
		Log.d(TAG, "Toast check :" + toast.toString());
		UiObject stringtext = new UiObject(new UiSelector().textContains(toast));
		boolean tc = stringtext.exists();
		if (state.equals("exist")) {
			Log.d(TAG, "*****************" + tc);
			return tc;
		} else {
			Log.d(TAG, "--------------------------" + tc);
			return !tc;
		}
		
	}

	@Override
	public boolean checkReturnValue(String returnvalue, String text, String state) {
		Log.d(TAG, "return value check: " + text.toString());
		tc = true;
		if (state.equals("equal")) {
			if (returnvalue.equals(text)) {
				Log.d(TAG, "***************** " + tc);
				return tc;
			} else {
				Log.d(TAG, "***************** " + tc);
				return !tc;
			}
		} else if (state.equals("no_equal")) {
			if (returnvalue.equals(text)) {
				Log.d(TAG, "***************** " + tc);
				return !tc;
			} else {
				Log.d(TAG, "***************** " + tc);
				return tc;
			}
		} else if (state.equals("contain")) {
			Log.d(TAG, "compare-----contain");
			if (text.contains(returnvalue)) {
				Log.d(TAG, "***************** " + tc);
				return tc;
			} else {
				Log.d(TAG, "***************** " + tc);
				return !tc;
			}
		}
		else {
			if (text.contains(returnvalue)) {
				Log.d(TAG, "------------------ " + tc);
				return !tc;
			} else {
				Log.d(TAG, "----------------- " + tc);
				return tc;
			}
		}
	}

	@Override
	public void saveScreen(String name) {
		try {
			File path = new File("/sdcard/" + name + ".jpg");
			try {
				TestLivetv.device.takeScreenshot(path);
			} catch (NoSuchMethodError e) {
				Log.d(TAG, "take screenshot metho not implemented" );
			}
			Log.d(TAG, "take screenshot, filename: " + name + ".jpg" );
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "take screenshot failed " );
		}
	}

}