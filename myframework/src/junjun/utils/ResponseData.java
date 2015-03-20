package junjun.utils;

public class ResponseData {
	
	private int httpStatus = -1;
	private String data;
	
	public ResponseData() {
		// TODO 自动生成的构造函数存根
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() 
	{
		return httpStatus +" , " +data;
	}
	
}
