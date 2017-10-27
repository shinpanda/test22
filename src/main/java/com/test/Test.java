package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int x = 0;
		int y = 0;
		HashMap<String, Integer> student = new HashMap<String, Integer>();
		// 데이터로 키값을 학생 이름으로 셋팅
		// 나중에 랜덤 돌린 뒤에 해당값으로 value 셋팅
		
		
		int s = 0;
		
		while(true) {
			x = scan.nextInt();
			y = scan.nextInt();
			
			if(x>0&&x<10&&y>0&&y<10) {
				break;
			}
		}
		System.out.println(x);
		System.out.println(y);
		
		//s = arr.length * arr[0].length; 
		
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<x*y; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		
		for(Integer i : list)
			System.out.print(i+" ");
		System.out.println();
		
		
		/*for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				arr[i][j] = list.get(i);
			}
		}*/
		
		for(int i=0; i<x*y; i++) {
			student.put("student ["+i+"]", list.get(i));
		}
		
		Iterator it = sortByValue(student).iterator();
		
		int count = 0;
		while(it.hasNext()) {
			String stu = (String)it.next();
			++count;
			System.out.print(stu +" : "+ student.get(stu));
			
			if(count == y) {
				System.out.println();
				count = 0;
			} else
				System.out.print(" ");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> sortByValue(Map<String, Integer> map) {
		List<String> list = new ArrayList<String>();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return((Comparable<Object>)v2).compareTo(v1);
			}
		});
		Collections.reverse(list);
		return list;
	}

}