package PackageShoot;
import java.awt.image.BufferedImage;
/** �������� */
public abstract class FlyingObject {
	protected int width;	//��
	protected int height;	//��
	protected int x;	//x����
	protected int y;	//y����
	protected BufferedImage image;	//ͼƬ
	
	/** �߲�  */
	public abstract void step();
	
	/** �ӵ������  */
	public boolean shootBy(Bullet b){
		int x = b.x;	//�ӵ���x
		int y = b.y;	//�ӵ���y
		
		//�ӵ���x��y�ڵ��˵�x��y ��x+width,y+height֮��
		return x>this.x && x<this.x + width
			   &&
			   y>this.y && y<this.y + height;
	}
	
	/** ����Ƿ���� */
	public abstract boolean outOfBounds();
	
}
