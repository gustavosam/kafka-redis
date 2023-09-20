package com.service.wallet.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.wallet.documents.ClientDocument;
import com.service.wallet.documents.WalletDocument;
import org.springframework.stereotype.Component;

@Component
public class Convert {

    public WalletDocument stringToWalletDoc(String data){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WalletDocument walletDocument = objectMapper.readValue(data, WalletDocument.class);
            return walletDocument;

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error apropiadamente en tu aplicación
        }
    }


    public TransferDto stringToTransfer(String data){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TransferDto transferDto = objectMapper.readValue(data, TransferDto.class);
            return transferDto;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ClientDocument stringToClientDoc(String data){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClientDocument clientDocument = objectMapper.readValue(data, ClientDocument.class);
            return clientDocument;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
