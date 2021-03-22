package com.test.type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * @author dengxiaolin
 * @since 2020/08/06
 */
public class Matrix extends Vector<Vector<Integer>> {
    Matrix mul(Matrix m) {
        Matrix ret = new Matrix();
        for (int i = 0; i < size(); i++) {
            Vector<Integer> v1 = this.elementAt(i);
            Vector<Integer> v2 = new Vector<Integer>();
            for (int j = 0; j < v1.size(); j++) {
                int erg = 0;
                for (int k = 0; k < v1.size(); k++) {
                    erg = erg + v1.elementAt(k) * (m.elementAt(k)).elementAt(j);
                    v2.addElement(erg);
                }
                ret.addElement(v2);
            }
        }
        return ret;
    }

    public static <R extends Number> void main(String[] args) {
        List<Box<Child>> list = new ArrayList<>();
        list.sort(Comparator.comparing(Box::getT));


        List<? super Number> list1 = new ArrayList<>();
        // why?
        Object object = list1.get(0);

        Box<? super Father> box = new Box<>();
        // why?
        Father genericT = box.getGenericT();

    }


    static class Box<T extends Father> {

        private T t;

        public Box() {
        }

        public Box(T t) {
            this.t = t;
        }

        public Father getT() {
            return t;
        }

        public T getGenericT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    public static class Father implements Comparable<Father> {

        @Override
        public int compareTo(Father o) {
            return 0;
        }
    }

    public static class Child extends Father {

    }
}
