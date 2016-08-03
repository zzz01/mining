package com.hust.datahandle.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordSegment {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(WordSegment.class);

	private static WordSegment ws;

	public WordSegment() {
		try {
			loadStopWord();
		} catch (Exception e) {
			logger.error("load stopwords dictionary fails \t" + e.toString());
		}
	}

	private void loadStopWord() throws IOException {
	    Properties pro = new Properties();
	    String path = this.getClass().getResource("/").getPath();
	    FileInputStream in = new FileInputStream(path+"library.properties");
	    pro.load(in);
	    String stopwords = pro.getProperty("stopwords");
	    in.close();
		FileReader fr = new FileReader(stopwords);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			FilterModifWord.insertStopWord(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
	}

	public String[] parse(String str) {
		List<Term> res = ToAnalysis.parse(str);
		res = FilterModifWord.modifResult(res);
		String[] words = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			words[i] = res.get(i).getName();
		}
		return words;
	}

	public static WordSegment getInstance() {
		if (ws == null) {
			ws = new WordSegment();
		}
		return ws;
	}
}
