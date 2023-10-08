package com.example.demo;

import com.google.cloud.secretmanager.v1.Secret;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretName;
import java.io.IOException;

public class Test {

    public static void getSecret() throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String projectId = "utopian-saga-401406";
        String secretId = "test";
        getSecret(projectId, secretId);
    }

    // Get an existing secret.
    public static void getSecret(String projectId, String secretId) throws IOException {
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            // Build the name.
            SecretName secretName = SecretName.of(projectId, secretId);

            // Create the secret.
            Secret secret = client.getSecret(secretName);

            // Get the replication policy.
            String replication = "";
            if (secret.getReplication().getAutomatic() != null) {
                replication = "AUTOMATIC";
            } else if (secret.getReplication().getUserManaged() != null) {
                replication = "MANAGED";
            } else {
                throw new IllegalStateException("Unknown replication type");
            }

            System.out.printf("Secret %s, replication %s\n", secret.getName(), replication);
        }
    }
}