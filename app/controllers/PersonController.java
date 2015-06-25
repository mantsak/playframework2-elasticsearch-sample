package controllers;

import models.Person;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

import com.github.eduardofcbg.plugin.es.ES;

public class PersonController extends Controller {

	@Inject
	public PersonController(ES es) {
		Person.registerAsType(Person.class, es);
	} 
	
    public static F.Promise<Result> getAdults(int page) {
        return Person.getAdults(page).map(persons -> {
            return ok(persons.toString());
        });
    }

}
