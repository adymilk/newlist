package linpeng.htmlutil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import linpeng.domain.News;
import linpeng.net.util.NetUtil;

public class GetNewsListService {

	public List<News> getNews(int page){
		System.out.println("");
		List<News> newss = new ArrayList<News>();
		try {
			String response = NetUtil.postAndGetDaet("http://m.baidu.com/news?tn=bdapisearch&word=酷派&pn="+page*20+"&rn=20&t=1386838893136");
			System.out.println(response);
			JSONArray jsonArray = new JSONArray(response);
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				News news = new News();
				news.setTitle(jsonObject.getString("title"));
				news.setSource(jsonObject.getString("author"));
				news.setUrl(jsonObject.getString("url"));
				news.setPhotoUrl(jsonObject.getString("imgUrl"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				news.setDate(dateFormat.format(new Date(jsonObject.getLong("sortTime")*1000)));
				//此处可能包含劳恩斯酷派的新闻（去掉）
				if(!news.getTitle().contains("劳恩斯")){
			    	newss.add(news);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newss;
	}
}
