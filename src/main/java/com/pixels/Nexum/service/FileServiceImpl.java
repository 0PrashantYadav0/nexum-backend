package com.pixels.Nexum.service;

import com.pixels.Nexum.model.FileModel;
import com.pixels.Nexum.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileRepository fileRepo;

    @Override
    public FileModel uploadFile(FileModel fileModel) {
        // TODO Auto-generated method stub
        return this.fileRepo.save(fileModel);
    }

    @Override
    public Optional<FileModel> downloadFile(int fileId) {
        return this.fileRepo.findById(fileId);
    }

    @Override
    public Vector<FileModel> getAllFiles() {
        return new Vector<>(this.fileRepo.findAll());
    }

    @Override
    public FileModel deleteFile(int fileId) {
        FileModel fileModel = this.fileRepo.findById(fileId).get();
        this.fileRepo.delete(fileModel);
        return fileModel;
    }

    @Override
    public Optional<FileModel> downloadFileByUserId(int userId) {
        List<FileModel> listFile =this.fileRepo.findAll();
        for(FileModel fileModel : listFile){
            if(fileModel.getUser().getUserId() == userId){
                return Optional.of(fileModel);
            }
        }
        return Optional.empty();
    }

}
