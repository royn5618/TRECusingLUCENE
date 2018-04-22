package com.TREC.Main;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

import com.TREC.Analyzers.CustomTestAnalyzer;
import com.TREC.Analyzers.EnglishStopAnalyzer;
import com.TREC.Analyzers.EnglishSynonymWNAnalyzer;
import com.TREC.Analyzers.NgramAnalyzer;
import com.TREC.Indexing.LuceneIndexer;
import com.TREC.Searching.LuceneSearcher;

public class LuceneSearchEngineStart {
	
		// All the required paths
		static String INPUT_DOC_PATH = "/input_docs/";
		static String INDEX_PATH_OLD = "/indexer_docs";
		static String INDEX_PATH = "/indexer_docs_Std";
		static String INDEX_PATH_WN = "/indexer_docs_WN";
		static String INDEX_PATH_Cust = "/indexer_docs_Cust";
		static String OUTPUT_PATH_STANDARD = "/output/output_standard.txt";
		static String OUTPUT_PATH_NGRAM = "/output/output_ngram_new.txt";
		static String QUERY_PATH = "/queries/CS7IS3-Assignment2-Topics";
		static String OUTPUT_PATH_STANDARD1 = "/output/output_standard1.txt";
		static String OUTPUT_PATH_NGRAM1 = "/output/output_ngram1_finaltry2.txt";
		static String OUTPUT_PATH_NGRAM2 = "/output/output_ngram2_new.txt";
		static String OUTPUT_PATH_NGRAM3 = "/output/output_ngram3_new.txt";
		static String OUTPUT_PATH_WN = "/output/output_ngram_wn.txt";
		static String OUTPUT_PATH_Cust = "/output/output_ngram_cust.txt";
		static String OUTPUT_PATH_EA = "/output/output_ngram_ea.txt";
		static String OUTPUT_PATH_EA_Cust = "/output/output_ngram_ea_cust.txt";
		static String OUTPUT_PATH_EA_WN = "/output/output_ngram_ea_wn.txt";
		static String OUTPUT_PATH_WN_Cust = "/output/output_ngram_wn_cust.txt";
		static String OUTPUT_PATH_CUST_WN = "/output/output_ngram_cust_wn.txt";
		static String OUTPUT_PATH_OLD = "/output/output_ngram_old.txt";
		static String OUTPUT_PATH_TEST = "/output/output_ngram_test.txt";
		

	public static void main(String[] args) throws IOException {
		String systemPath = System.getProperty("user.dir");
		//StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
		//LuceneIndexer.index(systemPath + INPUT_DOC_PATH, systemPath + INDEX_PATH, standardAnalyzer);
		//LuceneSearcher.runQueries(systemPath + QUERY_PATH, standardAnalyzer, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_STANDARD, systemPath + OUTPUT_PATH_STANDARD1);
		//NgramAnalyzer ngramAnalyzer = new NgramAnalyzer();
		//EnglishSynonymCustomAnalyzer synAnalyzer1 = new EnglishSynonymCustomAnalyzer();
		EnglishSynonymWNAnalyzer wn = new EnglishSynonymWNAnalyzer();
		EnglishStopAnalyzer ea = new EnglishStopAnalyzer();
		CustomTestAnalyzer cust = new CustomTestAnalyzer();
		LuceneIndexer.index(systemPath + INPUT_DOC_PATH, systemPath + INDEX_PATH_OLD, ea);
		System.out.println("Done..");
		//LuceneIndexer.index(systemPath + INPUT_DOC_PATH, systemPath + INDEX_PATH, ea);
		//System.out.println("Done..");
		//LuceneIndexer.index(systemPath + INPUT_DOC_PATH, systemPath + INDEX_PATH_WN, wn);
		//System.out.println("Done..");
		//LuceneIndexer.index(systemPath + INPUT_DOC_PATH, systemPath + INDEX_PATH_Cust, cust);
		//System.out.println("Done..");
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, wn, systemPath + INDEX_PATH_WN, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_WN);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, cust, systemPath + INDEX_PATH_Cust, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_Cust);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, ea, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_EA);
		//LuceneSearcher.runQueries(systemPath + QUERY_PATH, ea, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_EA);
		//LuceneSearcher.runQueries(systemPath + QUERY_PATH, synAnalyzer2, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_NGRAM2, systemPath + OUTPUT_PATH_NGRAM3);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, cust, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_EA_Cust);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, wn, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_EA_WN);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, wn, systemPath + INDEX_PATH_Cust, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_WN_Cust);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, cust, systemPath + INDEX_PATH_WN, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_CUST_WN);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, cust, systemPath + INDEX_PATH_OLD, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_OLD);
		LuceneSearcher.runQueries(systemPath + QUERY_PATH, cust, systemPath + INDEX_PATH, systemPath + OUTPUT_PATH_NGRAM, systemPath + OUTPUT_PATH_TEST);
		System.out.println("DONE!!");
	}

}
