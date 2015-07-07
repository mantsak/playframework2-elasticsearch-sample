package controllers;

import com.github.eduardofcbg.plugin.es.DocumentNotFound;
import com.github.eduardofcbg.plugin.es.ES;
import models.Person;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;
import java.util.Optional;

public class PersonController extends Controller {

	@Inject
	public PersonController(ES es) {
		Person.registerAsType(Person.class, es);
	}

    public F.Promise<Result> addAdult(String name, int age) {
        Person p = new Person(name, age);
        return p.index().map(res -> ok("Added person with id: " + res.getId()));
    }

    public F.Promise<Result> getAdults(int page) {
        return Person.getAdults(page).map(persons -> ok(listOfPerson.render(persons)));
    }

    public F.Promise<Result> getPerson(String id) {
        return Person.get(id).map(p -> {
            if (p.isPresent()) return ok(person.render(p.get()));
            else return notFound("not found person with id " + id);
        });
    }

    public F.Promise<Result> getPersonWithTemplateLogic(String id) {
        return Person.get(id).map(p -> ok(personWithTemplateLogic.render(p)));
    }


}
