package com.suber.controller

import com.suber.data.Rating
import com.suber.services.RatingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('rating')
class RatingController {

    @Autowired
    RatingService ratingService

    @GetMapping
    List<Rating> getAllRatingList(){
        ratingService.findAll()
    }

    @PostMapping
    Rating saveRating(@RequestBody Rating rating){
        ratingService.saveRating rating
    }

    @PutMapping
    Rating updateRating(@RequestBody Rating rating){
        ratingService.updateRating(rating)
    }

    @DeleteMapping('/{ratingId}')
    deleteRating(@PathVariable long ratingId){
        ratingService.deleteRating ratingId
    }

    @GetMapping('/{ratingId}')
    Optional<Rating> getRatingById(@PathVariable long ratingId){
        ratingService.findById(ratingId)
    }


}
