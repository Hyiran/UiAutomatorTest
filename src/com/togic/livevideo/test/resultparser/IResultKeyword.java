package com.togic.livevideo.test.resultparser;

public interface IResultKeyword {
	public boolean checkText(String text, String state);
	public boolean checkId(String id, String state);
	public boolean checkToast(String toast, String state);
	public boolean checkReturnValue(String returnvalue, String text, String state);	
	public String checkActivity();
	public void saveScreen(String name);
}