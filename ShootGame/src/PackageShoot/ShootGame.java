package PackageShoot;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

/** 游戏主界面  */
public class ShootGame extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = -44647058529149928L;
    public static final int WIDTH = 400;	//宽
	public static final int HEIGHT = 654;	//高
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage gameover;
	public static BufferedImage pause;
	public static BufferedImage diji;
	public static BufferedImage buff;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	
	public Hero hero = new Hero();	//玩家对象
	public Bullet[] bullets = {};	//子弹数组
	public FlyingObject[] flyings = {};	//敌机和buff
	
	private int score = 0;	//记录分数
	private int state;	//状态
	public static final int START = 0; 
	public static final int RUNNING = 1; 
	public static final int PAUSE = 2; 
	public static final int GAME_OVER = 3; 
	
	static{	//加载图片资源
		try {
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			diji = ImageIO.read(ShootGame.class.getResource("diji.png"));
			buff = ImageIO.read(ShootGame.class.getResource("buff.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 重写绘制方法  */
	public void paint(Graphics g){	//g为画笔
		g.drawImage(background, 0, 0, null);	//画图片
		paintHero(g);	//画玩家
		paintBullets(g);	//画子弹
		paintFlyingObjects(g);	//画敌人
		paintScore(g);	//画分数
		paintState(g);	//画游戏状态
	}
	
	/** 画游戏状态  */
	public void paintState(Graphics g){
		switch(state){
		case START:	//启动图片
			g.drawImage(start, 0, WIDTH / 2, null);
			break;
		case PAUSE:	//暂停图片
			g.drawImage(pause, 0, WIDTH / 2, null);
			break;
		case GAME_OVER:	//停止图片
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	
	/** 画玩家  */
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	/** 画子弹  */
	public void paintBullets(Graphics g){
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];	//每个子弹对象
			g.drawImage(b.image, b.x, b.y, null);
		}
	}
	
	/** 画敌人  */
	public void paintFlyingObjects(Graphics g){
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);	//每个敌人对象
		}
	}
	
	/** 画分数  */
	public void paintScore(Graphics g){
		int x = 10, y = 25;
		g.setColor(new Color(0x00ff00));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		g.drawString("SCORE："+score, x, y);		//画得分
		g.drawString("LIFE："+hero.getLife(), x, y+15);	//画命
	}
	
	private Timer timer;	//定时器
	private int interval =10;	//定时间隔ms
	
	/** 启动执行动操作  */
	public void action(){
		
		//鼠标事件适配器
		MouseAdapter l = new MouseAdapter(){
			/** 重写mouseMoved()方法  */
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state == RUNNING){
					int x = e.getX();	//得到鼠标的x
					int y = e.getY();	//得到鼠标的y
					hero.moveTo(x, y);	//玩家移动
				}
			}
			
			/** 重写mouseClicked()方法  */
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					hero = new Hero();	//游戏结束重置数据
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					score = 0;
					state = START;
					break;
				}
			}
		
			/** 重写mouseExited()方法*/
			public void mouseExited(MouseEvent e){
				if(state != GAME_OVER){
					state = PAUSE;
				}
			}
			
			/** 重写mouseEntered()方法*/
			public void mouseEntered(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}
			}
		};
		
		//给当前面板添加鼠标点击侦听
		this.addMouseListener(l);
		
		//给当前面板添加了鼠标的滑动侦听
		this.addMouseMotionListener(l);
		
		timer = new Timer();	//创建定时器对象
		/** 匿名内部类  */
		timer.schedule(new TimerTask(){// 创建实现类的对象
			public void run(){	//重写定时执行方法run()
				if(state == RUNNING){
					enterAction();	//让飞行物入场---new对象
					stepAction();	//飞行物走步
					shootAction();	//射击
					bangAction();	//命中目标
					outOfBoundsAction();	//删除越界的飞行物
					checkGameOverAction();	//判断游戏是否结束
				}
				
				repaint();	//重绘（调用paint方法）
			}
		}, interval, interval);	//定时触发
	}

	/** 检查游戏是否结束  */
	public void checkGameOverAction(){
		if(isGameOver()){	//判断是否结束
			state = GAME_OVER;	//改变状态为结束
		}
	}
	
	/** 判断游戏是否结束  */
	public boolean isGameOver(){
		for(int i=0; i<flyings.length; i++){	//遍历所有飞行物
			int index = -1;	//记录撞上的飞行物的索引
			FlyingObject obj = flyings[i];	//每个飞行物
			if(hero.hit(obj)){	//撞上
				hero.substractLife();	//减命
				hero.setDoubleFire(0);	//设置火力还原
				index = i;	//记录被撞击索引
			}
			
			if(index != -1){	//有撞上的
				//删除被撞的飞行物
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t;
				flyings = (FlyingObject[]) Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		
		return hero.getLife() <= 0;	//返回是否有命 
	}
	
	/** 删除越界飞行物  */
	public void outOfBoundsAction(){
		int index = 0;	//下标
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];	//没有出界的元素
		for(int i=0; i<flyings.length; i++){	//遍历数组
			FlyingObject f = flyings[i];	//得到敌人
			if(!f.outOfBounds()){	//若不出界
				flyingLives[index++] = f;
			}
		}
		flyings = (FlyingObject[]) Arrays.copyOf(flyingLives, index);	//删除出界元素
		//删除所有出界的子弹
		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];
		for(int i=0; i<bullets.length; i++){
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = (Bullet[]) Arrays.copyOf(bulletLives, index);
	}
	
	/** 子弹打敌人  */
	public void bangAction(){	//遍历所有子弹
		for (int i=0; i<bullets.length; i++) {	//遍历每个子弹
			Bullet b = bullets[i];	//每一个子弹
			bang(b);	//子弹和飞行物碰撞
		}
	}
	
	/** 子弹打敌人  b:子弹  */
	public void bang(Bullet b){
		int index = -1;	//击中的飞行物的索引
		for (int i=0; i<flyings.length; i++) {	//遍历敌人
			FlyingObject obj = flyings[i];	//得到每一个敌人
			if(obj.shootBy(b)){	//碰撞检测
				index = i;	//记录被击中的飞机的下标
				break;
			}
		}
		
		if(index != -1){	//击中的飞行物的索引
			FlyingObject one = flyings[index];	//击中的敌人
			
			//得分或得buff
			if(one instanceof Enemy){	//若为敌人
				Enemy e = (Enemy)one;	//飞行物强转为敌人
				score += e.getScore();	//设置加分
			}else if(one instanceof Buffer){	//若为奖励
				Buff bf = (Buff)one;	//飞行物强转为buff
				int type = bf.getType();	//得到buff类型
				switch(type){
				case Buff.DOUBLE_FIRE:
					hero.addDoubleFire();	//双倍火力
					break;
				case Buff.LIFE:
					hero.addLife();		//加命
					break;
				}
			}	
			
			//删除被击中飞行物
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			//删除数组中最后一个元素
			flyings = (FlyingObject[])Arrays.copyOf(flyings, flyings.length - 1);
		}
	}
	
	int shootIndex = 0;	//控制射击频率
	
	/** 射击   */
	public void shootAction(){
		shootIndex ++;
		if(shootIndex % 50 == 0){	//500ms发一次
			Bullet[] bs = hero.shoot();	//玩家发射
			bullets = (Bullet[]) Arrays.copyOf(bullets, bullets.length + bs.length);	//扩容
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);	//追加
		}
	}
	
	int flyEnteredIndex = 0;	//飞行物入场计数
	/** 飞行物入场  */
	public void enterAction(){	//每10ms走一次
		flyEnteredIndex ++;	//走一次index+1
		if(flyEnteredIndex % 20 == 0){	//10*20=200毫秒
			FlyingObject obj = nextOne();	//随机生成一个对象
			flyings = (FlyingObject[]) Arrays.copyOf(flyings, flyings.length + 1);	//扩容
			flyings[flyings.length - 1] = obj;	//将飞行物赋值到数组的最后一位
		}
	}
	
	/** 随机生成敌机或buff */
	//工厂方法：生产对象，一般为静态
	public static FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(20);
		if(type == 0){
			return new Buffer();
		}else{
			return new Diji();
		}
	}
	
	/** 飞行物走步  */
	public void stepAction(){
		//让敌人和buff走一步
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		//子弹走一步
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
		//玩家走步---切换图片
		hero.step();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();	//窗口对象-画框
		ShootGame game = new ShootGame();	//面板
		frame.add(game);	//将面板加到窗口上
		
		frame.setSize(WIDTH, HEIGHT);	//大小
		frame.setAlwaysOnTop(true);	//总在最上
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//设置默认关闭操作
		frame.setLocationRelativeTo(null);	//初始位置
		
		frame.setVisible(true);	//显示-尽快调用paint方法
		
		game.action();    //界面动起来
	}

}
