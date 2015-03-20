package junjun.web.struts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import junjun.utils.L;

public class PrepareFilter implements Filter
{

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException
	{
		L.logger.debug("prepare filter");
		try
		{
			arg2.doFilter(arg0, arg1);	
		}
		finally
		{
			//ConnectionPool.getInstance().free();
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

	public void destroy()
	{
		// TODO Auto-generated method stub

	}
}
