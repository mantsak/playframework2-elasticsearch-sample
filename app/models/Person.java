package models;

import com.github.eduardofcbg.plugin.es.Finder;
import com.github.eduardofcbg.plugin.es.Index;
import com.github.eduardofcbg.plugin.es.Type;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.FilterBuilders;
import play.libs.F;

import java.util.List;

@Type.Name("person")
@Type.ResultsPerPage(5)
public class Person extends Index {

    public String name;
    public int age;

    public static final Finder<Person> find = finder(Person.class);

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {}

    public F.Promise<IndexResponse> index() {
        return find.index(this);
    }

    public static F.Promise<Person> get(String id) {
        return find.get(id);
    }

    public static F.Promise<List<Person>> getAdults(int page) {
        return find.search(s ->  s.setPostFilter(FilterBuilders.rangeFilter("age").from(18)), page);
    }

}
