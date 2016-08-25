import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Collection;

/**
 * Created by Adam on 2015-07-21.
 *
 * Poni¿ej przyk³ad u¿ycia refleksji do przeprowadzenia analizy obiektów typu Person.
 * Klasa Person jest prost¹ klas¹ która sk³ada siê z pól:
 * name, surname, age, address oraz metod dostêpowych.
 * Dodatkowo klasa Person implementuje interfejs Comparable który umo¿liwia porównywanie obiektów miêdzy sob¹.
 *
 * source: http://java-programowanie.pl/podstawy/refleksja/analiza-obiektu-za-pomoca-refleksji/
 */
public class ReflectionSample {
    public static void main(String[] args){
        Person per = new Person("Jan", "Malinowski", 27, "Warszawa");
        ObjectAnalyzer objA = new ObjectAnalyzer(per);
        System.out.println(objA);
    }
}

class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private int age;
    private String address;

    public Person (String name, String surname, int age, String addr){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        return name + " " + surname + ", lat " + age + " adr. zam. " + address;
    }

    @Override
    public int compareTo(Person p) {
        int ret;
        ret = surname.compareTo(p.surname);
        if (ret == 0) ret = name.compareTo(p.name);
        if (ret == 0) ret = (age > p.age ? 1 :
                (age == p.age ? 0 : -1));
        if (ret == 0) ret = address.compareTo(p.address);
        return ret;
    }
}

 class ObjectAnalyzer {
     private Object _obj;
     private Method[] _allMethods = null;
     private Field[] _allFields = null;
     private Class _clazz = null;
     private Method _toStr = null;

     public ObjectAnalyzer (Object obj) {
         _obj = obj;
     }

     //przesy³am metode toString
     //uzywam mechanizmu  refleksji w celu uzyskania informacji o obiekcie
     public String toString() {
         //pobieram klase obiektu
         _clazz = _obj.getClass();

         //pobieram nazwe klasy obiektu
         String nameClazz = _clazz.getName();
         System.out.println("obiekt klasy: " + nameClazz);

         //pobieram wszystkie zadeklarowane metody
         _allMethods = _clazz.getDeclaredMethods();
         System.out.println("Metody klasy: ");
         int i = 1;
         for (Method m : _allMethods){
             System.out.println("Metoda nr " + i + ": ");
             System.out.println("nazwa " + m.getName());
             i++;

             //pobieram modyfikator metody
             System.out.println("modyfikator: " + Modifier.toString(m.getModifiers()));
             if(m.getName().equals("toString"))
                 _toStr = m;
             //pobiermay parametr metody
             Class[] param = m.getParameterTypes();
             if (param.length != 0) {
                 for (Class c : param)
                     System.out.println("       parametry metody (typy): " + c.getName());
             }
         }

         //pobieramy zadeklarowane pola
         _allFields = _clazz.getDeclaredFields();
         System.out.println("Pola klasy: ");
         int j=1;
         for(Field f : _allFields) {
             System.out.println("Pole numer " + j + ": ");
             System.out.println("nazwa: " + f.getName());
             //pobieramy modyfikator pola i go drukujemy
             System.out.println("modyfikator: "+ Modifier.toString(f.getModifiers()));
             j++;
         }

         //drukowanie obiektu, wywolanie metody toString
         try {
             Object o = _toStr.invoke(_obj);
             System.out.println("Wydruk obiektu: \n" + o);
         } catch (IllegalAccessException | InvocationTargetException e) {
             e.printStackTrace();
         }
         return "koniec";
     }

}
