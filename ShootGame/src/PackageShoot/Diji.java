package PackageShoot;
import java.util.Random;;
/** 敌机:是飞行物，也是敌人  */
public class Diji extends FlyingObject implements Enemy{
	private int speed = 2;	//敌机y的移动步数
	
	/** 初始化实例变量  */
	public Diji(){
		image = ShootGame.diji;	//图片
		width = image.getWidth();	//宽
		height = image.getHeight();	//高
		y = -height;	//y坐标
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);	//x坐标
	}
	
	/** 重写走步  */
	public void step(){
		y += speed;
	}
	
	/** 获取分数2 */
	public int getScore(){
		return 2;
	}
	
	/** 重写出界 */
	public boolean outOfBounds(){
		return y>ShootGame.HEIGHT;	//y大于面板的高度算出界
	}
}
