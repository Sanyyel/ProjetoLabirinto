import java.io.*;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
        try {
            System.out.println("Insira aqui o nome do arquivo do labirinto: ");
            String arquivo = Teclado.getUmString();
            Labirinto labirinto = new Labirinto(arquivo);
            labirinto.resolverLab();
            System.out.println(labirinto);
        }
        catch(Exception erro){
            System.err.println("Erro: " + erro.getMessage());
        }
