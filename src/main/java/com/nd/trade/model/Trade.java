package com.nd.trade.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String tradeId;

    private int version;

    private String counterParty;
    private String bookId;
    private Date maturityDate;
    private Date createdDate;
    private String status;


}
