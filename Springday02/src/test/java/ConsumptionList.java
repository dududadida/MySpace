/**
 * @PackageName PACKAGE_NAME
 * @Author wangzheng
 * @Date 2020/1/18 19:30
 * @Description 今日消费清单（注：下列消费已取消交通费，本程序无高级算法可言，就是懒得用计算器-_-!···）
 */
public class ConsumptionList {
    public static void main(String[] args) {
        int qth = 98;       //小郄消费
        int cxy = 388;      //老柴消费
        int wz = 176;       //俺的
        int averageDessert = qth / 5;       //点心均价
        int averageFruit = wz / 5;          //水果均价
        int averageLunch = cxy / 5;         //午饭均价

        //w是俺，q是小郄，c是老柴
        int wANDc = averageLunch - averageFruit;
        int cANDq = averageLunch - averageDessert;
        int qANDw = averageFruit - averageDessert;

        System.out.println("李昊：" + "给老柴" + averageLunch + "￥" + "，给俺" + averageFruit + "￥" +
                "，给小郄" + averageDessert+ "￥");
        System.out.println("俺：" + "给老柴" + wANDc + "￥");
        System.out.print("小郄：" + "给老柴" + cANDq*2 + "￥" + "，给俺" + qANDw*2 + "￥");
    }
}
