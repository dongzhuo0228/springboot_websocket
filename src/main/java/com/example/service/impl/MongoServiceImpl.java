package com.example.service.impl;

//@Service
/*public class MongoServiceImpl implements MongoService {
   
	@Autowired
	MongoDatabase db;
    
	
	@Override
	public Map<String, Integer> queryByID(String table, Object Id)
			throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		  BasicDBObject query = new BasicDBObject("_id", Id);
	        //  DBObject接口和BasicDBObject对象：表示一个具体的记录，BasicDBObject实现了DBObject，是key-value的数据结构，用起来和HashMap是基本一致的。
	        FindIterable<Document> iterable = collection.find(query);
	        Map<String,Integer> jsonStrToMap = null;
	        MongoCursor<Document> cursor = iterable.iterator();
	        while (cursor.hasNext()) {
	            Document user = cursor.next();
	            String jsonString = user.toJson();
//	            jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);//这里用到我自己写的方法,主要是包json字符串转换成map格式,为后面做准备,方法放在后面
	        }
	        System.out.println("检索ID完毕");

	        return jsonStrToMap;
	}

	@Override
	public boolean insert(String table, Document doc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String table, BasicDBObject doc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String table, BasicDBObject oldDoc,
			BasicDBObject newDoc) {
		// TODO Auto-generated method stub
		return false;
	}
}	
	*/


