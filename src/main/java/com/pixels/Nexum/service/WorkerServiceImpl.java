package com.pixels.Nexum.service;

import com.pixels.Nexum.model.WorkerModel;
import com.pixels.Nexum.repository.WorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class WorkerServiceImpl implements WorkerService{

    @Autowired
    private WorkersRepository workersRepository;
    @Override
    public WorkerModel createUser(WorkerModel workerModel) {
        return this.workersRepository.save(workerModel);
    }

    @Override
    public WorkerModel getUserById(int userId) {
        return this.workersRepository.findById(userId).orElse(null);
    }

    @Override
    public Vector<WorkerModel> getAllUsers() {
        return new Vector<>(this.workersRepository.findAll());
    }

    @Override
    public WorkerModel updateUser(WorkerModel workerModel) {
        return this.workersRepository.save(workerModel);
    }

    @Override
    public WorkerModel deleteUser(int userId) {
        WorkerModel workerModel = this.workersRepository.findById(userId).orElse(null);
        this.workersRepository.delete(workerModel);
        return workerModel;
    }

    @Override
    public WorkerModel getHeroWorker() {
        return null;
    }
}
