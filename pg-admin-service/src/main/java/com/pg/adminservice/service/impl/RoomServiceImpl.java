package com.pg.adminservice.service.impl;

import com.pg.adminservice.dto.RoomDTO;
import com.pg.adminservice.entity.Room;
import com.pg.adminservice.exception.ResourceNotFoundException;
import com.pg.adminservice.repository.RoomRepository;
import com.pg.adminservice.service.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        // Additional validation usually goes here or validation annotations on DTO
        if (roomRepository.existsByRoomNumber(roomDTO.getRoomNumber())) {
             throw new RuntimeException("Room with number " + roomDTO.getRoomNumber() + " already exists!");
        }
        
        // Allowed sharing types validation
        if (!List.of(2, 3, 4, 5).contains(roomDTO.getSharingType())) {
             throw new RuntimeException("Sharing type must be 2, 3, 4, or 5");
        }

        Room room = mapToEntity(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return mapToDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));

        // Update fields
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setSharingType(roomDTO.getSharingType());
        room.setFloorNumber(roomDTO.getFloorNumber());
        room.setBathroomAttached(roomDTO.getBathroomAttached());
        room.setRoomRent(roomDTO.getRoomRent());
        room.setRoomAdvance(roomDTO.getRoomAdvance());

        Room updatedRoom = roomRepository.save(room);
        return mapToDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
        roomRepository.delete(room);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
        return mapToDTO(room);
    }

    // Mapper methods
    private RoomDTO mapToDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(room, roomDTO);
        return roomDTO;
    }

    private Room mapToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        return room;
    }
}
