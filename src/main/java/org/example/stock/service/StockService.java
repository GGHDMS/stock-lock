package org.example.stock.service;

import org.example.stock.domain.Stock;
import org.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
