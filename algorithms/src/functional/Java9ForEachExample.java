package functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Java9ForEachExample {
    public static void main(String[] args) {
        List<Integer> sampleList = new ArrayList<>();
        for (int i=0; i<10; i++){
            sampleList.add(i);
        }

        //example of traversing by Iterator
        Iterator<Integer> iterator = sampleList.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            System.out.println("Iterator value is: " + i);
        }

        //example of traversing by anonymous class
        sampleList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println("forEach() anonymous class value: " + i);
            }
        });

        //example of traversing by Consumer implementation
        MySampleConsumer action = new MySampleConsumer();
        sampleList.forEach(action);
    }
}

//Consumer implementation that can be reused
class MySampleConsumer implements Consumer<Integer>{

    public void accept(Integer t) {
        System.out.println("Consumer impl Value::"+t);
    }


}
