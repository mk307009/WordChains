package pl.m4.kata.wordchains.wordchain;

import pl.m4.kata.wordchains.dictionary.Dictionary;

import java.util.List;
import java.util.stream.Collectors;

public class WordChainImpl implements WordChain {
    private String startWord;
    private String endWord;
    private Dictionary dictionary;
    private List<Node> nodes;

    public WordChainImpl(Dictionary dictionary, String startWord, String endWord) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.dictionary = dictionary;
        this.nodes = readAndMapWordsToNodes();
    }

    @Override
    public List<String> findWordsPath() {
        return null;
    }

    private List<Node> readAndMapWordsToNodes() {
        return dictionary.getWords()
                .parallelStream()
                .map(Node::new)
                .collect(Collectors.toList());
    }
}
