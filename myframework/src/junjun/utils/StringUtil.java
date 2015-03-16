package junjun.utils;
import java.util.regex.Pattern;
/**
 * 字符串工具类
 * @author Admin
 *
 */
public class StringUtil
{
	public static boolean isIp(String s)
	{
		if (s == null)
		{
			return false;
		}
		String rexp = "([0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		return Pattern.compile(rexp).matcher(s).matches();
	}
	public static boolean isEmpty(String s)
	{
		return s == null || "".equals(s.trim());
	}
	
	public static boolean isEmail(String s)
	{
		return s != null && Pattern.matches(".+@.+", s);
	}

	public static boolean isPhone(String s)
	{
		return s != null && Pattern.matches("1[0-9]{10}", s);
	}

	// 根据Unicode编码完美的判断中文汉字和符号
	public static boolean isChinese(char c)
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
		{
			return true;
		}
		return false;
	}
	
	public static boolean isLegalUnicodeCharacter(char c)
	{
		if (isChinese(c))
		{
			return true;
		}
		
		int v = (int)c;
		if (v <= 256 && v >= 128)
		{
			return false;
		}
		
		return true;
	}
	
	public static String toLegalUnicodeCharacter(String s)
	{
		if (s == null)
		{
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		char[] ch = s.toCharArray();
		for (int i = 0; i < ch.length; i++)
		{
			char c = ch[i];
			if (isLegalUnicodeCharacter(c))
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// 完整的判断中文汉字和符号
	public static boolean isChinese(String strName)
	{
		if (strName == null)
		{
			return true;
		}
		
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++)
		{
			char c = ch[i];
			if (!isChinese(c))
			{
				return false;
			}
		}
		return true;
	}
	
	// 完整的判断中文汉字和符号
	public static String toChinese(String strName)
	{
		if (strName == null)
		{
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++)
		{
			char c = ch[i];
			if (isChinese(c))
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static boolean isLegalUnicodeString(String str)
	{
		if(str == null || " ".equals(str)) return true;
		char [] cs = str.toCharArray();
		for (char c : cs)
		{
			if(!isLegalUnicodeCharacter(c))
				return false;
		}
		
		return true;
		
	}
	public static void main(String[] args)
	{
		String ss = "619,constraint,n. éå¶;  å¼ºè¿«, ææ; å¼ºå¶,è¿æ¡é¾å­éå¶äºæçæ´»å¨è½åã,con一起+strain拉紧，压+t → 约束；强迫";
//		String ss = "616,constitutional,adj.宪法的；本质的；体质上的；保健的n.保健散步；保健运动,美国有一个宪法体制。,con 共同 + stitut=stitute设立+ ion表名词，行为 + al 属于...的 → adj. 宪法的；本质的；保健的 n. 保健散步 ";
		char[] cs = ss.toCharArray();
		for (char c : cs)
		{
			System.out.println(c + ":" + (int)c + ":" + isLegalUnicodeCharacter(c));
		}
		System.exit(0);
		
		String[] testIp = new String[]{"127.0.0.1", "0.0.0.0", "255.255.255.255", "256.0.0.0", "9"};
		for (String s : testIp)
		{
			System.out.println(s + " " + isIp(s));
		}
		String[] testEmail = new String[]{"a", "", null, "@a", "a@", "@", "a@b"};
		for (String s : testEmail)
		{
			System.out.println(s + " " + isEmail(s));
		}
		
		String[] testPhone = new String[]{"1", "", null, "1234567890", "123456789012", "22222222222", "15120095680"};
		for (String s : testPhone)
		{
			System.out.println(s + " " + isPhone(s));
		}
	}

}
