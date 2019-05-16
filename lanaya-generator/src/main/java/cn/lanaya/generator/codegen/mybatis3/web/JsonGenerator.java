//package cn.lanaya.generator.codegen.mybatis3.web;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import com.alibaba.fastjson.JSONObject;
//
//import cn.lanaya.generator.dom.xml.XmlMapperKeys;
//import cn.lanaya.generator.table.FullyQualifiedTable;
//import cn.lanaya.generator.table.IntrospectedColumn;
//import cn.lanaya.generator.table.IntrospectedTable;
//import cn.lanaya.generator.util.StringTools;
//import cn.lanaya.json.postman.PostmanCollection;
//import cn.lanaya.json.postman.PostmanFolder;
//import cn.lanaya.json.postman.PostmanHeaderData;
//import cn.lanaya.json.postman.PostmanRequest;
//
//public class JsonGenerator {
//
//	public static void main(String[] args) {
//		String s = "bbd9e091-93e2-4dcc-8130-3f201f626200";
//		System.out.println(s.length());
//		UUID uuid = UUID.randomUUID();
//		System.out.println(uuid.toString());
//		System.out.println(uuid.toString().length());
//	}
//
//	public void generateJson(List<IntrospectedTable> introspectedTables, String javaRoot) {
//		String collectionId = UUID.randomUUID().toString();
//		List<String> folderKeys = new ArrayList<>();
//		List<PostmanFolder> folders = new ArrayList<>();
//		boolean flag = false;
//		Map<String, PostmanRequest> requests = new HashMap<>();
//		for (IntrospectedTable table : introspectedTables) {
//			if (flag) {
//				break;
//			}
//			String remarks = table.getRemarks();
//			List<PostmanHeaderData> headerData = getHeaderData(table);
//			String folderId = UUID.randomUUID().toString();
//			folderKeys.add(folderId);
//			PostmanFolder folder = new PostmanFolder();
//			folder.setId(folderId);
//			folder.setName(table.getRemarks());
//			folder.setCollection(collectionId);
//			folder.setCollectionId(collectionId);
//			folder.setFolderId(folderId);
//			folders.add(folder);
//			FullyQualifiedTable qualifiedTable = table.getFullyQualifiedTable();
//			String path = StringTools.lowerFirstChar(qualifiedTable.getDomainObjectName());
//			Map<String, PostmanRequest> map = getRequestUrl(path, remarks, folderId, headerData, collectionId, table);
//			requests.putAll(map);
//			flag = true;
//		}
//		PostmanCollection collection = new PostmanCollection();
//		collection.setId(collectionId);
//		collection.setName("视综3.0test");
//		collection.setFolders_order(folderKeys);
//		collection.setFolders(folders);
//		collection.setRequests(new ArrayList<>(requests.values()));
//		System.out.println(collection.toString());
////		System.out.println(JSONObject.toJSONString(collection));
//	}
//
//	private Map<String, PostmanRequest> getRequestUrl(String path, String remark, String folderId,
//			List<PostmanHeaderData> headerData, String collectionId, IntrospectedTable table) {
//		Map<String, PostmanRequest> map = new HashMap<>();
//		for (RequestEnum r : RequestEnum.values()) {
//			String uuid = UUID.randomUUID().toString();
//			PostmanRequest request = new PostmanRequest();
//			request.setId(uuid);
//			request.setName(remark + " - " + r.getName());
//			request.setUrl("{{url}}:{{port}}/" + path + "/" + r.getUrl());
//			request.setDataMode("raw");
//			request.setMethod("POST");
//			request.setFolder(folderId);
//			request.setHeaderData(headerData);
//			request.setCollectionId(collectionId);
//			String rawModeData = "";
//			Map<String, String> m = new HashMap<>();
//			int type = r.getType();
//			if (type == 1) {// 入参为主键
//				for (IntrospectedColumn c : table.getPrimaryKeyColumns()) {
//					m.put(c.getJavaProperty(), c.getQualifiedJavaType().getBaseShortName());
//				}
//			} else if (type == 2) {
//				for (IntrospectedColumn c : table.getBaseColumns()) {
//					m.put(c.getJavaProperty(), c.getQualifiedJavaType().getBaseShortName());
//				}
//			} else {
//				for (IntrospectedColumn c : table.getAllColumns()) {
//					m.put(c.getJavaProperty(), c.getQualifiedJavaType().getBaseShortName());
//				}
//			}
//			rawModeData = JSONObject.toJSONString(m);
//			request.setRawModeData(rawModeData.replace("\"", "\\\""));
//			;
//			map.put(uuid, request);
//		}
//		return map;
//	}
//
//	private List<PostmanHeaderData> getHeaderData(IntrospectedTable table) {
//		List<PostmanHeaderData> res = new ArrayList<>();
//		PostmanHeaderData data = new PostmanHeaderData();
//		data.setKey("Content-Type");
//		data.setName("Content-Type");
//		data.setValue("application/json");
//		data.setType("text");
//		res.add(data);
//		PostmanHeaderData bean = new PostmanHeaderData();
//		bean.setKey("bean");
//		bean.setValue(table.getFullyQualifiedTable().getFullViewObjectName());
//		bean.setType("text");
//		bean.setEnabled(true);
//		res.add(bean);
//		return res;
//	}
//
//	enum RequestEnum {
//		SELECT_BY_CLAUSE(XmlMapperKeys.SELECT_BY_CLAUSE_SQL, "根据条件查询", 2),
//		SELECT_BY_PRIMARY_KEY(XmlMapperKeys.SELECT_BY_PRIMARY_KEY_SQL, "根据id查询", 1),
//		INSERT_SELECTIVE(XmlMapperKeys.INSERT_SELECTIVE_SQL, "新增", 3),
//		UPDATE_BY_PRIMARY_KEY_SELECTIVE(XmlMapperKeys.UPDATE_BY_PRIMARY_KEY_SELECTIVE_SQL, "更新", 1),
//		DELETE_BY_PRIMARY_KEY(XmlMapperKeys.DELETE_BY_PRIMARY_KEY_SQL, "根据id删除", 1);
//
//		private String url;
//		private String name;
//		private int type;
//
//		private RequestEnum(String url, String name, int type) {
//			this.url = url;
//			this.name = name;
//			this.type = type;
//		}
//
//		public String getUrl() {
//			return url;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public int getType() {
//			return type;
//		}
//	}
//}
