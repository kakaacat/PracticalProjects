import com.sqs.utils.MD5Util;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

/**
 * @Author : kaka
 * @Date: 2022-02-17 15:57
 * @Description: 类
 */
public class test {
    @Test
    public void test1() {
        String mi = MD5Util.getMD5("000000");
        System.out.println(mi);

       // System.out.println(mi instanceof String);
    }
}
