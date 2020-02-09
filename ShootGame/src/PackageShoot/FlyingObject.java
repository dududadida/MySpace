package PackageShoot;
import java.awt.image.BufferedImage;
/** 飞行物类 */
public abstract class FlyingObject {
	protected int width;	//宽
	protected int height;	//高
	protected int x;	//x坐标
	protected int y;	//y坐标
	protected BufferedImage image;	//图片
	
	/** 走步  */
	public abstract void step();
	
	/** 子弹打敌人  */
	public boolean shootBy(Bullet b){
		int x = b.x;	//子弹的x
		int y = b.y;	//子弹的y
		
		//子弹的x，y在敌人的x，y 和x+width,y+height之间
		return x>this.x && x<this.x + width
			   &&
			   y>this.y && y<this.y + height;
	}
	
	/** 检测是否出界 */
	public abstract boolean outOfBounds();
	
}
