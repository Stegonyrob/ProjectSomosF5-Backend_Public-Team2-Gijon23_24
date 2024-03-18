package org.teamraccoon.dreamfusion.facades.encryptations;

import java.util.Base64;

import org.teamraccoon.dreamfusion.generic.IEncoder;

public class Base64Encoder implements IEncoder{

    @Override
    public String encode(String data) {
        String dataEncoded = Base64.getEncoder().encodeToString(data.getBytes());
        return dataEncoded;
    }

    public String decode(String data) {
        byte[] decodeBytes = Base64.getDecoder().decode(data);
        String dataDecoded = new String(decodeBytes);
        return dataDecoded;
    }

}
