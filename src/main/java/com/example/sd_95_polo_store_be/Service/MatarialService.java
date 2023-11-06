package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Materials;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;

import java.util.List;

public interface MatarialService {
    List<Materials> gets();

    Materials createOrUpdate(Materials materials);

    boolean isMaterial(Materials materials);

    void deleteMaterialByIds(List<Integer> ids);
}
