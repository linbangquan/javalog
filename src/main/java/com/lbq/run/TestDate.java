package com.lbq.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestDate {

	public static void main(String[] args) {
//		Date date = new Date(System.currentTimeMillis());
//		System.out.println("date.getTime():"+date.getTime());
//		System.out.println("System.currentTimeMillis():"+System.currentTimeMillis());
//		long a = 123;
//		long b = 50;
//		System.out.println("a / b:"+ a / b);
//		System.out.println("b / a:"+ b / a);
//		System.out.println("a % b:"+ a % b);
//		System.out.println("b % a:"+ b % a);
//		System.out.println();
//		int c = 123;
//		int d = -50;
//		System.out.println("c / d:"+ c / d);
//		System.out.println("d / c:"+ d / c);
//		System.out.println("c % d:"+ c % d);
//		System.out.println("d % c:"+ d % c);
		MyHashMap<String,Object> map = new MyHashMap<>();
		map.put("a","qwe");
		map.put("b","qwe");
		map.put("c","qwe");
		map.put("d","qwe");
		map.put("e","qwe");
		map.put("f","qwe");
		map.put("g","qwe");
		map.put("h","qwe");
		map.put("i","qwe");
		map.put("j","qwe");
		map.put("k","qwe");
		map.put("l","qwe");
		map.put("m","qwe");
		map.put("n","qwe");
		System.out.println("1 << 30="+(1 << 30));
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
//		for(String str : list) {
//			if("c".equals(str)) {
//				list.remove(str);
//			}
//		}
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			if("c".equals(str)) {
				iterator.remove();
			}
		}
		System.out.println(list);
	}

}
