import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
    static String actual_Host = "1530";
    static String expected_Host = "1522";
    static String actual_Local = "127.0.0.1";
    static String expected_Local = "localhost";
    static String actual_Orcl = "orcl30";
    static String expected_Orcl = "orcl18";
    static String actual_ST = "TEST_ST";
    static String expected_ST = "PAP_ST";
    static String actual_EDI = "TEST_EDI_DEV";
    static String expected_EDI = "PAP_EDI_DEV";
    static String actual_IF = "TEST_ST_IF";
    static String expected_IF = "PAP_ST_IF";
    static String rootURL = "D:\\PAP_SERVER\\EXE";
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


    public static void main(String[] args) throws FileNotFoundException{
        List<String> listDomainURL = Arrays.asList(arrURL);
        for (String item:listDomainURL){
            String url = rootURL + item;
            //Đọc file
            String textOut = readFile(url);
            //Thay đổi nội dung file
            String resultContent = changeContent(textOut, actual_ST, expected_ST);
            resultContent = changeContent(resultContent, actual_EDI, expected_EDI);
            resultContent = changeContent(resultContent, actual_IF, expected_IF);
            resultContent = changeContent(resultContent, actual_Host, expected_Host);
            resultContent = changeContent(resultContent, actual_Orcl, expected_Orcl);
            resultContent = changeContent(resultContent, actual_Local, expected_Local);
            //Ghi file mới
            saveFile(resultContent, url);
        }
        System.out.println("SUCCESS!");
    }

    //Đổi thông tin config
    public static String changeContent(String content, String actual, String expected){
        return content.replaceAll(actual, expected);
    }

    // Đọc dữ liệu từ File với Scanner
    public static String readFile(String url) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(url);
        Scanner scanner = new Scanner(fileInputStream);
        String textOut = "";
        try {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                textOut = textOut + nextLine + "\n";
            }
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
        return textOut;
    }

    public static void saveFile(String textOut, String url){
        try {
            FileWriter fw = new FileWriter(url);
            fw.write(textOut);
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
