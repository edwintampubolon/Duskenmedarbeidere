package no.dusken.medarbeidere.service;

import no.dusken.common.model.Person;
import no.dusken.common.model.Person_;
import no.dusken.common.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class PersonSearch {
    @Autowired
    private PersonService personService;

    public List<Person> searchByFirstAndSurname(final String query){
        Specification<Person> likeQuery = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> personRoot, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate firstnameLike = criteriaBuilder.like(personRoot.get(Person_.firstname), query);
                Predicate surnameLike = criteriaBuilder.like(personRoot.get(Person_.surname), query);

                return criteriaBuilder.or(firstnameLike, surnameLike);
            }
        };
        return personService.findAll(likeQuery);
    }
}
