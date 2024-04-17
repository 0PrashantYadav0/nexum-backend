package com.pixels.Nexum.service;

import com.pixels.Nexum.model.WorkerModel;

import java.util.Vector;

public interface WorkerService{

    WorkerModel createUser(WorkerModel workerModel);

    WorkerModel getUserById(int userId);

    Vector<WorkerModel> getAllUsers();

    WorkerModel updateUser(WorkerModel workerModel);

    WorkerModel deleteUser(int userId);

}
