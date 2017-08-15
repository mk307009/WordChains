package pl.m4.kata.wordchains.dictionary;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

public class DictionaryTest {
    private static final String DOG = "dog";
    private static final String RUBY = "ruby";
    private static final String WORD_NOT_EXISTS = "notExistingWordIThinkSo";

    @Test
    public void testSetOf5LengthWordsIsNotEmpty() throws DictionaryException {
        Dictionary dictionary = new KataDictionary(5);
        Set<String> result = dictionary.getWords();
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void testSetOf3LengthWordsIsNotEmpty() throws DictionaryException {
        Dictionary dictionary = new KataDictionary(3);
        Set<String> result = dictionary.getWords();
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void testFindDogWord() throws DictionaryException {
        Dictionary dictionary = new KataDictionary(DOG.length());
        Optional<String> result = dictionary.findWord(DOG);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testFindRubyWord() throws DictionaryException {
        Dictionary dictionary = new KataDictionary(RUBY.length());
        Optional<String> result = dictionary.findWord(RUBY);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testFindNotExistingWord() throws DictionaryException {
        Dictionary dictionary = new KataDictionary(WORD_NOT_EXISTS.length());
        Optional<String> result = dictionary.findWord(WORD_NOT_EXISTS);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void test5LengthWordsHave5Chars() throws DictionaryException {
        Dictionary dictionary = new KataDictionary(5);
        Set<String> result = dictionary.getWords();
        for (String word : result)
            Assert.assertTrue(word.length() == 5);
    }
}
