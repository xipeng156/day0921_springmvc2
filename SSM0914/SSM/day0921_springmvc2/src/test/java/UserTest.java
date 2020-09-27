import com.qst.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 14:18 2020/9/23
 * @Modified by:
 */
public class UserTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        UserService bean = (UserService) context.getBean("userService");
        int i = bean.getUserListCount("王", 3);
        System.out.println(i);
    }

    @Test
    public void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        UserService bean = (UserService) context.getBean("userService");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName","王");
        map.put("userRoleId","3");
        map.put("pageNo",2);
        map.put("pageSize",1);
        bean.getuserlist(map);

    }
}
