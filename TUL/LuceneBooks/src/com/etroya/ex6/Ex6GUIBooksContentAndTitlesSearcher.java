package com.etroya.ex6;

import com.etroya.utils.TitlesOfGutenbergIndexer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * User: trojnaradam@gmail.com
 * Date: 05.02.15
 * Time: 08:43
 * <p>
 * Należy rozbudować program z zadania poprzedniego. Celem jest dodanie indeksowania dla
 * całych dokumentów dzieląc je odpowiednio na tytuł i treść (należy pominąć nagłówek dodawany
 * przez projekt Gutenberg). Należy wykonać interfejs wyszukiwarki książek po tytułach i treści
 * dokumentu.
 * <p>
 * More info: http://pduch.kis.p.lodz.pl/EDwI/Eksploracja%20zad%206%20-%20Biblioteka%20Lucene_2.pdf
 */
public class Ex6GUIBooksContentAndTitlesSearcher extends Application {
  private static String homeDir = System.getProperty("user.home") + "/gutenbergBooks";
  private static String pathToIndex = homeDir + "/luceneContentAndTitlesIndex/";
  private ScoreDoc[] hits = null;
  private Long numOfAllBooksInLucene = Long.valueOf(0);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    countItemsFromHits("start", "title");
    primaryStage.setTitle("jGutenbergSearcher App. I've indexed: " + numOfAllBooksInLucene + " books");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.BASELINE_CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));

    Button btnSTitle = new Button("Search title!");
    HBox hbBtnSTitle = new HBox(10);
    hbBtnSTitle.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtnSTitle.getChildren().add(btnSTitle);
    grid.add(hbBtnSTitle, 0, 2);

    Button btnSContent = new Button("Search content!");
    HBox hbBtnSContent = new HBox(10);
    hbBtnSContent.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtnSContent.getChildren().add(btnSContent);
    grid.add(hbBtnSContent, 1, 2);

    TextField searchTextField = new TextField();
    grid.add(searchTextField, 0, 1, 2, 1);
    searchTextField.setPrefWidth(800);

    final Text actionSearachingTitles = new Text();
    grid.add(actionSearachingTitles, 0, 3, 2, 3);
    actionSearachingTitles.setStrokeWidth(800);

    ListView<String> list = new ListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList();
    list.setItems(items);
    list.setPrefHeight(800);
    grid.add(list, 0, 5, 2, 5);

    final Tooltip tooltip = new Tooltip();
    tooltip.setText(
        "\nClick a item on list to view content of book\n"
    );
    list.setTooltip(tooltip);

    btnSTitle.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String searchedText = searchTextField.getText();
        if (searchedText.length() > 0) {
          defaultSearchingTitleMessage(actionSearachingTitles, searchedText);
          try {
            ObservableList<String> items = countItemsFromHits(searchedText, "title");
            if (items.size() > 0) {
              list.setItems(items);
              defaultFoundMessage(actionSearachingTitles, items);
            } else
              defaultNOTFoundMessage(actionSearachingTitles);
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        } else
          defaultWaitingMessage(actionSearachingTitles);
      }
    });

    btnSContent.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String searchedText = searchTextField.getText();
        if (searchedText.length() > 0) {
          defaultSearchingContentMessage(actionSearachingTitles, searchedText);
          try {
            ObservableList<String> items = countItemsFromHits(searchedText, "content");
            if (items.size() > 0) {
              list.setItems(items);
              defaultFoundMessage(actionSearachingTitles, items);
            } else
              defaultNOTFoundMessage(actionSearachingTitles);
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        } else
          defaultWaitingMessage(actionSearachingTitles);
      }
    });

    searchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        String searchedText = searchTextField.getText();
        if (searchedText.length() > 0) {
          actionSearachingTitles.setFill(Color.GREEN);
          actionSearachingTitles.setText("You will searches for phrase: " + searchedText);
        } else {
          actionSearachingTitles.setText("Type in what you are looking for Search");
        }
      }
    });

    list.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> ov,
                              String old_val, String new_val) {
            String content = null;
            try {
              content = getContent(new_val);
            } catch (IOException e) {
              e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Book title: " + new_val);
            final HTMLEditor htmlEditor = new HTMLEditor();
            htmlEditor.setHtmlText(content);
            htmlEditor.setPrefWidth(800);
            Scene scene = new Scene(htmlEditor);
            stage.setScene(scene);
            stage.show();
          }
        });

    Scene scene = new Scene(grid, 800, 800);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  private void defaultWaitingMessage(Text actionSearachingTitles) {
    actionSearachingTitles.setFill(Color.FIREBRICK);
    actionSearachingTitles.setText("Type in what you are looking for search");
  }

  private void defaultNOTFoundMessage(Text actionSearachingTitles) {
    actionSearachingTitles.setFill(Color.FIREBRICK);
    actionSearachingTitles.setText("Nothing found");
  }

  private void defaultFoundMessage(Text actionSearachingTitles, ObservableList<String> items) {
    actionSearachingTitles.setFill(Color.GREEN);
    actionSearachingTitles.setText("I found the " + items.size() + " results");
  }

  private void defaultSearchingTitleMessage(Text actionSearachingTitles, String searchedText) {
    actionSearachingTitles.setFill(Color.GREEN);
    actionSearachingTitles.setText("I am looking for title: " + searchedText);
  }

  private void defaultSearchingContentMessage(Text actionSearachingTitles, String searchedText) {
    actionSearachingTitles.setFill(Color.GREEN);
    actionSearachingTitles.setText("I am looking for content: " + searchedText);
  }

  private String getContent(String title) throws IOException {
    Integer num = 1;
    int docId = 1;
    if (title != null) {
      String splittedTxt = title.split("\\.")[0];
      num = Integer.valueOf(splittedTxt);
      docId = hits[num].doc;
      IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIndex)));
      IndexSearcher searcher = new IndexSearcher(reader);
      Document d = searcher.doc(docId);
      return d.get("content");
    } else {
      System.out.println("I can't split title " + title);
      return "";
    }
  }

  private ObservableList<String> countItemsFromHits(String searchedText, String type) throws IOException {
    TitlesOfGutenbergIndexer searcher = new TitlesOfGutenbergIndexer();
    ObservableList<String> items = FXCollections.observableArrayList();
    hits = searcher.searchExactlyTitleQuery(searchedText, type, pathToIndex, 0, 5);
    System.out.println(hits.length);
    for (int i = 0; i < hits.length; i++) {
      int docId = hits[i].doc;
      IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIndex)));
      IndexSearcher indexSearcher = new IndexSearcher(reader);
      Document d = indexSearcher.doc(docId);
      numOfAllBooksInLucene = indexSearcher.collectionStatistics(type).docCount();
      items.add(i + "." + d.get("title"));
    }
    return items;
  }
}
