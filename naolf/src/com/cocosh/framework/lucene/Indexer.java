package com.cocosh.framework.lucene;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

/**
 * 创建索引
 * @author jerry
 */
public class Indexer {

	private IndexWriter indexWriter;

	public Indexer(IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}

	public void creIndex(List<Document> docs) {
		try {
			for (Document doc : docs) {
				indexWriter.addDocument(doc);
			}
			indexWriter.commit();
		} catch (IOException e) {
			try {
				indexWriter.rollback();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void delIndex(String id,String type){
		try {
			if (null!=id) {//根据ID删除
				indexWriter.deleteDocuments(new Term("id",id));
			}else if(null!=type){//根据类型删除
				indexWriter.deleteDocuments(new Term("dataType",type));
			}else {//删除全部
				indexWriter.deleteAll();
			}
			indexWriter.commit();
		} catch (IOException e) {
			try {
				indexWriter.rollback();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void updIndex(Document doc) {
		try {
			indexWriter.updateDocument(new Term("id",doc.get("id")),doc);
			indexWriter.commit();
		} catch (IOException e) {
			try {
				indexWriter.rollback();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
}
