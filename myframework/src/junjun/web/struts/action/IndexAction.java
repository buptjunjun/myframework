package junjun.web.struts.action;

import java.util.HashMap;
import java.util.Map;

public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 2229451719601758150L;
	
	private Map<String, String> root = new HashMap<String,String>();
		
	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}


	public Map<String, String> getRoot() {
		return root;
	}


	public void setRoot(Map<String, String> root) {
		this.root = root;
	}
	
	
}
