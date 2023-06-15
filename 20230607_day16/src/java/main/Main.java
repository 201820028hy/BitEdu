package java.main;

import java.DTO.BookDTO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void test(ArrayList<BookDTO> list) {
		JSONArray jsonArray = new JSONArray();
		for(BookDTO book : list) {
			JSONObject json = new JSONObject();
			json.put("title", book.getTitle());
			json.put("author", book.getAuthor());
			jsonArray.add(json);
		}
		
		FileWriter fw;
		try {
			fw = new FileWriter(new File("D:/bit/workspace/20230607_day16/src/json/SEARCH_BOOK_LIST.json"));
			fw.write(jsonArray.toJSONString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
