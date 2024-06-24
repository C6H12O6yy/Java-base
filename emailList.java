package sinh;

import java.util.ArrayList;
import java.util.List;

public class emailList {

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
		List<String> resultList = formatEmailList(emailList);
        
        for (String element : resultList) {
            System.out.println(element);
        }
	}
	private static List<String> formatEmailList(List<String> emailList) {
		List<String> listEmailFormated = new ArrayList<>();
		
		for(String e : emailList) {
			if (e.contains("@")) {
				int lastCharIndex = e.lastIndexOf("@");
				String preIndex = e.substring(0, lastCharIndex).replaceAll("@", "");
				String emailFormated = preIndex + "@" + "...";
				listEmailFormated.add(emailFormated);
			}
		}
		return listEmailFormated;
	}
}
