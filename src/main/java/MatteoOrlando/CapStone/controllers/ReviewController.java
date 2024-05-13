package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.ReviewDTO;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDTO createReview(@RequestBody @Validated ReviewDTO reviewDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid review data provided.");
        }
        return reviewService.saveReview(reviewDTO);
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody @Validated ReviewDTO reviewDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid review data provided.");
        }
        return reviewService.updateReview(id, reviewDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
