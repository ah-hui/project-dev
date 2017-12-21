package ind.lgh;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import ind.lgh.system.domain.QSimpleMenu;
import ind.lgh.system.domain.QSimpleRole;
import ind.lgh.system.repository.SimpleRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryDSLTests {

    @Autowired
    private SimpleRoleRepository roleRepository;

    @Test
    public void find() {
        QSimpleRole role = QSimpleRole.simpleRole;
        QSimpleMenu menu = QSimpleMenu.simpleMenu;
        Predicate predicate = role.id.eq(1);
        List<Tuple> result = roleRepository.findRoleAndMenu(predicate);
        for (Tuple row : result) {
            System.out.println("--------find---------");
            System.out.println("role:"+row.get(role).getName());
            System.out.println("menu:"+row.get(menu).getName());
            System.out.println("---------------------");
        }
        System.out.println(result);
    }

    @Test
    public void findByPage() {
        QSimpleRole role = QSimpleRole.simpleRole;
        QSimpleMenu menu = QSimpleMenu.simpleMenu;
        Predicate predicate = role.name.like("管理%");
        PageRequest pageRequest = new PageRequest(0,10);
        QueryResults<Tuple> result = roleRepository.findRoleAndMenuByPage(predicate,pageRequest);
        System.out.println("page:total="+result.getTotal()+",offset="+result.getOffset()+",limit="+result.getLimit());
        for (Tuple row : result.getResults()) {
            System.out.println("-----findByPage------");
            System.out.println("role:"+row.get(role).getName());
            System.out.println("menu:"+row.get(menu).getName());
            System.out.println("---------------------");
        }
        System.out.println(result);
    }
}
