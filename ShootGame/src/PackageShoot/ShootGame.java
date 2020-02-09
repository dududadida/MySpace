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

/** ��Ϸ������  */
public class ShootGame extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = -44647058529149928L;
    public static final int WIDTH = 400;	//��
	public static final int HEIGHT = 654;	//��
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage gameover;
	public static BufferedImage pause;
	public static BufferedImage diji;
	public static BufferedImage buff;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	
	public Hero hero = new Hero();	//��Ҷ���
	public Bullet[] bullets = {};	//�ӵ�����
	public FlyingObject[] flyings = {};	//�л���buff
	
	private int score = 0;	//��¼����
	private int state;	//״̬
	public static final int START = 0; 
	public static final int RUNNING = 1; 
	public static final int PAUSE = 2; 
	public static final int GAME_OVER = 3; 
	
	static{	//����ͼƬ��Դ
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
	
	/** ��д���Ʒ���  */
	public void paint(Graphics g){	//gΪ����
		g.drawImage(background, 0, 0, null);	//��ͼƬ
		paintHero(g);	//�����
		paintBullets(g);	//���ӵ�
		paintFlyingObjects(g);	//������
		paintScore(g);	//������
		paintState(g);	//����Ϸ״̬
	}
	
	/** ����Ϸ״̬  */
	public void paintState(Graphics g){
		switch(state){
		case START:	//����ͼƬ
			g.drawImage(start, 0, WIDTH / 2, null);
			break;
		case PAUSE:	//��ͣͼƬ
			g.drawImage(pause, 0, WIDTH / 2, null);
			break;
		case GAME_OVER:	//ֹͣͼƬ
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	
	/** �����  */
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	/** ���ӵ�  */
	public void paintBullets(Graphics g){
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];	//ÿ���ӵ�����
			g.drawImage(b.image, b.x, b.y, null);
		}
	}
	
	/** ������  */
	public void paintFlyingObjects(Graphics g){
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);	//ÿ�����˶���
		}
	}
	
	/** ������  */
	public void paintScore(Graphics g){
		int x = 10, y = 25;
		g.setColor(new Color(0x00ff00));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		g.drawString("SCORE��"+score, x, y);		//���÷�
		g.drawString("LIFE��"+hero.getLife(), x, y+15);	//����
	}
	
	private Timer timer;	//��ʱ��
	private int interval =10;	//��ʱ���ms
	
	/** ����ִ�ж�����  */
	public void action(){
		
		//����¼�������
		MouseAdapter l = new MouseAdapter(){
			/** ��дmouseMoved()����  */
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if(state == RUNNING){
					int x = e.getX();	//�õ�����x
					int y = e.getY();	//�õ�����y
					hero.moveTo(x, y);	//����ƶ�
				}
			}
			
			/** ��дmouseClicked()����  */
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					hero = new Hero();	//��Ϸ������������
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					score = 0;
					state = START;
					break;
				}
			}
		
			/** ��дmouseExited()����*/
			public void mouseExited(MouseEvent e){
				if(state != GAME_OVER){
					state = PAUSE;
				}
			}
			
			/** ��дmouseEntered()����*/
			public void mouseEntered(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}
			}
		};
		
		//����ǰ���������������
		this.addMouseListener(l);
		
		//����ǰ�����������Ļ�������
		this.addMouseMotionListener(l);
		
		timer = new Timer();	//������ʱ������
		/** �����ڲ���  */
		timer.schedule(new TimerTask(){// ����ʵ����Ķ���
			public void run(){	//��д��ʱִ�з���run()
				if(state == RUNNING){
					enterAction();	//�÷������볡---new����
					stepAction();	//�������߲�
					shootAction();	//���
					bangAction();	//����Ŀ��
					outOfBoundsAction();	//ɾ��Խ��ķ�����
					checkGameOverAction();	//�ж���Ϸ�Ƿ����
				}
				
				repaint();	//�ػ棨����paint������
			}
		}, interval, interval);	//��ʱ����
	}

	/** �����Ϸ�Ƿ����  */
	public void checkGameOverAction(){
		if(isGameOver()){	//�ж��Ƿ����
			state = GAME_OVER;	//�ı�״̬Ϊ����
		}
	}
	
	/** �ж���Ϸ�Ƿ����  */
	public boolean isGameOver(){
		for(int i=0; i<flyings.length; i++){	//�������з�����
			int index = -1;	//��¼ײ�ϵķ����������
			FlyingObject obj = flyings[i];	//ÿ��������
			if(hero.hit(obj)){	//ײ��
				hero.substractLife();	//����
				hero.setDoubleFire(0);	//���û�����ԭ
				index = i;	//��¼��ײ������
			}
			
			if(index != -1){	//��ײ�ϵ�
				//ɾ����ײ�ķ�����
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t;
				flyings = (FlyingObject[]) Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		
		return hero.getLife() <= 0;	//�����Ƿ����� 
	}
	
	/** ɾ��Խ�������  */
	public void outOfBoundsAction(){
		int index = 0;	//�±�
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];	//û�г����Ԫ��
		for(int i=0; i<flyings.length; i++){	//��������
			FlyingObject f = flyings[i];	//�õ�����
			if(!f.outOfBounds()){	//��������
				flyingLives[index++] = f;
			}
		}
		flyings = (FlyingObject[]) Arrays.copyOf(flyingLives, index);	//ɾ������Ԫ��
		//ɾ�����г�����ӵ�
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
	
	/** �ӵ������  */
	public void bangAction(){	//���������ӵ�
		for (int i=0; i<bullets.length; i++) {	//����ÿ���ӵ�
			Bullet b = bullets[i];	//ÿһ���ӵ�
			bang(b);	//�ӵ��ͷ�������ײ
		}
	}
	
	/** �ӵ������  b:�ӵ�  */
	public void bang(Bullet b){
		int index = -1;	//���еķ����������
		for (int i=0; i<flyings.length; i++) {	//��������
			FlyingObject obj = flyings[i];	//�õ�ÿһ������
			if(obj.shootBy(b)){	//��ײ���
				index = i;	//��¼�����еķɻ����±�
				break;
			}
		}
		
		if(index != -1){	//���еķ����������
			FlyingObject one = flyings[index];	//���еĵ���
			
			//�÷ֻ��buff
			if(one instanceof Enemy){	//��Ϊ����
				Enemy e = (Enemy)one;	//������ǿתΪ����
				score += e.getScore();	//���üӷ�
			}else if(one instanceof Buffer){	//��Ϊ����
				Buff bf = (Buff)one;	//������ǿתΪbuff
				int type = bf.getType();	//�õ�buff����
				switch(type){
				case Buff.DOUBLE_FIRE:
					hero.addDoubleFire();	//˫������
					break;
				case Buff.LIFE:
					hero.addLife();		//����
					break;
				}
			}	
			
			//ɾ�������з�����
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			//ɾ�����������һ��Ԫ��
			flyings = (FlyingObject[])Arrays.copyOf(flyings, flyings.length - 1);
		}
	}
	
	int shootIndex = 0;	//�������Ƶ��
	
	/** ���   */
	public void shootAction(){
		shootIndex ++;
		if(shootIndex % 50 == 0){	//500ms��һ��
			Bullet[] bs = hero.shoot();	//��ҷ���
			bullets = (Bullet[]) Arrays.copyOf(bullets, bullets.length + bs.length);	//����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);	//׷��
		}
	}
	
	int flyEnteredIndex = 0;	//�������볡����
	/** �������볡  */
	public void enterAction(){	//ÿ10ms��һ��
		flyEnteredIndex ++;	//��һ��index+1
		if(flyEnteredIndex % 20 == 0){	//10*20=200����
			FlyingObject obj = nextOne();	//�������һ������
			flyings = (FlyingObject[]) Arrays.copyOf(flyings, flyings.length + 1);	//����
			flyings[flyings.length - 1] = obj;	//�������︳ֵ����������һλ
		}
	}
	
	/** ������ɵл���buff */
	//������������������һ��Ϊ��̬
	public static FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(20);
		if(type == 0){
			return new Buffer();
		}else{
			return new Diji();
		}
	}
	
	/** �������߲�  */
	public void stepAction(){
		//�õ��˺�buff��һ��
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		//�ӵ���һ��
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
		//����߲�---�л�ͼƬ
		hero.step();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();	//���ڶ���-����
		ShootGame game = new ShootGame();	//���
		frame.add(game);	//�����ӵ�������
		
		frame.setSize(WIDTH, HEIGHT);	//��С
		frame.setAlwaysOnTop(true);	//��������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//����Ĭ�Ϲرղ���
		frame.setLocationRelativeTo(null);	//��ʼλ��
		
		frame.setVisible(true);	//��ʾ-�������paint����
		
		game.action();    //���涯����
	}

}
