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
	
	
	static class User 
	{ 
		int id;
		String name;
		public User(int id, String name)
		{
			this.id = id;
			this.name = name;
		}
	}
	public static void main(String args [] ) throws Exception
	{
		List<User> users  = Arrays.asList(new User(1,"junjun"), new User(2,"bobo"));
		Collection<Integer> userIds = getAttr(users, "id");
		System.out.println(userIds);
	}
	
}
