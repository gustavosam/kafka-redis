package com.service.wallet.documents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "client")
@ToString
public class ClientDocument {

    @Id
    private String clientDocument;

    private String name;

}
