package junjun.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressUtil 
{	
	static public void compress(File src , File target) throws Exception
	{		
		if(src == null || !src.exists())
		{
			return ;
		}	
		
		ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(target));
		compress(src, zipout, "",true);
		zipout.flush();
		zipout.close();
		zipout = null;
		return ;
		
	}
		
	/**
	 * 压缩文件
	 * 返回压缩文件的名字
	 * @param files
	 * @return
	 * @throws Exception 
	 */
	static public void compress(File src,ZipOutputStream zipout,String baseName, boolean flag) throws Exception
	{
		
		if(src == null || !src.exists())
			return;
		

		if(src.isDirectory())
		{	
			flag = false;
			if(baseName.trim().endsWith("/") == false)
				baseName += "/";
			
			for(File f : src.listFiles())
			{
				compress(f, zipout, baseName+f.getName(), flag);
			}			
		}
		else
		{
			BufferedInputStream bis = null;
			bis = new BufferedInputStream(new FileInputStream(src));
			if(flag == true)
			{
				baseName = src.getName();
				flag = false;
			}
			
		    ZipEntry zipEntry = new ZipEntry(baseName);
		    zipout.putNextEntry(zipEntry);
		    
		    byte [] buffer = new byte[1024];
		    int count = 0;
		    while( (count = bis.read(buffer) )!= -1)
		    {
		    	zipout.write(buffer,0,count);
		    }			
		    
			bis.close();
			bis = null;
			zipout.flush();
		}		
	}
	
	public static void main(String [] args)
	{
		try {
			compress(new File("/User/junjun/bird.jpg"),new File("/User/junjun/bird.jpg.gz"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
