package com.DeeksVault.SpringBoot.review;


import java.util.List;

public interface ReviewService {
    List<Review> getReviewsForCompany(Long companyId);

    Review createReviewForCompany(Long companyId , Review review);

    Review getReview(Long companyId , Long reviewId);

    boolean updateReview(Long companyId , Long reviewId);

    boolean deleteReview(Long reviewId);
}
