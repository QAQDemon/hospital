package edu.neu.medical.hospital.service;

import edu.neu.medical.hospital.bean.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();
}
