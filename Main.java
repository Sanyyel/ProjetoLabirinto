import java.io.*;

public class Main extends Coordenada{
    public static void main (String[] args) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("C:\projetos\untitled\ProjetoLabirinto\src\SemEntrada.txt"));
            // se for testar acima na sua máquina, ponha o caminho do arquivo nesse FileReader();
            
            String str;
            StringBuilder sb = new StringBuilder(str); // peguei isso pra dar .deteleCharAt() e tentar apagar os números do labirinto
            while ((str = in.readLine()) != null) {
                if(Objects.equals(str.charAt(0), "123456789") && Objects.equals(str.charAt(1), "123456789")){

                    sb.deleteCharAt(0); // aqui tento apagar o número do labirinto


                    // aqui embaixo eu deveria inserir o mapa numa matriz de caracteres à partir das linhas e colunas
                    // da classe Coordenada e fazer as restrições: sem entrada, sem saída, E ou S fora da margem e etc,
                    // o que ainda não cheguei a fazer
                }
                /*char[] mapa = str.toCharArray();
                int l;
                for(l = 0; l <= mapa.length; l++){
                    for(c = 0; c <= mapa.length; c++){
                        System.out.println(mapa [l][c]);
                    }
                }

                 */
            }
            in.close();
        } catch (IOException e) {
        }
        Coordenada mapa = new Coordenada();
        char [][] labirinto = new char[0][0];
        System.out.println(labirinto);
    }
}
