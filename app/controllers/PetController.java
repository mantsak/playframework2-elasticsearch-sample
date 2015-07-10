package controllers;

import com.github.eduardofcbg.plugin.es.DocumentNotFound;
import com.github.eduardofcbg.plugin.es.ES;
import models.Pet;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;
import java.util.Optional;

public class PetController extends Controller {

        @Inject
        public PetController(ES es) {
                Pet.registerAsType(es);
        }

    public F.Promise<Result> getPet(String id) {
        return Pet.get(id).map(p -> {
            if (p.isPresent()) return ok(pet.render(p.get()));
            else return notFound("not found pet with id " + id);
        });
    }

}
