package com.DeeksVault.SpringBoot.review;


import com.DeeksVault.SpringBoot.company.Company;
import com.DeeksVault.SpringBoot.company.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies/{companyId}/")
public class ReviewController {

    private ReviewService reviewService;
    private ReviewRepository reviewRepository;
    private CompanyRepository companyRepository;


    public ReviewController(ReviewService reviewService,
                            ReviewRepository reviewRepository,
                            CompanyRepository companyRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getOneCompanyReviews(@PathVariable Long companyId){
        if(companyRepository.findById(companyId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return new ResponseEntity<>(reviews , HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> createReview(@RequestBody Review review , @PathVariable Long companyId){
        System.out.println("in post request");
        System.out.println(review.getTitle());
        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if(optionalCompany.isPresent()){
           Company company = optionalCompany.get();
           System.out.println(company.getName());
           Review review1 = reviewService.createReviewForCompany(companyId , review);
           return new ResponseEntity<>(review1 , HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId , @PathVariable Long reviewId){
        if(companyRepository.findById(companyId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Review review = reviewService.getReview(companyId, reviewId);
        return new ResponseEntity<>(review , HttpStatus.OK);
    }
//    create update review api


    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<Long> deleteReview(@PathVariable Long companyId , @PathVariable Long reviewId){
        if(companyRepository.findById(companyId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(reviewService.deleteReview(reviewId)){
            return new ResponseEntity<>(reviewId , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


//GET /companies/{companyId}/reviews
//POST /companies/{companyId}/reviews
//GET /companies/{companyId}/reviews/{reviewId}
//PUT /companies/{companyId}/reviews/{reviewId}
//DELETE /companies/{companyId}/reviews/{reviewId}