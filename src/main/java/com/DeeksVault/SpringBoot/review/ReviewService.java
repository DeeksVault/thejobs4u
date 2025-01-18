package com.DeeksVault.SpringBoot.review;


import java.util.List;

public interface ReviewService {
    List<Review> getReviewsForCompany(Long companyId);

    void createReviewForCompany(Long companyId , Review review);

    Review getReviewByCompanyId(Long companyId , Long reviewId);

    boolean updateReview(Long companyId , Long reviewId);

    boolean deleteReview();
}
