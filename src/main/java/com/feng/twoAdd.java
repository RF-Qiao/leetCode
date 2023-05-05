package com.feng;


import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 规范：
 * 先题号  在标签  题目 难易程度
 * 例子
 * 思路
 */
public class twoAdd {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
    }

    /**
     * 1. 数组 两数之和 简单
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 暴力求解
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 9. 字符串 回文数 简单
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 123  321
     */
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        StringBuilder builder = new StringBuilder(s);
        StringBuilder builder1 = builder.reverse();
        String s1 = new String(builder1);
        if (s.equals(s1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 13. 字符串 罗马转整数 简单
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 先建立一个HashMap将I至M七个罗马符号及其对应的数字值存储下来，然后将字符串转换为int整数数组。初始化一个sum，
     * 从左向右遍历到倒数第二位，如果a[i]大于a[i+1]，则加上a[i]，否则减去a[i]。注意最后加上最后一位的a[s.length()-1]
     * 输入: s = "III"
     * 输出: 3
     */
    public int romanToInt(String s) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("I", 1);
        hashMap.put("V", 5);
        hashMap.put("X", 10);
        hashMap.put("L", 50);
        hashMap.put("C", 100);
        hashMap.put("D", 500);
        hashMap.put("M", 1000);
        int[] nums = new int[100];
        for (int i = 0; i < s.length(); i++) {
            nums[i] = hashMap.get(s.substring(i, i + 1));
        }
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                sum = sum - nums[i];
            } else {
                sum = sum + nums[i];
            }
        }
        sum += nums[s.length() - 1];
        return sum;
    }

    /**
     * 14.字符串 最长公共前缀  简单
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null && strs.length == 0) {
            return "";
        }
        //第一个数组的长度
        int lengthFirst = strs[0].length();
        int length = strs.length;
        for (int i = 0; i < lengthFirst; i++) {
            char charAt = strs[0].charAt(i);
            for (int j = 0; j < length; j++) {
                if (i == strs[j].length() || charAt != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 20. 栈 有效的括号 简单
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * "{[]}"
     * "{[]}"
     * 3 4 8 6
     * "()"
     *
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else if (stack.size() == 0) return false;
            else if (c == ')' && stack.peek() != '(') return false;
            else if (c == '}' && stack.peek() != '{') return false;
            else if (c == ']' && stack.peek() != '[') return false;
            else stack.pop();
        }
        return stack.empty();
    }

    /**
     * 21. 链表 合并两个有序链表 简单
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val<l2.val){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    /**
     * 27.数组 移除元素 简单
     *输入：nums = [3,2,2,3], val = 3
     *输出：2, nums = [2,2]
     * 思路：  遍历  把所非val的值过滤掉。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int k=0;
        for (int i : nums){
            if (i!=val){
                nums[k++]=i;
            }
        }
        return k;
    }

    /**
     * 35. 数组 二分查找 搜索插入位置 简单
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 思路： 遍历数组，如果改数组的值大于或等于目标值就返回改数组下表，如果都没有 则 返回该数组的长度。
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i <= nums.length-1; i++) {
            if (nums[i]>=target){
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 剑指offer63. 数组 股票的最大利润 中等
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min =Integer.MAX_VALUE;
        int res =0;
        for (int i = 0;i<prices.length;i++){
           min= Math.min(min,prices[i]);
           res=Math.max(prices[i]-min,res);
        }
        return res;
}

    /**
     * 66.数组 加1 简单
     *  给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * @param digits
     * @return
     * 思路：  判断是否为9  如果不为9加1 返回 如果为9 变为0   然后前一位加1
     */
    public int[] plusOne(int[] digits) {
        for (int i =digits.length-1;i>=0;i--){
            if (digits[i]!=9){
                digits[i]++;
                return digits;
            }
            digits[i]=0;
        }

        digits= new int[digits.length + 1];
        digits[0]=1;
        return digits;
    }

    /**
     * 136.数组 只出现一次的数字 简单
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     *
     * 输入：nums = [2,2,1]
     * 输出：1
     *思路：创建一个集合，遍历数组，集合中没有遍历的元素就直接添加，如果有就把集合中元素删除 代码
     */
    public int singleNumber(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i =0 ; i<=nums.length-1; i++){
            if (!list.contains(nums[i])){
                list.add(nums[i]);
            }else {
                list.remove((Integer)nums[i]);
            }
        }
        return list.get(0);
    }

    /**
     *217.数组 存在重复数组 简单
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Object> set = new HashSet<>();
        for (int i : nums){
            set.add(i);
        }
        return set.size()<nums.length?true:false;
    }




}

