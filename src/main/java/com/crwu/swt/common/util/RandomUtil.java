package com.crwu.swt.common.util;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * @author cr.wu
 *
 * 2015-8-2
 */
public class RandomUtil {
	
	public static final char[] SMALL_CHAR = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','_'};
	public static final char[] BIG_CHAR = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','_'};
	public static final Object[] NUM_CHAR = new Object[]{'0','1','2','3','4','5','6','7','8','9'};
	
	private static RandomUtil inst;
	private Random random;
	
	private RandomUtil(){
		random = new Random(new Date().getTime());
	}
	
	public static RandomUtil getInst(){
		if(inst == null){
			inst = new RandomUtil();
		}
		return inst;
	}
	
	/**
	 * 获取范围长度的中英数
	 * @param start
	 * @param end
	 * @return
	 */
	public  String getRandomECN(int start ,int end){
		String en = getEnRandom(start, end);
		String ch = getChRandom(start, end);
		String num = getNumRandom(start, end);
		int i = random.nextInt(3);
		if(i == 0){
			return en+ch+num;
		}else if(i == 1){
			return ch+num+en;
		}else {
			return num+en+ch;
		}
		
	}
	
	/**
	 * 获取范围内的英语+数字
	 * @param start
	 * @param end
	 * @return
	 */
	public String getRandomEN(int start,int end){
		int len = getRange(start, end);
		
		return getFixedEN(len);
	}
	
	/**
	 * 获取len长度的英文+数字
	 * @param len
	 * @return
	 */
	public String getFixedEN(int len){
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i<len;i++){
			if(getInt(2)%2 == 0){
				sb.append(getEn());
			}else{
				sb.append(getNum());
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 获取范围中的随机数
	 * @param start
	 * @param end
	 * @return
	 */
	public int getRange(int start , int end){
		int index = random.nextInt(end - start)+start;
		return index;
	}
	/**
	 * 获取一个随机数
	 * */
	public int getInt(int len){
		return random.nextInt(len);
	}
	/***********************************       英语随机        *********************************/
	
	/**
	 * 获取一个英语
	 * @return
	 */
	public  String getEn(){
		String f = SMALL_CHAR[random.nextInt(Integer.MAX_VALUE)%SMALL_CHAR.length]+"";
		return f;
	}
	
	/**获取长度为len的英语字符串
	 * @param len
	 * @return
	 */
	public String getEnFixed(int len){
		String str = "";
		for (int i = len; i > 0; i--) {
			str = str + getEn();
		}
		return str;
	}
	
	/**
	 * 获取指定范围的英语字符串
	 * @param len
	 * @return
	 */
	public  String getEnRandom(int start , int end){
		int len = getRange(start,end);
		return getEnFixed(len);
	}
	
	/*******************************        获取随机数字字符串            ******************************/	
	/**
	 * 获取一个数字
	 * @return
	 */
	public  String getNum(){
		int r = Math.abs(random.nextInt(10));
		return r+"";
	}
	/**获取长度为len的数字字符串
	 * @param len
	 * @return
	 */
	public  String getNumFixed(int len){
		String str = "";
		for (int i = len; i > 0; i--) {
			str = str + getNum();
		}
		return str;
	}
	/**
	 * 获取指定范围的英语字符串
	 * @param len
	 * @return
	 */
	public  String getNumRandom(int start , int end){
		int len = getRange(start,end);
		return getNumFixed(len);
	}
	
	/*******************************        获取随机中文字符串           ******************************/	
	
	/**
	 * 获取长度为len的中文字符串
	 * @param len
	 * @return
	 */
	public  String getChFixed(int len){
		String str = "";
		for (int i = len; i > 0; i--) {
			str = str + getChinese();
		}
		return str;
	}
	/**
	 * 获取指定范围的英语字符串
	 * @param len
	 * @return
	 */
	public  String getChRandom(int start , int end){
		int index = getRange(start,end);
		return getChFixed(index);
	}
	/**
	 * 获取一个中文
	 * @return
	 */
	public  String getChinese() {
		String str = null;
		int highPos, lowPos;
		highPos = (176 + Math.abs(random.nextInt(39)));
		lowPos = 161 + Math.abs(random.nextInt(93));
		byte[] b = new byte[2];
		b[0] = (new Integer(highPos)).byteValue();
		b[1] = (new Integer(lowPos)).byteValue();
		try {
			str = new String(b, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//2E80-2FDF, 3400-4DBF, 4E00-9FFF
		return str;
	}
	/***********************************    获取指定数组的随机内容      *********************************/
	
	public Object  getSpecial(Object[] sps){
		int r = Math.abs(random.nextInt(sps.length));
		return sps[r]+"";
	}
	
	public Object[] getSpecialFixed(Object[] sps , int len){
		Object[] objs = new Object[len];
		for (int i = len; i > 0; i--) {
			objs[i] = getSpecial(sps);
		}
		return objs;
	}
	
	public  Object[] getNumRandom(Object[] sps , int start , int end){
		int len = getRange(start,end);
		return getSpecialFixed(sps,len);
	}
	
	
	
}

