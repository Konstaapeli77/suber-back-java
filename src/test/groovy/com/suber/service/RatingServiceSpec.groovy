package com.suber.service

import com.suber.data.Rating
import com.suber.repository.PersonRepository
import com.suber.repository.RatingRepository
import com.suber.services.PersonService
import com.suber.services.RatingService
import com.suber.services.impl.RatingServiceImpl
import com.suber.util.TestData
import org.junit.Assert
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

@SpringBootTest
class RatingServiceSpec extends Specification {

    def "Return rating using id"() {

        given:
        def repository = Mock(RatingRepository.class)
        def service = new RatingServiceImpl(repository)
        Rating rating = new Rating()
        rating.id = 1L
        rating.comment = "comment"
        rating.stars = 3
        rating.recommend = 4

        when:
        service.findById(1L)

        then:
        1 * repository.findById(1L)

    }

    def "Save Rating"() {

        given:
        def repository = Mock(RatingRepository.class)
        def service = new RatingServiceImpl(repository)
        Rating rating = new Rating()
        rating.id = 1L
        rating.comment = "comment"
        rating.stars = 3
        rating.recommend = 4

        when:
        service.saveRating(rating)

        then:
        1 * repository.save(rating)

    }

}
