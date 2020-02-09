package password;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 该类用于实现13位随机密码生成器
 * @author wangzheng
 *
 */
public class PSDActiver extends JPanel{
  private static final long serialVersionUID = 1L;      //版本
  public static final int WIDTH = 420;  //宽
  public static final int HEIGHT = 300;  //高
  public static BufferedImage back;   //传入加载图片
  private static String passch;      //13位密码值
  private static Font font = new Font("宋体", Font.PLAIN, 20);;
  private final static JFrame frame = new JFrame();   //创建主体对象
  private static PSDActiver pwd = new PSDActiver();   //创建生成器对象
    
  //重写绘制方法
  public void paint(Graphics g){
    paintLoad(g);
  }
	
  //画生成器界面提示和背景图
  public void paintLoad(Graphics g){ 
    g.drawImage(back, 0, 0, null);
    g.setColor(new Color(0xff0000));
    g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
    g.drawString("请点击按钮生成16位随机密码！", 100, 60);
  }
	
  //加载静态资源
  static{    
    try {
      back = ImageIO.read(PSDActiver.class.getResource("background.png"));
	  //添加按键
	  JButton active = new JButton("生成密码");
	  active.setBounds(WIDTH/3, HEIGHT*3/5, 140, 25);
	  frame.add(active);
	      
	  //绘制文本区域
	  final JTextArea pString = new JTextArea();
	  pString.setFont(font);
	  pString.setBounds(130, HEIGHT*2/5, 160, 28);
	  frame.add(pString);
	      
	  //为按键添加事件侦听
	  active.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
	    JOptionPane.showMessageDialog(frame,"生成完毕！密码记录将保存至D盘下的Record.txt文件中。");       //弹出提示框
	    Active();
	    try {
          Record();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
	      pString.setText(passch);     //设置文本域字体格式
	    }
	  });
	      
	  //绘制主体对象
      frame.add(pwd);   
	  frame.setTitle("隔壁老王密码生成器-_-");           //设置标题
	  frame.setSize(WIDTH, HEIGHT);
	  frame.setAlwaysOnTop(true);               //总在顶层
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setLocationRelativeTo(null);            //窗口居中
	  frame.setResizable(false);            //设置窗体无法缩放
	      
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
  }
	
  //密码算法
  public static void Active(){
  passch = "";
  Random random = new Random();
    for (int i = 0; i < 16; i++) {
      int num = random.nextInt(3);
      switch(num){
        case 0:
          char ch = (char)(random.nextInt(26)+'a'); 
          passch += ch;
          break;
        
        case 1:
          char ch1 = (char)(random.nextInt(26)+'A'); 
          passch += ch1;
          break;
          
        case 2:
          passch += random.nextInt(10);
          break;
	       
        default:
          break;
      }
    }
  }
	
  public static void Record() throws IOException{
	Date date = new Date();
	String dateformat = "yyyy-MM-dd hh:mm:ss E a";
    SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
    String str = sdf.format(date);
    FileOutputStream fos = new FileOutputStream("D:\\Record.txt", true);
    OutputStreamWriter osw  = new OutputStreamWriter(fos, "UTF-8");
    PrintWriter pw = new PrintWriter(osw);
	try {
	  pw.println("密码:" + passch + "\t\t" + "生成时间" + str);
    } catch (Exception e) {
      e.printStackTrace();
    } finally{
      osw.close();
    }
  }
	
  public static void main(String[] args) {
    frame.setVisible(true);
  }
}