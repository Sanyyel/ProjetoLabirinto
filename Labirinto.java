import java.util.*;

public class Labirinto implements Cloneable {
    private int numLinhas;
    private int numColunas;
    private String arquivo;
    private char[][] labirinto;
    private Coordenada atual;
    private Fila<Coordenada> fila;
    private Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidades;
    private Pilha<Coordenada> inverso;

    public Labirinto(String arquivo) throws Exception {
        Arquivo arquivoLab = new Arquivo(arquivo);
        Arquivo arquivoCopy = new Arquivo(arquivo);
        int qtdLinhas = arquivoLab.getUmInt();
        String str = arquivoLab.getUmString();
        int qtdColunas = str.length();



        this.numLinhas = qtdLinhas;
        this.numColunas = qtdColunas;

        matrizDoLab(arquivoCopy);
    }

    public void matrizDoLab(Arquivo novoArquivo) throws Exception {
        try {
            String str = null;
            novoArquivo.getUmInt();
            this.labirinto = new char[this.numLinhas][this.numColunas];
            for (int i = 0; i < this.numLinhas; i++) {
                str = novoArquivo.getUmString();
                // verificar se o tamanho das linhas estão corretos e iguais --> TamanhoDeLinhaErrados.txt
                if (str.length() != this.numColunas) {
                    throw new Exception("As linhas não tem o mesmo tamanho.");
                }
                for (int j = 0; j < this.numColunas; j++) {
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

        } catch (Exception erro) {
            throw new Exception(erro);
        }
    }

    private void procurarAdj() throws Exception
    {
        fila = new Fila<Coordenada> ();

            // direita
        if (atual.getColuna()+1 < this.numColunas) {
            if (this.labirinto[atual.getLinha()][atual.getColuna() + 1] == ' ' || this.labirinto[atual.getLinha()][atual.getColuna() + 1] == 'S') {
                fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna() + 1));
            }
        }

            // esquerda
        if (atual.getColuna()-1 >= 0) {
            if (this.labirinto[atual.getLinha()][atual.getColuna() - 1] == ' ' || this.labirinto[atual.getLinha()][atual.getColuna() - 1] == 'S') {
                fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna() - 1));
            }
        }

            // baixo
        if (atual.getLinha()+1 < this.numLinhas) {
            if (this.labirinto[atual.getLinha() + 1][atual.getColuna()] == ' ' || this.labirinto[atual.getLinha() + 1][atual.getColuna()] == 'S') {
                fila.guardeUmItem(new Coordenada(atual.getLinha() + 1, atual.getColuna()));
            }
        }

            // cima
        if (atual.getLinha()-1 >= 0) {
            if (this.labirinto[atual.getLinha() - 1][atual.getColuna()] == ' ' || this.labirinto[atual.getLinha() - 1][atual.getColuna()] == 'S') {
                fila.guardeUmItem(new Coordenada(atual.getLinha() - 1, atual.getColuna()));
            }
        }
    }

    private Pilha<Coordenada> ExibirCoords() throws Exception{
        if(caminho.isVazia() == true){
            throw new Exception("Caminho está vazio.");
        }

        return caminho;
    }

    private void setCaminho(Coordenada pos)
    {
        this.labirinto[pos.getLinha()][pos.getColuna()] = '*';
    }

    private void limparCaminho(Coordenada pos){
        this.labirinto[pos.getLinha()][pos.getColuna()] = ' ';
    }

    private boolean procuraSaida() throws Exception
    {
        for (int linha=0; linha<this.numLinhas; linha++) {
            for (int coluna=0; coluna<this.numColunas; coluna++ )
            {
                if (labirinto[linha][coluna] == 'S')
                    return true;
            }
        }
        return false;
    }

    private void preencherCaminho () throws Exception
    {
        for (int i = 0; i < this.numLinhas; i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if (labirinto[i][j] == 'E') {
                    atual = new Coordenada(i,j);
                }
            }
        }

        caminho = new Pilha<Coordenada>();
        possibilidades = new Pilha<Fila<Coordenada>>();

        //Modo progressivo
        for(;;)
        {
            this.procurarAdj();
            while (fila.isVazia())
            {
                //Modo Regressivo
                atual = caminho.recupereUmItem();
                caminho.removaUmItem();

                if (labirinto[atual.getLinha()][atual.getColuna()] == '*') {
                    this.limparCaminho(atual);
                }

                fila = possibilidades.recupereUmItem();
                possibilidades.removaUmItem();
            }

            if (fila == null)
                throw new Exception ("Não existe solução para este labirinto !");

            atual = fila.recupereUmItem();
            fila.removaUmItem();

            if (labirinto[atual.getLinha()][atual.getColuna()] == ' ')
                this.setCaminho(atual);

            caminho.guardeUmItem(atual);
            possibilidades.guardeUmItem(fila);

            if (labirinto[atual.getLinha()][atual.getColuna()] == 'S')
                return;
        }
    }
    public Pilha<Coordenada> resolverLab() throws Exception {
        if (!isEntrada()) {
            throw new Exception("Labirinto sem entrada.");
        }
        if (!isSaida()) {
            throw new Exception("Labirinto sem saída.");
        }
        if (!isDuasEntradas()) {
            throw new Exception("Há duas entradas.");
        }

        if (!isDuasSaidas()) {
            throw new Exception("Há duas saidas.");
        }

        if (isCharDiff()) {
            throw new Exception("Caracteres não fazem/não devem fazer parte do labirinto.");
        }

        if (!this.procuraSaida())
            throw new Exception ("O labirinto não possui saída válida!");
        this.preencherCaminho();

        return ExibirCoords();

    }


    // verificar se há entrada --> SemEntrada.txt
    public boolean isEntrada() {
        for (int i = 0; i < this.numLinhas; i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if (labirinto[i][j] == 'E') {
                    return true;
                }
            }
        }
        return false;
    }

    // verificar se há saída --> SemSaida.txt
    public boolean isSaida() {
        for (int i = 0; i < this.numLinhas; i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if (labirinto[i][j] == 'S') {
                    return true;
                }
            }
        }
        return false;
    }

    // verificar se há duas entradas --> 2entradas.txt
    public boolean isDuasEntradas() {
        int cont = 0;
        for (int i = 0; i < this.numLinhas; i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if (labirinto[i][j] == 'E') {
                    cont++;
                }
            }
        }
        if (cont != 1) {
            return false;
        }
        return true;
    }

    // verificar se há duas saídas --> 2saidas.txt
    public boolean isDuasSaidas() {
        int cont = 0;
        for (int i = 0; i < this.numLinhas; i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if (labirinto[i][j] == 'S') {
                    cont++;
                }
            }
        }
        if (cont != 1) {
            return false;
        }
        return true;
    }

    // verificar diferença de char E/S/" " pra outros --> CaracteresEstranhos.txt
    public boolean isCharDiff() {
        for (int i = 0; i < this.numLinhas; i++) {
            for (int j = 0; j < this.numColunas; j++) {
                if (" ES#".indexOf(Character.toString(this.labirinto[i][j])) == -1){
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
    public String toString() {
        String str = "Labirinto:\n";
        if (this.labirinto == null) {
            str = "Labirinto inválido";
        } else {
            for (int i = 0; i < this.numLinhas; i++) {
                for (int j = 0; j < this.numColunas; j++) {
                    str += Character.toString(this.labirinto[i][j]);
                }
                str += "\n";
            }
        }
        return str;
    }
    
}
