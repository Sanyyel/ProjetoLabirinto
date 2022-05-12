public class Coordenada {
    private double x;
    private double y;

    public Coordenada (double xInicial, double yInicial)
    {
        x = xInicial;
        y = yInicial;
    }

    public Coordenada ()
    {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
