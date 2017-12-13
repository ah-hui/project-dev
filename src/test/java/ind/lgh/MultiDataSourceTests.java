package ind.lgh;

import ind.lgh.business.domain.Message;
import ind.lgh.business.repository.MessageRepository;
import ind.lgh.system.domain.SimpleUserRole;
import ind.lgh.system.repository.SimpleUserRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDataSourceTests {

    @Autowired
    private SimpleUserRoleRepository userRoleRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void test() {
        SimpleUserRole userRole1 = new SimpleUserRole();
        userRole1.setUserId(100);
        userRole1.setRoleId(100);
        SimpleUserRole userRole2 = new SimpleUserRole();
        userRole2.setUserId(101);
        userRole2.setRoleId(101);
        userRoleRepository.save(userRole1);
        userRoleRepository.save(userRole2);

        messageRepository.save(new Message("hello", "lgh"));
        messageRepository.save(new Message("world", "ha"));
    }
}
