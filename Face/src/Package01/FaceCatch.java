package Package01;

import java.io.IOException;

/**
 * ��������ʵ�ֵ����ⲿ�������չ���
 * @author wangzheng
 *
 */
public class FaceCatch{
  public static void main(String[] args) {
    try {
      /*
       * ����Runtime���getRuntime������
       * ����Process���exec()����ʵ�������ⲿ����
       */
      Runtime.getRuntime().exec("E:\\Meitu\\PaiPai\\PaiPai.exe");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}