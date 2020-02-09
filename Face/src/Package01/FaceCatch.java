package Package01;

import java.io.IOException;

/**
 * 该类用于实现调用外部程序拍照功能
 * @author wangzheng
 *
 */
public class FaceCatch{
  public static void main(String[] args) {
    try {
      /*
       * 利用Runtime类的getRuntime方法并
       * 调用Process类的exec()方法实现运行外部程序。
       */
      Runtime.getRuntime().exec("E:\\Meitu\\PaiPai\\PaiPai.exe");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}