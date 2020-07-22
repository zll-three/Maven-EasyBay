package com.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class StringUtils {
    private static String[] binaryArray =   
        {"0000","0001","0010","0011",  
        "0100","0101","0110","0111",  
        "1000","1001","1010","1011",  
        "1100","1101","1110","1111"};  
//    private static String[] chineseDigits = new String[] { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?"};
    public static String[] chineseDigits = new String[] { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?"};
	
    private static final char[] charBytes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    private static final char[] numberBytes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    /**
     * 生成指定位数的随机数�?.
     * @param number
     * @return
     */
    public static String randomNumbers(int number) {
    	int count = 0; //生成的密码的长度
    	int i; //生成的随机数
    	final int maxNum = numberBytes.length;
    	StringBuffer randomStr = new StringBuffer("");
        Random r = new Random();
        while(count < number){
        	 //生成随机数，取绝对�?�，防止生成负数�?
	         i = Math.abs(r.nextInt(maxNum)); //生成的数�?大为36-1
	         if (i >= 0 && i < numberBytes.length) {
	        	 randomStr.append(numberBytes[i]);
	          count ++;
	         }
        }
        return randomStr.toString();
    }
    
    
    
    public static String randomStrByNumber(int number) {
    	int count = 0; //生成的密码的长度
    	int i; //生成的随机数
    	final int maxNum = charBytes.length;
    	StringBuffer randomStr = new StringBuffer("");
        Random r = new Random();
        while(count < number){
        	 //生成随机数，取绝对�?�，防止生成负数�?
	         i = Math.abs(r.nextInt(maxNum)); //生成的数�?大为36-1
	         if (i >= 0 && i < charBytes.length) {
	        	 randomStr.append(charBytes[i]);
	          count ++;
	         }
        }
        return randomStr.toString();
    }
    
    
    public static String randomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
	public static String digitsTochinese(int i){
		//大于10的需要重�?
		return chineseDigits[i];
	}
	public static String toAllUpperCase(String uuid) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < uuid.length(); i++) {
			char c = uuid.charAt(i);
			if (Character.isLowerCase(c)) {
				buffer.append(Character.toUpperCase(c));
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	

	
	// 十六进制字符串转byte数组
		public static byte[] hexStringToBytes(String hexString) {
			if (hexString == null || hexString.equals("")) {
				return null;
			}
			hexString = hexString.toUpperCase();
			int length = hexString.length() / 2;
			char[] hexChars = hexString.toCharArray();
			byte[] d = new byte[length];
			for (int i = 0; i < length; i++) {
				int pos = i * 2;
				d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
			}
			return d;
		}

		private static byte charToByte(char c) {
			return (byte) "0123456789ABCDEF".indexOf(c);
		}

		// 数组转字符串、以空格间隔
		public static String bytesToHexString(byte[] src) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				if (i == src.length - 1) {
					stringBuilder.append(hv);
				} else {
					stringBuilder.append(hv);
				}
			}
			return stringBuilder.toString();
		}
		
		
		public static String bytesToHexStringNoAppendBit(byte[] src) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				/*if (hv.length() < 2) {
					stringBuilder.append(0);
				}*/
				if (i == src.length - 1) {
					stringBuilder.append(hv);
				} else {
					stringBuilder.append(hv);
				}
			}
			return stringBuilder.toString();
		}
		
		
		public static String bytesToString(byte[] src, String split) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = String.valueOf(v);
				if (i == src.length - 1) {
					stringBuilder.append(hv);
				} else {
					stringBuilder.append(hv + split);
				}
			}
			return stringBuilder.toString();
		}
		

		public static String generateHexString(int paramInt) {
			StringBuffer localStringBuffer = new StringBuffer();
			Random localRandom = new Random();
			int i = 16;
			for (int j = 0; j < paramInt; j++) {
				if (j % 2 == 0) {
					localStringBuffer.append("0123456789ABCDEF".charAt(localRandom
							.nextInt(i)));
				} else {
					localStringBuffer.append("0123456789ABCDEF".charAt(localRandom
							.nextInt(i)) + " ");
				}
			}
			return localStringBuffer.toString();
		}

		public static byte[] decodeTripleDES(byte[] data, byte[] key)
				throws NoSuchAlgorithmException, NoSuchPaddingException,
				InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			byte[] keys;
			if (key.length == 16) {
				keys = new byte[24];
				System.arraycopy(key, 0, keys, 0, 16);
				System.arraycopy(key, 0, keys, 16, 8);
			} else {
				keys = key;
			}

			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			SecretKey secretKey = new SecretKeySpec(keys, "DESede");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		}

		public static boolean equals(byte[] b1, byte[] b2) {
			if (b1.length != b2.length) {
				return false;
			}
			for (int i = 0; i < b1.length; i++) {
				if (b1[i] != b2[i]) {
					return false;
				}
			}
			return true;
		}
	/** 
     *  
     * @return 转换为二进制字符�?
     */  
    public static String bytes2BinaryStr(byte[] bArray){  
        String outStr = "";  
        int pos = 0;  
        for(byte b:bArray){  
            //高四�?  
            pos = (b&0xF0)>>4;  
            outStr+=binaryArray[pos];  
            //低四�?  
            pos=b&0x0F;  
            outStr+=binaryArray[pos];  
        }  
        return outStr;  
    }
    /**将二进制转换�?16进制
  �?�?* @param buf
  �?�?* @return
  �?�?*/
    public static String binaryToHexString(byte[] bytes) {
   		StringBuffer sb = new StringBuffer();
   		for (int i = 0; i < bytes.length; i++) {
   			String hex = Integer.toHexString(bytes[i] & 0xFF);
    	 	if (hex.length() == 1) {
    	 		hex = '0' + hex;
    	 	}
    	 	sb.append(hex.toUpperCase());
   		}
   		return sb.toString();
    }
    /**�?16进制转换为二进制
  �?�?* @param hexStr
  �?�?* @return
  �?�?*/
	public static byte[] hexStringToBinary(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	//十六进制转为字符
	public static String hexStringToString(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 给某个日期加几天后的日期 eg:2013-06-28�?+1天是 2013-06-29 �?+3天是2013-07-01
	 * @param date 日期
	 * @param dump 数字
	 * @return
	 */
	public static String getDateByAddSomeDay(Date date,int dump){
		Calendar ca=Calendar.getInstance();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");  //构�?�任意格�?
        String today = sm.format(date);
        String[] timeArray= today.split("-");
        ca.set(Calendar.YEAR,Integer.parseInt(timeArray[0]));
        ca.set(Calendar.MONTH, Integer.parseInt(timeArray[1])-1);
        ca.set(Calendar.DAY_OF_MONTH,Integer.parseInt(timeArray[2]));
        ca.add(Calendar.DAY_OF_MONTH, dump);
        String someDay = sm.format(ca.getTime());
        return someDay;
	}
	
	/**
	 * 生成公钥
	 * @param pubkey
	 * @return
	 * add by yaoyuan
	 */
	public static String generatePublicKey(String pubkey) {
//		BASE64Encoder base64en = new BASE64Encoder();
//		String encode = base64en.encode(hexStringToBinary(pubkey));
//		encode = "-----BEGIN RSA PUBLIC KEY-----" + encode + "-----END RSA PUBLIC KEY-----";
//		if (encode.getBytes().length < 256) {
//			encode = org.apache.commons.lang.StringUtils.rightPad(encode, 256, "0");
//		}
//		return encode;
		return null;
	}
	
	/**
	 * 获取当前时间 精确到毫�?
	 */
	public static String getCurrentTime(){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		 String currentTime = sdf.format(new Date());
		 return currentTime;
	}
	/** 
     * @功能: BCD码转�?10进制�?(阿拉伯数�?) 
     * @参数: BCD�? 
     * @结果: 10进制�? 
     */  
    public static String bcd2Str(byte[] bytes) {  
        StringBuffer temp = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));  
            temp.append((byte) (bytes[i] & 0x0f));  
        }  
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp  
                .toString().substring(1) : temp.toString();  
    }  
  
    /** 
     * @功能: 10进制串转为BCD�? 
     * @参数: 10进制�? 
     * @结果: BCD�? 
     */  
    public static byte[] str2Bcd(String asc) {  
        int len = asc.length();  
        int mod = len % 2;  
        if (mod != 0) {  
            asc = "0" + asc;  
            len = asc.length();  
        }  
        byte abt[] = new byte[len];  
        if (len >= 2) {  
            len = len / 2;  
        }  
        byte bbt[] = new byte[len];  
        abt = asc.getBytes();  
        int j, k;  
        for (int p = 0; p < asc.length() / 2; p++) {  
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {  
                j = abt[2 * p] - '0';  
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {  
                j = abt[2 * p] - 'a' + 0x0a;  
            } else {  
                j = abt[2 * p] - 'A' + 0x0a;  
            }  
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {  
                k = abt[2 * p + 1] - '0';  
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {  
                k = abt[2 * p + 1] - 'a' + 0x0a;  
            } else {  
                k = abt[2 * p + 1] - 'A' + 0x0a;  
            }  
            int a = (j << 4) + k;  
            byte b = (byte) a;  
            bbt[p] = b;  
        }  
        return bbt;  
    }
    /**
     * 去除字符串中的符�?${}形式的子�?.
     * @param
     * @return 去除${}的字符串
     */
    public static String escape$String(String input) {
        return input.replaceAll("\\$\\{[^}]*\\}", "");
    }

    public static String replace$String(String input, String newStr) {
        return input.replaceAll("\\$\\{[^}]*\\}", newStr);
    }

    public static String replace$SpecifyString(String input, String str,
            String newStr) {
    	if(input != null){
    		input = input.replaceAll("\\$\\{" + str + "\\}", newStr);
    	}
        return input;
    }
    
    public static String replace$String(Map<String, Object> map, String src) {
    	if (src != null && map != null) {
    		for (String key : map.keySet()) {
				String value = String.valueOf(map.get(key));
				src = replace$SpecifyString(src, key, value);
			}
    	}
    	return src;
    	
    }
	/**
	 * 生成验证�?
	 * @return
	 */
	public static String createValidateCode(){
		String str = "0,1,2,3,4,5,6,7,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D";
		String str2[] = str.split(",");//将字符串�?,分割
		String code="";
		for(int i=0;i<4;i++){
			int a=(int)(Math.random()*36);
			code=code+str2[a];
		}
		return code;
	}
	/**
	 * 根据身份证计算年�?
	 * @param idNumber
	 * @return
	 */
	public static int calculateAgeByIdNumber(String idNumber){
		int leh = idNumber.length();
		int years =0;
		if (leh == 18) {
			years=Integer.parseInt(idNumber.substring(6, 10));
		}else {
			years = Integer.parseInt("19" + idNumber.substring(6, 8));
		}
		Calendar a=Calendar.getInstance();
		return a.get(Calendar.YEAR)-years;
	}
	/**
	 * 根据身份证计算�?�别
	 * @param idNumber
	 * @return
	 */
	public static int calculateSexByIdNumber(String idNumber){
		int leh = idNumber.length();
		int se =0;
		if (leh == 18) {
			se=Integer.valueOf(idNumber.substring(16,17)) % 2;
		}
		return (se==1?1:2);
	}
	
	/**
	 * 查找数字在数组中得区�?.
	 * @param sortedData 排序数组
	 * @param findValue
	 * @return
	 */
	public static int searchValueSectionInArr(Integer[] sortedData,int findValue) {
		int start = 0;
		int end = 0;
		for (int i = 0; i < sortedData.length; i++) {
			if (findValue >= sortedData[i]) {
				start = i;
			} else {
				end = i;
				break;
			}
		}
		return start;
	}
	
	/** 
     * 循环二分查找数组区间，返回第�?次出现该值的位置 
     * @param sortedData    已排序的数组 
     * @param findValue     �?要找的�?? 
     * @return 值在数组中的位置，从0�?始�?�找不到返回-1 
     */  
    public static int binarySearch(Integer[] sortedData,int findValue)  {  
        int start=0;  
        int end=sortedData.length-1;  
          
        while(start<=end)  {  
            //中间位置  
            int middle=(start+end)>>1;    //相当�?(start+end)/2
     //   System.out.println("middle==>>" + middle);
            //中�??  
            int middleValue=sortedData[middle];  

             if(findValue < middleValue)  {
                //小于中�?�时在中值前面找  
                end = middle-1;
                //如果小于前边的�?? 和前 前边的�??  则继续下�?次查�?
                if (end >= 0) {
                	int end_v =sortedData[end];
                    if (findValue >= end_v) {
                    	return end;
                    }
                } else {
                	return middle;
                }
            }  
            else   {  
                //大于中�?�在中�?�后面找  
                start=middle+1;
                if (start <= sortedData.length -1) {
                	int end_v = sortedData[start];
                    if (findValue < end_v) {
                    	return middle;
                    }
                } else {
                	return middle;
                }
            }  
        }  
        //找不�?  
        return -1;  
    }  
	
	

    public static void main(String[] args) {
		Integer age=calculateAgeByIdNumber("133022198201242396");
		Integer sex=calculateSexByIdNumber("133022198201242396");
		System.out.println("age"+age+">>>>>>sex>>>>>>>>>"+sex);
	}
}
