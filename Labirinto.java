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

    public void matrizDoLab(Arquivo novoArquivo){
        try{
            String str = null;
            novoArquivo.getUmInt();
            this.labirinto = new char[this.numLinhas][this.numColunas];
            for(int i=0; i < this.numLinhas;i++){
                str = novoArquivo.getUmString();
                for(int j=0; j < this.numColunas;j++){
                    this.labirinto[i][j] = str.charAt(j);
                }
            }

        }catch(Exception erro){

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
    }

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

    public boolean isCharDiff(){
        // fazer diferença de char E/S/" " pra outros
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
