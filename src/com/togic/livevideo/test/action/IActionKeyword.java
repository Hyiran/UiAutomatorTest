package com.togic.livevideo.test.action;

import java.io.IOException;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IActionKeyword {
	public void clickTextView(String text) throws UiObjectNotFoundException, InterruptedException, IOException;
	public void pressKey(String keyname) throws UiObjectNotFoundException, InterruptedException, IOException;
	public void pressKey(int keycode);
	public void inputText(String text);
	public void clickScreen(int x, int y);
	public void clickViewID(String id) throws UiObjectNotFoundException, InterruptedException, IOException;
	public void clickKeycodeLoop(String keyname, int count);
	public void dragScreen();
	public void sleepScreen(int time);
	public void clickCheckBox(int index);
	public String getTextFromId(String id);
	public String getCurrentActivity();
	public	void clearLog();
	public String getTextByIndex(int index);
	public void jumpActivity(String activityname);
	public	void backActivity();
	public void searchTextByClickEvent(String keyname, String text, int maxnumber);
	public String checkScreenRatio();
	public void waitForActivity(String activity);
	public void waitForNoText(String text);
}