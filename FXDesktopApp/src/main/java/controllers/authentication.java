package controllers;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.param.*;
import com.stripe.model.*;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.net.*;
import com.stripe.param.issuing.CardCreateParams;
import javafx.scene.control.Alert;
import model.User;
import model.paymentCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class authentication {

    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_51IXZk5IplnIS4MmwVHJiEZHfzOyKqpBJY9saSa1ugKy3sY08pXRN1rjgLaAXUABoj6TW2aV80iz5yd3nryM2H9Bi00jOdOIpE7";

    public authentication() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;

    }

    public static void alertBox(String text, String Title, String HeaderText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public static boolean isNumeric(String num) {
        int intValue;
        try {
            intValue = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException ignored) {

        }
        return false;
    }

    public static CustomerCollection getCustomers() {

        try {
            CustomerListParams params = CustomerListParams.builder().build();
            CustomerCollection customers = Customer.list(params);
            System.out.println(customers.toJson());
            return customers;
        } catch (CardException e) {
            alertBox(e.getMessage(), "Fail!", "Card Exception");
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
            alertBox(e.getMessage(), "Fail!", "Slow down Buddy!");

        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
            alertBox(e.getMessage(), "Fail", "Invalid Request!");

        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed (wrong API key?)
            System.out.println("Wrong API Key!");
        } catch (StripeException e) {
            // Generic error
            alertBox(e.getMessage(), "Fail", "Invalid Request!");

        } catch (Exception e) {
            // Something else happened unrelated to Stripe
            alertBox(e.getMessage(), "Fail", "Invalid Request!");

        }
        return null;
    }

    public static Customer getCustomerByID(String id) {

        try {
            CustomerListParams params = CustomerListParams.builder().build();
            Customer customer = Customer.retrieve(id);
            System.out.println(customer);
            return customer;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String addCustomer(User user) {
        if (user.getFullName().isEmpty() || user.getEmail().isEmpty() || user.getPostalCode().isEmpty() || user.getPhone().isEmpty()) {
            alertBox("Missing fields!", "Fail!", "Invalid Request!");
        } else if (!isNumeric(user.getPostalCode())) {
            alertBox("Postal Code is invalid!", "Fail!", "Invalid Request!");
        } else if (!isNumeric(user.getPhone()) || user.getPhone().length() != 8) {
            alertBox("Phone Number is invalid!", "Fail!", "Invalid Request!");
        } else {
            CustomerCreateParams params = CustomerCreateParams.builder()
                    .setName(user.getFullName())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhone())
                    .setAddress(
                            CustomerCreateParams.Address.builder()
                                    .setCity(user.getCity())
                                    .setCountry("Tunisia")
                                    .setPostalCode(user.getPostalCode())
                                    .build()
                    ).setBalance(10000L)//For testing
                    .build();
            try {
                Customer customer = Customer.create(params);
                alertBox("Request Successful Proceed to payment!", "Done!", "You Can now add a Card for your payment!");
                return customer.getId();
            } catch (CardException e) {
                alertBox(e.getMessage(), "Fail!", "Card Exception");
            } catch (RateLimitException e) {
                // Too many requests made to the API too quickly
                alertBox(e.getMessage(), "Fail!", "Slow down Buddy!");

            } catch (InvalidRequestException e) {
                // Invalid parameters were supplied to Stripe's API
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (AuthenticationException e) {
                // Authentication with Stripe's API failed (wrong API key?)
                System.out.println("Wrong API Key!");
            } catch (StripeException e) {
                // Generic error
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (Exception e) {
                // Something else happened unrelated to Stripe
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            }
        }
        return null;
    }

    public String createInvoice(User user) {
        if (user.getCustomerID().isEmpty()) {
            return null;
        } else {

            try {
                Map<String, Object> params = new HashMap<>();
                params.put("customer", "cus_JCjWOvgXixaPi7");

                Invoice invoice = Invoice.create(params);

            } catch (CardException e) {
                alertBox(e.getMessage(), "Fail!", "Card Exception");
            } catch (RateLimitException e) {
                // Too many requests made to the API too quickly
                alertBox(e.getMessage(), "Fail!", "Slow down Buddy!");

            } catch (InvalidRequestException e) {
                // Invalid parameters were supplied to Stripe's API
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (AuthenticationException e) {
                // Authentication with Stripe's API failed (wrong API key?)
                System.out.println("Wrong API Key!");
            } catch (StripeException e) {
                // Generic error
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (Exception e) {
                // Something else happened unrelated to Stripe
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            }
        }
        return null;
    }

    public String chargeCustomer(User user) {
        if (user.getCustomerID().isEmpty()) {
            return null;
        } else {

            try {
                int chargeAmountCents = (int) 20 * 100;


                Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", chargeAmountCents);
                chargeParams.put("currency", "usd");
                chargeParams.put("description", "Monthly Charges");
                chargeParams.put("customer", user.getCustomerID());
                Charge charge = Charge.create(chargeParams);
                System.out.println(charge);

            } catch (CardException e) {
                alertBox(e.getMessage(), "Fail!", "Card Exception");
            } catch (RateLimitException e) {
                // Too many requests made to the API too quickly
                alertBox(e.getMessage(), "Fail!", "Slow down Buddy!");

            } catch (InvalidRequestException e) {
                // Invalid parameters were supplied to Stripe's API
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (AuthenticationException e) {
                // Authentication with Stripe's API failed (wrong API key?)
                System.out.println("Wrong API Key!");
            } catch (StripeException e) {
                // Generic error
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (Exception e) {
                // Something else happened unrelated to Stripe
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            }
        }
        return null;
    }

    public String addCard(String customerID, paymentCard payCard) {
        if (customerID.isEmpty()) {
            return null;
        } else {

            try {

                PaymentMethodCreateParams params3 = PaymentMethodCreateParams.builder().
                        setBillingDetails(PaymentMethodCreateParams.BillingDetails.builder()
                                .setAddress(PaymentMethodCreateParams.BillingDetails.Address.builder()
                                        .setCity(payCard.getCity())
                                        .setCountry(payCard.getCountry())
                                        .setLine1(payCard.getAdr1())
                                        .setLine2(payCard.getAdr2())
                                        .build()).build())
                        .setCard(PaymentMethodCreateParams.CardDetails.builder()
                                .setNumber(payCard.getCardNumber())
                                .setExpMonth((long) Integer.parseInt(payCard.getMM()))
                                .setExpYear((long) Integer.parseInt(payCard.getYY()))
                                .setCvc(payCard.getCVC())
                                .build())
                        .setType(PaymentMethodCreateParams.Type.CARD)
                        .build();


                PaymentMethod paymentMethod =
                        PaymentMethod.create(params3);

                PaymentMethod paymentMethod2 =
                        PaymentMethod.retrieve(
                                paymentMethod.getId()
                        );

                Map<String, Object> params2 = new HashMap<>();
                params2.put("customer", customerID);

                PaymentMethod updatedPaymentMethod =
                        paymentMethod.attach(params2);
                Customer customer =
                        Customer.retrieve(customerID);

                CustomerUpdateParams paramsCus = CustomerUpdateParams.builder()

                        .setInvoiceSettings(CustomerUpdateParams.InvoiceSettings.builder()
                                .setDefaultPaymentMethod(paymentMethod.getId())
                                .build())

                        .build();

                Customer updatedCustomer = customer.update(paramsCus);

                return customerID;

            } catch (CardException e) {
                alertBox(e.getMessage(), "Fail!", "Card Exception");
            } catch (RateLimitException e) {
                // Too many requests made to the API too quickly
                alertBox(e.getMessage(), "Fail!", "Slow down Buddy!");

            } catch (InvalidRequestException e) {
                // Invalid parameters were supplied to Stripe's API
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (AuthenticationException e) {
                // Authentication with Stripe's API failed (wrong API key?)
                System.out.println("Wrong API Key!");
            } catch (StripeException e) {
                // Generic error
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            } catch (Exception e) {
                // Something else happened unrelated to Stripe
                alertBox(e.getMessage(), "Fail", "Invalid Request!");

            }
        }
        return null;
    }


    public String chargeCustomer(String customerID,Long Order) {
        if(customerID.isEmpty()){
            return null;
        }
        else {

            try {

                Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount",Order*100 );
                chargeParams.put("currency", "usd");
                chargeParams.put("description", "1 Hour Class");
                chargeParams.put("customer", customerID);
                Charge charge = Charge.create(chargeParams);
            return charge.getId();

            }  catch (CardException e) {
                alertBox(e.getMessage(),"Fail!","Card Exception");
            } catch (RateLimitException e) {
                // Too many requests made to the API too quickly
                alertBox(e.getMessage(),"Fail!","Slow down Buddy!");

            } catch (InvalidRequestException e) {
                // Invalid parameters were supplied to Stripe's API
                alertBox(e.getMessage(),"Fail","Invalid Request!");

            } catch (AuthenticationException e) {
                // Authentication with Stripe's API failed (wrong API key?)
                System.out.println("Wrong API Key!");
            } catch (StripeException e) {
                // Generic error
                alertBox(e.getMessage(),"Fail","Invalid Request!");

            } catch (Exception e) {
                // Something else happened unrelated to Stripe
                alertBox(e.getMessage(),"Fail","Invalid Request!");

            }
        }
        return null;
    }
}
