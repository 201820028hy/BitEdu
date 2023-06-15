package work.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import work.dto.BookDTO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<BookDTO> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			BookDTO book = new BookDTO(i, "title "+i, "content "+i, "author "+i, "rental "+i, "display "+i, "category "+i);
			list.add(book);
		}
		
		Main main = new Main();
		main.test(list);
	}
	
	public void test(ArrayList<BookDTO> list) {
		JSONArray jsonArray = new JSONArray();
//		for(BookDTO book : list) {
//			JSONObject json = new JSONObject();
//			json.put("title", book.getTitle());
//			json.put("author", book.getAuthor());
//			jsonArray.add(json);
//		}
		
		FileWriter fw;
		try {
			//fw = new FileWriter(new File("D:/bit/workspace/20230607_day16/src/json/SEARCH_BOOK_LIST.json"));
			fw = new FileWriter(new File("D:/이혜윤/visualStudio/test/src/json/jsonTEST.json"));
			fw.write(JSONArray.toJSONString(list));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
