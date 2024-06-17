// resources/components/PaymentComponent.js
import React, { useEffect, useState } from 'react';
import { getPayments } from '../services/paymentService';

const PaymentComponent = () => {
    const [payments, setPayments] = useState([]);

    useEffect(() => {
        getPayments().then(response => {
            setPayments(response.data);
        }).catch(error => {
            console.error('Error fetching payments:', error);
        });
    }, []);

    return (
        <div>
            <h2>Payments</h2>
            <ul>
                {payments.map(payment => (
                    <li key={payment.id}>{payment.amount} - {payment.status}</li>
                ))}
            </ul>
        </div>
    );
};

export default PaymentComponent;
