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
 * ��������ʵ������ʶ����
 * @author wangzheng
 *
 */
public class FaceID {
  //�������Ӽ�ʱ����
  private final static int CONNECT_TIME_OUT = 30000;
  //�����ȡͼƬ��ʱ����
  private final static int READ_OUT_TIME = 50000;
  //�����ַ����ĳ���
  private static String boundaryString = getBoundary();
  //�����ڴ���ʱ��Ҫʹ�÷��;���
  @SuppressWarnings({ "rawtypes", "unchecked" })
  
  /*
   * ����һ����̬������post�����������˷������󲢻�ȡ���������ṩ��API
   * 
   */
  protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
      //����һ���������Ӷ���
      HttpURLConnection conne;
      //����һ��URL�������ڻ�ȡAPI��URL
      URL url1 = new URL(url);
      conne = (HttpURLConnection) url1.openConnection();
      conne.setDoOutput(true);
      conne.setUseCaches(false);
      //��������ʽΪpost
      conne.setRequestMethod("POST");
      //�������������ĳ�ʱʱ��
      conne.setConnectTimeout(CONNECT_TIME_OUT);
      //���ô�������ȡ���ݵĳ�ʱʱ��
      conne.setReadTimeout(READ_OUT_TIME);
      //����property����ļ�
      conne.setRequestProperty("accept", "*/*");
      conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
      conne.setRequestProperty("connection", "Keep-Alive");
      conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
      //����һ���������������д����
      DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
      //����һ��������������map����ת��Ϊset���ϣ����ڱ�������
      Iterator iter = map.entrySet().iterator();
      //�ж��Ƿ��������
      while(iter.hasNext()){
          //ע�⣬�������δ���ʱδʹ�÷��;�����Ϣ
          //��Map��ֵ�Դ���Set���ϣ��Ա��ڱ������ϵ���Ϣ
          Map.Entry<String, String> entry = (Map.Entry) iter.next();
          //���key
          String key = entry.getKey();
          //���value
          String value = entry.getValue();
          //����д��ĸ�ʽ��׼
          obos.writeBytes("--" + boundaryString + "\r\n");
          obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                  + "\"\r\n");
          obos.writeBytes("\r\n");
          obos.writeBytes(value + "\r\n");
      }
      
      //�ж����Map���ϵ�ֵ��Ϊ�գ��õ�����������д��
      if(fileMap != null && fileMap.size() > 0){
          Iterator fileIter = fileMap.entrySet().iterator();
          while(fileIter.hasNext()){
              Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
              //����д�����ݵĸ�ʽ
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
      //������ǿ��д��
      obos.flush();
      obos.close();
      InputStream ins = null;
      //��ȡURL��Ӧ״̬��
      int code = conne.getResponseCode();
      try{
          //��״̬��Ϊ200ʱ˵����Ӧ�ɹ�
          if(code == 200){
              //�ϴ���ȡ��������
              ins = conne.getInputStream();
          }else{
              //���������ʾ
              ins = conne.getErrorStream();
          }
      }catch (SSLException e){
          e.printStackTrace();
          
          return new byte[0];
      }
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      //���û�������СΪ4096���ֽ�
      byte[] buff = new byte[4096];
      //�����ļ�ĩβ����
      int len;
      while((len = ins.read(buff)) != -1){
          baos.write(buff, 0, len);
      }
      //�������ֽ�������ת��Ϊ�ֽ�����
      byte[] bytes = baos.toByteArray();
      ins.close();
      
      return bytes;
  }
  
  //����һ����ȡ�߽�ֵ����
  private static String getBoundary() {
      //����һ��StringBulider����
      StringBuilder sb = new StringBuilder();
      Random random = new Random();
      //���峤��Ϊ32λ
      for(int i = 0; i < 32; ++i) {
          //������ʽ���ַ��Ĺ������������д��ĸA~Z��Сд��ĸa~z������0~9��ɣ��ܳ���Ϊ32λ
          sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
          random.nextInt(
              "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
      }
      
      //��StringBulider����ת��ΪString������
      return sb.toString();
  }
  
  //����URL�����ʽ
  private static String encode(String value) throws Exception{
      return URLEncoder.encode(value, "UTF-8");
  }
  
  //����һ���ֽڶ��뷽�����������ֽڶ�ȡͼƬ�ļ�
  public static byte[] getBytesFromFile(File f) {
      if (f == null) {
          return null;
      }
      try {
          //�ļ�������
          FileInputStream stream = new FileInputStream(f);
          //�ֽ�����д����
          ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
          //����һ���ֽ��������������ͼƬ�ж�ȡ��������
          byte[] b = new byte[1000];
          int n;
          //����ȡ����ͼƬ����д�뵽�ֽ�������
          while ((n = stream.read(b)) != -1)
              out.write(b, 0, n);
          stream.close();
          out.close();
          
          //���ֽ���ת��Ϊ�ֽ����鲢����
          return out.toByteArray();
      } catch (IOException e) {
      }
      return null;
  }
  
    public static void main(String[] args) throws Exception{
        //����һ��ͼƬ
        String picture = "E:\\picture\\meitu_00001.jpg";
        File file = new File(picture); 
        //��ͼƬת��Ϊ�ֽ����ļ�
        byte[] buff = getBytesFromFile(file);    
        //Face����ƽ̨API��URL
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";        
        //����һ��Map������ŵ���API����Ȩ��Ϣ
        HashMap<String, String> map = new HashMap<>();                  
        HashMap<String, byte[]> byteMap = new HashMap<>();
        //����API������Ȩ��
        map.put("api_key", "zSTSBZuSsjg484Zime48GRI1sL4XYu0-");      
        //������Կ
        map.put("api_secret", "Q16HC7rlzcMGCtNJ6D3SaW5nSny4thE_");     
        /*
         * �Ƿ��Ⲣ���������ؼ��㡣�Ϸ�ֵΪ��
         *  2   ��⡣���� 106 �������ؼ��㡣
         *  1   ��⡣���� 83 �������ؼ��㡣
         *  0   �����
         *
         *  ע��������Ĭ��ֵΪ 0
         */
        map.put("return_landmark", "1");
        //�Ƿ��Ⲣ���ظ������������жϳ������䡢�Ա����������ԡ�
        map.put("return_attributes", 
                    //�Ա�������������ֵΪ��Male    ����,Female  Ů��
                    "gender," +
                    //����������������ֵΪһ���Ǹ�������
                    "age," + 
                    /*
                     * Ц�ݷ������������ֵ�����������ԣ�
                     * value��ֵΪһ�� [0,100] �ĸ�������С�����3λ��Ч���֡���ֵԽ���ʾЦ�̶ȸߡ�
                     * threshold������Ц�ݵ���ֵ����������ֵ��Ϊ��Ц�ݡ�
                     */
                    "smiling," +
                     /*
                      * �������Ʒ��������
                      * ����ֵ�����������ԣ�
                      * ÿ�����Ե�ֵΪһ�� [-180, 180] �ĸ�������
                      * С����� 6 λ��Ч���֡���λΪ�Ƕȡ�
                      *                   pitch_angle��̧ͷ
                      *                   roll_angle����ת��ƽ����ת��
                      *                   yaw_angle��ҡͷ
                      */
                    "headpose," +
                     /*
                      * ����ģ���������������ֵ�����������ԣ�
                      * blurness������ģ�����������
                      * ÿ�����Զ����������ֶΣ�
                      *     value ��ֵΪ��һ������������Χ [0,100]��С����� 3 λ��Ч���֡�ֵԽ��Խģ����
                      *     threshold ��ʾ����ģ�����Ƿ�Ӱ���ʶ����ֵ��
                      */
                    "blur," +
                     /*
                      * ���������жϽ��������ֵ�����������ԣ�
                      *     value��ֵΪ�����������жϵķ�������һ������������Χ [0,100]��С����� 3 λ��Ч���֡�
                      *     threshold����ʾ�������������ϸ��һ����ֵ����������ֵ�������ʺ����������ȶԡ�
                      */
                    "facequality," +
                    /*
                     * �۾�״̬��Ϣ������ֵ�����������ԣ�
                     *   left_eye_status�����۵�״̬
                     *   right_eye_status�����۵�״̬
                     *   ÿ�����Զ����������ֶΡ�
                     *   ÿ���ֶε�ֵ����һ������������Χ [0,100]��
                     *   С����� 3 λ��Ч���֡��ֶ�ֵ���ܺ͵��� 100��
                     *   occlusion���۾����ڵ������Ŷ�
                     *   no_glass_eye_open�������۾������۵����Ŷ�
                     *   normal_glass_eye_close�������ͨ�۾��ұ��۵����Ŷ�
                     *   normal_glass_eye_open�������ͨ�۾������۵����Ŷ�
                     *   dark_glasses�����ī�������Ŷ�
                     *   no_glass_eye_close�������۾��ұ��۵����Ŷ�
                     */
                    "eyestatus," +
                    "emotion," +
                    /*
                     * ���ַ������������ֵΪ��
                     * Asian   ������
                     * White   ����
                     * Black   ����
                     */
                    "ethnicity," +
                     /*
                      * ��ֵʶ����������ֵ�������������ֶΡ�ÿ���ֶε�ֵ��һ������������Χ [0,100]��С����� 3 λ��Ч���֡�
                      *     male_score��������Ϊ�Ĵ�������ֵ������ֵԽ����ֵԽ�ߡ�
                      *     female_score��Ů����Ϊ�Ĵ�������ֵ������ֵԽ����ֵԽ�ߡ�
                      */
                    "beauty," +
                      /*
                       * �첿״̬��Ϣ�����������ֶΡ�
                       * ÿ���ֶε�ֵ����һ������������Χ [0,100]��С����� 3 λ��Ч���֡�
                       * �ֶ�ֵ���ܺ͵��� 100��
                       *     surgical_mask_or_respirator���첿��ҽ�ÿ��ֻ���������ڵ������Ŷ�
                       *     other_occlusion���첿�����������ڵ������Ŷ�
                       *     close���첿û���ڵ��ұ��ϵ����Ŷ�
                       *     open���첿û���ڵ����ſ������Ŷ�
                       */
                    "mouthstatus," +
                    /*
                     * ����λ�������߷�����Ϣ������ֵ�����������ԣ�
                     *     left_eye_gaze�����۵�λ��������״̬
                     *     right_eye_gaze�����۵�λ��������״̬
                     *     ÿ�����Զ����������ֶΣ�ÿ���ֶε�ֵ����һ����������С����� 3 λ��Ч���֡�
                     *     position_x_coordinate: ��������λ�õ� X �����ꡣ
                     *     position_y_coordinate: ��������λ�õ� Y �����ꡣ
                     *     vector_x_component: �������߷��������� X �������
                     *     vector_y_component: �������߷��������� Y �������
                     *     vector_z_component: �������߷��������� Z �������
                     */
                    "eyegaze," +
                     /*
                      * �沿����ʶ���������������ֶΡ�
                      * ÿ���ֶε�ֵ����һ������������Χ [0,100]��С����� 3 λ��Ч���֡�
                      * ÿ���ֶεķ���ֵԽ������ֶδ����״̬�����Ŷ�Խ�ߡ�
                      *    health������
                      *    stain��ɫ��
                      *    acne���ഺ��
                      *    dark_circle������Ȧ
                      */
                    "skinstatus"
                    );
        
        //����ͼƬ�ļ�
        byteMap.put("image_file", buff);
        try{
            //����һ���ֽ�����������post�����ȡ��������
            byte[] bacd = post(url, map, byteMap);
            //���ֽ�����ת��Ϊ�ַ���
            String str = new String(bacd);
            //���������������Լ�������Ϣ��
            System.out.println(str);
        }catch (Exception e) {
            System.out.println("ͼƬδ��ȷ����û��ʶ��������");
        }
    }
}
