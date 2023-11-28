package cn.xyz.user;

import cn.xyz.BlogApp;
import cn.xyz.user.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest(classes = BlogApp.class)
public class MongobTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test1() {
        Person person = new Person(null, "tom", 30, "新疆乌鲁木齐");
        mongoTemplate.save(person);
        System.out.println(person);
    }
    @Test
    public void test2() {
        List<Person> personList = mongoTemplate.findAll(Person.class);
        personList.forEach(System.out::println);
    }
}
