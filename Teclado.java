import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teclado
    {
        private static BufferedReader teclado =
                new BufferedReader (
                        new InputStreamReader(
                                System.in));

        public static String getUmString ()
        {
            String ret=null;

            try
            {
                ret = teclado.readLine ();
            }
            catch (IOException erro)
            {} // sei que nao vai dar erro

            return ret;
        }

        public static int getUmInt () throws Exception
        {
            int ret=0;

            try
            {
                ret = Integer.parseInt (teclado.readLine ());
            }
            catch (IOException erro)
            {} // sei que nao vai dar erro
            catch (NumberFormatException erro)
            {
                throw new Exception ("Int invalido!");
            }

            return ret;
        }
    }
