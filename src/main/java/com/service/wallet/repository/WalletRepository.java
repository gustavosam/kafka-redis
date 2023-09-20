package com.service.wallet.repository;

import com.service.wallet.documents.WalletDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface WalletRepository extends ReactiveMongoRepository<WalletDocument, String> {

    Mono<WalletDocument> findByPhoneNumber(String phoneNumber);
}
