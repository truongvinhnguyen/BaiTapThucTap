package org.example.baitapthuctap.service;

import org.example.baitapthuctap.entity.Project;
import org.example.baitapthuctap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll(){
        return projectRepository.findAll();
    }
}
