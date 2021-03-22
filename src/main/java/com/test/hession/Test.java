package com.test.hession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author dengxiaolin
 * @since 2020/11/02
 */
public class Test {

    public static void main(String[] args) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        To to = new To();
        try {

            Kryo kryo = new Kryo();
            kryo.register(To.class);
            Output output = new Output(os);
            kryo.writeObject(output, to);
            output.close();

//            byte[] byteArray = output.getBuffer();
//            for (int i = 0; i < byteArray.length; i++) {
//                System.out.println(byteArray[i]);
//            }

            byte[] byteArray = new byte[] {2, 49, 50, -77};

            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            Input input = new Input(bis);
            To student = kryo.readObject(input, To.class);
            System.out.println(student.getName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
