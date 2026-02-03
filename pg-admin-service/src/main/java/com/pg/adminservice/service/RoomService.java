package com.pg.adminservice.service;

import com.pg.adminservice.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(Long id, RoomDTO roomDTO);
    void deleteRoom(Long id);
    List<RoomDTO> getAllRooms();
    RoomDTO getRoomById(Long id);
}
