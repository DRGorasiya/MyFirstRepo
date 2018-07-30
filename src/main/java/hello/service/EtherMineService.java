package hello.service;

import hello.threads.StreamWrapper;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class EtherMineService {

    private final static String location = getBasePath();

    private Process process;

    private static String getBasePath() {
        return Paths.get("C:\\Users\\DG\\Downloads\\Claymore").toString();
    }

    public void startMining() {
        try {
            String cmd = "cmd /c " + location.substring(0, 2) + " && cd \"" + location + "\" && EthDcrMiner64.exe -epool \"eu1.ethermine.org:4444\" -ewal \"0xD69af2A796A737A103F12d2f0BCC563a13900E6F\" -epsw \"x\"";
            runMinner(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int runMinner(String cmd) {

        StreamWrapper error = null, output = null;
        try {
            process = Runtime.getRuntime().exec(cmd);

            error = StreamWrapper.getStreamWrapper(process.getInputStream(), StreamWrapper.Type.ERROR);
            output = StreamWrapper.getStreamWrapper(process.getErrorStream(), StreamWrapper.Type.ERROR);
            error.start();
            output.start();

            //int retCode = process.waitFor();
            return 0;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            if (error != null && error.isAlive()) {
                error.interrupt();
            }
            if (output != null && output.isAlive()) {
                output.interrupt();
            }
            try {
                if (process.isAlive()) {
                    process.destroy();
                }
            } catch (Exception ee) {
                try {
                    process.destroyForcibly();
                } catch (Exception ignored) {
                }
            } finally {
                process = null;
            }
        }
        return 1;
    }
}
