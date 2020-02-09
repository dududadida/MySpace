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
			System.out.println("成功连接至后台数据库！");
		}
		catch(ClassNotFoundException | SQLException e){
			System.out.print("无法找到驱动程序，连接失败！！");
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
		// 检测匹配id
		if (!map.containsKey(id)) {
			// 没有匹配id
			return false;
		} else {
			// 有匹配的id
			return true;
		}
	}
 
	public static void add() throws Exception {// 新增商品
		System.out.print(">>新增商品\n");
		System.out.print("请输入商品编号：\n");
		int pid = in.nextInt();
		System.out.print("请输入商品名称：\n");
		String pname = in.next();
		System.out.print("请输入商品单价：\n");
		float pprice = in.nextFloat();
		System.out.print("请输入商品单位：\n");
		String punits = in.next();
		System.out.print("请输入商品数量：\n");
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
			System.out.println("新增商品成功！请继续输入指令：");
		}
		else{
			System.out.println("新增商品失败！请重新输入指令：");
		}

	}

	public static void show() throws Exception {// 显示商品信息
		System.out.println("商品编号"+"\t"+"商品名称"+"\t\t"+"单价"+"\t"+"单位"+"\t"+"数量");
		get();
		System.out.println("查询成功！请继续输入指令：");
	}

	public static void update() throws Exception {//修改商品信息
		System.out.print(">>修改商品信息\n");
		System.out.println("请输入您要修改的内容（SQL语句）：");
		PreparedStatement upstmt = null;
		try{	
			String sql = in.nextLine();
			upstmt = conn.prepareStatement(sql);	
		}
		catch(SQLException error){
			System.out.println("SQL语句语法有错，修改失败！");
			error.printStackTrace();
		}
		int len = upstmt.executeUpdate();
		if(len == 1){
			System.out.println("修改商品信息成功!请输入其它指令：");
		}
	}
	
	public static void delete() throws Exception{
    	System.out.println("请输入SQL语句删除的商品：");
    	PreparedStatement dpstmt = null;
		String sid = in.nextLine();
		dpstmt = conn.prepareStatement(sid);	
		int len = dpstmt.executeUpdate();
		if(len == 1){
			System.out.println("删除商品成功！请输入其它指令：");
		}
	}	
	
	//菜单
	public static void Menu(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println("+     输入对应按键使用库房管家               +");
		System.out.println("+         1.查询库存                              +");
		System.out.println("+         2.新增商品                              +");
		System.out.println("+        3.修改商品信息                         +");
		System.out.println("+         4.删除商品                              +");
		System.out.println("+         5.退出管家                              +");
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println("登入时间："+df.format(new Date()));
		System.out.println("请输入指令：");
		// new Date()为获取当前系统时间
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
				System.out.print("已退出库房管家!\n");
				System.exit(0);	
				break;
			default:
				System.out.print("输入错误！请重新输入：\n");
				break;
			}
		}
	}
}