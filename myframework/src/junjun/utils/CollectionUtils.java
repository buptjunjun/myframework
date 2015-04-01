package junjun.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
	
	/**
	 * 从collection中
	 * @param qs
	 * @param attrName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T,M> Collection<T> getAttr(Collection<M> items, String attrName) throws Exception
	{
		if(items == null || items.size() == 0) return null;
		
		Collection<T> ret = new ArrayList<>();
		for(M item : items)
		{
			
				Field field;			
				field = item.getClass().getDeclaredField(attrName);		
				field.setAccessible(true);			
				Object obj = field.get(item);
				ret.add((T) obj);							
		}	
		
		return ret;
	}
	
	public static <M> boolean setAttr(List<M> qs,String attrName,Object value)
	{
		if(qs == null || qs.size() == 0) return true;
		
		for(M q : qs)
		{
			try {
				Field field;			
				field = q.getClass().getDeclaredField(attrName);		
				field.setAccessible(true);			
				field.set(q, value);			
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		}	
		return  true;
	}
	
	static class User 
	{ 
		int id;
		String name;
		public User(int id, String name)
		{
			this.id = id;
			this.name = name;
		}
		
		@Override
		public String toString() {
			// TODO 自动生成的方法存根
			return id+","+name;
		}
	}
	public static void main(String args [] ) throws Exception
	{
		List<User> users  = Arrays.asList(new User(1,"junjun"), new User(2,"bobo"));
		System.out.println(users);
		
		Collection<Integer> userIds = getAttr(users, "id");
		System.out.println(userIds);	
		
		setAttr(users, "id", 11111);
		System.out.println(users);
	}
	
}
