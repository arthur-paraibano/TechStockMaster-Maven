package com.techstockmaster.util;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/*
    Casse responsável pela criptografia de arquivos properties.
 */
public class CriptografiaUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String SECRET_KEY_FILE = "secret.key";

    // Metodo para gerar uma chave secreta e salvar em um arquivo
    private static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                Files.newOutputStream(Paths.get(SECRET_KEY_FILE)))) {
            outputStream.writeObject(secretKey);
        }
        return secretKey;
    }

    // Metodo para ler a chave secreta a partir do arquivo
    private static SecretKey loadSecretKey() throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(SECRET_KEY_FILE)))) {
            return (SecretKey) inputStream.readObject();
        }
    }

    // Metodo para criptografar o arquivo
    void criptografarArquivo(String nomeArquivo) {
        try {
            // Gera ou carrega a chave secreta
            SecretKey secretKey = Files.exists(Paths.get(SECRET_KEY_FILE)) ? loadSecretKey() : generateSecretKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // Lê as propriedades do arquivo
            Properties properties = new Properties();
            try (InputStream input = Files.newInputStream(Paths.get(nomeArquivo))) {
                properties.load(input);
            }

            // Criptografa as propriedades
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String dburl = properties.getProperty("dburl");
            String useSSL = properties.getProperty("useSSL");

            String encryptedUser = encrypt(user, cipher);
            String encryptedPassword = encrypt(password, cipher);
            String encryptedDburl = encrypt(dburl, cipher);
            String encryptedUseSSL = encrypt(useSSL, cipher);

            // Salva as propriedades criptografadas em um novo arquivo
            Properties encryptedProperties = new Properties();
            encryptedProperties.setProperty("user", encryptedUser);
            encryptedProperties.setProperty("password", encryptedPassword);
            encryptedProperties.setProperty("dburl", encryptedDburl);
            encryptedProperties.setProperty("useSSL", encryptedUseSSL);

            try (OutputStream output = Files.newOutputStream(Paths.get(nomeArquivo + ".encrypted"))) {
                encryptedProperties.store(output, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para descriptografar o arquivo
    void descriptografarArquivo(String nomeArquivo) {
        try {
            // Carrega a chave secreta
            SecretKey secretKey = loadSecretKey();

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Lê as propriedades criptografadas do arquivo
            Properties encryptedProperties = new Properties();
            try (InputStream input = Files.newInputStream(Paths.get(nomeArquivo))) {
                encryptedProperties.load(input);
            }

            // Descriptografa as propriedades
            String encryptedUser = encryptedProperties.getProperty("user");
            String encryptedPassword = encryptedProperties.getProperty("password");
            String encryptedDburl = encryptedProperties.getProperty("dburl");
            String encryptedUseSSL = encryptedProperties.getProperty("useSSL");

            String user = decrypt(encryptedUser, cipher);
            String password = decrypt(encryptedPassword, cipher);
            String dburl = decrypt(encryptedDburl, cipher);
            String useSSL = decrypt(encryptedUseSSL, cipher);

            // Exibe as informações descriptografadas
            System.out.println("user: " + user);
            System.out.println("password: " + password);
            System.out.println("dburl: " + dburl);
            System.out.println("useSSL: " + useSSL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties obterPropriedadesDescriptografadas(String nomeArquivo) {
        try {
            // Carrega a chave secreta
            SecretKey secretKey = loadSecretKey();

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Lê as propriedades criptografadas do arquivo
            Properties encryptedProperties = new Properties();
            try (InputStream input = Files.newInputStream(Paths.get(nomeArquivo))) {
                encryptedProperties.load(input);
            }

            // Descriptografa as propriedades
            String encryptedUser = encryptedProperties.getProperty("user");
            String encryptedPassword = encryptedProperties.getProperty("password");
            String encryptedDburl = encryptedProperties.getProperty("dburl");
            String encryptedUseSSL = encryptedProperties.getProperty("useSSL");

            String user = decrypt(encryptedUser, cipher);
            String password = decrypt(encryptedPassword, cipher);
            String dburl = decrypt(encryptedDburl, cipher);
            String useSSL = decrypt(encryptedUseSSL, cipher);

            // Cria e retorna as propriedades descriptografadas
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("dburl", dburl);
            properties.setProperty("useSSL", useSSL);

            return properties;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo para criptografar uma string
    private static String encrypt(String input, Cipher cipher) throws Exception {
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(inputBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Metodo para descriptografar uma string
    private static String decrypt(String encryptedInput, Cipher cipher) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /*
     * Medodo Main para testar a classe, e criar novas propriedades criptografadas.
     * Descomentar para testar.
     */

    public static void main(String[] args) {
        // Colocar dentro de um metodo
        CriptografiaUtils criptoUtils = new CriptografiaUtils();
        // Exemplo de uso:
        // criptoUtils.criptografarArquivo("dbLocal.properties");
        Properties props = criptoUtils.obterPropriedadesDescriptografadas("dbLykos.properties.encrypted");
        System.out.println(props);
    }
}