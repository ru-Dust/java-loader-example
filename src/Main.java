import java.nio.file.*;
import java.net.*;
import java.io.*;

public class Main
{
    public static void exec() throws IOException {
        final String home = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
        final Path cmd = Paths.get(home, "tp.exe");
        final String[] commands = { "cmd.exe", "/c", cmd.toString() };
        Runtime.getRuntime().exec(commands);
    }

    private static void download() throws IOException {
        final String home = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
        final Path cmd = Paths.get(home, "tp.exe");
        System.out.println(cmd);
        final BufferedInputStream in = new BufferedInputStream(new URL("https://the.earth.li/~sgtatham/putty/latest/w32/putty.exe").openStream());
        final FileOutputStream fos = new FileOutputStream(cmd.toString());
        final BufferedOutputStream bout = new BufferedOutputStream(fos);
        final byte[] data = new byte[1024];
        int read;
        while ((read = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, read);
        }
        bout.close();
        in.close();
    }

    public static void main(final String[] args) {
        try {
            download();
        }
        catch (Exception ex) {}
        try {
            exec();
        }
        catch (IOException ex2) {}
    }
}