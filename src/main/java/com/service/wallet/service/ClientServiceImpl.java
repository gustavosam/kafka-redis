package com.service.wallet.service;

import com.service.wallet.documents.ClientDocument;
import com.service.wallet.repository.ClientRepository;
import com.service.wallet.util.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private Convert convert;

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private ClientDocument prueba;

    @Override
    public void createClient(String data) {

        ClientDocument clientDocument1 = convert.stringToClientDoc(data);
        clientRepository.save(clientDocument1).subscribe();
    }

    @Override
    @Cacheable(value = "userCache")
    public String getClient(String clientDocument) {

        Mono<ClientDocument> clientDocumentMono = clientRepository.findById(clientDocument);

        logger.info("PRIMERA VEZ SE CONSULTA A LA BD");

        clientDocumentMono.doOnNext(clientDoc -> {
            this.prueba = clientDoc;
        }).subscribe();

        return prueba.toString();
    }
}
