package uz.pdp.apphotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.apphotel.dto.ApiResponse;
import uz.pdp.apphotel.dto.RoomDto;
import uz.pdp.apphotel.entity.Hotel;
import uz.pdp.apphotel.entity.Room;
import uz.pdp.apphotel.repository.HotelRepository;
import uz.pdp.apphotel.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    final RoomRepository roomRepository;
    final HotelRepository hotelRepository;


    public ApiResponse add(RoomDto dto) {
        Room room = new Room();
        room.setFloor(dto.getFloor());
        room.setNumber(dto.getNumber());
        room.setSize(dto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
        if (optionalHotel.isEmpty()) {
            return new ApiResponse("Not found hotel",false);
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return new ApiResponse("Added",true);
    }
    public ApiResponse get(){
        List<Room> all = roomRepository.findAll();
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getById(Integer id){
        if (roomRepository.findById(id).isEmpty()) {
            return new ApiResponse("Not found room",false);
        }
        return new ApiResponse("Mana",true,roomRepository.findById(id).get());
    }
    public ApiResponse edit(RoomDto dto,Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            return new ApiResponse("Not found room",false);
        }
        Room room = optionalRoom.get();
        room.setNumber(dto.getNumber());
        room.setSize(dto.getSize());
        room.setFloor(dto.getFloor());
        Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
        if (optionalHotel.isEmpty()) {
            return new ApiResponse("Not found hotel",false);
        }
        room.setHotel(optionalHotel.get());
        return new ApiResponse("Edited",true);
    }
    public ApiResponse delete(Integer id){
        roomRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }

    public Page<Room> getRooms(int page,Integer hotelId) {
        Pageable pageable = PageRequest.of(page,10);
        return roomRepository.findAllByHotelId(hotelId, pageable);
    }
}
