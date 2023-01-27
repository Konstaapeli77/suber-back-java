package com.suber.repository

import com.suber.data.Rating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository extends JpaRepository<Rating, Long> {

}


