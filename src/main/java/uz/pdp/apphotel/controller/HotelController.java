package uz.pdp.apphotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apphotel.dto.ApiResponse;
import uz.pdp.apphotel.entity.Hotel;
import uz.pdp.apphotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    final HotelService hotelService;

    @PostMapping
    public ApiResponse add(@RequestBody Hotel hotel){
        return hotelService.add(hotel);
    }
    @GetMapping
    public ApiResponse get(){
        return hotelService.get();
    }
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id){
        return hotelService.getById(id);
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@RequestBody Hotel hotel,@PathVariable Integer id){
        return hotelService.edit(hotel,id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return hotelService.delete(id);
    }

}
