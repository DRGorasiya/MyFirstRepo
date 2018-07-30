package hello.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamWrapper extends Thread {
    private InputStream is = null;
    private Type type = null;
    private String message = null;

    public String getMessage() {
        return message;
    }

    StreamWrapper(InputStream is, Type type) {
        this.is = is;
        this.type = type;
    }

    public static StreamWrapper getStreamWrapper(InputStream is, Type type) {
        return new StreamWrapper(is, type);
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                if (type.equals(Type.ERROR))
                    System.out.println(line);
                else
                    System.err.println(line);
//                buffer.append(line);//.append("\n");
            }
//            message = buffer.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public enum Type {
        ERROR,
        OUTPUT
    }
}
