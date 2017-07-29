package com.spw.elife.util;


/**
 * #############################################################################
 * # DESCRIBE 将汉字转化成拼音 # DATE 2006-7-12 # COMPANY FLX # PORJECT JAVA
 * #############################################################################
 */

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.http.HttpStatus;

import com.spw.elife.common.exception.CommonRuntimeException;

/**
 * 将汉字转化成拼音.
 *
 * @author Administrator
 */
public class CnToSpell {

    /**
     * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
     *
     * @param cnStr String 字符串
     * @return String 转换成全拼后的字符串
     */
    public static String getFullSpell(String cnStr) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        char[] input = cnStr.trim().toCharArray();
        StringBuilder output = new StringBuilder("");
        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output.append(temp[0]);
                } else if (Character.toString(input[i]).matches("[^\\x00-\\x80]")) {
                    output.append("");
                } else {
                    output.append(Character.toString(input[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new CommonRuntimeException(HttpStatus.NOT_ACCEPTABLE, "汉字转拼音出错", e);
        }
        return output.toString();
    }
}
