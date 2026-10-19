package com.college.college_management_system.service;

import com.college.college_management_system.entity.Section;
import com.college.college_management_system.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
    }

    public Section updateSection(Long id, Section section) {

        Section existingSection = getSectionById(id);

        existingSection.setName(section.getName());
        existingSection.setSectionCode(section.getSectionCode());
        existingSection.setBranch(section.getBranch());
        existingSection.setSemester(section.getSemester());

        return sectionRepository.save(existingSection);
    }

    public void deleteSection(Long id) {
        Section section = getSectionById(id);
        sectionRepository.delete(section);
    }
}
