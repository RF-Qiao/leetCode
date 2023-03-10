package com.feng;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 两数相加
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
     * 两数之和
     * 1  2   3
     *
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
     * 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
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
     * 罗马转整数
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
     * “II”
     * I I
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
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
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
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
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
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode(0);
        ListNode listNodeMove = listNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                listNodeMove.next = list1;
                listNodeMove = listNodeMove.next;
                list1 = list1.next;
            } else {
                listNodeMove.next = list2;
                listNodeMove = listNodeMove.next;
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            listNodeMove.next = list2;
        } else {
            listNodeMove.next = list1;
        }
        return listNode.next;
    }

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
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int a = digits.length;
        for (int i=a-1;i>0;i--){
            digits[i]++;
            digits[i]%=10;
            if (digits[i]!=0)
                return digits;
            }
            digits= new int[a+1];
            digits[0]=1;
            return digits;
        }
    }

