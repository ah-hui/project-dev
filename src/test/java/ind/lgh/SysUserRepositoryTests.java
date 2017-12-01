package ind.lgh;

import ind.lgh.system.domain.SysUser;
import ind.lgh.system.service.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserRepositoryTests {

    @Autowired
    private SysUserRepository userRepository;

    @Test
    public void test() {
        List<SysUser> list = userRepository.findAll();
        for (SysUser user: list) {
            System.out.println(user.getName());
        }
    }
}
