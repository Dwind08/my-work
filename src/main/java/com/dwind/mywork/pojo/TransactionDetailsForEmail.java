package com.dwind.mywork.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsForEmail {
    private File file;

    private String matchedFlowSize;

    private String unMatchedFlowSize;

    private String busFlag;
}
