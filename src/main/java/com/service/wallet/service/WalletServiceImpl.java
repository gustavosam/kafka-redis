package com.service.wallet.service;

import com.service.wallet.documents.WalletDocument;
import com.service.wallet.repository.WalletRepository;
import com.service.wallet.util.Convert;
import com.service.wallet.util.TransferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private Convert convert;

    private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);


    @Override
    public void createWallet(String data) {

        WalletDocument walletDocument = convert.stringToWalletDoc(data);
        walletRepository.save(walletDocument).subscribe();
    }

    @Override
    public void transferWallet(String data) {
        TransferDto transferDto = convert.stringToTransfer(data);

        String originPhone = transferDto.getOriginPhone();
        String destPhone = transferDto.getDestinPhone();
        Double amount = transferDto.getAmount();

        Mono<WalletDocument> walletOrigin = walletRepository.findByPhoneNumber(originPhone);
        Mono<WalletDocument> walletDest = walletRepository.findByPhoneNumber(destPhone);

        walletOrigin.zipWith(walletDest).flatMap(tuple -> {

            WalletDocument walletO = tuple.getT1();
            WalletDocument walletD = tuple.getT2();

            walletD.setAmount( walletD.getAmount() + amount );
            walletO.setAmount( walletO.getAmount() - amount );

            return walletRepository.save(walletD).then(walletRepository.save(walletO));

        }).subscribe();

    }

    @Override
    public void getWallet(String data) {
        Mono<WalletDocument> walletOrigin = walletRepository.findByPhoneNumber(data);

        walletOrigin.doOnNext(walletDocument -> {
            logger.info(walletDocument.toString());
        }).subscribe();
    }
}
