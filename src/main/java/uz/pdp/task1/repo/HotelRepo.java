package uz.pdp.task1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {
}
