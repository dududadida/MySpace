package PackageShoot;
import java.util.Random;;
/** �л�:�Ƿ����Ҳ�ǵ���  */
public class Diji extends FlyingObject implements Enemy{
	private int speed = 2;	//�л�y���ƶ�����
	
	/** ��ʼ��ʵ������  */
	public Diji(){
		image = ShootGame.diji;	//ͼƬ
		width = image.getWidth();	//��
		height = image.getHeight();	//��
		y = -height;	//y����
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);	//x����
	}
	
	/** ��д�߲�  */
	public void step(){
		y += speed;
	}
	
	/** ��ȡ����2 */
	public int getScore(){
		return 2;
	}
	
	/** ��д���� */
	public boolean outOfBounds(){
		return y>ShootGame.HEIGHT;	//y�������ĸ߶������
	}
}
