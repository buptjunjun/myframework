package junjun.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressUtil {

	/**
	 * 压缩文件
	 * 返回压缩文件的名字
	 * @param files
	 * @return
	 */
	static public String compress(File src,File target)
	{
		if(src == null || !src.exists() || target == null || !target.exists())
			return null;
		

		ZipOutputStream zipout = null;
		BufferedInputStream bis = null;
		try 
		{
			FileOutputStream fout = new FileOutputStream(target);
			zipout = new ZipOutputStream(fout);
			bis = new BufferedInputStream(new FileInputStream(src));
		    ZipEntry zipEntry = new ZipEntry(src.getName());
		    zipout.putNextEntry(zipEntry);
		    
		    byte [] buffer = new byte[1024];
		    int count = 0;
		    while( (count = bis.read(buffer) )!= -1)
		    {
		    	zipout.write(buffer,0,count);
		    }
			
		} 
		catch (Exception e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		finally
		{
			
			try {
				bis.close();
				zipout.close();
				bis = null;
				zipout = null;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				return null;
			}
			
		}
		return target.getName();
	}
	
	
	/**
	 * 压缩一些列文件
	 * 返回压缩文件的名字
	 * @param files
	 * @return
	 */
	static public String compress(List<File>  src,File target)
	{
		if(src == null  || target == null || !target.exists())
			return null;
		

		ZipOutputStream zipout = null;
		BufferedInputStream bis = null;
		try 
		{
			FileOutputStream fout = new FileOutputStream(target);
			zipout = new ZipOutputStream(fout);
			for(File file : src)
			{
				bis = new BufferedInputStream(new FileInputStream(file));
			    ZipEntry zipEntry = new ZipEntry(file.getName());
			    zipout.putNextEntry(zipEntry);
			    
			    byte [] buffer = new byte[1024];
			    int count = 0;
			    while( (count = bis.read(buffer) )!= -1)
			    {
			    	zipout.write(buffer,0,count);
			    }
			    bis.close();
			}
			
		} 
		catch (Exception e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		finally
		{			
			try 
			{
				zipout.close();
				bis = null;
				zipout = null;
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
				return null;
			}		
		}		
		return target.getName();
	}

}
