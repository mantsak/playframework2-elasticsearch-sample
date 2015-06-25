package controllers;

import com.github.eduardofcbg.plugin.es.ES;
import models.Person;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.listOfPerson;

import javax.inject.Inject;

public class PersonController extends Controller {

	@Inject
	public PersonController(ES es) {
		Person.registerAsType(Person.class, es);
	}

    public F.Promise<Result> addAdult(String name, int age) {
        Person p = new Person(name, age);
        return p.index().map(res -> {
            return ok("Added person with id: " + res.getId());
        });
    }

    public F.Promise<Result> getAdults(int page) {
        return Person.getAdults(page).map(persons -> {
            return ok(listOfPerson.render(persons));
        });
    }

}
