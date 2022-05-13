package demo.service;

import demo.entity.Store;
import demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public Store addStore(Store request) {
        return storeRepository.save(request);
    }

    public Optional<Store> findStoreByStoreId(Long storeId) {
        return storeRepository.findById(storeId);
    }
}
