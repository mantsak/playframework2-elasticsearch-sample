package models;

import com.github.eduardofcbg.plugin.es.Finder;
import com.github.eduardofcbg.plugin.es.Index;
import com.github.eduardofcbg.plugin.es.Type;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.FilterBuilders;
import play.libs.F;

import java.util.List;
import java.util.Optional;

@Type.Name("pet")
@Type.ResultsPerPage(5)
public class Pet extends Index {

    public String name;

    public static final Finder<Pet> find = finder(Pet.class);

    public Pet(String name) {
        this.name = name;
    }

    public Pet() {}

    public F.Promise<IndexResponse> index() {
        return find.index(this);
    }

    public static F.Promise<Optional<Pet>> get(String id) {
        play.Logger.info("Pet.get(" + id + ") type:" + find.getType());
        return find.get(id);
    }

}
