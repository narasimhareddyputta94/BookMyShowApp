package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Payment;
import com.bookmyshow.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setAmount(paymentDetails.getAmount());
            payment.setPaymentMode(paymentDetails.getPaymentMode());
            payment.setPaymentStatus(paymentDetails.getPaymentStatus());
            payment.setBooking(paymentDetails.getBooking());
            payment.setPaymentGateWayProvider(paymentDetails.getPaymentGateWayProvider());
            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id " + id);
        }
    }
}
