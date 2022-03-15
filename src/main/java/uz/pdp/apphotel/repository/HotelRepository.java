package uz.pdp.apphotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apphotel.entity.Hotel;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    boolean existsByName(String name);
}
