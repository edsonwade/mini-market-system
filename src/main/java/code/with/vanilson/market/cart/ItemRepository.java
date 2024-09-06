package code.with.vanilson.market.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CartRepository
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-06
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
