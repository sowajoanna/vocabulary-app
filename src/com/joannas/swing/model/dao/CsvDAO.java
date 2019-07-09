package com.joannas.swing.model.dao;

import com.joannas.swing.conroller.exceptions.CantLoadDictionaryException;
import com.joannas.swing.model.Dictionary;
import com.joannas.swing.model.Item;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class CsvDAO implements DAO{
    @Override
    public Dictionary loadDictionary(File file) throws CantLoadDictionaryException {
        Collection<Item> items = new HashSet<>();
        try (Scanner readFromFile = new Scanner(file)) {
            while (readFromFile.hasNext()) {
                items.add(new Item(readFromFile.nextLine().toLowerCase()));
            }
            return new Dictionary(items);
        } catch (IOException e) {
            throw new CantLoadDictionaryException("Can't load dictionary from file");
        }
    }
}
