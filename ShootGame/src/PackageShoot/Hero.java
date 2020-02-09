package PackageShoot;
import java.awt.image.BufferedImage;
/** 玩家类：是飞行物  */
public class Hero extends FlyingObject{
	private BufferedImage[] images = {};	//两张图片
	private int index;	//图片交换计数
	private int doubleFire;	//双倍火力
	private int life;	//命
	
	/** 初始化实例变量  */
	public Hero(){
		image = ShootGame.hero0;	//图片
		width = image.getWidth();	//宽
		height = image.getHeight();	//高
		x = 150;	//x坐标
		y = 400;	//y坐标
		doubleFire = 0;	//单倍火力
		life = 3;	//命3条
		images = new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
	}

	/** 重写走步  */
	public void step(){	//切图片
		//step()方法被调用10次，换一次图片
		int num = index++/10%images.length;
		/*
		 * index=0 0/10=0%2 0
		 * index=1 1/10=0%2 0
		 * ...
		 * index=10 10/10=1%2 1
		 * index=11 11/10=1&2 1
		 * ...
		 * index=20 20/10=2%2 0
		 */
		image = images[num];
		/*
		 * image=images[0]
		 * or
		 * image=images[1]
		 */
	}

	/** 发射子弹   */
	public Bullet[] shoot(){
		int xStep = this.width/4;	//4半
		int yStep = 20;
		if(doubleFire>0){	//双倍火力
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x+xStep, this.y-yStep);
			bullets[1] = new Bullet(this.x+3*xStep, this.y-yStep);
			return bullets;
		}else{	//单倍火力
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x+2*xStep, this.y-yStep);
			return bullets;
		}
	}

	/** 移动 x：鼠标的x坐标 y:鼠标的y坐标*/
	public void moveTo(int x, int y){
		this.x = x - width/2;	//鼠标的x-1/2的宽
		this.y = y - height/2;	//鼠标的y-1/2的高
	}
	
	/** 添加双倍火力buff */
	public void addDoubleFire(){
		doubleFire += 40;
	}
	
	/** 增加一条命  */
	public void addLife(){
		life ++;
	}

	/** 获取生命值  */
	public int getLife(){
		return life;
	}
	
	/** 重写出界 */
	public boolean outOfBounds(){
		return false;	//永不出界
	}
	
	/** 判断玩家是否与敌人碰撞 
	 * other:敌机或buff */
	public boolean hit(FlyingObject other){
		int x1 = other.x - this.width / 2;
		int x2 = other.x + other.width + this.width / 2;
		int y1 = other.y - this.height / 2;
		int y2 = other.y + other.height + this.height / 2;
		int heroX = this.x + this.width / 2;
		int heroY = this.y + this.height / 2;
		
		return heroX>x1 && heroX<x2
			   &&
			   heroY>y1 && heroY<y2;	//是否碰撞
	}

	/** 碰撞结果，减命 */
	public void substractLife(){
		life --;
	}
	
	/** 碰撞结果，设置火力  */
	public void setDoubleFire(int doubleFire){
		this.doubleFire = doubleFire;
	}
}
