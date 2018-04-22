package com.TREC.Analyzers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SolrSynonymParser;
import org.apache.lucene.analysis.synonym.SynonymFilter;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.util.ResourceLoader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.Version;


public class CustomTestAnalyzer extends Analyzer {
	
	public static SynonymMap synmap;

	public CustomTestAnalyzer() throws IOException{
		ArrayList<String> list  = new ArrayList<String>();
		SynonymMap.Builder builder = new SynonymMap.Builder(true);
		  try (BufferedReader br = new BufferedReader(new FileReader("synonym/synonyms_corrected.txt")))
			{
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					list.add(sCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		  String line = list.get(0);
		  String[] synSets = line.split(";");
		  int splitIndex = 0;
		  for (String synSet : synSets)
		  {
			  splitIndex = synSet.indexOf("=>");
			  if(splitIndex != -1){
				  String s1 = synSet.substring(0,splitIndex);
				  String s2 = synSet.substring(splitIndex, synSet.length());	
				  builder.add(new CharsRef(s1), new CharsRef(s2), true);
			  }
		  }
		  synmap = builder.build();
		  System.out.println("================ Custom analyzer built the synonym map =============== ");
	}
	
	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		final Tokenizer source = new StandardTokenizer();
	    TokenStream result = new StandardFilter(source);
	    result = new EnglishPossessiveFilter(result);
	    result = new LowerCaseFilter(result);
	    List<String> stopList = Arrays.asList("i" , 
	    		"me" , 
	    		"my" , 
	    		"myself" , 
	    		"we" , 
	    		"our" , 
	    		"ours" , 
	    		"ourselves" , 
	    		"you" , 
	    		"your" , 
	    		"yours" , 
	    		"yourself" , 
	    		"yourselves" , 
	    		"he" , 
	    		"him" , 
	    		"his" , 
	    		"himself" , 
	    		"she" , 
	    		"her" , 
	    		"hers" , 
	    		"herself" , 
	    		"it" , 
	    		"its" , 
	    		"itself" , 
	    		"they" , 
	    		"them" , 
	    		"their" , 
	    		"theirs" , 
	    		"themselves" , 
	    		"what" , 
	    		"which" , 
	    		"who" , 
	    		"whom" , 
	    		"this" , 
	    		"that" , 
	    		"these" , 
	    		"those" , 
	    		"am" , 
	    		"is" , 
	    		"are" , 
	    		"was" , 
	    		"were" , 
	    		"be" , 
	    		"been" , 
	    		"being" , 
	    		"have" , 
	    		"has" , 
	    		"had" , 
	    		"having" , 
	    		"do" , 
	    		"does" , 
	    		"did" , 
	    		"doing" , 
	    		"a" , 
	    		"an" , 
	    		"the" , 
	    		"and" , 
	    		"but" , 
	    		"if" , 
	    		"or" , 
	    		"because" , 
	    		"as" , 
	    		"until" , 
	    		"while" , 
	    		"of" , 
	    		"at" , 
	    		"by" , 
	    		"for" , 
	    		"with" , 
	    		"about" , 
	    		"against" , 
	    		"between" , 
	    		"into" , 
	    		"through" , 
	    		"during" , 
	    		"before" , 
	    		"after" , 
	    		"above" , 
	    		"below" , 
	    		"to" , 
	    		"from" , 
	    		"up" , 
	    		"down" , 
	    		"in" , 
	    		"out" , 
	    		"on" , 
	    		"off" , 
	    		"over" , 
	    		"under" , 
	    		"again" , 
	    		"further" , 
	    		"then" , 
	    		"once" , 
	    		"here" , 
	    		"there" , 
	    		"when" , 
	    		"where" , 
	    		"why" , 
	    		"how" , 
	    		"all" , 
	    		"any" , 
	    		"both" , 
	    		"each" , 
	    		"few" , 
	    		"more" , 
	    		"most" , 
	    		"other" , 
	    		"some" , 
	    		"such" , 
	    		"no" , 
	    		"nor" , 
	    		"not" , 
	    		"only" , 
	    		"own" , 
	    		"same" , 
	    		"so" , 
	    		"than" , 
	    		"too" , 
	    		"very" , 
	    		"s" , 
	    		"t" , 
	    		"can" , 
	    		"will" , 
	    		"just" , 
	    		"don" , 
	    		"should" , 
	    		"now",
	    		"its" , 
				"total" , 
				"time" , 
				"being" , 
				"each" ,  
				"managed" , 
				"which" ,  
				"type" , 
				"ensure" , 
				"pay" , 
				"that" , 
				"they" , 
				"also" , 
				"some" , 
				"though" , 
				"said" , 
				"over" , 
				"still" , 
				"meanwhile" , 
				"would" ,  
				"certainly" , 
				"whole" , 
				"made" , 
				"make" , 
				"follows");
	    result = new StopFilter(result, StopFilter.makeStopSet(stopList));
	    result = new PorterStemFilter(result);
	    result = new SynonymGraphFilter(result, synmap, false);	
	    return new TokenStreamComponents(source, result);
	}
}
