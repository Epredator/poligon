/**
 * User: trojnaradam@gmail.com
 * Date: 18.08.16
 * Time: 21:01
 *
 * Access Levels Modifier	Class	Package	Subclass	World
 *        public	    Y	Y	Y	Y
 *        protected	  Y	Y	Y	N
 *        no modifier	Y	Y	N	N
 *        private	    Y	N	N	N

 */
public class Car {
  //atrybuty
  String color;
  int engine;

  public String print(){
    return "car with color " + color;

  }

  public static void main(String[] args){
    Car obj = new Car();
    obj.color = "red";
    obj.engine = 2000;

    obj.print();
  }
}
