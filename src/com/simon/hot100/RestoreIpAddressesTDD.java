package com.simon.hot100;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/restore-ip-addresses/description/">...</a>
 */
public class RestoreIpAddressesTDD {
    public List<String> restoreIpAddresses(String s) {
        return null;
    }

    static void test(List<String> exp, List<String> act) {
        if (Objects.equals(exp, act)) {
            System.out.println("PASS exp" + exp + " act:" + act);
        } else {
            System.out.println("FAIL exp" + exp + " act:" + act);
        }
    }

    public static void main(String[] args) {
        RestoreIpAddressesTDD s = new RestoreIpAddressesTDD();
        test(List.of("255.255.11.135", "255.255.111.35"),
                s.restoreIpAddresses("25525511135"));
        test(List.of("0.0.0.0"),
                s.restoreIpAddresses("0000"));
        test(List.of("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"),
                s.restoreIpAddresses("101023"));
    }
}
