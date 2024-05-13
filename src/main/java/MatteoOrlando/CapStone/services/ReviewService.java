package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.ReviewDTO;
import MatteoOrlando.CapStone.entities.Review;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.ReviewDAO;
import MatteoOrlando.CapStone.repositories.UserDAO;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    private ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(review.getId(), review.getUser().getId(), review.getProduct().getId(), review.getRating(), review.getContent());
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.id());
        review.setContent(reviewDTO.comment());
        review.setRating(reviewDTO.rating());

        User user = userDAO.findById(reviewDTO.userId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + reviewDTO.userId()));
        review.setUser(user);

        Product product = productDAO.findById(reviewDTO.productId())
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + reviewDTO.productId()));
        review.setProduct(product);

        return review;
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        Review savedReview = reviewDAO.save(review);
        return convertToDTO(savedReview);
    }

    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewDAO.findByProductId(productId);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        if (!reviewDAO.existsById(id)) {
            throw new NotFoundException("Review not found with id: " + id);
        }
        Review review = convertToEntity(reviewDTO);
        review.setId(id);
        Review updatedReview = reviewDAO.save(review);
        return convertToDTO(updatedReview);
    }

    public void deleteReview(Long id) {
        if (!reviewDAO.existsById(id)) {
            throw new NotFoundException("Review not found with id: " + id);
        }
        reviewDAO.deleteById(id);
    }
}
