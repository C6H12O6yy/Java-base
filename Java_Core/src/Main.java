import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String emailChar = "@";
    private static final String threeDotChar = "...";
    private static final String emptyChar = "";
    private static final int numZero = 0;

    public static void main(String[] args) {
        List <String> emailList = new ArrayList<>();
        emailList.add("tran.the.dung@vsi-international.com");
        emailList.add("JavaCore");
        emailList.add("@@@gmail.com");
        emailList.add("Java");
        emailList.add("List");
        emailList.add("ArrayList");
        emailList.add("dungtt@gmail.com");
        emailList.add("d@ung@vsi-international.com");
        emailList.add("VietSoftw@re@vsi-international.com");
        emailList.add(null);
        List<String> resultList = formatEmailList(emailList);

        for (String element : resultList) {
            System.out.println(element);
        }
    }
    private static List<String> formatEmailList(List<String> emailList) {
        List<String> listEmailFormated = new ArrayList<>();

        for(String e : emailList) {
            try {
                if (e.contains("@")) {
                    int lastCharIndex = e.lastIndexOf(emailChar);
                    String preIndex = e.substring(numZero, lastCharIndex).replaceAll(emailChar, emptyChar);
                    String emailFormated = preIndex + emailChar + threeDotChar;
                    listEmailFormated.add(emailFormated);
                }
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }

        }
        return listEmailFormated;
    }
}