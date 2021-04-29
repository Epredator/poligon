package oca;

import java.util.ArrayList;

interface PracticeTest3Question39a
{
    void m1();
    public void m2();
}
class Practice implements PracticeTest3Question39a
{
    public void m1(){}

    @Override
    public void m2() {
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.size();

    }
}