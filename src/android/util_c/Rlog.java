package android.util_c;
import android.util.Log;

import java.io.UnsupportedEncodingException;


public class Rlog {

	public static void e(String logTag, String string, RuntimeException ex) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.e(logTag, string);
		ex.printStackTrace();
		
	}

	public static void e(String logTag, String string, OutOfMemoryError e) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.e(logTag, string);
		e.printStackTrace();
		
	}

	public static void w(String logTag, String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.w(logTag, string);
	}

	public static void e(String logTag, String string,
			UnsupportedEncodingException uex) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.i(logTag, string);
		
	}

	public static void d(String logTag, String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.d(logTag, string);
	}

	public static void v(String logTag, String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.v(logTag, string);
	}

	public static void e(String logTag, String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.e(logTag, string);
	}

	public static void d(String logTag, String string, RuntimeException tr) {
		// TODO Auto-generated method stub
		System.out.println(string);
        Log.d(logTag, string);
		tr.printStackTrace();
	}

}
