package others.leetc.arrays;

import util.Creator;
import util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/word-ladder-ii/discuss/899147/Java-Single-pass-BFS-solution-or-with-Comments
//https://leetcode.com/problems/word-ladder-ii

/**
 * We start from start word and keep on looking for valid next words in bfs way. After each level is generated completely,
 * we remove all nodes of that level from the given dictionary so that they are not found again during bfs (because if they are
 * that would be a longer path).
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        Queue<String> wordQueue = new LinkedList<>();
        // keeps comma separated path so far
        Queue<String> solutionQueue = new LinkedList<>();

        wordQueue.add(beginWord);
        solutionQueue.add(beginWord);
        while(!wordQueue.isEmpty()) {
            int size=wordQueue.size();
            boolean found = false;
            Set<String> addedWords = new HashSet<>();
            while(size>0) {
                String word = wordQueue.remove();
                String solution = solutionQueue.remove();

                if (word.equals(endWord)) {
                    result.add(Arrays.asList(solution.split(",")));
                    // we continue to find all other answers at the same level
                    found=true;
                } else if (!found) {
                    // If we already found an answer, no need to expand anymore.
                    for(String newWord : generateValidWords(words, word)) {
                        wordQueue.add(newWord);
                        addedWords.add(newWord);
                        solutionQueue.add(solution + "," + newWord);
                    }
                }

                size--;
            }

            // Remove all the words generated at this level from valid words
            // Since reusing them would mean a longer path.
            words.removeAll(addedWords);
            if (found) break;
        }

        return result;
    }

    private List<String> generateValidWords(Set<String> words, String word) {
        List<String> newWords = new ArrayList<>();
        char[] wordChars = word.toCharArray();
        for(int i=0;i<word.length();i++) {
            char oldChar = wordChars[i];
            for (char a = 'a'; a <= 'z'; a++){
                if (wordChars[i] != a) {
                    wordChars[i] = a;
                    String newWord = new String(wordChars);
                    if (words.contains(newWord)) {
                        newWords.add(newWord);
                    }
                }
            }
            wordChars[i] = oldChar;
        }

        return newWords;
    }

    public static void main(String[] args) {
        WordLadderII wl2 = new WordLadderII();
        Printer.printLists(wl2.findLadders("hit",
                "cog",
                Creator.getList("hot", "dot", "dog", "lot", "log", "cog")));
        Printer.printLists(wl2.findLadders("red",
                "tax",
                Creator.getList("ted","tex","red","tax","tad","den","rex","pee")));
        long t1 = System.currentTimeMillis();
        Printer.printLists(wl2.findLadders("cet",
                "ism",
                        Creator.getList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob")));
        System.out.println(System.currentTimeMillis() - t1);
    }

}
