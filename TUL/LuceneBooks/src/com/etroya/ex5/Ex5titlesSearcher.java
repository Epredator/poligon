package com.etroya.ex5;

import com.etroya.utils.CreateIndexWrapper;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User: trojnaradam@gmail.com
 * Date: 10.01.15
 * Time: 21:10
 *
 * Celem zadania jest poindeksowanie wszystkich książek wchodzących w skład
 * projektu po tytułach oraz zaimplementowanie prostej wyszukiwarki.
 *
 * More info: http://pduch.kis.p.lodz.pl/EDwI/Eksploracja%20zad%205%20-%20Biblioteka%20Lucene.pdf
 */
public class Ex5titlesSearcher {
  static Logger log = Logger.getLogger(
      Ex5titlesSearcher.class.getName());

  private static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);

  public static void main(String[] args) throws IOException {
    String homeDir = System.getProperty("user.home") + "/gutenbergBooks/";
    log.info("Hello this is an info message about Ex5titlesSearcher program");
    String pathToIndex = homeDir+"luceneTitlesIndex";
    String pathToSourceFolder = homeDir+"pgdvd042010";
    boolean createNewIndex = false;  //if you want build new Index of titles change it for TRUE value (remeber to delete old index)
    String typeOfIndexing = "createOnlyIndexOfTitles";    //!important don't change!! because this class only indexing titles
    new CreateIndexWrapper(pathToIndex, pathToSourceFolder, createNewIndex, typeOfIndexing);
    BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in));
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIndex)));
    IndexSearcher searcher = new IndexSearcher(reader);


    System.out.println("I've got in Lucene index: " + searcher.collectionStatistics("title").docCount() + " titles now, let's search!");
    log.info("I've indexed: " + searcher.collectionStatistics("title").docCount() + " titles");
    String s = "";
    while (!s.equalsIgnoreCase("q")) {
      TopScoreDocCollector collector = TopScoreDocCollector.create(5, true);
      try {
        System.out.println("Enter the search query (q=quit):");
        s = br.readLine();
        log.info("You wrote query: " + s);
        if (s.equalsIgnoreCase("q")) {
          break;
        }
        Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(s);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        System.out.println("Found " + hits.length + " hits.");
        log.info("Found " + hits.length + " hits.");
        for (int i = 0; i < hits.length; ++i) {
          int docId = hits[i].doc;
          Document d = searcher.doc(docId);
          System.out.println((i + 1) + ". " + d.get("title") + " score=" + hits[i].score  + ", Name of file in system: " + d.get("nameOfFile"));
          log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
        }
      } catch (Exception e) {
        System.out.println("Error searching " + s + " : " + e.getMessage());
        log.info("Error searching " + s + " : " + e.getMessage());
      }
    }
  }
}