package uz.pdp.apphotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apphotel.dto.ApiResponse;
import uz.pdp.apphotel.entity.Hotel;
import uz.pdp.apphotel.repository.HotelRepository;

import java.util.Optional;

@Service
public class HotelService {
    final
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public ApiResponse add(Hotel hotel) {
        Hotel newHotel = new Hotel();
        if (hotelRepository.existsByName(hotel.getName())) {
            return new ApiResponse("there is hotel with such name",false);
        }
        newHotel.setName(hotel.getName());
        hotelRepository.save(newHotel);
        return new ApiResponse("Added",true);
    }

    public ApiResponse get() {
        return new ApiResponse("Success",true,hotelRepository.findAll());
    }

    public ApiResponse getById(Integer id) {
        return new ApiResponse("Success",true,hotelRepository.findById(id));
    }

    public ApiResponse edit(Hotel hotel, Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            return new ApiResponse("Not found hotel", false);
        }
        Hotel editedHotel = optionalHotel.get();
        editedHotel.setName(hotel.getName());
        return new ApiResponse("Edited",true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            return new ApiResponse("Not found hotel",false);
        }
        hotelRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
