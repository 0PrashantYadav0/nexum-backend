package com.pixels.Nexum.service;


import com.pixels.Nexum.model.FileModel;

import java.util.Optional;
import java.util.Vector;

public interface FileService {

    FileModel uploadFile(FileModel fileModel);

    Optional<FileModel> downloadFile(int fileId);

    Vector<FileModel> getAllFiles();

    FileModel deleteFile(int fileId);

    Optional<FileModel> downloadFileByUserId(int userId);

}