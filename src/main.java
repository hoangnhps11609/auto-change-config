import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class main {
    static String actual_Project = "PAP", expected_Project = "PAP";
    static String actual_Port = "1522", expected_Port = "1522";
    static String actual_Host = "127.0.0.1", expected_Host = "127.0.0.1";
    static String actual_Orcl = "orcl18", expected_Orcl = "orcl18";
    static String actual_ST = "PAP_ST", expected_ST = "PAP_ST";
    static String actual_EDI = "PAP_EDI_DEV", expected_EDI = "PAP_EDI_DEV";
    static String actual_IF = "PAP_ST_IF", expected_IF = "PAP_ST_IF";
    static String rootURL = "D:\\CATOS_8.1_SERVER\\EXE";
    static String[] arrURL = {
            "\\EXE_C3IT_SERVER\\conf\\tsb.conf",
            "\\EXE_XIS_SERVER\\config\\server-applicationContext.properties",
            "\\EXE\\tnsnames.ora",
            "\\EXE_API\\BL\\config\\BILLING\\database-Info.properties",
            "\\EXE_API\\CFS\\config\\CFS\\database-Info.properties",
            "\\EXE_API\\GT\\config\\GT\\database-Info.properties",
            "\\EXE_API\\QU\\config\\QU\\database-Info.properties",
            "\\EXE_API\\STD\\config\\STD\\database-Info.properties",
            "\\EXE_EDI\\config\\DB_CONFIG.properties", "\\EXE_EDI\\config\\system.properties",
            "\\EXE_STAT\\conf\\server.xml", "\\EXE_STAT\\Statistics\\config\\server-applicationContext.properties",
            "\\EXE_WEB_CLIENT\\config\\server-applicationContext.properties",
            "\\EXE_WEB_MOBILE\\WebYt\\application-server.properties",
            "\\EXE_WEB_SOCKET\\config\\server-applicationContext.properties",
            "\\EXE_WEBIP\\config\\server-applicationContext.properties"
    };

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> listDomainURL = Arrays.asList(arrURL);
        for (String item : listDomainURL) {
            String url = rootURL + item;

            //Đọc file
            String textOut = new String(Files.readAllBytes(Paths.get(url)), StandardCharsets.UTF_8);

            //Thay đổi nội dung file
            String resultContent = textOut.replaceAll(actual_ST, expected_ST);
            resultContent = resultContent.replaceAll(actual_EDI, expected_EDI);
            resultContent = resultContent.replaceAll(actual_IF, expected_IF);
            resultContent = resultContent.replaceAll(actual_Port, expected_Port);
            resultContent = resultContent.replaceAll(actual_Orcl, expected_Orcl);
            resultContent = resultContent.replaceAll(actual_Host, expected_Host);
            resultContent = resultContent.replaceAll(actual_Project, expected_Project);

            //Ghi file mới
            try {
                FileWriter fw = new FileWriter(url);
                fw.write(resultContent);
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("SUCCESS!");
    }
}
