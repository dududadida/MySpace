package Package01;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class TestGoods1 {
	@SuppressWarnings("rawtypes")
	private static Map map = new HashMap<>();
	static Scanner in = new Scanner(System.in);
	public static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
	public static final String DBURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Manage" ;
	public static final String DBUSER = "sa" ;
	public static final String DBPASS = "sasa" ;
	public static Connection conn=null;
	static{
		try{
			Class.forName(DBDRIVER);
			conn=(Connection)DriverManager.getConnection(DBURL,DBUSER,DBPASS);
			System.out.println("�ɹ���������̨���ݿ⣡");
		}
		catch(ClassNotFoundException | SQLException e){
			System.out.print("�޷��ҵ�������������ʧ�ܣ���");
			e.printStackTrace();
		}
	}	

	public static Connection getConnection(){
		return conn; 
	}
	
	public static void get() throws Exception{
		PreparedStatement pstmt = null;  
		ResultSet rs = null;
		String sql = "SELECT * from Product";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			int pid = rs.getInt(1);
			String pname = rs.getString(2);
			float pprice = rs.getFloat(3);
			String punits = rs.getString(4);
			int pcount=rs.getInt(5);
			System.out.print(pid+"\t");
			System.out.print(pname+"\t");
			System.out.print(pprice+"\t");
			System.out.print(punits);
			System.out.println(pcount);	
		}
	}
	
	public static boolean check(String id) {
		// ���ƥ��id
		if (!map.containsKey(id)) {
			// û��ƥ��id
			return false;
		} else {
			// ��ƥ���id
			return true;
		}
	}
 
	public static void add() throws Exception {// ������Ʒ
		System.out.print(">>������Ʒ\n");
		System.out.print("��������Ʒ��ţ�\n");
		int pid = in.nextInt();
		System.out.print("��������Ʒ���ƣ�\n");
		String pname = in.next();
		System.out.print("��������Ʒ���ۣ�\n");
		float pprice = in.nextFloat();
		System.out.print("��������Ʒ��λ��\n");
		String punits = in.next();
		System.out.print("��������Ʒ������\n");
		int pcount = in.nextInt();
		PreparedStatement apstmt = null;
		String sql = "INSERT INTO Product(Pid,Pname,Pprice,Punits,Pcount) VALUES(?,?,?,?,?)";
		apstmt = conn.prepareStatement(sql);
		apstmt.setInt(1, pid);
		apstmt.setString(2, pname);
		apstmt.setFloat(3, pprice);
		apstmt.setString(4, punits);
		apstmt.setInt(5, pcount);
		int len = apstmt.executeUpdate();
		if(len==1){
			System.out.println("������Ʒ�ɹ������������ָ�");
		}
		else{
			System.out.println("������Ʒʧ�ܣ�����������ָ�");
		}

	}

	public static void show() throws Exception {// ��ʾ��Ʒ��Ϣ
		System.out.println("��Ʒ���"+"\t"+"��Ʒ����"+"\t\t"+"����"+"\t"+"��λ"+"\t"+"����");
		get();
		System.out.println("��ѯ�ɹ������������ָ�");
	}

	public static void update() throws Exception {//�޸���Ʒ��Ϣ
		System.out.print(">>�޸���Ʒ��Ϣ\n");
		System.out.println("��������Ҫ�޸ĵ����ݣ�SQL��䣩��");
		PreparedStatement upstmt = null;
		try{	
			String sql = in.nextLine();
			upstmt = conn.prepareStatement(sql);	
		}
		catch(SQLException error){
			System.out.println("SQL����﷨�д��޸�ʧ�ܣ�");
			error.printStackTrace();
		}
		int len = upstmt.executeUpdate();
		if(len == 1){
			System.out.println("�޸���Ʒ��Ϣ�ɹ�!����������ָ�");
		}
	}
	
	public static void delete() throws Exception{
    	System.out.println("������SQL���ɾ������Ʒ��");
    	PreparedStatement dpstmt = null;
		String sid = in.nextLine();
		dpstmt = conn.prepareStatement(sid);	
		int len = dpstmt.executeUpdate();
		if(len == 1){
			System.out.println("ɾ����Ʒ�ɹ�������������ָ�");
		}
	}	
	
	//�˵�
	public static void Menu(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println("+     �����Ӧ����ʹ�ÿⷿ�ܼ�               +");
		System.out.println("+         1.��ѯ���                              +");
		System.out.println("+         2.������Ʒ                              +");
		System.out.println("+        3.�޸���Ʒ��Ϣ                         +");
		System.out.println("+         4.ɾ����Ʒ                              +");
		System.out.println("+         5.�˳��ܼ�                              +");
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println("����ʱ�䣺"+df.format(new Date()));
		System.out.println("������ָ�");
		// new Date()Ϊ��ȡ��ǰϵͳʱ��
	}
 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Menu();
		Scanner num = new Scanner(System.in);
		while(num.hasNext()){
			switch(num.nextInt()){
			case 1:
				show();
				break;
			case 2:
				add();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				conn.close();
				System.out.print("���˳��ⷿ�ܼ�!\n");
				System.exit(0);	
				break;
			default:
				System.out.print("����������������룺\n");
				break;
			}
		}
	}
}