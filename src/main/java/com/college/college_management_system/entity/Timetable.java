package com.college.college_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "timetables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Branch is required")
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @NotNull(message = "Semester is required")
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @NotNull(message = "Section is required")
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
    private String fileUrl;

    private String fileType;

    private LocalDateTime uploadedAt;
}
