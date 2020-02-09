package PackageShoot;
import java.util.Random;;
/** buffer�ࣺ�Ƿ�����Ҳ��buff  */
public class Buffer extends FlyingObject implements Buff{
	
	private int xSpeed = 1;	//x���경��
	private int ySpeed = 2;	//y���경��
	private int buffType;	//buff����
	
	/** ���췽������ʼ��ʵ������  */
	public Buffer(){
		image = ShootGame.buff;	//ͼƬ
		width = image.getWidth();	//��
		height = image.getHeight();	//��
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);
		buffType = rand.nextInt(2);
	}
	
	/** ��д�߲� */
	public void step(){
		x += xSpeed;
		y += ySpeed;
		if(x < 0){
			xSpeed = 1;	//+1����
		}
		if(x > ShootGame.WIDTH - width){
			xSpeed = -1;	//+(-1)����
		}
	}
	
	/** ��ȡbuff  */
	public int getType(){
		return buffType;
	}
	
	/** ��д���� */
	public boolean outOfBounds(){
		return y>ShootGame.HEIGHT;	//y�������ĸ߶������
	}
}
