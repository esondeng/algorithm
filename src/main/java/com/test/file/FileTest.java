package com.test.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dengxiaolin
 * @since 2020/06/28
 */
public class FileTest {

    public static void main(String[] args) {
        FileTest fileTest = new FileTest();
        File sourceFile = new File("/Users/dengxiaolin/test/source.txt");
        File targetFile = new File("/Users/dengxiaolin/test/target.txt");
        fileTest.mapRead(sourceFile, targetFile);
    }

    private void read(File sourceFile, File targetFile) {
        byte[] byteArray = new byte[1024];

        FileInputStream fis = null;
        FileOutputStream fos = null;
        int temp = 0;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            while ((temp = fis.read(byteArray, 0, byteArray.length)) != -1) {
                fos.write(byteArray, 0, temp);
            }
            fos.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void bufferRead(File sourceFile, File targetFile) {
        byte[] byteArray = new byte[1024];
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        int temp = 0;
        try {
            fileInputStream = new FileInputStream(sourceFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(targetFile);

            while ((temp = bufferedInputStream.read(byteArray, 0, byteArray.length)) != -1) {
                fileOutputStream.write(byteArray, 0, temp);
            }
            fileOutputStream.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fileChannelRead(File file, File newFile) {
        long d1 = System.currentTimeMillis();
        FileInputStream in = null;
        FileOutputStream output = null;
        FileChannel fic = null;
        FileChannel foc = null;
        try {
            in = new FileInputStream(file);
            output = new FileOutputStream(newFile);
            fic = in.getChannel();
            foc = output.getChannel();

            int position = 0;
            long size = fic.size();
            while (0 < size) {
                long count = foc.transferFrom(fic, position, size);
                if (count > 0) {
                    position += count;
                    size -= count;
                }

            }

            long d2 = System.currentTimeMillis();
            System.out.println("fileChannelRead读取完成，耗时：" + (d2 - d1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (output != null) {
                    output.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void mapRead(File file, File newFile) {
        FileChannel fcin = null;
        FileChannel fout = null;

        try {
            fcin = new FileInputStream(file).getChannel();
            fout = new FileOutputStream(newFile).getChannel();

            MappedByteBuffer mbb = fcin.map(FileChannel.MapMode.READ_ONLY, 0, fcin.size());
            fout.write(mbb);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fcin != null) {
                    fcin.close();
                }
                if (fout != null) {
                    fout.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
