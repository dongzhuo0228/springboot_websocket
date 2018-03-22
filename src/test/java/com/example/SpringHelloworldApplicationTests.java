package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;
import com.example.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringHelloworldApplicationTests {
	@Autowired
	private SolrClient client;
	@Autowired
	private JavaMailSender  mailSender;
	@Autowired
	UserMapper u;
	/*@Test
	public void sendSimpleMail()throws Exception{
		SimpleMailMessage sim = new SimpleMailMessage();
		sim.setFrom("565148905@qq.com");
		sim.setTo("1611466955@qq.com");
		sim.setSubject("主题：简单邮件");
		sim.setText("测试邮件内容");
		mailSender.send(sim);
		System.out.println("发送完毕");
	}*/
	@Test
	public void okHttp()throws Exception{
		OkHttpClient client = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder()
                .get()
                .url("https://www.baidu.com")
                .build();
        //通过client发起请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                 String str = response.body().string();
                 System.out.println(str);
                }
    
            }
        });


		
	}
	public void setIndex() throws Exception{
		List<User> selectList = u.getAll();
        client.addBeans(selectList);
        client.commit();
	}
	@Test
	 public void SetSolr() {
	        try {
	            //创建索引
	           /* SolrInputDocument solrInputFields=new SolrInputDocument();
//	            solrInputFields.addField("id", "1");
//	            solrInputFields.addField("name", "admin");
//	            solrInputFields.addField("password", "11111");
	            client.add(solrInputFields);
	            client.commit();*/


	            //基于实体类创建索引
	           /* List<User> selectList = u.getAll();
	            client.addBeans(selectList);
	            client.commit();*/

	            //查询第一种方式
	            /*ModifiableSolrParams params =new ModifiableSolrParams();
	            params.add("q","name:哈登");
	            params.add("ws","json");
	            params.add("start","0");
	            params.add("rows","10");
	            QueryResponse queryResponse=client.query(params);
	            System.out.println("queryResponse"+queryResponse);
*/

	            //查询第二种方式
	            int page = 1;
	            int rows = 10;

	            SolrQuery solrQuery = new SolrQuery(); // 构造搜索条件
	            solrQuery.set("q", "name:登");
	            solrQuery.setSort("id", SolrQuery.ORDER.desc);
//	            solrQuery.setQuery("name:哈登");// 搜索关键词
	            solrQuery.setHighlight(true); // 开启高亮
	            solrQuery.addHighlightField("name"); // 高亮字段
	            solrQuery.setHighlightSimplePre("<font color='red'>"); // 高亮单词的前缀
	            solrQuery.setHighlightSimplePost("</font>"); // 高亮单词的后缀
	            // 设置分页
	            solrQuery.setStart((Math.max(page, 1) - 1) * rows);
	            solrQuery.setRows(rows);
	            QueryResponse  docs = client.query(solrQuery);
	            Map<String, Map<String, List<String>>> highlightresult = docs.getHighlighting();
	            System.out.println("高亮的值||"+   highlightresult);
	            List<User> userS = new ArrayList<User>();
	            SolrDocumentList solrDocuments=docs.getResults();
	            for(SolrDocument sd : solrDocuments){
	            	Object id =sd.get("id");
	            	User user = new User();
		            if (highlightresult.get(id) != null && highlightresult.get(id).get("name") != null) {
		            	user.setName(highlightresult.get(id).get("name").get(0));
		            	userS.add(user);
		            }
	            	System.out.println("搜索返回值id:"+sd.get("id"));
	                System.out.println("搜索返回值name:"+sd.get("name"));
	            }
	            
	            
	            System.out.println("高亮的值"+userS);

	            //List<User> userList=docs.getBeans(User.class);//直接转实体(!这个B有问题，只有用上面的循环赋值比较靠谱)


	            //删除索引
//	            client.deleteByQuery("id:2");
//	            client.commit();

	            //通过版本号删除索引
//	            client.deleteById("123123");
//	            client.commit();

	        } catch (SolrServerException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
//	        return true;
	    }
	
	
	
	

}
