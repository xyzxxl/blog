package cn.xyz.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id//将当前字段与mongodb中的主键关联
    private String _id;
    private String name;
    private Integer age;
    private String address;
}
