package com.suber.services.impl

class Test {


//    static def letterMap = ['A':1, 'B':3, 'C':3, 'D':2, 'E':1, 'F':4, 'G':2, 'H':4, 'I':1, 'J':8, 'K':5, 'L':1, 'M':3, 'N':1, 'O':1, 'P':3, 'Q':10, 'R':1, 'S':1, 'T':1, 'U':1, 'V':4,'W':4, 'Y':4, 'Z':10, 'X':8]
        static def letterMap = ["A":1, "B":3, "C":3, "D":2, "E":1, "F":4, "G":2, "H":4, "I":1, "J":8, "K":5, "L":1, "M":3, "N":1, "O":1, "P":3, "Q":10, "R":1, "S":1, "T":1, "U":1, "V":4,"W":4, "Y":4, "Z":10, "X":8]
        //ArrayList list1 = new ArrayList() [1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 4, 10, 8];

        static def int scoreWord(String word) {

            int counter = 0

            if (word == "")
                return counter

            String wordUppercase = word.toUpperCase()
            for (int i=0; i < word.length(); i++) {
                def key = wordUppercase.charAt(i)
                counter = counter + letterMap.get("" + key)
            }



            return counter
            //throw new UnsupportedOperationException('scoreWord method not implemented.')
        }



    static void main(String[] args) {

        println Test.scoreWord('a')
        println Test.scoreWord('c')
        println Test.scoreWord('ac')
    }

}
