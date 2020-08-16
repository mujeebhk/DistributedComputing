package com.test2.demo2.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
	private static String SECRET_KEY = "oaRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

	private static Key secretKey = getKey();
	public static String create( Map<String, Object> claims) {
		logger.debug("Entering JWTController create method");
		String errorMessage = "";

		try {
			// Let's set the JWT Claims
			Date now = new Date();
			JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString()).setIssuedAt(now).setClaims(claims)
					.signWith(secretKey);

			// Builds the JWT and serializes it to a compact, URL-safe string
			return builder.compact();

		} catch (Exception e) {
			// TODO
			errorMessage = "Error occured while creating jwt. Error: " + e.getMessage();
			logger.error(errorMessage);
		} finally {
			logger.debug("Leaving JWTController create method");
		}

		return errorMessage;
	}

	
	public static boolean verify( String jwt) {
		logger.debug("Entering JWTController verify method");
		String errorMessage = "";

		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			// TODO
			errorMessage = "Error occured while verifying jwt. Error: " + e.getMessage();
			logger.error(errorMessage);
		} finally {
			logger.debug("Leaving JWTController verify method");
		}

		return false;
	}
	
	
	public static Key getKey() {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		return signingKey;
	}
	
public static void main(String[] args ) {
	Map<String, Object> claims = new HashMap<>();
	claims.put("name", "Mujeeb");
	claims.put("age", "33");
	
	String jwt = create(claims);
	System.out.println(jwt);
	System.out.println(verify(jwt));
	
}
}


