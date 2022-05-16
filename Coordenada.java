public class Coordenada {
    private int linha;
    private int coluna;

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public Coordenada(int l, int c) throws Exception {
        this.linha = l;
        this.coluna = c;
    }

    @Override
    public String toString() {
        return "(" + this.linha + "," + this.coluna + ")";
}
