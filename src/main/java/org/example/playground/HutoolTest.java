package org.example.playground;

import org.dromara.hutool.core.data.IdcardUtil;
import org.dromara.hutool.core.data.MaskingUtil;

public class HutoolTest {

    public static void main(String[] args) {
        System.out.println(MaskingUtil.mobilePhone("13589642210"));
        System.out.println(MaskingUtil.idCardNum("140214200009093140", 4, 4));
        System.out.println(MaskingUtil.chineseName("李三"));
        System.out.println(MaskingUtil.chineseName("张北海"));
        System.out.println(IdcardUtil.isValidCard18("140214200009093140"));

    }
}
