package demo.michael.synopsis.communication.pipeline;

import java.io.*;

/**
 * @className: Main
 * @description: 已内存为媒介，用户线程之间的数据传输
 * 面向字节: [PipedInputStream、pipedOutputStream]
 * 面向字符: [PipedReader、PipedWriter]
 * @author: charles
 * @date: 12/29/21
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();

        pipedOutputStream.connect(pipedInputStream);

        new Thread(new Reader(pipedInputStream)).start();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            pipedOutputStream.write(bufferedReader.readLine().getBytes());
        } finally {
            pipedOutputStream.close();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

}
