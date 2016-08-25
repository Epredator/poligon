package com.etroya.utils;

import java.io.IOException;

/**
 * User: trojnaradam@gmail.com
 * Date: 31.01.15
 * Time: 19:32
 */
public class CreateIndexWrapper {
//  private IndexWriter writer;
//  private static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);

  /**
   * Constructor
   *
   * @param indexDir the name of the folder in which the index should be created
   * @throws java.io.IOException when exception creating index.
   */
  public CreateIndexWrapper(String pathToIndex, String pathToSourceFolder, boolean createNewIndex, String typeOfIndexing) throws IOException {
    System.out.println("The path where the index was created: (" + pathToIndex + ")");
    try {
      System.out.println("The full path to add into the index (q=quit): (" + pathToIndex + ")");
      System.out.println("[Acceptable file types: .xml, .html, .html, .txt, .zip]");

      //try to add file into the index
      TitlesOfGutenbergIndexer indexer = new TitlesOfGutenbergIndexer();
      if (createNewIndex == true)
        indexer.indexFileOrDirectory(pathToIndex, pathToSourceFolder, typeOfIndexing);
    } catch (Exception e) {
      System.out.println("Error indexing " + pathToIndex + " : " + e.getMessage());
    }
  }

}
