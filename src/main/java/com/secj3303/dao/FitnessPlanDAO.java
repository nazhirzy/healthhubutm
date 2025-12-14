package com.secj3303.dao;

import com.secj3303.model.FitnessPlan;
import java.util.List;

public interface FitnessPlanDAO {
    List<FitnessPlan> findAll();
    FitnessPlan findById(int id);
    void save(FitnessPlan plan); 
    void delete(int id);
    List<FitnessPlan> findPlansByTrainerId(int trainerId);
}
