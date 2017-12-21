package ind.lgh.system.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 提供汉字转拼音等方法
 *
 * @author lgh
 * @since 2017-12-21
 */
public class PinyinUtil {

    public static void main(String[] args) {
        System.out.println(getFirstSpell("汉语拼音"));
        System.out.println(getFullSpell("汉语拼音"));
        System.out.println(getFullSpellWithMark("汉语拼音"));
    }

    /**
     * 获得汉字的首字母
     *
     * @param chinese
     * @return
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer sb = new StringBuffer();
        char[] charArr = chinese.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (c > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获得汉字全拼
     *
     * @param chinese
     * @return
     */
    public static String getFullSpell(String chinese) {
        StringBuffer sb = new StringBuffer();
        char[] charArr = chinese.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (c > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获得汉字全拼
     *
     * @param chinese
     * @return
     */
    public static String getFullSpellWithMark(String chinese) {
        StringBuffer sb = new StringBuffer();
        char[] charArr = chinese.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (c > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
