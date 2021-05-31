package utils;

/**
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-05-28
 */
public class MyTempUtils {

    public static void main(String[] args) {
        double finance = 24;
        double rate = 1.2;
        double count = 0;
        int year = 5;

        for (int i = 0; i < year; i++) {
            count = (finance + count) * rate;
        }
        System.out.println(count);
    }
}
