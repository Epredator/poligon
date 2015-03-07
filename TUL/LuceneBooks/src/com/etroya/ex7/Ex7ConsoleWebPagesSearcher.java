package com.etroya.ex7;

import com.etroya.ex6.Ex6ConsoleContentAndTitlesSearcher;
import com.etroya.utils.CreateIndexWrapper;
import com.meterware.httpunit.*;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: trojnaradam@gmail.com
 * Date: 06.02.15
 * Time: 10:58
 *
 * Należy zaindeksować 10000 – 100000 stron www (stron i ich podstron łącznie).
 *  Program pobiera i indeksuje strony www
 *
 * TODO// Program wyszukuje strony podobne. Obok wyszukanej strony ma pojawić się adres do strony o treści podobnej.
 *
 */

class Ex7ConsoleWebPagesSearcher {
  public static void main(String[] params) throws IOException, SAXException, URISyntaxException {
    boolean downloadNewPages = false;
    Ex7ConsoleWebPagesSearcher craw = new Ex7ConsoleWebPagesSearcher();

   if (downloadNewPages == true)
    craw.downloadPages();
   else
    craw.indexPages();
  }

  private static String homeDir = System.getProperty("user.home") + "/gutenbergBooks";
  private static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
  private static String pathToIndex = homeDir + "/webPagesIndex/";
  private static String pathToSrcFolder = homeDir + "/webPages/";
  static Logger log = Logger.getLogger(
      Ex6ConsoleContentAndTitlesSearcher.class.getName());

  private void indexPages() throws IOException {
    log.info("Hello this is an info message about web pages indexer");
    boolean createNewIndex = true;
    String typeOfIndexing = "createIndexOfWebPagesContentAndTitles";
    new CreateIndexWrapper(pathToIndex, pathToSrcFolder, createNewIndex, typeOfIndexing);
    BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in));


    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIndex)));
    IndexSearcher searcher = new IndexSearcher(reader);
    System.out.println("I've got in Lucene web pages index: " + searcher.collectionStatistics("title").docCount() + " web pages now, let's search!");
    log.info("I've indexed: " + searcher.collectionStatistics("title").docCount() + " web pages");
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

        // 4. display results
        System.out.println("Found " + hits.length + " hits.");
        log.info("Found " + hits.length + " hits.");
        for (int i = 0; i < hits.length; ++i) {
          int docId = hits[i].doc;
          org.apache.lucene.document.Document d = searcher.doc(docId);
          System.out.println((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
          log.info((i + 1) + ". " + d.get("title") + " score=" + hits[i].score + ", Name of file in system: " + d.get("nameOfFile"));
        }
      } catch (Exception e) {
        System.out.println("Error searching " + s + " : " + e.getMessage());
        log.info("Error searching " + s + " : " + e.getMessage());
      }
    }

  }

  private static String base_url = "http://pl.wikipedia.org";
  private String baseIP;
  private WebResponse response;
  private List<String> webLinks = new ArrayList<String>();
  private final static int IMMERSION = 2;
  private List<String> temporaryExternalHrefs = new ArrayList<String>();
  private List<String> temporaryInternalHrefs = new ArrayList<String>();
  private String linksFromDisc = "";
  private Integer numberOfInternalLinks = null;
  private Integer counter = 0;

  private void downloadPages() throws IOException, SAXException, URISyntaxException {
    baseIP = checkStartLink();
    if (baseIP != null) {
      checkWebLinks();
    } else
      System.out.println("Strona: " + base_url + " jest niedostępna, więc nie można ustalić numeru IP ");
    System.out.println("Pomyślnie zakończono pobieranie " + numberOfInternalLinks + " stron wewnętrznych dla nazwy domeny DNS: " + base_url);
  }

  private String checkStartLink() throws MalformedURLException, UnknownHostException {
    webLinks = Arrays.asList(base_url);
    return checkDNS(base_url);
  }

  private void checkWebLinks() throws IOException, SAXException, URISyntaxException {
    webLinks = scrapeDoc(webLinks);
    for (int i = 1; i < IMMERSION; i++) {
      System.out.println("Iteracja numer: " + (i + 1));
      linksFromDisc = openLinksFromDisc("wewnetrzne linki", linksFromDisc);
      if (linksFromDisc.length() > 1) {
        String[] links = linksFromDisc.split(", ");
        webLinks = Arrays.asList(links);
        scrapeDoc(webLinks);
      }
    }
    numberOfInternalLinks = temporaryInternalHrefs.size();
  }

  private List<String> scrapeDoc(List<String> webLinks) throws IOException, SAXException, URISyntaxException {
    for (String link : webLinks) {
      fetchPage(link);
      getWebLinks();
      saveLinksOnDisc("wewnetrzne linki", temporaryInternalHrefs);
      saveLinksOnDisc("zewnetrzne linki", temporaryExternalHrefs);
    }
    return temporaryInternalHrefs;
  }

  private void fetchPage(String link) throws IOException, SAXException {
    WebConversation conversation = new WebConversation();
    HttpUnitOptions.setScriptingEnabled(false);
    try {
      System.out.println("pobieram zawartość strony: " + link);
      WebRequest request = new GetMethodWebRequest(link);
      response = conversation.getResponse(request);
    } catch (Exception e) {
      System.out.println("strona " + link + " nie odpowiada");
    }
  }

  private List<String> getWebLinks() throws IOException, URISyntaxException {
    URL url = response.getURL();
    String new_base_url = "http://" + url.getHost().toString() + "/";
    if (!base_url.contains(new_base_url) || temporaryInternalHrefs.size() == 0) {
      base_url = new_base_url;
    }
    Document doc = Jsoup.parse(response.getText());
    Elements elems = doc.select("a");
    for (Element elm : elems) {
      String href = elm.attr("href");
      checkLink(href);
    }
    return temporaryInternalHrefs;
  }

  private void checkLink(String href) throws IOException, URISyntaxException {
    if (href.contains("https:")) {
      href = href.replace("http://", "");
    }
    Pattern p = Pattern.compile("^" + href + "$");
    Matcher m = p.matcher(base_url);
    if (href.length() > 1 && !m.find() && !href.contains("#") && !href.contains("javascript:"))
      if (!href.contains("@")) {
        if (!href.contains("http://"))
          if (!href.contains("https://")) {
            Pattern pHref = Pattern.compile("^/");
            Matcher mHref = pHref.matcher(href);
            if (!mHref.find()) {
              href = "/" + href;
            }
            Pattern pBaseUrl = Pattern.compile("/$");
            Matcher mBaseUrl = pBaseUrl.matcher(base_url);
            if (mBaseUrl.find()) {
              base_url = base_url.substring(0, base_url.length() - 1);
              href = base_url + href;
            } else {
              href = base_url + href;
            }
          }
        String ipToCheck = checkDNS(href);
        if (ipToCheck != null)
          if (baseIP.contains(ipToCheck)) {
            if (!temporaryInternalHrefs.contains(href)) {
              addItemToHrefs(href);
              System.out.println("Dodano " + href + " do linków wewnętrznych");
              downloadPage(href);
            }
          } else {
            if (!temporaryExternalHrefs.contains(href)) {
              temporaryExternalHrefs.add(href);
              System.out.println("Dodano " + href + " do linków zewnętrznych");
            }
          }
      }
  }

  private void downloadPage(String href) throws IOException, URISyntaxException {

    URL website = new URL(href);
    try {
      ReadableByteChannel rbc = Channels.newChannel(website.openStream());
      String dirToDownload = homeDir + "/webPages/";
      String fileDir = href.replaceAll("\\W", "");
      String allDir = dirToDownload + counter + fileDir + ".html";
      File f = new File(allDir);
      if (!f.exists()) {
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(f));
        writer.write("<!-- " + href + " -->");
        writer.close();

        FileOutputStream fos = new FileOutputStream(allDir, true);
        System.out.print(counter + " ");
        counter++;
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
      }
    } catch (IOException e) {
      System.out.println("Nie udalo się ściągnąć z powodu " + e);
    }


  }

  private String checkDNS(String href) throws MalformedURLException, UnknownHostException {
    String res = null;
    URL oaiBaseURL = null;
    try {
      oaiBaseURL = new URL(href);
      String mainHost = oaiBaseURL.getHost();
      res = InetAddress.getByName(mainHost).getHostAddress();
    } catch (IOException e) {
      System.out.println("Nie można otworzyc strony: " + oaiBaseURL + ". Typ błędu: " + e);
    }
    return res;
  }

  private void addItemToHrefs(String href) {
    if (href.contains("https")) {
      String properHref = href.replace("https://" + base_url, "");
      if (!(temporaryInternalHrefs.contains(properHref)))
        temporaryInternalHrefs.add((properHref));
    } else if (href.contains("#")) {
      String properHref = href.replace("#", "/#");
      if (!(temporaryInternalHrefs.contains(properHref)))
        temporaryInternalHrefs.add((properHref));
    } else if (!(temporaryInternalHrefs.contains(href)))
      temporaryInternalHrefs.add(href);
  }

  private String openLinksFromDisc(String name, String htmlTextFromDisc) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("page" + onlyText(name) + ".txt"));
    String sCurrentLine;
    htmlTextFromDisc = "";
    while ((sCurrentLine = br.readLine()) != null)
      htmlTextFromDisc += sCurrentLine;
    return htmlTextFromDisc;
  }

  private void saveLinksOnDisc(String name, List<String> hrefs) throws IOException {
    String htmlPageToSave = hrefs.toString().replaceAll("\\[", " ").replaceAll("]", "");
    FileWriter fw = new FileWriter("page" + onlyText(name) + ".txt");
    fw.write(htmlPageToSave);
    fw.close();
  }

  private String onlyText(String text) {
    return text.replaceAll("[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ\\s]*", "").toLowerCase();
  }


}
