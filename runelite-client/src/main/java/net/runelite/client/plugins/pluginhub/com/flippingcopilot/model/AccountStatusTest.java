package net.runelite.client.plugins.pluginhub.com.flippingcopilot.model;

import org.junit.Test;

public class AccountStatusTest {

    @Test
    public void testMoreGpNeeded() {
        AccountStatus accountStatus = new AccountStatus();
        assert accountStatus.moreGpNeeded();
    }

    @Test
    public void testNoMoreGpNeeded() {
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.getInventory().add(new RSItem(995, 2000));
        assert !accountStatus.moreGpNeeded();
    }
}

//package com.flippingcopilot.model;
//
//import org.junit.Test;
//
//import java.time.Instant;
//
//public class OfferTest {
//
//    @Test
//    public void testTransactionFromNewOffer() {
//        Offer oldOffer = Offer.getEmptyOffer(0);
//        Offer newOffer = new Offer(OfferStatus.BUY, 560, 100, 50, 1000,
//                10, 10, 0, 0, true, false);
//        Transaction expectedTransaction = new Transaction(null, OfferStatus.BUY, 560, 100, 10, 0, 1000, null,false, 0, false);
//        Transaction actualTransaction = newOffer.getTransaction(oldOffer);
//        assert expectedTransaction.equals(actualTransaction);
//    }
//
//    @Test
//    public void testTransactionFromProgressingSell() {
//        Offer oldOffer = new Offer(OfferStatus.SELL, 560, 100, 50, 1000,
//                10, 0, 1000, 0, true, false);
//        Offer newOffer = new Offer(OfferStatus.SELL, 560, 100, 50, 4000,
//                40, 0, 4000, 0, true, false);
//        Transaction expectedTransaction = new Transaction(null, OfferStatus.SELL, 560, 100, 30, 0, 3000, null,false, 0, false);
//        Transaction actualTransaction = newOffer.getTransaction(oldOffer);
//        assert expectedTransaction.equals(actualTransaction);
//    }
//
//    @Test
//    public void testNoTransaction() {
//        Offer oldOffer = new Offer(OfferStatus.SELL, 560, 100, 50, 1000,
//                10, 0, 1000, 0, true, false);
//        Offer newOffer = new Offer(OfferStatus.SELL, 560, 100, 50, 1000,
//                10, 0, 1000, 0, true, false);
//        Transaction actualTransaction = newOffer.getTransaction(oldOffer);
//        assert actualTransaction == null;
//    }
//}
