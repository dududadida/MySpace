package PackageShoot;
/** �ӵ���:�Ƿ�������ǵ���  */
public class Bullet extends FlyingObject{
	private int speed = 3;	//�ӵ��ƶ�����
	
	/** ��ʼ��ʵ������  */
	public Bullet(int x, int y){
		image = ShootGame.bullet;	//ͼƬ
		width = image.getWidth();	//��
		height = image.getHeight();	//��
		this.x = x;	//x����
		this.y = y;	//y����
	}

	/** ��д�߲�   */
	public void step(){
		y -= speed;
	}
	
	/** ��д���� */
	public boolean outOfBounds(){
		return y<-height;	//yС��-�ĸ�ʱ����
	}
}
