package uz.pdp.apphotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apphotel.dto.ApiResponse;
import uz.pdp.apphotel.dto.RoomDto;
import uz.pdp.apphotel.entity.Room;
import uz.pdp.apphotel.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    final RoomService roomService;

    @PostMapping
    public ApiResponse add(@RequestBody RoomDto dto){
        return roomService.add(dto);
    }
    @GetMapping
    public ApiResponse get(){
        return roomService.get();
    }
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        return roomService.getById(id);
    }
    @GetMapping("/byHotelId/{hotelId}")
    public Page<Room> getRooms(@RequestParam int page,@PathVariable Integer hotelId){
        return roomService.getRooms(page,hotelId);
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@RequestBody RoomDto dto,@PathVariable Integer id){
        return roomService.edit(dto,id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return roomService.delete(id);
    }

}
