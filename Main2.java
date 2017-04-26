import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

public class Main2 {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args){
		File f = new File("a.properties");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		menu(f);
	}
	//�˵�
	public static void menu(File f){
		System.out.println("-------��ѡ��-------");
		System.out.println("   1.��¼");
		System.out.println("   2.ע��");
		int num = sc.nextInt();
		if(num==1){
			System.out.println("-------��¼-------");
			enter(f);
		}else if(num==2){
			System.out.println("-------ע��-------");
			login(f);
		}
	}
	//��¼
	public static void enter(File f){
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("�û�����");
		String username = sc.next();
		System.out.print("�ܡ��룺");
		String password = sc.next();
		Iterator<String> it=prop.stringPropertyNames().iterator();
		boolean b = false;
		while(it.hasNext()){
			if(it.next().equals(username)){
				b = true;
			}
		}
		if(b){
			if(prop.getProperty(username).equals(password)){
				System.out.println("��¼�ɹ���");
				menu(f);
			}else{
				System.out.println("�������");
				menu(f);
			}
		}else if(!b){
			System.out.println("û�д��û���");
			menu(f);
		}
	}
	//ע��
	public static void login(File f){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(f,"rw");
			String username = null;
			String password = null;
			System.out.print("username=");//�û���
			username = sc.next();
			System.out.print("password=");//����
			password = sc.next();
			raf.seek(raf.length());
			raf.write((username+"="+password+"\r").getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("ע��ɹ���");
			menu(f);
		}
	}
}
