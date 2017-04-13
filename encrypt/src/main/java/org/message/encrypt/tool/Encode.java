package org.message.encrypt.tool;

import com.google.common.io.BaseEncoding;

public class Encode {

	/**
	 * byte[] convert into string ,use base encode default value BASE64
	 * 
	 * @param encodeMassage
	 *            not null byte[]
	 * @return after encode result
	 */
	public static String baseEncode(final byte[] encodeMassage) {
		return baseEncode(encodeMassage, BaseType.BASE64);
	}

	/**
	 * byte[] convert into string ,use base encode default value BASE64
	 * 
	 * @param encodeMassage
	 *            not null byte[]
	 * @param encoder
	 *            of type please see enum type of inner class , the class name
	 *            'BaseType'
	 * @return after encode result , the type of string
	 */
	public static String baseEncode(final byte[] encodeMassage, final BaseType encoder) {
		String baseResult = null;
		switch (encoder) {
		case BASE64:
			baseResult = BaseEncoding.base64().encode(encodeMassage);
			break;
		case BASE32:
			baseResult = BaseEncoding.base32().encode(encodeMassage);
			break;
		case BASE32HEX:
			baseResult = BaseEncoding.base32Hex().encode(encodeMassage);
			break;
		case BASE16:
			baseResult = BaseEncoding.base16().encode(encodeMassage);
			break;
		default:
			break;
		}
		return baseResult;
	}

	/**
	 * string convert into byte[], use base decode
	 * 
	 * @param decodeMassage
	 *            not null string
	 * @return after decode result , the type of byte[]
	 */
	public static byte[] baseDecode(final String decodeMassage) {
		return baseDecode(decodeMassage, BaseType.BASE64);
	}

	public static byte[] baseDecode(final String decodeMassage, final BaseType encoder) {
		byte[] baseResult = null;
		switch (encoder) {
		case BASE64:
			baseResult = BaseEncoding.base64().decode(decodeMassage);
			break;
		case BASE32:
			baseResult = BaseEncoding.base32().decode(decodeMassage);
			break;
		case BASE32HEX:
			baseResult = BaseEncoding.base32Hex().decode(decodeMassage);
			break;
		case BASE16:
			baseResult = BaseEncoding.base16().decode(decodeMassage);
			break;
		default:
			break;
		}
		return baseResult;
	}

	enum BaseType {
		BASE64, BASE32, BASE32HEX, BASE16;
	}
}
