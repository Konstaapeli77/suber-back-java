package com.suber.services

import com.suber.data.Rating


interface RatingService {

    List<Rating> findAll()

    Optional<Rating> findById(long ratingId)

    Rating saveRating(Rating rating)

    Rating updateRating(Rating rating)

    Rating deleteRating(long ratingId)

}