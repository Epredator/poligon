package com.etroya.ex7;

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
import java.util.Arrays;
import java.util.List;

/**
 * User: trojnaradam@gmail.com
 * Date: 06.02.15
 * Time: 20:21
 *
 * Należy zaindeksować 10000 – 100000 stron www (stron i ich podstron łącznie).
 * Program pobiera i indeksuje strony www
 *
 *
 * TODO// Program wyszukuje strony podobne. Obok wyszukanej strony ma pojawić się adres do strony o treści podobnej.
 *
 */

public class Ex7GUIWebPagesSearcher extends Application {
  private static String homeDir = System.getProperty("user.home") + "/gutenbergBooks";
  private static String pathToIndex = homeDir + "/webPagesIndex/";
  private static String selectedOption = "any of words";
  private ScoreDoc[] hits = null;
  private Long numOfAllBooksInLucene = Long.valueOf(0);
  final ToggleGroup group = new ToggleGroup();
  ToggleButton tb = new ToggleButton("Search exact");
  ListView<String> list = new ListView<String>();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    countItemsFromHits("wiki", "title");
    primaryStage.setTitle("Web crawler. I've indexed: " + numOfAllBooksInLucene + " web pages");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.BASELINE_CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));

    Button btnSTitle = new Button("Search web address!");
    HBox hbBtnSTitle = new HBox(10);
    hbBtnSTitle.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtnSTitle.getChildren().add(btnSTitle);
    grid.add(hbBtnSTitle, 0, 2);

    Button btnSContent = new Button("Search content!");
    HBox hbBtnSContent = new HBox(10);
    hbBtnSContent.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtnSContent.getChildren().add(btnSContent);
    grid.add(hbBtnSContent, 1, 2);



    RadioButton rb1 = new RadioButton("any of words");
    rb1.setToggleGroup(group);
    rb1.setSelected(true);

    RadioButton rb2 = new RadioButton("all words");
    rb2.setToggleGroup(group);

    RadioButton rb3 = new RadioButton("exactly the same");
    rb3.setToggleGroup(group);
    List<RadioButton> radioButtons = Arrays.asList(rb1, rb2, rb3);
    int rowIndex = 0;
    for ( RadioButton radioButton : radioButtons ) {
      grid.add( radioButton, 2, rowIndex++ );
    }



    TextField searchTextField = new TextField();
    grid.add(searchTextField, 0, 1, 2, 1);
    searchTextField.setPrefWidth(500);



    final Text actionSearachingTitles = new Text();
    grid.add(actionSearachingTitles, 0, 3, 2, 3);


    ObservableList<String> items = FXCollections.observableArrayList();
    list.setItems(items);
    list.setPrefHeight(800);
    grid.add(list, 0, 5, 3, 5);

    final Tooltip tooltip = new Tooltip();
    tooltip.setText(
        "\nClick a item on list to view content of web page\n"
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
    list.setItems(null);
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
    TitlesOfGutenbergIndexer titlesSearcher = new TitlesOfGutenbergIndexer();
    ObservableList<String> items = FXCollections.observableArrayList();
    if (selectedOption.contains("exactly the same"))
    hits = titlesSearcher.searchExactlyTitleQuery(searchedText, type, pathToIndex, 0, 5);
    else if (selectedOption.contains("any of words"))
      hits = titlesSearcher.searchAnyOfTheWorldInIndex(searchedText, type, pathToIndex, 10000000, 5);
    else
      hits = titlesSearcher.searchAllOfTheWorldInIndex(searchedText, type, pathToIndex, 10000000, 5);

    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

        RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
        selectedOption = chk.getText();

      }
    });
      System.out.println(hits.length);
      for (int i = 0; i < hits.length; i++) {
        int docId = hits[i].doc;
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(pathToIndex)));
        IndexSearcher searcher = new IndexSearcher(reader);
        Document d = searcher.doc(docId);
        numOfAllBooksInLucene = searcher.collectionStatistics(type).docCount();
        items.add(i + "." + d.get("title"));
      }
    return items;
  }
}


