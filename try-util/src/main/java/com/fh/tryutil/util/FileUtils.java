package com.fh.tryutil.util;

import com.fh.tryutil.bean.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author jhYang
 * @date 2019/8/17 0017
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 按行读取文件(字符)
     *
     * @param filename
     */
    public static void ReadFileByLine(String filename) {

        File file = new File(filename);

        try(
                InputStream is = new FileInputStream(file);
                Reader reader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("【文件不存在...】",e);
        } catch (IOException e) {
            LOGGER.error("【IO异常...】",e);
        }
    }



    /**
     * 按字节读取文件
     *
     * @param filename
     */
    public static void ReadFileByBytes(String filename) {
        File file = new File(filename);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            int index = 0;
            while (-1 != (index = is.read())) {
                System.out.write(index);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------按字节读出-------------");
        try {
            is = new FileInputStream(file);
            //一次读多个字节
            byte[] tempbyte = new byte[1024];
            int index = 0;
            while (-1 != (index = is.read(tempbyte))) {

                //println（）输出字符  write()输出字节
                // write参数的含义：要输出的字符数组，起始下标和结束下标
                System.out.write(tempbyte, 0, index);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("-----------按字节读出   2 -------------");
    }


    /**
     * 按字符读取文件
     *
     * @param filename
     */
    public static void ReadFileByChar(String filename) {
        File file = new File(filename);
        InputStream is = null;
        Reader isr = null;
        try {
            is = new FileInputStream(file);
            isr = new InputStreamReader(is);
            int index = 0;
            while (-1 != (index = isr.read())) {
                //字符 但是没有使用缓冲池
                System.out.print((char) index);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != isr) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        String filename = "D:/ceshi.txt";
        String test = test(filename);

        System.out.println(filename);
        System.out.println(test);


        int aa = 33;
        int testa = testa(aa);
        System.out.println(aa);
        System.out.println(testa);

        Student student = new Student();
        Student testv = testv(student);
        System.out.println(student.getName());
        System.out.println(testv.getName());

//      ReadFileByLine(filename);
//      ReadFileByBytes(filename);
        ReadFileByChar(filename);
        System.out.println("------结束--------");


//         ReadFileByLine(filename);
//         ReadFileByBytes(filename);
//         ReadFileByChar(filename);
//        String writeFile = "javawrite2file.txt";
//        // Write2FileByOutputStream(writeFile);
//        // Write2FileByBuffered(writeFile);
//        Write2FileByFileWriter(writeFile);
    }

    public static String test(String sss){
        sss = "XXX";
        System.out.println(sss);
        return sss;
    }

    public static int testa(int sss){
        sss = 222;
        System.out.println(sss);
        return sss;
    }

    public static Student testv(Student student){
        student.setName("dddd");
        System.out.println(student.getName());
        return student;
    }
    /**
     * 通过OutputStreamWriter写文件
     *
     * @param filename
     */
    public static void Write2FileByOutputStream(String filename) {
        File file = new File(filename);
        FileOutputStream fos = null;
        // BufferedOutputStream bos = null;
        OutputStreamWriter osw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            osw.write("Write2FileByOutputStream");
            // bos = new BufferedOutputStream(fos);
            // bos.write("Write2FileByOutputStream".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过BufferedWriter写文件
     *
     * @param filename
     */
    public static void Write2FileByBuffered(String filename) {
        File file = new File(filename);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write("Write2FileByBuffered");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过FileWriter写文件
     *
     * @param filename
     */
    public static void Write2FileByFileWriter(String filename) {
        File file = new File(filename);
        FileWriter fw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write("Write2FileByFileWriter");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将字符内容输出到文件
     * @param bytes  字节内容
     * @param fileName 文件名
     */
    public static void getFileByBytes(byte[] bytes ,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            File file = new File(fileName);

            //输出流
            fos = new FileOutputStream(file);

            //缓冲流
            bos = new BufferedOutputStream(fos);

            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
