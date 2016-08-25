import org.junit.Assert;

/**
 * User: trojnaradam@gmail.com
 * Date: 04.08.16
 * Time: 18:02
 */
public class Test {

  @org.junit.Test
  public void testCreate(){

    int width = 10;
    int height = 10;

    Board board = new Board(width, height);
    Assert.assertNotNull(board);

    for(int i=0; i<width; i++){
      for(int j=0; j<height; j++){
        Assert.assertEquals(false, board.getCellValue(i,j));
      }
    }

  }

  @org.junit.Test
  public void testSetValue(){
    Board board = new Board(10, 10);
    board.setCellValue(5, 5, true);
    Assert.assertEquals(true, board.getCellValue(5, 5));

    board.setCellValue(0, 0, true);
    Assert.assertEquals(true, board.getCellValue(0, 0));
  }

  @org.junit.Test
  public void testDieLonely() {

    // zero sasiadow
    Board board = new Board(5, 5);
    board.setCellValue(0, 0, true);
    Assert.assertEquals(0, board.countAliveNeighbours(0, 0));
    board.nextCycle();
    Assert.assertEquals(false, board.getCellValue(0, 0));

    // jeden sasiad
    Board board2 = new Board(5, 5);
    board2.setCellValue(0, 0, true);
    board2.setCellValue(0, 1, true);
    Assert.assertEquals(1, board2.countAliveNeighbours(0, 0));
    board2.nextCycle();
    Assert.assertEquals(false, board2.getCellValue(0, 0));

  }

  @org.junit.Test
  public void testSurvive() {

    // dwoch sasiadow
    Board board2 = new Board(5, 5);
    board2.setCellValue(0, 0, true);
    board2.setCellValue(0, 1, true);
    board2.setCellValue(1, 0, true);
    Assert.assertEquals(2, board2.countAliveNeighbours(0, 0));
    board2.nextCycle();
    Assert.assertEquals(true, board2.getCellValue(0, 0));

    // trzech sasiadow
    Board board3 = new Board(5, 5);
    board3.setCellValue(0, 0, true);
    board3.setCellValue(0, 1, true);
    board3.setCellValue(1, 0, true);
    board3.setCellValue(1, 1, true);
    Assert.assertEquals(3, board3.countAliveNeighbours(0, 0));
    board3.nextCycle();
    Assert.assertEquals(true, board3.getCellValue(0, 0));

  }

  @org.junit.Test
  public void testDieOverpopulation() {

    // czterech sasiadow
    Board board = new Board(5, 5);
    board.setCellValue(2, 2, true);
    board.setCellValue(2, 1, true);
    board.setCellValue(2, 3, true);
    board.setCellValue(1, 2, true);
    board.setCellValue(3, 2, true);
    Assert.assertEquals(4, board.countAliveNeighbours(2, 2));
    board.nextCycle();
    Assert.assertEquals(false, board.getCellValue(2, 2));

  }

  @org.junit.Test
  public void testRessurection() {

    Board board = new Board(5, 5);
    board.setCellValue(2, 2, false);
    board.setCellValue(2, 1, true);
    board.setCellValue(2, 3, true);
    board.setCellValue(1, 2, true);
    Assert.assertEquals(3, board.countAliveNeighbours(2, 2));
    board.nextCycle();
    Assert.assertEquals(true, board.getCellValue(2, 2));

  }
}