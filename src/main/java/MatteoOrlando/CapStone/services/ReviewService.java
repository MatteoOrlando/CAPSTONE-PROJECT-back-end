package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.Review;
import MatteoOrlando.CapStone.exceptions.NotFoundException;

import MatteoOrlando.CapStone.repositories.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    public Review saveReview(Review review) {
        return reviewDAO.save(review);
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewDAO.findByProductId(productId);
    }

    public Review updateReview(Review review) {
        if (!reviewDAO.existsById(review.getId())) {
            throw new NotFoundException("Review not found with id: " + review.getId());
        }
        return reviewDAO.save(review);
    }

    public void deleteReview(Long id) {
        if (!reviewDAO.existsById(id)) {
            throw new NotFoundException("Review not found with id: " + id);
        }
        reviewDAO.deleteById(id);
    }
}
