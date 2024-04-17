package com.pixels.Nexum.controller;


import com.pixels.Nexum.model.WorkerModel;
import com.pixels.Nexum.service.WorkerService;
import com.pixels.Nexum.utils.APIReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@CrossOrigin
@RequestMapping("/api/worker/")
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    private APIReturnModel apiReturnModel;

    private Vector<WorkerModel> workerVec;



    @GetMapping("getWorker")
    public ResponseEntity<?> getWorker(){
        apiReturnModel = new APIReturnModel();
        workerVec = new Vector<>();

        try {
            workerVec = this.workerService.getAllUsers();
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("All Users");
            apiReturnModel.setCount(workerVec.size());
            apiReturnModel.setData(workerVec);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("getWorkerById/{id}")
    public ResponseEntity<?> getWorkerById(@PathVariable("id") int id){
        apiReturnModel = new APIReturnModel();
        workerVec = new Vector<>();

        try {
            WorkerModel worker = this.workerService.getUserById(id);
            workerVec.add(worker);
            apiReturnModel.setData(workerVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("User Found Successfully !");
            apiReturnModel.setCount(workerVec.size());
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @PostMapping("addWorker")
    public ResponseEntity<?> createWorker(@RequestBody WorkerModel workerModel){
        apiReturnModel = new APIReturnModel();
        workerVec = new Vector<>();

        try {
            WorkerModel worker = this.workerService.createUser(workerModel);
            workerVec.add(worker);
            apiReturnModel.setData(workerVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("User Created Successfully !");
            apiReturnModel.setCount(workerVec.size());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @PutMapping("updateWorker")
    public ResponseEntity<?> updateWorker(@RequestBody WorkerModel workerModel){
        apiReturnModel = new APIReturnModel();
        workerVec = new Vector<>();

        try {
            WorkerModel worker = this.workerService.updateUser(workerModel);
            workerVec.add(worker);
            apiReturnModel.setData(workerVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("User Updated Successfully !");
            apiReturnModel.setCount(workerVec.size());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @DeleteMapping("deleteWorker/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable("id") int id){
        apiReturnModel = new APIReturnModel();
        workerVec = new Vector<>();

        try {
            WorkerModel worker = this.workerService.deleteUser(id);
            workerVec.add(worker);
            apiReturnModel.setData(workerVec);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("User Deleted Successfully !");
            apiReturnModel.setCount(workerVec.size());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }


}
