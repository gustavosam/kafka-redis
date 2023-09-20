package com.service.wallet.consumer;

import com.service.wallet.service.ClientService;
import com.service.wallet.service.ClientServiceImpl;
import com.service.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WalletConsumer {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ClientService clientService;

    private static final Logger logger = LoggerFactory.getLogger(WalletConsumer.class);

    @KafkaListener(topics = "yanki-topic", groupId = "group_id")
    public void consumeCreate(String data) {

        walletService.createWallet(data);
    }

    @KafkaListener(topics = "yanki-tranf-topic", groupId = "group_id_transf")
    public void consumeTransfer(String data) {
        walletService.transferWallet(data);
    }

    @KafkaListener(topics = "yanki-get-topic", groupId = "group_id_get")
    public void getWallet(String data) {
        walletService.getWallet(data);
    }

    @KafkaListener(topics = "yanki-client-topic", groupId = "group_client_create")
    public void createClient(String data) {
        clientService.createClient(data);
    }

    @KafkaListener(topics = "yanki-client-get-topic", groupId = "group_client_get")
    public void getClient(String clientDocument) {
        logger.info(clientService.getClient(clientDocument));
    }
}
