package pl.m4.kata.wordchains;

import pl.m4.kata.wordchains.dictionary.Dictionary;
import pl.m4.kata.wordchains.dictionary.DictionaryException;
import pl.m4.kata.wordchains.dictionary.KataDictionary;
import pl.m4.kata.wordchains.wordchain.WordChain;
import pl.m4.kata.wordchains.wordchain.WordChainException;
import pl.m4.kata.wordchains.wordchain.WordChainImpl;

public class WordChains {
    public static void main(String[] args) {
        validateInputs(args);
        String startWord = args[0];
        String endWord = args[1];
        Dictionary dictionary = null;

        try {
            dictionary = new KataDictionary(startWord.length());
            checkWordInDictionary(dictionary, startWord);
            checkWordInDictionary(dictionary, endWord);
        } catch (DictionaryException e) {
            System.out.println("Can not read file, check if file exist.");
            System.exit(-2);
        }

        WordChain wordChain = new WordChainImpl(dictionary, startWord, endWord);

        try {
            wordChain.findWordsPath().forEach(System.out::println);
        } catch (WordChainException e) {
            System.out.println("Can not find path from: " + startWord + " to: " + endWord);
        }
    }

    private static void validateInputs(String[] args) {
        if (args.length < 2) {
            System.out.println("Must be at least two words. Additional words will be ignored.");
            System.exit(-1);
        }
        if (args[0].length() != args[1].length()) {
            System.out.println("Length of words must be equals.");
            System.exit(-1);
        }
    }

    private static void checkWordInDictionary(Dictionary dictionary, String word) {
        if (!dictionary.findWord(word).isPresent()) {
            System.out.println("Can not find '" + word + "' in dictionary. Please use another word.");
            System.exit(-3);
        }
    }
}
