
public class Test {
	public static final int KEY = 7;
	private static String str = "Hello world!";
	
	public static String code(String str){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = (char) (str.charAt(i) ^ KEY);
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("ԭ�ַ�����" + str);
		String change = code(str);
		System.out.println("���ܺ�" + change);
		System.out.println("���ܺ�" + code(change));
	}

}
