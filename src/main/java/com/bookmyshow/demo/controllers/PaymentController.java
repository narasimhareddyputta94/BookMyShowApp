package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Payment;
import com.bookmyshow.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (!paymentOptional.isPresent()) {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }
        Payment payment = paymentOptional.get();
        payment.setAmount(paymentDetails.getAmount());
        payment.setPaymentMode(paymentDetails.getPaymentMode());
        payment.setPaymentStatus(paymentDetails.getPaymentStatus());
        paymentRepository.save(payment);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable Long id) {
        if (!paymentRepository.existsById(id)) {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }
        paymentRepository.deleteById(id);
        return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
    }
}
