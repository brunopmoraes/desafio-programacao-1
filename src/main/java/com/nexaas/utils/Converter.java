package com.nexaas.utils;

import com.nexaas.domain.File;

import java.io.IOException;
import java.math.BigDecimal;

public class Converter {

    public static File stringToDTO(String line) throws IOException{
        String[] datas = line.split("\t");
        try {
            return new File(
                    datas[0],
                    datas[1],
                    new BigDecimal(datas[2]),
                    Long.valueOf(datas[3]),
                    datas[4],
                    datas[5]

            );
        }catch (ArrayIndexOutOfBoundsException ex){
            throw new IOException(ex);
        }
    }
}
