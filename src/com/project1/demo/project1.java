package com.project1.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class project1 {
    List<Block> blockchain = new ArrayList<>();
    int prefix = 4;
    String prefixString = new String(new char[prefix]).replace('\0','0');

    public static void main(String[] args) {
//        Block block = new  Block("2021/03/09", "28917498135601974970310", 344L);
//        System.out.print(block.getHash());
    }

    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess(){
        Block newBlock = new Block(
                "This is a New Block",
                blockchain.get(blockchain.size()).getHash(),
                new Date().getTime());
        newBlock.mainBlock(prefix);
        assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
        blockchain.add(newBlock);
    }

    @Test
    public void givenBlockchain_whenValidated_thenSuccess(){
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i==0 ? "0" : blockchain.get(i - 1).getHash();
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
                    && previousHash.equals(blockchain.get(i).getPreviousHash())
                    && blockchain.get(i).getHash().substring(0,prefix).equals(prefixString);
            if (!flag) break;
        }
        assertTrue(flag);
    }

}
