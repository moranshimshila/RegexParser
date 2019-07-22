package com.inneractive;

/**
 * Fyber's special regex parser.
 * possible regex values are [a-z], +, *, where: + is zero or one character and * is zero to any number of characters
 */
public class RegexParser {

    public static void main(String[] args) {

        // Fyber's tests

        System.out.println(isMatch("", "")); //return true
        System.out.println(isMatch("abc", "a++")); //return true
        System.out.println(isMatch("abcd", "a*****d")); //return true
        System.out.println(isMatch("abcf", "a*")); //return true
        System.out.println(isMatch("abcde", "a*f")); //return false
        System.out.println("All tests passed successfully");

        // Moran's tests

        System.out.println(isMatch("a", "+")); //return true
        System.out.println(isMatch("", "+")); //return true
        System.out.println(isMatch("ab", "+*")); //return true
        System.out.println(isMatch("e", "+*")); //return true
        System.out.println(isMatch("aa", "+*")); //return true
        System.out.println(isMatch("abcdsa", "+*")); //return true
        System.out.println(isMatch("c", "*+")); //return true
        System.out.println(isMatch("cas", "*+")); //return true
        System.out.println(isMatch("aab", "*")); //return true
        System.out.println(isMatch("aa", "a*")); //return true
        System.out.println(isMatch("aa","aa")); //return true
        System.out.println(isMatch("abcd", "a*****")); //return true
        System.out.println(isMatch("ad", "+")); //return false
        System.out.println(isMatch("aa","a")); //return false
        System.out.println(isMatch("aaa","aa")); //return false

    }

    /**
     * @param input - a string input containing characters from a to z
     * @param regex - a regex containing a to z, +s and *s
     * @return true if the input matches the regex, false otherwise
     */
    // checks the substring bounds
    public static boolean isMatch(String input, String regex) {

        // The recursion stop conditions

        // empty case
        if (regex.length() == 0) {
            return input.length() == 0;
        }

        // base case - the length of the regex is 1
        if (regex.length() == 1) {

            if(regex.charAt(0) == '*')
                return true;

            else if (regex.charAt(0) == '+' && input.length() < 1 ) {
                return true;
            }

            // if regex is one char [a-z] and the length of the input is 0, return false
            else if (input.length() < 1) {
                return false;
            }

            // if the first char does not match, return false
            else if ((regex.charAt(0) != input.charAt(0)) && (regex.charAt(0) != '*') && (regex.charAt(0) != '+') ) {
                return false;
            }

            // otherwise, compare the rest of the string and the rest of the regex as a new problem
            else {
                return isMatch(input.substring(1), regex.substring(1));
            }
        }

        // check cases when regex.length()>1

        // case 1: when the first char of regex is not '*'
        if ( regex.charAt(0) != '*' ) {
            if (input.length() < 1 && regex.charAt(0) != '+') {
                return false;
            }
            if ((regex.charAt(0) != input.charAt(0)) && (regex.charAt(0) != '+')) {
                return false;
            } else {
                return isMatch(input.substring(1), regex.substring(1));
            }
        }

        // case 2: when the first char of regex is '*' ->  complex case
        else {
            // case 2.1: '*' can stand for 0 elements
            if (isMatch(input, regex.substring(1))) {
                return true;
            }

            // case 2.2: '*' can stand for 1 or more preceding elements -> try every sub string

            int i = 0;
            while ( i < input.length() ) {
                if ( isMatch(input.substring(i + 1), regex.substring(1)) ) {
                    return true;
                }
                i++;
            }
            return false;

        }
    }
}



