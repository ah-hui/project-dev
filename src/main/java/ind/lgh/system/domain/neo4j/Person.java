package ind.lgh.system.domain.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Neo4j实体 Person
 *
 * @author lgh
 * @since 2018-01-15
 */
@NodeEntity
@Getter
public class Person {

    /**
     * 实体的标识符(只能使用Long类型)
     */
    @GraphId
    @Setter
    private Long id;

    /**
     * 为属性添加索引
     */
    @Index(unique = true)
    @Setter
    private String name;

    @Setter
    @Property(name = "phone")
    private String phone;

    @Setter
    @Property(name = "email")
    private String email;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String phone, String email) {
        super();
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Relationship(type = "FOLLOW", direction = Relationship.INCOMING)
    Set<Person> followers = new HashSet<>();

    @Relationship(type = "FOLLOW", direction = Relationship.OUTGOING)
    Set<Person> followed = new HashSet<>();

    @Relationship(type = "PUBLISH")
    Set<Publish> messages = new HashSet<>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
