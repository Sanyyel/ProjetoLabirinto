import java.util.Collections;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
        Pilha<Coordenada> inverso;
        Pilha<Coordenada> caminho = new Pilha<>();
        try {
            System.out.println("Insira aqui o nome do arquivo do labirinto: ");
            String arquivo = Teclado.getUmString();
            Labirinto labirinto = new Labirinto(arquivo);
            inverso = labirinto.resolverLab();
            System.out.println(labirinto);
            try {
                for (int i = 0; i <= inverso.devolveTamanho(); i++){
                    System.out.println(inverso.recupereUmItem());
                    inverso.removaUmItem();
                }
            }
            catch(Exception erro){

            }
        }
        catch(Exception erro){
            System.err.println("Erro: " + erro.getMessage());
        }
    }

}
