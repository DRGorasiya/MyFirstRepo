import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        StreamWrapper error, output;

        try {
            Process proc = rt.exec("cmd /c C: && cd \"C:\\Users\\DG\\Downloads\\Claymore\" && chdir && EthDcrMiner64.exe -epool \"eu1.ethermine.org:4444\" -ewal \"0xD69af2A796A737A103F12d2f0BCC563a13900E6F\" -epsw \"x\"");
            output = StreamWrapper.getStreamWrapper(proc.getInputStream(), "OUTPUT");
            error = StreamWrapper.getStreamWrapper(proc.getErrorStream(), "ERROR");
            int exitVal = 0;

            error.start();
            output.start();
            error.join(3000);
            output.join(3000);
            exitVal = proc.waitFor();
//            System.out.println("Output: "+output.getMessage()+"\nError: "+error.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
