package com.college.college_management_system.service;

import com.college.college_management_system.entity.Classroom;
import com.college.college_management_system.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

    public Classroom updateClassroom(Long id, Classroom classroom) {

        Classroom existingClassroom = getClassroomById(id);

        existingClassroom.setRoomNumber(classroom.getRoomNumber());
        existingClassroom.setBlock(classroom.getBlock());
        existingClassroom.setFloor(classroom.getFloor());
        existingClassroom.setRoomType(classroom.getRoomType());
        existingClassroom.setCapacity(classroom.getCapacity());

        return classroomRepository.save(existingClassroom);
    }

    public void deleteClassroom(Long id) {
        Classroom classroom = getClassroomById(id);
        classroomRepository.delete(classroom);
    }

    public Classroom getClassroomByRoomNumber(String roomNumber) {
        return classroomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }
}
