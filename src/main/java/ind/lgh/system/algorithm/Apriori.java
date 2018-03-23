package ind.lgh.system.algorithm;

import java.io.*;
import java.util.*;

/**
 * Apriori算法.
 * 一种挖掘关联规则（Association rule mining）的频繁项集算法，数据挖掘十大算法之一。
 * 关联规则：
 * 1.是形如 X→Y 的蕴涵表达式，其中X和Y是不相交的项集，即 X∩Y=∅
 * 2.强度可以用它的支持度和置信度衡量
 * 3.支持度确定规则可以用于给定数据集的频繁程度（s：Fraction of transactions that contain both X and Y）
 * 4.置信度确定Y在包含X的交易中出现的频繁程度（c：How often items in Y appear in transactions that contain X）
 * 算法步骤：
 * 1.频繁项集产生：其目标是发现满足最小支持度阈值的所有项集，这些项集称作频繁项集
 * 2.规则的产生：其目标是从上一步发现的频繁项集中提取所有高置信度的规则，这些规则称作强规则
 * 注：通常，频繁项集产生所需的计算开销远大于产生规则所需的计算开销
 * 最简单最直接，暴力搜索（Brute-force）的方法：
 * 1.List all possible association rules
 * 2.Compute the support and confidence for each rule
 * 3.Prune rules that fail the minsup and minconf thresholds
 * 计算量爆炸体现在：
 * 1.一个包含k个项的数据集可能产生2的k次方−1个频繁项集，指数规模的搜索
 * 2.将每个候选项集与每个交易进行比较，如果候选项集包含在交易中，则候选项集的支持度计数增加
 * 比较的开销巨大，需要进行O(NMw)次比较，其中N是交易数，M=2的k次方−1是候选项集数，而w是交易的最大宽度（也就是交易中最大的项数）
 * Apriori算法，使用先验性质来压缩搜索空间：
 * Apriori定律1：如果一个集合是频繁项集，则它的所有子集都是频繁项集。
 * Apriori定律2：如果一个集合不是频繁项集，则它的所有超集都不是频繁项集。
 * Apriori定律3：如果规则X⟶Y−X不满足置信度阈值，则对于X的子集X′，规则X′⟶Y−X′也不满足置信度阈值。
 * 先验原理可以降低产生频繁项集的计算复杂度，这个操作也叫利用支持度对候选项集进行剪枝
 * <p>
 * 【算法】：Apriori
 * 【输入】：1.Ck:候选数据集（Candidate itemsets of size k） 2.min_sup:最小支持度阈值
 * 【输出】：Lk:频繁k项集（frequent itemsets of size k）
 * 【实现】：
 * --L1 = {frequent 1-itemsets};
 * --for(k=1; Lk!=∅; K++)
 * --  Ck+1 = GenerateCandidates(Lk)
 * --  for each transaction t in database do
 * --    increment count of candidates in Ck+1 that are contained in t
 * --  endfor
 * --  Lk+1 = candidates in Ck+1 with support >= min_sup
 * --endfor
 * --return UkLk;
 * 【过程】：数据集-create->C1-支持度过滤->L1-拼接->C2-支持度过滤->L2-拼接->C3-...->Lk
 * 【注意点】：为了有效实现，算法假定事务或项集中的项按照字典序排列（所以自定义项集数据结构实现comparable接口）
 * <p>
 * 项集：包含0个或多个项的集合，包含k个项的集合被称为k项集
 *
 * @author lgh
 */
public class Apriori {

    /**
     * 支持度阈值
     * 支持度：support(A=>B)=num(A∪B)/W = P(A∩B)
     * 设W中有s％的事务同时支持物品集A和B，s％称为{A，B}的支持度
     * num(A∪B)表示含有物品集{A,B}的事务集的个数，不是数学中的并集
     */
    private static final int MIN_SUPPORT = 10;

    /**
     * 置信度阈值
     * 置信度：confidence(A=>B)=P(B|A)
     * 置信度可理解为条件概率p(Y|X)，度量在已知事务中包含了X时包含Y的概率
     * 物品A->B的置信度=物品{A,B}的支持度 / 物品{A}的支持度
     */
    private static final double MIN_CONFIDENCE = 0.7;

    /**
     * 项之间的分隔符
     */
    private static final String ITEM_SPLIT = "_";

    /**
     * 关联规则表示符号
     */
    private static final String ASSOCIATION_RULE_ARROW = "->";

    private static final int INTEGER_2 = 2;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        String rootPath = System.getProperty("user.dir");

        List<String> dataList = readDataFile(rootPath + "\\src\\main\\java\\ind\\lgh\\system\\algorithm\\data\\apriori_demo.csv");

        List<String> excludeList = new ArrayList<>();
        Collections.addAll(excludeList, new String[]{null, ""});
        System.out.println("======数据集合=========");
        for (String string : dataList) {
            System.out.println(string);
        }

        System.out.println("======频繁项集=========");
        Map<String, Integer> frequentItemset = Apriori.apriori(dataList);
        Set<String> keySet = frequentItemset.keySet();
        for (String key : keySet) {
            System.out.println(key + ":" + frequentItemset.get(key));
        }

        System.out.println("======关联规则=========");
        Map<String, Double> relationRules = Apriori.aprioriGenerateRules(frequentItemset);
        Set<String> ruleSet = relationRules.keySet();
        for (String rule : ruleSet) {
            System.out.println(rule + ":" + relationRules.get(rule));
        }

        long endTime = System.currentTimeMillis();

        System.out.println("共计耗时：" + (endTime - startTime) / 1000);

    }

    /**
     * 读取数据文件，转换为可处理的格式.
     *
     * @param filePath 文件路径
     * @return 原始数据集
     */
    private static List<String> readDataFile(String filePath) {
        File file = new File(filePath);
        List<String> dataList = new ArrayList<>();
        List<String> excludeList = new ArrayList<>();
        Collections.addAll(excludeList, new String[]{null, ""});
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            List<String> list = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                Collections.addAll(list, str.split(ITEM_SPLIT));
                // 数据清洗
                list.removeAll(excludeList);
                // 排序 - 字典序
                list.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                dataList.add(String.join(ITEM_SPLIT, list));
                list.clear();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Apriori算法主程序.
     * 算法第一步：频繁项集产生.
     * 其目标是发现满足最小支持度阈值的所有项集，这些项集称作频繁项集
     * 通常，频繁项集产生所需的计算开销远大于产生规则所需的计算开销
     * 剪枝将发生在这一步，这也是算法优化的重点
     *
     * @param sortedDataList 原始数据集（必须按照字典序排序）
     * @return 关联规则
     */
    public static Map<String, Integer> apriori(List<String> sortedDataList) {
        // 频繁项集
        Map<String, Integer> frequentItemset = new HashMap<>();
        // 产生频繁1项集，1.作为k+1项集拼接依据；2.加入到频繁项集中
        Map<String, Integer> itemset = new HashMap<>();
        itemset.putAll(generateFrequentOneItemset(sortedDataList));
        frequentItemset.putAll(itemset);
        // 以itemset作为循环核心，每次由频繁k-1项集生成频繁k项集，都写入到itemset
        int ii = 0;
        while (itemset != null && itemset.size() > 0) {
            ii++;
            System.out.println("递归次数: " + ii + ", sortedDataList大小：" + sortedDataList.size());
            // 生成候选集，并做支持度计数
            Map<String, Integer> candidateItemset = generateFrequentItemset(itemset);
            Set<String> candidateKeySet = candidateItemset.keySet();
            System.out.println("候选集生成完毕");
            // 遍历原数据集
            for (String data : sortedDataList) {
                List<String> dataItems = Arrays.asList(data.split(ITEM_SPLIT));
                // 遍历候选k项集，检查每个k项集
                for (String candidate : candidateKeySet) {
                    boolean flag = true;
                    // 检查每个k项集的每个项，如果存在原数据记录是该k项集的超集，则对该k项集支持度计数加1
                    List<String> candidateItems = Arrays.asList(candidate.split(ITEM_SPLIT));
                    for (String str : candidateItems) {
//                        if (data.indexOf(str) == -1) {
//                            flag = false;
//                            break;
//                        }
                        if (!dataItems.contains(str)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        candidateItemset.put(candidate, candidateItemset.get(candidate) + 1);
                    }
                }
            }
            System.out.println("已经生成候选集，并做支持度计数 ");
            // 从候选集中找到满足最小支持度阈值的频繁项集
            itemset.clear();
            for (String candidate : candidateKeySet) {
                Integer count = candidateItemset.get(candidate);
                if (count >= MIN_SUPPORT) {
                    itemset.put(candidate, count);
                }
            }
            // 合并到所有频繁项集
            frequentItemset.putAll(itemset);
        }
        return frequentItemset;
    }

    /**
     * 算法第二步：规则的产生.
     * 其目标是从上一步发现的频繁项集中提取所有高置信度的规则，这些规则称作强规则
     * 关联规则是由频繁项集生成的，即对于Fk，找出项集hm，使得规则fk−hm⟶hm的置信度大于置信度阈值
     *
     * @param frequentItemset 频繁项集
     * @return 关联规则
     */
    public static Map<String, Double> aprioriGenerateRules(Map<String, Integer> frequentItemset) {
        Map<String, Double> relations = new HashMap<>();
        Set<String> keySet = frequentItemset.keySet();
        // 遍历频繁项集
        for (String key : keySet) {
            List<String> subset = subset(key);
            // 对每个频繁项集求取其非空真子集，遍历
            for (String item : subset) {
                // 如果该频繁项集的非空真子集，也是频繁项集
                Integer count = frequentItemset.get(item);
                if (count != null) {
                    // 置信度CONF = 频繁项集S1的支持数SUP1 / S1的频繁真子集S2的支持数SUP2
                    // 刻画的是 S2->(S1-S2)的置信度 = SUP(S1)/SUP(S2)
                    Double confidence = (1.0 * frequentItemset.get(key)) / (1.0 * frequentItemset.get(item));
                    if (confidence >= MIN_CONFIDENCE) {
                        relations.put(item + ASSOCIATION_RULE_ARROW + expect(key, item), confidence);
                    }
                }
            }
        }
        return relations;
    }

    /**
     * 使用原始数据集产生频繁1项集.
     *
     * @param dataList 原始数据集
     * @return 频繁1项集
     */
    private static Map<String, Integer> generateFrequentOneItemset(List<String> dataList) {
        Map<String, Integer> itemset = new HashMap<>();
        for (String data : dataList) {
            List<String> list = Arrays.asList(data.split(ITEM_SPLIT));
            for (String str : list) {
                str += ITEM_SPLIT;
                if (itemset.containsKey(str)) {
                    itemset.put(str, itemset.get(str) + 1);
                } else {
                    itemset.put(str, 1);
                }
            }
        }
        Map<String, Integer> itemset2 = new HashMap<>();
        Set<String> keySet = itemset.keySet();
        for (String key : keySet) {
            if (itemset.get(key) >= MIN_SUPPORT) {
                itemset2.put(key, itemset.get(key));
            }
        }
        System.out.println("频繁一项集生成完毕！共计" + itemset2.size());
        return itemset2;
    }

    /**
     * 连接k-1项集，生成频繁k项集.
     *
     * @param itemset 频繁k-1项集
     * @return 频繁k项集
     */
    private static Map<String, Integer> generateFrequentItemset(Map<String, Integer> itemset) {
        Map<String, Integer> candidateItemset = new HashMap<>();
        Set<String> candidateKeySet = itemset.keySet();

        for (String s1 : candidateKeySet) {
            String[] arr1 = s1.split(ITEM_SPLIT);
            for (String s2 : candidateKeySet) {
                String[] arr2 = s2.split(ITEM_SPLIT);
                // 是否可连接
                boolean flag = true;
                // 如果k-1项集的前k-2项相同，则它是可连接的
                for (int i = 0; i < arr1.length - 1; i++) {
                    if (arr1[i].compareTo(arr2[i]) != 0) {
                        flag = false;
                        break;
                    }
                }
                // 可连接 && 保证将要产生的k项集不重复
                if (flag && arr1[arr1.length - 1].compareTo(arr2[arr1.length - 1]) < 0) {
                    // 连接步：产生候选k项集
                    String candidate = s1 + arr2[arr2.length - 1] + ITEM_SPLIT;
                    if (hasInfrequentSubset(candidate, itemset)) {
                        // 剪枝步：删除非频繁的候选（不记录到集合）
                    } else {
                        candidateItemset.put(candidate, 0);
                    }
                }
            }
        }
        return candidateItemset;
    }

    /**
     * 使用先验知识，判断候选集是否是频繁项集：
     * 如果一个集合是频繁项集，则它的所有子集都是频繁项集
     *
     * @param candidate
     * @param itemset
     * @return
     */
    private static boolean hasInfrequentSubset(String candidate, Map<String, Integer> itemset) {
        String[] arr = candidate.split(ITEM_SPLIT);
        // 判断每个子集是否是频繁子集 - 遍历每次抠出一个元素，生成长度为k-1的子集（因为从1项集递归，所以每次只需判断k项集的k-1项集子集即可）
        for (int i = 0; i < arr.length; i++) {
            String subset = "";
            for (int j = 0; j < arr.length; j++) {
                if (j != i) {
                    subset += arr[j] + ITEM_SPLIT;
                }
            }
            if (itemset.get(subset) == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 求一个集合的所有非空真子集 - 非递归实现.
     * 实现思路：
     * 假设集合S(A,B,C,D)，其大小为4，那么它拥有2的4次方个子集，
     * 即0-15，二进制表示为0000,0001,0011,...,1111
     * 对应的子集为：{},{D},{C,D},...,{A,B,C,D}
     *
     * @param set 所求集合
     * @return 所有非空真子集列表 - 每个子集被stringify
     */
    private static List<String> subset(String set) {
        List<String> result = new ArrayList<>();
        String[] arr = set.split(ITEM_SPLIT);

        for (int i = 1; i < (int) Math.pow(INTEGER_2, arr.length) - 1; i++) {
            StringBuffer item = new StringBuffer("");
            StringBuffer placeholder = new StringBuffer("");
            int num = i;
            // 将num转化为二进制倒序表示，并记录到placeholder
            do {
                placeholder.append(num % 2);
                num = num / 2;
            } while (num > 0);
            // 倒序遍历，如：(i=3) = 0011 -> 1100(placeholder)
            for (int j = placeholder.length() - 1; j >= 0; j--) {
                if (("1").equals(placeholder.charAt(j) + "")) {
                    // 相当于prepend，保证子集的排序继承自原集合
                    item.insert(0, arr[j] + ITEM_SPLIT);
                }
            }
            result.add(item.toString());
        }
        return result;
    }

    /**
     * 集合运算 - A-B.
     * 从集合A中去除集合B中含有的元素
     *
     * @param stringA 集合A
     * @param stringB 集合B
     * @return
     */
    private static String expect(String stringA, String stringB) {
        String result = "";
        String[] arrA = stringA.split(ITEM_SPLIT);
        String[] arrB = stringB.split(ITEM_SPLIT);

        for (int i = 0; i < arrA.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arrB.length; j++) {
                if (arrA[i].compareTo(arrB[j]) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result += arrA[i] + ITEM_SPLIT;
            }
        }
        return result;
    }

}
