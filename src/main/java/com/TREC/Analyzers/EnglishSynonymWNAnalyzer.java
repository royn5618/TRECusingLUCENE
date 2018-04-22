package com.TREC.Analyzers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.synonym.WordnetSynonymParser;

public class EnglishSynonymWNAnalyzer extends Analyzer{
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
		    SynonymMap mySynonymMap = null;
		    try {
		        mySynonymMap = buildSynonym();
		    } catch (IOException | ParseException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		    result = new SynonymGraphFilter(result, mySynonymMap, false); 
		    return new TokenStreamComponents(source, result);
	}
	
	private SynonymMap buildSynonym() throws IOException, ParseException
	{
	    File file = new File("wn/wn_s.pl");
	    InputStream stream = new FileInputStream(file);
	    Reader rulesReader = new InputStreamReader(stream); 
	    SynonymMap.Builder parser = null;
	    parser = new WordnetSynonymParser(true, true, new StandardAnalyzer(CharArraySet.EMPTY_SET));
	    ((WordnetSynonymParser) parser).parse(rulesReader);         
	    SynonymMap synonymMap = parser.build();
	    return synonymMap;
	}
}
