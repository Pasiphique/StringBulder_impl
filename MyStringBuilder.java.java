
import java.util.Arrays;

public class MyStringBuilder {

    private CNode firstC;	// reference to front of list.  This reference is necessary
    // to keep track of the list
    private int length;  	// number of characters in the list

    public MyStringBuilder(String s) {
        if (s == null || s.length() == 0) // special case for empty String
        {
            firstC = null;
            length = 0;
        } else {
            firstC = new CNode(s.charAt(0));  // create first node
            length = 1;
            CNode currNode = firstC;
            // Iterate through remainder of the String, creating a new
            // node at the end of the list for each character.  Note
            // how the nodes are being linked and the current reference
            // being moved down the list.
            for (int i = 1; i < s.length(); i++) {
                CNode newNode = new CNode(s.charAt(i));
                currNode.next = newNode;
                newNode.prev = currNode;
                currNode = newNode;
                length++;
            }
            // Connect end back to front to make list circular
            firstC = firstC;
            firstC.prev = currNode;
            currNode.next = firstC;
        }
    }

    // Create a new MyStringBuilder initialized with the chars in array s
    public MyStringBuilder(char[] s) {
        firstC = new CNode(s[0]);  // create first node          
        length = 1;
        CNode currNode = firstC;
        if (s == null || s.length == 0) // special case for empty String
        {
            firstC = null;
            length = 0;
        } else {

            // Iterate through remainder of the String, creating a new
            // node at the end of the list for each character.  Note
            // how the nodes are being linked and the current reference
            // being moved down the list.
            for (int i = 1; i < s.length; i++) {
                CNode newNode = new CNode(s[i]);
                currNode.next = newNode;
                newNode.prev = currNode;
                currNode = newNode;
                length++;
            }
            // Connect end back to front to make list circular

            firstC.prev = currNode;
            currNode.next = firstC;
        }

    }

// Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
// that you make new nodes for the copy.
    public MyStringBuilder(MyStringBuilder old) {

        if (old.length == 0) {
            firstC = null;
            length = 0;
        } else {

            CNode currentNode = firstC;
            CNode currentOldNode = old.firstC;
            currentNode = new CNode(currentOldNode.data);
            length++;
            while (currentOldNode.next != old.firstC) {
                length++;
                CNode newNode = new CNode(currentOldNode.next.data);
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentOldNode = currentOldNode.next;
                currentNode = newNode;
            }
            firstC = old.firstC;
            currentNode.next = firstC;
            firstC.prev = currentNode;
        }

    }

    public MyStringBuilder() {
        firstC = null;
        length = 0;
    }

    public String toString() {
        char[] c = new char[length];
        int i = 0;
        CNode currNode = firstC;
        while (i < length) {
            c[i] = currNode.data;
            i++;
            currNode = currNode.next;
        }
        return new String(c);
    }

    // Append MyStringBuilder b to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(MyStringBuilder b) {
        CNode currentNode = firstC.prev;
        if (length == 0) {
            firstC = null;
            length = 0;
        } else {
            for (int i = 0; i < b.length; i++) {
                CNode newNode = new CNode(b.charAt(i));
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentNode = newNode;
                length++;
            }
            firstC = firstC;
            currentNode.next = firstC;
            firstC.prev = currentNode;

        }
        return this;
    }

    // Append String s to the end of the current MyStringBuilder, and return
    // the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(String s) {
        CNode currentNode = null;
        if (length == 0) {
            firstC = new CNode(s.charAt(0));
            length = 1;
            currentNode = firstC;
        } else {
            currentNode = firstC;
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            CNode newNode = new CNode(s.charAt(i));
            currentNode.next = newNode;
            newNode.prev = currentNode;
            currentNode = newNode;
            length++;
        }

        currentNode.next = firstC;
        firstC.prev = currentNode;

        return this;

    }

    // Append char array c to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(char[] c) {
        CNode currentNode = firstC.prev;
        if (length == 0) {
            firstC = null;
            length = 0;
        } else {
            for (int i = 0; i < c.length; i++) {
                CNode newNode = new CNode(c[i]);
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentNode = newNode;
                length++;
            }
        }

        currentNode.next = firstC;
        firstC.prev = currentNode;
        return this;

    }

    // Append char c to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(char c) {
        CNode currentNode = firstC.prev;
        CNode sNode = new CNode(c);
        if (length == 0) {
            firstC = null;
            length = 0;
        } else {
            CNode newNode = sNode; // adding first element in old stringbuilder
            currentNode.next = newNode;
            newNode.prev = currentNode;
            currentNode = newNode;
            length++;
        }

        currentNode.next = firstC;
        firstC.prev = currentNode;
        return this;
    }

    // Return the character at location "index" in the current MyStringBuilder.
    // If index is invalid, throw an IndexOutOfBoundsException.
    public char charAt(int index) {

        CNode currentNode = firstC;
        int counter = 0;
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException(" ");
        }
        if (counter == index) {
            return currentNode.data;
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
                counter++;
                if (counter == index) {
                    return currentNode.data;
                }
            }
        }
        return 0;
    }

    // Delete the characters from index "start" to index "end" - 1 in the
    // current MyStringBuilder, and return the current MyStringBuilder.
    // If "start" is invalid or "end" <= "start" do nothing (just return the
    // MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
    // only remove up until the end of the MyStringBuilder. Be careful for 
    // special cases!
    public MyStringBuilder delete(int start, int end) {
        CNode currentNode = firstC;
        int counter = 0;
        if (start < 0 || start > length || end < start) {
            firstC = currentNode;
            firstC.prev = new CNode(firstC.prev.data);
            return this;
        } else if (start == 0) {
            while (counter == start && start < end) {
                firstC = firstC.next;
                currentNode = firstC;
                start++;
                counter++;
                length--;
            }
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
            }

            firstC.prev = currentNode;
            currentNode.next = firstC;
            return this;
        } else if (end > length) {
            length = start;
            counter++;
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
                if (counter >= length) {
                    break;
                }
            }

            firstC.prev = currentNode;
            currentNode.next = firstC;
            return this;

        } else if (end == length) {
            currentNode = firstC.prev;
            currentNode = currentNode.prev;
            length--;
        } else {
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
                while (counter == start && counter < end) {
                    CNode nodeBefore = currentNode.prev;
                    CNode nodeAfter = currentNode.next;
                    nodeBefore.next = nodeAfter;
                    nodeAfter.prev = nodeBefore;
                    start++;
                    currentNode = nodeAfter;
                    length--;
                    counter++;
                }
            }

        }

        currentNode.next = firstC;
        firstC.prev = currentNode;
        return this;

    }

    // Delete the character at location "index" from the current
    // MyStringBuilder and return the current MyStringBuilder.  If "index" is
    // invalid, do nothing (just return the MyStringBuilder as is).  If "index"
    // is the last character in the MyStringBuilder, go backward in the list in
    // order to make this operation faster (since the last character is simply
    // the previous of the first character)
    // Be careful for special cases!
    public MyStringBuilder deleteCharAt(int index) {

        if (index > length || index < 0) {
            return this;

        }
        int counter = 0;
        CNode currentNode = firstC;
        if (index == counter) {
            firstC = firstC.next;
            currentNode = firstC;
            length--;
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;

            }

            firstC.prev = currentNode;
            currentNode.next = firstC;
            return this;
        } else if (index == length - 1) {
            CNode tempNode = firstC.prev;
            CNode tempNode2 = tempNode.prev;
            length--;
            firstC.prev = tempNode2;

            return this;

        } else if (index > length) {
            firstC = firstC;

            firstC.prev = currentNode;
            currentNode.next = firstC;

            return this;

        } else {
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
                if (counter == index) {
                    CNode nodeBefore = currentNode.prev;
                    CNode nodeAfter = currentNode.next;
                    nodeBefore.next = nodeAfter;
                    nodeAfter.prev = nodeBefore;
                    currentNode = nodeAfter;
                    length--;
                    counter++;
                }
            }

        }

        firstC.prev = currentNode;
        currentNode.next = firstC;

        return this;
    }
    // Find and return the index within the current MyStringBuilder where
    // String str first matches a sequence of characters within the current
    // MyStringBuilder.  If str does not match any sequence of characters
    // within the current MyStringBuilder, return -1.  Think carefully about
    // what you need to do for this method before implementing it.

    public int indexOf(String str) {

        int counterForNode = 0; // keeps track of currentNode pointer
        int matches = 0; // track of how many times stringbuilder data matches String str data
        CNode currentNode = firstC;

        char firstIndex = str.charAt(0);
        if (currentNode.prev.data == firstIndex) {
            counterForNode = length - 1;
            return counterForNode;
        }
        while (currentNode.next != firstC || matches == str.length()) {

            CNode copyNode = currentNode;
            if (copyNode.data == firstIndex) {
                matches++;
                if (str.length() == 1) {
                    return counterForNode;
                }
                for (int i = 1; i < str.length(); i++) {
                    copyNode = copyNode.next;
                    if (copyNode.data == str.charAt(i)) {
                        matches++;

                    }
                }
            }

            if (matches == str.length()) {
                return counterForNode;
            } else {
                matches = 0;
                counterForNode++;

            }
            currentNode = currentNode.next;
        }
        return -1;

    }
    // Insert String str into the current MyStringBuilder starting at index
    // "offset" and return the current MyStringBuilder.  if "offset" == 
    // length, this is the same as append.  If "offset" is invalid
    // do nothing.

    public MyStringBuilder insert(int offset, String str) {

        int counter = 0;
        if (length == 0) {
            firstC = new CNode(str.charAt(0));
            length++;
            CNode currentNode = firstC;
        }
        if (offset == 0) {
            CNode firstNode = new CNode(str.charAt(0));
            CNode currentNode = firstNode;
            CNode afterNode = firstC;

            if (str.length() == 1) {
                firstNode = new CNode(str.charAt(0));
                currentNode = firstNode;
                length++;

                firstC = firstNode;
                currentNode.next = firstC;
                firstC.prev = currentNode;
                return this;

            } else {
                length++;

                for (int i = 1; i < str.length(); i++) {
                    CNode newNode = new CNode(str.charAt(i));
                    currentNode.next = newNode;
                    newNode.prev = currentNode;
                    currentNode = newNode;

                    length++;
                }

                currentNode.next = afterNode;
                afterNode.prev = currentNode;
                currentNode = afterNode;

                while (currentNode.next != firstC) {
                    currentNode = currentNode.next;

                }
                firstC = firstNode;

                currentNode.next = firstC;
                firstC.prev = currentNode;
                return this;

            }
        }
        if (offset == length) {
            CNode currentNode = firstC;
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;

            }
            for (int i = 0; i < str.length(); i++) {
                CNode newNode = new CNode(str.charAt(i));
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentNode = newNode;
                length++;
            }
            firstC = firstC;
            currentNode.next = firstC;
            firstC.prev = currentNode;
            return this;
        }
        {
            CNode currentNode = firstC;
            while (currentNode.next != firstC) {

                CNode afterNode = currentNode.next;

                counter++;
                if (counter == offset) {
                    for (int i = 0; i < str.length(); i++) {
                        CNode newNode = new CNode(str.charAt(i));
                        currentNode.next = newNode;
                        newNode.prev = currentNode;
                        currentNode = newNode;

                        length++;
                    }
                    currentNode.next = afterNode;
                    afterNode.prev = currentNode;
                }
                currentNode = currentNode.next;
            }
            firstC = firstC;
            currentNode.next = firstC;
            firstC.prev = currentNode;
        }

        return this;
    }
    // Insert character c into the current MyStringBuilder at index
    // "offset" and return the current MyStringBuilder.  If "offset" ==
    // length, this is the same as append.  If "offset" is invalid, 
    // do nothing.

    public MyStringBuilder insert(int offset, char c) {
        if (offset < 0 || offset > length) {
            return this;
        }
        CNode currentNode = firstC;
        int counter = 0;
        if (counter == offset) {
            currentNode = new CNode(c);
            length++;
        }
        {
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
                if (counter == offset) {
                    CNode newNode = new CNode(c);
                    currentNode.next = newNode;
                    newNode.prev = currentNode;
                    currentNode = newNode;
                    length++;
                }

            }

        }

        currentNode.next = firstC;
        firstC.prev = currentNode;
        return this;
    }

    // Return the length of the current MyStringBuilder
    public int length() {
        return length;
    }

    // Delete the substring from "start" to "end" - 1 in the current
    // MyStringBuilder, then insert String "str" into the current
    // MyStringBuilder starting at index "start", then return the current
    // MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
    // If "end" is past the end of the MyStringBuilder, only delete until the
    // end of the MyStringBuilder, then insert.  This method should be done
    // as efficiently as possible.  In particular, you may NOT simply call
    // the delete() method followed by the insert() method, since that will
    // require an extra traversal of the linked list.
    public MyStringBuilder replace(int start, int end, String str) {
        int counter = 0;
        int starts = start;
        int ends = end;
        CNode currentNode = firstC;
        if (start < 0 || start > length || end <= start) {
            return this;
        }
        if (counter == start) {
            CNode copyNode = firstC;

            while (counter == start && counter < end) {
                length--;
                firstC = firstC.next;
                counter++;
                start++;

            }
            CNode afterNode = firstC;
            CNode newNode = new CNode(str.charAt(0));
            currentNode = newNode;
            CNode firstNode = newNode;

            length++;
            for (int i = 1; i < str.length(); i++) {
                newNode = new CNode(str.charAt(i));
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentNode = newNode;
                length++;
            }
            currentNode.next = afterNode;
            afterNode.prev = currentNode;
            currentNode = afterNode;
            while (currentNode.next != copyNode) {
                currentNode = currentNode.next;

            }

            firstC = firstNode;
            currentNode.next = firstC;
            firstC.prev = currentNode;
            return this;
        }
        if (end > length) {
            end = length;
            currentNode = firstC;
            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
                while (counter == start && counter < end) {
                    CNode nodeBefore = currentNode.prev;
                    CNode nodeAfter = currentNode.next;
                    nodeBefore.next = nodeAfter;
                    nodeAfter.prev = nodeBefore;
                    start++;
                    currentNode = nodeAfter;
                    length--;
                    counter++;
                }
                if (currentNode.next == firstC) {
                    currentNode.next = firstC;
                    break;
                }

            }

            for (int i = 0; i < str.length(); i++) {
                CNode newNode = new CNode(str.charAt(i));
                currentNode.next = newNode;
                newNode.prev = currentNode;
                currentNode = newNode;
                length++;
            }

            currentNode.next = firstC;
            firstC.prev = currentNode;
            return this;
        } else {

            while (currentNode.next != firstC) {
                currentNode = currentNode.next;
                counter++;
                while (counter == start && counter < end) {
                    CNode nodeBefore = currentNode.prev;
                    CNode nodeAfter = currentNode.next;
                    nodeBefore.next = nodeAfter;
                    nodeAfter.prev = nodeBefore;
                    start++;
                    currentNode = nodeAfter;
                    length--;
                    counter++;
                }
                if (counter == end) {
                    CNode nextNode = currentNode;
                    CNode newCurrNode = currentNode.prev;
                    currentNode = newCurrNode;
                    for (int i = 0; i < str.length(); i++) {
                        CNode newNode = new CNode(str.charAt(i));
                        currentNode.next = newNode;
                        newNode.prev = currentNode;
                        currentNode = newNode;
                        length++;
                    }
                    currentNode.next = nextNode;
                    nextNode.prev = currentNode;
                    currentNode = nextNode;
                }
            }

        }

        currentNode.next = firstC;
        firstC.prev = currentNode;
        return this;

    }
    // Return as a String the substring of characters from index "start" to
    // index "end" - 1 within the current MyStringBuilder.  For this method
    // you should do the following:
    // 1) Create a character array of the appropriate length
    // 2) Fill the array with the proper characters from your MyStringBuilder
    // 3) Return a new String using the array as an argument, or
    //    return new String(charArray);

    public String substring(int start, int end) {
        char[] array = new char[length];
        int counter = 0;
        CNode curr = firstC;
        if (counter == start) {
            array[counter] = curr.data;
            start++;
        }
        while (curr.next != firstC) {
            counter++;
            curr = curr.next;
            while (counter == start && start < end) {
                array[counter] = (curr.data);
                curr = curr.next;
                counter++;
                start++;
            }

        }

        return new String(array);
    }

    // Return as a String the reverse of the contents of the MyStringBuilder.  Note
    // that this does NOT change the MyStringBuilder in any way.  See substring()
    // above for the basic approach.
    public String revString() {// use the current.prev 

        char[] array = new char[length];
        char[] newArray = new char[length];
        int counter = 0;
        CNode curr = firstC;
        array[counter] = (curr.data);

        while (curr.next != firstC) {
            counter++;
            curr = curr.next;
            array[counter] = (curr.data);
        }
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[counter];
            counter--;
        }
        return new String(newArray);

    }

    // You must use this inner class exactly as specified below.  Note that
    // since it is an inner class, the MyStringBuilder class MAY access the
    // data, next and prev fields directly.
    private class CNode {

        private char data;
        private CNode next, prev;

        public CNode(char c) {
            data = c;
            next = null;
            prev = null;
        }

        public CNode(char c, CNode n, CNode p) {
            data = c;
            next = n;
            prev = p;
        }
    }
}
