package org.client.Performance.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import org.client.Performance.core.NavigationTiming;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PerformanceMatrix {

	private static JSONArray jsonList;

	public static ThreadLocal<HashMap<String, JSONArray>> mapthread = new ThreadLocal<HashMap<String, JSONArray>>() {
		@Override
		protected HashMap<String, JSONArray> initialValue() {
			return new HashMap<>();
		}
	};

	@SuppressWarnings("unchecked")
	public static  JSONArray getPerformanceAttribute() {
		//HashMap<String, JSONArray> perfomMapTemp = new HashMap<String, JSONArray>();
		HashMap<String, Long> performanceMatrix = NavigationTiming.loadData();
		jsonList = new JSONArray();
		for (String eventName : performanceMatrix.keySet()) {
			JSONObject JObject = new JSONObject();
			JObject.put(eventName, performanceMatrix.get(eventName));
			jsonList.add(JObject);
		}

		//perfomMapTemp.put(EventName, jsonList);
		return jsonList;

	}

	@SuppressWarnings("unchecked")
	public static void setPerformanceAttribute(String JSONfilePath) {
		jsonList = new JSONArray();
		jsonList.add(mapthread.get());

		try {
			String jsoncontent = jsonList.toJSONString();

			File file = new File(JSONfilePath);

			if (!(file.exists())) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file, true);
			PrintWriter printer = new PrintWriter(writer, true);
			if (file.exists() && file.isFile()) {
				printer.println(jsoncontent);
				printer.flush();
				writer.flush();
				printer.close();
				writer.close();
			} else {
				System.out.println("Please provide a valid path to desitnation Jsonfile");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
