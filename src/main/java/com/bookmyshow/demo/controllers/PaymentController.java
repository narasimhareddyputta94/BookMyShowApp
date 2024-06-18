package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Payment;
import com.bookmyshow.demo.services.PaymentService;
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
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPayment(@RequestBody Payment payment) {
        try {
            Payment savedPayment = paymentService.createPayment(payment);
            return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        try {
            Optional<Payment> updatedPayment = paymentService.updatePayment(id, paymentDetails);
            if (!updatedPayment.isPresent()) {
                return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedPayment.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePayment(@PathVariable Long id) {
        try {
            boolean isDeleted = paymentService.deletePayment(id);
            if (!isDeleted) {
                return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}