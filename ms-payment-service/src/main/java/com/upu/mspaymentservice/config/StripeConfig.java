// src/main/java/com/upu/mspaymentservice/config/StripeConfig.java
package com.upu.mspaymentservice.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${spring.stripe.api-key}")
    private String stripeApiKey;

    public StripeConfig() {
        Stripe.apiKey = stripeApiKey;
    }
}
