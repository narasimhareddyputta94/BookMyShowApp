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
        try {
            return paymentRepository.save(payment);
        } catch (Exception e) {
            System.err.println("Error creating payment: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for debugging
            throw e;
        }
    }

    public Optional<Payment> updatePayment(Long id, Payment paymentDetails) {
        try {
            Optional<Payment> paymentOptional = paymentRepository.findById(id);
            if (paymentOptional.isPresent()) {
                Payment payment = paymentOptional.get();
                payment.setAmount(paymentDetails.getAmount());
                payment.setPaymentMode(paymentDetails.getPaymentMode());
                payment.setPaymentStatus(paymentDetails.getPaymentStatus());
                payment.setBooking(paymentDetails.getBooking());
                payment.setPaymentGateWayProvider(paymentDetails.getPaymentGateWayProvider());
                return Optional.of(paymentRepository.save(payment));
            }
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error updating payment: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for debugging
            throw e;
        }
    }

    public boolean deletePayment(Long id) {
        try {
            if (paymentRepository.existsById(id)) {
                paymentRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error deleting payment: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for debugging
            throw e;
        }
    }
}
