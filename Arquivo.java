import java.io.*;

public class Arquivo {
    private BufferedReader buffR;

    public Arquivo(String labirinto) throws Exception {
        try {
            buffR = new BufferedReader(new FileReader(labirinto));
        } catch (IOException erro) {
            throw new Exception("Local do arquivo não existe");
        }
    }

    public String getUmString() {
        String str = null;

        try {
            str = buffR.readLine();
        } catch (IOException erro) {
        }
        return str;
    }

    public int getUmInt() throws Exception{
        int ret = 0;

        try{
            ret = Integer.parseInt(buffR.readLine());
        }catch(IOException erro){

        }
        catch(NumberFormatException error){
            throw new Exception("Número não é inteiro.");
        }
        return ret;
    }
}
