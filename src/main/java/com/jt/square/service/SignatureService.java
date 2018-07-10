package com.jt.square.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignatureService {

	@Value("${square.signatureKey}")
	String signatureKey;

	@Value("${square.webhooksUrl}")
	String webhooksUrl;

	public boolean validate(String notificationSignature, String notificationBody)
			throws NoSuchAlgorithmException, InvalidKeyException {

		String stringToSign = webhooksUrl + notificationBody;

		final String algo = "HMacSHA1";
		final Mac mac = Mac.getInstance(algo);
		mac.init(new SecretKeySpec(signatureKey.getBytes(), algo));
		String stringSignature = Base64.getEncoder().encodeToString(mac.doFinal(stringToSign.getBytes()));

		return notificationSignature.equals(stringSignature);
	}
}