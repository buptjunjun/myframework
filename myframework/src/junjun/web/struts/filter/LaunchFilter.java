package junjun.web.struts.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import junjun.utils.L;

import org.slf4j.MDC;

public class LaunchFilter implements Filter
{

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException
	{
		long start = System.currentTimeMillis();
		HttpServletRequest hsr= (HttpServletRequest)arg0;
		String url = hsr.getRequestURL().toString();
		if (hsr.getQueryString() != null)
		{
			url += "?" + hsr.getQueryString();
		}
		hsr.setAttribute("currentUrl", url);

		try
		{
			// 把ip放到日志里
			MDC.put("ip", arg0.getRemoteAddr());
			
			arg2.doFilter(arg0, arg1);
		}
		catch (Exception e)
		{
			String msg = "process request error! addr[" + url + "] ";
			L.logger.warn(msg, e);
			arg0.getRequestDispatcher("/500.jsp").forward(arg0, arg1);
		}
		finally
		{
			long end = System.currentTimeMillis();
			Map<String, String[]> ps = hsr.getParameterMap();
			StringBuffer args = new StringBuffer();
			boolean first = true;
			for (Map.Entry<String, String[]> p : ps.entrySet())
			{
				if (!first)
				{
					args.append("&");
				}
				else
				{
					first = false;
				}
				
				String key = p.getKey();
				String[] value = p.getValue();
				if (value != null)
				{
					for (String v : value)
					{
						args.append(key + "=" + v);
					}
				}
			}
			
			String msg = String.format("req_addr[%s] args[%s] proc_time[%d]",  url, args.toString(), end - start);
			L.logger.info(msg);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException
	{
		L.logger.info("LaunchFilter init!");
		
		//Configure.getInstance();
	}

	public void destroy()
	{
		//Configure.getInstance().destroy();
		
		L.logger.info("LaunchFilter destroy!");
	}
}
