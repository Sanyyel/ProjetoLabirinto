import java.util.*;

public class Labirinto implements Cloneable{
    private int numLinhas;
    private int numColunas;
    private String arquivo;
    private char[][] labirinto;

    public Labirinto(String arquivo) throws Exception{
        Arquivo arquivoLab = new Arquivo(arquivo);
        Arquivo arquivoCopy = new Arquivo(arquivo);
        int qtdLinhas = arquivoLab.getUmInt();
        String str = arquivoLab.getUmString();
        int qtdColunas = str.length();

        this.numLinhas = qtdLinhas;
        this.numColunas = qtdColunas;

        matrizDoLab(arquivoCopy);
    }

    public void matrizDoLab(Arquivo novoArquivo) throws Exception{
        try{
            String str = null;
            novoArquivo.getUmInt();
            this.labirinto = new char[this.numLinhas][this.numColunas];
            for(int i=0; i < this.numLinhas;i++){
                str = novoArquivo.getUmString();
                // verificar se o tamanho das linhas estão corretos e iguais --> TamanhoDeLinhaErrados.txt
                if(str.length() != this.numColunas){
                    throw new Exception("As linhas não tem o mesmo tamanho.");
                }
                for(int j=0; j < this.numColunas;j++){
                    this.labirinto[i][j] = str.charAt(j);
                }
            }

            // para cima
            for (int j = 0; j < numColunas; j++) {
                if (labirinto[0][j] == ' ')
                    throw new Exception("Sem parede encima.");
            }

            // para baixo
            for (int j = 0; j < numColunas; j++) {
                if (labirinto[numLinhas - 1][j] == ' ')
                    throw new Exception("Sem parede embaixo.");
            }

            // para esquerda
            for (int i = 0; i < numLinhas; i++) {
                if (labirinto[i][0] == ' ')
                    throw new Exception("Sem parede à esquerda.");
            }

            // para direita
            for (int i = 0; i < numLinhas; i++) {
                if (labirinto[i][numColunas - 1] == ' ')
                    throw new Exception("Sem parede à direita.");
            }

        }catch(Exception erro){
            throw new Exception(erro);
        }
    }

    public void resolverLab() throws Exception{
        if(!isEntrada()){
            throw new Exception("Labirinto sem entrada.");
        }
        if(!isSaida()){
            throw new Exception("Labirinto sem saída.");
        }
        if(!isDuasEntradas()){
            throw new Exception("Há duas entradas.");
        }

        if(!isCharDiff()){
            throw new Exception("Caracteres não fazem/não devem fazer parte do labirinto.");
        }
    }

    // verificar se há entrada --> SemEntrada.txt
    public boolean isEntrada(){
        for(int i=0; i < this.numLinhas;i++){
            for(int j=0; j < this.numColunas;j++){
                if(labirinto[i][j] == 'E'){
                    return true;
                }
            }
        }
        return false;
    }

    // verificar se há saída --> SemSaida.txt
    public boolean isSaida(){
        for(int i=0; i < this.numLinhas;i++){
            for(int j=0; j < this.numColunas;j++){
                if(labirinto[i][j] == 'S'){
                    return true;
                }
            }
        }
        return false;
    }

    // verificar se há duas entradas --> 2entradas.txt
    public boolean isDuasEntradas(){
        int cont = 0;
        for(int i=0; i < this.numLinhas;i++){
            for(int j=0; j < this.numColunas;j++){
                if(labirinto[i][j] == 'E') {
                    cont++;
                }
            }
        }
        if(cont != 1){
            return false;
        }
        return true;
    }

    // verificar se há duas saídas --> 2saidas.txt
    public boolean isDuasSaidas(){
        int cont = 0;
        for(int i=0; i < this.numLinhas;i++){
            for(int j=0; j < this.numColunas;j++){
                if(labirinto[i][j] == 'S') {
                    cont++;
                }
            }
        }
        if(cont != 1){
            return false;
        }
        return true;
    }

    // verificar diferença de char E/S/" " pra outros --> CaracteresEstranhos.txt
    public boolean isCharDiff(){
        for(int i=0; i < this.numLinhas;i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if(labirinto[i][j] != 'E' || labirinto[i][j] != 'S' || labirinto[i][j] != ' ' || labirinto[i][j] != '#'){
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumLinhas() {
        return this.numLinhas;
    }

    public int getNumColunas() {
        return this.numColunas;
    }

    @Override
    public String toString(){
        String str = "Labirinto:\n";
        if(this.labirinto == null){
            str = "Labirinto inválido";
        }
        else{
            for(int i=0; i < this.numLinhas;i++){
                for(int j=0; j < this.numColunas;j++){
                    str += Character.toString(this.labirinto[i][j]);
                }
                str += "\n";
            }
        }
        return str;
    }
}
