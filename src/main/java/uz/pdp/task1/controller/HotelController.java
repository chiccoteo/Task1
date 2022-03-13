package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.repo.HotelRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepo hotelRepo;

    @PostMapping("/save")
    public String save(@RequestBody Hotel hotel) {
        hotelRepo.save(hotel);
        return "Saved";
    }

    @GetMapping("/all")
    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }

    @PutMapping("/edit/{id}")
    public String editById(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (optionalHotel.isEmpty())
            return "Hotel not found";
        Hotel hotel1 = optionalHotel.get();
        hotel1.setName(hotel.getName());
        hotelRepo.save(hotel1);
        return "Edited";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (optionalHotel.isEmpty())
            return "Hotel not found";
        hotelRepo.deleteById(id);
        return "Deleted";
    }
}
