package com.togic.livevideo.test.action;

import java.io.IOException;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.togic.testlivetv.TestLivetv;

@SuppressLint("DefaultLocale")
public class ExternalSolo extends UiAutomatorTestCase implements
		IActionKeyword {

	private static final String TAG = "AutoTestLog";

	
	public static HashMap<String, Integer> keyMap() {
		HashMap<String, Integer> keyDict = new HashMap<String, Integer>();
		keyDict.put("UP", 19);
		keyDict.put("DOWN", 20);
		keyDict.put("LEFT", 21);
		keyDict.put("RIGHT", 22);
		keyDict.put("ENTER", 23);
		keyDict.put("MENU", 82);
		keyDict.put("BACK", 4);
		keyDict.put("MEDIA_NEXT", 87);
		keyDict.put("MEDIA_PREVIOUS", 88);
		keyDict.put("HOME", 3);
		keyDict.put("VOLUME_UP", 24);
		keyDict.put("VOLUME_DOWN", 25);
		keyDict.put("MUTE", 91);
		keyDict.put("0", 7);
		keyDict.put("1", 8);
		keyDict.put("2", 9);
		keyDict.put("3", 10);
		keyDict.put("4", 11);
		keyDict.put("5", 12);
		keyDict.put("6", 13);
		keyDict.put("7", 14);
		keyDict.put("8", 15);
		keyDict.put("9", 16);
		keyDict.put("a", 29);
		keyDict.put("b", 30);
		return keyDict;
	}

	public String getCurrentActivity() {
		@SuppressWarnings("deprecation")
		String currentActivity = TestLivetv.device.getCurrentActivityName();

		Log.d(TAG, "current activity : " + currentActivity);
		return currentActivity;
	}





	public void clickScreen(int x, int y) {
		TestLivetv.device.click(x, y);
		Log.d(TAG, "click on screen " + x + ",   " + y);
	}

	@Override
	public void clickTextView(String text) throws UiObjectNotFoundException, InterruptedException, IOException  {
		try {
			UiObject stringtext = new UiObject(new UiSelector().textContains(text));
			boolean ag1exist = stringtext.exists();
			if (ag1exist) {
				stringtext.click();
				Log.d(TAG, "click " + text);
			} else {
				Log.d(TAG, text.toString() + " not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "click on textview : " + text.toString() + "  failed");
		}
	}

	@Override
	public void pressKey(String keyName) throws UiObjectNotFoundException, InterruptedException, IOException {
		try {
			int keycode;
			keycode = ExternalSolo.keyMap().get(keyName.toUpperCase());
			Log.d(TAG, "keycode: " + keycode);
			TestLivetv.device.pressKeyCode(keycode);
			Log.d(TAG, "send keyevent: " + keyName);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "send keyevent : " + keyName.toString()
					+ "  failed");
		}
	}

	@Override
	public void dragScreen() {
	}


	@Override
	public void clickViewID(String id) throws UiObjectNotFoundException, InterruptedException, IOException {
		/*
		try {
			UiObject stringid = new UiObject(new UiSelector().resourceId(id));

			if (stringid.exists()) {
				stringid.click();
				Log.d(TAG, "click view by id : " + id.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "click view by id : " + id.toString() + "  failed");
		}
		*/
	}

	@Override
	public void clickKeycodeLoop(String keyName, int count) {
		Log.d(TAG, "press key " + keyName.toString() + " " + count + " times");
		for (int i = 0; i < count; i++) {
			try {
				int keycode;
				keycode = ExternalSolo.keyMap().get(keyName.toUpperCase());
				TestLivetv.device.pressKeyCode(keycode);
				sleep(200);
				Log.d(TAG, "send keyevent: " + keyName);

			} catch (Exception e) {
				e.printStackTrace();
				Log.d(TAG, "send keyevent : " + keyName.toString()
						+ "  failed");
			}
		}

	}

	@Override
	public void sleepScreen(int time) {
		sleep(time);
		Log.d(TAG, "screen sleep " + time + " millseconds");

	}

	@Override
	public void clickCheckBox(int index) {
		UiObject stringid = new UiObject(new UiSelector().index(index));
		if (stringid.exists()) {
			try {
				stringid.click();
				Log.d(TAG, "click on checkBox: " + index);
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public String getTextFromId(String id) {
		String value = null;
		/*
		UiObject stringid = new UiObject(new UiSelector().resourceId(id));
		try {
			value = stringid.getText().toString();
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			value = null;
		}
		*/
		return value; 
	}
	
	@Override
	public void clearLog() {
		
	}
	
	@Override
	public String getTextByIndex(int index) {
		String text = null;
		UiObject stringid = new UiObject(new UiSelector().className(android.widget.TextView.class).instance(index));
		if (stringid.exists()) {
			try {
				text = stringid.getText();
				Log.d(TAG, "get text by index: " + index + ",text: " + text.toString());
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
		return text;
	}

	@Override
	public void jumpActivity(String activityname) {

	}

	@Override
	public void backActivity() {
		TestLivetv.device.pressKeyCode(4);
	}

	@Override
	public void searchTextByClickEvent(String keyname, String text,
			int maxnumber) {
		for (int i = 0; i < maxnumber; i++) {
			UiObject stringtext = new UiObject(new UiSelector().textContains(text));
			boolean state = stringtext.exists();
			if (!state) {
				TestLivetv.device.pressKeyCode(ExternalSolo.keyMap().get(keyname.toUpperCase().toString()));
				Log.d(TAG, text + " not found, press " + keyname + " to continue");
			} else {
				Log.d(TAG,"text " + text + " found");
				break;
			}
			sleep(500);
		}
		
	}

	@Override
	public void pressKey(int keycode) {
		// TODO Auto-generated method stub
		try {
			TestLivetv.device.pressKeyCode(keycode);
			Log.d(TAG, "send keyevent: " + keycode);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "send keyevent: " + keycode + " failed");
		}
	}
	
	public String checkScreenRatio() {
		int width = TestLivetv.device.getDisplayWidth();
		int height = TestLivetv.device.getDisplayHeight();
		double ratio = height/width;
		double a= 0.75;
		if (ratio == a) {
			Log.d(TAG, "current screen ratio: 4:3");
			return "4:3";
		} else {
			Log.d(TAG, "current screen ratio: 16:9");
			return "16:9";
		}
	}

	@Override
	public void inputText(String text) {
		for (int i = 0; i < text.split("\\.")[0].length(); i++) {
			int keycode;
			Log.d(TAG, "text : " + text.substring(i,i+1));
			keycode = ExternalSolo.keyMap().get(text.substring(i,i+1));
			Log.d(TAG, "input keycode (text) : " + keycode);
			TestLivetv.device.pressKeyCode(keycode);
		}
		Log.d(TAG, "input text : " + text.toString());
	}

	@Override
	public void waitForActivity(String activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForNoText(String text) {
		int count = 1;
		boolean tc = true;
		Log.d(TAG, "wait for " + text + " dismiss");
		while (tc) {
			UiObject stringtext = new UiObject(new UiSelector().textContains(text));
			tc = stringtext.exists();
			sleep(200);
			Log.d(TAG, "dismiss method run " + count + " time");
			count++;
			if (count > 50) {
				break;
			}
		}
		Log.d(TAG,"text " + text + " dismissed.");
		
	}
}