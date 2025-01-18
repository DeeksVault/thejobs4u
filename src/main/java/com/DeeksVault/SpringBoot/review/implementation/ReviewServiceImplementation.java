package com.DeeksVault.SpringBoot.review.implementation;

import com.DeeksVault.SpringBoot.company.Company;
import com.DeeksVault.SpringBoot.company.CompanyRepository;
import com.DeeksVault.SpringBoot.company.CompanyService;
import com.DeeksVault.SpringBoot.review.Review;
import com.DeeksVault.SpringBoot.review.ReviewRepository;
import com.DeeksVault.SpringBoot.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImplementation() {
    }

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getReviewsForCompany(Long companyId){
        if(companyService.getCompanyById(companyId)!=null){
            return reviewRepository.findByCompanyId(companyId);
        }
        return null;
    }

    @Override
    public void createReviewForCompany(Long companyId , Review review){
        Company company = companyService.getCompanyById(companyId);
        review.setCompany(company);
        reviewRepository.save(review);
    }

    @Override
    public Review getReviewByCompanyId(Long companyId , Long reviewId){
        if(companyService.getCompanyById(companyId)!=null){
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            for(Review review : reviews){
                if(review.getId().equals(reviewId)){
                    return review;
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId , Long reviewId){

        return false;
    }

    @Override
    public boolean deleteReview(){
        return false;
    }

}
