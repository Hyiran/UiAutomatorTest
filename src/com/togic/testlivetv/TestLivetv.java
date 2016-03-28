package com.togic.testlivetv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.togic.livevideo.test.excel.ExcelOption;
import com.togic.livevideo.test.excel.LoadTestData;
import com.togic.livevideo.test.resultparser.CheckResult;

import com.togic.livevideo.test.action.ExternalSolo;


public class TestLivetv extends UiAutomatorTestCase {

	private int sheetindex = 0;
	private String filepath = "/sdcard/testcase.xls";
	private static final String TAG = "AutoTestLog";
	private int execFailCount = 0;
	private final int maxRetryTime = 3;

	LoadTestData td = new LoadTestData();
	ExcelOption eo = new ExcelOption();
	public static UiDevice device = UiDevice.getInstance();

	ExternalSolo externalsolo = new ExternalSolo();
	CheckResult checkresult = new CheckResult();

	public void testDemo() throws UiObjectNotFoundException,
			InterruptedException, IOException {
		// getUiDevice().pressHome();
		// 进入设置菜单
		/*
		 * UiObject settingApp = new UiObject(new UiSelector().text("电视台"));
		 * settingApp.click(); //休眠3秒 try { Thread.sleep(3000); } catch
		 * (InterruptedException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } // 进入语言和输入法设置
		 * getUiDevice().pressDPadCenter(); UiObject program = new UiObject(new
		 * UiSelector().textContains("综艺")); program.click();
		 * Thread.sleep(3000); getUiDevice().pressMenu(); UiObject menu = new
		 * UiObject(new UiSelector().text("立即同步")); menu.click();
		 * getUiDevice().click(725, 300); UiObject m = new UiObject(new
		 * UiSelector().description("收藏")); m.click();
		 */

		String testcaseid;
		String teststepid;

		Log.d(TAG, "test start");
		Map<String, String> get_testdata;
		Map<String, HashMap<String, Map<String, String>>> testcases = td
				.loadTestCasetest(sheetindex, filepath);
		Log.d(TAG, "load test data finish");
		Log.d(TAG, testcases.toString());

		ArrayList<String> returnValueList = new ArrayList<String>();
		for (int testcase_num = 1; testcase_num < testcases.size() + 1; testcase_num++) {

			if (testcase_num < 10) {
				testcaseid = "Test_00" + String.valueOf(testcase_num);
			} else if (testcase_num < 100) {
				testcaseid = "Test_0" + String.valueOf(testcase_num);
			} else {
				testcaseid = "Test_" + String.valueOf(testcase_num);
			}
			Log.d(TAG, testcaseid);
			Log.d(TAG, testcases.get(testcaseid).toString());
			for (int teststep_num = 1; teststep_num < testcases.get(testcaseid)
					.size() + 1; teststep_num++) {

				teststepid = String.valueOf(teststep_num) + ".0";
				Log.d(TAG, "teststep:" + String.valueOf(teststep_num)
						+ testcases.get(testcaseid).get(teststepid).toString()
						+ testcaseid);
				get_testdata = testcases.get(testcaseid).get(teststepid);
				Log.d(TAG, get_testdata.get("testDesign"));
				Log.d(TAG,
						"current row:    "
								+ String.valueOf(get_testdata.get("rowNumber")));
				int k = Integer.parseInt(String.valueOf(get_testdata
						.get("rowNumber")));
				// data structure
				String ok = get_testdata.get("optionKeyWord");
				String act = get_testdata.get("action");
				String ag1 = get_testdata.get("argv1");
				String ag2 = get_testdata.get("argv2");
				String erk = get_testdata.get("expectResultKeyWord");
				String erv = get_testdata.get("expectResultValue");
				String cc = get_testdata.get("checkCondition");
				String efe = get_testdata.get("execFailElement");
				String efk = get_testdata.get("execFailKeyword");
				String efc = get_testdata.get("execFailCondition");
				String returnvalue;
				boolean execState = true;

				if (get_testdata.get("flagExec").equals("Y")) {
					Log.d(TAG,
							"case run flag :"
									+ get_testdata.get("optionKeyWord"));

					try {
						returnvalue = null;
						if (ok.equals("textview") && act.equals("click")) {
							externalsolo.clickTextView(ag1);
						} else if (ok.equals("textview")
								&& act.equals("gettext")) {
							returnvalue = externalsolo.getTextByIndex(eo
									.converStr2Num(ag1.toString()));
							returnValueList.add(returnvalue.toString());
						} else if (ok.equals("text") && act.equals("input")) {
							externalsolo.inputText(ag1.toString());
						} else if (ok.equals("view_id") && act.equals("click")) {
							externalsolo.clickViewID(ag1);
						} else if (ok.equals("keycode")) {
							externalsolo.pressKey(act);
						} else if (ok.equals("keycodeloop")) {
							externalsolo.clickKeycodeLoop(act,
									eo.converStr2Num(ag1.toString()));
						} else if (ok.equals("screen") && act.equals("touch")) {
							externalsolo.clickScreen(
									eo.converStr2Num(ag1.toString()),
									eo.converStr2Num(ag2.toString()));
						} else if (ok.equals("screen") && act.equals("click")) {
							externalsolo.clickScreen(
									eo.converStr2Num(ag1.toString()),
									eo.converStr2Num(ag2.toString()));
						} else if (ok.equals("screen") && act.equals("ratio")) {
							returnvalue = externalsolo.checkScreenRatio();
						} else if (ok.equals("screen") && act.equals("sleep")) {
							externalsolo.sleepScreen(eo.converStr2Num(ag1
									.toString()));
						} else if (ok.equals("imagebutton")
								&& act.equals("click")) {
							Log.d(TAG, ag1.toString());
						} else if (ok.equals("checkbox") && act.equals("click")) {
							externalsolo.clickCheckBox(eo.converStr2Num(ag1
									.toString()));
						} else if (ok.equals("activity") && act.equals("jump")) {
							Log.d(TAG, "current activity : ");
							externalsolo.jumpActivity(ag1.toString());
						} else if (ok.equals("activity")
								&& act.equals("goback")) {
							externalsolo.backActivity();
						} else if (ok.equals("textview")
								&& act.equals("switch")) {
							externalsolo.searchTextByClickEvent(ag1.toString(),
									ag2.toString(), 42);
						} else if (ok.equals("textview")
								&& act.equals("dismiss")) {
							externalsolo.waitForNoText(ag1);
						} else {
							Log.d(TAG, "test case not run");
							externalsolo.clearLog();
						}
					} catch (Exception e) {
						e.printStackTrace();
						eo.setValue(0, filepath, k, 2, "N");
						break;
					} finally {

					}

					// result parser
					if (erk.equals("text_check")) {
						// tc = solo.waitForText(erv, 0, 18000);
						if (checkresult.checkText(erv, cc)) {

							eo.setValue(0, filepath, k, 2, "Y");
							Log.d(TAG, "result :" + erv.toString() + " true"
									+ " write to " + k);
						} else {
							execState = false;
							eo.setValue(0, filepath, k, 2, "N");
							checkresult.saveScreen(testcaseid + "_"
									+ teststep_num);
							Log.d(TAG, "result :" + erv.toString() + " false"
									+ " write to " + k);
						}
					} else if (erk.equals("id_check")) {
						if (checkresult.checkId(erv, cc)) {
							eo.setValue(0, filepath, k, 2, "Y");
							Log.d(TAG, "result :" + erv.toString() + " true"
									+ " write to " + k);
						} else {
							execState = false;
							eo.setValue(0, filepath, k, 2, "N");
							checkresult.saveScreen(testcaseid + "_"
									+ teststep_num);
							Log.d(TAG, "result :" + erv.toString() + " false"
									+ " write to " + k);
						}
					} else if (erk.equals("activity_check")) {
						if (erv.toString().equals(
								checkresult.checkActivity().toString())) {
							eo.setValue(0, filepath, k, 2, "Y");
							Log.d(TAG, "result :" + erv.toString() + " true"
									+ " write to " + k);
						} else {
							execState = false;
							eo.setValue(0, filepath, k, 2, "N");
							checkresult.saveScreen(testcaseid + "_"
									+ teststep_num);
							Log.d(TAG, "result :" + erv.toString() + " false"
									+ " write to " + k);
						}
					} else if (erk.equals("toast_check")) {
						if (checkresult.checkToast(erv, cc)) {
							eo.setValue(0, filepath, k, 2, "Y");
							Log.d(TAG, "result :" + erv.toString() + " true"
									+ " write to " + k);
						} else {
							execState = false;
							eo.setValue(0, filepath, k, 2, "N");
							checkresult.saveScreen(testcaseid + "_"
									+ teststep_num);
							Log.d(TAG, "result :" + erv.toString() + " false"
									+ " write to " + k);
						}
					} else if (erk.equals("returnvalue_compare")) {
						if (erv.toString().equals("previous")) {
							try {
								if (checkresult.checkReturnValue(
										returnValueList.get(0).toString(),
										returnValueList.get(1).toString(), cc)) {
									eo.setValue(0, filepath, k, 2, "Y");
									returnValueList.clear();
									Log.d(TAG, "result :" + erv.toString()
											+ " true" + " write to " + k);
								} else {
									execState = false;
									eo.setValue(0, filepath, k, 2, "N");
									returnValueList.clear();
									checkresult.saveScreen(testcaseid + "_"
											+ teststep_num);
									Log.d(TAG, "result :" + erv.toString()
											+ " false" + " write to " + k);
								}
							} catch (java.lang.IndexOutOfBoundsException e) {
								e.printStackTrace();
								Log.d(TAG, "return value compare failed");
							}
						} else {
							if (checkresult.checkReturnValue(returnvalue,
									erv.toString(), cc)) {
								eo.setValue(0, filepath, k, 2, "Y");
								Log.d(TAG, "result :" + erv.toString()
										+ " true" + " write to " + k);
							} else {
								execState = false;
								eo.setValue(0, filepath, k, 2, "N");
								checkresult.saveScreen(testcaseid + "_"
										+ teststep_num);
								Log.d(TAG, "result :" + erv.toString()
										+ " false" + " write to " + k);
							}
						}
					}
					if (!execState) {
						if (execFailCount < maxRetryTime) {
							if (efk.equals("textview") && efc.equals("exist")) {
								testcase_num = testcase_num - 1;
								externalsolo.searchTextByClickEvent("back",
										efe.toString(), 40);
								execFailCount++;
								Log.d(TAG, "testcase fail, retry "
										+ execFailCount + " times");
							}
						}
					}
				}
			}

		}
	}
}