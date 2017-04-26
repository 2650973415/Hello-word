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
	//菜单
	public static void menu(File f){
		System.out.println("-------请选择-------");
		System.out.println("   1.登录");
		System.out.println("   2.注册");
		int num = sc.nextInt();
		if(num==1){
			System.out.println("-------登录-------");
			enter(f);
		}else if(num==2){
			System.out.println("-------注册-------");
			login(f);
		}
	}
	//登录
	public static void enter(File f){
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("用户名：");
		String username = sc.next();
		System.out.print("密　码：");
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
				System.out.println("登录成功！");
				menu(f);
			}else{
				System.out.println("密码错误！");
				menu(f);
			}
		}else if(!b){
			System.out.println("没有此用户！");
			menu(f);
		}
	}
	//注册
	public static void login(File f){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(f,"rw");
			String username = null;
			String password = null;
			System.out.print("username=");//用户名
			username = sc.next();
			System.out.print("password=");//密码
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
			System.out.println("注册成功！");
			menu(f);
		}
	}
}
