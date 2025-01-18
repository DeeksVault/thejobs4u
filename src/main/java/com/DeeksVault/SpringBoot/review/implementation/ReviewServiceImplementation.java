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
    public Review createReviewForCompany(Long companyId , Review review){
        Company company = companyService.getCompanyById(companyId);
        review.setCompany(company);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Review getReview(Long companyId , Long reviewId){
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId , Long reviewId){

        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId){
        if(reviewRepository.findById(reviewId).isPresent()){
            try{
                reviewRepository.deleteById(reviewId);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }
        return false;
    }

}
