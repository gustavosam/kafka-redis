package com.service.wallet.documents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "yankiwallet")
@ToString
public class WalletDocument {

    @Id
    private String walletNumber;

    private String debitCard;

    private Double amount;

    private String clientDocument;

    private String phoneNumber;

    private String phoneImei;

    private String email;

}
