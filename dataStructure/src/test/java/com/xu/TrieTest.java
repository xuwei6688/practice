package com.xu;


import com.xu.trie.FileOperation;
import com.xu.trie.Trie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author xuwei
 * @Date 2020/9/6
 * @Version V1.0
 **/
public class TrieTest {
    @Test
    public void test1() {
        List<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)){

            long startTime = System.nanoTime();


            Trie trie = new Trie();
            for(String word: words)
                trie.add(word);

            for(String word: words)
                trie.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.size());
            System.out.println("Trie: " + time + " s");
        }
    }
}
