import java.util.Collections;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
        Pilha<Coordenada> inverso = new Pilha<>();
        Pilha<Coordenada> caminho = new Pilha<>();
        try {
            System.out.println("Insira aqui o nome do arquivo do labirinto: ");
            String arquivo = Teclado.getUmString();
            Labirinto labirinto = new Labirinto(arquivo);
            caminho = labirinto.resolverLab();
            System.out.println(labirinto);
            try {

                while (!caminho.isVazia()){
                    inverso.guardeUmItem(caminho.recupereUmItem());
                    caminho.removaUmItem();
                }

                while (!inverso.isVazia()){
                    System.out.println(inverso.recupereUmItem());
                    inverso.removaUmItem();
                }

                System.out.println("\n");
                System.out.println("Labirinto concluído!");
            }
            catch(Exception erro){
                System.err.println("Erro: " + erro.getMessage());
            }
        }
        catch(Exception erro){
            System.err.println("Erro: " + erro.getMessage());
        }
    }

}
