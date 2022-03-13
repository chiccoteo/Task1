package uz.pdp.task1.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {

    boolean existsByNumberAndHotelId(Integer number, Integer hotel_id);

    Page<Room> findByHotelId(Integer hotel_id, Pageable pageable);
}
