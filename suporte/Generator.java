package suporte;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//Metodo para criação do TimeStamp
public class Generator {
    public static String dataHoraParaArquivo(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMddhhmmss").format(ts);
    }
}
