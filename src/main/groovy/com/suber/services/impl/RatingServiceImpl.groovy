package com.suber.services.impl

import com.suber.data.Rating
import com.suber.repository.RatingRepository
import com.suber.services.RatingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    RatingServiceImpl(RatingRepository repository) {
        this.ratingRepository = repository
    }

    @Override
    List<Rating> findAll() {
        ratingRepository.findAll()
    }

    @Override
    Optional<Rating> findById(long ratingId) {
        ratingRepository.findById(ratingId)
    }

    @Override
    Rating saveRating(Rating rating) {
        ratingRepository.save rating
    }

    @Override
    Rating updateRating(Rating rating) {
        ratingRepository.save(rating)
    }

    @Override
    Rating deleteRating(long ratingId) {
        ratingRepository.deleteById(ratingId)
    }


}
