package com.etroya.utils;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * User: trojnaradam@gmail.com
 * Date: 31.01.15
 * Time: 19:53
 * This code try to open .txt files from project Gutenberg and save titles into the Lucene Index
 */
public class TitlesOfGutenbergIndexer {
  static Logger log = Logger.getLogger(
      TitlesOfGutenbergIndexer.class.getName());
  private ArrayList<File> queue = new ArrayList<File>();
  private boolean checkToUnzipFiles = false;
  String pathToSrcFolder = null;
  String pathToIndex = null;
  Integer originalNumDocs = null;
  Integer newNumDocs = null;
  int counter = 1;
  int overAllcounter = 1;

  /**
   * Indexes a file or directory
   */
  public void indexFileOrDirectory(String pathToIndexParam, String pathToSourceFolder, String typeOfIndexing) throws IOException, ParseException {
    pathToSrcFolder = pathToSourceFolder;
    pathToIndex = pathToIndexParam;
    long startTime = System.currentTimeMillis();
    addFiles(new File(pathToSrcFolder));
    if (typeOfIndexing.contains("createIndexOfContent"))
      addContentAndTitlesToIndex();
    if (typeOfIndexing.contains("createOnlyIndexOfTitles"))
      addTitlesToIndex();
    if (typeOfIndexing.contains("createIndexOfWebPagesContentAndTitles"))
      addWebPagesToIndex();

    queue.clear();
    long endTime = System.currentTimeMillis();
    System.out.println("");
    System.out.println("************************");
    System.out.println((newNumDocs - originalNumDocs) + " documents added properly.");
    System.out.println("Operation of unpack and indexing took " + (endTime - startTime) / 1000 + " seconds (" + (endTime - startTime) + " milliseconds)");
    System.out.println("************************");
    log.info((newNumDocs - originalNumDocs) + " documents added properly.");
    log.info("Operation of unpack and indexing took " + (endTime - startTime) / 1000 + " seconds (" + (endTime - startTime) + " milliseconds)");
  }

  private void addWebPagesToIndex() throws IOException {
    IndexWriter writer = createWriter();
    originalNumDocs = writer.numDocs();
    for (File f : queue) {
      String nameOfFile = f.getName();
      FileReader fr = null;
        try {
          Document doc = new Document();
          fr = new FileReader(f);
          BufferedReader sc = new BufferedReader(fr);
          String line = null;
          String title = null;
          StringBuffer sb = new StringBuffer();
          Integer counter = 0;
          while ((line = sc.readLine()) != null) {
            if (counter == 0) {
            title = line.replaceFirst("<!-- ", "").replace("--><!DOCTYPE html>", "");
          }else
            sb.append(line);
            counter++;
          }
          org.jsoup.nodes.Document soupDoc =  Jsoup.parse(sb.toString());
          String content = soupDoc.text();

          doc.add(new Field("title", title, Field.Store.YES, Field.Index.ANALYZED));
          doc.add(new Field("nameOfFile", nameOfFile, Field.Store.YES, Field.Index.ANALYZED));
          doc.add(new Field("content", content, Field.Store.YES, Field.Index.ANALYZED));
          writer.addDocument(doc);
          System.out.println("Added: " + f + " " + title);
          log.info("Added: " + f + " " + title);
          sb = null;
        } catch (Exception e) {
          System.out.println("Could not add: " + f);
          log.info("Could not add: " + f);
        } finally {
          fr.close();
        }
    }
    newNumDocs = writer.numDocs();
    closeIndex(writer);
  }

  private void addTitlesToIndex() throws IOException {
    IndexWriter writer = createWriter();
    originalNumDocs = writer.numDocs();
    for (File f : queue) {
      String nameOfFile = f.getName();
      FileReader fr = null;
      if (!f.getAbsoluteFile().toString().contains("ETEXT")) {
        try {
          Document doc = new Document();
          fr = new FileReader(f);
          BufferedReader sc = new BufferedReader(fr);
          String line = null;
          String title = null;
          while (!(line = sc.readLine()).contains("Language:")) {
            if (line.matches("Title: (.+)")) {
              title = line.replace("Title:", "");
            }
          }
          doc.add(new Field("title", title, Field.Store.YES, Field.Index.ANALYZED));
          doc.add(new Field("nameOfFile", nameOfFile, Field.Store.YES, Field.Index.ANALYZED));
          writer.addDocument(doc);
          System.out.println("Added: " + f + " " + title);
          log.info("Added: " + f + " " + title);
        } catch (Exception e) {
          System.out.println("Could not add: " + f);
          log.info("Could not add: " + f);
        } finally {
          fr.close();
        }
      }
    }
    newNumDocs = writer.numDocs();
    closeIndex(writer);
  }

  private void addContentAndTitlesToIndex() throws IOException, ParseException {
    originalNumDocs = 0;
    for (File f : queue) {
      FileReader fr = null;
      if (!f.getAbsoluteFile().toString().contains("ETEXT")) {
        try {
          IndexWriter writer = null;
          writer = createWriter();
          fr = new FileReader(f);
          BufferedReader sc = new BufferedReader(fr);
          String line = null;
          String title = null;
          String allText = null;
          String nameOfFile = f.getName();
          String normalizeName = overAllcounter + "Id" + nameOfFile.toLowerCase().replaceAll("_", "").replaceAll("-", "").replaceAll(".txt", "");
          overAllcounter++;
          writer.close();
          if (checkIfExistInIndex(normalizeName) == false) {
            while ((line = sc.readLine()) != null) {
              if ((line.toLowerCase().contains("end of the project gutenberg ebook"))) {
                break;
              }
              if (line == null) {
                System.out.println("EOF of book");
                break;
              }
              if (line.length() > 0) {
                if (line.matches("Title: (.+)")) {
                  title = line;
                } else {
                  allText += line + " ";
                }
              }
            }
            if (title != null) {
              String content = null;
              if (allText.contains("START OF"))
                content = allText.split("START OF")[1];
              else {
                content = allText;
              }
              writer = createWriter();
              String properTitle = title.replaceAll("Title:", "");
              Document doc = new Document();
              doc.add(new Field("nameOfFile", normalizeName, Field.Store.YES, Field.Index.ANALYZED));
              doc.add(new Field("title", properTitle, Field.Store.YES, Field.Index.ANALYZED));
              doc.add(new Field("content", content, Field.Store.YES, Field.Index.ANALYZED));
              writer.addDocument(doc);
              System.out.println("Added: " + f + " " + properTitle);
              newNumDocs = writer.numDocs();
              closeIndex(writer);
            } else {
              warningMessage(normalizeName);
            }
          } else {
            warningMessage(normalizeName);
          }
        } finally {
          fr.close();
        }
      }
    }
  }

  private void warningMessage(String normalizeName) {
    System.out.println(normalizeName + " does exist in libary. And cannot ad this item.");
    log.info(normalizeName + " does exist.");
  }

  private IndexWriter createWriter() throws IOException {
    IndexWriter writer = null;
    FSDirectory dir = FSDirectory.open(new File(pathToIndex));
    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
    writer = new IndexWriter(dir, config);
    return writer;
  }

  private boolean checkIfExistInIndex(String nameOfF) throws IOException, ParseException {
    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIndex)));

    IndexSearcher searcher = new IndexSearcher(reader);
    TopScoreDocCollector collector = TopScoreDocCollector.create(1, true);
    Query q = new QueryParser(Version.LUCENE_40, "nameOfFile", analyzer).parse(nameOfF);
    searcher.search(q, collector);
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    System.out.println("I found " + hits.length + " occurrences the same as " + nameOfF);
    for (int i = 0; i < hits.length; ++i) {
      int docId = hits[i].doc;
      Document d = searcher.doc(docId);
      System.out.println((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
      log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
    }
    reader.close();
    if (hits.length == 0)
      return false;
    else
      return true;
  }

  private void addFiles(File file) {
    String filepath = file.getAbsoluteFile().toString();
    if (!file.exists()) {
      System.out.println(file + " does not exist.");
      log.info(file + " does not exist.");
    }
    if (file.isDirectory() && !filepath.contains("ETEXT")) {
      for (File f : file.listFiles()) {
        addFiles(f);
      }
    } else {
      String filename = file.getName().toLowerCase();
      //because my machine have got only Pentium Dual Core 2.1gHZ
      // cannot analyse big sie files and I must set limit to 5415206 kB
      if (filename.endsWith(".txt") && file.length() < 2415206) {
        queue.add(file);
        System.out.println(counter + "Added " + filepath + " to indexing in Lucene");
        log.info(counter + " Added " + filepath + " to indexing in Lucene");
        counter++;
      } else if (filename.contains("http") && file.length() < 2415206) {
        queue.add(file);
        System.out.println(counter + "Added " + filepath + " to indexing in web pages Lucene");
        log.info(counter + " Added " + filepath + " to indexing in web pages Lucene");
        counter++;
      }
      else if (filename.contains(".zip") && !filepath.contains("ETEXT") && checkToUnzipFiles == true) {
        byte[] buffer = new byte[1024];
        try {
          ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
          ZipEntry ze = zis.getNextEntry();
          while (ze != null) {
            String fileName = ze.getName();
            if (fileName.contains(".txt")) {
              File newFile = new File(pathToSrcFolder + "/unziped" + File.separator + fileName);
              String pathToFile = newFile.getAbsoluteFile().toString();
              if (!newFile.exists() && !newFile.isDirectory()) {
                System.out.print("File unzip: " + pathToFile);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                  fos.write(buffer, 0, len);
                }
                fos.close();
                System.out.print(" -done. \n");
              } else {
                System.out.println("File: " + pathToFile + " exsist");
                log.info("File: " + pathToFile + " exsist");
              }
            }
            ze = zis.getNextEntry();
          }
          zis.closeEntry();
          zis.close();
        } catch (IOException e) {
          String msg = " Failed to properly unpack because: ";
          System.out.println(msg + e);
          log.info(msg + e);
        }
      }
    }
  }

  public ScoreDoc[] searchExactlyTitleQuery(String searchedText, String type, String pathToIdx, int phraseSlop, int numberOfHits) throws IOException {
    ScoreDoc[] hits = null;
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIdx)));
    IndexSearcher searcher = new IndexSearcher(reader);
    log.info("I've got in Lucene index: " + searcher.collectionStatistics(type).docCount() + " items");
    try {
      Query q = new TermQuery(new Term(type, searchedText));
      StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
      QueryParser parser = new QueryParser(Version.LUCENE_46,type,analyzer);
      parser.setDefaultOperator(QueryParser.Operator.AND);
      parser.setPhraseSlop(phraseSlop);
      Query query=parser.createPhraseQuery(type, searchedText);
      displayQuery(query);
      hits= searcher.search(query, null, numberOfHits).scoreDocs;

      System.out.println("Found " + hits.length + " hits.");
      log.info("Found " + hits.length + " hits.");
      for (int i = 0; i < hits.length; ++i) {
        int docId = hits[i].doc;
        Document d = searcher.doc(docId);
        System.out.println((i) + ". " + d.get("title") + " score=" + hits[i].score  + ", Name of file in system: " + d.get("nameOfFile"));
        log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
      }
    } catch (Exception e) {
      System.out.println("Error searching " + searchedText + " : " + e.getMessage());
      log.info("Error searching " + searchedText + " : " + e.getMessage());
    }
    return hits;

  }

  public ScoreDoc[] searchAnyOfTheWorldInIndex(String searchedText, String type, String pathToIdx, int phraseSlop, int numberOfHits) throws IOException {
    ScoreDoc[] hits = null;
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIdx)));
    IndexSearcher searcher = new IndexSearcher(reader);
    log.info("I've got in Lucene index: " + searcher.collectionStatistics(type).docCount() + " items");
    try {
      StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
      QueryParser parser = new QueryParser(Version.LUCENE_46,type,analyzer);
      parser.setDefaultOperator(QueryParser.Operator.OR);
      parser.setPhraseSlop(phraseSlop);
      Query query = parser.parse(searchedText);
      hits= searcher.search(query, null, numberOfHits).scoreDocs;
      displayQuery(query);

      hits= searcher.search(query, null, numberOfHits).scoreDocs;

      System.out.println("Found " + hits.length + " hits.");
      log.info("Found " + hits.length + " hits.");
      for (int i = 0; i < hits.length; ++i) {
        int docId = hits[i].doc;
        Document d = searcher.doc(docId);
        System.out.println((i) + ". " + d.get("title") + " score=" + hits[i].score  + ", Name of file in system: " + d.get("nameOfFile"));
        log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
      }
    } catch (Exception e) {
      System.out.println("Error searching " + searchedText + " : " + e.getMessage());
      log.info("Error searching " + searchedText + " : " + e.getMessage());
    }
    return hits;

  }

  public ScoreDoc[] searchAllOfTheWorldInIndex(String searchedText, String type, String pathToIdx, int phraseSlop, int numberOfHits) throws IOException {
    ScoreDoc[] hits = null;
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIdx)));
    IndexSearcher searcher = new IndexSearcher(reader);
    log.info("I've got in Lucene index: " + searcher.collectionStatistics(type).docCount() + " items");
    try {
      //more about searching in lucene: http://stackoverflow.com/questions/15407649/why-lucene-algorithm-not-working-for-exact-string-in-java
      StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

      QueryParser parser = new QueryParser(Version.LUCENE_46,type,analyzer);
      parser.setPhraseSlop(phraseSlop);
      parser.setDefaultOperator(QueryParser.Operator.AND);
      Query query = parser.parse(searchedText);


      hits= searcher.search(query, null, numberOfHits).scoreDocs;
      displayQuery(query);


      System.out.println("Found " + hits.length + " hits.");
      log.info("Found " + hits.length + " hits.");
      for (int i = 0; i < hits.length; ++i) {
        int docId = hits[i].doc;
        Document d = searcher.doc(docId);
        System.out.println((i) + ". " + d.get("title") + " score=" + hits[i].score  + ", Name of file in system: " + d.get("nameOfFile"));
        log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
      }
    } catch (Exception e) {
      System.out.println("Error searching " + searchedText + " : " + e.getMessage());
      log.info("Error searching " + searchedText + " : " + e.getMessage());
    }
    return hits;

  }

  public static void displayQuery(Query query) {
    System.out.println("Query: " + query.toString());
  }

  public void closeIndex(IndexWriter writer) throws IOException {
    writer.close();
  }
}
