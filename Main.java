import java.io.*;

public class Main extends Coordenada{
    public static void main (String[] args) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("/Users/u21420/Downloads/SemEntrada.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
        }
        Coordenada mapa = new Coordenada();
        char [][] labirinto = new char[0][0];
        System.out.println(labirinto);
    }
}
