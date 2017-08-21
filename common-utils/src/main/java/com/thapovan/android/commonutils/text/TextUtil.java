package com.thapovan.android.commonutils.text;

import android.widget.EditText;

public class TextUtil {

	private TextUtil() { }

	public static String cleanupString(Object obj) {
		if(obj == null) return "";
		if(obj instanceof EditText){
			return ((EditText) obj).getText().toString().trim();
		}else{
			return cleanupString((String)obj);
		}
	}

	public static String cleanupString(String str) {
		if(!TextUtil.isValidString(str)) return "";
		return str.trim();
	}


	public static boolean isValidString(EditText editText){
		if(editText==null) return false;
		return isValidString(editText.getText().toString());
	}

	public static boolean isValidString(String string) {
		if(string == null){
			return false;
		}else if(string.trim().length() <= 0){
			return false;
		}else if(string.equals("null")){
			return false;
		}else {
			return true;
		}
	}

	// Workaround for EditText Cursor position
	public static void setText(EditText editText, String text){
		editText.setText("");
		if(TextUtil.isValidString(text)){
			editText.append(text);
		}
	}

	public static String capitalizeFirstLetter(String original) {
		if (original == null || original.length() == 0) return original;
		return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
	}

}
