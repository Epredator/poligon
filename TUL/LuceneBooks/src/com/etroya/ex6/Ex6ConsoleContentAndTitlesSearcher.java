package com.etroya.ex6;

import com.etroya.utils.CreateIndexWrapper;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
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
 * <p>
 * Należy rozbudować program z zadania poprzedniego. Celem jest dodanie indeksowania dla
 * całych dokumentów dzieląc je odpowiednio na tytuł i treść (należy pominąć nagłówek dodawany
 * przez projekt Gutenberg). Należy wykonać interfejs wyszukiwarki książek po tytułach i treści
 * dokumentu.
 * <p>
 * More info: http://pduch.kis.p.lodz.pl/EDwI/Eksploracja%20zad%206%20-%20Biblioteka%20Lucene_2.pdf
 */

public class Ex6ConsoleContentAndTitlesSearcher {

  private static String homeDir = System.getProperty("user.home") + "/gutenbergBooks";
  private static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
  private static String pathToIndex = homeDir + "/luceneContentAndTitlesIndex";
  private static String pathToSrcFolder = homeDir + "/pgdvd042010";
  static Logger log = Logger.getLogger(
      Ex6ConsoleContentAndTitlesSearcher.class.getName());

  public static void main(String[] args) throws IOException {
    boolean createNewIndex = true;
    boolean searchBooksByConsole = true;
    log.info("Hello this is an info message about Ex6contentAndTitlesSearcher program");
    String createIndexOfContent = "createIndexOfContent";    //!important here we declare what type of indexing will be

    new CreateIndexWrapper(pathToIndex, pathToSrcFolder, createNewIndex, createIndexOfContent);

    if (searchBooksByConsole == true) {
      Ex6ConsoleContentAndTitlesSearcher searcher = new Ex6ConsoleContentAndTitlesSearcher();
      searcher.searchBooksByConsole();
    }
  }

  private void searchBooksByConsole() throws IOException {
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
        Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse( "\""+ s +"\"~0");
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        // 4. display results
        System.out.println("Found " + hits.length + " hits.");
        log.info("Found " + hits.length + " hits.");
        for (int i = 0; i < hits.length; ++i) {
          int docId = hits[i].doc;
          Document d = searcher.doc(docId);
          System.out.println((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
          log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
        }
      } catch (Exception e) {
        System.out.println("Error searching " + s + " : " + e.getMessage());
        log.info("Error searching " + s + " : " + e.getMessage());
      }
    }
  }

}
