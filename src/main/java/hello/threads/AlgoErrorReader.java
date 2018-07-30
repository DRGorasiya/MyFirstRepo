package hello.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlgoErrorReader extends Thread {

    private final InputStream inputStream;
    private boolean stop = false;

    public AlgoErrorReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        if(inputStream != null) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while (!stop && (line = br.readLine()) != null) // searches for state in the child process output
                {
                    System.err.println(line);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException("Failed to read error");
            }
        }
    }

    public void setStop(boolean b) {
        stop = b;
    }
}
