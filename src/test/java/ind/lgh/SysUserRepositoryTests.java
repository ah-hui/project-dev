package ind.lgh;

import ind.lgh.system.domain.SysUser;
import ind.lgh.system.service.SysUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserRepositoryTests {

    @Autowired
    private SysUserRepository userRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        userRepository.save(new SysUser("aa1", "123456", "aa", "133","aa@126.com",formattedDate));
        userRepository.save(new SysUser("bb1", "123456", "bb", "134","bb@126.com",formattedDate));
        userRepository.save(new SysUser("cc1", "123456", "cc", "135","cc@126.com",formattedDate));

        Assert.assertEquals(9,userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByLoginName("bb1").getNickName());
        userRepository.delete(userRepository.findByLoginNameOrPhone("","135"));
    }
}
