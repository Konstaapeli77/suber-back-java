package com.suber.controller

import com.suber.data.Rating
import com.suber.dto.PersonDTO
import com.suber.repository.RatingRepository
import com.suber.util.mapper.DataMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import spock.lang.Specification

@SpringBootTest
class RatingControllerSpec extends Specification {

    static String baseUrl = "http://localhost:8080/rating/"

    @Autowired
    PersonController personController;

    @Autowired
    RatingController controller;

    @Autowired
    RatingRepository repository;

    def setup() {
        repository.deleteAll()
    }

    def "Make GET (by id) request"() {
        given: "Create Rating object to be saved into database"
        def rating = new Rating();
        rating.comment = "Kommentti"
        rating.recommend = 1
        rating.stars = 3

        when: "Save rating to database and get it through controller"
        repository.save(rating);
        def resultRating = controller.getRatingById(1)

        then: "Check that there is matching rating received from controller"
        resultRating.isPresent()
        resultRating.get().getComment() == "Kommentti"
    }

    def "Make UPDATE (by id) request"() {
        given:
        def rating = new Rating()
        rating.comment = "Kommentti"
        rating.recommend = 1
        rating.stars = 3

        when:
        def savedRating = repository.save(rating)
        def updatedRating = controller.getRatingById(savedRating.getId())
        updatedRating.isPresent()
        updatedRating.get().setComment("Changed Comment")
        def updatedRating2 = controller.updateRating(updatedRating.get())
        def resultRating2 = controller.getRatingById(savedRating.getId())

        then:
        resultRating2.isPresent()
        resultRating2.get().getComment() == "Changed Comment"
    }

    def "Make POST request"() {
        given:
        def rating = new Rating();
        rating.comment = "Kommentti"
        rating.recommend = 1
        rating.stars = 3

        when:
        def savedRating = repository.save(rating);
        def resultRating = controller.getRatingById(savedRating.getId())

        then:
        resultRating.isPresent()
        resultRating.get().getComment() == "Kommentti"
    }

    def "Make DELETE (by id) request"() {
        given:
        def rating = new Rating();
        rating.comment = "Kommentti"
        rating.recommend = 1
        rating.stars = 3

        when:
        def savedRating = repository.save(rating);
        def savedRatingId = savedRating.getId()
        def resultRating = controller.getRatingById(savedRatingId)
        controller.deleteRating(savedRatingId)
        def deletedRating = controller.getRatingById(savedRatingId)

        then:
            deletedRating.isPresent() == false

    }

    def "Make GET (all ratings) request"() {
        given:
        def rating = new Rating();
        rating.comment = "Kommentti"
        rating.recommend = 1
        rating.stars = 3
        def rating2 = new Rating();
        rating.comment = "Kommentti2"
        rating.recommend = 3
        rating.stars = 4

        when:
        repository.deleteAll()
        repository.save(rating)
        repository.save(rating2)

        def resultRatingList = controller.getAllRatingList()

        then:
        resultRatingList.size() == 2
    }

    def "Make Person GET request"() {
        given:
            def personDTO = PersonDTO.builder()
                .firstname("Sanna")
                .lastname("Marin")
                .build();

        when:
            def savedPerson = repository.save(DataMapper.getInstance().convertToEntity(personDTO));

            def personsWithThatName = personController.findByFirstname("Jaakko");
            def list = personsWithThatName.getBody();

        then:
            list == null
            HttpStatus.NO_CONTENT == personsWithThatName.getStatusCode()
    }

}
