package Package01;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;

/**
 * 该类用于实现人脸识别功能
 * @author wangzheng
 *
 */
public class FaceID {
  //定义连接计时常量
  private final static int CONNECT_TIME_OUT = 30000;
  //定义读取图片计时常量
  private final static int READ_OUT_TIME = 50000;
  //定义字符串的长度
  private static String boundaryString = getBoundary();
  //屏蔽在传参时需要使用泛型警告
  @SuppressWarnings({ "rawtypes", "unchecked" })
  
  /*
   * 定义一个静态方法用post方法来向服务端发送请求并获取服务器端提供的API
   * 
   */
  protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
      //定义一个网络连接对象
      HttpURLConnection conne;
      //定义一个URL对象用于获取API的URL
      URL url1 = new URL(url);
      conne = (HttpURLConnection) url1.openConnection();
      conne.setDoOutput(true);
      conne.setUseCaches(false);
      //定义请求方式为post
      conne.setRequestMethod("POST");
      //设置连接主机的超时时间
      conne.setConnectTimeout(CONNECT_TIME_OUT);
      //设置从主机读取数据的超时时间
      conne.setReadTimeout(READ_OUT_TIME);
      //配置property组件文件
      conne.setRequestProperty("accept", "*/*");
      conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
      conne.setRequestProperty("connection", "Keep-Alive");
      conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
      //定义一个数据输出流用来写数据
      DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
      //定义一个迭代器，并将map集合转换为set集合，便于遍历集合
      Iterator iter = map.entrySet().iterator();
      //判断是否读到数据
      while(iter.hasNext()){
          //注解，用于屏蔽传参时未使用泛型警告信息
          //将Map键值对存入Set集合，以便于遍历集合的信息
          Map.Entry<String, String> entry = (Map.Entry) iter.next();
          //获得key
          String key = entry.getKey();
          //获得value
          String value = entry.getValue();
          //设置写入的格式标准
          obos.writeBytes("--" + boundaryString + "\r\n");
          obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                  + "\"\r\n");
          obos.writeBytes("\r\n");
          obos.writeBytes(value + "\r\n");
      }
      
      //判断如果Map集合的值不为空，用迭代器将数据写入
      if(fileMap != null && fileMap.size() > 0){
          Iterator fileIter = fileMap.entrySet().iterator();
          while(fileIter.hasNext()){
              Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
              //定义写入数据的格式
              obos.writeBytes("--" + boundaryString + "\r\n");
              obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                      + "\"; filename=\"" + encode(" ") + "\"\r\n");
              obos.writeBytes("\r\n");
              obos.write(fileEntry.getValue());
              obos.writeBytes("\r\n");
          }
      }
      obos.writeBytes("--" + boundaryString + "--" + "\r\n");
      obos.writeBytes("\r\n");
      //将数据强制写出
      obos.flush();
      obos.close();
      InputStream ins = null;
      //获取URL相应状态码
      int code = conne.getResponseCode();
      try{
          //当状态码为200时说明响应成功
          if(code == 200){
              //上传读取到的数据
              ins = conne.getInputStream();
          }else{
              //输出错误提示
              ins = conne.getErrorStream();
          }
      }catch (SSLException e){
          e.printStackTrace();
          
          return new byte[0];
      }
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      //设置缓冲区大小为4096个字节
      byte[] buff = new byte[4096];
      //定义文件末尾变量
      int len;
      while((len = ins.read(buff)) != -1){
          baos.write(buff, 0, len);
      }
      //将数组字节流对象转换为字节数组
      byte[] bytes = baos.toByteArray();
      ins.close();
      
      return bytes;
  }
  
  //定义一个获取边界值方法
  private static String getBoundary() {
      //创建一个StringBulider对象
      StringBuilder sb = new StringBuilder();
      Random random = new Random();
      //定义长度为32位
      for(int i = 0; i < 32; ++i) {
          //正则表达式，字符的构成由任意个大写字母A~Z和小写字母a~z和数字0~9组成，总长度为32位
          sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
          random.nextInt(
              "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
      }
      
      //将StringBulider对象转换为String并返回
      return sb.toString();
  }
  
  //定义URL编码格式
  private static String encode(String value) throws Exception{
      return URLEncoder.encode(value, "UTF-8");
  }
  
  //定义一个字节读入方法，用来按字节读取图片文件
  public static byte[] getBytesFromFile(File f) {
      if (f == null) {
          return null;
      }
      try {
          //文件读入流
          FileInputStream stream = new FileInputStream(f);
          //字节数组写出流
          ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
          //定义一个字节数组用来保存从图片中读取到的数据
          byte[] b = new byte[1000];
          int n;
          //将读取到的图片数据写入到字节数组中
          while ((n = stream.read(b)) != -1)
              out.write(b, 0, n);
          stream.close();
          out.close();
          
          //将字节流转换为字节数组并返回
          return out.toByteArray();
      } catch (IOException e) {
      }
      return null;
  }
  
    public static void main(String[] args) throws Exception{
        //读入一张图片
        String picture = "E:\\picture\\meitu_00001.jpg";
        File file = new File(picture); 
        //将图片转换为字节码文件
        byte[] buff = getBytesFromFile(file);    
        //Face智能平台API的URL
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";        
        //创建一个Map用来存放调用API的授权信息
        HashMap<String, String> map = new HashMap<>();                  
        HashMap<String, byte[]> byteMap = new HashMap<>();
        //存入API调用授权码
        map.put("api_key", "zSTSBZuSsjg484Zime48GRI1sL4XYu0-");      
        //存入密钥
        map.put("api_secret", "Q16HC7rlzcMGCtNJ6D3SaW5nSny4thE_");     
        /*
         * 是否检测并返回人脸关键点。合法值为：
         *  2   检测。返回 106 个人脸关键点。
         *  1   检测。返回 83 个人脸关键点。
         *  0   不检测
         *
         *  注：本参数默认值为 0
         */
        map.put("return_landmark", "1");
        //是否检测并返回根据人脸特征判断出的年龄、性别、情绪等属性。
        map.put("return_attributes", 
                    //性别分析结果。返回值为：Male    男性,Female  女性
                    "gender," +
                    //年龄分析结果。返回值为一个非负整数。
                    "age," + 
                    /*
                     * 笑容分析结果。返回值包含以下属性：
                     * value：值为一个 [0,100] 的浮点数，小数点后3位有效数字。数值越大表示笑程度高。
                     * threshold：代表笑容的阈值，超过该阈值认为有笑容。
                     */
                    "smiling," +
                     /*
                      * 人脸姿势分析结果。
                      * 返回值包含以下属性，
                      * 每个属性的值为一个 [-180, 180] 的浮点数，
                      * 小数点后 6 位有效数字。单位为角度。
                      *                   pitch_angle：抬头
                      *                   roll_angle：旋转（平面旋转）
                      *                   yaw_angle：摇头
                      */
                    "headpose," +
                     /*
                      * 人脸模糊分析结果。返回值包含以下属性：
                      * blurness：人脸模糊分析结果。
                      * 每个属性都包含以下字段：
                      *     value 的值为是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。值越大，越模糊。
                      *     threshold 表示人脸模糊度是否影响辨识的阈值。
                      */
                    "blur," +
                     /*
                      * 人脸质量判断结果。返回值包含以下属性：
                      *     value：值为人脸的质量判断的分数，是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。
                      *     threshold：表示人脸质量基本合格的一个阈值，超过该阈值的人脸适合用于人脸比对。
                      */
                    "facequality," +
                    /*
                     * 眼睛状态信息。返回值包含以下属性：
                     *   left_eye_status：左眼的状态
                     *   right_eye_status：右眼的状态
                     *   每个属性都包含以下字段。
                     *   每个字段的值都是一个浮点数，范围 [0,100]，
                     *   小数点后 3 位有效数字。字段值的总和等于 100。
                     *   occlusion：眼睛被遮挡的置信度
                     *   no_glass_eye_open：不戴眼镜且睁眼的置信度
                     *   normal_glass_eye_close：佩戴普通眼镜且闭眼的置信度
                     *   normal_glass_eye_open：佩戴普通眼镜且睁眼的置信度
                     *   dark_glasses：佩戴墨镜的置信度
                     *   no_glass_eye_close：不戴眼镜且闭眼的置信度
                     */
                    "eyestatus," +
                    "emotion," +
                    /*
                     * 人种分析结果，返回值为：
                     * Asian   亚洲人
                     * White   白人
                     * Black   黑人
                     */
                    "ethnicity," +
                     /*
                      * 颜值识别结果。返回值包含以下两个字段。每个字段的值是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。
                      *     male_score：男性认为的此人脸颜值分数。值越大，颜值越高。
                      *     female_score：女性认为的此人脸颜值分数。值越大，颜值越高。
                      */
                    "beauty," +
                      /*
                       * 嘴部状态信息，包括以下字段。
                       * 每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。
                       * 字段值的总和等于 100。
                       *     surgical_mask_or_respirator：嘴部被医用口罩或呼吸面罩遮挡的置信度
                       *     other_occlusion：嘴部被其他物体遮挡的置信度
                       *     close：嘴部没有遮挡且闭上的置信度
                       *     open：嘴部没有遮挡且张开的置信度
                       */
                    "mouthstatus," +
                    /*
                     * 眼球位置与视线方向信息。返回值包括以下属性：
                     *     left_eye_gaze：左眼的位置与视线状态
                     *     right_eye_gaze：右眼的位置与视线状态
                     *     每个属性都包括以下字段，每个字段的值都是一个浮点数，小数点后 3 位有效数字。
                     *     position_x_coordinate: 眼球中心位置的 X 轴坐标。
                     *     position_y_coordinate: 眼球中心位置的 Y 轴坐标。
                     *     vector_x_component: 眼球视线方向向量的 X 轴分量。
                     *     vector_y_component: 眼球视线方向向量的 Y 轴分量。
                     *     vector_z_component: 眼球视线方向向量的 Z 轴分量。
                     */
                    "eyegaze," +
                     /*
                      * 面部特征识别结果，包括以下字段。
                      * 每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。
                      * 每个字段的返回值越大，则该字段代表的状态的置信度越高。
                      *    health：健康
                      *    stain：色斑
                      *    acne：青春痘
                      *    dark_circle：黑眼圈
                      */
                    "skinstatus"
                    );
        
        //缓存图片文件
        byteMap.put("image_file", buff);
        try{
            //定义一个字节数组来保存post请求获取到的数据
            byte[] bacd = post(url, map, byteMap);
            //将字节数组转换为字符串
            String str = new String(bacd);
            //输出人脸分析结果以及错误信息等
            System.out.println(str);
        }catch (Exception e) {
            System.out.println("图片未正确保存没有识别到人脸！");
        }
    }
}
