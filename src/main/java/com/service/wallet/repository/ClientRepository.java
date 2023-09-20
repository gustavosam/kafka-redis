package com.service.wallet.repository;

import com.service.wallet.documents.ClientDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<ClientDocument, String> {
}
