package com.SDN.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        
        JSONObject obj2 = new JSONObject();
        obj2.put("name", "mkyong.com");
        obj2.put("age", new Integer(100));

        obj.put("message",obj2);
        System.out.print(obj);

	}

}
