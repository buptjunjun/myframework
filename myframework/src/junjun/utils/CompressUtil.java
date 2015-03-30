package junjun.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
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
	

	/**
	 * GUN ZIP格式压缩文件
	 * @param src
	 * @param to
	 * @throws Exception
	 */
	static public void compressGZ(File src , String to) throws Exception
	{		
		if(src == null || !src.exists())
		{
			return ;
		}	
	
		FileInputStream fin=new FileInputStream(src); 
		FileOutputStream fout=new FileOutputStream(to); 
		 
		GZIPOutputStream gzout=new GZIPOutputStream(fout); 
		byte[] buf=new byte[1024];//设定读入缓冲区尺寸 
		int num; 

		while ((num=fin.read(buf)) != -1) 
		{ 
			gzout.write(buf,0,num); 
		} 
		gzout.close();
		fout.close(); 
		fin.close(); 
	}
	
	/**
	 * GUN ZIP格式解压缩文件
	 * @param src
	 * @param to
	 * @throws Exception
	 */
	public static void uncompressGZ(File src, File to) throws FileNotFoundException, IOException
	{
	    byte[] buffer = new byte[1024];
    	GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(src));
    	FileOutputStream out = new FileOutputStream(to);
        int len;
        while ((len = gzis.read(buffer)) > 0) {
        	out.write(buffer, 0, len);
        }
        gzis.close();
    	out.close();

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
