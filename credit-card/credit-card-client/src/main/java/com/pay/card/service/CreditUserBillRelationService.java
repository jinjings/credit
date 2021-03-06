package com.pay.card.service;

import java.util.List;

import com.pay.card.model.CreditUserBillRelation;

public interface CreditUserBillRelationService {

    public List<CreditUserBillRelation> findCreditUserBillRelation(CreditUserBillRelation creditUserBillRelation)
            throws Exception;

    public void saveCreditUserBillRelation(CreditUserBillRelation creditUserBillRelation) throws Exception;

    public void updateNewStatus(List<Long> billId, Long userId) throws Exception;
}
