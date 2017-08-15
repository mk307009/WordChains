package pl.m4.kata.wordchains.wordchain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.m4.kata.wordchains.dictionary.Dictionary;
import pl.m4.kata.wordchains.dictionary.DictionaryException;
import pl.m4.kata.wordchains.dictionary.KataDictionary;

import java.util.Arrays;
import java.util.List;

public class WordChainTest {
    private List<String> catDogPath;
    private List<String> leadGoldPath;
    private List<String> rubyCodePath;
    private Dictionary dictionary3Length;
    private Dictionary dictionary4Length;

    @Before
    public void init() throws DictionaryException {
        catDogPath = Arrays.asList("cat", "cot", "cog", "dog");
        leadGoldPath = Arrays.asList("lead", "load", "goad", "gold");
        rubyCodePath = Arrays.asList("ruby", "rubs", "robs", "rods", "rode", "code");
        dictionary3Length = new KataDictionary(3);
        dictionary4Length = new KataDictionary(4);
    }

    //cat, cot, cog, dog
    @Test
    public void testPathFromCatToDogHas4Words() {
        WordChain wordChain = new WordChainImpl(dictionary3Length,"cat", "dog");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals(4, result.size());
    }

    @Test
    public void testPathFromCatToDogStartsWithCatWord() {
        WordChain wordChain = new WordChainImpl(dictionary3Length,"cat", "dog");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals("cat", result.get(0));
    }

    @Test
    public void testPathFromCatToDogEndsWithDogWord() {
        WordChain wordChain = new WordChainImpl(dictionary3Length,"cat", "dog");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals("dog", result.get(result.size() - 1));
    }

    @Test
    public void testPathFromCatToDogHasCatCotCogDogWords() {
        WordChain wordChain = new WordChainImpl(dictionary3Length,"cat", "dog");
        List<String> result = wordChain.findWordsPath();
        for (int i = 0; i < result.size(); i++)
            Assert.assertEquals(catDogPath.get(i), result.get(i));
    }

    //lead, load, goad, gold
    @Test
    public void testPathFromLeadToGoldHas4Words() {
        WordChain wordChain = new WordChainImpl(dictionary4Length,"lead", "gold");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals(4, result.size());
    }

    @Test
    public void testPathFromLeadToGoldStartsWithLeadWord() {
        WordChain wordChain = new WordChainImpl(dictionary4Length,"lead", "gold");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals("lead", result.get(0));
    }

    @Test
    public void testPathFromLeadToGoldHasLeadLoadGoadGoldWords() {
        WordChain wordChain = new WordChainImpl(dictionary4Length,"lead", "gold");
        List<String> result = wordChain.findWordsPath();
        for (int i = 0; i < result.size(); i++)
            Assert.assertEquals(leadGoldPath.get(i), result.get(i));
    }

    @Test
    public void testPathFromLeadToGoldEndsWithGoldWord() {
        WordChain wordChain = new WordChainImpl(dictionary4Length,"lead", "gold");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals("gold", result.get(result.size() - 1));
    }

    //ruby, rubs, robs, rods, rode, code
    @Test
    public void testPathFromRubyToCodeHas6Words() {
        WordChain wordChain = new WordChainImpl(dictionary4Length,"ruby", "code");
        List<String> result = wordChain.findWordsPath();
        Assert.assertEquals(6, result.size());
    }

    @Test
    public void testPathFromRubyToCodeHasExpectedWords() {
        WordChain wordChain = new WordChainImpl(dictionary4Length,"ruby", "code");
        List<String> result = wordChain.findWordsPath();
        for (int i = 0; i < result.size(); i++)
            Assert.assertEquals(rubyCodePath.get(i), result.get(i));
    }
}
