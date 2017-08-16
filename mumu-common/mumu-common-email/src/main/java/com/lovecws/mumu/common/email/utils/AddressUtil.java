package com.lovecws.mumu.common.email.utils;

import javax.mail.Address;

public class AddressUtil {

	/**
	 * 地址数据转化为字符串数组
	 * @param addresss
	 * @return
	 */
	public static String[] addressToString(Address[] addresss) {
		if (addresss == null || addresss.length == 0) {
			return null;
		}

		String[] adds = new String[addresss.length];

		for (int i = 0; i < addresss.length; i++) {
			adds[i] = addresss[i].toString();
		}
		return adds;
	}
}
