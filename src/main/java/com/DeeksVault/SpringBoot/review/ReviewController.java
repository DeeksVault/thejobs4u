package com.DeeksVault.SpringBoot.review;


import com.DeeksVault.SpringBoot.company.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/")
public class ReviewController {

    private ReviewRepository reviewRepository;
    private CompanyRepository companyRepository;

    public ReviewController() {
    }

    public ReviewController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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
    public ResponseEntity<Review> createReview(){
        return null;
    }
}


//GET /companies/{companyId}/reviews
//POST /companies/{companyId}/reviews
//GET /companies/{companyId}/reviews/{reviewId}
//PUT /companies/{companyId}/reviews/{reviewId}
//DELETE /companies/{companyId}/reviews/{reviewId}