package PackageShoot;
import java.util.Random;;
/** buffer类：是飞行物也是buff  */
public class Buffer extends FlyingObject implements Buff{
	
	private int xSpeed = 1;	//x坐标步数
	private int ySpeed = 2;	//y坐标步数
	private int buffType;	//buff类型
	
	/** 构造方法，初始化实例变量  */
	public Buffer(){
		image = ShootGame.buff;	//图片
		width = image.getWidth();	//宽
		height = image.getHeight();	//高
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);
		buffType = rand.nextInt(2);
	}
	
	/** 重写走步 */
	public void step(){
		x += xSpeed;
		y += ySpeed;
		if(x < 0){
			xSpeed = 1;	//+1往右
		}
		if(x > ShootGame.WIDTH - width){
			xSpeed = -1;	//+(-1)往左
		}
	}
	
	/** 获取buff  */
	public int getType(){
		return buffType;
	}
	
	/** 重写出界 */
	public boolean outOfBounds(){
		return y>ShootGame.HEIGHT;	//y大于面板的高度算出界
	}
}
