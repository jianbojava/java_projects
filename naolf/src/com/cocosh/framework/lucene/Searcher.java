package com.cocosh.framework.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 搜索
 * 
 * @author jerry
 */
public class Searcher {

	private IndexWriter indexWriter;
	private IKAnalyzer analyzer;

	public Searcher(IndexWriter indexWriter, IKAnalyzer analyzer) {
		this.indexWriter = indexWriter;
		this.analyzer = analyzer;
	}

	public List<Document> search(String keyWord, String[] fields) {
		try {
			IndexSearcher indexSearcher = getSearcher();
			QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_48,
					fields, analyzer);
			Query query = parser.parse(keyWord);
			// TopDocs 搜索返回的结果
			TopDocs topDocs = indexSearcher.search(query,Integer.MAX_VALUE);// 返回前XXXX条记录
			ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 搜索的结果集合
			List<Document> docs = new ArrayList<Document>();
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 文档编号
				int docID = scoreDoc.doc;
				// 根据文档编号获取文档
				Document doc = indexSearcher.doc(docID);
				docs.add(doc);
			}
			return docs;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 组合查询
	 * 
	 * @param keyWord
	 * @param fields
	 * @return
	 */
	public List<Document> searchGroup(String keyWord, String[] fields) {
		try {
			IndexSearcher indexSearcher = getSearcher();
			BooleanQuery query = new BooleanQuery();
			Term term1 = new Term("ADDRESS", "浙");
			TermQuery tq1 = new TermQuery(term1);
			BooleanClause clause = new BooleanClause(tq1,
					BooleanClause.Occur.SHOULD);
			query.add(clause);

			Term term2 = new Term("weight", "1");
			TermQuery tq2 = new TermQuery(term2);
			BooleanClause clause2 = new BooleanClause(tq2,
					BooleanClause.Occur.MUST);
			query.add(clause2);

			// TopDocs 搜索返回的结果
			TopDocs topDocs = indexSearcher.search(query,Integer.MAX_VALUE);// 返回前XXXX条记录
			ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 搜索的结果集合
			List<Document> docs = new ArrayList<Document>();
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 文档编号
				int docID = scoreDoc.doc;
				// 根据文档编号获取文档
				Document doc = indexSearcher.doc(docID);
				docs.add(doc);
			}
			return docs;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页
	 * 
	 * @param keyWord
	 * @param fields
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<Document> pageSearch(String keyWord, String catid,
			String[] fields, int pageSize, int currentPage, Sort sort,
			BooleanQuery query) throws ParseException {
		try {
			int begin = pageSize * (currentPage - 1);
			List<Document> docs = new ArrayList<Document>();
			IndexSearcher indexSearcher = getSearcher();
			//System.out.println(keyWord == null || catid == null);
			if ((keyWord == null || keyWord.equals(""))&&(catid == null || catid.equals(""))) {
				int size = indexWriter.maxDoc();
				int end = Math.min(begin + pageSize, size);
				for (int i = begin; i < end; i++) {
					Document doc1 = indexSearcher.doc(i);
					docs.add(doc1);
				}
				return docs;
			} else {
				TopDocs topDocs = null;
				if (sort == null) {
					topDocs = indexSearcher.search(query,Integer.MAX_VALUE);
				} else {
					topDocs = indexSearcher.search(query,Integer.MAX_VALUE, sort);
				}
				ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 搜索的结果集合
				int end = Math.min(begin + pageSize, scoreDocs.length);
				// 分页查询
				if (end > 0) {
					for (int i = begin; i < end; i++) {
						// 文档编号
						int docID = scoreDocs[i].doc;
						Document doc = indexSearcher.doc(docID);
						docs.add(doc);
					}
				}
				return docs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取总条数
	 * @param fields
	 * @param query
	 * @return
	 */
	public int getCount(String[] fields, BooleanQuery query) {
		try {
			IndexSearcher indexSearcher = getSearcher();
			// TopDocs搜索返回的结果
			TopDocs topDocs = indexSearcher.search(query,Integer.MAX_VALUE);// 返回前XXXX条记录
			return topDocs.totalHits;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("deprecation")
	private IndexSearcher getSearcher() throws IOException {
		return new IndexSearcher(IndexReader.open(indexWriter.getDirectory()));
	}
	
	
	
	/**-------lucene搜索--------**/
	
	/**
	 * 分页查询
	 * @param query
	 * @param sort
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("finally")
	public Map<String,Object> searchPageByAfter(BooleanQuery query, Sort sort, int pageIndex, int pageSize) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Document> docs = new ArrayList<Document>();
		try {
			IndexSearcher indexSearcher = getSearcher();
			// 先获取上一页的最后一个元素
			ScoreDoc lastSd = getLastScoreDoc(pageIndex, pageSize, query.getClauses().length==0?new MatchAllDocsQuery():query, indexSearcher,sort);
			// 通过最后一个元素搜索下页的pageSize个元素
			TopDocs tds = indexSearcher.searchAfter(lastSd, query.getClauses().length==0?new MatchAllDocsQuery():query, pageSize, sort);
			// 总记录数
			int total=tds.totalHits;
			for (ScoreDoc sd : tds.scoreDocs) {
				docs.add(indexSearcher.doc(sd.doc));
			}
			map.put("total",total);
			map.put("pageCount",total%pageSize==0?total/pageSize:total/pageSize+1);
			map.put("docs", docs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			return map;
		}
	}
	
	/**
	 * 查询全部
	 * @param query
	 * @param sort
	 * @return
	 */
	public List<Document> searchNoPage(BooleanQuery query, Sort sort) {
		List<Document> docs = new ArrayList<Document>();
		try {
			IndexSearcher indexSearcher = getSearcher();
			TopDocs tds = indexSearcher.search(query.getClauses().length==0?new MatchAllDocsQuery():query,Integer.MAX_VALUE,sort);
			for (ScoreDoc sd : tds.scoreDocs) {
				docs.add(indexSearcher.doc(sd.doc));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	/**
	 * 根据页码和分页大小获取上一次的最后一个ScoreDoc
	 */
	private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher,Sort sort) throws IOException {
		if(pageIndex==1)return null;//如果是第一页就返回空
		int num = pageSize*(pageIndex-1);//获取上一页的数量
		TopFieldDocs tds = searcher.search(query, num, sort);
		return tds.scoreDocs[num-1];
	}
	
}
