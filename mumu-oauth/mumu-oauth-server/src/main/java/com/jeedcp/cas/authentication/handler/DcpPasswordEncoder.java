package com.jeedcp.cas.authentication.handler;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import javax.validation.constraints.NotNull;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * 自定义密码编码器，hash方式，支持sha1、md5
 * @author Jeedcp
 *
 */
public class DcpPasswordEncoder implements PasswordEncoder{

    @NotNull
    private final String encodingAlgorithm;
    /**hash层数**/
    private final Integer hashInterations;

    private String characterEncoding;

    public DcpPasswordEncoder(final String encodingAlgorithm,final Integer hashInterations) {
        this.encodingAlgorithm = encodingAlgorithm;
        this.hashInterations=hashInterations;
    }

    public String encode(final String input) {
        String plain = StringEscapeUtils.unescapeHtml(input);
        return new String(Hex.encodeHex(digest(plain, this.encodingAlgorithm, this.hashInterations)));
    }

    public void setCharacterEncoding(final String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    private byte[] digest(String input, String algorithm, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(Hex.decodeHex(input.substring(0,16).toCharArray()));
            if (StringUtils.hasText(this.characterEncoding)) {
                digest.update(input.substring(16).getBytes(this.characterEncoding));
            } else {
                digest.update(input.substring(16).getBytes());
            }

            byte[] result = digest.digest();

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException();
        } catch (DecoderException e) {
            throw new RuntimeException();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }
}
