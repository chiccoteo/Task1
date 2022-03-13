package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.entity.Room;
import uz.pdp.task1.payload.RoomDto;
import uz.pdp.task1.repo.HotelRepo;
import uz.pdp.task1.repo.RoomRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    HotelRepo hotelRepo;

    @PostMapping("/save")
    public String save(@RequestBody RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(roomDto.getHotelId());
        if (optionalHotel.isEmpty())
            return "Hotel not found";

        boolean existsByNumber = roomRepo.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
        if (existsByNumber)
            return "This room already exists";

        Hotel hotel = optionalHotel.get();
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        roomRepo.save(room);
        return "Saved";
    }

    @GetMapping("/all")
    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    @PutMapping("/edit/{id}")
    public String editById(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (optionalRoom.isEmpty())
            return "Room not found";

        Optional<Hotel> optionalHotel = hotelRepo.findById(roomDto.getHotelId());
        if (optionalHotel.isEmpty())
            return "Hotel not found";

        boolean existsByNumber = roomRepo.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
        if (existsByNumber)
            return "This room already exists";

        Hotel hotel = optionalHotel.get();
        Room room = optionalRoom.get();
        room.setHotel(hotel);
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        roomRepo.save(room);
        return "Edited";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<Room> byId = roomRepo.findById(id);
        if (byId.isEmpty())
            return "Room not found";
        roomRepo.deleteById(id);
        return "Deleted";
    }

    @GetMapping("/allByHotelId/{hotelId}")
    public Page<Room> getPageOfRooms(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 2);
        return roomRepo.findByHotelId(hotelId, pageable);
    }
}
